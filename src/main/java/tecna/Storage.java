package tecna;

import java.util.ArrayList;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.time.format.DateTimeFormatter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Storage {
    private String filePath;
    private static FileWriter fileWriter;

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
     * @author: https://dzone.com/articles/how-can-we-read-a-json-file-in-java
     * Parses the tasks data in the tecna.json file into an ArrayList of tecna.Task(s)
     * @return an ArrayList of Tasks
     */
    public ArrayList<Task> load() {
        try {
            Object o = new JSONParser().parse(new FileReader(filePath));
            JSONObject jsonObject = (JSONObject) o;
            JSONArray jsonTasks = (JSONArray) jsonObject.get("taskList");

            ArrayList<Task> tasks = new ArrayList<>();
            for (Object taskObj : jsonTasks) {
                JSONObject rawTask = (JSONObject) taskObj;
                if (rawTask.get("type").equals("todo")) {
                    tasks.add(new ToDoParser().parse(rawTask));
                } else if (rawTask.get("type").equals("deadline")) {
                    tasks.add(new DeadlineParser().parse(rawTask));
                } else if (rawTask.get("type").equals("event")) {
                    tasks.add(new EventParser().parse(rawTask));
                } else {
                    throw new TaskParseException();
                }
            }

            return tasks;
        } catch (IOException | ParseException | TaskParseException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    /**
     * @author Crunchify.com.
     * https://crunchify.com/how-to-write-json-object-to-file-in-java/.
     *
     * Converts java tecna.Task objects into JSON object and write to a json file.
     * @param taskList
     */
    @SuppressWarnings("unchecked")
    public void save(TaskList taskList) {
        try {
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

            FileWriter fileWriter = new java.io.FileWriter(this.filePath);
            fileWriter.write(taskListObj.toJSONString());

            fileWriter.flush();
            fileWriter.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

}
