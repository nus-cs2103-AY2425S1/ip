public class AddCommand extends Command {

    @Override
    public String execute(String input, TaskList tasks) {
        Task t = tasks.addTask(input);

        return String.format("Added yet another task\n   %s\nYou now have %d tasks. Are you gonna do any of them?", t, tasks.noOfTasks());

    }
}
