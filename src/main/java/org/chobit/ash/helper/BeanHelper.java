package org.chobit.ash.helper;

import org.chobit.ash.tools.ReflectionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author robin
 */
public abstract class BeanHelper {


    private static final Map<Class<?>, Object> BEANS = new HashMap<>(64);


    public static void init() {
        Set<Class<?>> set = ClassHelper.getBeanClassSet();
        for (Class<?> cls : set) {
            BEANS.put(cls, ReflectionUtils.newInstance(cls));
        }
    }


    public static Map<Class<?>, Object> getBeanMap() {
        return BEANS;
    }


    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> cls) {
        if (!BEANS.containsKey(cls)) {
            throw new RuntimeException("Cannot get object by class:" + cls);
        }
        return (T) BEANS.get(cls);
    }


}
