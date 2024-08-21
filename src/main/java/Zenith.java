import java.util.Scanner;
public class Zenith {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println(Message.getGreeting());

        while (true) {

            String input = scanner.nextLine();
            switch (input) {
                case "bye":
                    System.out.println(Message.getExit());
                    break;
                default:
                    System.out.println(input + "\n");
                    break;
            }

            if (input.equals("bye")) {
                break;
            }

        }
    }
}
