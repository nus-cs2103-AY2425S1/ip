package command;

public class Echo extends Command {
    @Override
    public void execute(UiComponent ui) {
    }

    public void execute(UiComponent ui, String echoMessage) {
        ui.printDialogue(echoMessage);
    }
}
