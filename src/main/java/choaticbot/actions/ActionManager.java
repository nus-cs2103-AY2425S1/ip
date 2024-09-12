package choaticbot.actions;

import choaticbot.exceptions.UnknownActionException;
import choaticbot.inputs.ProcessedInput;
import choaticbot.tasks.TaskList;

public class ActionManager {
    public ActionManager() {};

    public Action createAction (ProcessedInput processedInput, TaskList taskList) throws UnknownActionException{
        assert processedInput != null : "ProcessedInput should not be null";
        assert taskList != null : "TaskList should not be null";

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
