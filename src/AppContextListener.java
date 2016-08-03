import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by Aleksey on 03.08.2016.
 */
public class AppContextListener implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext cnt = servletContextEvent.getServletContext();
        String fileName = cnt.getInitParameter("PERSON_LIST");
        File file;
        try {
            file = new File(fileName);
            JAXBContext jaxbContext = JAXBContext.newInstance(Persons.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Persons persons = (Persons) unmarshaller.unmarshal(file);
            Persons.setInstance(persons);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext cnt = servletContextEvent.getServletContext();
        String fileName = cnt.getInitParameter("PERSON_LIST");
        Persons persons = Persons.getInstance();
        try {
            File file = new File(fileName);
            JAXBContext jaxbContext = JAXBContext.newInstance(Persons.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(persons,file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}