import java.io.FileWriter;
import java.io.IOException;

import java.time.format.DateTimeFormatter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * @author Crunchify.com.
 * https://crunchify.com/how-to-write-json-object-to-file-in-java/.
 *
 * An object converts java Task objects into JSON object and write to a json file.
 */
public class TaskWriter {

    private static FileWriter fileWriter;

    @SuppressWarnings("unchecked")
    public void saveTasks(java.util.ArrayList<Task> taskList) {
        try {
            JSONObject taskListObj = new JSONObject();
            JSONArray taskListArr = new JSONArray();

            for (int i = 0; i < taskList.size(); ++i) {
                JSONObject taskObj = new JSONObject();
                Task task = taskList.get(i);
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

            java.io.FileWriter fileWriter = new java.io.FileWriter("src/main/data/tecna1.json");
            fileWriter.write(taskListObj.toJSONString());

            fileWriter.flush();
            fileWriter.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
