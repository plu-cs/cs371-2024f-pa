package cardtrick;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTrickTests {

    @ParameterizedTest
    @ValueSource(strings = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15"})
    public void testAll(String file) throws Exception {
        String input = Files.readString(Paths.get("test-data/card/" + file + ".txt"));
        Set<String> expected = new HashSet<>(Files.readAllLines(Paths.get("test-data/card/" + file + ".ans")));
        Set<String> result = CardTrick.doTrick(input);
        assertEquals(expected, result);
    }
}
