package org.chobit.ash.boot;

/**
 * @author robin
 */
public class AshConfig {


    private Class<?> source;


    public Class<?> getSource() {
        return source;
    }


    public void setSource(Class<?> source) {
        this.source = source;
    }

    public String getSourcePackage(){
        return source.getPackage().getName();
    }
}
