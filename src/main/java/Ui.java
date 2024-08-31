import exceptions.AstorException;

import java.util.Scanner;

public class Ui {
    private final String SEPARATOR_LINE = "--------------------------------------";
    private final String INTRODUCTION_LINE = "Hello, I'm Astor!\n" + "What can I do for you?\n"
            + SEPARATOR_LINE;
    private Parser parser;
    private Scanner scanner;

    public Ui() {
        parser = new Parser();
    }

    public void start() {
        System.out.println(INTRODUCTION_LINE);
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            }
            String output;
            try {
                output = parser.process(input);
            } catch (AstorException e) {
                output = e.getMessage();
            }
            System.out.println(output + "\n" + SEPARATOR_LINE);
        }
        scanner.close();
    }

    /*
    public void showWelcome() {
        System.out.println(INTRODUCTION_LINE);
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
    }

    public void showLine() {
        System.out.println(SEPARATOR_LINE);
    }

     */
}
