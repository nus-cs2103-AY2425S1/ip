package calebyyy.commands;

import calebyyy.Calebyyy;
import calebyyy.TaskList;
import calebyyy.Ui;

/**
 * Represents a command to exit the program.
 */
public class ByeCommand extends Command {
    /**
     * Constructor for ByeCommand.
     *
     * @param calebyyy The main Calebyyy object.
     * @param ui The Ui object responsible for user interaction.
     * @param taskList The TaskList object responsible for storing tasks.
     */
    public ByeCommand(Calebyyy calebyyy, Ui ui, TaskList taskList) {
        super(calebyyy, ui, taskList);
    }

    /**
     * Exits the program.
     *
     * @param input The user input.
     */
    @Override
    public void execute(String input) {
        ui.byeMessage();
        new Thread(() -> {
            try {
                Thread.sleep(3000); // Sleep for 3 seconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                // Handle the exception if needed
            }
            System.exit(0);
        }).start();
    }
}
