package sigmabot.command;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PrintInternalCommand extends ChatCommand{
	@Override
	public void execute(TextArea displayArea, TextField inputField) {
		displayArea.appendText("printing statement");
	}
}
