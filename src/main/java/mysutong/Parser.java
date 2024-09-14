package mysutong;

import mysutong.Deadline;
import mysutong.Event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Parser {
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
                    int markIndex = Integer.parseInt(inputs[1]) - 1;
                    tasks.getTask(markIndex).markAsDone();
                    ui.showLine();
                    ui.showMessage("Nice! I've marked this task as done:");
                    ui.showMessage(tasks.getTask(markIndex).toString());
                    ui.showLine();
                    storage.save(tasks);
                    break;

                case "unmark":
                    int unmarkIndex = Integer.parseInt(inputs[1]) - 1;
                    tasks.getTask(unmarkIndex).unmark();
                    ui.showLine();
                    ui.showMessage("OK, I've marked this task as not done yet:");
                    ui.showMessage(tasks.getTask(unmarkIndex).toString());
                    ui.showLine();
                    storage.save(tasks);
                    break;

                case "todo":
                    tasks.addTask(new Todo(inputs[1]));
                    ui.showLine();
                    ui.showMessage("Got it. I've added this task:");
                    ui.showMessage(tasks.getTask(tasks.getTasks().size() - 1).toString());
                    ui.showLine();
                    storage.save(tasks);
                    break;

                case "deadline":
                    String[] deadlineDetails = inputs[1].split("/by");
                    tasks.addTask(new Deadline(deadlineDetails[0].trim(), LocalDateTime.parse(deadlineDetails[1].trim(), DateTimeFormatter.ofPattern("d/M/yyyy HHmm"))));
                    ui.showLine();
                    ui.showMessage("Got it. I've added this task:");
                    ui.showMessage(tasks.getTask(tasks.getTasks().size() - 1).toString());
                    ui.showLine();
                    storage.save(tasks);
                    break;

                case "event":
                    String[] eventDetails = inputs[1].split("/from|/to");
                    tasks.addTask(new Event(eventDetails[0].trim(), LocalDateTime.parse(eventDetails[1].trim(), DateTimeFormatter.ofPattern("d/M/yyyy HHmm")), LocalDateTime.parse(eventDetails[2].trim(), DateTimeFormatter.ofPattern("d/M/yyyy HHmm"))));
                    ui.showLine();
                    ui.showMessage("Got it. I've added this task:");
                    ui.showMessage(tasks.getTask(tasks.getTasks().size() - 1).toString());
                    ui.showLine();
                    storage.save(tasks);
                    break;

                case "delete":
                    int deleteIndex = Integer.parseInt(inputs[1]) - 1;
                    Task removedTask = tasks.getTask(deleteIndex);
                    tasks.removeTask(deleteIndex);
                    ui.showLine();
                    ui.showMessage("Noted. I've removed this task:");
                    ui.showMessage(removedTask.toString());
                    ui.showLine();
                    storage.save(tasks);
                    break;

                case "find":
                    if (inputs.length < 2 || inputs[1].trim().isEmpty()) {
                        throw new NoDescriptionException("Find command requires a keyword.");
                    }
                    List<Task> foundTasks = tasks.findTasksByKeyword(inputs[1].trim());
                    ui.showSearchResults(foundTasks);
                    break;

                default:
                    throw new UnknownCommandException("I'm sorry, but I don't know what that means.");
            }
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
    }
}
