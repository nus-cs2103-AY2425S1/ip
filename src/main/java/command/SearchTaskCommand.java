package command;

import task.TaskList;

public class SearchTaskCommand extends Command {
    private static final String[] SEARCH_CMD_PREFIXES = new String[] {
        "Searching... Got something!",
        "Hang on a second...",
        "Here you go!",
        "Ctrl-F and bam!",
        "Man that'll take me forever to find. Just kidding!"
    };

    private String searchTerm;

    public SearchTaskCommand(String s) {
        super(0, null);
        this.searchTerm = s;
    }

    @Override
    public String execute(TaskList tasks) {
        return generateRandomPrefix(SEARCH_CMD_PREFIXES) + "\n"
                + tasks.search(this.searchTerm).taskListToString();
    }
}
