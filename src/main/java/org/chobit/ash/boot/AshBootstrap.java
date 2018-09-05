package org.chobit.ash.boot;

/**
 * @author robin
 */
public class AshBootstrap {


    private AshConfig config;


    public AshBootstrap() {
        this.config = new AshConfig();
    }

    public AshBootstrap source(Class<?> source) {
        this.config.setSource(source);
        return this;
    }


    public void run() {
        System.out.println(config.getSourcePackage());
    }
}
