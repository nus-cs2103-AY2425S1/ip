package Kotori.Command;

import Kotori.Ui.Ui;

public class GreetCommand extends Command{
    @Override
    public void execute() {
        Ui.printGreeting();
    }
}
