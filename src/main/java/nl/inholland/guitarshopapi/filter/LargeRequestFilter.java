package nl.inholland.guitarshopapi.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LargeRequestFilter implements Filter {

    @Value("${application.max.content.size}")
    private int maxContentSize;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        int size = servletRequest.getContentLength();
        if (size > maxContentSize) {
            throw new ServletException("Request too large");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
