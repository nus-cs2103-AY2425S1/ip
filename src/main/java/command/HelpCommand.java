package command;

import task.TaskList;

public class HelpCommand extends Command {
    private static String datettimeFormat = "\nDatetimes formatted as follows: dd/mm/yy h[:m]am/pm\n";
    private static String ending = "See user guide for more details: https://dinoman44.github.io/ip";
    private String cmd;

    public HelpCommand() {
        super(0, null);
        this.cmd = "help";
    }

    public HelpCommand(String cmd) {
        super(0, null);
        this.cmd = cmd.toLowerCase();
    }

    @Override
    public String execute(TaskList tasks) {
        switch (cmd) {
        case "todo":
            return "Usage: todo <task>\n" + ending;
        case "deadline":
            return "Usage: deadline <task> /by <datetime>\n" + datettimeFormat + ending;
        case "event":
            return "Usage: event <task> /from <datetime> /to <datetime>\n" + datettimeFormat + ending;
        case "mark":
            return "Usage: mark <taskNumber>\n" + ending;
        case "unmark":
            return "Usage: unmark <taskNumber>\n" + ending;
        case "find":
            return "Usage: find <query>\n" + ending;
        case "update":
            return "Usage: update <taskNumber> [/desc <new description>] [/from <new from datetime] [/to <new to datetime>] [/by <new deadline>]\n" + ending;
        case "delete":
            return "Usage: delete <taskNumber>\n" + ending;
        case "list":
            return "List out all tasks\n" + ending;
        case "bye":
            return "Save tasks and exit\n" + ending;
        default:
            return ending;
        }
    }
}
