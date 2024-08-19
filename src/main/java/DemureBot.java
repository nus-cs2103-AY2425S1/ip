import java.util.Scanner;

public class DemureBot {
    public static void main(String[] args) {
        boolean finished = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("____________________________________________________________\n" +
            " Hello! I'm DemureBot\n" +
            " What can I do for you?\n" +
            "____________________________________________________________\n" +
            "\n"
        );
        while (!finished) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                finished = true;
            } else {
                System.out.println("____________________________________________________________\n" +
                        command +
                        "\n" +
                        "____________________________________________________________\n" +
                        "\n"
                );
            }
        }
        System.out.println("____________________________________________________________\n" +
            " Bye. Hope to see you again soon!\n" +
            "____________________________________________________________\n" +
            "\n"
        );
    }
}
