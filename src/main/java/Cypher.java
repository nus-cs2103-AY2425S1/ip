import java.util.Scanner;
import java.util.ArrayList;

public class Cypher {
    private static ArrayList<Task> taskList;
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

    private static void addToList(String description) {
        Task task = new Task(description);
        taskList.add(task);
        Cypher.lineBreak();
        System.out.println("Added: " + description);
        Cypher.lineBreak();
    }

    private static void printTaskList() {
        Cypher.lineBreak();
        for (int i = 0; i < Cypher.taskList.size(); i++) {
            System.out.println((i + 1) + ". " + Cypher.taskList.get(i));
        }
        Cypher.lineBreak();
    }
    public static void main(String[] args) {
        Cypher.greet();
        Cypher.taskList = new ArrayList<>();
        String command = "";
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your command: ");
            command = scanner.nextLine();

            // Echo
            if (command.equalsIgnoreCase("list")) {
                Cypher.printTaskList();
            } else if (!command.equalsIgnoreCase("bye")) {
                Cypher.addToList(command);
            } else {
                Cypher.lineBreak();
                break;
            }
        }

        Cypher.goodBye();
    }
}
