import datatree.DataTreeTestSuit;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Created by Urs on 3/24/2017.
 */
public class TestRunner {
    public static void main(String[] args){
        Result results = JUnitCore.runClasses(DataTreeTestSuit.class);

        // Print results
        for(Failure failure : results.getFailures()){
            System.out.println(failure.getTestHeader() + ": " + failure.getTrace());
        }

        System.out.print("Unit Tests were " + ((results.wasSuccessful()) ? "successful" : "failed"));
    }
}
