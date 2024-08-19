public class Agave {

    private UserInterface ui;
    private boolean isRunning;

    public Agave() {
        this.ui = new UserInterface();
        this.isRunning = true;
    }

    public void run() {
        ui.showWelcome();

        while (isRunning) {
            String userInput = ui.getUserInput("Enter a command: ");
            System.out.println(userInput);
            if (userInput.equalsIgnoreCase("bye")) {
                isRunning = false;
                ui.showBye();
            } else {
                ui.showEcho(userInput);
            }
        }
    }

    public static void main(String[] args) {
        Agave agave = new Agave();
        agave.run();
    }
}
