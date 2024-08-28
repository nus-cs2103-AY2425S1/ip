package bruno;

import bruno.command.Command;
import bruno.task.TaskList;

public class Bruno {
    public enum TaskType {
        TODO, DEADLINE, EVENT
    }
    private Storage storage;
    private TaskList tasks;

    public Bruno(String directoryPath, String filePath) {
        this.storage = new Storage(directoryPath, filePath);
        this.storage.ensureDirectoryExists();
        this.storage.ensureFileExists();
        this.tasks = new TaskList(this.storage);
    }

    public static void main(String[] args) {
        Bruno bruno = new Bruno("src/main/data/", "src/main/data/bruno.txt");
        bruno.run();
    }

    public void run() {
        Ui.printGreetingMessage();
        boolean running = true;
        while (running) {
            String userResponse = Ui.readCommand();
            Command command = Parser.parse(userResponse, tasks);
            if (command != null) {
                command.execute();
                running = !command.isExit();
            }
        }
    }
}