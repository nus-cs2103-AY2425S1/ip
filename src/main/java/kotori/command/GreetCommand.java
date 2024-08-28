package kotori.command;

import kotori.Ui.Ui;

public class GreetCommand extends Command{
    @Override
    public void execute() {
        Ui.printGreeting();
    }
}
