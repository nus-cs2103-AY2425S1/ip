public class Parser {
    public static Command parse(String command) throws BlitzException {
        if (command.equals("list")) {
            return new CommandList(command);
        } else {
            String[] cont = command.split(" ", 2);
            String inst = cont[0];

            if (!Instruction.checkCommandExist(inst)) {
                throw new BlitzCommandDoesNotExistException();
            }

            if (cont.length == 1 || cont[1].isBlank()) {
                throw new BlitzNoParameterException();
            }

            switch (inst) {
            case "mark":
                String[] markParam = cont[1].split(" ");
                if (markParam.length > 1) {
                    throw new BlitzInvalidParameterMoreThanOneException("mark" + " [Integer]");
                }
                return new CommandMark(command, cont[1]);
            case "unmark":
                String[] unmarkParam = cont[1].split(" ");
                if (unmarkParam.length > 1) {
                    throw new BlitzInvalidParameterMoreThanOneException("unmark" + " [Integer]");
                }
                return new CommandUnmark(command, cont[1]);
//            case "todo":
//                commandTodo(cont[1]);
//                break;
//            case "deadline":
//                commandDeadline(cont[1]);
//                break;
//            case "event":
//                commandEvent(cont[1]);
//                break;
//            case "delete":
//                commandDelete(cont[1]);
//                break;
            default:
                throw new BlitzCommandDoesNotExistException();
            }
        }
    }
}
