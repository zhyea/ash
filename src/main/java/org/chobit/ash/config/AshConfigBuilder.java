package org.chobit.ash.config;

/**
 * @author robin
 */
public class AshConfigBuilder {

    private AshConfig config = new AshConfig();


    public AshConfigBuilder basePackage(String basePackage) {
        this.config.setBasePackage(basePackage);
        return this;
    }



    public AshConfig build() {
        return this.config;
    }
}
