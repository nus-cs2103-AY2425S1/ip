import java.util.Scanner;

public class Llama {
    public static void displayString(String str) {
        System.out.println("\t" + str);
    }

    public static void displayTask(int num, Task task) {
        System.out.println("\t" + num + ". " + task);
    }

    public static void main(String[] args) {
        String logo = "";
        String hr = "____________________________________________________________" ;
        Scanner sc = new Scanner(System.in);

        TaskStorage tasks = new TaskStorage(100);

        // Initializing message
        displayString(hr);
        displayString("Hello! I'm Llama!");
        displayString(logo);
        displayString("What can I do for you?");

        // Get user input
        boolean shouldContinue = true;
        while (shouldContinue) {
            displayString(hr);
            String input = sc.nextLine();
            displayString(hr);

            // Split input into command and remaining
            String command = input;
            String remaining = "";
            if (input.contains(" ")) {
                command = input.substring(0, input.indexOf(" "));
                remaining = input.substring(input.indexOf(" ") + 1);
            }

            if (command.equals("bye")) {                            // end program
                shouldContinue = false;
                sc.close();
            } else if (command.equals("list")) {                    // list out tasks
                tasks.listAllTasks();
            } else if (command.equals("mark")) {                    // mark task
                int index = Integer.parseInt(remaining);
                tasks.markTask(index);
            } else if (command.equals("unmark")) {                  // unmark task
                int index = Integer.parseInt(remaining);
                tasks.unmarkTask(index);
            } else {                                                // add other tasks
                if (command.equals("todo")) {
                    tasks.addTask(new Todo(remaining));
                } else if (command.equals("deadline")) {
                    tasks.addTask(new Deadline(remaining));
                }
            }
        }

        // Exit message
        displayString("Baaaaaa byeeee. Come baaaaack soon!");
    }
}
