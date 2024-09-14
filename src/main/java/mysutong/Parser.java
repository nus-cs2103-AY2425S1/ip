package mysutong;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The {@code Parser} class interprets and executes user commands within the MySutong application.
 * It processes the input commands, interacts with the task list, UI, and storage, and manages the task lifecycle.
 */
public class Parser {

    /**
     * Executes a user command by parsing it and performing the corresponding operation on the {@code TaskList}.
     * Commands include adding tasks (todo, deadline, event), marking/unmarking tasks as done, listing tasks, and deleting tasks.
     *
     * @param fullCommand the raw input command from the user.
     * @param tasks the current task list to operate on.
     * @param ui the UI handler to provide feedback to the user.
     * @param storage the storage handler to save/load tasks from persistent storage.
     */
    public void executeCommand(String fullCommand, TaskList tasks, Ui ui, Storage storage) {
        String[] inputs = fullCommand.split(" ", 2);
        String command = inputs[0];

        try {
            switch (command) {
            case "bye":
                ui.showGoodbye();
                System.exit(0);
                break;

            case "list":
                ui.showTaskList(tasks);
                break;

            case "mark":
                if (inputs.length < 2) {
                    throw new InvalidTaskNumberException("No task index provided.");
                }
                int markIndex = Integer.parseInt(inputs[1].trim()) - 1;
                if (markIndex < 0 || markIndex >= tasks.getTasks().size()) {
                    throw new InvalidTaskNumberException("Task index out of range.");
                }
                tasks.getTask(markIndex).markAsDone();
                ui.showLine();
                ui.showMessage("Nice! I've marked this task as done:");
                ui.showMessage(tasks.getTask(markIndex).toString());
                ui.showLine();
                storage.save(tasks);
                break;

            case "unmark":
                if (inputs.length < 2) {
                    throw new InvalidTaskNumberException("No task index provided.");
                }
                int unmarkIndex = Integer.parseInt(inputs[1].trim()) - 1;
                if (unmarkIndex < 0 || unmarkIndex >= tasks.getTasks().size()) {
                    throw new InvalidTaskNumberException("Task index out of range.");
                }
                tasks.getTask(unmarkIndex).unmark();
                ui.showLine();
                ui.showMessage("OK, I've marked this task as not done yet:");
                ui.showMessage(tasks.getTask(unmarkIndex).toString());
                ui.showLine();
                storage.save(tasks);
                break;

            case "todo":
                if (inputs.length < 2 || inputs[1].trim().isEmpty()) {
                    throw new NoDescriptionException("Description cannot be empty for todo.");
                }
                tasks.addTask(new Todo(inputs[1].trim()));
                ui.showLine();
                ui.showMessage("Got it. I've added this task:");
                ui.showMessage(tasks.getTask(tasks.getTasks().size() - 1).toString());
                ui.showLine();
                storage.save(tasks);
                break;

            case "deadline":
                if (inputs.length < 2 || !inputs[1].contains(" /by ")) {
                    throw new NoDescriptionException("Deadline command must include '/by' followed by a date/time.");
                }
                String[] deadlineDetails = inputs[1].split(" /by ", 2);
                if (deadlineDetails[0].trim().isEmpty() || deadlineDetails[1].trim().isEmpty()) {
                    throw new NoDescriptionException("Description or date/time missing for deadline.");
                }
                tasks.addTask(new Deadline(deadlineDetails[0].trim(), LocalDateTime.parse(deadlineDetails[1].trim(), DateTimeFormatter.ofPattern("d/M/yyyy HHmm"))));
                ui.showLine();
                ui.showMessage("Got it. I've added this task:");
                ui.showMessage(tasks.getTask(tasks.getTasks().size() - 1).toString());
                ui.showLine();
                storage.save(tasks);
                break;

            case "event":
                if (inputs.length < 2 || !inputs[1].contains(" /from ") || !inputs[1].contains(" /to ")) {
                    throw new NoDescriptionException("Event command must include '/from' and '/to' followed by their respective times.");
                }
                String[] eventDetails = inputs[1].split(" /from | /to ", 3);
                if (eventDetails[0].trim().isEmpty() || eventDetails[1].trim().isEmpty() || eventDetails[2].trim().isEmpty()) {
                    throw new NoDescriptionException("Description or times missing for event.");
                }
                tasks.addTask(new Event(eventDetails[0].trim(), LocalDateTime.parse(eventDetails[1].trim(), DateTimeFormatter.ofPattern("d/M/yyyy HHmm")), LocalDateTime.parse(eventDetails[2].trim(), DateTimeFormatter.ofPattern("d/M/yyyy HHmm"))));
                ui.showLine();
                ui.showMessage("Got it. I've added this task:");
                ui.showMessage(tasks.getTask(tasks.getTasks().size() - 1).toString());
                ui.showLine();
                storage.save(tasks);
                break;

            case "delete":
                if (inputs.length < 2) {
                    throw new InvalidTaskNumberException("No task index provided for deletion.");
                }
                int deleteIndex = Integer.parseInt(inputs[1].trim()) - 1;
                if (deleteIndex < 0 || deleteIndex >= tasks.getTasks().size()) {
                    throw new InvalidTaskNumberException("Task index out of range for deletion.");
                }
                Task removedTask = tasks.getTask(deleteIndex);
                tasks.removeTask(deleteIndex);
                ui.showLine();
                ui.showMessage("Noted. I've removed this task:");
                ui.showMessage(removedTask.toString());
                ui.showLine();
                storage.save(tasks);
                break;

            default:
                throw new UnknownCommandException("I'm sorry, but I don't know what that means.");
            }
        } catch (NumberFormatException e) {
            ui.showError("Invalid number format. Please enter a valid task index.");
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
    }
}
