package org.chobit.ash.helper;

import org.chobit.ash.annotation.Component;
import org.chobit.ash.annotation.Service;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.chobit.ash.tools.ClassUtils.getClassSet;

/**
 * Java 类操作辅助工具类
 *
 * @author robin
 */
public abstract class ClassHelper {


    private static Set<Class<?>> CLASS_SET;


    public static void init(String basePackage) {
        CLASS_SET = getClassSet(basePackage);
    }


    /**
     * 获取所有添加Service注解的类
     *
     * @return 添加Service注解的类
     */
    public static Set<Class<?>> getServiceClassSet() {
        return getClassSetByAnnotation(Service.class);
    }


    /**
     * 获取所有添加Component注解的类
     *
     * @return 添加Component注解的类
     */
    public static Set<Class<?>> getComponentClassSet() {
        return getClassSetByAnnotation(Component.class);
    }


    /**
     * 获取所有需要注入的类
     *
     * @return 需要注入的类
     */
    public static Set<Class<?>> getBeanClassSet() {
        Set<Class<?>> beanClassSet = new HashSet<>();
        beanClassSet.addAll(getServiceClassSet());
        beanClassSet.addAll(getComponentClassSet());
        return beanClassSet;
    }


    private static Set<Class<?>> getClassSetByAnnotation(Class<? extends Annotation> clazz) {
        return CLASS_SET.parallelStream()
                .filter(e -> e.isAnnotationPresent(clazz))
                .collect(Collectors.toSet());
    }


}
