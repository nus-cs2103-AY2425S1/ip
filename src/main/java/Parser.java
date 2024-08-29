public class Parser {

    public static void parseInput(String input) throws TalkerException {
        String[] parsed = input.split(" ");
        switch (parsed[0]) {
            case "list":
                Talker.listTasks();
                break;
            case "mark":
                Talker.markTaskComplete(parsed);
                break;
            case "unmark":
                Talker.unmarkTaskComplete(parsed);
                break;
            case "delete":
                Talker.deleteTask(parsed);
                break;
            case "todo":
                Talker.createToDo(input);
                break;
            case "deadline":
                Talker.createDeadline(input);
                break;
            case "event":
                Talker.createEvent(input);
                break;
            case "date":
                Talker.printTasksOn(input);
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
