package echo;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

public class EchoTest {
    @Test
    public void test() {
        String input =
                "List\n" +
                "Todo walk dog";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        String expectedOutput;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        Echo e = new Echo("src/main/data/testSavedTasks.txt");
        String output = e.run();
    }
}
