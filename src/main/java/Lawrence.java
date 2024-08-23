import java.util.Scanner;

public class Lawrence {
    private static final String NAME = "Lawrence";
    private static final Tasks tasks = new Tasks();

    public static void main(String[] args) {
        greetUser();
        Scanner sc = new Scanner(System.in);

        String userInput;
        while (true) {
            userInput = sc.nextLine();  // Get next user input
            String[] inputComponents = userInput.split(" ", 2);
            String command = inputComponents[0];
            switch (command) {
                case "bye":
                    exitSession();
                    break;
                case "list":
                    displayTasks();
                    break;
                case "mark":
                    if (inputComponents.length < 2) {
                        displayMessage("Please specify the task you want to mark as complete.");
                        break;
                    }
                    markTaskAsComplete(inputComponents[1]);
                    break;
                case "unmark":
                    if (inputComponents.length < 2) {
                        displayMessage("Please specify the task you want to mark as incomplete.");
                        break;
                    }
                    markTaskAsIncomplete(inputComponents[1]);
                    break;
                case "todo":
                    if (inputComponents.length < 2) {
                        displayMessage("Todo description cannot be empty! Please try again.");
                        break;
                    }
                    addTodo(inputComponents[1]);
                    break;
                case "deadline":
                    if (inputComponents.length < 2) {
                        displayMessage("Deadline information not found! Please try again.");
                        break;
                    }
                    addDeadline(inputComponents[1]);
                    break;
                case "event":
                    if (inputComponents.length < 2) {
                        displayMessage("Event information not found! Please try again.");
                        break;
                    }
                    addEvent(inputComponents[1]);
                    break;
                default:
                    displayMessage(String.format("Unable to recognise command '%s'. Please try again.", command));
            }
        }
    }

    private static void addTodo(String description) {
        addTask(new ToDo(description));
    }

    private static void addDeadline(String parameters) {
        String[] deadlineComponents = parameters.split(" /by ");
        if (deadlineComponents.length < 2) {
            displayMessage("Not enough information to create deadline.");
            return;
        }
        addTask(new Deadline(deadlineComponents[0], deadlineComponents[1]));
    }

    private static void addEvent(String parameters) {
        String[] eventComponents = parameters.split(" /from | /to ");
        if (eventComponents.length < 3) {
            displayMessage("Not enough information to create event.");
            return;
        }
        addTask(new Event(eventComponents[0], eventComponents[1], eventComponents[2]));
    }

    private static void addTask(Task t) {
        tasks.add(t);
        int numberOfTasks = tasks.getSize();
        String verb = numberOfTasks == 1 ? "is" : "are";
        String plural = numberOfTasks == 1 ? "" : "s";
        displayMessage(String.format("Alright, added task:%n%s to the list.%n"
                + "There %s currently %d task%s in the list.", t, verb, numberOfTasks, plural));
    }

    private static void markTaskAsComplete(String parameters) {
        try {
            int taskNumber = Integer.parseInt(parameters);
            Task completeTask = tasks.completeTask(taskNumber);
            displayMessage(
                    String.format("I've marked the task as complete:\n%s", completeTask));
        } catch (NumberFormatException e) {
            displayMessage("Please input a number to select a task.");
        } catch (IllegalArgumentException e) {
            displayMessage(
                    String.format("%s Please try again.", e.getMessage()));
        }
    }

    private static void markTaskAsIncomplete(String parameters) {
        try {
            int taskNumber = Integer.parseInt(parameters);
            Task incompleteTask = tasks.uncompleteTask(taskNumber);
            displayMessage(
                    String.format("Changed your mind? The task is set to incomplete:\n%s", incompleteTask));
        } catch (NumberFormatException e) {
            displayMessage("Please input a number to select a task.");
        } catch (IllegalArgumentException e) {
            displayMessage(
                    String.format("%s Please try again.", e.getMessage()));
        }
    }

    private static void greetUser() {
        String greeting = String.format("Hello! I'm %s and I'm here to establish another GST hike.\n"
                + "What can I do for you?", NAME);
        displayMessage(greeting);
    }

    private static void displayTasks() {
        if (tasks.getSize() < 1) {
            displayMessage("You have no tasks at the moment.");
            return;
        }
        displayMessage(String.format("Here's your laundry list:%n%s", tasks.toString()));
    }

    private static void exitSession() {
        displayMessage("That's all folks! Hope to see you again soon!");
        System.exit(0);
    }

    private static void displayMessage(String message) {
        displayHorizontalLine();
        System.out.println(message);
        displayHorizontalLine();
    }

    private static void displayHorizontalLine() {
        System.out.println("____________________");
    }
}
