import java.util.Scanner;

public class BotimusPrime {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String name = "BotimusPrime";
        String greetingMessage =
                "____________________________________________________________\n" +
                " Hello! I'm " + name + "\n" +
                " What can I do for you?\n" +
                "____________________________________________________________";
        String byeMessage =
                "____________________________________________________________\n" +
                " Bye! Hope to see you again soon!\n" +
                "____________________________________________________________\n";

        System.out.println(greetingMessage);

        while (true) {
            String input = sc.nextLine();
            System.out.println("\n");
            if (input.equalsIgnoreCase("bye")) {
                System.out.println(byeMessage);
                break;
            } else {
                System.out.println(
                        "____________________________________________________________\n" +
                        input + "\n" +
                        "____________________________________________________________\n");
            }
        }

        sc.close();
    }
}
