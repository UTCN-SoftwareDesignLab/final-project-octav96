package bookStore.servlet;

import bookStore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    private static final Long serialVersionUID = 1l;
    @Autowired
    UserService userService;

    @Override
    public void init(ServletConfig config) {
        try {
            super.init(config);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        request.setAttribute("users", userService.findAll());
        try {
            request.getRequestDispatcher("/resources/templates/list_all_users_admin.html").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
