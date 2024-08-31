package luke;

import java.util.List;
import java.util.Arrays;

import luke.command.Command;
public class Parser {
    /**
     * Parses strings from save data
     * @param input string input from a line in save data
     * @return a command object that stores the mark, task type (command), and args of tasks from save data
     */
    public static Command parseSavedData(String input) {
        List<String> inputList = Arrays.asList(input.split("\\|"));
        //        System.out.println("inputList: " + inputList);
        String mark = inputList.get(0).strip().toLowerCase();
        String command = inputList.get(1).strip();
        String args = String.join(" ", inputList.subList(2, inputList.size())).strip();
        return new Command(mark, command, args);
    }

    /**
     * Parses string input from Scanner objects
     * @param input string input from a Scanner line
     * @return a command object that stores the mark, task type (command), and args of tasks from save data
     */
    public static Command parseInputData(String input) {
        List<String> inputList = Arrays.asList(input.split(" "));
        //        System.out.println("inputList: " + inputList);
        String command = inputList.get(0).strip();
        String args = String.join(" ", inputList.subList(1, inputList.size())).strip();
        return new Command("-", command, args);
    }
}
