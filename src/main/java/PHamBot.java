import java.util.Scanner;

public class PHamBot {
    private static final String line = "____________________________________________________________\n";

    public static void main(String[] args) {
        Greet();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                SayGoodbye();
                break;
            }

            input = OutlineMessage(input);
            System.out.println(input);
        }
    }

    private static String OutlineMessage(String msg) {
        String res= line + msg + "\n" + line;
        return res;
    }

    public static void Greet() {
        String greeting = "Hi! I'm PHamBot\nHappy to be of service to you today!";
        System.out.println(OutlineMessage(greeting));
    }

    public static void SayGoodbye() {
        String goodbye = "Hope I was able to help\nGoodbye!";
        System.out.println(OutlineMessage(goodbye));
    }
}
