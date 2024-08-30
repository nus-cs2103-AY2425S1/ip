package sigmabot.command;

import sigmabot.ui.UiComponent;

import java.util.Scanner;

/**
 * The Joke class represents a command that triggers a joke to be told.
 * It provides the functionality to execute the joke-telling process,
 * which, in this case, outputs a joke to the console.
 */
public class Joke extends Command {

    /**
     * Executes the joke command.
     * This method outputs a placeholder joke message to the console.
     * The actual implementation can be extended to provide a variety of jokes.
     */
    @Override
    public void execute(Scanner sc) {
        System.out.println("tell joke");
    }
}
