import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> userInputs = new ArrayList<>();

    private static void addItem(String inp) {
        userInputs.add(new Task(inp));
    }

    private static void display() {
        System.out.println("Here are the tasks in your list:");
        for (int i=0; i<userInputs.size(); i++) {
            System.out.println((i+1) + "." + userInputs.get(i));
        }
    }

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________\n" +
                "Hello! I'm Tesla\n" +
                "What can I do for you?\n" +
                "____________________________________________________________\n");
        Scanner scanner = new Scanner(System.in);
        String inp = scanner.nextLine();
        while (!inp.equals("bye")) {
            if (inp.equals("list")) {
                display();
            } else if (inp.startsWith("mark")) {
                int idx = Integer.parseInt(String.valueOf(inp.charAt(5)))-1;
                userInputs.get(idx).setDone(true);
                System.out.println("Nice! I've marked this task as done:\n" +
                                    userInputs.get(idx));
            } else if (inp.startsWith("unmark")) {
                int idx = Integer.parseInt(String.valueOf(inp.charAt(7)))-1;
                userInputs.get(idx).setDone(false);
                System.out.println("OK, I've marked this task as not done yet:\n" +
                        userInputs.get(idx));
            } else {
                addItem(inp);
                System.out.println("added: " + inp);
            }
            inp = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
