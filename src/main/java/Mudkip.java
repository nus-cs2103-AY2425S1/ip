import ui.Ui;
import java.util.Scanner;
import java.io.IOException;

public class Mudkip {
    private Echo echo;

    public Mudkip() {
        this.echo = new Echo();
    }

    /**
     * Generates a response for the user's chat message.
     * @param input Input command from the user.
     * @return a string by the Chat bot.
     */
    public String getResponse(String input) {
        echo.setWord(input);

        if (input.isEmpty()) {
            return "Input cannot be empty. Please try again.";
        }
        return echo.echoOut();
    }
}
