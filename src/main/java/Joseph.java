import java.util.Scanner;
public class Joseph {
    public static void main(String[] args) {
        final String NAME = "Joseph";
        final String EXIT = "bye";
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------------------------");
        System.out.println("Hello, I'm " + NAME + "!");
        System.out.println("How can I help you today?");
        System.out.println("----------------------------------");

        while (true) {
            String input = scanner.nextLine();
            if (input.equals(EXIT)) {
                System.out.println("Bye! Have a nice day :)");
                System.out.println("----------------------------------");
                scanner.close();
                break;
            }
            System.out.println("----------------------------------");
            System.out.println(input);
            System.out.println("----------------------------------");
        }
    }
}