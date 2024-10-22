package chatbot.interfaces;

import java.util.Scanner;

public interface MessageView<T> {
    T parseInput(String input) throws IllegalArgumentException;
    String[] splitInput(String input);
    void bind(Scanner scanner);
    String listen();
    void send(String response);
    void endMessage();
    void introduce();
    void exit();
}
