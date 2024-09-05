package Nave;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public enum Command {
        LIST, HELP, MARK, UNMARK, TASK, DELETE, UNSURE, FIND
    }
    //Function to handle input checking
    public Command handleInput(String input) {
        //Regex checking for mark/unmark
        Pattern markPattern = Pattern.compile("^(mark|unmark) (\\d+)$");
        Matcher markMatcher = markPattern.matcher(input);

        //Regex checking for delete
        Pattern deletePattern = Pattern.compile("^delete (\\d+)$");
        Matcher deleteMatcher = deletePattern.matcher(input);

        //Regex checking for tasks
        Pattern taskPattern = Pattern.compile("^(todo|deadline|event)\\s?(.*)$");
        Matcher taskMatcher = taskPattern.matcher(input);

        //Regex checking for find
        Pattern findPattern = Pattern.compile("^find (.+)$");
        Matcher findMatcher = findPattern.matcher(input);

        if (input.equals("list")) {
            return Command.LIST;
        } else if (input.equals("/help")) {
            return Command.HELP;
        } else if (markMatcher.matches()) {
            String markOrNot = markMatcher.group(1);
            return markOrNot.equals("mark")
                    ? Command.MARK
                    : Command.UNMARK;
        } else if (taskMatcher.matches()) {
            return Command.TASK;
        } else if (deleteMatcher.matches()) {
            return Command.DELETE;
        } else if (findMatcher.matches()) {
            return Command.FIND;
        } else {
            return Command.UNSURE;
        }
    }

    public int parseMark(String input) {
        Pattern markPattern = Pattern.compile("^(mark) (\\d+)$");
        Matcher markMatcher = markPattern.matcher(input);

        return markMatcher.matches()
            ? Integer.parseInt(markMatcher.group(2))
            : -1;
    }

    public int parseUnmark(String input) {
        Pattern markPattern = Pattern.compile("^(unmark) (\\d+)$");
        Matcher markMatcher = markPattern.matcher(input);
        return markMatcher.matches()
            ? Integer.parseInt(markMatcher.group(2))
            : -1;
    }

    public int parseDelete(String input) {
        Pattern deletePattern = Pattern.compile("^delete (\\d+)$");
        Matcher deleteMatcher = deletePattern.matcher(input);
        return deleteMatcher.matches()
            ? Integer.parseInt(deleteMatcher.group(1))
            : -1;
    }

    public Task parseTask(String input) throws WrongInputException {
        Pattern taskPattern = Pattern.compile("^(todo|deadline|event)\\s(.*)$");
        Matcher taskMatcher = taskPattern.matcher(input);
        Task newTask = null;
        if (!taskMatcher.matches()) {
            throw new WrongInputException("There is an error in your command");
        }
        newTask = switch (taskMatcher.group(1)) {
            case "todo" -> Todo.handleInput(taskMatcher.group(2));
            case "deadline" -> Deadline.handleInput(taskMatcher.group(2));
            case "event" -> Event.handleInput(taskMatcher.group(2));
            default -> newTask;
        };
        return newTask;
    }

    public String parseFind(String input) {
        Pattern findPattern = Pattern.compile("^find (.+)$");
        Matcher findMatcher = findPattern.matcher(input);
        return findMatcher.matches() ? findMatcher.group(1) : "";
    }
}
