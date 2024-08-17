import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Echo {
    private Tasks tasks;
    public Echo() {
        tasks = new Tasks();
    }
    // Handles user input
    public Boolean handleInput(Scanner s) {
        // Reads user input
        String userInput = s.nextLine();
        if (userInput.toLowerCase().startsWith("mark")) {
            int index = Integer.valueOf(userInput.substring(5));
            tasks.markTask(index);

            System.out.println(
                    "_________________________________________\n" +
                    "Nice! I've marked this task as done: ");
            tasks.printTask(index);
            System.out.print(
                    "_________________________________________\n");
            return true;
        } else if (userInput.toLowerCase().startsWith("unmark")) {
            int index = Integer.valueOf(userInput.substring(7));
            tasks.unmarkTask(index);

            System.out.println(
                    "_________________________________________\n" +
                    "Ok, I've marked this task as not done yet: ");
            tasks.printTask(index);
            System.out.print(
                    "_________________________________________\n");
            return true;
        } else if (userInput.toLowerCase().startsWith("todo")) {
            String task;
            if (userInput.length() < 6) {
                System.out.println("Enter task description: ");
                task = s.nextLine();
            } else {
                task = userInput.substring(5);
            }
            tasks.addTask(task, TaskType.TODO, "");
            printSuccessMsg();
            return true;
        } else if (userInput.toLowerCase().startsWith("deadline")) {
            if (!userInput.contains("/by")) {
                System.out.println("Deadline: ");
                userInput += " /by ";
                userInput += s.nextLine();
            }
            int byIndex = userInput.indexOf('/');
            String task = userInput.substring(9, byIndex);
            if (task.isEmpty()) {
                System.out.println("Enter task description: ");
                task = s.nextLine() + " ";
            }
            String byDate = userInput.substring(byIndex);
            tasks.addTask(task, TaskType.DEADLINE, byDate);

            printSuccessMsg();
            return true;
        }  else if (userInput.toLowerCase().startsWith("event")) {
            if (!userInput.contains("/from")) {
                System.out.println("Start: ");
                if (userInput.contains("/to")) {
                    userInput = userInput.substring(0, userInput.indexOf('/')) +
                                " /from " +
                                s.nextLine() +
                                userInput.substring(userInput.indexOf('/'));
                } else {
                    userInput += " /from ";
                    userInput += s.nextLine();
                    System.out.println("End: ");
                    userInput += " /to ";
                    userInput += s.nextLine();
                }
            } else if (!userInput.contains("/to")) {
                System.out.println("When does it end?");
                userInput += " /to ";
                userInput += s.nextLine();
            }
            int fromIndex = userInput.indexOf('/');
            String task = userInput.substring(6, fromIndex);
            if (task.isEmpty()) {
                System.out.println("Enter task description: ");
                task = s.nextLine() + " ";
            }
            tasks.addTask(task, TaskType.EVENT, userInput.substring(fromIndex));

            printSuccessMsg();
            return true;
        } else {
            switch (userInput) {
                case "bye":
                    System.out.println(
                        "_________________________________________\n" +
                        "Bye. Hope to see you again soon!\n" +
                        "_________________________________________\n");
                    return false;
                case "list":
                    System.out.println("_________________________________________");
                    System.out.println("Here are the tasks in your list: ");
                    tasks.printTasks();
                    System.out.println("_________________________________________");
                    return true;
                default:
                    return true;
            }
        }
    }

    public void printSuccessMsg() {
        System.out.println(
            "_________________________________________\n" +
            "Got it. I've added this task: \n");
        tasks.printTask(tasks.getNumTasks());
        System.out.printf(
            "Now you have %d task" +
            (tasks.getNumTasks() == 1 ? "" : "s") +
            " in the list.\n" +
            "_________________________________________\n",
        tasks.getNumTasks());
    }

    public static void main(String[] args) {
        Echo e = new Echo();
        // Creates a scanner object
        Scanner scanner = new Scanner(System.in);

        // Greets user
        String welcomeMsg =
                "_________________________________________\n" +
                "Hello! I'm Echo!\n" +
                "What can I do for you?\n" +
                "_________________________________________\n";
        System.out.println(welcomeMsg);

        Boolean isAcceptingInput = true;
        while (isAcceptingInput) { // handles input until user says bye
            isAcceptingInput = e.handleInput(scanner);
        }
    }
}
