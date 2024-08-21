import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bob {
    private static List<String> memory = new ArrayList<String>();
    public static void main(String[] args) {
        printBob("Hello! I'm your chatbot, Bob.\n" +
                "How may I assist you?");

        Scanner reader = new Scanner(System.in);
        String response;

        while (!(response = readUserInput(reader)).equals("bye")) {

            if (response.equals("list")) {
                String list = "";
                for (int i = 0; i < memory.size() - 1; i++){
                    list += (i + 1) +": " + memory.get(i) + "\n";
                }
                list += memory.size() + ": " + memory.get(memory.size() - 1);
                printBob(list);
                continue;
            }

            memory.add(response);
            printBob("Added: " + response);
        }

        printBob("Bye.");
    }

    private static String readUserInput(Scanner s) {
        System.out.print("> ");
        return s.next();
    }

    private static void printBob(String s) {
        System.out.println("____________________________________________________________");
        System.out.println(s);
        System.out.println("____________________________________________________________");
    }
}
