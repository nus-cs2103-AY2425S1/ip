public class Ui {
    private final String welcome;

    Ui(String botName) { // Starting of bot programme
        String horiLine = "_____________";
        this.welcome = horiLine + "\n"
                + "Hello! I'm " + botName + "\n"
                + "What can I do for you?\n"
                + horiLine + "\n";
    }

    public void showWelcome() {
        System.out.println(this.welcome);
    }

}
