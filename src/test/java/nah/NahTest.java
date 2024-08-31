package nah;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class NahTest {
    @Test
    /**
     * Test method run of chatBot.
     * The test pass if the chatBot response match the expected response and
     * no exception is thrown.
     */
    public void runTest1() {
        try {
            String inputPath = Paths.get("D:", "cs2103T_week_2", "src", "test", "java",
                    "nah", "input.txt").toString();
            String inputContent = new String(Files.readAllBytes(Paths.get(inputPath)));
            String expectedPath = Paths.get("D:", "cs2103T_week_2", "src", "test", "java",
                    "nah", "expected.txt").toString();
            String expectedContent = new String(Files.readAllBytes(Paths.get(expectedPath)));

            String path = Paths.get("D:", "cs2103T_week_2", "src", "test", "java",
                    "nah", "NahTestFile.txt").toString();

            ByteArrayInputStream inputStream = new ByteArrayInputStream(inputContent.getBytes());
            System.setIn(inputStream);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));

            Nah nah = new Nah(path);
            nah.run();
            String actualContent = outputStream.toString()
                    .trim()
                    .replace("\r\n", "\n")
                    .replace("\r", "\n");

            expectedContent = expectedContent
                    .trim()
                    .replace("\r\n", "\n")
                    .replace("\r", "\n");

            assertEquals(expectedContent, actualContent);
        } catch (IOException e) {
            fail("Unexpected exception");
        }

    }

}
