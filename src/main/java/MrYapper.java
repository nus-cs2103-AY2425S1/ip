import java.io.IOException;

public class MrYapper {

    private static final String TASK_DATA_PATH = "src/data/tasks.txt";
    private final StorageManager storageManager;
    private TaskList tasks;
    private final Ui ui;

    public MrYapper(String filePath) {
        this.ui = new Ui();
        this.storageManager = new StorageManager(filePath);
        try {
            this.tasks = storageManager.retrieveData();
        } catch (IOException e) {
            System.out.println(" An error occurred when creating a new data file :(");
        }
    }

    public void run() {
        boolean conversationIsOngoing = false;
        if (tasks != null && storageManager != null) {
            ui.showGreeting();
            conversationIsOngoing = true;
        }

        while (conversationIsOngoing) {
            try {
                String userInput = ui.readCommand();
                Command c = Parser.parse(userInput);
                conversationIsOngoing = !c.execute(tasks, ui, storageManager);
            } catch (IllegalTaskException | InvalidSyntaxException | IllegalArgumentException e) {
                ui.say(e.getMessage());
            }
        }
        ui.close();
    }

    public static void main(String[] args) {
        new MrYapper(TASK_DATA_PATH).run();
    }
}
