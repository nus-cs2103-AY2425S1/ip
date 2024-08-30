package chobo;
import java.util.Scanner;
public class Chobo {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private static final String FILE_PATH = "./data/chobo.txt";

    public Chobo() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        taskList = new TaskList(storage.loadTasks());
    }

    public void run() {
        ui.printWelcome();
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            try {
                String Command = scanner.nextLine();
                isExit = Parser.parse(Command, taskList, ui, storage);
            } catch (InputException e) {
                ui.printError(e.getMessage());
            }
        }
        ui.printGoodbye();
    }

    public static void main(String[] args) {
        new Chobo().run();
    }
}






