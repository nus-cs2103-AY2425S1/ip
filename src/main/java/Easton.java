public class Easton {

    public static final String CHATBOT_NAME = "Easton";

    public static void main(String[] args) {
        String logo = " _______  _______  _______  _______  _______  __    _ \n"
                + "|       ||   _   ||       ||       ||       ||  |  | |\n"
                + "|    ___||  |_|  ||  _____||_     _||   _   ||   |_| |\n"
                + "|   |___ |       || |_____   |   |  |  | |  ||       |\n"
                + "|    ___||       ||_____  |  |   |  |  |_|  ||  _    |\n"
                + "|   |___ |   _   | _____| |  |   |  |       || | |   |\n"
                + "|_______||__| |__||_______|  |___|  |_______||_|  |__|\n";
        System.out.println("Hello from\n" + logo);
        printDivider();
        System.out.println("Hello! I'm " + CHATBOT_NAME);
        System.out.println("What can I do for you?");
        printDivider();
        System.out.println("Bye. Hope to see you again soon!");
        printDivider();


    }

    private static void printDivider() {
        System.out.println("____________________________________________________________");
    }
}
