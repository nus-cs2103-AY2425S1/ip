import java.util.Scanner;

public class IpMan {
    public static final String SEPARATOR = "____________________________________________________________";
    public static void main(String[] args) {
        String[] list = new String[100];
        int nextIdx = 0;
        System.out.println(SEPARATOR);
        System.out.println("Hello from Ip Man!\nWhat can I do for you?");
        System.out.println(SEPARATOR);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(SEPARATOR);
            if (line.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(SEPARATOR);
                break;
            } else if (line.equalsIgnoreCase("list")) {
                for (int i = 0; i < nextIdx; i++){
                    System.out.println((i + 1) + ". " + list[i]);
                }
            } else {
                System.out.println("added: " + line);
                list[nextIdx++] = line;
            }
            System.out.println(SEPARATOR);
        }
    }
}
