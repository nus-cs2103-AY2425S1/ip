import java.util.Scanner;

public class Matcha {
    public static void main(String[] args) {
        String greet = "____________________________________________________________\n" +
                " Hi there! I am Matcha, your personal chatbot.\n" +
                " How can I help you today?\n" +
                "____________________________________________________________\n" ;
        System.out.println(greet);

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                String exit = "____________________________________________________________\n" +
                        " Bye. Hope to see you again!\n" +
                        "____________________________________________________________\n";
                System.out.println(exit);
                break;
            } else {
                String reply = "____________________________________________________________\n" +
                        input + "\n" +
                        "____________________________________________________________\n";
                System.out.println(reply);
            }

        }
    }
}
