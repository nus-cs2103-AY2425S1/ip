package juno.manager;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import juno.manager.exception.TaskManagerException;
import juno.task.Deadline;
import juno.task.Event;
import juno.task.Task;
import juno.task.Todo;

import java.io.IOException;

public class TaskAdapter extends TypeAdapter<Task> {

    @Override
    public Task read(JsonReader reader) throws IOException {
        reader.beginObject();
        String taskType = "";
        String description = "";
        boolean isDone = false;
        String endTimeString = "";
        String startTimeString = "";

        while (reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "taskType":
                    taskType = reader.nextString();
                    break;
                case "description":
                    description = reader.nextString();
                    break;
                case "isDone":
                    isDone = reader.nextBoolean();
                    break;
                case "endTimeString":
                    endTimeString = reader.nextString();
                    break;
                case "startTimeString":
                    startTimeString = reader.nextString();
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();

        Task task = null;
        switch (taskType) {
            case "todo":
                task = new Todo(description, taskType);
                break;
            case "deadline":
                try {
                    task = new Deadline(description, endTimeString, taskType);
                } catch (TaskManagerException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "event":
                try {
                    task = new Event(description, startTimeString, endTimeString, taskType);
                } catch (TaskManagerException e) {
                    throw new RuntimeException(e);
                }
                break;
        }

        if (task != null && isDone) {
            task.markAsDone();
        }

        return task;
    }

    @Override
    public void write(JsonWriter writer, Task task) throws IOException {
        writer.beginObject();
        if (task instanceof Todo) {
            writer.name("taskType").value("todo");
        } else if (task instanceof Deadline) {
            writer.name("taskType").value("deadline");
            writer.name("endTimeString").value(((Deadline) task).getEndTimeString());
        } else if (task instanceof Event) {
            writer.name("taskType").value("event");
            writer.name("endTimeString").value(((Event) task).getEndTimeString());
            writer.name("startTimeString").value(((Event) task).getStartTimeString());
        }
        writer.name("description").value(task.getDescription());
        writer.name("isDone").value(task.getIsDone());
        writer.endObject();
    }
}
