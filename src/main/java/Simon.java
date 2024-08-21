
import java.util.Scanner;
import java.util.ArrayList;
public class Simon {
    final String HOR_LINE = "\t____________________________________________________________\n";
    final String WLC_MSG = "Hello! I'm Simon \n" +
            " \tWhat can I do for you?";
    final String EXT_MSG = " Bye. Hope to see you again soon!";
    private String printMessage(String msg) {
        return HOR_LINE + "\t" + msg + "\n" + HOR_LINE;
    }
    public void run() {
        System.out.print(printMessage(WLC_MSG));
        Scanner sc = new Scanner(System.in);
        String input;
        while (!(input = sc.nextLine().trim()).equals("bye")) {
            String output = printMessage(input);
            System.out.println(output);

        }
        System.out.println(printMessage(EXT_MSG));
    }
    public static void main(String[] args) {
        new Simon().run();
    }
}
