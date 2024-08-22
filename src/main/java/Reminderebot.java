public class Reminderebot {
    private static String greetingText = "____________________________________________________________\n" +
            " Hello! I'm [Reminderebot]\n" +
            " What can I do for you?\n" +
            "____________________________________________________________\n" +
            " Bye. Hope to see you again soon!\n" +
            "____________________________________________________________";


    public static void main(String[] args) {
        greeting();
    }

    public static void greeting() {
        System.out.println(greetingText);
    }
}
