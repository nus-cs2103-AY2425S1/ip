package rei;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Main class for REI bot.
 */
public class Rei {

    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs a REI instance
     * @param filePath where to load the stored data
     */
    public Rei(String filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.load());
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
                    taskIndex = Integer.parseInt(prompt.substring(4).trim());
                    output = tasks.markTask(taskIndex);
                    break;
                case UNMARK:
                    taskIndex = Integer.parseInt(prompt.substring(6).trim());
                    output = tasks.unmarkTask(taskIndex);
                    break;
                case TODO:
                    output = tasks.addTask(Task.createToDo(prompt.substring(5)));
                    break;
                case DEADLINE:
                    output = tasks.addTask(Task.createDeadline(prompt.substring(9, prompt.indexOf("/by")),
                            LocalDateTime.parse(prompt.substring(prompt.indexOf("/by") + 4))));
                    break;
                case EVENT:
                    output = tasks.addTask(Task.createEvent(prompt.substring(6, prompt.indexOf("/from")),
                            LocalDateTime.parse(prompt.substring(prompt.indexOf("/from") + 6, prompt.indexOf("/to") - 1)),
                            LocalDateTime.parse(prompt.substring(prompt.indexOf("/to") + 4))));
                    break;
                case DELETE:
                    taskIndex = Integer.parseInt(prompt.substring(6).trim());
                    output = tasks.deleteTask(taskIndex);
                    break;
                case FIND:
                    String keyword = prompt.substring(5).trim();
                    output = tasks.findTasks(keyword);
                    break;
                case ANNYEONG:
                    output = "annyeong";
                    break;
                case TAG:
                    details = prompt.substring(3).trim().split(" ");
                    taskIndex = Integer.parseInt(details[0]);
                    List<String> tags = new ArrayList<>();
                    for (int i = 1; i < details.length; i++) {
                        tags.add(details[i]);
                    }
                    output = tasks.addTags(taskIndex, tags);
                    break;
                case UNTAG:
                    details = prompt.substring(5).trim().split(" ");
                    taskIndex = Integer.parseInt(details[0]);
                    output = tasks.deleteTag(taskIndex, details[1]);
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
