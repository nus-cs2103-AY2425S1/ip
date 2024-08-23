import java.util.Scanner;
public class Reminderebot {
    private static String greetingText = "____________________________________________________________\n" +
            " Hello! I'm [Reminderebot]\n" +
            " What can I do for you?\n" +
            "____________________________________________________________\n";

    private static String goodbyeText = "____________________________________________________________\n" +
            " Bye. Hope to see you again soon!\n" +
            "____________________________________________________________";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greeting();
        while (true) {
            System.out.println("");
            String command = sc.nextLine();
            if (command.equals("bye")) break;
            System.out.println("____________________________________________________________\n" +
                    command + "\n____________________________________________________________"
            );
        }
        goodbye();
    }

    public static void greeting() {
        System.out.println(greetingText);
    }

    public static void goodbye() {
        System.out.println(goodbyeText);
    }
}
