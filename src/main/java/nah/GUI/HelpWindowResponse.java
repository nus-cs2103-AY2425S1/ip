package nah.GUI;

public class HelpWindowResponse {
    private final static String byeResponse = " That command is for exiting the chatBot Nah";
    private final static String cleanResponse = " That command is for cleaning the storage of Nah";
    private final static String listResponse = " That command is for listing all the tasks in Nah's storage";
    private final static String unknownResponse =
            " Nah do not recognise that. Please type 'help' for more instructions";
    private final static String formatOpening = " The format for this command is:\n";
    private final static String findFormat = formatOpening
            + " Find 'one or more words')";
    private final static String markFormat = formatOpening
            + " Mark 'ordinal number of the task'";
    private final static String unmarkFormat = formatOpening
            + " Unmark 'ordinal number of the task'";
    private final static String deleteFormat = formatOpening
            + " Delete 'ordinal number of the task'";
    private final static String dueOnFormat = formatOpening
            + " DueOn yyyy-mm-dd HHmm";
    private final static String toDoFormat = formatOpening
            + " Todo 'description'";
    private final static String deadLineFormat = formatOpening
            + " Deadline 'description' /by yyyy-mm-dd HHmm";
    private final static String eventFormat = formatOpening
            + "Event 'description' /from yyyy-mm-dd HHmm /to yyyy-mm-dd HHmm";
    private final static String helpLine = " These are Nah's single word command:\n"
            + " 1.Bye : to exit the program\n"
            + " 2.List : to list the tasks in the storage\n"
            + " 3.Clean : to clean the storage\n"
            + " Or type one of these key words to get the corresponding command format:\n"
            + " 1.Find : to find the matching tasks\n"
            + " 2.DueOn : to find the uncompleted tasks that before due\n"
            + " 3.Mark : to mark the corresponding task as done\n"
            + " 4.Unmark : to mark the corresponding task as not done\n"
            + " 5.Delete : to delete the task\n"
            + " 6.Todo : to add a todo task\n"
            + " 7.Deadline : to add a deadline task\n"
            + " 8.Event : to add an event task\n"
            + " Or type 'exit' to close Help Window ^:\n";
    public static String responseTo(String s) {

        switch (s.toLowerCase()) {
        case "exit": {
            return "exit";
        }
        case "bye": {
            return byeResponse;
        }
        case "list": {
            return listResponse;
        }
        case "clean": {
            return cleanResponse;
        }
        case "help": {
            return helpLine;
        }
        case "find": {
            return findFormat;
        }
        case "mark": {
            return markFormat;
        }
        case "unmark": {
            return unmarkFormat;
        }
        case "delete": {
            return deleteFormat;
        }
        case "dueone": {
            return dueOnFormat;
        }
        case "todo": {
            return toDoFormat;
        }
        case "deadline": {
            return deadLineFormat;
        }
        case "event": {
            return eventFormat;
        }
        default:
            return unknownResponse;
        }
    }

}
