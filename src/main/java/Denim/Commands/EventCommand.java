package Denim.Commands;

import Denim.Tasks.Event;
import Denim.Exceptions.DenimException;
import Denim.TaskList;
import Denim.Storage.TaskIO;
import Denim.Tasks.Task;

import java.time.LocalDateTime;

public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String USAGE = "event <eventDescription> /from <startDateTime /to <endDateTime>\nWhere startDateTime and endDateTime is in dd/MM/yyyy HHmm";
    private Event eventTask;

    public EventCommand(String eventDescription, LocalDateTime from, LocalDateTime to) {
        eventTask = new Event(eventDescription, from, to);
    }
    @Override
    public CommandResult execute(TaskList taskList, TaskIO taskIO) {
        try {
            taskIO.writeTaskData(eventTask);
        } catch (DenimException e) {
            return new CommandResult("Command Failed. Error:\n" + e.getMessage());
        }

        taskList.addTask(eventTask);
        int taskListSize = taskList.getTaskListSize();

        String returnMessage = String.format("Got it. I've added this task:%n %s %nNow you have %d tasks in the list.", eventTask, taskListSize);
        return new CommandResult(returnMessage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
