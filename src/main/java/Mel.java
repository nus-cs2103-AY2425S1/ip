import java.util.Objects;
import java.util.Scanner;

public class Mel {
    public static void main(String[] args) {
        String logo = " __    __        __  \n"
                    + "|   \\/   | ____ |  | \n"
                    + "| |\\  /| |/ __ \\|  | \n"
                    + "| | \\/ | |  ___/|  | \n"
                    + "|_|    |_|\\____||__| ";
        String line = "____________________________________";

        System.out.println(logo + "\n" + line + "\n"
                            + "Hihi! Mel here (:\n"
                            + "What you need?\n" + line);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (Objects.equals(input, "bye")) {
                System.out.println(line + "\n" + "Buh-bye :)\n" + line);
                break;
            } else {
                System.out.println(line + "\n" + input + "\n" + line);
            }
        }

    }
}
