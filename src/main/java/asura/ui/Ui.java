package asura.ui;

import java.util.Scanner;

public class Ui {

    private Scanner scanner;
    private String introduction = """
                Hello! I'm Asura!
                What can I do for you?""";
    private String goodbye = """
                Bye. Hope to see you again soon!""";

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showIntroduction() {
        System.out.println(formatResponse(introduction));
    }

    public void showGoodbye() {
        System.out.println(formatResponse(goodbye));
    }

    public void showError(String error) {
        System.out.println(formatResponse(error));
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void printString(String s) {
        System.out.println(formatResponse(s));
    }

    private String formatResponse(String msg) {
        String startBorder = "---------------------------------------\n";
        String endBorder = "\n---------------------------------------";
        String formattedMsg = startBorder + msg + endBorder;
        return formattedMsg.indent(3);
    }
}
