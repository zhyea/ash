package org.chobit.ash.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * IO操作工具集
 *
 * @author robin
 */
public class IOUtils {


    private static Logger logger = LoggerFactory.getLogger(IOUtils.class);

    public static String read(InputStream input) {
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (Exception e) {
            logger.error("Read from InputStream failed.", e);
        }
        return builder.toString();
    }


}
