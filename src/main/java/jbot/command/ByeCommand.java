package jbot.command;

import jbot.JBot;

public class ByeCommand implements JBotCommand {

    private static final ByeCommand instance = new ByeCommand();
    private ByeCommand() {
    };
    public static ByeCommand getInstance() {
        return instance;
    }
    @Override
    public void run(String input) {
        System.out.println("Bye. Hope to see you again soon!");
        JBot.close();
    }
}
