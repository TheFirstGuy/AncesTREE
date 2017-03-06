package datatree;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by TheFirstGuy on 1/3/2017.
 */

// Stores a person's information and biological relationship

public class Person {

    public enum SEX { MALE, FEMALE }

    // Relatives
    private Person father;
    private Person mother;
    private ArrayList<Person> children = new ArrayList<Person>();
    private SEX sex_;


    // Names of person
    private String firstName_;
    private ArrayList<String> middleNames_ = new ArrayList<String>();
    private String lastName_;

    // Dates
    private Date birthDate_;
    private Date deathDate;
    private boolean alive_;

    // Generation
    private int generation_;



    // Description
    private String description;

    // Basic constructor, other fields must be set with methods
    public Person(String firstName,
                  String lastName,
                  Date birthDate,
                  SEX sex,
                  boolean alive){
        firstName_ = firstName;
        this.lastName_ = lastName;
        birthDate_ = birthDate;
        sex_ = sex;
        alive_ = alive;
        generation_ = 0;
    }

    // Constructor which takes middle names which are separated by spaces
    public Person(String firstName,
                  String middleNames,
                  String lastName,
                  SEX sex,
                  Date birthDate,
                  boolean alive){
        firstName_ = firstName;
        this.middleNames_ = Person.parseMiddleNames(middleNames);
        this.lastName_ = lastName;
        this.sex_ = sex;
        this.birthDate_ = birthDate;
        this.alive_ = alive;
    }

    public Person(String firstName,
                  String lastName,
                  SEX sex,
                  Date birthDate,
                  Date deathDate,
                  boolean alive){
        firstName_ = firstName;
        this.lastName_ = lastName;
        this.sex_ = sex;
        this.birthDate_ = birthDate;
        this.deathDate = deathDate;
        this.alive_ = alive;
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
        return Person.isEqual(person1.firstName_, person1.getMiddleNames(), person1.lastName_, person2);
    }


    // Tests full equality of a person by matching all of their names
    public static boolean isEqual(String firstName,
                                  ArrayList<String> middleNames,
                                  String lastName,
                                  Person person){
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
        return Person.partialEqual(person1.firstName_, person1.lastName_, person2);
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
        // Remove from the father's list of children if one existed
        if(this.father != null){
            this.father.removeChild(this);
        }
        this.father = father;
        // Check that not already set as child
        if( !father.children.contains(this) ){
            father.children.add(this);
        }
    }

    public Person getMother() {
        return mother;
    }

    public void setMother(Person mother) {
        // Remove from mother's list of children if one existed
        if(this.mother != null){
            this.mother.removeChild(this);
        }
        this.mother = mother;
        // Check that not already set as child
        if( !mother.children.contains(this) ) {
            mother.children.add(this);
        }
    }

    public ArrayList<Person> getChildren() {
        return children;
    }

    public void addChild(Person child) {
        // Check that child is not already set
        if(!children.contains(child)){
            children.add(child);
        }
        // Set at parent
        if(sex_ == SEX.MALE){
            child.father = this;
        }
        else if(sex_ == SEX.FEMALE){
            child.mother = this;
        }
    }

    public void removeChild(Person child){
        children.remove(child);
    }

    public String getFirstName() {
        return firstName_;
    }

    public void setFirstName(String firstName) {
        this.firstName_ = firstName;
    }

    public ArrayList<String> getMiddleNames() {
        return middleNames_;
    }

    public void addMiddleNames(String middleName) {
        middleNames_.add(middleName);
    }

    public String getLastName() {
        return lastName_;
    }

    public void setLastName(String lastName) {
        lastName_ = lastName;
    }

    public Date getBirthDate() {
        return birthDate_;
    }

    public void setBirthDate(Date birthDate) {
        birthDate_ = birthDate;
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

    public boolean isAlive() {
        return alive_;
    }

    public void setAlive(boolean isAlive){
        alive_ = isAlive;
    }

    public Person.SEX getSex_(){
        return sex_;
    }

    public void setGeneration(int generation){
        generation_ = generation;
    }

    public int getGeneration(){
        return generation_;
    }

}
