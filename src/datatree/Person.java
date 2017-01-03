package datatree;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by TheFirstGuy on 1/3/2017.
 */

// Stores a person's information and biological relationships

public class Person {

    // Relatives
    private Person father;
    private Person mother;
    private ArrayList<Person> children;


    // Names of person
    private String firstName;
    private ArrayList<String> middleNames;
    private String lastName;

    // Dates
    private Date birthDate;
    private Date deathDate;

    // Description
    private String description;

    // Basic constructor, other fields must be set with methods
    public Person(String firstName,
                  String lastName,
                  Date birthDate){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    // Constructor which takes middle names which are separated by spaces
    public Person(String firstName,
                  String middleNames,
                  String lastName,
                  Date birthDate){
        this.firstName = firstName;
        this.middleNames = Person.parseMiddleNames(middleNames);
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    // Parses a string of middle names into an arraylist of names
    public static ArrayList<String> parseMiddleNames(String middleNames){
        // TODO: Add support for multiword names
        // Split based on space.
        String[] names = middleNames.split(" ");

        // If no names return empty list
        if(names == null){
            return new ArrayList<String>(0);
        }
        // Otherwise allocate only enough space for list
        else{
            ArrayList<String> middleNameList = new ArrayList<String>(names.length);

            // Copy names
            for( int i = 0; i < names.length; i++){
                middleNameList.add(names[i]);
            }
            return middleNameList;
        }
    }

    // Test full equality of a person by matching all of their names
    public static boolean isEqual(Person person1, Person person2){
        return Person.isEqual(person1.firstName, person1.getMiddleNames(), person1.lastName, person2);
    }


    // Tests full equality of a person by matching all of their names
    public static boolean isEqual(String firstName, ArrayList<String> middleNames, String lastName, Person person){
        boolean isEqual = false;
        isEqual = person.getFirstName() == firstName;
        isEqual = isEqual && person.getLastName() == lastName;
        for( int i = 0; isEqual && middleNames.size() == person.getMiddleNames().size() && i < middleNames.size(); i++){
            isEqual = isEqual && middleNames.get(i) == person.getMiddleNames().get(i);
        }
        return isEqual;
    }

    // Tests partial equality of a person by matching the first and last name
    public static boolean partialEqual(Person person1, Person person2){
        return Person.partialEqual(person1.firstName, person1.lastName, person2);
    }

    // Tests partial equality of a person by matching the first and last name
    public static boolean partialEqual(String firstName, String lastName, Person person){
        boolean isEqual = false;
        isEqual = person.getFirstName() == firstName;
        isEqual = isEqual && person.getLastName() == lastName;
        return  isEqual;
    }


   // Getters and Setters

    public Person getFather() {
        return father;
    }

    public void setFather(Person father) {
        this.father = father;
    }

    public Person getMother() {
        return mother;
    }

    public void setMother(Person mother) {
        this.mother = mother;
    }

    public ArrayList<Person> getChildren() {
        return children;
    }

    public void addChild(Person child) {
        children.add(child);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public ArrayList<String> getMiddleNames() {
        return middleNames;
    }

    public void addMiddleNames(String middleName) {
        middleNames.add(middleName);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(Date deathDate) {
        this.deathDate = deathDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}
