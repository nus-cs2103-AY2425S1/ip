package sigmabot.command;
import sigmabot.ui.UiComponent;

import java.util.Scanner;

public class Echo extends Command {
    @Override
    public void execute(Scanner sc) {
        String echoMessage = sc.nextLine().trim();
        System.out.println(echoMessage);
    }
}
