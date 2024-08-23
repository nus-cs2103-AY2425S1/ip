import java.util.Scanner;

public class ConverSage {
    public static void main(String[] args) {
        String logo = "  ____                          ____                   \n" +
                " / ___|___  _ ____   _____ _ __/ ___|  __ _  __ _  ___ \n" +
                "| |   / _ \\| '_ \\ \\ / / _ \\ '__\\___ \\ / _` |/ _` |/ _ \\\n" +
                "| |__| (_) | | | \\ V /  __/ |   ___) | (_| | (_| |  __/\n" +
                " \\____\\___/|_| |_|\\_/ \\___|_|  |____/ \\__,_|\\__, |\\___|\n" +
                "                                            |___/      ";
        String horizontalLine = "____________________________________________________________";
        System.out.println(logo);
        System.out.println("Greetings, I'm your ConverSage.");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);

        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();
            System.out.println(horizontalLine);
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Goodbye. We shall meet again soon.");
                System.out.println(horizontalLine);
                break;
            }
            System.out.println(input);
            System.out.println(horizontalLine);
        }
    }
}
