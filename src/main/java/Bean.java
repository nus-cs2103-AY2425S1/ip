import java.util.Scanner;
public class Bean {
    public static void main(String[] args) {
        String greeting = "________________________________\n"
                + "Hello! I'm Bean\n"
                + "What can i do for you?\n"
                +"________________________________\n";
        String byeMsg =
                 "________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "________________________________\n";
        System.out.println(greeting);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String response = scanner.nextLine();
            if (!response.equals("bye")) {
                System.out.println(response);
            }
            else {
                System.out.println(byeMsg);
                break;
            }
        }
    }
}
