import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Skibidi {
    static void printSeparator() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    static void parseAndExecuteCommand(String line) {
        System.out.printf("COMMAND ENTERED: %s\n", line);
        Skibidi.printSeparator();
    }

    public static void main(String[] args) {
        String filePath = "resources/skibidi-ascii.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException err) {
            err.printStackTrace();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while (true) {
            System.out.print("> ");
            try {
                line = reader.readLine();
                if (line == null || line.equals("bye")) {
                    Skibidi.printSeparator();
                    System.out.println("EXITING APPLICATION");
                    Skibidi.printSeparator();
                    break;
                }
                parseAndExecuteCommand(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
