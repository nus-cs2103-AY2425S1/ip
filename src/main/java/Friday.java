public class Friday {
    public static void main(String[] args) {
        String logo = "___________        .__     .___                \n"
                + "\\_   _____/_______ |__|  __| _/_____    ___.__.\n"
                + " |    __)  \\_  __ \\|  | / __ | \\__  \\  <   |  |\n"
                + " |     \\    |  | \\/|  |/ /_/ |  / __ \\_ \\___  |\n"
                + " \\___  /    |__|   |__|\\____ | (____  / / ____|\n"
                + "     \\/                     \\/      \\/  \\/     \n";

        System.out.println("Loading........\n" + logo);
        printLine();
        System.out.println("""
                Hello! I'm Friday!
                What would you like to do?
                """);
        terminate();

    }
    public static void printLine() {
        System.out.println("----------------------------------------------");
    }

    public static void terminate() {
        printLine();
        System.out.println("Bye! See you soon!");
        printLine();
    }
}
