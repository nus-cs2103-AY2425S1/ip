public class MortalReminder {
    private static final String separatorLine = "_________________________________";

    private static void Welcome() {
        String welcomeMessage = separatorLine
                + "\n"
                + "Hello I'm Mortal Reminder! \n"
                + "What can I do for you? \n"
                + separatorLine;
        System.out.println(welcomeMessage);
    }

    private static void GoodBye() {
        String goodByeMessage = "Bye. Hope to see you again soon! \n"
                + separatorLine;
        System.out.println(goodByeMessage);
    }

    public static void main(String[] args) {
        Welcome();
        GoodBye();
    }
}
