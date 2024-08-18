import java.util.Scanner;

public class Blitz {
    public static void main(String[] args) {
        String tab = "    ";
        String divider = tab + "-----------------------------------------------\n";

        String greet = tab + "Hello! I'm Blitz.\n" +
                tab + "What can I do for you?\n";
        String end = tab + "Bye. Hope to see you again soon!\n";

        System.out.println(divider + greet + divider);

        Scanner sc = new Scanner(System.in);
        while (true) {
            String inp = sc.nextLine();

            if (inp.equals("bye")) {
                break;
            }
            System.out.println(divider + tab + inp + "\n" + divider);
        }

        System.out.println(divider + end + divider);
    }
}
