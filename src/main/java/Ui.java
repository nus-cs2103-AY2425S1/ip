import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ui {
    private BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));

    public void showLoadingError() {
        System.out.println("A loading error has occurred. Starting with a new list.");
    }

    public void showError(Exception e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
    }

    public void startChat() {
        // Initiate the chat
        System.out.println("Hello I'm Jude");
        System.out.println("What can I do for you, poyo?");
    }

    public String readCommand() throws JudeException {
        try {
            return bi.readLine();
        } catch (IOException ie) {
            throw new JudeException("IO operation was failed or interrupted. Please try again, poyo...");
        }
    }
}
