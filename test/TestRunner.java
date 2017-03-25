import datatree.DataTreeTestSuit;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Created by Urs on 3/24/2017.
 */
public class TestRunner {
    public static void main(String[] args){
        int totalTests = 0;
        Result results = JUnitCore.runClasses(DataTreeTestSuit.class);
        totalTests += results.getRunCount();

        // Print results
        for(Failure failure : results.getFailures()){
            System.out.println(failure.getTestHeader() + ": " + failure.getTrace());
        }

        System.out.println(totalTests + " run. " + results.getFailureCount() + " failed.");
        System.out.println("Unit Tests were " + ((results.wasSuccessful()) ? "successful" : "failed"));
    }
}
