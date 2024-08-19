import java.util.Objects;
import java.util.Scanner;

public class Mel {
    public static void main(String[] args) {
        final String logo = " __    __        __  \n"
                          + "|   \\/   | ____ |  | \n"
                          + "| |\\  /| |/ __ \\|  | \n"
                          + "| | \\/ | |  ___/|  | \n"
                          + "|_|    |_|\\____||__| ";
        final String line = "____________________________________";

        System.out.println(logo + "\n" + line + "\n"
                            + "Hihi! Mel here (:\n"
                            + "What you need?\n" + line);

        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage();

        while (true) {
            String input = scanner.nextLine();
            System.out.println(line);
            if (Objects.equals(input, "bye")) {
                System.out.println("Buh-bye :)\n" + line);
                break;
            } else if (Objects.equals(input, "list")) {
                System.out.println("Mel remembers all your stuff~");
                storage.printAll();
            } else if (input.contains("unmark")) {
                int idx = Integer.parseInt(input.split(" ")[1]);
                System.out.println("Mel wonders how you undid your task...");
                storage.unmark(--idx);
            } else if (input.contains("mark")) {
                int idx = Integer.parseInt(input.split(" ")[1]);
                System.out.println("Mel sees you completed your task!");
                storage.mark(--idx);
            } else {
                storage.add(input);
                System.out.println("Mel remembers: " + input);
            }
            System.out.println(line);
        }

    }
}
