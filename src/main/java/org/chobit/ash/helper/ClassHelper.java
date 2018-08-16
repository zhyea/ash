package org.chobit.ash.helper;

import org.chobit.ash.core.annotation.Component;
import org.chobit.ash.core.annotation.Controller;
import org.chobit.ash.core.annotation.Service;

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
public class ClassHelper {


    private static Set<Class<?>> CLASS_SET;

    static {
        CLASS_SET = getClassSet("");
    }


    /**
     * 获取所有添加Controller注解的类
     *
     * @return 添加Controller注解的类
     */
    public static Set<Class<?>> getControllerClassSet() {
        return getClassSetByAnnotation(Controller.class);
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
        beanClassSet.addAll(getControllerClassSet());
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
