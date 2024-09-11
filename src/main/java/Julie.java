import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import julie.command.Command;
import julie.exception.JulieException;
import julie.misc.Parser;
import julie.misc.Storage;
import julie.misc.UI;
import julie.task.Task;

/**
 * The Class that represents the Julie Chat Bot.
 */
public class Julie {
    private static final String NAME = "Julie";
    private static boolean isExit = false;
    private static final List<Task> taskList = new ArrayList<>();
    public static void main(String[] args) {
        Storage.start();
        Storage.load(taskList);
        UI.wrappedLinePrint("Hi!! My name is: " + NAME + "!\nHow may I help?");
        Scanner sc = new Scanner(System.in);
        while (!isExit) {
            try {
                String input = sc.nextLine();
                Command c = Parser.parse(input);
                c.run(taskList);
                isExit = c.isExit;
            } catch (JulieException e) {
                UI.wrappedLinePrint(e.getMessage());
            }
        }
    }
}
