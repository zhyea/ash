package org.chobit.ash.boot;

/**
 * @author robin
 */
public class AshConfig {


    private Class<?> source;


    public AshConfig(Class<?> source) {
        this.source = source;
    }

    public Class<?> getSource() {
        return source;
    }

    public String getSourcePackage() {
        return source.getPackage().getName();
    }
}
