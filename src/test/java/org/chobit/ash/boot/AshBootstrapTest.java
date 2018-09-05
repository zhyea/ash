package org.chobit.ash.boot;

import org.junit.Test;

/**
 * @author robin
 */
public class AshBootstrapTest {


    @Test
    public void run(){
        new AshBootstrap().source(AshBootstrapTest.class).run();
    }

}
