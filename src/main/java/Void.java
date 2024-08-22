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
                "Mew! You have been graced by \n",
                "Greetings from the abyss, friend, for I am \n",
                "Meow! Happy to help, they call me \n"
        };

        String[] assistGreeting = {
                "How can this void assist you today?",
                "At your service, human."

        };

        // Example of exits
        String[] exits = {
                "Purr... Until next time, friend.",
                "Meow! I shall vanish into the shadows now.",
                "Farewell! May your path be clear.",
                "Mew! See you in the void again soon.",
                "The void calls, but I'll return. Goodbye!"
        };

        // Display a random greeting
        System.out.println("------------------------------------------------------------------");
        System.out.println(greetings[(int) (Math.random() * greetings.length)]);

        System.out.println(logo);
        System.out.println("At your service, human");
        System.out.println("------------------------------------------------------------------");

        // Display a random exit
        System.out.println(exits[(int) (Math.random() * exits.length)]);
        System.out.println("------------------------------------------------------------------");


        //System.out.println("Hello from\n" + logo);
    }
}
