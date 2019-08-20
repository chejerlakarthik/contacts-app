package com.karthik.contacts.filter;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Component
public class LoggingMDCFilter extends OncePerRequestFilter {

    private String requestIdHeader;
    private static final String DEFAULT_REQUEST_ID_HEADER = "x-request-id";

    public LoggingMDCFilter() {
        this.requestIdHeader = "x-request-id";
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String correlationIdValue = Optional.ofNullable(request.getHeader(sanitized(requestIdHeader)))
                                                .orElseGet(this::generateUniqueId);

            MDC.put(this.requestIdHeader, correlationIdValue);

            response.addHeader(this.requestIdHeader, correlationIdValue);
            filterChain.doFilter(request, response);
        }
        finally{
            MDC.remove(this.requestIdHeader);
        }
    }

    private String sanitized(String correlationIdHeader) {
        return Optional.of(correlationIdHeader)
                       .filter(x -> !StringUtils.isEmpty(x))
                       .orElse(DEFAULT_REQUEST_ID_HEADER);
    }

    private String generateUniqueId() {
        return UUID.randomUUID().toString().toLowerCase().replace("-", "");
    }
}
