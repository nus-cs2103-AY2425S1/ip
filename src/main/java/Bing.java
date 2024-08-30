import java.util.Scanner;
public class Bing {
    public static void main(String[] args) {
        String message = "______________________________\n"
                + "Hi! My name is Bing\n"
                + "How can I help you?\n"
                + "______________________________\n";
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            input = scanner.nextLine();
            if (input.equals("Bye") || input.equals("bye")) {
                System.out.println("______________________________\n"
                        + "Bye. Have a good day.\n"
                        + "______________________________\n");
                break;
            }
            else {
                System.out.println("______________________________\n"
                        + input + "\n"
                        + "______________________________\n");
            }
        }
    }
}

