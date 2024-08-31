package derek;

import derek.task.TaskList;

/**
 * Derek is a bot that interacts with the user, manages tasks, and provides functionalities like
 * adding tasks, marking them as complete or incomplete, deleting tasks, and more.
 */
public class Derek {
    private TaskList taskList;
    private Storage storage;

    public Derek() {
        this.taskList = new TaskList();
        this.storage = new Storage(this.taskList);
        this.taskList = storage.readFile();
        this.handleUserInteraction();
    }


    public void handleUserInteraction() {
        Ui ui = new Ui(storage, taskList);
        ui.introduce();
    }

    public static void main(String[] args) {
        Derek instance = new Derek();
    }


}






