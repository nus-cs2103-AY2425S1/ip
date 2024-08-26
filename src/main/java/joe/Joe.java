package joe;

public class Joe {
    public static final String CHATBOT_NAME = "Joe";

    public static String input = "";

    private Controller controller;
    private Parser parser;

    public Joe() {
        this.controller = new Controller(CHATBOT_NAME);
        this.parser = new Parser(controller);
    }

    public void run() {
        controller.startProgram();
        boolean isRunning = true;
        while (isRunning) {
            isRunning = parser.parse();
        }
        controller.endProgram();
    }

    public static void main(String[] args) {
        new Joe().run();
    }
}
