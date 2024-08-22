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
        Task[] tasks = new Task[100];
        int taskcounter = 0;

        while(true) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                break;
            } else if (input.equalsIgnoreCase("list")) {
                for (int i = 0; i < taskcounter; i++) {
                    System.out.println(" " + (i + 1) + ". " + tasks[i].toString());
                }
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("mark")) {
                String[] parts = input.split(" ");
                //prone to error if format is off or value after " " is not an int
                int num = Integer.parseInt(parts[1]);
                tasks[num - 1].markAsDone();
                System.out.println("Good job on the completion my minion!\n" + tasks[num - 1].toString());
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("unmark")) {
                String[] parts = input.split(" ");
                //prone to error if format is off or value after " " is not an int
                int num = Integer.parseInt(parts[1]);
                tasks[num - 1].markAsUndone();
                System.out.println("Better get to work before I execute you!\n" + tasks[num - 1].toString());
                System.out.println("____________________________________________________________");
            } else {
                tasks[taskcounter] = new Task(input);
                taskcounter++;
                System.out.println("____________________________________________________________");
            }
        }
        System.out.println("You are dismissed my humble servant.");
        System.out.println("____________________________________________________________");
        scanner.close();
    }
}
