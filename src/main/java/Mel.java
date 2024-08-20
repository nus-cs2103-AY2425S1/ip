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
                storage.printAll();
            } else if (input.contains("mark")) {
                storage.mark(input);
            } else if (input.contains("delete")) {
                storage.delete(input);
            } else {
                System.out.println("Mel remembers...");
                try {
                    storage.add(input);
                } catch (MelException e) {
                    System.out.println(e);
                }
            }
            System.out.println(line);
        }

    }
}
