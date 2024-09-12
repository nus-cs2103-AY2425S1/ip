package choaticbot.actions;

import choaticbot.exceptions.UnknownActionException;
import choaticbot.inputs.ProcessedInput;
import choaticbot.tasks.TaskList;

/**
 * The {@code ActionManager} class is responsible for creating and managing
 * actions based on user input. It maps processed input to the appropriate
 * action that will be executed by the bot.
 */
public class ActionManager {

    /**
     * Default constructor for {@code ActionManager}.
     */
    public ActionManager() {};

    /**
     * Creates an action based on the processed input and task list. Depending
     * on the action string from the processed input, it returns the corresponding
     * action.
     *
     * @param processedInput The processed input containing the action and details.
     * @param taskList The task list that the action will operate on.
     * @return The action to be executed based on the user input.
     * @throws UnknownActionException If the action string in the processed input does not match a known action.
     */
    public Action createAction (ProcessedInput processedInput, TaskList taskList) throws UnknownActionException{
        return switch (processedInput.getAction()) {
            case "bye" -> new Bye(taskList);
            case "list" -> new List(taskList);
            case "mark" -> new Mark(taskList, processedInput.getDetails());
            case "unmark" -> new Unmark(taskList, processedInput.getDetails());
            case "delete" -> new Delete(taskList, processedInput.getDetails());
            case "find" -> new Find(taskList, processedInput.getDetails());
            //Allow fall through on purpose to cover all cases
            case "todo", "deadline", "event" ->
                    new CreateTask(taskList, processedInput.getAction(), processedInput.getDetails());
            default -> throw new UnknownActionException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        };
    }
}
