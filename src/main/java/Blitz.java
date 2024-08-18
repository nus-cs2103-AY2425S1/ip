import java.util.ArrayList;
import java.util.Scanner;

public class Blitz {
    private static void printInDivider(String divider, String cont) {
        System.out.print(divider + cont + divider);
    }

    public static void main(String[] args) {
        ArrayList<Task> db = new ArrayList<>();
        String tab = "    ";
        String divider = tab + "-----------------------------------------------\n";
        String greet = tab + "Hello! I'm Blitz.\n" +
                tab + "What can I do for you?\n";
        String end = tab + "Bye. Hope to see you again soon!\n";

        printInDivider(divider, greet);

        Scanner sc = new Scanner(System.in);
        while (true) {
            String inp = sc.nextLine();

            if (inp.equals("bye")) {
                break;
            }

            if (inp.equals("list")) {
                System.out.println(divider +
                        tab + "Here are the tasks in your list:");
                for (int i = 0; i < db.size(); i++) {
                    Task curr = db.get(i);
                    System.out.println(tab + (i + 1) + ".[" + (curr.getStatus() ? "X" : " ") + "] " + curr);
                }
                System.out.print(divider);
            } else {
                String[] cont = inp.split(" ");
                if (cont[0].equals("mark")) {
                    int ind = Integer.parseInt(cont[1]) - 1;
                    db.get(ind).markDone();

                    String toPrint = tab + "Nice! I've marked this task as done:\n" +
                            tab + "  [X] "  + db.get(ind) + "\n";
                    printInDivider(divider, toPrint);
                } else if (cont[0].equals("unmark")) {
                    int ind = Integer.parseInt(cont[1]) - 1;
                    db.get(ind).unmarkDone();

                    String toPrint = tab + "Ok, I've marked this task as not done yet:\n" +
                            tab + "  [ ] "  + db.get(ind) + "\n";
                    printInDivider(divider, toPrint);
                } else {
                    db.add(new Task(inp, false));
                    String toPrint = tab + "added: " + inp + "\n";

                    printInDivider(divider, toPrint);
                }
            }
        }

        printInDivider(divider, end);
    }
}
