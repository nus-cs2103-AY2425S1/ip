import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Sage {
    public static List<Task> tasksList = new ArrayList<>();
    public static Ui ui;
    public static Storage storage;

    public static void main(String[] args) {
        ui = new Ui();
        storage = new Storage("data/sage.txt");

        try {
            tasksList = storage.load();
        } catch (IOException | SageException e) {
            ui.showError(e.getMessage());
        }

        ui.showWelcome();

        while (true) {
            try {
                String input = ui.readInput();
                String[] fullCommand = input.split(" ", 2);
                String command = fullCommand[0];

                if (input.equalsIgnoreCase("bye")) {
                    storage.save(tasksList);
                    ui.showGoodbye();
                    break;

                } else if (input.equals("list")) {
                    if (tasksList.isEmpty()) {
                        ui.showEmptyList();
                    } else {
                        StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
                        for (int i = 0; i < tasksList.size(); i++) {
                            Task task = tasksList.get(i);
                            result.append(String.format("%d. %s\n", i + 1, task.toString()));
                        }
                        ui.showResponse(String.valueOf(result));
                    }

                } else if ((command.equals("mark") || command.equals("unmark")) && fullCommand.length > 1) {
                    boolean doneStatus = command.equals("mark");
                    StringBuilder confirmationMessage = new StringBuilder(doneStatus ? "Nice! I've marked this task as done:\n"
                            : "OK, I've marked this task as not done yet:\n");

                    int index;
                    try {
                        index = Integer.parseInt(fullCommand[1].trim()) - 1;
                    } catch (NumberFormatException e) {
                        throw new SageException("Invalid mark/unmark command. Index must be a number.");
                    }

                    if (index < 0 || index >= tasksList.size()) {
                        throw new SageException("Invalid index. Please try again.");
                    }

                    Task task = tasksList.get(index);
                    task.setDone(doneStatus);
                    confirmationMessage.append(task);
                    ui.showResponse(String.valueOf(confirmationMessage));

                } else if (command.equals("todo") && fullCommand.length > 1) {
                    String description = fullCommand[1].trim();
                    if (description.isEmpty())
                        throw new SageException("Invalid todo command. Please include a description.");

                    Task toDo = new ToDo(description);
                    tasksList.add(toDo);
                    ui.showAddedTask(toDo, tasksList.size());

                } else if (command.equals("deadline") && fullCommand.length > 1) {
                    String[] deadlineCommand = fullCommand[1].split(" /by ", 2);
                    if (deadlineCommand.length < 2)
                        throw new SageException("Invalid deadline command. Please include a description and /by.");

                    String description = deadlineCommand[0].trim();
                    String by = deadlineCommand[1].trim();

                    Task deadline = new Deadline(description, by);
                    tasksList.add(deadline);
                    ui.showAddedTask(deadline, tasksList.size());

                } else if (command.equals("event") && fullCommand.length > 1) {
                    String[] eventCommand = fullCommand[1].split(" /from | /to ", 3);
                    if (eventCommand.length < 3)
                        throw new SageException("Invalid event command. Please include description, /from, and /to.");

                    String description = eventCommand[0].trim();
                    String from = eventCommand[1].trim();
                    String to = eventCommand[2].trim();

                    Task event = new Event(description, from, to);
                    tasksList.add(event);
                    ui.showAddedTask(event, tasksList.size());

                } else if (command.equals("delete") && fullCommand.length > 1) {
                    int index;
                    try {
                        index = Integer.parseInt(fullCommand[1].trim()) - 1;
                    } catch (NumberFormatException e) {
                        throw new SageException("Invalid delete command. Index must be a number.");
                    }
                    if (index < 0 || index >= tasksList.size()) {
                        throw new SageException("Invalid index. Please try again.");
                    }
                    Task deletedTask = tasksList.get(index);
                    tasksList.remove(index);
                    ui.showDeletedTask(deletedTask, tasksList.size());

                } else {
                    throw new SageException("Invalid command.");
                }
                
            } catch (IOException | SageException e) {
                ui.showError(e.getMessage());
            }
        }
    }
}
