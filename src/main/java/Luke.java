import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Luke {
    private static ArrayList<String> list = new ArrayList<>();
    public static void command() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (Objects.equals(input, "bye")) {
                System.out.println("""
                        ____________________________________________________________
                        yeah bye bye to you too human being
                        ____________________________________________________________
                        """);
                System.exit(0);
            } else if (Objects.equals(input, "list")) {
                showList();
            } else {
                addToList(input);
            }
        }
    }
    public static void addToList(String input) {
        list.add(input);
        System.out.println("____________________________________________________________\n"
                + "added: " + input + "\n"
                + "____________________________________________________________\n");
        command();
    }

    public static void showList() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i+1 + ". " + list.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        String logo = """
                .____           __          \s
                |    |    __ __|  | __ ____ \s
                |    |   |  |  \\  |/ // __ \\\s
                |    |___|  |  /    <\\  ___/\s
                |_______ \\____/|__|_ \\\\___  >
                        \\/          \\/    \\/""";
        System.out.println("Hello from\n" + logo);

        System.out.println("""
                ____________________________________________________________
                i'm luke, your chatbot.
                say something and i will echo it (unless...you wanna say bye to me)
                ____________________________________________________________
                """);
        command();
    }
}
