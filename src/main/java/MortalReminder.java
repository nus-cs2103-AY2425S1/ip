public class MortalReminder {
    private final String separatorLine = "_________________________________";

    public static void main(String[] args) {
        MortalReminder mortalReminder = new MortalReminder();
        mortalReminder.Welcome();
        mortalReminder.GoodBye();
    }

    private void Welcome() {
        String welcomeMessage = separatorLine
                + "\n"
                + "Hello I'm Mortal Reminder! \n"
                + "What can I do for you? \n"
                + separatorLine;
        System.out.println(welcomeMessage);
    }

    private void GoodBye() {
        String goodByeMessage = "Bye. Hope to see you again soon! \n"
                + separatorLine;
        System.out.println(goodByeMessage);
    }
}
