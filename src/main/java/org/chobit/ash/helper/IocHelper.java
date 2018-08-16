package org.chobit.ash.helper;

import org.chobit.ash.core.annotation.Autowired;
import org.chobit.ash.tools.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author robin
 */
public class IocHelper {

    static {
        inject();
    }

    public static void inject() {
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if (null == beanMap || beanMap.isEmpty()) {
            return;
        }

        for (Map.Entry<Class<?>, Object> e : beanMap.entrySet()) {
            Class<?> cls = e.getKey();
            Object instance = e.getValue();
            Field[] fields = cls.getDeclaredFields();
            if (null == fields || fields.length == 0) {
                continue;
            }
            for (Field field : fields) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    continue;
                }

                Class<?> fieldClass = field.getDeclaringClass();
                Object fieldInstance = beanMap.get(fieldClass);
                if (null != fieldInstance) {
                    ReflectionUtils.setField(instance, field, fieldInstance);
                }
            }
        }
    }
}
