public class Quack {

    // Variables
    private String botName = "Quack";
    private String logo =
        "________                       __    \n" +
        "\\_____  \\  __ _______    ____ |  | __\n" +
        " /  / \\  \\|  |  \\__  \\ _/ ___\\|  |/ /\n" +
        "/   \\_/.  \\  |  // __ \\\\  \\___|    < \n" +
        "\\_____\\ \\_/____/(____  /\\___  >__|_ \\ \n" +
        "       \\__>          \\/     \\/     \\/\n";

    public String spacer = "-".repeat(40);
    
    // Functions

    Quack() {}

    private void printLogo() {
        System.out.println(logo + "\n" + spacer);
    }

    private void greeting() {
        System.out.println("Hello! I'm " + botName + "\nWhat can I do for you?\n" + spacer);
    }

    private void farewell() {
        System.out.println("Bye. Hope to see you again soon!\n" + spacer);
    }

    private void run() {

        this.printLogo();
        this.greeting();
        this.farewell();
    }

    public static void main(String[] args) {    
        Quack bot = new Quack();
        bot.run();
    }
}
