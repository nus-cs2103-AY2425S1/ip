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
            } else if (command.toUpperCase().startsWith("TODO")){
                String description = command.substring(5);
                Task task = new Todo(description, false);
                tasks.add(task);
                System.out.println("Task added:" + "\n" + task.printTask());
                System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
            } else if (command.toUpperCase().startsWith("DEADLINE")){
                String description = command.substring(9, command.lastIndexOf("/by")).trim();
                String deadline = command.substring(command.lastIndexOf("/by") + 4).trim();
                Task task = new Deadline(description, false, deadline);
                tasks.add(task);
                System.out.println("Task added:" + "\n" + task.printTask());
                System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
            } else if (command.toUpperCase().startsWith("EVENT")){
                String description = command.substring(6, command.lastIndexOf("/from")).trim();
                String start = command.substring(command.lastIndexOf("/from") + 6, command.lastIndexOf("/to")).trim();
                String end = command.substring(command.lastIndexOf("/to") + 4).trim();
                Task task = new Event(description, false, start, end);
                tasks.add(task);
                System.out.println("Task added:" + "\n" + task.printTask());
                System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
            }
        }

        System.out.println(exitMessage);

        scanner.close();
    }
}

