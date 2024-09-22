package parser;

import java.util.Arrays;

/**
 * Contains a String instruction and String[] args where the first element in args is instruction.
 */
public class Instruction {
    private String instruction;
    private String[] args;

    Instruction(String instruction, String[] args) {
        this.instruction = instruction;
        this.args = args;
    }

    public String getInstruction() {
        return (instruction);
    }

    /**
     * Returns args variable without the first element.
     *
     * @return String array of arguments to instruction.
     */
    public String[] getArgs() {
        return (Arrays.copyOfRange(args, 1, args.length));
    }

    public static boolean isTaskCommand(String input) {
        return (input.equals("todo") || input.equals("event") || input.equals("deadline"));
    }

    public static boolean isMarkCommand(String input) {
        return (input.equals("m") || input.equals("mark") || input.equals("um") || input.equals("unmark"));
    }

    public static boolean isDeleteCommand(String input) {
        return (input.equals("d") || input.equals("del") || input.equals("delete"));
    }

    public static boolean isListCommand(String input) {
        return (input.equals("l") || input.equals("list"));
    }

    public static boolean isFindCommand(String input) {
        return (input.equals("f") || input.equals("find"));
    }
}
