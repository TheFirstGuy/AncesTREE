package datatree;


import java.util.ArrayList;
import java.util.Date;

/**
 * Created by TheFirstGuy on 1/3/2017.
 */
public class DataTree {

    /**
     * List containing all persons
     */
    private ArrayList<Person> family_ = new ArrayList<Person>();

    //
    /**
     * List of relationships between persons
     */
    private ArrayList<Relationship> relationships = new ArrayList<Relationship>();


    /**
     * This method finds person in list which fully matches given name.
     * @param firstName The person's first name
     * @param middleNames The person's middle names
     * @param lastNames The person's last name
     * @return The person who matches the given name. Null if no match found
     */
    public Person find(String firstName, ArrayList<String> middleNames, String lastNames){
        Person match = null;
        for(int i = 0; i < family_.size() && match == null ; i++){
            if(Person.isEqual(firstName, middleNames, lastNames, family_.get(i))) {
                match = family_.get(i);
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
        for(int i = 0; i < family_.size(); i++){
            if(Person.partialEqual(firstName, lastName, family_.get(i))){
                people.add(family_.get(i));
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
    public void addPerson(String firstName, String lastName, Date birthdate){
        Person person = new Person(firstName, lastName, birthdate);
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
        for(int i = 0; i < relationships.size() && existingRelationship == null; i++){
            if(Relationship.isEqual(partner1,partner2, relationships.get(i))){
                existingRelationship = relationships.get(i);
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
            relationships.add(relationship);
        }
        return true;
    }


}
