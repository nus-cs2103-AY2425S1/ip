package jude;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ui {
    private BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));

    public void showError(Exception e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void startChat() {
        // Initiate the chat
        System.out.println("Hello I'm jude.Jude");
        System.out.println("What can I do for you, poyo?");
    }

    public void endChat() {
        System.out.println("Poyo. Hope to see you again soon!");
    }

    public String readCommand() throws JudeException {
        try {
            return bi.readLine();
        } catch (IOException ie) {
            throw new JudeException("IO operation was failed or interrupted. Please try again, poyo...");
        }
    }

    public void showLine() {
        System.out.println("----------------------------------------------------------------------------------");
    }
}
