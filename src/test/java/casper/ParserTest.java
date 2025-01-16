package casper;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void testSplitInputIntoTwo() {
        Parser parser = new Parser();
        String testInput1 = "hello world";
        String[] expected1 = {"hello", "world"};
        String testInput2 = "hello";
        String[] expected2 = {"hello", ""};
        String testInput3 = "hello world again";
        String[] expected3 = {"hello", "world again"};
        String testInput4 = "hello world      ";
        String[] expected4 = {"hello", "world"};

        assertArrayEquals(expected1, parser.splitInputIntoTwo(testInput1));
        assertArrayEquals(expected2, parser.splitInputIntoTwo(testInput2));
        assertArrayEquals(expected3, parser.splitInputIntoTwo(testInput3));
        assertArrayEquals(expected4, parser.splitInputIntoTwo(testInput4));
    }
}
