package justbot.task;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The TaskMatcher class is responsible for finding tasks that match specific keywords.
 * It iterates through a list of tasks and checks whether their descriptions contain any of the provided keywords.
 */
public class TaskMatcher {

    private TaskList taskList;
    private List<String> keywordList;

    /**
     * Constructs a TaskMatcher object that uses a given TaskList and an array of keywords.
     *
     * @param taskList The TaskList containing the tasks to be matched.
     * @param keywords The array of keywords to be used for matching tasks.
     */
    public TaskMatcher(TaskList taskList, String... keywords) {
        this.taskList = taskList;
        this.keywordList = Arrays.asList(keywords);
    }

    /**
     * Finds and returns a list of tasks that match any of the specified keywords.
     *
     * @return A list of tasks where the task description contains at least one of the provided keywords.
     */
    public List<Task> findMatchingTasks() {
        return taskList.getTasks().stream()
                .filter(this::doesTaskMatchAnyKeyword)
                .collect(Collectors.toList());
    }

    /**
     * Checks if the task description contains any of the specified keywords (case-insensitive).
     *
     * @param task The task to check.
     * @return True if the task description contains any of the keywords, false otherwise.
     */
    public boolean doesTaskMatchAnyKeyword(Task task) {
        String taskDescription = task.getTaskDescription().toLowerCase();
        return keywordList.stream()
                .anyMatch(keyword -> taskDescription.contains(keyword.toLowerCase()));
    }
}
