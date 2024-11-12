package lemon.command;

/**
 * Enum representation for all possible commands
 * @author He Yiheng
 */
public enum CommandType {
    BYE("Exits and Saves all the tasks into my big brain"),
    LIST("Lists all the task that I have remembered so far"),
    MARK("Takes in the index of the task to be marked"),
    UNMARK("Takes in the index of the task to be unmarked"),
    TODO("Lets me remember a simple todo task"),
    DEADLINE("Lets me remember a task with a deadline\n"
            + " Please specify its deadline with a \"/by\" in the format dd-mm-yyy"),
    EVENT("Lets me remember a task with a duration\n"
            + " Please specify its duration with a \"/from\" and then a \"/to\" in the format dd-mm-yyy"),
    DELETE("Takes in the index of the task to be deleted"),
    FIND("Takes in a certain key word I could try really hard to find for you within your tasks"),
    HELP("I can repeat this message if you want :)");

    private String description;
    private CommandType(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return super.toString() + ":\n " + description + "\n";
    }
}
