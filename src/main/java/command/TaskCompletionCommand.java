package command;

import task.TaskList;

public class TaskCompletionCommand extends Command {
    private boolean isCompleted;
    private static final String[] TASK_COMPLETED_PREFIXES = new String[] {
        "Nice! Task marked as completed\n",
        "Nice! Task marked as completed\n",
        "Nice! Task marked as completed\n",
        "Alright! Task marked as completed\n",
        "Phew! Got that one out of the way!\n",
        "Phew! Got that one out of the way!\n",
        "Wahoo! Task complete!\n",
        "Wahoo! Task complete!\n",
        "Done and done! Should we do it again?\n",
        "Done and done! Should we do it again?\n"
    };

    private static final String[] TASK_UNCOMPLETED_PREFIXES = new String[] {
        "Marking as incomplete.\n",
        "Booooo\n",
        "Booooo\n",
        "Booooo\n",
        "Aw man. Task marked as incomplete\n",
        "Aw man. Task marked as incomplete\n",
        "Oh well. Marking task as incomplete\n",
        "Oh well. Marking task as incomplete\n",
        "Damn. Thought we had that.\n",
        "Aw man. Thought we had that.\n",
        "Damn. Thought we had that.\n"
    };

    public TaskCompletionCommand(boolean isCompleted, int i) {
        super(i, null);
        this.isCompleted = isCompleted;
    }

    @Override
    public String execute(TaskList tasks) {
        try {
            if (this.isCompleted) {
            tasks.setTaskAsCompleted(this.getTaskIndex());
                return generateRandomPrefix(TASK_COMPLETED_PREFIXES) + tasks.taskListToString();
            } else {
                tasks.setTaskAsNotCompleted(this.getTaskIndex());
                return generateRandomPrefix(TASK_UNCOMPLETED_PREFIXES) + tasks.taskListToString();
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            return "Oops! You don't have those many tasks!";
        }
        
    }
}
