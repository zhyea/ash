package org.chobit.ash.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;

/**
 * 编解码工具集
 *
 * @author robin
 */
public class CodecUtils {

    private static Logger logger = LoggerFactory.getLogger(CodecUtils.class);

    /**
     * 编码URL
     *
     * @param src 原始URL
     * @return 编码后的URL
     */
    public static String encodeURL(String src) {
        try {
            return URLEncoder.encode(src, Charset.defaultCharset().name());
        } catch (Exception e) {
            logger.error("Encode url failed.", e);
            throw new RuntimeException(e);
        }
    }


    /**
     * 解码URL
     *
     * @param src 编码后的URL
     * @return 原始URL
     */
    public static String decodeURL(String src) {
        try {
            return URLDecoder.decode(src, Charset.defaultCharset().name());
        } catch (Exception e) {
            logger.error("Decode url failed.", e);
            throw new RuntimeException(e);
        }
    }

}
