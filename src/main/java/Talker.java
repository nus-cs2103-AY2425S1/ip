import java.util.Scanner;

public class Talker {
    private static final String NAME = "Talker";
    private static final String DIRECTORY_PATH = "./data";
    private static final String FILE_PATH ="./data/talker.txt";

    private static Ui ui = new Ui(NAME);
    private static Storage storage = new Storage(DIRECTORY_PATH, FILE_PATH);
    private static TaskList list;


    public static void main(String[] args) {

        list = new TaskList();

        try {
            list.setTasks(storage.readFile());
        } catch (TalkerException e) {
            ui.printError(e);
        }

        ui.printWelcome();
        // read user input
        String input = ui.readCommand();
        ui.printLine();

        // if command "bye" entered, exit
        while (!input.equals("bye")) {
            try {
                Parser.parseInput(input, list, ui);
                storage.writeFile(list);
            } catch (TalkerException e) {
                ui.printError(e);
            } finally {
                ui.printLine();
                input = ui.readCommand();
                ui.printLine();
            }
        }
        ui.printGoodBye();
    }
}

