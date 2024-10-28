import java.awt.geom.Point2D;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {
    public static List<Point2D.Double> load(String path ) throws IOException {
        try( DataInputStream stream = new DataInputStream(new FileInputStream(path))) {
            int nPoints = stream.readInt();
            List<Point2D.Double> pts = new ArrayList<>(nPoints);
            for( int i = 0; i < nPoints; i++ ) {
                double x = stream.readFloat();
                double y = stream.readFloat();
                pts.add( new Point2D.Double(x, y) );
            }
            return pts;
        }
    }
}
