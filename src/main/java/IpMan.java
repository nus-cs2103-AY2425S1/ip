import java.util.Scanner;

public class IpMan {
    public static final String SEPARATOR = "____________________________________________________________";
    public static void main(String[] args) {
        System.out.println(SEPARATOR);
        System.out.println("Hello from Ip Man!\n What can I do for you?");
        System.out.println(SEPARATOR);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("bye")) {
                System.out.println(SEPARATOR);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(SEPARATOR);
                break;
            }
            System.out.println(SEPARATOR);
            System.out.println(line);
            System.out.println(SEPARATOR);
        }
    }
}
