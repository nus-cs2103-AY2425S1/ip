import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Ui {
    private static final String LINE_BREAK = "---------------------------------";
    private BufferedReader br;

    public Ui() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public void showWelcome() {
        System.out.println(LINE_BREAK);
        System.out.println("Hello! I'm Ah Bang Mang.\nWhat you want sia?");
        System.out.println(LINE_BREAK);
    }

    public void showExit() {
        System.out.println(LINE_BREAK);
        System.out.println("Ok, I zao first then!");
        System.out.println(LINE_BREAK);
    }

    public void showLine() {
        System.out.println(LINE_BREAK);
    }

    public void showError(String message) {
        System.out.println(LINE_BREAK);
        System.out.println(message);
        System.out.println(LINE_BREAK);
    }

    public String readCommand() throws IOException {
        return br.readLine();
    }

}
