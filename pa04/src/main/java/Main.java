import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.List;

/**
 * Use this main method to test and visualize an individual data set.
 * Change the file name on the first line to test one of the input files.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        String file = "square3";
        List<Point2D.Double> points = DataLoader.load("data/" + file + ".dat");
        QuickHullSolver solver = new QuickHullSolver(points);
        List<Integer> hull = solver.solve();
        List<Point2D.Double> allPts = solver.getPoints();
        ConvexHullVisualizer vis = new ConvexHullVisualizer(allPts, hull, true);
        vis.setVisible(true);
    }
}
