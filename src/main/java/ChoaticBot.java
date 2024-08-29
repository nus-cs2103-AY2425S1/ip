import java.util.Objects;
import java.util.Scanner;

import actions.ActionManager;
import actions.Action;
import inputs.InputProcessor;
import inputs.ProcessedInput;
import ui.Ui;
import tasks.Deadlines;
import tasks.Events;
import tasks.TaskList;
import tasks.ToDos;

import static ui.Ui.printText;

public class ChoaticBot {
    public TaskList tasklist;

    public ChoaticBot() {
        this.tasklist = new TaskList();
    }

    public static void welcome() {
        String welcomeMsg = "Hello! I'm ChoaticBot\n" + "What can I do for you?\n";
        printText(welcomeMsg);
    }

    public static void bye() {
        String byeMsg = "Bye. Hope to see you again soon!\n";
        printText(byeMsg);
    }

    public static void main(String[] args) {
        ChoaticBot chatBot = new ChoaticBot();
        InputProcessor processor = new InputProcessor();
        Scanner scanner = new Scanner(System.in);
        ActionManager actionManager = new ActionManager();
        boolean isEnd = false;

        ChoaticBot.welcome();
        while (!isEnd) {
            String userInput = scanner.nextLine();
            ProcessedInput processedInput = processor.processInput(userInput);
            Action action = actionManager.createAction(processedInput, chatBot.tasklist);
            action.execute();
            isEnd = action.isEnd();
        }
        ChoaticBot.bye();
    }
}