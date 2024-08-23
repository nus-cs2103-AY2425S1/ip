import java.util.StringJoiner;

public class Parser {
    public record Input(String command, String args) {}

    public static Input parseInput(String input) {
        String[] split = input.split(" ", 2);
        if (split.length <= 1) {
            return new Input(input, "");
        }
        return new Input(split[0], split[1]);
    }

    public static String[] extractArgs(String inputArgs, String[] args) {
        // The values ot the arguments. One additional space is used for the
        // text before all the arguments, i.e. <command> TEXT <arg1> ARG1 VAL...
        String[] values = new String[args.length + 1];
        int index = 0;

        StringJoiner sj = new StringJoiner(" ");
        String[] split = inputArgs.split(" ");
        for (String s : split) {
            // s is part of the previous argument
            if (!(index < args.length && s.equals(args[index]))) {
                sj.add(s);
                continue;
            }

            // s is the name of the next argument
            values[index] = sj.toString();
            sj = new StringJoiner(" ");
            index++;
        }
        values[index] = sj.toString();

        if (index != args.length) {
            throw new MissingArgumentException(args.length, index);
        }

        return values;
    }
}
