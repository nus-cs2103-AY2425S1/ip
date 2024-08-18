public class BingBongBot {
    private final BingBongUI ui;

    public BingBongBot(BingBongUI ui) {
        this.ui = ui;
    }

    public void run() {
        ui.showGreeting();
        boolean isRunning = true;

        while (isRunning) {
            String command = ui.readCommand();
            if (command.equalsIgnoreCase("bye")) {
                isRunning = false;
                ui.showGoodbye();
            } else {
                ui.showResponse("BingBong: " + command);
            }
        }
    }
}
