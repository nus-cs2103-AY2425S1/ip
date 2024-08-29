import tasks.TaskList;

public class Peridot {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Peridot(String filePath) {
        storage = new Storage(filePath);
        this.taskList = new TaskList(storage.load());
        ui = new Ui(taskList, storage);
    }

    /**
     * Starts the chatbot "Peridot"
     */
    public void start() {
        ui.run();
        storage.write(taskList.getTaskList());
    }

    public static void main(String[] args) {
        new Peridot("./src/data/chatData.txt").start();
    }
}
