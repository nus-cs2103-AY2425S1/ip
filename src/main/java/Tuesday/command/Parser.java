package Tuesday.command;

import Tuesday.util.TuesdayException;

public class Parser {
    public Parser() {

    }

    public static Command parse(String command) throws TuesdayException {
        String[] userInputArr = command.split(" ", 2); // separate the command by " " into 2
        if (userInputArr[0].equals("bye")) {
            return new ExitCommand(command);
        } else if (userInputArr[0].equals("list")){
            return new ListCommand(command);
        } else if (userInputArr[0].equals("mark")){
            return new MarkCommand(userInputArr[0], Integer.parseInt(userInputArr[1]), true);
        } else if (userInputArr[0].equals("unmark")){
            return new MarkCommand(userInputArr[0], Integer.parseInt(userInputArr[1]), false);
        } else if (userInputArr[0].equals("todo")){
            return new AddCommand(userInputArr[0], userInputArr[1]);
        } else if (userInputArr[0].equals("deadline")){
            return new AddCommand(userInputArr[0], userInputArr[1]);
        } else if (userInputArr[0].equals("event")){
            return new AddCommand(userInputArr[0], userInputArr[1]);
        } else if (userInputArr[0].equals("delete")){
            return new DeleteCommand(command, Integer.parseInt(userInputArr[1]));
        } else if (userInputArr[0].equals("find")){
            return new FindCommand(command, userInputArr[1]);
        } else {
            throw new TuesdayException("Hey there!! I do not know what you mean. Can you type it out differently?");
        }
    }
}
