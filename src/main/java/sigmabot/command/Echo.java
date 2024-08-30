package sigmabot.command;
import sigmabot.ui.UiComponent;

public class Echo extends Command {
    @Override
    public void execute(UiComponent ui) {
    }

    public void execute(UiComponent ui, String echoMessage) {
        ui.printDialogue(echoMessage);
    }
}
