public class ExitCommand extends Command{
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        System.out.println("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
