package Alfred.parser;

import Alfred.ui.Ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public static int getTaskNumberFromInput(String input) {
        String[] parts = input.split(" ");
        return Integer.parseInt(parts[1]);
    }

    public static String getCommand(String input) {
        return input.split(" ")[0];
    }

    public static boolean isValidCommand(String input, String action, int listSize) {
        String regex = "^" + action + " \\d+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (!matcher.matches()) {
            Ui.showInvalidCommandFormat();
            return false;
        }

        int taskNumber = Parser.getTaskNumberFromInput(input);
        if (taskNumber <= 0 || taskNumber > listSize) {
            Ui.showInvalidTaskNumber(listSize);
            return false;
        }
        return true;
    }
}
