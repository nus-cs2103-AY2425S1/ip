package fence;

import fence.parser.Parser;
import fence.storage.Storage;
import fence.task.Task;
import fence.tasklist.TaskList;
import fence.ui.Ui;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Fence {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Fence() {
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();
        try {
            tasks = new TaskList(storage.read(parser));
        } catch (NoSuchElementException e) {
            ui.printLoadingError();
            tasks = new TaskList(new ArrayList<>());
        }
    }

    public void run() {
        ui.greet();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String command = scanner.nextLine();
            try {
                String commandType = parser.parseInput(command);
                if (commandType.equals("bye")) {
                    ui.exit();
                    break;
                } else if (commandType.equals("list")) {
                    ui.list(tasks);
                } else if (commandType.equals("task")) {
                    Task task = parser.getTask();
                    tasks.add(task);
                    ui.add(task);
                    ui.count(tasks.getSize());
                    storage.saveAppend(task);
                } else if (commandType.equals("mark")) {
                    int index = parser.getIndex();
                    tasks.mark(index);
                    ui.mark(tasks.getTask(index - 1));
                    storage.saveRewrite(tasks);
                } else if (commandType.equals("unmark")) {
                    int index = parser.getIndex();
                    tasks.unmark(index);
                    ui.unmark(tasks.getTask(index - 1));
                    storage.saveRewrite(tasks);
                } else if (commandType.equals("delete")) {
                    int index = parser.getIndex();
                    ui.delete(tasks.getTask(index - 1));
                    tasks.delete(index);
                    ui.count(tasks.getSize());
                    storage.saveRewrite(tasks);
                } else {
                    ui.printUnknownCommand();
                }
            } catch (NoSuchElementException e) {
                ui.printMissingFieldError();
            } catch (DateTimeParseException e) {
                ui.printInvalidDateError();
            } catch (NumberFormatException e) {
                ui.printInvalidNumberError();
            }
        }
    }

    public static void main(String[] args) {
        Fence fence = new Fence();
        fence.run();
    }
}


