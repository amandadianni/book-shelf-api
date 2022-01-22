package org.example.bookshelf.config;

import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CorsFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE");
        response.addHeader("Access-Control-Expose-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With, If-Modified-Since, x-status-error");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With, x-auth-token, If-Modified-Since");
        response.addHeader("Access-Control-Allow-Credentials", "true");
        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig filterConfig) {
        // It's not necessary to do anything here.
    }

    @Override
    public void destroy() {
        // It's not necessary to do anything here.
    }
}
