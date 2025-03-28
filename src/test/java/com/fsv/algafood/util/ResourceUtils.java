package com.fsv.algafood.util;

import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class ResourceUtils {

    public static String getContentFromResource(String resourceName) {
        try {
            InputStream inputStream = ResourceUtils.class.getResourceAsStream(resourceName);

            return StreamUtils.copyToString(inputStream, Charset.forName("UTF-8"));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
