package org.chobit.ash.core;

import org.chobit.ash.boot.AshConfig;
import org.chobit.ash.tools.ClassUtils;
import org.chobit.ash.tools.ReflectionUtils;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author robin
 */
public class BeanFactory {


    private static ConcurrentHashMap<Class<?>, Object> beanCache = new ConcurrentHashMap<>(16);


    private String basePackage;


    public BeanFactory(AshConfig config) {
        this.basePackage = config.getSourcePackage();

    }


    private void init(){
        Set<Class<?>> set = ClassUtils.getClassSet(basePackage);
        for (Class<?> cls : set) {
            beanCache.put(cls, ReflectionUtils.newInstance(cls));
        }
    }



    @SuppressWarnings("unchecked")
    public  <T> T getBean(Class<T> cls) {
        if (!beanCache.containsKey(cls)) {
            throw new RuntimeException("Cannot get object by class:" + cls);
        }
        return (T) beanCache.get(cls);
    }



}
