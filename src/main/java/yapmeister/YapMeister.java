package yapmeister;

import yapmeister.task.TaskList;

public class YapMeister {
    final static int MAX_TASKS = 100;
    private UI ui;
    private Storage storage;
    private TaskList tasks;
    private boolean isRunning;
    
    enum TaskType {
        ToDo,
        Deadline,
        Event
    }

    public YapMeister(String filepath) {
        this.isRunning = true;
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

    public void run() {
        ui.process();
    }

    public static void main(String[] args) {
        new YapMeister("data/tasks.txt").run();
    }


}
