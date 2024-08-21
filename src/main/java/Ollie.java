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
        while(!input.matches("bye")) {

            // Input parser

            if(input.matches("list")) {
                Ollie.list();
            } else if (input.matches("mark [0-9]")){
                int i = Integer.parseInt(input.replaceAll("\\D+",""));
                Ollie.mark(i);
            } else if (input.matches("unmark [0-9]")) {
                int i = Integer.parseInt(input.replaceAll("\\D+",""));
                Ollie.unmark(i);
            } else {
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
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < tasks.size(); i++) {
            list.add(String.format("%d.%s%s", i + 1,tasks.get(i),i == tasks.size() - 1 ? "": "\n"));
        }
        Ollie.printResponse(String.join("",list));
    }

    private static void mark(int i) {
        int index = i - 1;
        if (index < 0 || index >= tasks.size()) {
            Ollie.printResponse("Invalid Serial Number!");
            return;
        }

        Task task = tasks.get(i - 1);
        task.markAsDone();

        Ollie.printResponse("Nice! I've marked this task as done:\n  " + task.toString());
    }

    private static void unmark(int i) {
        int index = i - 1;
        if (index < 0 || index >= tasks.size()) {
            Ollie.printResponse("Invalid Serial Number!");
            return;
        }

        Task task = tasks.get(i - 1);
        task.markAsUndone();

        Ollie.printResponse("OK, I've marked this task as not done yet:\n  " + task.toString());
    }


    private static void echo(String s) {
        Ollie.printResponse(s);
    }

    private static void printResponse(String s) {
        System.out.println( Ollie.DIVIDER + "\n" + s + "\n" + Ollie.DIVIDER);
    }


}
