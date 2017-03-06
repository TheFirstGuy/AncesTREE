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

    private int numGenerations_;
    /**
     * List of relationships between persons
     */
    private ArrayList<Relationship> relationships_ = new ArrayList<Relationship>();


    public DataTree(){
        family_ = new HashSet<Person>();
        relationships_ = new ArrayList<Relationship>();
        numGenerations_ = 0;
    }
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

    /**
     * Gets the persons who are roots of the family (have no parents)
     * @return Persons with no parents
     */
    public ArrayList<Person> getRoots(){
        ArrayList<Person> roots = new ArrayList<Person>();
        for(Person person : family_){
            // Check if person has no parents
            if(person.getMother() == null && person.getFather() == null){
                roots.add(person);
            }
        }
        return roots;
    }

    public ArrayList<Person> getGeneration(int genNum){
        ArrayList<Person> generation = new ArrayList<Person>();
        for(Person person : family_){
            if(person.getGeneration() == genNum){
                generation.add(person);
            }
        }
        return generation;
    }

    /**
     * Resets the generation of all persons in the family tree
     */
    private void clearGenerations(){
        for(Person person : family_){
            person.setGeneration(0);
        }
    }

    /**
     * Calculates and sets and generations for the family tree
     * @return leafs in the youngest generation
     */
    public HashSet<Person> calcGenerations(){
        clearGenerations();

        ArrayList<Person> roots = getRoots();
        HashSet<Person> leafs = new HashSet<Person>();

        for(Person root: roots){
            leafs.addAll(calcGenerationsHelper(root));
        }
        filterDeepestLeafs(leafs);
        // Store number of generations
        numGenerations_ = leafs.iterator().next().getGeneration() + 1;
        return leafs;
    }

    /**
     * Recursively calculate generations for each person
     * @param person root person to
     * @return Persons from the youngest generation
     */
    private HashSet<Person> calcGenerationsHelper(Person person){
        HashSet<Person> leafs = new HashSet<Person>();
        ArrayList<Person> children = person.getChildren();

        // If no children, person is a leaf
        if(children.size() == 0){
            leafs.add(person);
        }
        // Otherwise recursively go through tree
        else {
            for (Person child : children) {
                backPropagateGeneration(child);
                // Set the childs generation
                if (child.getGeneration() < (person.getGeneration() + 1)) {
                    child.setGeneration(person.getGeneration() + 1);
                    leafs.addAll(calcGenerationsHelper(child));
                }
            }
        }
        // Correct generational shift by setting all siblings
        return leafs;
    }

    /**
     * Corrects generations of ancestors
     * @param person Person of interest to correct past generations from
     */
    private void backPropagateGeneration(Person person){
        // Check mother
        Person mother = person.getMother();
        Person father = person.getFather();
        if(mother != null) {
            if (mother.getGeneration() < (person.getGeneration() - 1)) {
                // Correct mother's generation
                mother.setGeneration(person.getGeneration() - 1);
                backPropagateGeneration(person.getMother());
            }
        }
        // Check father
        if(father != null){
            if(father.getGeneration() < (person.getGeneration() - 1)){
                // Correct father's generation
                father.setGeneration(person.getGeneration() - 1);
                backPropagateGeneration(person.getFather());
            }
        }
    }

    /**
     * Removes leafs that are not of the deepest generation
     * @param leafs Set of persons to
     */
    private void filterDeepestLeafs(HashSet<Person> leafs){
        int max = 0;
        // Find max generation
        for(Person person : leafs){
            if( max < person.getGeneration()){
                max = person.getGeneration();
            }
        }
        // Remove all leafs that are less than max
        Iterator<Person> itr = leafs.iterator();
        while(itr.hasNext()){
            Person person = itr.next();
            if(person.getGeneration() < max){
                itr.remove();
            }
        }
    }

    public HashSet<Person> getFamily(){
        return family_;
    }

    public int getNumGenerations(){
        return numGenerations_;
    }
}
