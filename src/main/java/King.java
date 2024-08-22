import java.util.Scanner;

class King {
    public static void main(String[] args) {
        String logo = " _  __ _             \n" +
                "| |/ /(_)_ __   __ _ \n" +
                "| ' / | | '_ \\ / _` |\n" +
                "| . \\ | | | | | (_| |\n" +
                "|_|\\_\\|_|_| |_|\\__, |\n" +
                "               |___/ ";

        System.out.println("Behold the wrath of the\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("What do you have for me?");
        System.out.println("____________________________________________________________");

        //Take in user input
        Scanner scanner = new Scanner(System.in);

        //Array to store tasks
        String[] tasks = new String[100];
        int taskcounter = 0;

        while(true) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                break;
            } else if (input.equalsIgnoreCase("list")) {
                for (int i = 0; i < taskcounter; i++) {
                    System.out.println(" " + (i + 1) + ". " + tasks[i]);
                }
            }
            tasks[taskcounter] = input;
            taskcounter++;
            System.out.println("____________________________________________________________");
        }
        System.out.println("You are dismissed my loyal subject.");
        System.out.println("____________________________________________________________");

        scanner.close();
    }
}
