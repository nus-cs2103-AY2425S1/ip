import java.util.Scanner;

public class Meep {
    private final Storage storage;
    private TaskList taskList;
    private final Ui ui;
    private final Parser parser;

    public Meep(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (MeepException e) {
            ui.errorLoadingTask();
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.greeting();
        Scanner scanner = new Scanner(System.in);
        boolean isDone = false;
        // keep scanning for user input
        while (!isDone) {
            try {
                ui.inputWaiting();
                String input = scanner.nextLine();
                isDone = this.parser.checkCommand(input, taskList);
                storage.saveTasks(taskList);
            } catch (MeepException e) {
                ui.error();
            }
        }
    }

    public static void main(String[] args) {
        new Meep("data.txt").run();
    }
}
