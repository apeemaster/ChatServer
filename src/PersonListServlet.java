import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
/**
 * Created by Aleksey on 03.08.2016.
 */
public class PersonListServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException
    {
        String json = Persons.getInstance().toJSON();
        if (json!=null){
            OutputStream os = resp.getOutputStream();
            os.write(json.getBytes());
        }
    }

}
