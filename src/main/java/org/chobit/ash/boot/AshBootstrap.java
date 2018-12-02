package org.chobit.ash.boot;

import org.chobit.ash.helper.BeanHelper;
import org.chobit.ash.helper.ClassHelper;
import org.chobit.ash.helper.IocHelper;

/**
 * @author robin
 */
public final class AshBootstrap {


    private final AshConfig config;


    public AshBootstrap(Class<?> source) {
        this.config = new AshConfig(source);
    }


    public void run() {
        ClassHelper.init(config.getSourcePackage());
        BeanHelper.init();
        IocHelper.init();
    }
}
