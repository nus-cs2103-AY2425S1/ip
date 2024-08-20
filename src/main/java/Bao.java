import java.util.Scanner;
public class Bao {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String baoHi =   "     ___   \n"
                        + "   /     \\  \n"
                        + "  /       \\ \n"
                        + " |  ^   ^  |\n"
                        + " |    3    |\n"
                        + " \\________/ \n";

        String baoBye =   "     ___   \n"
                        + "   /     \\  \n"
                        + "  /       \\ \n"
                        + " |  T   T  |\n"
                        + " |    ^    |\n"
                        + " \\________/ \n";

        System.out.println("____________________________________________________________");
        System.out.println(baoHi);
        System.out.println("Hello! I'm Bao but you can call me Bao");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println(baoBye);
                System.out.println("Bye :( Come back soon!");
                System.out.println("____________________________________________________________");
                break;
            } else {
                System.out.println("____________________________________________________________");
                System.out.println(input);
                System.out.println("____________________________________________________________");
            }
        }
        scanner.close();
    }
}
