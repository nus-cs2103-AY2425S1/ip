package sigmabot.command;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public abstract class Command {
    public abstract void execute(TextArea displayArea, TextField inputField);
}
