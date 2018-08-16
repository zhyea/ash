package org.chobit.ash.helper;

import org.chobit.ash.core.annotation.Delete;
import org.chobit.ash.core.annotation.Get;
import org.chobit.ash.core.annotation.Post;
import org.chobit.ash.core.annotation.Put;
import org.chobit.ash.core.bean.Handler;
import org.chobit.ash.core.bean.Request;
import org.chobit.ash.core.enums.RequestMethod;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.chobit.ash.tools.StringUtils.isBlank;

/**
 * @author robin
 */
public class ControllerHelper {

    private static Map<Request, Handler> REQUEST_MAP = new HashMap<>(64);


    static {
        init();
    }

    private static void init() {
        Set<Class<?>> set = ClassHelper.getControllerClassSet();
        if (null != set && !set.isEmpty()) {
            return;
        }
        for (Class<?> cls : set) {
            Method[] methods = cls.getDeclaredMethods();
            if (null == methods || methods.length == 0) {
                continue;
            }
            for (Method m : methods) {
                read(cls, m);
            }
        }
    }


    private static void read(Class<?> cls, Method method) {
        Request request = readRequest(method);
        if (null == request) {
            return;
        }
        Handler handler = new Handler(cls, method);
        REQUEST_MAP.put(request, handler);
    }


    private static Request readRequest(Method method) {
        if (method.isAnnotationPresent(Get.class)) {
            return readGetRequest(method);
        }
        if (method.isAnnotationPresent(Post.class)) {
            return readPostRequest(method);
        }
        if (method.isAnnotationPresent(Delete.class)) {
            return readDeleteRequest(method);
        }
        if (method.isAnnotationPresent(Put.class)) {
            return readPutRequest(method);
        }
        return null;
    }


    private static Request readGetRequest(Method method) {
        Get g = method.getAnnotation(Get.class);
        String path = g.path();
        if (isBlank(path)) {
            path = g.value();
        }

        if (isBlank(path)) {
            return null;
        }

        return new Request(path, RequestMethod.GET, g.headers());
    }


    private static Request readPostRequest(Method method) {
        Post p = method.getAnnotation(Post.class);
        String path = p.path();
        if (isBlank(path)) {
            path = p.value();
        }

        if (isBlank(path)) {
            return null;
        }

        return new Request(path, RequestMethod.POST, p.headers());
    }


    private static Request readDeleteRequest(Method method) {
        Delete d = method.getAnnotation(Delete.class);
        String path = d.path();
        if (isBlank(path)) {
            path = d.value();
        }

        if (isBlank(path)) {
            return null;
        }

        return new Request(path, RequestMethod.DELETE, d.headers());
    }


    private static Request readPutRequest(Method method) {
        Put p = method.getAnnotation(Put.class);
        String path = p.path();
        if (isBlank(path)) {
            path = p.value();
        }

        if (isBlank(path)) {
            return null;
        }

        return new Request(path, RequestMethod.PUT, p.headers());
    }


    public static Handler getHandler(RequestMethod method, String path) {
        return REQUEST_MAP.get(new Request(path, method));
    }


    public static Handler getHandler(String method, String path) {
        RequestMethod m = RequestMethod.valueOf(method.toUpperCase());
        if (null == m) {
            return null;
        }
        return getHandler(m, path);
    }


}
