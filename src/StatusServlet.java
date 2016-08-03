import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
/**
 * Created by Aleksey on 03.08.2016.
 */
public class StatusServlet extends HttpServlet{
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Persons persons = Persons.getInstance();
        String login = req.getParameter("login");
        Person person = persons.findByName(login);
        if (person==null) {resp.setStatus(401); return;}
        String json = person.toJSON();
        OutputStream os = resp.getOutputStream();
        os.write(json.getBytes());
    }

}
