import java.util.ArrayList;
import java.util.Scanner;

public class Gravitas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = "Gravitas";
        ArrayList<String> tasks = new ArrayList<>();
        System.out.println("____________________________________________________________ \nHello! I'm " + name + "\nWhat can I do for you? \n____________________________________________________________\n");
        while (true) {
            String msg = sc.nextLine();
            if (msg.equals("bye")) {
                System.out.println("____________________________________________________________ \nBye. Hope to see you again soon! \n____________________________________________________________");
                break;
            } else if (msg.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + ". " + tasks.get(i));
                }
                System.out.println("____________________________________________________________\n");
            } else {
                tasks.add(msg);
                System.out.println("____________________________________________________________ \n" + "added: " + msg + "\n____________________________________________________________\n");
            }
        }
    }
}
