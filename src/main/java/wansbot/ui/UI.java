package wansbot.ui;

public class UI {
    private static final String HR = "----------------------------------------------------------------------";

    public UI() {}

    // Method that abstracts Bot message when bot first starts
    public void introduceToUser() {
        String logo ="                 __"
                + "\n|  |  /\\  |\\ | /__` "
                + "\n|/\\| /~~\\ | \\| .__/\n";

        System.out.println(HR + "\nWans:\n"
                + "Hey, I'm\n"
                + logo
                + "\nCan I help? (I can only manage a todo list so...)\n" + HR);
    }
}
