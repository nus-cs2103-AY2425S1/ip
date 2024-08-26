package neko;
import neko.task.Task;
import neko.task.TaskList;
import neko.ui.Ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.IOException;

public class Neko {
    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_ADD = "add";
    private static final String COMMAND_DELETE = "delete";
    private static final String FILE_PATH = "./data/neko.txt";
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Neko(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (FileNotFoundException e) {
            ui.showMessage(e.getMessage());
            tasks = new TaskList(new ArrayList<>());
        }
    }
    public static void main(String[] args) {
        Neko neko = new Neko(FILE_PATH);
        neko.run();
    }

    public void run() {

        ui.showGreeting();

        String input = ui.getUserCommand();
        while (!input.equals(COMMAND_EXIT)) {
            try {
                String command = Parser.parseCommand(input);
                handleInput(command, input);
            } catch (NekoException | IOException e) {
                ui.showMessage(e.getMessage());
            } finally {
                input = ui.getUserCommand();
            }
        }
        ui.showExitMessage();
    }

    private void handleInput(String command, String input) throws NekoException, IOException {
        Task task;
        switch (command) {
        case COMMAND_LIST:
            tasks.listTasks(ui);
            break;
        case COMMAND_MARK:
            task = tasks.markTask(
                    Integer.parseInt(input.split(" ")[1]) - 1);
            if (task != null) {
                ui.showMessage("Nice meow! I've marked this task as done:\n " + task);
            } else {
                throw new NekoException("The task is already marked as done meow!");
            }
            break;
        case COMMAND_UNMARK:
            task = tasks.unmarkTask(
                    Integer.parseInt(input.split(" ")[1]) - 1);
            if (task != null) {
                ui.showMessage("Ok meow, I've marked this task as not done yet:\n " + task);
            } else {
                throw new NekoException("The task is not marked as done yet meow!");
            }
            break;
        case COMMAND_DELETE:
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            task = tasks.getTask(index);
            tasks.deleteTask(index);
            storage.rewriteFile(tasks);
            ui.showMessage("Noted meow. I've removed this task\n " + task +"\nNow you have "
                    + tasks.size() + " tasks in the list.");
            break;
        case COMMAND_ADD:
            ui.showMessage("Let's add a task meow!");
            tasks.addTask(ui.getTaskType(), ui, storage);
            break;
        default:
            throw new NekoException("Unknown command.");
        }
    }
}
