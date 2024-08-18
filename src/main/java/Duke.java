import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        String greeting = "   *        *        *        __o    *       *\n"
                + "*      *       *        *    /_| _     *\n"
                + "   K  *     K      *        O'_)/ \\  *    *\n"
                + "  <')____  <')____    __*   V   \\  ) __  *\n"
                + "   \\ ___ )--\\ ___ )--( (    (___|__)/ /*     *\n"
                + " *  |   |    |   |  * \\ \\____| |___/ /  *\n"
                + "    |*  |    |   |     \\____________/       *\n";
        String festiveMessage =
                "Merry★* 。 • ˚ ˚ ˛ ˚ ˛ •\n" +
                        "•。★Christmas★ 。* 。\n" +
                        "° 。 ° ˛˚˛ * Π__。˚\n" +
                        "˚ ˛ •˛•˚ */__/~＼。˚ ˚ ˛\n" +
                        "˚ ˛ •˛• ˚ ｜ 田田 ｜門｜ ˚\n" +
                        "And a happy new year!";
        System.out.println(greeting + "Hello, I am Rudolf, Santa's trusty red-nose reindeer");
        System.out.println("Christmas is nearing, and I am here to help you with your christmas preparations. Tell me the tasks that you need to complete.");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        String input = "";
        List<String> entries = new ArrayList<>();

        while (!input.equals("bye")) {
            input = scanner.nextLine();
            System.out.println("____________________________________________________________");

            if (input.equals("list")) {
                if (entries.isEmpty()) {
                    System.out.println("No entries to display.");
                } else {
                    for (int i = 0; i < entries.size(); i++) {
                        System.out.println((i + 1) + ". " + entries.get(i));
                    }
                }
            } else {
                entries.add(input);
                System.out.println("added: " + input);
            }

            System.out.println("____________________________________________________________");
        }

        System.out.println("Bye~ Hope to see you again soon! \n" + festiveMessage);
        System.out.println("____________________________________________________________");
    }
}
