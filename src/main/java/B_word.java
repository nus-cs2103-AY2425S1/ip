import java.util.Scanner;

public class B_word {
    public static final String hline = "____________________________________________________________\n";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print(hline +
                " Hello! I'm 'B word'\n" +
                " What can I do for you?\n" +
                hline);

        // plan:
        // while loop, String s = scanner nextLine, strip
        // echo

        System.out.println(hline +
                " Bye. Hope to see you again soon!\n" +
                hline);
    }
}
