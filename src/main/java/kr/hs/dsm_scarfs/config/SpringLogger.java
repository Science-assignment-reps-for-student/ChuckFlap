package kr.hs.dsm_scarfs.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SpringLogger implements Filter {

    Logger logger = LoggerFactory.getLogger(SpringLogger.class);

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        try {
            chain.doFilter(request, response);
            if (req.getRequestURI().contains("/socket") && !req.getRequestURI().contains("/send")) return;
            logger.info(
                    "{} [{}] {} - {}",
                    req.getRemoteHost(),
                    req.getMethod(),
                    req.getRequestURI(),
                    res.getStatus());
        } catch (Exception e) {
            logger.warn(
                    "{} [{}] {} - {} : {}",
                    req.getRemoteHost(),
                    req.getMethod(),
                    req.getRequestURI(),
                    res.getStatus(),
                    e.getMessage());
        }
    }
}