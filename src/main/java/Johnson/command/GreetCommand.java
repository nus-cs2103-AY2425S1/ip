package Johnson.command;

import java.util.Random;

public class GreetCommand extends Command {
    /**
     * The command words that identifies a GreetCommand instance.
     */
    public static final String[] COMMAND_WORDS = {"greet", "hello", "hi"};

    /**
     * The possible messages that are displayed when a GreetCommand instance is executed successfully.
     */
    private static final String[] COMMAND_MSGS = {"Well, look who decided to show up! Welcome to the fight, Chief!",
            "About damn time you showed up! What's the plan, Chief?",
            "Rise and shine buttercup! We got work to do!"};

    @Override
    public String executeCommand() {
        Random rand = new Random();
        int index = rand.nextInt(COMMAND_MSGS.length);
        return COMMAND_MSGS[index];
    }
}
