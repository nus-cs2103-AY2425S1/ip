import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hyperion {
    private Storage storage;
    private TaskList allTasks;
    private Ui ui;
    boolean carryOn = true;
    // takes in a filepath for the storage
    public Hyperion(String filePath) {
        try {
            this.ui = new Ui();
            this.ui.showWelcome();
            this.storage = new Storage(filePath);
            this.allTasks = new TaskList(storage.load());
            this.ui.showList(this.allTasks.getAllTasks());
        } catch (CommandFoundButInvalidException e ) {
            System.out.print("There is an error" + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        String input;

        while(carryOn) {
            System.out.println("> ");
            input = scanner.nextLine();

            try {
                Parser parser = new Parser(input, allTasks, this.storage, this.ui);
                carryOn = parser.carryOn();
            } catch (EmptyStringException |
                     CommandFoundButInvalidException |
                     CommandNotFoundException e) {
                ui.displayError(e.getMessage());
            }
        }

        scanner.close();
    }
    public static void main(String[] args) {
        new Hyperion("data/tasks.txt");
    }
}
