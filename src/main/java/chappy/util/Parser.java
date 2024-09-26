package chappy.util;

import java.util.Objects;

import chappy.exception.CreateTaskException;

public class Parser {
    public static enum Command {
        BYE("bye"), LIST("list"), UNMARK("unmark"), MARK("mark"), TODO("todo"), DEADLINE(
                "deadline"), EVENT("event"), DELETE("delete"), FIND("find"), DEFAULT("default");

        private final String keyword;

        Command(String keyword) {
            this.keyword = keyword;
        }

        public String getKeyword() {
            return this.keyword;
        }

    }

    public Parser() {

    }

    private Command matchUserCommand(String userInput) {
        Command userInputCommand = null;
        for (Command command : Command.values()) {
            if (userInput.toLowerCase().contains(command.getKeyword())) {
                userInputCommand = command;
                break;
            }
        }
        return userInputCommand;
    }

    public Command parseUserInput(String userInput) throws CreateTaskException {
        Command userCommand = matchUserCommand(userInput);
        if (!Objects.isNull(userCommand)) {
            return userCommand;
        } else {
            return Command.DEFAULT;
        }
    }

}
