import exceptions.LukeException;
import presentation.Ui;
import tasks.DeadLine;
import tasks.Event;
import tasks.Task;
import tasks.Todo;
import utility.Parser;
import utility.Storage;
import utility.TaskList;

import java.io.*;
import java.util.ArrayList;

public class Luke {
    private final Storage storage;
    private final Parser parser;
    private final TaskList taskList;
    private final Ui ui;

    public Luke(String filePath) throws IOException {
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        this.taskList = new TaskList(this.storage);
        this.ui = new Ui();
    }

    public void run() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ui.greetDialog();
        String line;
        boolean flag = true;
        while (flag) {
            line = br.readLine();
            try {
                parser.parse(line);
            } catch (LukeException e) {
                System.out.println(e.getMessage());
                continue;
            }

            switch (parser.getCommand()) {
            case list -> {
                ui.listTaskDialog();
                this.taskList.listTasks();
            }
            case find -> {
                ui.findDialog(taskList.findTasks(parser.description));
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
                ui.deleteTaskDialog(deletedTask, this.taskList.getTasksSize());
            }
            case todo -> {
                Task t = new Todo(parser.getDescription());
                this.taskList.addTask(t);
                ui.addTaskDialog(t, taskList.getTasksSize());
            }
            case event -> {
                Task t = new Event(parser.getDescription(), parser.getFrom(), parser.getTo());
                this.taskList.addTask(t);
                ui.addTaskDialog(t, taskList.getTasksSize());
            }
            case deadline -> {
                Task t = new DeadLine(parser.getDescription(), parser.getBy());
                this.taskList.addTask(t);
                ui.addTaskDialog(t, taskList.getTasksSize());
            }
            case bye -> flag = false;
            }
        }
        taskList.save(storage);
        ui.closingDialog();
        br.close();
    }
    public static void main(String[] args) throws IOException {
        new Luke("./data/Luke.txt").run();
    }
}



