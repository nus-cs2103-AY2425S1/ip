package yapmeister;

import yapmeister.task.TaskList;

/**
 * YapMeister main entry point.
 */
public class YapMeister {
    private UI ui;
    private Storage storage;
    private TaskList tasks;
    private final String name = "YapMeister";

    /**
     * Creates YapMeister program with specified save file at filepath
     * @param filepath Save file for Tasks created with YapMeister.
     */
    public YapMeister(String filepath) {
        this.ui = new UI();
        this.storage = new Storage(filepath);
        try {
            this.tasks = new TaskList(storage.loadTasks());
        } catch (StorageException e) {
            //ui.showLoadingError();
            this.tasks = new TaskList();
        }
        Parser parser = new Parser(this.storage, this.tasks, this.ui);
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

    public String getResponse(String input) {
        return this.ui.getResponse(input);
    }

    public String getWelcomeMessage() {
        return this.ui.getWelcomeMessage();
    }

    public String getName() {
        return this.name;
    }
}
