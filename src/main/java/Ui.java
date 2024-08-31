import java.util.Scanner;

public class Ui {

    private Scanner scanner;
    private String introduction = """
                Hello! I'm Asura!
                What can I do for you?""";
    private String goodbye = """
                Bye. Hope to see you again soon!""";
    private String loadingError = """
                Unable to load task data. Resumed with empty task list.""";
    private String saveError = """
                Unable to save task data. """;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showIntroduction() {
        System.out.println(formatResponse(introduction));
    }

    public void showGoodbye() {
        System.out.println(formatResponse(goodbye));
    }

    public void showLoadingError() {
        System.out.println(formatResponse(loadingError));
    }

    public void showSaveError(String error) {
        System.out.println(formatResponse(saveError + error));
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    private String formatResponse(String msg) {
        String startBorder = "---------------------------------------\n";
        String endBorder = "\n---------------------------------------";
        String formattedMsg = startBorder + msg + endBorder;
        return formattedMsg.indent(3);
    }
}
