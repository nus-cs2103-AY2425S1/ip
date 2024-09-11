package parser;

import tasks.DeadlineTask;
import tasks.EventTask;
import tasks.ToDoTask;
import tasks.Task;

public class Parser {
    /**
     * Returns a DeadlineTask object that can be used to get the details of the Task.
     *
     * @param deadlineCommand A string taken in to be parsed.
     * @return A new Deadline task.
     * @throws StringIndexOutOfBoundsException When parsing goes wrong.
     */

    public DeadlineTask parseDeadlineTask(String deadlineCommand) throws StringIndexOutOfBoundsException {
        int byIndex = deadlineCommand.indexOf("/by");
        assert byIndex < 0 : "Index error while parsing deadline task!";
        String desc = deadlineCommand.substring(0, byIndex);
        String deadline = deadlineCommand.substring(byIndex + 4);
        return new DeadlineTask(desc, false, deadline);
    }
    /**
     * Returns a EventTask object that can be used to get the details of the Task.
     *
     * @param eventCommand A string taken in to be parsed.
     * @return A new event task.
     * @throws StringIndexOutOfBoundsException When parsing goes wrong.
     */
    public Task parseEventTask(String eventCommand) throws StringIndexOutOfBoundsException {
        int fromIndex = eventCommand.indexOf("/from");
        int toIndex = eventCommand.indexOf("/to");

        String details = eventCommand.substring(0, fromIndex);
        String start = eventCommand.substring(fromIndex + 6, toIndex - 1);
        String end = eventCommand.substring(toIndex + 4);
        return new EventTask(details, false, start, end);
    }
    /**
     * Returns a ToDoTask object that can be used to get the details of the Task.
     *
     * @param toDoCommand A string taken in to be parsed.
     * @return A new To Do task.
     * @throws StringIndexOutOfBoundsException When parsing goes wrong.
     */
    public Task parseToDoTask(String toDoCommand) {
        return new ToDoTask(toDoCommand, false);
    }

}
