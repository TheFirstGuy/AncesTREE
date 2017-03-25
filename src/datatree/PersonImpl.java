package datatree;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by TheFirstGuy on 1/3/2017.
 */

// Stores a person's information and biological relationship

public class PersonImpl implements Person{

    // Relatives
    private Person father;
    private Person mother;
    private ArrayList<Person> children = new ArrayList<Person>();
    private Person.SEX sex_;


    // Names of person
    private String firstName_;
    private ArrayList<String> middleNames_ = new ArrayList<String>();
    private String lastName_;

    // Dates
    private Calendar birthDate_;
    private Calendar deathDate_;
    private boolean alive_;

    // Generation
    private int generation_;



    // Description
    private String description;

    // Basic constructor, other fields must be set with methods
    public PersonImpl(String firstName,
                      String lastName,
                      Calendar birthDate,
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
    public PersonImpl(String firstName,
                      String middleNames,
                      String lastName,
                      SEX sex,
                      Calendar birthDate,
                      boolean alive){
        firstName_ = firstName;
        this.middleNames_ = PersonImpl.parseMiddleNames(middleNames);
        this.lastName_ = lastName;
        this.sex_ = sex;
        this.birthDate_ = birthDate;
        this.alive_ = alive;
    }

    public PersonImpl(String firstName,
                      String lastName,
                      SEX sex,
                      Calendar birthDate,
                      Calendar deathDate,
                      boolean alive){
        firstName_ = firstName;
        this.lastName_ = lastName;
        this.sex_ = sex;
        this.birthDate_ = birthDate;
        this.deathDate_ = deathDate;
        this.alive_ = alive;
    }



    @Override
    public boolean equals(Object obj){
        boolean isEquals = false;
        if(this == obj){
            isEquals = true;
        }
        else if(obj != null && getClass() == obj.getClass()){
            PersonImpl other = (PersonImpl)obj;
            isEquals = this.alive_ == other.alive_ &&
                    this.birthDate_.equals(other.birthDate_) &&
                    this.deathDate_.equals(other.deathDate_) &&
                    this.firstName_.equals(other.firstName_) &&
                    this.middleNames_.equals(other.middleNames_) &&
                    this.lastName_.equals(other.lastName_) &&
                    this.children.equals(other.children) &&
                    this.generation_ == other.generation_;
        }
        return isEquals;
    }

   // Getters and Setters

    public Person getFather() {
        return father;
    }

    @Override
    public void setFather(Person father) {
        // Remove from the father's list of children if one existed
        if(this.father != null){
            this.father.removeChild(this);
        }
        this.father = father;

    }

    @Override
    public Person getMother() {
        return mother;
    }

    @Override
    public void setMother(Person mother) {
        // Remove from mother's list of children if one existed
        if(this.mother != null){
            this.mother.removeChild(this);
        }
        this.mother = mother;
        // Check that not already set as child

    }

    @Override
    public Person[] getChildren() {
        return (Person[])children.toArray();
    }

    @Override
    public void addChild(Person child) {
        // Check that child is not already set
        if(!children.contains(child)){
            children.add(child);
        }
        // Set at parent
        if(sex_ == SEX.MALE){
            child.setFather(this);
        }
        else if(sex_ == SEX.FEMALE){
            child.setMother(this);
        }
    }

    @Override
    public void removeChild(Person child){
        children.remove(child);
    }

    @Override
    public String getFirstName() {
        return firstName_;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName_ = firstName;
    }

    @Override
    public String[] getMiddleNames() {
        return (String[])middleNames_.toArray();
    }

    @Override
    public void addMiddleName(String middleName) {
        middleNames_.add(middleName);
    }

    @Override
    public String getLastName() {
        return lastName_;
    }

    @Override
    public void setLastName(String lastName) {
        lastName_ = lastName;
    }

    @Override
    public Calendar getBirthDate() {
        return birthDate_;
    }

    @Override
    public void setBirthDate(Calendar birthDate) {
        birthDate_ = birthDate;
    }

    @Override
    public Calendar getDeathDate() {
        return deathDate_;
    }

    @Override
    public void setDeathDate(Calendar deathDate) {
        this.deathDate_ = deathDate;
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

    @Override
    public void setSex(Person.SEX sex) {

    }

    @Override
    public Person.SEX getSex(){
        return sex_;
    }

    public void setGeneration(int generation){
        generation_ = generation;
    }

    public int getGeneration(){
        return generation_;
    }

    // Parses a string of middle names into an arraylist of names
    private static ArrayList<String> parseMiddleNames(String middleNames){
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

}
