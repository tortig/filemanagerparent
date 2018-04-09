package filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
       //
    }

    @Override
    public void destroy() {
        //
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("\nSecurity Filter");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String formPassword = req.getParameter("password");
        String formUsername = req.getParameter("username");

        // Logged user
        if (session != null) {
            System.out.println("User logged");
            chain.doFilter(req, resp);
        }   // User not logged
        else if (formUsername != null && formPassword != null){
            System.out.println("User Not logged");
            req.getRequestDispatcher("/login").forward(request, response);
        }
        else {
            System.out.println("User Not logged");
            resp.sendRedirect("/view/login.html");
        }
    }
}

    //Security filter in JournalDev
    /*public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
     throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        this.context.log("Requested Resource::"+uri);

        HttpSession session = req.getSession(false);

        if(session == null && !(uri.endsWith("html") || uri.endsWith("LoginServlet"))){
            this.context.log("Unauthorized access request");
            res.sendRedirect("login.html");
        }else{
            // pass the request along the filter chain
            chain.doFilter(request, response);
        }*/