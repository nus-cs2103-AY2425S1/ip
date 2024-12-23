package colress.testutil;

import colress.Colress;
import colress.Ui;
import colress.tasklist.TaskList;

/**
 * A stub class for the Ui class for testing purposes.
 */
public final class UiStub extends Ui {
    public UiStub(Colress colress) {
        super(colress);
    }

    @Override
    public String processInput(String input, TaskList taskList) {
        return "This method should not be called.";
    }
}
