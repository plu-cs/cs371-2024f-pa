
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.awt.geom.Point2D;
import java.io.File;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuickHullTests {

    @ParameterizedTest
    @ValueSource(strings = {"triangle", "square_uniform", "square", "square2", "square3",
            "small_square", "med_square", "big_square",
            "disk1", "disk2", "disk3", "disk_50000"})
    public void hullTest(String fileName) throws Exception {
        List<Point2D.Double> pts = DataLoader.load("data/" + fileName + ".dat");
        QuickHullSolver solver = new QuickHullSolver(pts);
        List<Integer> hull = solver.solve();
        List<Integer> expected = new ArrayList<>();
        try( Scanner scan = new Scanner(new File( "data/" + fileName + ".ans")) ) {
            while(scan.hasNext()) expected.add(scan.nextInt());
        }
        assertEquals(expected, hull);
    }
}
