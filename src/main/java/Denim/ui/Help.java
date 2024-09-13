package denim.ui;

import java.util.List;

import denim.commands.CommandUsages;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * A class representing the main container for all the commands available to the program.
 */
public class Help extends VBox {

    /**
     * Constructs a new Help Window with all the instructions available to the program.
     */
    public Help() {
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setSpacing(10);

        addDetailedInstructions(this);
    }

    /**
     * Takes the available commands from CommandUsages and generate the commands available as well as examples
     * on how to use the commands.
     */
    private static void addDetailedInstructions(VBox helpWindow) {
        List<String> commandUsages = CommandUsages.COMMAND_USAGES;
        List<String> commandExamples = CommandUsages.COMMAND_EXAMPLES;

        assert (CommandUsages.TOTAL_USER_COMMANDS == commandUsages.size());
        assert (CommandUsages.TOTAL_USER_COMMANDS == commandExamples.size());

        for (int i = 0; i < commandUsages.size(); i++) {
            String commandUsage = commandUsages.get(i);
            String commandExample = commandExamples.get(i);

            Label label = new Label(commandUsage + "\nExample: " + commandExample);
            helpWindow.getChildren().add(label);
        }
    }


}
