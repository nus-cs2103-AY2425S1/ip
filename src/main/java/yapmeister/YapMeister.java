package yapmeister;

import yapmeister.task.TaskList;

/**
 * YapMeister main entry point.
 */
public class YapMeister {
    final static int MAX_TASKS = 100;
    private UI ui;
    private Storage storage;
    private TaskList tasks;
    
    enum TaskType {
        ToDo,
        Deadline,
        Event
    }

    /**
     * Creates YapMeister program with specified save file at filepath
     * @param filepath Save file for Tasks created with YapMeister.
     */
    public YapMeister(String filepath) {
        this.ui = new UI();
        this.storage = new Storage(filepath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (StorageException e) {
            //ui.showLoadingError();
            this.tasks = new TaskList();
        }
        Parser parser = new Parser(this.storage, this.tasks);
        this.ui.setParser(parser);
    }

    /**
     * Starts YapMeister through UI.process()
     */
    public void run() {
        this.ui.process();
    }

    public static void main(String[] args) {
        new YapMeister("data/tasks.txt").run();
    }


}
