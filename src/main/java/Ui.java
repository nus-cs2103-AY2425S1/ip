public class Ui {
    public static String horizontalLine = "____________________________________________________________";
    public static String greeting = "Hello homie! I'm JackBean, a chatbot designed to help you with your daily tasks!\nHow may I help you today my homie?";
    public static String exitMessage = "Bye homie! Come back if you need anything else. JackBean, signing off!";

    public static void greet() {
        System.out.println(horizontalLine);
        System.out.println(greeting);
        System.out.println(horizontalLine);
    }

    public static void sayGoodBye() {
        System.out.println(horizontalLine);
        System.out.println(exitMessage);
        System.out.println(horizontalLine);
    }

    public static void showLine() {
        System.out.println(horizontalLine);
    }
}
