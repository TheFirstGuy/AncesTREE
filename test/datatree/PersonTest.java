package datatree;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Urs on 3/24/2017.
 */
public class PersonTest {
    protected Person female;
    protected Person male;

    @Before
    public void setUp() throws Exception {
        this.female = new Person("Jane","Smith", new Date(1,1,1), Person.SEX.FEMALE, true);
        this.male= new Person("John", "Smith", new Date(1,2,3), Person.SEX.MALE, true);
    }

    @Test
    public void testTwoPersonsEqualNominal(){
        Person targetFemale = this.female;
        Person targetMale = this.male;
        Person sameNameFemale = new Person("Jane", "Smith", new Date(1,2,3), Person.SEX.FEMALE, true);

        Assert.assertTrue("Message",Person.isEqual(targetFemale, this.female));
        Assert.assertTrue(Person.isEqual(targetMale, this.male));
        assertFalse(Person.isEqual(this.female, this.male));
        assertFalse("alskjdf",Person.isEqual(sameNameFemale, this.female));

    }





}