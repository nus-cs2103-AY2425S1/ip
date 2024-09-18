package denim.ui;

import java.util.List;

import denim.commands.CommandUsages;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

/**
 * A class representing the main container for all the commands available to the program.
 */
public class HelpWindow extends VBox {

    @FXML
    private VBox helpWindow;

    private void addInstruction(String usage, String example) {
        HelpBox helpBox = new HelpBox(usage, example);
        helpWindow.getChildren().add(helpBox);
    }

    @FXML
    private void initialize() {
        List<String> commandUsages = CommandUsages.COMMAND_USAGES;
        List<String> commandExamples = CommandUsages.COMMAND_EXAMPLES;

        final String prependExample = "Example: ";

        assert (CommandUsages.TOTAL_USER_COMMANDS == commandUsages.size());
        assert (CommandUsages.TOTAL_USER_COMMANDS == commandExamples.size());

        for (int i = 0; i < commandUsages.size(); i++) {
            String usage = commandUsages.get(i);
            String example = commandExamples.get(i);
            addInstruction(usage, prependExample + example);
        }
    }
}
