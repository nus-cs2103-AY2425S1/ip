package rainy.commands;

public class ByeCommand extends Command {
    public ByeCommand() {

    }

    public void getResponse() {
        this.ui.goodbyeMessage();
    }
}