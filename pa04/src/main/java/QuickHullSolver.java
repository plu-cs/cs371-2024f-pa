import java.awt.geom.Point2D;
import java.util.*;

public class QuickHullSolver {

    private List<Point2D.Double> points;

    public QuickHullSolver(List<Point2D.Double> points) {
        this.points = points;
    }

    public List<Point2D.Double> getPoints() {
        return new ArrayList<>(points);
    }

    /**
     * Find the convex hull of the points in this object (variable points)
     * using the divide-and-conquer "quick-hull" algorithm.
     *
     * @return a list of the indices of the points that make up the convex hull
     * starting with the point with the minimum x coordinate and proceeding
     * clockwise around the hull.
     */
    public List<Integer> solve( ) {

        // TODO: Implement your solution here.  Create any private helper methods needed.

        return null;
    }
}
