import java.util.Scanner;
public class Joseph {
    public static void main(String[] args) {
        final String NAME = "Joseph";
        final String EXIT = "bye";
        final String LIST = "list";
        String[] list = new String[100];
        int tracker = 0;

        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------------------------");
        System.out.println("Hello, I'm " + NAME + "!");
        System.out.println("How can I help you today?");
        System.out.println("----------------------------------");

        while (true) {
            String input = scanner.nextLine();
            if (input.equals(EXIT)) {
                System.out.println("----------------------------------");
                System.out.println("Bye! Have a nice day :)");
                System.out.println("----------------------------------");
                scanner.close();
                break;
            } else if (input.equals(LIST)) {
                System.out.println("----------------------------------");
                for (int i = 1; i < tracker + 1; i++) {
                    System.out.println(i + ". " + list[i-1]);
                }
                System.out.println("----------------------------------");
            } else {
                list[tracker] = input;
                System.out.println("----------------------------------");
                System.out.println("I've added: " + input);
                System.out.println("----------------------------------");
                tracker++;
            }
        }
    }
}