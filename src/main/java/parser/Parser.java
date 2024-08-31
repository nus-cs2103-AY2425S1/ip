package parser;

import tasks.DeadlineTask;
import tasks.EventTask;
import tasks.ToDoTask;
import tasks.Task;

public class Parser {

    public DeadlineTask parseDeadlineTask(String deadlineCommand) throws StringIndexOutOfBoundsException {
        int byIndex = deadlineCommand.indexOf("/by");
        String desc = deadlineCommand.substring(0, byIndex);
        String deadline = deadlineCommand.substring(byIndex + 4);
        return new DeadlineTask(desc, false, deadline);
    }

    public Task parseEventTask(String eventCommand) throws StringIndexOutOfBoundsException {
        int fromIndex = eventCommand.indexOf("/from");
        int toIndex = eventCommand.indexOf("/to");

        String details = eventCommand.substring(0, fromIndex);
        String start = eventCommand.substring(fromIndex + 6, toIndex - 1);
        String end = eventCommand.substring(toIndex + 4);
        return new EventTask(details, false, start, end);
    }

    public Task parseToDoTask(String toDoCommand) {
        return new ToDoTask(toDoCommand, false);
    }

}
