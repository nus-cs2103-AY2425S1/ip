public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Storage storage) {
        System.out.println("Bye! Hope to see you again soon!");
    }
}
