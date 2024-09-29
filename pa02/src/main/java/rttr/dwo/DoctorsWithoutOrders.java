package rttr.dwo;

import java.util.*;

/**
 * PROBLEM 1 - Doctors Without Orders
 *
 * Fill in the method canAllPatientsBeSeen below.  To test it you have the following
 * options:
 *   - Use the GUI:  ./gradlew run
 *   - Run the JUnit test suites:
 *         ./gradlew test --tests "rttr.dwo.DoctorsWithoutOrdersTests"
 *         ./gradlew test --tests "rttr.dwo.DoctorsWithoutOrdersFileTests"
 *   - Run an individual JUnit test (for example notGreedy):
 *         ./gradlew test --tests "rttr.dwo.DoctorsWithoutOrdersTests.notGreedy"
 *   - Create your own JUnit tests in the above files, you can find those files in: src/test/java/rttr/dwo
 */
public class DoctorsWithoutOrders {

    /**
     * Determine whether all patients can be scheduled with the given doctors.
     * If it is possible, this method returns true, and fills the Map assignments with the
     * schedule.  If not, this method returns false.
     *
     * @param doctors a Map of information about doctors.  The key is the doctor's name, and
     *                the value is the number of hours that doctor has available to see patients.
     * @param patients a Map of information about patients.  The key is the patient's name, and the value
     *                 is the number of hours that that patient needs to schedule with a doctor.
     * @param assignments if a scheduling is possible, this parameter will be filled with the day's
     *                    schedule.  The key is the doctor's name, and the value is the set of patients
     *                    names that the doctor will see.
     * @return true if all patients can be scheduled, and false otherwise.
     */
    public static boolean canAllPatientsBeSeen(Map<String, Integer> doctors,
                                               Map<String, Integer> patients,
                                               Map<String, Set<String>> assignments) {
        // TODO: fill in this method

        // IMPORTANT:  The Maps that are passed to this method may be unmodifiable.
        //  if you want to modify them, you should make copies and pass the copies to a
        //  private helper method.  In other words, have this method make the copies, and
        //  immediately pass them to a helper method (recursive) that does all of the work.
        //  This method only does three things: makes copies of the maps, passes them to a
        //  helper method that does the recursion, and returns the result.  Don't make
        //  copies within your recursive method.  That is wasteful and probably not
        //  necessary.

        return false;
    }
}
