package commands.parser;

import java.util.StringJoiner;

public class Parser {
    public record Message(String keyword, String args) {}

    public static Message parseMessage(String message) {
        String[] split = message.split(" ", 2);
        if (split.length <= 1) {
            return new Message(message, "");
        }
        return new Message(split[0], split[1]);
    }

    public static String[] extractArgs(String messageArgs, String[] argNames) {
        // The values ot the arguments. One additional space is used for the
        // text before all the arguments, i.e. <keyword> TEXT <arg1> ARG1 VAL...
        String[] values = new String[argNames.length + 1];
        int index = 0;

        StringJoiner sj = new StringJoiner(" ");
        String[] split = messageArgs.split(" ");
        for (String s : split) {
            // s is part of the previous argument
            if (!(index < argNames.length && s.equals(argNames[index]))) {
                sj.add(s);
                continue;
            }

            // s is the name of the next argument
            values[index] = sj.toString();
            sj = new StringJoiner(" ");
            index++;
        }
        values[index] = sj.toString();

        if (index != argNames.length) {
            throw new MissingArgumentException(argNames.length, index);
        }

        return values;
    }

    public static int parseInt(String s) throws NumberFormatException {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(String.format("%s is not an integer. Please enter an integer.", s));
        }
    }
}
