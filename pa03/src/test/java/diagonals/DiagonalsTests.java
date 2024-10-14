package diagonals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiagonalsTests {

    @ParameterizedTest
    @ValueSource(strings = {"eight_easy","eight_hard", "eight_with_a_4", "five_easy", "five_hard",
            "one0xxx", "one1xxx", "one0110", "one1001", "onex0xx", "onex1xx", "onexx0x", "onexx1x", "onexxx0", "onexxx1",
            "p1", "p2", "p3", "p4", "p5", "p6", "p7", "p8", "r2", "s7", "t36", "t47", "t161",
            "t30950-112", "tt2", "tt3", "tt4"})
    // The basic solution is too slow for these test cases:
    // @ValueSource(strings = {"hb", "t27", "t45", "tt"})
    public void testFile( String file ) throws Exception {
        String input = Files.readString(Paths.get("test-data/diagonals/" + file + ".in"));
        String expected = Files.readString(Paths.get("test-data/diagonals/" + file + ".ans"));

        DiagonalsBoard board = new DiagonalsBoard(input);
        assertTrue(Diagonals.solve(board));

        // Normalize line endings if necessary
        expected = expected.replaceAll("\\r", "");

        assertEquals(expected, board.toString());
    }
}
