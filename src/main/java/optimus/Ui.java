package optimus;

import java.util.Scanner;

public class Ui {

    Scanner scanner;
    private static final String linebreak = "____________________________";

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void greet() {
        System.out.println("Hello! I'm optimus.Optimus\nWhat can I do for you?");
        showLineBreak();
    }

    public void showLineBreak() {
        System.out.println(linebreak);
    }

    public void printToInterface(String out) {
        System.out.println(out);
    }


}
