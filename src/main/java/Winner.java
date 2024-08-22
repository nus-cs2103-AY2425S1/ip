public class Winner {

    public static String greetUser() {
        return "Hi! I am Winner.\n" +
                "What can I do for you today?";
    }

    public static String exit() {
        return "Hope I have been of service. Goodbye and see you again!";
    }


    public static void main(String[] args) {
        System.out.println(greetUser());
        System.out.println(exit());
    }
}
