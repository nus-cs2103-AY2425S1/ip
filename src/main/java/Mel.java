import java.util.Objects;
import java.util.Scanner;

public class Mel {
    private static final String LOGO = " __    __        __  \n"
            + "|   \\/   | ____ |  | \n"
            + "| |\\  /| |/ __ \\|  | \n"
            + "| | \\/ | |  ___/|  | \n"
            + "|_|    |_|\\____||__| ";
    private static final String LINE = "____________________________________";

    public static void main(String[] args) {
        System.out.println(LOGO + "\n" + LINE + "\n"
                + "Hihi! Mel here (:\n"
                + "What you need?\n" + LINE);

        System.out.println();

        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage();

        while (true) {
            String input = scanner.nextLine();
            System.out.println(LINE);
            if (input.length() > 100) {
                System.out.println("Mel's eyes explode "
                        + "reading your many words XD");
            } else if (Objects.equals(input, "bye")) {
                System.out.println("Buh-bye :)\n" + LINE);
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
            System.out.println(LINE);
        }

    }
}
