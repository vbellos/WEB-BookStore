import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CacheFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest new_request = (HttpServletRequest)request;
        HttpServletResponse new_response = (HttpServletResponse) response;
        new_response.setHeader("Cache-Control", "no-cache, no-store");
        new_response.setHeader("Pragma", "no-cache");
        new_response.setDateHeader("Expires", 0);

        chain.doFilter(new_request, new_response);
    }
}
