public class MonoBot {
    public static void main(String[] args) {
        printGreeting();
        printFarewell();
    }

    private static void printGreeting() {
        System.out.println("―――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
        System.out.println(" Hello! I'm MonoBot");
        System.out.println(" What can I do for you?");
        System.out.println("―――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
    }

    private static void printFarewell() {
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("―――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
    }
}
