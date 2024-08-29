package snipe.command;

import snipe.exception.SnipeException;
import snipe.storage.Storage;
import snipe.core.TaskList;
import snipe.util.Ui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The {@code HelpCommand} class represents a command to display help instructions to the user.
 * It reads help content from a text file and displays it using the user interface.
 */
public class HelpCommand extends Command{

    /**
     * Executes the help command by reading the help instructions from a file
     * and displaying them to the user.
     *
     * @param tasks   The task list (not used in this command).
     * @param ui      The user interface used to display the help instructions.
     * @param storage The storage object (not used in this command).
     * @throws SnipeException If an application-specific error occurs during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SnipeException {
        String filePath = "src/main/txt/helpinstructions.txt"; // Instructions manual
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            ui.printWithLines(content); // Print the instructions
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
}
