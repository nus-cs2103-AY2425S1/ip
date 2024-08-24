package luna;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() throws LunaException {

        String input = scanner.nextLine();

        if (input.isEmpty()) {
            throw new LunaException("""
                    Please enter one of the following commands:
                    "todo", "deadline", "event"
                    "mark", "unmark", "delete"
                    "list", "bye"
                    """);
        }

        return input;
    }

    public void showLine() {
        System.out.println("______________________________________");
    }

    public void showError(String message) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(message);
    }

}
