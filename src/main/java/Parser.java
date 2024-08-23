import java.util.StringJoiner;

public class Parser {
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

        return values;
    }
}
