public class Nen2 {
    public static String logo = " _   _                 __  \n"
            + "| \\ | |  ___  _ ___   |_  \\ \n"
            + "|  \\| | / _ \\| '__ |    ) |\n"
            + "| |\\  ||  __/| | | |   / /_ \n"
            + "|_| \\_| \\___||_| |_|  |____|\n";
    public static String seperator = "--------------------------------------------\n";

    public static void main(String[] args) {
        greet();
        exit();
    }

    /**
     * Greets user by printing out logo and greeting messages
     */
    public static void greet() {
        System.out.print(Nen2.seperator);
        System.out.print(logo + "Hello! I'm Nen2 \nWhat can I do for you?\n");
        System.out.print(Nen2.seperator);
    }

    /**
     * End the conversation with ending messages
     */
    public static void exit() {
        System.out.print("Bye. Hope to see you again soon! \n");
        System.out.print(Nen2.seperator);
    }
}
