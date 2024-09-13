import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

    private boolean parseLine(BufferedReader br) throws IOException {
        try {
            parser.parse(br.readLine());
            return true;
        } catch (LukeException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Runs the Luke application
     */
    public void run() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ui.greetDialog();
        boolean isRunning = true;
        while (isRunning) {
            if (!parseLine(br)) {
                continue;
            }
            switch (parser.getCommand()) {
            case list -> {
                ui.listTaskDialog();
                this.taskList.listTasks();
            }
            case find -> ui.findDialog(taskList.findTasks(parser.getDescription()));
            case note -> {
                Note n = new Note(parser.getDescription());
                this.taskList.addTask(n);
                ui.addNoteDialog(n);
            }
            case deleteNote -> {
                Note n = (Note) taskList.deleteTask(parser.getIndex() - 1);
                ui.addNoteDialog(n);
            }
            case mark -> {
                Task t = taskList.markTask(parser.getIndex() - 1);
                ui.markDialog(t);
            }
            case unmark -> {
                Task t = taskList.unMarkTask(parser.getIndex() - 1);
                ui.unMarkDialog(t);
            }
            case delete -> {
                Task deletedTask = this.taskList.deleteTask(parser.getIndex() - 1);
                ui.deleteTaskDialog(deletedTask, this.taskList.getTaskListSize());
            }
            case todo -> {
                Task t = new Todo(parser.getDescription());
                this.taskList.addTask(t);
                ui.addTaskDialog(t, taskList.getTaskListSize());
            }
            case event -> {
                Task t = new Event(parser.getDescription(), parser.getFrom(), parser.getTo());
                this.taskList.addTask(t);
                ui.addTaskDialog(t, taskList.getTaskListSize());
            }
            case deadline -> {
                Task t = new DeadLine(parser.getDescription(), parser.getBy());
                this.taskList.addTask(t);
                ui.addTaskDialog(t, taskList.getTaskListSize());
            }
            case bye -> isRunning = false;

            default -> { }
            }

        }
        taskList.save(storage);
        ui.closeDialog();
        br.close();
    }

    public static void main(String[] args) throws IOException {
        new Luke("./data/Luke.txt").run();
    }
}
