package thebotfather.command;

import thebotfather.util.TheBotFatherException;

public enum CommandList {
    MARK, UNMARK, DELETE, TODO, LIST, DEADLINE, EVENT, BYE, FIND;

    public static CommandList findCommand(String command) throws TheBotFatherException {
        try {
            return CommandList.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new TheBotFatherException("OOPS!!! I'm sorry, but I don't know what that means :-(.\n" +
                    "\tUse \"bye\" if you want to exit the program");
        }
    }
}
