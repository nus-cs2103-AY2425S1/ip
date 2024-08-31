package chatbot.util;

import chatbot.util.Task;
import chatbot.util.UserInterface;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static KillJoy kj;
    private static UserInterface ui;

    public Parser(KillJoy kj, UserInterface ui) {
        this.kj = kj;
        this.ui = ui;
    }

    public static Task.TaskType parseUserInput(String input) {
        String[] inputSplitBySlash = input.split("/");
        String[] inputSplitBySpace = inputSplitBySlash[0].split(" ");
        String typeOfTask = inputSplitBySpace[0];

        try {
            return Task.TaskType.valueOf(typeOfTask.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public static String getDescription(String input) {
        String[] inputSplitBySlash = input.split("/");
        return inputSplitBySlash[0].replaceFirst("todo ", "")
                .replaceFirst("deadline ", "").replaceFirst("event ", "");
    }

    public static String getBy(String input) {
        String[] inputSplitBySlash = input.split("/");
        if (inputSplitBySlash.length < 2) {
            return null;
        }
        return inputSplitBySlash[1].replaceFirst("by ", "");
    }

    public static String getFrom(String input) {
        String[] inputSplitBySlash = input.split("/");
        if (inputSplitBySlash.length < 2) {
            return null;
        }
        return inputSplitBySlash[1].replaceFirst("from ", "");
    }

    public static String getTo(String input) {
        String[] inputSplitBySlash = input.split("/");
        if (inputSplitBySlash.length < 3) {
            return null;
        }
        return inputSplitBySlash[2].replaceFirst("to ", "");
    }

    public static String[] parseMarkUnmarkDelete(String input) {
        String[] inputAsList = input.split(" ");

        if (inputAsList.length == 1) {
            ui.displayEnterNumberMessage();
            return null;
        }
        //Regex section referenced from https://jenkov.com/tutorials/java-regex/index.html
        String regex = "^(mark|unmark|delete)(\\s)(\\d+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (!matcher.find()) {
            ui.displayInvalidCommandFormatMessage();
            return null;
        }

        return new String[]{matcher.group(1), matcher.group(3)};
    }

    public static String[] parseTaskInfo(String taskInfo) {
        return taskInfo.split("\\|");
    }


}
