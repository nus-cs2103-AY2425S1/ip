import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class Skibidi {
    List<String> store = new ArrayList<>();

    static void printSeparator() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    void printList() {
        if (store.size() == 0) {
            System.out.println("NO ITEMS");
            return;
        }
        System.out.println("\tLISTING ITEMS:");
        for (int i = 0; i < store.size(); i++) {
            System.out.printf("\t  %d. %s\n", i + 1, store.get(i));
        }
    }

    void parseAndExecuteCommand(String line) {
        if (line.equals("list")) {
            printList();
        } else {
            store.add(line);
            System.out.printf("ADDED: %s\n", line);
        }
        Skibidi.printSeparator();
    }

    public void start() {
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

    public static void main(String[] args) {
        Skibidi bot = new Skibidi();
        bot.start();
    }
}
