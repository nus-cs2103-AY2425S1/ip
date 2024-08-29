package components.commands;

import components.DateTimeData;
import components.tasks.Event;
import components.tasks.Task;
import components.tasks.TaskList;
import exceptions.DongjiEmptyTaskNameException;

public class EventCommand implements Command {
    
    private TaskList taskList;
    private String taskName;
    private DateTimeData startDate;
    private DateTimeData endDate;

    public EventCommand(TaskList taskList, String taskName, DateTimeData startDate, DateTimeData endDate) {
        this.taskList = taskList;
        this.taskName = taskName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String execute() {
        Task addedEvent;
        try {
            addedEvent = this.taskList.add(new Event(this.taskName, this.startDate, this.endDate));
        }
        catch (DongjiEmptyTaskNameException e) {
            return e.getMessage();
        }

        return "added: " + addedEvent.getName();
    }
}
