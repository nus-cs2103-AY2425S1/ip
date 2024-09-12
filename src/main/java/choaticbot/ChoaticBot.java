package choaticbot;

import java.util.Scanner;

import choaticbot.actions.Action;
import choaticbot.actions.ActionManager;
import choaticbot.exceptions.ChoaticBotException;
import choaticbot.inputs.InputProcessor;
import choaticbot.inputs.ProcessedInput;
import choaticbot.storage.Storage;
import choaticbot.tasks.TaskList;
import choaticbot.ui.Ui;

/**
 * The main class for the ChoaticBot application. This class handles the initialization
 * of the bot, processes user inputs, manages actions, and interacts with the task list.
 */
public class ChoaticBot {
    private TaskList tasklist;

    /**
     * Constructs a new ChoaticBot instance with an empty task list.
     */
    public ChoaticBot() {
        this.tasklist = new TaskList();
    }

    /**
     * The main method that runs the ChoaticBot application.
     * It initializes the bot, processes user inputs, manages actions, and handles
     * interactions with the task list and storage. The bot continues running until
     * the user decides to end the session.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        ChoaticBot chatBot = new ChoaticBot();
        InputProcessor processor = new InputProcessor();
        Scanner scanner = new Scanner(System.in);
        ActionManager actionManager = new ActionManager();
        boolean isEnd = false;

        Storage.loadTasksFromFile(chatBot.tasklist);
        Ui.printWelcomeMsg();

        while (!isEnd) {
            try {
                String userInput = scanner.nextLine();
                ProcessedInput processedInput = processor.processInput(userInput);
                Action action = actionManager.createAction(processedInput, chatBot.tasklist);
                action.execute();
                Storage.saveTasks(chatBot.tasklist);
                isEnd = action.isEnd();
            } catch (ChoaticBotException e) {
                System.out.println(e.getMessage());
            }
        }

        Ui.printByeMsg();
    }
}
