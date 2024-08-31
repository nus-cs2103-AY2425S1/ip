package colress;

import java.io.IOException;

import colress.exception.FileCorruptedException;

public final class Colress {
    private final Ui UI;
    private final Storage STORAGE;
    private final TaskList TASK_LIST;

    public Colress(String filePath) {
        UI = new Ui();
        STORAGE = new Storage(filePath);
        TASK_LIST = new TaskList();
    }

    public void run() {
        try {
            UI.welcome();
            UI.printLoadTaskStatus(STORAGE.loadTasks(TASK_LIST));
            UI.printTasks(TASK_LIST, true);
            while (!UI.getHasCalledExitCommand()) {
                UI.processInput(TASK_LIST);
                STORAGE.writeToTaskFile(TASK_LIST);
            }
        } catch (FileCorruptedException e) {
            UI.print(String.valueOf(e));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Colress("task.txt").run();
    }
}
