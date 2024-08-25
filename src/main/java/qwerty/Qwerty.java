package qwerty;

import java.util.Scanner;
import qwerty.command.Command;

/**
 * This class encapsulates a task helper chatbot.
 */
public class Qwerty {

    /** True if the bot is currently chatting and accepting input */
    private boolean isChatting;
    private Ui ui;
    /** List of tasks entered by the user */
    private TaskList tasks;
    /** TODO doc this */
    private Storage storage;

    public Qwerty() {
        this.isChatting = true;
        this.ui = new Ui();
        this.tasks = new TaskList();
        this.storage = new Storage("savefile.txt");
    }

    /**
     * Starts the chatbot and run the main chat loop.
     */
    public void start() {
        Scanner scanner = new Scanner(System.in);
        isChatting = true;
        storage.loadTasks(tasks);
        ui.showGreeting();

        while (isChatting) {
            System.out.println(); // blank line before user input
            String rawInput = scanner.nextLine();
            try {
                Command command = Parser.parse(rawInput);
                command.execute(tasks, ui, storage);
                storage.saveTasks(tasks);
                isChatting = !command.isExitCommand();
            } catch (QwertyException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Qwerty chatBot = new Qwerty();
        chatBot.start();
    }
}
