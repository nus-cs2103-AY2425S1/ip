package torne.command;

import java.util.Map;

/**
 * Bye command. TODO this doesn't actually do anything at the moment, the exiting is handled right in
 * {@link torne.ui.Torne}
 */
public class Bye extends Command{
    protected Bye() {
        super("bye", "Exits Torne.");
    }

    @Override
    public void execute(Map<String, String> arguments) {
        String exitText = """
                Aww, bye to you as well :c""";
        output.writeText(exitText);
    }
}
