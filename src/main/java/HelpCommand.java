import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HelpCommand extends Command{
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
