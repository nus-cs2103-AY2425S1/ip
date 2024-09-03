import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Lawrence {
    private static final String NAME = "Lawrence";
    private static final Path SAVE_LOCATION = Paths.get(".", "data", "tasks.txt");
    private TaskList taskList;
    private final TaskFileManager manager;
    private final UserInterface ui;

    public Lawrence() {
        ui = new UserInterface(NAME);
        manager = new TaskFileManager(SAVE_LOCATION);
        try {
            Task[] existingTasks = manager.readTasksFromFile();
            taskList = new TaskList(existingTasks);
        } catch (IOException e) {
            // initialise with no tasks instead
            taskList = new TaskList(new Task[0]);
        }
    }

    public static void main(String[] args) {
        Lawrence primeMinister = new Lawrence();
        primeMinister.run();
    }

    public void run() {
        ui.greetUser();

        Scanner sc = new Scanner(System.in);
        String userInput;
        while (true) {
            userInput = sc.nextLine();  // Get next user input

            // Parse command at start of the line
            String[] inputComponents = userInput.split(" ", 2);

            CommandType command;
            try {
                command = CommandType.fromString(inputComponents[0]);
            } catch (IllegalArgumentException e) {
                ui.showMessage(
                        String.format("Unable to recognize command '%s'. Please try again.", inputComponents[0]));
                continue;  // Skip to the next iteration
            }

            switch (command) {
            case EXIT:
                ui.exitSession();
                break;
            case DISPLAY:
                displayTasks();
                break;
            case MARK_COMPLETE:
                if (inputComponents.length < 2) {
                    ui.showMessage("Please specify the task you want to mark as complete.");
                    break;
                }
                int taskNumberToComplete = Integer.parseInt(inputComponents[1]);
                markTaskAsComplete(taskNumberToComplete);
                break;
            case MARK_INCOMPLETE:
                if (inputComponents.length < 2) {
                    ui.showMessage("Please specify the task you want to mark as incomplete.");
                    break;
                }
                int taskNumberToIncomplete = Integer.parseInt(inputComponents[1]);
                markTaskAsIncomplete(taskNumberToIncomplete);
                break;
            case DELETE:
                if (inputComponents.length < 2) {
                    ui.showMessage("Please specify the task you want to delete.");
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
                    ui.showMessage(
                            String.format("Invalid date and/or time provided. DateTime should be in the format: %s",
                                    DateUtils.USER_INPUT_FORMAT_STRING));
                } catch (IllegalArgumentException e) {
                    ui.showMessage(String.format(e.getMessage()));
                }
                break;
            default:
                // This case should never be reached
                throw new IllegalStateException("Unexpected command: " + command);
            }
        }
    }

    private void addTask(Task t) {
        try {
            taskList.addTask(t);
            saveTasks();

            // Format components of message
            int numberOfTasks = taskList.getSize();
            String verb = numberOfTasks == 1 ? "is" : "are";
            String plural = numberOfTasks == 1 ? "" : "s";
            ui.showMessage(String.format("Alright, added task:%n%s%nto the list.%n"
                    + "There %s currently %d task%s in the list.", t, verb, numberOfTasks, plural));
        } catch (IOException e) {
            ui.showMessage("Failed to add task to the list. Please try again later.");
        }
    }

    private void deleteTask(int taskNumber) {
        try {
            Task deletedTask = taskList.deleteTask(taskNumber);
            saveTasks();

            ui.showMessage(String.format("Task %s has been deleted.", deletedTask));
        } catch (NumberFormatException e) {
            ui.showMessage("Please specify a number to select a task.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            ui.showMessage(String.format("%s Please try again.", e.getMessage()));
        } catch (IOException e) {
            ui.showMessage(String.format("Failed to delete task %d from the list. Please try again.", taskNumber));
        }
    }

    private void markTaskAsComplete(int taskNumber) {
        try {
            Task completeTask = taskList.completeTask(taskNumber);
            saveTasks();

            ui.showMessage(
                    String.format("I've marked the task as complete:%n%s", completeTask));
        } catch (NumberFormatException e) {
            ui.showMessage("Please specify a number to select a task.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            ui.showMessage(String.format("%s Please try again.", e.getMessage()));
        } catch (IOException e) {
            ui.showMessage(String.format("Failed to mark task %d as complete. Please try again later.", taskNumber));
        }
    }

    private void markTaskAsIncomplete(int taskNumber) {
        try {
            Task incompleteTask = taskList.uncompleteTask(taskNumber);
            saveTasks();

            ui.showMessage(String.format("Changed your mind? The task is set to incomplete:%n%s", incompleteTask));
        } catch (NumberFormatException e) {
            ui.showMessage("Please specify a number to select a task.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            ui.showMessage(String.format("%s Please try again.", e.getMessage()));
        } catch (IOException e) {
            ui.showMessage(String.format("Failed to mark task %d as incomplete. Please try again later.", taskNumber));
        }
    }

    private void saveTasks() throws IOException{
        manager.saveTasksToFile(taskList.getTasks());
    }

    private void displayTasks() {
        if (taskList.getSize() < 1) {
            ui.showMessage("You have no tasks at the moment.");
            return;
        }
        ui.showMessage(String.format("Here's your laundry list:%n%s", taskList));
    }
}
