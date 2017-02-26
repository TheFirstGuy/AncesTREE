package datatree;


import java.util.*;

/**
 * Created by TheFirstGuy on 1/3/2017.
 */
public class DataTree {

    /**
     * List containing all persons
     */
    private HashSet<Person> family_ = new HashSet<Person>();

    //
    /**
     * List of relationships between persons
     */
    private ArrayList<Relationship> relationships_ = new ArrayList<Relationship>();


    /**
     * This method finds person in list which fully matches given name.
     * @param firstName The person's first name
     * @param middleNames The person's middle names
     * @param lastNames The person's last name
     * @return The person who matches the given name. Null if no match found
     */
    public Person find(String firstName, ArrayList<String> middleNames, String lastNames){
        Person match = null;
        for(Person person : family_){
            if(Person.isEqual(firstName, middleNames, lastNames, person)) {
                match = person;
                break;
            }
        }
        return match;
    }

    /**
     * Returns a list of Person who have the first and last name provided
     * @param firstName The person's first name
     * @param lastName The person's last name
     * @return List of person who have first and last name
     */
    public ArrayList<Person> find(String firstName, String lastName){
        ArrayList<Person> people = new ArrayList<Person>();
        for(Person person : family_){
            if(Person.partialEqual(firstName, lastName, person)){
                people.add(person);
                break;
            }
        }
        return people;
    }

    // Finds if Person is contained in family

    /**
     * Verifies if a Person is in the family
     * @param person The person object to search for
     * @return 'true' if person is in family, 'false' otherwise
     */
    public boolean inFamily(Person person){
        return family_.contains(person);
    }

    /**
     * Adds a person to the family using the birth date as identifier
     * @param firstName The person's first name
     * @param lastName The person's last name
     * @param birthdate The person's date of birth
     */
    public void addPerson(String firstName, String lastName, Date birthdate, Person.SEX sex, boolean alive){
        Person person = new Person(firstName, lastName,  birthdate, sex, alive);
        family_.add(person);
    }

    /**
     * Adds a relationship to the data tree
     * @param partner1 The first person in the relationship
     * @param partner2 The second person in the relationship
     * @param startDate The date the relationship began, null if unknown
     * @param stopDate The data the relationship ended null if unknown
     * @return 'True" if relationship added successfully. 'False' otherwise.
     */
    public boolean addRelationship(Person partner1, Person partner2, Date startDate, Date stopDate){
        // Check that partners are not the same
        if(partner1 == partner2){
            return false;
        }
        Relationship existingRelationship = null;
        // Check to see if relationship is found
        for(int i = 0; i < relationships_.size() && existingRelationship == null; i++){
            if(Relationship.isEqual(partner1,partner2, relationships_.get(i))){
                existingRelationship = relationships_.get(i);
            }
        }
        // If an existing relationship exists, modify the start and end date
        if(existingRelationship != null) {
            existingRelationship.startDate = startDate;
            existingRelationship.stopDate = stopDate;
        }
        // Create a new relationship and add to list
        else{
            Relationship relationship = new Relationship(partner1, partner2, startDate, stopDate);
            relationships_.add(relationship);
        }
        return true;
    }

    /**
     * Gets all of a persons siblings
     * @param person Person of interest.
     * @return Set of persons who are silbings
     */
    public HashSet<Person> getSiblings(Person person){
        HashSet<Person> siblings = new HashSet<Person>();
        // Get siblings on the father's side
        if(person.getFather() != null){
            siblings.addAll(person.getFather().getChildren());
        }
        // Get siblings on the mothers side
        if(person.getMother() != null){
            siblings.addAll(person.getMother().getChildren());
        }
        return siblings;
    }

    /**
     * Gets a set of persons which the person of interest has had children with
     * @param person
     * @return Set of persons which the person of interest has had children with
     */
    public HashSet<Person> getPartners(Person person){

        HashSet<Person> partners = new HashSet<Person>();
        ArrayList<Person> children = person.getChildren();
        for(Person child : children){
            if(person.getSex_() == Person.SEX.MALE){
                partners.add(child.getMother());
            }
            else if(person.getSex_() == Person.SEX.FEMALE){
                partners.add(child.getFather());
            }
        }
        return partners;
    }


}
