package org.chobit.ash.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author robin
 */
public abstract class ReflectionUtils {


    private static Logger logger = LoggerFactory.getLogger(ReflectionUtils.class);


    /**
     * 创建实例
     */
    public static Object newInstance(Class<?> clazz) {
        Object instance;
        try {
            instance = clazz.newInstance();
        } catch (Exception e) {
            logger.error("Create new instance failed.", e);
            throw new RuntimeException(e);
        }
        return instance;
    }


    /**
     * 调用方法
     */
    public static Object invokeMethod(Object obj, Method method, Object... args) {
        Object result;
        try {
            method.setAccessible(true);
            result = method.invoke(obj, args);
        } catch (Exception e) {
            logger.error("Invoke method failed.", e);
            throw new RuntimeException(e);
        }
        return result;
    }


    /**
     * 设置成员变量的值
     */
    public static void setField(Object obj, Field field, Object value) {
        try {
            field.setAccessible(true);
            field.set(obj, value);
        } catch (Exception e) {
            logger.error("Set field value failed.", e);
            throw new RuntimeException(e);
        }
    }


}
