package sigmabot.command;

import sigmabot.ui.UiComponent;

public class Greeting extends Command {
    @Override
    public void execute(UiComponent ui) {
        System.out.println("Hi, what's up");
    }

    public static void greet(UiComponent ui) {
        System.out.println("Hi, what's u");
    }
}
