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
     * and returning them as a string to be displayed to the user.
     *
     * @param tasks   The {@link TaskList}, not used in this command.
     * @param ui      The {@link Ui} instance used to display the help instructions to the user.
     * @param storage The {@link Storage} instance, not used in this command.
     * @return The help instructions as a string, or an error message if the instructions are unavailable.
     * @throws SnipeException If an application-specific error occurs during execution.
     * @throws IOException    If an I/O error occurs while reading the help instructions file.
     */
    @Override
    public String getResponse(TaskList tasks, Ui ui, Storage storage) throws SnipeException, IOException {
        String filePath = "src/main/txt/helpinstructions.txt"; // Instructions manual
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            return content;
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
            return "Sorry, help list is not available at the current moment";
        }
    }

}
