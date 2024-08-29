package denim.commands;

import denim.TaskList;
import denim.exceptions.DenimException;
import denim.storage.TaskIo;
import denim.tasks.Event;

import java.time.LocalDateTime;

public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String COMMAND_USAGE = "event <eventDescription> /from <startDateTime /to <endDateTime>\n"
            + "Where startDateTime and endDateTime is in dd/MM/yyyy HHmm";
    private Event eventTask;

    public EventCommand(String eventDescription, LocalDateTime from, LocalDateTime to) {
        eventTask = new Event(eventDescription, from, to);
    }
    @Override
    public CommandResult execute(TaskList taskList, TaskIo taskIo) {
        try {
            taskIo.writeTaskData(eventTask);
        } catch (DenimException e) {
            return new CommandResult("Command Failed. Error:\n" + e.getMessage());
        }

        taskList.addTask(eventTask);
        int taskListSize = taskList.getTaskListSize();

        String returnMessage = String.format("Got it. I've added this task:%n %s %n"
                + "Now you have %d tasks in the list.", eventTask, taskListSize);
        return new CommandResult(returnMessage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
