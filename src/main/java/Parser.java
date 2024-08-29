public class Parser {

    public static void parseInput(String input, TaskList list, Ui ui) throws TalkerException {
        String[] parsed = input.split(" ");
        switch (parsed[0]) {
            case "list":
                list.listTasks(ui);
                break;
            case "mark":
                list.markTaskComplete(parsed, ui);
                break;
            case "unmark":
                list.unmarkTaskComplete(parsed, ui);
                break;
            case "delete":
                list.deleteTask(parsed, ui);
                break;
            case "todo":
                list.createToDo(input, ui);
                break;
            case "deadline":
                list.createDeadline(input, ui);
                break;
            case "event":
                list.createEvent(input, ui);
                break;
            case "date":
                list.printTasksOn(input, ui);
                break;
            default:
                throw new TalkerException("Unknown command!");
        }
    }

    public static Task parseTaskFromFile(String taskString) throws TalkerException {
        String[] parsed = taskString.split(" \\| ");
        boolean isComplete;

        if (parsed[1].equals("X") || parsed[1].equals(" ")) {
            isComplete = parsed[1].equals("X");
        } else {
            throw new TalkerException("Invalid completion tag, corrupted file detected.");
        }
        switch (parsed[0]) {
        case "T":
            if (parsed.length != 3) {
                throw new TalkerException("Invalid ToDo Task, corrupted file detected.");
            }
            return new ToDo(parsed[2], isComplete);
        case "D":
            if (parsed.length != 4) {
                throw new TalkerException("Invalid Deadline Task, corrupted file detected.");
            }
            return new Deadline(parsed[2], parsed[3], isComplete);
        case "E":
            if (parsed.length != 5) {
                throw new TalkerException("Invalid Event Task, corrupted file detected.");
            }
            return new Event(parsed[2], parsed[3], parsed[4], isComplete);
        default:
            throw new TalkerException("Invalid task type, corrupted file detected.");
        }
    }
}
