package Dawn;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void greet() throws DawnException {
        String divider = "--".repeat(30);

        System.out.println(divider);
        System.out.println("Dawn 🌙 speaking, what can I do for you?");
        interact();
    }

    public void interact() throws DawnException {
        while (scanner.hasNextLine()) {
            String command = scanner.next();
            new Parser(command, scanner.nextLine().trim());
            if (command.equals("bye")) {
                break;
            }
        }
    }
    public void showLoadingError(DawnException ex) {
        System.out.println(ex);
    }

}
