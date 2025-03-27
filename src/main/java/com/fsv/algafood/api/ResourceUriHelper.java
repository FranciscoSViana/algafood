package com.fsv.algafood.api;

import jakarta.servlet.http.HttpServletResponse;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@UtilityClass
public class ResourceUriHelper {

    public static void addUriResponseHeader(Object resourceId) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(resourceId).toUri();

        HttpServletResponse httpServletResponse = ((ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes()).getResponse();

        httpServletResponse.setHeader(HttpHeaders.LOCATION, uri.toString());
    }
}
