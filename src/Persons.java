import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Aleksey on 03.08.2016.
 */
@XmlRootElement(name = "Persons")
public class Persons {
    private static Persons PersonList = new Persons();


    @XmlElement(name="person")
    private final List<Person> Persons = new ArrayList<>();

    private Persons(){}

    public synchronized void add(Person person){Persons.add(person);}

    public static Persons getInstance(){return PersonList;}

    public static void setInstance(Persons Persons){
        PersonList = Persons;
    }

    public List<Person> getPersons(){return Persons;}



    @Override
    public String toString() {
        return Arrays.deepToString(Persons.toArray());
    }

    public Person findByName(String login){
        for (Person person:Persons) {
            if (login.equals(person.getLogin())) return person;
        }
        return null;
    }

    public String toJSON(){
        Gson gson = new GsonBuilder().create();
        return gson.toJson(PersonList.Persons.toArray());
    }


}