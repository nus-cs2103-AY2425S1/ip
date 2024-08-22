import java.util.Scanner;
import java.util.ArrayList;

public class Cypher {
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static void lineBreak() {
        System.out.println("-------------------------------------------------------");
    }

    private static void greet() {
        Cypher.lineBreak();
        System.out.println("Hello! I am Cypher \nWhat can I do for you!\n");
        Cypher.lineBreak();
    }

    private static void goodBye() {
        System.out.println("Bye. Hope to see you again soon!");
        Cypher.lineBreak();
    }
    public static void main(String[] args) {
        Cypher.greet();
        String command = "";
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your command: ");
            command = scanner.nextLine();

            // Echo
            if (!command.equalsIgnoreCase("bye")) {
                Cypher.lineBreak();
                System.out.println(command);
                Cypher.lineBreak();
            } else {
                Cypher.lineBreak();
                break;
            }
        }

        Cypher.goodBye();
    }
}
