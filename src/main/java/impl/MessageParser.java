package impl;

import exceptions.InvalidCommandException;

public class MessageParser {
    enum Command{
        List,
        Todo,
        Deadline,
        Event,
        Mark,
        Unmark,
        Delete,
        Error,
        Unknown
    }
    public static Command checkCommand(String in, int size) throws InvalidCommandException{
        String test = in.toLowerCase().split(" ")[0];
        switch(test){
            case "list":
                return Command.List;
            case "todo":
                checkTodo(in);
                return Command.Todo;
            case "deadline":
                checkDeadline(in);
                return Command.Deadline;
            case "event":
                checkEvent(in);
                return Command.Event;
            case "mark":
                handleIntInput(in,size);
                return Command.Mark;
            case "unmark":
                handleIntInput(in,size);
                return Command.Unmark;
            case "delete":
                handleIntInput(in,size);
                return Command.Delete;
            default:
                return Command.Unknown;
        }
    }

    private static void handleIntInput(String in, int size) throws InvalidCommandException{
        int i = Integer.parseInt(in.split(" ")[1])-1;
        if(i>=size){
            throw new InvalidCommandException("Out of index");
        }
    }
    private static void checkTodo(String in) throws InvalidCommandException{
        if(in.length()<=4){
            throw new InvalidCommandException("Please Enter Something for Todo!");
        }
    }
    private static void checkDeadline(String in) throws InvalidCommandException{
        if(in.split("/by ").length < 2){
            throw new InvalidCommandException("Wrong Syntax! Use /by for Deadlines!");
        }
    }
    private static void checkEvent(String in) throws InvalidCommandException{
        String[] eventSplit = in.split("/from ");
        if(eventSplit.length < 2 || eventSplit[1].split("/to ").length<2){
            throw new InvalidCommandException("Wrong Syntax! Use /from and /to for Events!");
        }
    }


}
