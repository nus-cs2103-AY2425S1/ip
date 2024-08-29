import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class JEFF {
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static final String DIR_PATH = "./data";
    private static final String FILE_PATH = DIR_PATH + "/JEFF.txt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public JEFF() {
        this.ui = new Ui();
        this.storage = new Storage(DIR_PATH, FILE_PATH);
    }

    public static void main(String[] args) {
        new JEFF().run();
    }

    public void run() {
        // Load saved files (if any)
        tasks =  new TaskList(storage.loadData());
        ui.showWelcome();
        ui.showLine();
        boolean exitChat = false;
        while (!exitChat) {
            try {
                String input = ui.readCommand();
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                exitChat = c.isExit();
            } catch (JEFFException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}