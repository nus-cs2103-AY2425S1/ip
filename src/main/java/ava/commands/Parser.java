package ava.commands;

public class Parser {

    /**
     * Parses the text command and maps it to one of the available commands
     *
     *
     * This command helps Ava interpret text commands.
     * @param command Command entered by user as text
     * @return Command corresponding to user's input.
     */
    public static Command parseCommand(String command){
        if(command.equals("list")){
            return Command.LIST;
        } else if(command.startsWith("mark")){
            return Command.MARK;
        } else if(command.startsWith("unmark")) {
            return Command.UNMARK;
        } else if(command.startsWith("delete")){
            return Command.DELETE;
        } else if (command.startsWith("todo")){
            return Command.TODO;
        } else if (command.startsWith("deadline")){
            return Command.DEADLINE;
        } else if (command.startsWith("event")){
            return Command.EVENT;
        } else if (command.startsWith("find")){
            return Command.FIND;
        } else {
            throw new IllegalArgumentException("Unsupported Command");
        }
    }
}
