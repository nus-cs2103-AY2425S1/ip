import java.util.Scanner;

public class Bibi {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = """
                        ########   #######   ########   #######\s
                        #       #     #      #       #     #   \s
                        ########      #      ########      #   \s
                        #       #     #      #       #     #   \s
                        #       #     #      #       #     #   \s
                        ########   #######   ########   #######\s
                      """;

        System.out.println("Hello from\n" + logo + "\n"
                    + "How can I help you?");
        System.out.println("---------------------------------");

        String cmd;
        while (scanner.hasNext()) {
            cmd = scanner.next();
            if (cmd.equals("bye")) {
                break;
            }

            System.out.println("--------------------------------");
            System.out.println(cmd);
            System.out.println("--------------------------------");
        }

        // Exit
        System.out.println("---------------------------------");
        System.out.println("See you soon :3");
        System.out.println("---------------------------------");
    }
}
