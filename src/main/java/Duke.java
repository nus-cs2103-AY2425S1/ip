import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<String> userInputs = new ArrayList<>();

    private static void addItem(String inp) {
        userInputs.add(inp);
    }

    private static void display() {
        for (int i=0; i<userInputs.size(); i++) {
            System.out.println((i+1) + ". " + userInputs.get(i));
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
                inp = scanner.nextLine();
                continue;
            }
            addItem(inp);
            System.out.println("added: " + inp);
            inp = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
