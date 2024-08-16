import java.util.*;

public class Duke {
    private List<String> inputStore = new ArrayList<String>();
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner scan = new Scanner(System.in);
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        Duke duke = new Duke();
//        while (scan.hasNext()) {
//            String input = scan.nextLine();
//            if (input.equals("bye")) {
//                System.out.println("Bye! Hope to see you again soon!");
//                scan.close();
//                break;
//            }
//            System.out.println(input);
//        }
        duke.handleUserInput();
    }

    private void handleUserInput() {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            String input = scan.nextLine();
            switch (input) {
                case "bye":
                    System.out.println("Bye! Hope to see you again soon!");
                    scan.close();
                    return;
                case "list":
                    for (int i = 0; i < this.inputStore.size(); ++i) {
                        System.out.printf("%d. %s\n", i+1, inputStore.get(i));
                    }
                    break;
                default:
                    this.inputStore.add(input);
                    System.out.printf("Added %s\n", input);
                    break;
            }
        }
    }
}
