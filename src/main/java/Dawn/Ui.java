package Dawn;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    /**
     * Creates a new instance of Ui which interacts with the user
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints a greeting and begins interaction with the user
     *
     * @throws DawnException
     */
    public void greet() throws DawnException {
        String divider = "--".repeat(30);
        String logo = "૮꒰ ˶• ༝ •˶꒱ა ♡";

        System.out.println(divider);
        System.out.println("Bunny🐰 speaking, what can I do for you?");
        System.out.println(logo);

        interact();
    }

    /**
     * Handles user input
     *
     * @throws DawnException
     */
    public void interact() throws DawnException {
        while (scanner.hasNextLine()) {
            String command = scanner.next();
            new Parser(command, scanner.nextLine().trim());
            if (command.equals("bye")) {
                break;
            }
        }
    }

    /**
     * Prints the error message if the chatbot is unable to load previously saved tasks
     *
     * @param ex
     */
    public void showLoadingError(DawnException ex) {
        System.out.println(ex);
    }

}
