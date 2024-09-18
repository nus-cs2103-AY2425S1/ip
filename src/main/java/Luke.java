import java.io.IOException;

import exceptions.LukeException;
import presentation.Ui;
import tasks.*;
import utility.Parser;
import utility.Storage;
import utility.TaskList;


/**
 * Main class of the application.
 */
public class Luke {
    private final Storage storage;
    private final Parser parser;
    private final TaskList taskList;
    private final Ui ui;

    /**
     * Returns an instance of Luke object.
     *
     * @param filePath Where data is stored.
     * @throws IOException If filePath direct to a non *.txt file.
     */
    public Luke(String filePath) throws IOException {
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        this.taskList = new TaskList(this.storage);
        this.ui = new Ui();
    }

    public String greetUser() {
        return ui.greetDialog();
    }

    public String handleUserInput(String input) throws IOException {
        try {
            parser.parse(input);
        } catch (LukeException e) {
            return "Parse failed: " + e.getMessage();
        }

        switch (parser.getCommand()) {
        case list -> {
            return ui.listTaskDialog(taskList.getTasks());
        }
        case find -> {
            return ui.findDialog(taskList.findTasks(parser.getDescription()));
        }
        case note -> {
            Note n = new Note(parser.getDescription());
            this.taskList.addTask(n);
            taskList.save(storage);
            return ui.addNoteDialog(n);
        }
        case deleteNote -> {
            Note n = (Note) taskList.deleteTask(parser.getIndex() - 1);
            taskList.save(storage);
            if (n != null) {
                return ui.addNoteDialog(n);
            } else {
                return ui.indexOutOfRangeDialog(parser.getIndex());
            }
        }
        case mark -> {
            Task t = taskList.markTask(parser.getIndex() - 1);
            taskList.save(storage);
            if (t != null) {
                return ui.markDialog(t);
            } else {
                return ui.indexOutOfRangeDialog(parser.getIndex());
            }
        }
        case unmark -> {
            Task t = taskList.unMarkTask(parser.getIndex() - 1);
            taskList.save(storage);
            if (t != null) {
                return ui.unMarkDialog(t);
            } else {
                return ui.indexOutOfRangeDialog(parser.getIndex());
            }
        }
        case delete -> {
            Task deletedTask = this.taskList.deleteTask(parser.getIndex() - 1);
            taskList.save(storage);
            if (deletedTask != null) {
                return ui.deleteTaskDialog(deletedTask, this.taskList.getTaskListSize());
            } else {
                return ui.indexOutOfRangeDialog(parser.getIndex());
            }
        }
        case todo -> {
            Task t = new Todo(parser.getDescription());
            this.taskList.addTask(t);
            taskList.save(storage);
            return ui.addTaskDialog(t, taskList.getTaskListSize());
        }
        case event -> {
            Task t = new Event(parser.getDescription(), parser.getFrom(), parser.getTo());
            this.taskList.addTask(t);
            taskList.save(storage);
            return ui.addTaskDialog(t, taskList.getTaskListSize());
        }
        case deadline -> {
            Task t = new DeadLine(parser.getDescription(), parser.getBy());
            this.taskList.addTask(t);
            taskList.save(storage);
            return ui.addTaskDialog(t, taskList.getTaskListSize());
        }
        case bye -> {
            taskList.save(storage);
            return ui.closeDialog();
        }

        case parseError -> {
            return String.format("Yo! This command \"%s\" doesn't exist.", input.split(" ")[0].trim());
        }

        default -> {
            return input;
        }
        }
    }

    public String getResponse(String input) {
        try {
            return handleUserInput(input);
        } catch (IOException e) {
            return "Dude, I cannot save the file: " + e.getMessage();
        }
    }
}
