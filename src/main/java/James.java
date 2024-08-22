import java.util.Scanner;
import java.util.ArrayList;

public class James {
    public static void main(String[] args) {
        String greeting = "Hello Big Boy! I'm James \n"
                + "How can I assist you today? \n";
        String exitMessage = "Goodbye. Come back anytime! \n";

        System.out.println(greeting);

        Scanner scanner = new Scanner(System.in);
        String command;
        ArrayList<Task> tasks = new ArrayList<>();

        while (true) {
            System.out.print("> ");
            command = scanner.nextLine();

            if (command.equalsIgnoreCase("bye")) {
                break;
            } else if (command.equalsIgnoreCase("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    String task = String.format("%d. %s", (i + 1), tasks.get(i).printTask());
                    System.out.println(task);
                }
            } else if (command.toUpperCase().startsWith("MARK")) {
                int taskNum = Integer.parseInt(command.substring(command.length() - 1));
                tasks.get(taskNum - 1).mark();
            } else if (command.toUpperCase().startsWith("UNMARK")) {
                int taskNum = Integer.parseInt(command.substring(command.length() - 1));
                tasks.get(taskNum - 1).unMark();
            } else {
                tasks.add(new Task(command, false));
                System.out.println("Task added:" + command + "\n");
            }
        }

        System.out.println(exitMessage);

        scanner.close();
    }
}

