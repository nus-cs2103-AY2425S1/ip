public class AddTaskCommand extends Command {
    private final String[] ADD_TASK_PREFIXES = new String[] {
        "\tYou got it! Adding task:\n\t",
        "\tYou got it! Adding task:\n\t",
        "\tYou got it! Adding task:\n\t",
        "\tYou sure wanna do that?\n\t",
        "\tAlright! Adding task:\n\t",
        "\tAlright! Adding task:\n\t",
        "\tAlright! Adding task:\n\t",
        "\tOn it! Task added:\n\t",
        "\tOn it! Task added:\n\t",
        "\tWhatever you say! *nervous laughter*:\n\t",
        "\tHmmmm..... Done. Task added:\n\t",
        "\tWorking..... Done. Task added:\n\t",
    };

    public AddTaskCommand(Task t) {
        super(0, t);
    }

    public String execute(TaskList tasks) {
        tasks.addTask(this.getTask());
        return generateRandomPrefix(ADD_TASK_PREFIXES) + this.getTask().toString();
    }
}
