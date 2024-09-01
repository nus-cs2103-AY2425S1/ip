import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {

    private static final String LINE = "_________________________";

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String getUserCommand() {
        this.showLine();
        return in.nextLine();
    }

    public void showIntroduction() {
        System.out.println("Hello! I'm Potong");
        System.out.println("What can I do for you?\n");
//        System.out.println(Ui.LINE);
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void sayGoodbye() {
        System.out.println("Bye. Hope to see you again soon!\n");
        this.showLine();
    }

}
