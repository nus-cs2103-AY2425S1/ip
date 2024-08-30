import java.util.Scanner;

public class Ui {

    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    private void showIntroduction() {
        String introduction = """
                Hello! I'm Asura!
                What can I do for you?""";

        System.out.println(formatResponse(introduction));
    }

    private void showGoodbye() {
        String goodbye = """
                Bye. Hope to see you again soon!""";
        System.out.println(formatResponse(goodbye));
    }

    private void getUserInput() {}

    private String formatResponse(String msg) {
        String startBorder = "---------------------------------------\n";
        String endBorder = "\n---------------------------------------";
        String formattedMsg = startBorder + msg + endBorder;
        return formattedMsg.indent(3);
    }
}
