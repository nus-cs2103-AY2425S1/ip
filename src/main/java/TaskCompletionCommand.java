public class TaskCompletionCommand extends Command {
    private boolean isCompleted;
    private final String[] TASK_COMPLETED_PREFIXES = new String[] {
        "\tNice! Task marked as completed\n",
        "\tNice! Task marked as completed\n",
        "\tNice! Task marked as completed\n",
        "\tAlright! Task marked as completed\n",
        "\tPhew! Got that one out of the way!\n",
        "\tPhew! Got that one out of the way!\n",
        "\tWahoo! Task complete!\n",
        "\tWahoo! Task complete!\n",
        "\tDone and done! Should we do it again?\n",
        "\tDone and done! Should we do it again?\n"
    };

    private final String[] TASK_UNCOMPLETED_PREFIXES = new String[] {
        "\tMarking as incomplete.\n",
        "\tBooooo\n",
        "\tBooooo\n",
        "\tBooooo\n",
        "\tAw man. Task marked as incomplete\n",
        "\tAw man. Task marked as incomplete\n",
        "\tOh well. Marking task as incomplete\n",
        "\tOh well. Marking task as incomplete\n",
        "\tDamn. Thought we had that.\n",
        "\tAw man. Thought we had that.\n",
        "\tDamn. Thought we had that.\n"
    };

    public TaskCompletionCommand(boolean isCompleted, int i) {
        super(i, null);
        this.isCompleted = isCompleted;
    }

    public String execute(TaskList tasks) {
        try {
            if (this.isCompleted) {
            tasks.setTaskAsCompleted(this.getI());
                return generateRandomPrefix(TASK_COMPLETED_PREFIXES) + tasks.taskListToString();
            } else {
                tasks.setTaskAsNotCompleted(this.getI());
                return generateRandomPrefix(TASK_UNCOMPLETED_PREFIXES) + tasks.taskListToString();
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            return "\tOops! You don't have those many tasks!";
        }
        
    }
}
