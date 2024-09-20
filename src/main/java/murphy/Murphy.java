package murphy;

import javafx.application.Platform;
import murphy.storage.Storage;
import murphy.task.Deadline;
import murphy.task.Event;
import murphy.task.Task;
import murphy.task.TaskList;
import murphy.task.Todo;
import murphy.ui.Ui;

/**
 * Main class of the Murphy application, a chatbot which can record and display tasks.
 */
public class Murphy {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Creates a new Murphy object using the save file at the specified filepath.
     */
    public Murphy(String filepath, Ui ui) {
        this.ui = ui;
        try {
            storage = new Storage(filepath);
            tasks = new TaskList(storage.load());
        } catch (MurphyException e) {
            ui.showError(String.format("Error loading save file: %s", e.getMessage()));
            tasks = new TaskList();
        }
    }

    /**
     * Gets user input continually to add, delete and modify tasks until the user decides to terminate
     * the program.
     */
    public void inputListener(String input) {
        if (input.equals("bye")) {
            bye();
        } else if (input.equals("list")) {
            list();
        } else if (input.startsWith("mark ")) {
            mark(input);
        } else if (input.startsWith("unmark ")) {
            unmark(input);
        } else if (input.startsWith("delete ")) {
            delete(input);
        } else if (input.startsWith("todo ")) {
            todo(input);
        } else if (input.startsWith("deadline ")) {
            deadline(input);
        } else if (input.startsWith("event ")) {
            event(input);
        } else if (input.startsWith("find ")) {
            find(input);
        } else {
            ui.showError("Command not found");
        }
    }

    private void bye() {
        ui.showText("Bye. Hope to see you again soon!");
        try {
            storage.write(tasks);
        } catch (MurphyException e) {
            ui.showError(String.format("Error saving tasks to storage: %s", e.getMessage()));
        } finally {
            Platform.exit();
        }
    }

    private void list() {
        ui.showText(tasks.toString());
    }

    private void mark(String input) {
        String[] split = input.split(" ");
        if (split.length != 2) {
            ui.showError("mark usage: \"mark [task number]\"");
        }
        try {
            int index = Integer.parseInt(split[1]);
            ui.showText(tasks.markItem(index));
        } catch (MurphyException | NumberFormatException e) {
            ui.showError(String.format("Error marking task: %s", e.getMessage()));
        }
    }

    private void unmark(String input) {
        String[] split = input.split(" ");
        if (split.length != 2) {
            ui.showError("unmark usage: \"unmark [task number]\"");
        }
        try {
            int index = Integer.parseInt(split[1]);
            ui.showText(tasks.unmarkItem(index));
        } catch (MurphyException e) {
            ui.showError(String.format("Error unmarking task: %s", e.getMessage()));
        }
    }

    private void delete(String input) {
        String[] split = input.split(" ");
        if (split.length != 2) {
            ui.showError("delete usage: \"delete [task number]\"");
        }
        try {
            int index = Integer.parseInt(split[1]);
            ui.showText(tasks.deleteItem(index));
        } catch (MurphyException e) {
            ui.showError(String.format("Error deleting task: %s", e.getMessage()));
        }
    }

    private void todo(String input) {
        try {
            Task todo = new Todo(input.substring(5));
            ui.showText(tasks.addItem(todo));
        } catch (MurphyException e) {
            ui.showError(String.format("Error adding todo: %s", e.getMessage()));
        }
    }

    private void deadline(String input) {
        if (!input.contains("/by ")) {
            ui.showError("deadline usage: \"deadline [description] /by [date]\"");
        }
        String[] split = input.split("/by ");
        try {
            Task deadline = new Deadline(split[0].substring(9).trim(), split[1]);
            ui.showText(tasks.addItem(deadline));
        } catch (MurphyException e) {
            ui.showError(String.format("Error adding deadline: %s", e.getMessage()));
        }
    }

    private void event(String input) {
        if (!input.contains("/from ") || !input.contains("/to ")) {
            ui.showError("event usage: \"event [description] /from [date] /to [date]\"");
        }
        String[] split = input.split("/from ");
        String[] split2 = split[1].split("/to ");
        try {
            Task event = new Event(split[0].substring(6).trim(), split2[0].trim(), split2[1]);
            ui.showText(tasks.addItem(event));
        } catch (MurphyException e) {
            ui.showError(String.format("Error adding event: %s", e.getMessage()));
        }
    }

    private void find(String input) {
        try {
            ui.showText(tasks.find(input.substring(5)));
        } catch (MurphyException e) {
            ui.showError(e.getMessage());
        }
    }
}
