
import java.util.Scanner;
public class Bob {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Bob \nWhat can I do for you?");
        System.out.println();
        String s = "";
        while (true) {
            Scanner input = new Scanner(System.in);
            s = input.nextLine();
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
