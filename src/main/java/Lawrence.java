import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Lawrence {
    private static final String NAME = "Lawrence";
    private static final Path saveLocation = Paths.get(".", "data", "tasks.txt");
    private static final Tasks tasks = new Tasks(saveLocation);

    public static void main(String[] args) {
        greetUser();

        // Parse saved tasks from file
        try {
            tasks.initialiseExistingTasks();
        } catch (IOException e) {
            displayMessage("Failed to initialise tasks from file");
        }

        Scanner sc = new Scanner(System.in);

        String userInput;
        while (true) {
            userInput = sc.nextLine();  // Get next user input

            // Parse command at start of the line
            String[] inputComponents = userInput.split(" ", 2);

            Command command;
            try {
                command = Command.fromString(inputComponents[0]);
            } catch (IllegalArgumentException e) {
                displayMessage(
                        String.format("Unable to recognize command '%s'. Please try again.", inputComponents[0]));
                continue;  // Skip to the next iteration
            }

            switch (command) {
            case EXIT:
                exitSession();
                break;
            case DISPLAY:
                displayTasks();
                break;
            case MARK_COMPLETE:
                if (inputComponents.length < 2) {
                    displayMessage("Please specify the task you want to mark as complete.");
                    break;
                }
                int taskNumberToComplete = Integer.parseInt(inputComponents[1]);
                markTaskAsComplete(taskNumberToComplete);
                break;
            case MARK_INCOMPLETE:
                if (inputComponents.length < 2) {
                    displayMessage("Please specify the task you want to mark as incomplete.");
                    break;
                }
                int taskNumberToIncomplete = Integer.parseInt(inputComponents[1]);
                markTaskAsIncomplete(taskNumberToIncomplete);
                break;
            case DELETE:
                if (inputComponents.length < 2) {
                    displayMessage("Please specify the task you want to delete.");
                    break;
                }
                int taskNumberToDelete = Integer.parseInt(inputComponents[1]);
                deleteTask(taskNumberToDelete);
                break;
            case CREATE_TODO:
                // Intentional falling through of case
            case CREATE_DEADLINE:
                // Intentional falling through of case
            case CREATE_EVENT:
                try {
                    Task t = TaskFactory.createTask(userInput, InputSource.USER);
                    addTask(t);
                } catch (DateTimeParseException e) {
                    System.out.println(e);
                    displayMessage("Invalid date and/or time provided. Please try again.");
                }
                break;
            default:
                // This case should never be reached
                throw new IllegalStateException("Unexpected command: " + command);
            }
        }
    }

    private static void addTask(Task t) {
        try {
            tasks.addTask(t);
            int numberOfTasks = tasks.getSize();
            String verb = numberOfTasks == 1 ? "is" : "are";
            String plural = numberOfTasks == 1 ? "" : "s";
            displayMessage(String.format("Alright, added task:%n%s%nto the list.%n"
                    + "There %s currently %d task%s in the list.", t, verb, numberOfTasks, plural));
        } catch (IOException e) {
            displayMessage("Failed to add task to the list. Please try again later.");
        }
    }

    private static void deleteTask(int taskNumber) {
        try {
            Task deletedTask = tasks.deleteTask(taskNumber);
            displayMessage(
                    String.format("Task %s has been deleted.", deletedTask));
        } catch (NumberFormatException e) {
            displayMessage("Please specify a number to select a task.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            displayMessage(
                    String.format("%s Please try again.", e.getMessage()));
        } catch (IOException e) {
            displayMessage(
                    String.format("Failed to delete task %d from the list. Please try again later.", taskNumber));
        }
    }

    private static void markTaskAsComplete(int taskNumber) {
        try {
            Task completeTask = tasks.completeTask(taskNumber);
            displayMessage(
                    String.format("I've marked the task as complete:%n%s", completeTask));
        } catch (NumberFormatException e) {
            displayMessage("Please specify a number to select a task.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            displayMessage(
                    String.format("%s Please try again.", e.getMessage()));
        } catch (IOException e) {
            displayMessage(
                    String.format("Failed to mark task %d as complete. Please try again later.", taskNumber));
        }
    }

    private static void markTaskAsIncomplete(int taskNumber) {
        try {
            Task incompleteTask = tasks.uncompleteTask(taskNumber);
            displayMessage(
                    String.format("Changed your mind? The task is set to incomplete:%n%s", incompleteTask));
        } catch (NumberFormatException e) {
            displayMessage("Please specify a number to select a task.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            displayMessage(
                    String.format("%s Please try again.", e.getMessage()));
        } catch (IOException e) {
            displayMessage(
                    String.format("Failed to mark task %d as incomplete. Please try again later.", taskNumber));
        }
    }

    private static void displayTasks() {
        if (tasks.getSize() < 1) {
            displayMessage("You have no tasks at the moment.");
            return;
        }
        displayMessage(String.format("Here's your laundry list:%n%s", tasks));
    }

    private static void greetUser() {
        String greeting = String.format("Hello! I'm %s and I'm here to establish another GST hike.%n"
                + "What can I do for you?", NAME);
        displayMessage(greeting);
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
        System.out.println("====================");
    }
}
