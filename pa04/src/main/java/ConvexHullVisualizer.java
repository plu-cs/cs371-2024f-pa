import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

public class ConvexHullVisualizer extends JFrame {

    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 800;

    private static final Color BACKGROUND_COLOR = new Color(184, 184, 184);
    private static final int BORDER_SIZE = 50;
    private static final Color POINT_COLOR = new Color(55,55,55);
    private static final int POINT_SIZE = 4;
    private static final int HULL_POINT_SIZE = 10;
    private static final Color HULL_POINT_COLOR = new Color(255,0,0);
    private static final Color HULL_LINE_COLOR = new Color(10, 104, 29);

    private DrawingPanel drawingPanel;
    private List<Point2D.Double> allPoints;
    private List<Integer> hull;

    private boolean drawLines;

    private Point2D.Double min;
    private Point2D.Double max;

    private class DrawingPanel extends JPanel {

        private int screenW, screenH;
        private double invW, invH;

        public DrawingPanel() {
            setBackground(BACKGROUND_COLOR);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Don't draw anything until bounds are determined
            if( min == null || max == null ) return;

            screenW = this.getWidth() - BORDER_SIZE * 2;
            screenH = this.getHeight() - BORDER_SIZE * 2;
            invW = 1.0 / (max.x - min.x);
            invH = 1.0 / (max.y - min.y);
            int pointSize2 = POINT_SIZE / 2;
            int hullPointSize2 = HULL_POINT_SIZE / 2;
            Point ssPoint = new Point();  // Screen space point (for drawing)

            Graphics2D g2 = (Graphics2D)g;

            // Draw all points
            g2.setColor(POINT_COLOR);
            for( Point2D.Double p : allPoints ) {
                toScreenSpace(p, ssPoint);
                g2.fillRect( ssPoint.x - pointSize2, ssPoint.y - pointSize2, POINT_SIZE, POINT_SIZE );
            }

            // Draw hull points and edges
            if( hull.size() == 0 ) return;

            if( drawLines ) {
                Point ssCurrent = new Point();
                Point ssNext = new Point();
                Point ssStart = new Point();
                Point2D.Double start = allPoints.get(hull.get(0));
                toScreenSpace(start, ssStart);
                ssCurrent.setLocation(ssStart);
                g2.setColor(HULL_LINE_COLOR);
                for (int i = 0; i < hull.size(); i++) {
                    if( i == hull.size() - 1 ) {
                        ssNext.setLocation(ssStart);
                    } else {
                        toScreenSpace(allPoints.get(hull.get(i+1)), ssNext);
                    }

                    g2.drawLine(ssCurrent.x, ssCurrent.y, ssNext.x, ssNext.y);
                    ssCurrent.setLocation(ssNext);
                }
            }

            // Draw hull points
            g2.setColor( HULL_POINT_COLOR );
            for( int i = 0; i < hull.size() ; i++ ) {
                toScreenSpace(allPoints.get(hull.get(i)), ssPoint);

                g2.fillOval( ssPoint.x - hullPointSize2, ssPoint.y - hullPointSize2, HULL_POINT_SIZE, HULL_POINT_SIZE );
            }
        }

        private void toScreenSpace( Point2D.Double p, Point result) {
            result.x = (int) ((p.x - min.x) * invW * screenW + BORDER_SIZE);
            result.y = (int) ((1 - (p.y - min.y) * invH) * screenH + BORDER_SIZE);
        }
    }


    /**
     * Constructs a new ConvexHullVisualizer.  Call `setVisible(true)` to make the window
     * visible on the screen.
     *
     * @param allPoints a list of all the points
     * @param hull a list of indices to the allPoints list that represents the points in the
     *             convex hull.  The list is assumed to be in the order that they appear as you
     *             traverse around the hull.
     * @param drawLines whether to draw the lines between the points in the hull.
     */
    public ConvexHullVisualizer(List<Point2D.Double> allPoints, List<Integer> hull, boolean drawLines) {
        this.allPoints = allPoints;
        this.hull = hull;
        this.drawLines = drawLines;

        setTitle("Convex Hull Visualizer");

        drawingPanel = new DrawingPanel();
        this.add(drawingPanel, BorderLayout.CENTER);
        drawingPanel.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        computeBounds();
    }

    private void computeBounds() {
        if( allPoints.size() == 0 ) return;
        Point2D.Double firstPt = allPoints.get(0);
        min = new Point2D.Double(firstPt.x, firstPt.y);
        max = new Point2D.Double(firstPt.x, firstPt.y);

        for( int i = 1; i < allPoints.size(); i++ ) {
            Point2D.Double p = allPoints.get(i);
            if( p.x > max.x ) max.x = p.x;
            if( p.y > max.y ) max.y = p.y;
            if( p.x < min.x ) min.x = p.x;
            if( p.y < min.y ) min.y = p.y;
        }

        drawingPanel.repaint();
    }

}
