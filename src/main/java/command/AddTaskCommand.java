package command;

import task.Task;
import task.TaskList;

public class AddTaskCommand extends Command {
    private static final String[] ADD_TASK_PREFIXES = new String[] {
        "You got it! Adding task:\n",
        "You got it! Adding task:\n",
        "You got it! Adding task:\n",
        "You sure wanna do that?\n",
        "Alright! Adding task:\n",
        "Alright! Adding task:\n",
        "Alright! Adding task:\n",
        "On it! Task added:\n",
        "On it! Task added:\n",
        "Whatever you say! *nervous laughter*:\n",
        "Hmmmm..... Done. Task added:\n",
        "Working..... Done. Task added:\n",
    };

    public AddTaskCommand(Task t) {
        super(0, t);
    }

    @Override
    public String execute(TaskList tasks) {
        tasks.addTask(this.getTask());
        return generateRandomPrefix(ADD_TASK_PREFIXES) + this.getTask().toString();
    }
}
