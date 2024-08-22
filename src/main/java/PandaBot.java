import java.util.ArrayList;
import java.util.Scanner;

public class PandaBot {
    private static void printLine() {
        String line = "_________________________________________";
        System.out.println(line);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // statically storing tasks
        ArrayList<Task> taskList = new ArrayList<>();

        // Simple greeting to the user by PandaBot
        printLine();
        System.out.println("Hello! I'm PandaBot.");
        System.out.println("What can I do for you?");
        printLine();

        while (true) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                printLine();
                System.out.println("Bye. Hope to see you again soon!");
                printLine();
                break;
            } else if (input.equalsIgnoreCase("list")){
                if (taskList.isEmpty()) {
                    System.out.println("There are currently no items in the list.");
                    continue;
                }
                printLine();
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + ". " + taskList.get(i));
                }
                printLine();
            } else if (input.startsWith("mark")) {
                if (input.equalsIgnoreCase("mark")) {
                    System.out.println("Please specify which Task number to mark.");
                    continue;
                }
                int taskNum = Integer.parseInt(input.split(" ")[1]) - 1;
                if (taskNum >= 0 && taskNum < taskList.size()) {
                    taskList.get(taskNum).markAsDone();
                    printLine();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(taskList.get(taskNum));
                    printLine();
                } else {
                    System.out.println("The specified task does not exist.");
                }
            } else if (input.startsWith("unmark")) {
                if (input.equalsIgnoreCase("unmark")) {
                    System.out.println("Please specify which Task number to unmark.");
                    continue;
                }
                int taskNum = Integer.parseInt(input.split(" ")[1]) - 1;
                if (taskNum >= 0 && taskNum < taskList.size()) {
                    taskList.get(taskNum).unmark();
                    printLine();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(taskList.get(taskNum));
                    printLine();
                } else {
                    System.out.println("The specified task does not exist.");
                }
            } else if (input.startsWith("delete")) {
                if (input.equalsIgnoreCase("delete")) {
                    System.out.println("Please specify which Task number to delete.");
                    continue;
                }
                int taskNum = Integer.parseInt(input.split(" ")[1]) - 1;
                if (taskNum >= 0 && taskNum < taskList.size()) {
                    printLine();
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(taskList.remove(taskNum));
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    printLine();
                } else {
                    System.out.println("The specified task does not exist.");
                }
            } else {
                Task task = null;
                try {
                    if (input.startsWith("todo")) {
                        task = new ToDo("").createTask(input);
                    } else if (input.startsWith("deadline")) {
                        task = new Deadline("", "").createTask(input);
                    } else if (input.startsWith("event")) {
                        task = new Event("", "", "").createTask(input);
                    } else {
                        System.out.println("Invalid command. Type '/help' for assistance.");
                        continue;
                    }
                    taskList.add(task);
                    printLine();
                    System.out.println("Got it. I've added this task:");
                    System.out.println(taskList.get(taskList.size() - 1));
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    printLine();
                } catch (InputException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        scanner.close();
    }
}
