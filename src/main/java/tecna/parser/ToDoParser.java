package tecna.parser;

import org.json.simple.JSONObject;

import tecna.exception.JsonLoadingException;
import tecna.exception.JsonLoadingExceptionType;
import tecna.task.ToDo;

/**
 * Parses the task of ToDo type from a JSONObject.
 *
 * @author DennieDan.
 */
public class ToDoParser {

    /**
     * Parses a JSONObject into a ToDo.
     * @param jsonObject is a json object with the value of "type" is "todo".
     * @return a ToDo instance.
     * @throws JsonLoadingException when there is error parsing some attributes of ToDo.
     */
    public ToDo parse(JSONObject jsonObject) throws JsonLoadingException {
        String taskName = (String) jsonObject.get("taskName");

        Boolean isDone = (Boolean) jsonObject.get("isDone");
        if (isDone == null | taskName == null) {
            throw new JsonLoadingException(JsonLoadingExceptionType.TODO_DATA_MISSING);
        }

        return new ToDo(taskName, isDone);

    }
}
