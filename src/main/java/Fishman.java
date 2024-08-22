import java.util.Objects;

public class Fishman {
    private Ui ui;

    public Fishman(Ui ui) {
        this.ui = ui;
    }

    public void start() {
        ui.displayLogo();
        ui.displayWelcome();
        boolean isExit = false;

        while (!isExit) {
            String command = ui.readCommands();
            if (!command.equals("bye")) {
                System.out.println(command);
            } else {
                isExit = true;
            }

        }
        ui.displayGoodbye();
    }
    public static void main(String[] args) {
        Ui ui = new Ui();
        Fishman fishman = new Fishman(ui);
        fishman.start();
    }
}

