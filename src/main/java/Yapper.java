import java.util.Scanner;

public class Yapper {
    public static void main(String[] args) {

        String divider = "____________________________________________________________";
        String name = "Yapper";

        System.out.println(divider);
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println(divider);

        while (true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println(divider);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(divider);
                break;
            } else {
                System.out.println(divider);
                System.out.println(input);
                System.out.println(divider);
            }
        }
    }
}
