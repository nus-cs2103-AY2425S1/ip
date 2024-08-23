import java.util.Scanner;

public class Void {
    public static void main(String[] args) {
       /* String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        */
        String logo =
                "### ###   ## ##     ####   ### ##   \n" +
                " ##  ##  ##   ##     ##     ##  ##  \n" +
                " ##  ##  ##   ##     ##     ##  ##  \n" +
                " ##  ##  ##   ##     ##     ##  ##  \n" +
                " ### ##  ##   ##     ##     ##  ##  \n" +
                "  ###    ##   ##     ##     ##  ##  \n" +
                "   ##     ## ##     ####   ### ## \n";

        String[] greetings = {
                "Hello! I'm your friendly void cat, \n",
                "Purr... Hello, wanderer. I am \n",
                "Mew! Welcome human! I'm \n",
                "Greetings from the abyss, friend, for I am \n",
                "Meow! Happy to help, they call me \n"
        };

        String[] assistGreeting = {
                "How can this void assist you today?",
                "At your service, human.",
                "What help does human need today?",
                "Need any help?",
                "What can I do for you?"
        };

        // Example of exits
        String[] exits = {
                "Purr... Until next time, friend.",
                "Meow! I shall vanish into the shadows now.",
                "Farewell! May your path be clear.",
                "Mew! See you in the void again soon.",
                "The void calls, but I'll return. Goodbye!"
        };

        Scanner scanner = new Scanner(System.in);
        String input;
        //Tab string format
        String format = "\t%s%n";

        String[] tasks = new String[100];
        int taskCounter = 0;

        // Display a random greeting
        System.out.printf(format, "------------------------------------------------------------------");
        System.out.printf(format, greetings[(int) (Math.random() * greetings.length)]);

        System.out.println(logo.indent(4));
        // Display a random assist greeting
        System.out.printf(format, assistGreeting[(int) (Math.random() * assistGreeting.length)]);
        System.out.printf(format, "------------------------------------------------------------------");

        while (true) {
            input = scanner.nextLine();  // Reads user input
            if (input.equals("bye")) {
                System.out.printf(format, "------------------------------------------------------------------");
                //Display a random exit
                System.out.printf(format, exits[(int) (Math.random() * exits.length)]);
                break;  // Exit loop when bye
            } else if (input.equals("list")){
                System.out.printf(format, "------------------------------------------------------------------");
                for (int i = 0; i < taskCounter; i++) {
                    System.out.printf(format, (i + 1) + ". " + tasks[i]);  // Echoes it
                }
                System.out.printf(format, "------------------------------------------------------------------");
            } else {
                tasks[taskCounter] = input;
                taskCounter++;
                System.out.printf(format, "------------------------------------------------------------------");
                System.out.printf(format, "added: " + input);  // Echoes it
                System.out.printf(format, "------------------------------------------------------------------");

            }
        }

        System.out.printf(format, "------------------------------------------------------------------");
        scanner.close();

    }
}
