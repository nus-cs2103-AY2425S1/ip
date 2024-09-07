package ahmad.fx;


import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controller for chats
 */
public class UserChatController {
    @FXML
    private Label chatLabel;

    public void setText(String message) {
        System.out.println(message);
        chatLabel.setText(message);
    }
}
