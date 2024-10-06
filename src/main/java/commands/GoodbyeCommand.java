package commands;

public class GoodbyeCommand extends Command{
    public GoodbyeCommand() {
        super("Already leaving? Fine... I'll shut up :(");
        this.setAsGoodbye();
    }
}
