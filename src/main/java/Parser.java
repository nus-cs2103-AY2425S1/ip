import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public enum Command {
        LIST, HELP, MARK, UNMARK, TASK, DELETE, UNSURE
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
        } else {
            return Command.UNSURE;
        }
    }

    public int parseMark(String input) {
        Pattern markPattern = Pattern.compile("^(mark) (\\d+)$");
        Matcher markMatcher = markPattern.matcher(input);
        markMatcher.matches();
        return Integer.parseInt(markMatcher.group(2));
    }

    public int parseUnmark(String input) {
        Pattern markPattern = Pattern.compile("^(unmark) (\\d+)$");
        Matcher markMatcher = markPattern.matcher(input);
        markMatcher.matches();
        return Integer.parseInt(markMatcher.group(2));
    }

    public int parseDelete(String input) {
        Pattern deletePattern = Pattern.compile("^delete (\\d+)$");
        Matcher deleteMatcher = deletePattern.matcher(input);
        deleteMatcher.matches();
        return Integer.parseInt(deleteMatcher.group(1));
    }

    public Task parseTask(String input) throws WrongInputException {
        Pattern taskPattern = Pattern.compile("^(todo|deadline|event)\\s(.*)$");
        Matcher taskMatcher = taskPattern.matcher(input);
        Task newTask = null;
        taskMatcher.matches();
        System.out.println(taskMatcher.group(1));
        switch(taskMatcher.group(1)) {
            case "todo":
                newTask = Todo.handleInput(taskMatcher.group(2));
                break;
            case "deadline":
                newTask = Deadline.handleInput(taskMatcher.group(2));
                break;
            case "event":
                newTask = Event.handleInput(taskMatcher.group(2));
                break;
        }
        return newTask;
    }
}
