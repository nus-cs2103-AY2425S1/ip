import java.util.Scanner;

class King {
    public static void main(String[] args) {
        String logo = " _  __ _             \n" +
                "| |/ /(_)_ __   __ _ \n" +
                "| ' / | | '_ \\ / _` |\n" +
                "| . \\ | | | | | (_| |\n" +
                "|_|\\_\\|_|_| |_|\\__, |\n" +
                "               |___/ ";

        System.out.println("Greetings from the\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("How may I assist you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);

        while(true) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                break;
            }
            System.out.println(input);
            System.out.println("____________________________________________________________");
        }
        System.out.println("You are dismissed my loyal subject.");
        System.out.println("____________________________________________________________");

        scanner.close();
    }
}
