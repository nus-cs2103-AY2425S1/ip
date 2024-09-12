package choaticbot.actions;

import choaticbot.tasks.Task;
import choaticbot.tasks.TaskList;
import choaticbot.tasks.Deadlines;
import choaticbot.tasks.Events;
import choaticbot.tasks.ToDos;

public class CreateTask extends Action{
    public String taskType;
    public String details;

    public CreateTask(TaskList taskList, String taskType, String details) {
        super(taskList);
        this.taskType = taskType;
        this.details = details;
    }

    @Override
    public void execute() {
        Task task = switch (this.taskType) {
            case "todo" -> new ToDos(this.details);
            case "deadline" -> {
                //[0] = taskName, [1] = deadline
                String[] deadlineDetails = this.details.split("/by ");
                assert deadlineDetails.length == 2 : "Deadline details should have 2 parts: taskName and deadline";
                yield new Deadlines(deadlineDetails[0], deadlineDetails[1]);
            }
            case "event" -> {
                //[0] = taskName, [1] = from, [2] = to
                String[] eventDetails = this.details.split("/");
                assert eventDetails.length == 3 : "Event details should have 3 parts: taskName, from, and to";
                yield new Events(eventDetails[0], eventDetails[1], eventDetails[2]);
            }
            default -> null;
        };

        if (task != null) {
            this.taskList.addTask(task);
        }
    }
}
