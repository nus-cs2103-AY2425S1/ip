package llama.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Handles saving of tasks into file
 */
public class Storage {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    // CHECKSTYLE.OFF: AbbreviationAsWordInName
    private final String TASK_FILE_PATH = "./data/taskFile.txt";
    private final String TAG_FILE_PATH = "./data/tagFile.txt";
    // CHECKSTYLE.ON: AbbreviationAsWordInName
    private File taskFile;
    private File tagFile;

    /**
     * Loads all tasks from previously saved task file. If saved file not found, creates a new task file to save to.
     *
     * @return TaskList that has all the tasks loaded from previous saved file
     * @throws IOException if there is an error opening existing saved file
     */
    public TaskList loadTasks() throws IOException {
        taskFile = new File(TASK_FILE_PATH);
        File folder = new File("./data");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        taskFile.createNewFile();
        return loadTasksFromFile(taskFile);
    }

    /**
     * Loads all tags from previously saved tag file. If saved file not found, creates a new tag file to save to.
     * @return TagList that has all the tags loaded from previous saved file
     * @throws IOException if there is an error opening existing saved file
     */
    public TagList loadTags() throws IOException {
        tagFile = new File(TAG_FILE_PATH);
        File folder = new File("./data");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        tagFile.createNewFile();
        return loadTagsFromFile(tagFile);
    }

    private TaskList loadTasksFromFile(File file) throws FileNotFoundException {
        Scanner s = new Scanner(file);
        TaskList taskList = new TaskList();

        while (s.hasNext()) {
            String taskString = s.nextLine();
            String[] arr = taskString.split("\\|");

            Task currentLoadedTask = null;
            boolean isDone = arr[1].equals("1");

            if (arr[0].equals("T")) {
                currentLoadedTask = new Todo(arr[2], isDone);
            } else if (arr[0].equals("D")) {
                LocalDateTime endTime = LocalDateTime.parse((arr[3]), FORMATTER);
                currentLoadedTask = new Deadline(arr[2], endTime, isDone);
            } else if (arr[0].equals("E")) {
                LocalDateTime startTime = LocalDateTime.parse(arr[3], FORMATTER);
                LocalDateTime endTime = LocalDateTime.parse(arr[4], FORMATTER);
                currentLoadedTask = new Event(arr[2], startTime, endTime, isDone);
            }

            if (currentLoadedTask != null) {
                taskList.loadTask(currentLoadedTask);
            }
        }
        s.close();
        return taskList;
    }

    /**
     * Saves tasks from TaskList into the file
     *
     * @param taskList tasks to save into file
     * @throws IOException if there is an error loading file
     */
    public void saveTasks(TaskList taskList) throws IOException {
        int numberOfTasks = taskList.getSize();
        BufferedWriter writer = new BufferedWriter(new FileWriter(taskFile));
        for (int i = 0; i < numberOfTasks; i++) {
            writer.write(taskList.getTask(i).getSaveTaskString());
            writer.newLine();
        }
        writer.close();
    }

    private TagList loadTagsFromFile(File file) throws FileNotFoundException {
        Scanner s = new Scanner(file);
        TagList tagList = new TagList();

        while (s.hasNext()) {
            String tagTitle = s.nextLine();
            tagTitle = tagTitle.substring(1, tagTitle.length() - 1);
            tagList.loadTag(tagTitle);
        }

        return tagList;
    }

    /**
     * Saves tags from Tag list into the file
     * @param tagList tags to save into file
     * @throws IOException if there is an error loading file
     */
    public void saveTags(TagList tagList) throws IOException {
        int numberOfTags = tagList.getSize();
        BufferedWriter writer = new BufferedWriter(new FileWriter(tagFile));
        for (int i = 0; i < numberOfTags; i++) {
            writer.write(tagList.getTag(i).toString());
            writer.newLine();
        }
        writer.close();
    }
}
