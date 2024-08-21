import java.util.Scanner;
import java.util.ArrayList;

public class Ollie {
    // Private Types
    private static final String DIVIDER = "____________________________________________________________";
    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Greet
        Ollie.greet();

        String input = scanner.nextLine();
        while(!input.equals("bye")) {

            if(input.equals("list")) {
                // List
                Ollie.list();
            } else {
                // add
                Ollie.add(input);

            }
            input = scanner.nextLine();
        }

        // Exit
        Ollie.exit();
    }

    // Private Methods
    private static void greet() {
        Ollie.printResponse("Hello! I'm Ollie!\nWhat can I do for you?");
    }

    private static void exit() {
        // Exit
        Ollie.printResponse("Bye. Hope to see you again soon!");
    }

    private static void add(String s) {
        tasks.add(new Task(s));
        Ollie.printResponse("added: " + s);
    }
    private static void list() {
        String listItem = "";
        for(int i = 0; i < tasks.size(); i++) {
            listItem += String.format("%d. %s", i + 1,tasks.get(i));
            if (i != tasks.size() - 1) {
                listItem += "\n"; // List indentation
            }
        }
        Ollie.printResponse(listItem);
    }


    private static void echo(String s) {
        Ollie.printResponse(s);
    }

    private static void printResponse(String s) {
        System.out.println( Ollie.DIVIDER + "\n" + s + "\n" + Ollie.DIVIDER);
    }


}
