package donk;

import donk.task.TaskList;
import java.util.Scanner;


public class Donk {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Donk(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DonkException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    public void run() {
        ui.greet();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            try {
                String userInput = scanner.nextLine();
                Parser.parse(userInput, this.tasks, this.storage,this.ui);

            } catch (TodoException e) {
                System.out.println("    " + e.getMessage());
            } catch (Exception e) {
                System.out.println("    " + e.getMessage());

            }
        }
    }

    public static void main(String[] args) {
        new Donk("./save.txt").run();
    }






}

