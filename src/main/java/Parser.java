public class Parser {
    public Parser() {}

    public String[] processInput(String input) throws NumberFormatException {
        String[] newInput = input.split(" ");
        String[] ignoreInstruction = new String[] {"ignore"};
        String command = newInput[0];
        if (command.equals("bye")) {
            return new String[] {"bye"};
        } else if (command.equals("mark")) {
            try {
                int taskNumber = Integer.parseInt(newInput[1]);
                return new String[] {"mark", String.valueOf(taskNumber)};
            } catch (NumberFormatException e) {
                throw e;
            } 
        } else if (command.equals("unmark")) {
            try {
                int taskNumber = Integer.parseInt(newInput[1]);
                return new String[] {"unmark", String.valueOf(taskNumber)};
            } catch (NumberFormatException e) {
                throw e;
            } 
        } else if (command.equals("delete")) {
            try {
                int taskNumber = Integer.parseInt(newInput[1]);
                return new String[] {"delete", String.valueOf(taskNumber)};
            } catch (NumberFormatException e) {
                throw e;
            } 
        } else if (command.equals("list")) {
            return new String[] {"list"};
        } else {
            return new String[] {"add", input};
        }
    }
}