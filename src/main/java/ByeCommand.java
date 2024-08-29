public class ByeCommand extends Command {
    public void execute(TaskList tasks,Ui ui, Storage storage) {
        System.out.println("    " +"Bye!!! Thanks for chatting!");
        super.quitBot();
    }


}
