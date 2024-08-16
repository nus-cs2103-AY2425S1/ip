import java.util.Scanner;

public class Gravitas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = "Gravitas";
        System.out.println("____________________________________________________________ \nHello! I'm " + name + "\nWhat can I do for you? \n____________________________________________________________\n");
        while (true) {
            String msg = sc.nextLine();
            if (msg.equals("bye")) {
                System.out.println("____________________________________________________________ \nBye. Hope to see you again soon! \n____________________________________________________________");
                break;
            }
            System.out.println("____________________________________________________________ \n" + msg + "\n____________________________________________________________\n");
        }
    }
}
