package ahmad.processor.task;

import ahmad.exceptions.list.ListInvalidArgsException;
import ahmad.response.Response;

public class List {
    public static Response process(String prompt) throws ListInvalidArgsException {
        if (prompt.length() != 4) {
            throw new ListInvalidArgsException();
        }
        return new Response(ahmad.processor.task.TaskList.getStringList());
    }
}
