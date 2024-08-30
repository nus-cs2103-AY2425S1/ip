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
        ui = new UI();
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.load());
        } catch (StorageException e) {
            //ui.showLoadingError();
            tasks = new TaskList();
        }
        Parser parser = new Parser(storage, tasks);
        ui.setParser(parser);
    }

    /**
     * Starts YapMeister through UI.process()
     */
    public void run() {
        ui.process();
    }

    public static void main(String[] args) {
        new YapMeister("data/tasks.txt").run();
    }


}
