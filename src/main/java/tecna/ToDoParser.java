package tecna;

import org.json.simple.JSONObject;

public class ToDoParser {
    public ToDo parse(JSONObject jsonObject) throws JsonLoadingException {
        String taskName = (String) jsonObject.get("taskName");

        Boolean isDone = (Boolean) jsonObject.get("isDone");
        if (isDone == null | taskName == null) {
            throw new JsonLoadingException(JsonLoadingExceptionType.TODO_DATA_MISSING);
        }

        return new ToDo(taskName, isDone);

    }
}
