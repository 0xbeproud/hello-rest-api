package com.weproud.config.interceptor;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import com.weproud.config.UserSessionStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;


/**
 * @author Logan.k
 */
@Slf4j
@Component
public class ApiHttpInterceptor extends HandlerInterceptorAdapter {
    public static final String HTTP_USER_AGENT = "User-Agent";
    private final TimeBasedGenerator uuidGenerator = Generators.timeBasedGenerator(EthernetAddress.fromInterface());

    @Autowired
    private UserSessionStorage storage;

    public ApiHttpInterceptor() {
        super();
        log.info("HttpInterceptor() 생성");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

//        log.info("-- request header User-Agent: {}", request.getHeader("User-Agent"));

        String method = request.getMethod();
        String requestPath = request.getContextPath() + request.getServletPath();
        String ip = getRequestIpAddress(request);
        String requestId = uuidGenerator.generate().toString();
        String userAgent = request.getHeader(HTTP_USER_AGENT);
//        log.info("---- preHandle[{}] {} {}", requestId, method, requestPath);

        this.storage.setRequestId(requestId);
        this.storage.setMethod(method);
        this.storage.setRequestPath(requestPath);
        this.storage.setIp(ip);
        this.storage.setUserAgent(userAgent);
        this.storage.setRequestDateTime(LocalDateTime.now());

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    public String getRequestIpAddress(HttpServletRequest request) {

        String requestIpAddress = request.getHeader("X-FORWARDED-FOR");
        if (StringUtils.isEmpty(requestIpAddress)) {
            requestIpAddress = request.getRemoteAddr();
        }
        String ipArray[] = requestIpAddress.split(",");
        if (ipArray.length > 1) {
            requestIpAddress = ipArray[0];
        }
        if ("0:0:0:0:0:0:0:1".equals(requestIpAddress)) {
            requestIpAddress = "127.0.0.1";
        }

        return requestIpAddress;
    }
}