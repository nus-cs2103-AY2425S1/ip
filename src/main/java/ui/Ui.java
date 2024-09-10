package ui;

/**
 * Class that reads the raw user command.
 */
public class Ui {
    public String readCommand(String rawCommand) {
        // Trim away leading & trailing whitespaces
        // Replace multiple whitespaces with a single one
        return rawCommand.trim()
                .replaceAll(" +", " ");
    }
}
