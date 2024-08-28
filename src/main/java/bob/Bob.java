package bob;

import java.util.Scanner;

public class Bob {

    private static Ui ui = new Ui();
    private static Storage storage;
    private static TaskList taskList;
    private static final String NAME = "bob.Bob";

    public static void main(String[] args) {
        storage = new Storage("./data/bob.txt");
        taskList = new TaskList();
        ui.showWelcomeMessage(NAME);

        try {
            taskList.setTasks(storage.load());
        } catch (ChatBotException e) {
            ui.showError(e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        String phrase = scanner.nextLine();

        while (!phrase.equals("bye")) {
            try {
                ui.showLine();
                Parser.handleInput(phrase, taskList, ui);
                storage.save(taskList.getTasks());
            } catch (ChatBotException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
                phrase = scanner.nextLine();
            }
        }

        ui.showGoodbyeMessage();
    }
}
