package colress;

import colress.exception.FileCorruptedException;

import java.io.IOException;

public final class Colress {
    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;

    public Colress(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList();
    }

    public void run() {
        try {
            ui.welcome();
            ui.printLoadTaskStatus(storage.loadTasks(taskList));
            ui.printTasks(taskList, true);
            while (!ui.getHasCalledExitCommand()) {
                ui.processInput(taskList);
                storage.writeToTaskFile(taskList);
            }
        } catch (FileCorruptedException e) {
            ui.print(String.valueOf(e));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Colress("task.txt").run();
    }
}
