import sigmabot.ui.UiComponent;

import java.util.Map;

public class Dialogue {
    private String identifier = "default chat";
    private Map<String, Map<String, Task>> taskListCatalog;
    private UiComponent ui = new UiComponent();
    private Dialogue() {}
    private Dialogue(String identifier) {
        this.identifier = identifier;
    };
    public static Dialogue defaultDialogue() {
        return new Dialogue();
    }
    public static Dialogue newNamedDialogue(String identifier) {
        return new Dialogue(identifier);
    }

    public void run() {
        Greeting.greet();
        Command command = ui.readCommand();
        while (!(command instanceof Terminate)) {
            command.execute(ui);
            command = ui.readCommand();
        }
        ui.closeScanner();
    }
}
