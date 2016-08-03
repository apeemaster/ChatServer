import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Created by Aleksey on 03.08.2016.
 */
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Persons persons = Persons.getInstance();
        String login = req.getParameter("login");
        Person person = persons.findByName(login);
        if (person==null) {resp.setStatus(401); return;}
        person.setOnline(false);
        person.setLastConnection(LocalDateTime.now().toString());
    }

}