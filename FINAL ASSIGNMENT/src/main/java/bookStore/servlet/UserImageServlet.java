package bookStore.servlet;

import bookStore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.sql.Blob;
import java.sql.SQLException;

@WebServlet("/users/image.html")
public class UserImageServlet extends HttpServlet {
    private static final Long serialVersionUID = 1l;
    @Autowired
    private UserService userService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response, Principal principal) {
        Long id = Long.parseLong(request.getParameter("id"));
        Blob blob =  userService.findById(id).get().getIcon();
       // int blobLength = (int)blob.length();
        try {
            byte[] image = blob.getBytes(1,(int)blob.length());
            response.setContentType("image/jpeg");
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(image);
            outputStream.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
