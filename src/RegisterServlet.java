import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * Created by Aleksey on 03.08.2016.
 */
public class RegisterServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Persons persons = Persons.getInstance();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Person person = persons.findByName(login);
        if (person!=null) {resp.setStatus(401); return;}
        else person = new Person(login, password);
        HttpSession httpSession = req.getSession();
        person.setSessionID(httpSession.getId());
        person.setOnline(true);
        persons.add(person);
    }

}