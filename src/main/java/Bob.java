
import java.util.Scanner;
public class Bob {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Bob \nWhat can I do for you?");
        System.out.println();
        String s = "";
        while (true) {
            Scanner input = new Scanner(System.in);
            s = input.nextLine();
            int i = s.indexOf(" ");
            if (i != -1) {
                String s1 = s.substring(0, i);
                if (s1.equals("mark") || s1.equals("unmark")) {
                    int a = Integer.parseInt(s.substring(i+1)) - 1;
                    if (s1.equals("mark")) {
                        Task.mark(a);
                    } else {
                        Task.unmark(a);
                    }
                    s = "list";
                }
            }
            if (s.equals("list")) {
                Task.printList();
            } else if (s.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                new Task(s);
                System.out.println("added: " + s);
            }

        }

    }
}
