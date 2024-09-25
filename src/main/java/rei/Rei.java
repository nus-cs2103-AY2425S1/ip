package rei;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * Main class for REI bot.
 */
public class Rei {

    private Storage storage;
    private TaskList tasks;

    private static final int MARK_COMMAND_LENGTH = 4;
    private static final int UNMARK_COMMAND_LENGTH = 6;
    private static final int TODO_COMMAND_LENGTH = 4;
    private static final int DEADLINE_COMMAND_LENGTH = 8;
    private static final int EVENT_COMMAND_LENGTH = 5;
    private static final int DELETE_COMMAND_LENGTH = 6;
    private static final int FIND_COMMAND_LENGTH = 4;
    private static final int TAG_COMMAND_LENGTH = 3;
    private static final int UNTAG_COMMAND_LENGTH = 5;
    private static final int VIEWTAGS_COMMAND_LENGTH = 8;


    /**
     * Constructs a REI instance
     * @param filePath where to load the stored data
     */
    public Rei(String filePath) {

        // Adapted from https://github.com/adipanda2002/ip/blob/master/src/main/java/pandabot/main/PandaBot.java
        assert filePath != null : "File path for task storage should not be null.";
        storage = new Storage(filePath);
        TaskList tasks;
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            System.out.println(("Error loading tasks: " + e.getMessage()));
            tasks = new TaskList();
        }
        this.tasks = tasks;
    }


    /**
     * Gets REI's response for a prompt
     * @param prompt
     * @return the desired output or response
     */
    public String getResponse(String prompt) {
        try {
            Parser.Prompt promptType = Parser.parse(prompt);
            String output = "";
            int taskIndex;
            String[] details;
            switch (promptType) {
                case LIST:
                    output = tasks.toString();
                    break;
                case MARK:
                    taskIndex = Integer.parseInt(prompt.substring(MARK_COMMAND_LENGTH).trim());
                    output = tasks.markTask(taskIndex);
                    break;
                case UNMARK:
                    taskIndex = Integer.parseInt(prompt.substring(UNMARK_COMMAND_LENGTH).trim());
                    output = tasks.unmarkTask(taskIndex);
                    break;
                case TODO:
                    output = tasks.addTask(Task.createToDo(prompt.substring(TODO_COMMAND_LENGTH).trim()));
                    break;
                case DEADLINE:
                    output = tasks.addTask(Task.createDeadline(prompt.substring(DEADLINE_COMMAND_LENGTH + 1, prompt.indexOf("/by")), // +1 to remove whitespace
                            LocalDateTime.parse(prompt.substring(prompt.indexOf("/by") + 4)))); // +4 to take the /by information
                    break;
                case EVENT:
                    output = tasks.addTask(Task.createEvent(prompt.substring(EVENT_COMMAND_LENGTH + 1, prompt.indexOf("/from")), // +1 to remove whitespace
                            LocalDateTime.parse(prompt.substring(prompt.indexOf("/from") + 6, prompt.indexOf("/to") - 1)), // +6 and -1 to take the /from information
                            LocalDateTime.parse(prompt.substring(prompt.indexOf("/to") + 4)))); // +4 to take the /to information
                    break;
                case DELETE:
                    taskIndex = Integer.parseInt(prompt.substring(DELETE_COMMAND_LENGTH).trim());
                    output = tasks.deleteTask(taskIndex);
                    break;
                case FIND:
                    String keyword = prompt.substring(FIND_COMMAND_LENGTH).trim();
                    output = tasks.findTasks(keyword);
                    break;
                case ANNYEONG:
                    output = "annyeong";
                    break;
                case TAG:
                    details = prompt.substring(TAG_COMMAND_LENGTH).trim().split(" ");
                    taskIndex = Integer.parseInt(details[0]);
                    List<String> tags = new ArrayList<>();
                    for (int i = 1; i < details.length; i++) {
                        tags.add(details[i]);
                    }
                    output = tasks.addTags(taskIndex, tags);
                    break;
                case UNTAG:
                    details = prompt.substring(UNTAG_COMMAND_LENGTH).trim().split(" ");
                    taskIndex = Integer.parseInt(details[0]);
                    output = tasks.deleteTag(taskIndex, details[1]);
                    break;
                case VIEWTAGS:
                    taskIndex = Integer.parseInt(prompt.substring(VIEWTAGS_COMMAND_LENGTH).trim());
                    output = tasks.viewTags(taskIndex);
                    break;
                default:
                    assert false : "switch-case should never reach this";
                    break;
            }
            storage.save(tasks);
            return output;
        } catch (ReiException e) {
            return e.getMessage();
        }

    }

}
