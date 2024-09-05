package chatbot;

import storage.Storage;
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

    public String getResponse(String input) {
        try {
            String response = Parser.answer(input, taskList);
            storage.write(taskList.getTaskList());
            return response;
        } catch (Exception e) {
            return("?");
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello!");
    }
}
