import java.util.ArrayList;
import java.util.Scanner;

public class Blitz {
    public static void main(String[] args) {
        ArrayList<String> db = new ArrayList<>();
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

            if (inp.equals("list")) {
                System.out.print(divider);
                for (int i = 0; i < db.size(); i++) {
                    System.out.println(tab + (i + 1) + ". " + db.get(i));
                }
                System.out.print(divider);
                continue;
            }

            db.add(inp);
            System.out.println(divider + tab + "added: " + inp + "\n" + divider);
        }

        System.out.println(divider + end + divider);
    }
}
