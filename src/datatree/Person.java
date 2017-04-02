package datatree;

import java.time.LocalDate;

/**
 * Created by Urs on 3/25/2017.
 */
public interface Person {

    public enum SEX{ MALE, FEMALE}

    /**
     * Returns if Person instance is equal to an object
     * @param obj Other object to compare
     * @return 'true' if equal, 'false' otherwise
     */
    public boolean equals(Object obj);

    // Getters and Setters
    public Person getFather();

    public void setFather(Person father);

    public void removeFather();

    public Person getMother();

    public void setMother(Person mother);

    public void removeMother();

    public Person[] getChildren();

    public void addChild(Person child);

    public void removeChild(Person child);

    public String getFirstName();

    public void setFirstName(String firstName);

    public String[] getMiddleNames();

    public void addMiddleName(String middleName);

    public String getLastName();

    public void setLastName(String lastName);

    public LocalDate getBirthDate();

    public void setBirthDate(LocalDate birthDate);

    public LocalDate getDeathDate();

    public void setDeathDate(LocalDate deathDate);

    public String getDescription();

    public void setDescription(String description);

    public boolean isAlive();

    public void setAlive(boolean alive);

    public Person.SEX getSex();

    public void setSex(Person.SEX sex);

    public void setGeneration(int generation);

}
