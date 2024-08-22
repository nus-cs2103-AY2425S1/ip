public class Elster {
    public static void main(String[] args) {
        String logo = "___________.__            __\n" +
                    "\\_   _____/|  |   _______/  |_  ___________\n" +
                    " |    __)_ |  |  /  ___/\\   __\\/ __ \\_  __ \\\n" +
                    " |        \\|  |__\\___ \\  |  | \\  ___/|  | \\/\n" +
                    "/_______  /|____/____  > |__|  \\___  >__|\n" +
                    "        \\/           \\/            \\/";
        System.out.println(logo);
        printLine();
        System.out.println("Hello, \"greetings\" from your friendly neighbourhood chatbot Elster..");
        System.out.println("How can I help you today :|");
        printLine();
        System.out.println("See you next time.");
        printLine();
    }

    static public void printLine() {
        System.out.println("____________________________________________________________________________");
        return;
    }
}
