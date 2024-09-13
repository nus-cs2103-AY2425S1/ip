package pochat.bot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import pochat.exceptions.ChatHistoryFileMissingException;
import pochat.tasks.Task;

/**
 * This class takes in a <code>filename</code> in its constructor
 *     and acts as a wrapper class to handle the reading / writing of
 *     information from this file
 */
public class ChatData {
    private final String filename;

    public ChatData(String filename) {
        this.filename = filename;
    }

    /**
     * Loads the chat history as saved in the TaskList into the current
     *     active session of the bot
     * @param chatHistory : <code>TaskList</code> containing the chat history from
     *                    the previous run of the chatbot.
     */
    public void save(TaskList chatHistory) {
        ArrayList<Task> arrayListOfTasks = chatHistory.toList();
        try {
            FileWriter fileWriter = new FileWriter(this.filename, false);
            for (Task task : arrayListOfTasks) {
                try {
                    fileWriter.write(task.toString() + "\n");
                } catch (IOException e) {
                    throw new ChatHistoryFileMissingException();
                }
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            throw new ChatHistoryFileMissingException();
        }
    }

    /**
     * Returns the chat history as stored in the text file in the
     * form of a <code>TaskList</code>
     * @return TaskList of task items in chat data
     */
    public TaskList toTaskList() {
        ArrayList<Task> list = new ArrayList<Task>();
        try {
            FileReader fileReader = new FileReader(this.filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            boolean hasNextLine = true;
            while (hasNextLine) {
                try {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        hasNextLine = false;
                    } else {
                        list.add(Task.of(line));
                    }
                } catch (IOException e) {
                    throw new ChatHistoryFileMissingException();
                }
            }

            fileReader.close();
            return new TaskList(list);
        } catch (IOException e) {
            throw new ChatHistoryFileMissingException();
        }
    }
}
