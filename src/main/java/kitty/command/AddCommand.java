package kitty.command;

import kitty.Parser;
import kitty.Storage;
import kitty.TaskList;
import kitty.Ui;
import kitty.kittyexceptions.DeadlineException;
import kitty.kittyexceptions.EventException;
import kitty.kittyexceptions.KittyException;
import kitty.tasks.Deadline;
import kitty.tasks.Event;
import kitty.tasks.Task;
import kitty.tasks.Todo;


import java.io.IOException;

public class AddCommand extends Command {
    private final String commandBody;
    private final Storage storage;

    public AddCommand(Ui ui, TaskList taskList, String commandBody, Storage storage) {
        super(ui, taskList);
        this.commandBody = commandBody;
        this.storage = storage;
    }

    @Override
    public String run() {
        String[] parts = commandBody.split(" ", 2);
        Task task;

        // create task if input is valid
        try {
            switch (parts[0]) {
            case "todo" -> {
                task = new Todo(parts[1].trim());
            }
            case "deadline" -> {
                if (Parser.checkDeadline(parts[1].trim(), ui)) {
                    String[] aux = Parser.parseDeadline(parts[1]);
                    task = new Deadline(aux[0], Parser.parseDateTime(aux[1].trim()));
                } else {
                    throw new DeadlineException();
                }
            }
            case "event" -> {
                if (Parser.checkEvent(parts[1], ui)) {
                    String[] aux = Parser.parseEvent(parts[1]);
                    task = new Event(aux[0],
                            Parser.parseDateTime(aux[1].trim()),
                            Parser.parseDateTime(aux[2].trim()));
                } else {
                    throw new EventException();
                }
            }
            default -> {
                return "default addCommand";
            }
            }
        } catch (KittyException e) {
            return e.toString();
        }

        return addTaskToList(task);
    }

    private String addTaskToList(Task task) {
        int size = tasks.addTask(task);
        if (size == -1) {
            return "task not created";
        }

        // if successfully add task to list, update storage and return data back
        String data = task.getTaskData();
        try {
            System.out.println(data);
            storage.addContent(data);
            return ui.showAddTaskMessage(task, size);
        } catch (IOException e) {
            String fileWritingFailMessage = "File writing unsuccessful.\n"
                    + "This task is not updated to hard disk.";
            return ui.showErrorMessage(fileWritingFailMessage);
        }

    }
}
