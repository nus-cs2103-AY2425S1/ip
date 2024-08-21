public class TheBotFather {

    public static void main(String[] args) {

        printLine();
        printGreeting();
        printLine();
        printGoodBye();
        printLine();


    }

    public static void printGreeting() {
        System.out.println("Hello! I'm The BotFather");
        System.out.println("What can I do for you?");
    }

    public static void printGoodBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
}
