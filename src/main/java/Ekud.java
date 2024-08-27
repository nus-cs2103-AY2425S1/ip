import java.util.Scanner;

public class Ekud {
    public static final String TASK_DATA_PATH = "data/tasks.txt";
    public static final String OUTPUT_PREFIX = "\t ";
    public static Scanner sc;


    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public Ekud(String outputPrefix, String filePath) {
        ui = new Ui(outputPrefix);
        storage = new Storage(filePath);
        tasks = storage.loadTasks(ui);
    }

    public void run() {
        ui.printGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand(sc);
                ui.printLineSeparator();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (EkudException e) {
                ui.printOutput(e.getMessage());
            } finally {
                ui.printLineSeparator();
            }
        }
    }

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        new Ekud(OUTPUT_PREFIX, TASK_DATA_PATH).run();
        sc.close();
    }
}
