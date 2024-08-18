import java.util.ArrayList;
import java.util.List;

public class BingBongBot {
    private final BingBongUI ui;
    private List<String> commandStorage;

    public BingBongBot(BingBongUI ui) {
        this.ui = ui;
        this.commandStorage = new ArrayList<>();
    }

    public void run() {
        ui.showGreeting();
        boolean isRunning = true;

        while (isRunning) {
            String command = ui.readCommand();
            if (command.equalsIgnoreCase("bye")) {
                isRunning = false;
                ui.showGoodbye();
            } else if (command.equalsIgnoreCase("list")) {
                listCommands();
            } else {
                this.commandStorage.add(command);
                ui.showResponse("added: " + command);
            }
        }
    }

    private void listCommands() {
        if (commandStorage.isEmpty()) {
            ui.showResponse("No commands have been saved.");
        } else {
            StringBuilder list = new StringBuilder();
            for (int i = 0; i < commandStorage.size(); i++) {
                list.append(i + 1).append(". ").append(commandStorage.get(i)).append("\n");
            }
            ui.showResponse(list.toString());
        }
    }
}
