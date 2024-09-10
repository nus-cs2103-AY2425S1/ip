package tecna.storage;

import java.util.ArrayList;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.time.format.DateTimeFormatter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import tecna.task.Deadline;
import tecna.task.Event;
import tecna.JsonLoadingException;
import tecna.JsonLoadingExceptionType;
import tecna.parser.DeadlineParser;
import tecna.parser.EventParser;
import tecna.parser.ToDoParser;
import tecna.task.Task;
import tecna.collection.TaskList;
import tecna.task.ToDo;

/**
 * Saves and loads the data generated while the chatbot is running.
 * A <code>filePath</code> is the path to the file saving app data.
 *
 * @author DennieDan.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Parses the tasks data in the tecna.json file into an ArrayList of Task(s).
     * @return an ArrayList of Tasks.
     * @throws IOException when there are problems accessing the data file.
     * @throws JsonLoadingException when there are missing attributes required to fully parse the data.
     * @throws ParseException when there are problem parsing the data file to a JSONObject.
     *
     * @author https://dzone.com/articles/how-can-we-read-a-json-file-in-java.
     */
    public ArrayList<Task> load() throws IOException, JsonLoadingException, ParseException {
        ArrayList<Task> tasks = new ArrayList<>();

        // Code adapted from ChatGPT at 31 Aug 2024 12:39 AM SGT
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath)) {

            if (inputStream == null) {
                throw new FileNotFoundException("File not found: " + filePath);
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            Object o = new JSONParser().parse(reader);
            JSONObject jsonObject = (JSONObject) o;
            JSONArray jsonTasks = (JSONArray) jsonObject.get("taskList");

            if (jsonTasks == null) {
                throw new JsonLoadingException(JsonLoadingExceptionType.TASKLIST_NOT_FOUND);
            }

            for (Object taskObj : jsonTasks) {
                JSONObject rawTask = (JSONObject) taskObj;
                if (rawTask.get("type").equals("todo")) {
                    tasks.add(new ToDoParser().parse(rawTask));
                } else if (rawTask.get("type").equals("deadline")) {
                    tasks.add(new DeadlineParser().parse(rawTask));
                } else if (rawTask.get("type").equals("event")) {
                    tasks.add(new EventParser().parse(rawTask));
                } else {
                    throw new JsonLoadingException(JsonLoadingExceptionType.INVALID_TASK_TYPE);
                }
            }
        }
        return tasks;
    }

    /**
     * Converts java Task objects into JSON object and write to a json file.
     * @param taskList storing the lists of all tasks currently in the app.
     *
     * @author Crunchify.com.
     * https://crunchify.com/how-to-write-json-object-to-file-in-java/.
     */
    @SuppressWarnings("unchecked")
    public void save(TaskList taskList) throws IOException {
        JSONObject taskListObj = new JSONObject();
        JSONArray taskListArr = new JSONArray();
        for (int i = 0; i < taskList.getSize(); ++i) {
            JSONObject taskObj = new JSONObject();
            Task task = taskList.getTask(i);
            if (task instanceof ToDo) {
                taskObj.put("taskName", task.getTaskName());
                taskObj.put("isDone", task.isDone());
            } else if (task instanceof Deadline) {
                taskObj.put("taskName", task.getTaskName());
                taskObj.put("isDone", task.isDone());

                DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                taskObj.put("by", ((Deadline) task).getBy().format(pattern));
            } else if (task instanceof Event) {
                taskObj.put("taskName", task.getTaskName());
                taskObj.put("isDone", task.isDone());

                DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                taskObj.put("from", ((Event) task).getFrom().format(pattern));
                taskObj.put("to", ((Event) task).getTo().format(pattern));
            }
            taskListArr.add(taskObj);
        }
        taskListObj.put("taskList", taskListArr);


        FileWriter fileWriter = new FileWriter(this.filePath);
        BufferedWriter writer = new BufferedWriter(fileWriter);

        writer.write(taskListObj.toJSONString());

        writer.flush();
        writer.close();

    }

}
