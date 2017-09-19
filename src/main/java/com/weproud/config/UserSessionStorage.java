package com.weproud.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Logan. k
 */
@NoArgsConstructor
@Data
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserSessionStorage {
    public static final String API_REQUEST_ID = "request-id";
    public static final String HEADER_TOK_API_TOKEN = "tok-api-token";
    public static final String HEADER_APP_VERSION = "app-version";
    public static final String HEADER_APP_OS_NAME = "app-os-name";
    public static final String HEADER_APP_OS_VERSION = "app-os-version";

    private String requestId;
    private String requestPath;
    private String method;
    private String userAgent;
    private String ip;
    private LocalDateTime requestDateTime;

    private String token;
    private String apiKey;
    //    private String apiName;

    String appVersion;
    String appOsName;
    String appOsVersion;

    public HttpHeaders getHttpHeaders() {

        HttpHeaders headers = new HttpHeaders();
        headers.add(API_REQUEST_ID, this.requestId);

        return headers;
    }
}
