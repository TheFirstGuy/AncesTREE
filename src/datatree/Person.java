package datatree;

import java.util.Calendar;

/**
 * Created by Urs on 3/25/2017.
 */
public interface Person {

    public enum SEX{ MALE, FEMALE}

    public boolean equals(Object obj);

    public Person getFather();

    public void setFather(Person father);

    public Person getMother();

    public void setMother(Person mother);

    public Person[] getChildren();

    public void addChild(Person child);

    public void removeChild(Person child);

    public String getFirstName();

    public void setFirstName(String firstName);

    public String[] getMiddleNames();

    public void addMiddleName(String middleName);

    public String getLastName();

    public void setLastName(String lastName);

    public Calendar getBirthDate();

    public void setBirthDate(Calendar birthDate);

    public Calendar getDeathDate();

    public void setDeathDate(Calendar deathDate);

    public String getDescription();

    public void setDescription(String description);

    public boolean isAlive();

    public void setAlive(boolean alive);

    public Person.SEX getSex();

    public void setSex(Person.SEX sex);

    public void setGeneration(int generation);

}
