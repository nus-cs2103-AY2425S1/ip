package command;
public class ExitCommand extends Command {
    @Override
    public void execute() {
        System.out.println("Bye. Hope to see you again soon!");
    }   
}
