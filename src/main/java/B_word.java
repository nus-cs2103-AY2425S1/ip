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

        enum States {to_loop, to_exit}
        States currentState = States.to_loop;

        while (currentState != States.to_exit) {
            String s = sc.nextLine();
            String tmp = s.strip();
            if (tmp.equals("bye")) {
                currentState = States.to_exit;
                break;
            }
            // plan for future iterations:
            // pass to handler object
            System.out.print(hline);
            System.out.println(tmp);
            System.out.print(hline);
        }

        System.out.println(hline +
                " Bye. Hope to see you again soon!\n" +
                hline);
    }
}
