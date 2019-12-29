package jee.task22.servlet;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class LogFilter implements Filter {
    private Logger logger = LogManager.getLogger(LogFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
        logger.info("init logFilter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException,
            ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        String uri = "Requested Uri::" + req.getRequestURI();
        logger.info(uri);
        String method = "Requested Method::" + req.getMethod();
        logger.info(method);
        if ("/mobiles/".equalsIgnoreCase(req.getRequestURI()) || StringUtils.isNotBlank(req.getHeader("referer"))) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            logger.info("кто то лезет без авторизации");
        }
    }

    @Override
    public void destroy() {
        logger.info("destroy logFilter");
    }
}
