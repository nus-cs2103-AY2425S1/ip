package actions;

import exceptions.UnknownActionException;
import inputs.ProcessedInput;
import tasks.TaskList;

public class ActionManager {
    public ActionManager() {};

    public Action createAction (ProcessedInput processedInput, TaskList taskList) throws UnknownActionException{
        return switch (processedInput.getAction()) {
            case "bye" -> new Bye(taskList);
            case "list" -> new List(taskList);
            case "mark" -> new Mark(taskList, processedInput.getDetails());
            case "unmark" -> new Unmark(taskList, processedInput.getDetails());
            case "delete" -> new Delete(taskList, processedInput.getDetails());
            //Allow fall through on purpose to cover all cases
            case "todo", "deadline", "event" ->
                    new CreateTask(taskList, processedInput.getAction(), processedInput.getDetails());
            default -> throw new UnknownActionException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        };
    }
}
