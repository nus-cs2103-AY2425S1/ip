package edith;

import java.util.Objects;
import java.util.Scanner;

/**
 * This class is the entry point for chatbot Edith.
 */
public class Edith {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final ToDoList TO_DO_LIST = new ToDoList();

    public String getResponse(String input) {
        return Ui.handleUserInput(input, TO_DO_LIST);
    }

    public void loadTasks() {
        Storage.loadTasks(TO_DO_LIST);
    }
}
