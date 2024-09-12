package choaticbot;

import java.util.Scanner;

import choaticbot.actions.ActionManager;
import choaticbot.actions.Action;
import choaticbot.exceptions.ChoaticBotException;
import choaticbot.inputs.InputProcessor;
import choaticbot.inputs.ProcessedInput;
import choaticbot.tasks.TaskList;
import choaticbot.ui.Ui;
import choaticbot.storage.Storage;

public class ChoaticBot {
    public TaskList tasklist;

    public ChoaticBot() {
        this.tasklist = new TaskList();
    }

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