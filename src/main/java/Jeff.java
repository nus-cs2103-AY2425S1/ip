import java.util.Scanner;
import java.util.ArrayList;

public class Jeff {
    private static final String HORIZONTAL =
            "_____________________________________________________________________________________";
    private static ArrayList<Task> taskList = new ArrayList<>();

    // Function for enclosing text with horizontal lines and printing it out
    private static void printText(String text) {
        System.out.println(indentText(HORIZONTAL + "\n " + text + "\n" + HORIZONTAL));
    }

    // Function to indent text
    private static String indentText(String text) {
        // Split the text into lines
        String[] lines = text.split("\n");

        // Initialise a StringBuilder
        StringBuilder indentedText = new StringBuilder();

        // Add indentation to each line
        for (String line : lines) {
            indentedText.append("\t").append(line).append("\n");
        }

        // Convert the StringBuilder back to a String
        return indentedText.toString();

    }

    // Function for printing out greetings
    private static void printGreetings() {
        printText("Hello! I'm Jeff.\n What can I do for you?");
    }

    // Function for printing out farewells
    private static void printFarewell() {
        printText("Bye. Hope to see you again soon!");
    }

    // Function for printing out the input list
    private static void printList() throws JeffException {
        // Check if the list is empty
        if (taskList.isEmpty()) {
            throw new JeffException("List is empty!");
        }

        // Initialise a StringBuilder
        StringBuilder listString = new StringBuilder();

        // Loop through the inputList and add it to the StringBuilder
        for (int i = 0; i < taskList.size(); i++) {
            listString.append(Integer.toString(i + 1)).append(".").append(taskList.get(i).toString());

            // Only add a new line when it is not the last task in the list
            if (i != taskList.size() - 1) {
                listString.append("\n ");
            }

        }

        // Print the text
        printText(listString.toString());

    }

    // Function to get task from input
    private static Task getTask(String input, String prefix) throws JeffException {
        // Check if input is valid
        if (!input.matches(prefix + "\\d+")) {
            throw new JeffException("The format is wrong! It should be \"" + prefix + "xx\", where xx is a number.");
        }

        // Get the taskIndex
        int taskIndex = Integer.parseInt(input.substring(prefix.length())) - 1;

        // Check if taskIndex exists in taskList
        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            throw new JeffException("This task number does not exist!");
        }

        // Get the task from taskList and return it
        return taskList.get(taskIndex);

    }

    // Function for marking the task as done
    private static void markTask(String input) throws JeffException {
        // Get the task from taskList
        Task targetTask = getTask(input, "mark ");

        // Check if the task has been done or not
        if (targetTask.isDone()) {
            // Tell the user that it already is done
            throw new JeffException("This task has already been marked as done!");

        } else {
            // Mark the task as done
            targetTask.markAsDone();

            // Print the task text
            printText("OK, I've marked this task as done:\n   " + targetTask.toString());
        }
    }

    // Function for unmarking the task
    private static void unmarkTask(String input) throws JeffException {
        // Get the task from taskList
        Task targetTask = getTask(input, "unmark ");

        // Check if the task has been done or not
        if (targetTask.isDone()) {
            // Unmark the task
            targetTask.markAsNotDone();

            // Print the task text
            printText("OK, I've marked this task as not done yet:\n   " + targetTask.toString());

        } else {
            // Tell the user that it already is not done
            throw new JeffException("This task has already been marked as not done yet!");

        }

    }

    // Function for deleting the task
    private static void deleteTask(String input) throws JeffException {
        // Get the task from taskList
        Task targetTask = getTask(input, "delete ");

        // Delete the task
        taskList.remove(targetTask);

        // Print delete statement
        printText("Noted. I've removed this task:\n   " +
                targetTask.toString() +
                "\n Now you have " + taskList.size() + " tasks in the list.");
    }

    // Function to categorise the task, add it to the task list and print it out
    private static void handleTask(String input) throws JeffException {
        // Check if the input is in the correct format
        if (input.matches("todo .+") ||
                input.matches("deadline .+") ||
                input.matches("event .+")) {

            // Split the string to obtain the task type and task description
            String[] taskParts = input.split(" ", 2);
            String taskType = taskParts[0];
            String taskDescription = taskParts[1];

            // Initialise the task
            Task targetTask = null;

            // Check the task type and initialise accordingly
            switch (taskType) {
            case "todo":
                targetTask = new ToDoTask(taskDescription);
                break;
            case "deadline":
                // Split the description into content and deadline
                String[] deadlineParts = taskDescription.split(" /by ", 2);

                // Check if the format is correct
                if (deadlineParts.length != 2) {
                    throw new JeffException("The format is wrong! It should be \"deadline xx /by xx\"!");
                } else {
                    String content = deadlineParts[0];
                    String deadline = deadlineParts[1];
                    targetTask = new DeadlineTask(content, deadline);
                }

                break;

            case "event":
                // Split the description into content, start and end
                String[] eventParts = taskDescription.split(" /from ", 2);
                String eventContent = eventParts[0];
                String eventPeriod = eventParts.length > 1 ? eventParts[1] : "";
                String[] periodParts = eventPeriod.split(" /to ", 2);
                String start = periodParts[0];
                String end = periodParts.length > 1 ? periodParts[1] : "";

                // Check if the format is correct
                if (end.isEmpty()) {
                    throw new JeffException("The format is wrong! It should be \"event xx /from xx /to xx\"!");
                } else {
                    targetTask = new EventTask(eventContent, start, end);
                }

                break;

            default:
                break;
            }

            if (targetTask != null) {
                // Add the task to the taskList
                taskList.add(targetTask);

                // Print the task out
                printText("Got it. I've added this task:\n   " +
                        targetTask.toString() +
                        "\n Now you have " + taskList.size() + " tasks in the list.");
            }

        } else {
            if (input.equals("todo") || input.equals("todo ")) {
                throw new JeffException("Sorry, the description of a todo cannot be empty!");
            } else if (input.equals("deadline") || input.equals("deadline ")) {
                throw new JeffException("Sorry, the description of a deadline cannot be empty!");
            } else if (input.equals("event") || input.equals("event ")) {
                throw new JeffException("Sorry, the description of a event cannot be empty!");
            } else {
                throw new JeffException();
            }
        }
    }

    // Function for printing out user input
    private static void printUserInput() {
        // Initialise the Scanner
        Scanner scanner = new Scanner(System.in);
        String input = "";

        // Continue looping until input is bye
        while (!input.equals("bye")) {
            // Prompt the user for input
            System.out.print("");

            // Get the input
            input = scanner.nextLine();

            try {
                // Check for input == some keyword or starts with some keyword
                if (input.equals("list")) {
                    printList();
                } else if (input.equals("bye")) {
                    continue;
                } else if (input.startsWith("mark")) {
                    markTask(input);
                } else if (input.startsWith("unmark")) {
                    unmarkTask(input);
                } else if (input.startsWith("delete")) {
                    deleteTask(input);
                } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
                    handleTask(input);
                } else {
                    throw new JeffException();
                }
            } catch (JeffException e) {
                printText(e.toString());
            }

        }

        // Close the Scanner
        scanner.close();
    }

    public static void main(String[] args) {
        printGreetings();
        printUserInput();
        printFarewell();
    }
}
