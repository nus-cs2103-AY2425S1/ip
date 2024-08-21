import java.util.Objects;
import java.util.Scanner;

public class Luke {
    public static void echo() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (Objects.equals(input, "bye")) {
            System.out.println("____________________________________________________________\n"
                    + "yeah bye bye to you too human being\n"
                    + "____________________________________________________________\n");
        } else {
            System.out.println("____________________________________________________________\n"
                    + input + "\n"
                    + "____________________________________________________________\n");
            echo();
        }
    }

    public static void main(String[] args) {
        String logo = ".____           __           \n"
                + "|    |    __ __|  | __ ____  \n"
                + "|    |   |  |  \\  |/ // __ \\ \n"
                + "|    |___|  |  /    <\\  ___/ \n"
                + "|_______ \\____/|__|_ \\\\___  >\n"
                + "        \\/          \\/    \\/";
        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________\n"
                + "i'm luke, your chatbot.\n"
                + "say something and i will echo it (unless...you wanna say bye to me)\n"
                + "____________________________________________________________\n");
        echo();
    }
}
