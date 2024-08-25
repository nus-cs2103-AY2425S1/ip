import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

public class TaskAdapter extends TypeAdapter<Task> {

    @Override
    public Task read(JsonReader reader) throws IOException {
        reader.beginObject();
        String taskType = "";
        String description = "";
        boolean isDone = false;
        String endTime = null;
        String startTime = null;

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
                case "endTime":
                    endTime = reader.nextString();
                    break;
                case "startTime":
                    startTime = reader.nextString();
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
                task = new Deadline(description, endTime, taskType);
                break;
            case "event":
                task = new Event(description, startTime, endTime, taskType);
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
            writer.name("endTime").value(((Deadline) task).getEndTime());
        } else if (task instanceof Event) {
            writer.name("taskType").value("event");
            writer.name("endTime").value(((Event) task).getEndTime());
            writer.name("startTime").value(((Event) task).getStartTime());
        }
        writer.name("description").value(task.getDescription());
        writer.name("isDone").value(task.getIsDone());
        writer.endObject();
    }
}
