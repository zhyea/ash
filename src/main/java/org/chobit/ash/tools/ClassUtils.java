package org.chobit.ash.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import static org.chobit.ash.tools.StringUtils.isNotEmpty;

/**
 * @author robin
 */
public final class ClassUtils {


    private static Logger logger = LoggerFactory.getLogger(ClassUtils.class);

    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }


    public static Class<?> loadClass(String className) {
        return loadClass(className, true);
    }


    public static Class<?> loadClass(String className, boolean initialize) {
        Class<?> clazz;

        try {
            clazz = Class.forName(className, initialize, getClassLoader());
        } catch (Exception e) {
            logger.error("Load class:{} failed.", className, e);
            throw new RuntimeException(e);
        }

        return clazz;
    }


    public static Set<Class<?>> getClassSet(String packageName) {
        Set<Class<?>> set = new HashSet<>();
        try {
            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace('.', '/'));
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (null == url) {
                    continue;
                }
                String protocol = url.getProtocol();
                if ("file".equals(protocol)) {
                    String packagePath = url.getPath().replaceAll("%20", "");
                    addClassFromDirectory(set, packagePath, packageName);
                } else if ("jar".equals(protocol)) {
                    addClassFromJar(set, url);
                }

            }
        } catch (IOException e) {
            logger.error("Get class set at package:{} failed.", packageName, e);
        }
        return set;
    }


    private static void addClassFromDirectory(Set<Class<?>> set, String packagePath, String packageName) {
        File[] files = new File(packagePath)
                .listFiles(f -> f.isDirectory() || (f.isFile() && f.getName().endsWith(".class")));
        for (File f : files) {
            String fileName = f.getName();
            if (f.isFile()) {
                String className = fileName.substring(0, fileName.lastIndexOf("."));
                if (isNotEmpty(packageName)) {
                    className = packageName + "." + className;
                }
                addClass(set, className);
            } else if (f.isDirectory()) {
                String packagePath0 = fileName;
                if (isNotEmpty(packagePath)) {
                    packagePath0 = packagePath + File.separator + f.getName();
                }
                String packageName0 = fileName;
                addClassFromDirectory(set, packagePath0, packageName0);
            }
        }
    }


    private static void addClassFromJar(Set<Class<?>> set, URL url) throws IOException {
        URLConnection conn = url.openConnection();
        if (null == conn) {
            return;
        }
        JarURLConnection jarConn = (JarURLConnection) conn;
        JarFile jarFile = jarConn.getJarFile();
        if (null == jarFile) {
            return;
        }
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            String entryName = entry.getName();
            if (entryName.endsWith(".class")) {
                String className = entryName.substring(0, entryName.lastIndexOf("."))
                        .replace('.', '/');
                addClass(set, className);
            }
        }
    }


    private static void addClass(Set<Class<?>> set, String className) {
        set.add(loadClass(className, false));
    }


}
