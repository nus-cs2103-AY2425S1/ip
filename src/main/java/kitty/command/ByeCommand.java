package kitty.command;

import kitty.Ui;

public class ByeCommand extends Command{
    public ByeCommand(Ui ui) {
        super(ui, null);
    }

    @Override
    public void run() {
        ui.showGoodbyeMessage();
    }
}
