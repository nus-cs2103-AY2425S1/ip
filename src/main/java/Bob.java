import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Bob {
    private static final String hLine = "____________________________________________________________\n";

    public static void main(String[] args) {
        String greeting = "Hello! I'm Bob\nWhat can I do for you?\n";
        String bye = "Bye. Hope to see you again soon!\n";
        Scanner myScanner = new Scanner(System.in);
        String greetingMessage = hLine + greeting + hLine;
        String byeMessage = hLine + bye + hLine;
        // Database to store text
        List<String> todoList = new ArrayList<>();

        // Greets user
        System.out.println(greetingMessage);
        // Await user input
        String userInput = myScanner.nextLine();
        // Loop for user input
        do {
            switch (userInput) {
                case "list":
                    printList(todoList);
                    userInput = myScanner.nextLine();
                    break;
                default:
                    todoList.add(userInput);
                    System.out.println(hLine + "added: " + userInput + "\n" + hLine);
                    userInput = myScanner.nextLine();
            }
        } while (!userInput.toLowerCase().strip().equals("bye"));

        // User input == "bye"
        System.out.print(byeMessage);
    }

    // Method to print todolist
    private static void printList(List<String> todoList) {
        System.out.print(hLine);
        for (int i = 0; i < todoList.size(); i++) {
            System.out.println(i+1 + ". " + todoList.get(i));
        }
        System.out.println(hLine);
    }
}
