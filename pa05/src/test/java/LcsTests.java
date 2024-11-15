import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LcsTests {

    @ParameterizedTest
    @ValueSource( strings = {"2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2012",
    "3001", "3002", "3003"} )
    public void test( String fileName ) throws Exception {
        // Load Strings
        int k;
        List<String> sList;
        try( Scanner scan = new Scanner( new File("data/" + fileName + ".in"))) {
            int n = scan.nextInt();
            k = scan.nextInt();
            sList = new ArrayList<>(n);
            for( int i = 0; i < n; i++ ) {
                sList.add(scan.next());
            }
        }

        // Load answer
        int expected;
        try( Scanner scan = new Scanner( new File( "data/" + fileName + ".ans"))) {
            expected = scan.nextInt();
        }

        LongestCommonSubsequence sub = new LongestCommonSubsequence(sList, k);
        assertEquals(expected, sub.solve());
    }

}
