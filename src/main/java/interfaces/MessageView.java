package main.java.interfaces;

import java.util.Scanner;

public interface MessageView<T> {
    T parseInput(String input) throws IllegalArgumentException;
    String[] splitInput(String input);
    void bind(Scanner scanner);
    String listen();
    void send(String output);
    void endMessage();
    void introduce();
    void exit();
}
