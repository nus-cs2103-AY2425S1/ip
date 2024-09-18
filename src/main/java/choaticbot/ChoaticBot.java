package choaticbot;

import choaticbot.actions.Action;
import choaticbot.actions.ActionManager;
import choaticbot.actions.ActionResult;
import choaticbot.exceptions.ChoaticBotException;
import choaticbot.inputs.InputProcessor;
import choaticbot.inputs.ProcessedInput;
import choaticbot.storage.Storage;
import choaticbot.tasks.TaskList;

/**
 * The main class for the ChoaticBot application. This class handles the initialization
 * of the bot, processes user inputs, manages actions, and interacts with the task list.
 */
public class ChoaticBot {
    private static TaskList tasklist;
    private InputProcessor processor = new InputProcessor();
    private ActionManager actionManager = new ActionManager();

    /**
     * Constructs a new ChoaticBot instance with an empty task list.
     */
    public ChoaticBot() {
        tasklist = new TaskList();
    }

    public TaskList getTasklist() {
        return tasklist;
    }

    /**
     * Generates a response for the user's chat message.
     *
     * @param input User input.
     * @return A string to represent the output of the bot.
     *
     */
    public String getResponse(String input) {
        String response;
        try {
            ProcessedInput processedInput = processor.processInput(input);
            Action action = actionManager.createAction(processedInput, ChoaticBot.tasklist);
            ActionResult actionResult = action.execute();
            response = actionResult.getMessage();
            Storage.saveTasks(ChoaticBot.tasklist);
        } catch (ChoaticBotException e) {
            response = e.getMessage();

        }

        return "ChoaticBot says:\n" + response;
    }
}
