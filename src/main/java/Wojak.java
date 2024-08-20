import java.util.Scanner;
import java.util.ArrayList;

public class Wojak {

    public static void printList(ArrayList<String> ls) {
        if (!ls.isEmpty()) {
            System.out.println("____________________________________________________________");
            for (int i = 0; i < ls.size(); i++) {
                System.out.println((i+1) + ". " + ls.get(i));
            }
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("____________________________________________________________");
            System.out.println("List is empty");
            System.out.println("____________________________________________________________");
        }

    }

    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<>();

        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Wojak\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");
        Scanner sc = new Scanner(System.in);
        String nextLine = sc.nextLine();
        while (!(nextLine.equals("bye"))) {
            if (nextLine.equals("list")) {
                printList(list);
            } else {
                list.add(nextLine);
                System.out.println("____________________________________________________________\n" +
                        "added: " + nextLine + "\n" +
                        "____________________________________________________________\n");
            }

            nextLine = sc.nextLine();
        }


        System.out.println(
                " Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________\n");
    }
}