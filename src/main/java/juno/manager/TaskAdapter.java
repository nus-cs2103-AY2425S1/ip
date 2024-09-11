package juno.manager;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import juno.manager.exception.TaskManagerException;
import juno.task.Deadline;
import juno.task.Event;
import juno.task.Task;
import juno.task.Todo;

/**
 * A class to customise the Gson TypeAdapter for serialising and deserialising Task objects.
 * Required as reading and writing takes in an ArrayList, so this class will help in reading the writing correctly
 * into the task.json file.
 * Handles different Task subclasses (Todo, Deadline, Event) during the JSON read/write process.
 */
public class TaskAdapter extends TypeAdapter<Task> {

    /**
     * Deserialises JSON into a Task object.
     * Identifies the task type and creates the appropriate Task subclass using switch statement.
     *
     * @param reader The JsonReader to read the JSON data from.
     * @return The deserialised Task object.
     * @throws IOException If there is an error reading the JSON data.
     */
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
        case "todo":
            task = new Todo(description, taskType);
            break;
        default:
            throw new RuntimeException();
        }

        if (task != null && isDone) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Serialises a Task object into JSON.
     *
     * @param writer The JsonWriter to write the JSON data to.
     * @param task   The Task object to be serialised.
     * @throws IOException If there is an error writing the JSON data.
     */
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
        } else {
            throw new RuntimeException();
        }
        writer.name("description").value(task.getDescription());
        writer.name("isDone").value(task.getIsDone());
        writer.endObject();
    }
}
