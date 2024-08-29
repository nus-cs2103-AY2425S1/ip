import java.io.IOException;

public class Sage {
    public static TaskList taskList;
    public static Ui ui;
    public static Storage storage;

    public static void main(String[] args) {
        new Sage("data/sage.txt").run();
    }

    public Sage(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            taskList = new TaskList(storage.load());
        } catch (IOException | SageException e) {
            ui.showError(e.getMessage());
        }
    }

    public void run() {
        ui.showWelcome();

        while (true) {
            try {
                String input = ui.readInput();
                String[] fullCommand = input.split(" ", 2);
                String command = fullCommand[0];

                if (input.equalsIgnoreCase("bye")) {
                    storage.save(taskList);
                    ui.showGoodbye();
                    break;

                } else if (input.equals("list")) {
                    taskList.listTasks();

                } else if ((command.equals("mark") || command.equals("unmark")) && fullCommand.length > 1) {
                    boolean isDone = command.equals("mark");
                    int index;
                    try {
                        index = Integer.parseInt(fullCommand[1].trim()) - 1;
                    } catch (NumberFormatException e) {
                        throw new SageException("Invalid mark/unmark command. Index must be a number.");
                    }
                   taskList.mark(index, isDone);

                } else if (command.equals("todo") && fullCommand.length > 1) {
                    String description = fullCommand[1].trim();
                    if (description.isEmpty())
                        throw new SageException("Invalid todo command. Please include a description.");

                    Task toDo = new ToDo(description);
                    taskList.add(toDo);
                    ui.showAddedTask(toDo, taskList.size());

                } else if (command.equals("deadline") && fullCommand.length > 1) {
                    String[] deadlineCommand = fullCommand[1].split(" /by ", 2);
                    if (deadlineCommand.length < 2)
                        throw new SageException("Invalid deadline command. Please include a description and /by.");

                    String description = deadlineCommand[0].trim();
                    String by = deadlineCommand[1].trim();

                    Task deadline = new Deadline(description, by);
                    taskList.add(deadline);
                    ui.showAddedTask(deadline, taskList.size());

                } else if (command.equals("event") && fullCommand.length > 1) {
                    String[] eventCommand = fullCommand[1].split(" /from | /to ", 3);
                    if (eventCommand.length < 3)
                        throw new SageException("Invalid event command. Please include description, /from, and /to.");

                    String description = eventCommand[0].trim();
                    String from = eventCommand[1].trim();
                    String to = eventCommand[2].trim();

                    Task event = new Event(description, from, to);
                    taskList.add(event);
                    ui.showAddedTask(event, taskList.size());

                } else if (command.equals("delete") && fullCommand.length > 1) {
                    int index;
                    try {
                        index = Integer.parseInt(fullCommand[1].trim()) - 1;
                    } catch (NumberFormatException e) {
                        throw new SageException("Invalid delete command. Index must be a number.");
                    }

                    Task deletedTask = taskList.delete(index);
                    ui.showDeletedTask(deletedTask, taskList.size());

                } else {
                    throw new SageException("Invalid command.");
                }

            } catch (IOException | SageException e) {
                ui.showError(e.getMessage());
            }
        }
    }
}
