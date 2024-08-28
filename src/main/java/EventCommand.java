import java.time.LocalDateTime;

public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    private Event eventTask;

    public EventCommand(String eventDescription, LocalDateTime from, LocalDateTime to) {
        eventTask = new Event(eventDescription, from, to);
    }
    @Override
    public CommandResult execute(TaskList taskList) {
        taskList.addTask(eventTask);

        int taskListSize = taskList.getTaskListSize();

        String returnMessage = String.format("Got it. I've added this task:%n %s %nNow you have %d tasks in the list.", eventTask, taskListSize);
        return new CommandResult(returnMessage);
    }
}
