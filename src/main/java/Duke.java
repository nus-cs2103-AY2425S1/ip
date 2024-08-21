import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<String> toDoList = new ArrayList<String>();
    private static int counter = 1;

    private static void greet() {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
    }

    private static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void printList() {
        for (String task : toDoList) {
            System.out.println(counter + ". " + task);
            counter += 1;
        }
    }

    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        String command;
        while (sc.hasNext()) {
            command = sc.nextLine();
            if (command.equals("list")) {
                printList();
            } else if (command.equals("bye")) {
                break;
            } else {
                toDoList.add(command);
                System.out.println("added: " + command);
            }
        }
        sc.close();
        bye();
    }
}
