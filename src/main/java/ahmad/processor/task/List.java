package ahmad.processor.task;

import java.util.Arrays;

import ahmad.exceptions.list.ListInvalidArgsException;
import ahmad.response.Response;

/**
 * List processor class.
 */
public class List {
    /**
     * Returns a response based on the prompt for a list command.
     *
     * @param prompt Prompt/argument for list command.
     * @return Appropriate response for list command.
     * @throws ListInvalidArgsException If the prompt is invalid.
     */
    public static Response process(String prompt) throws ListInvalidArgsException {
        java.util.List<String> prompts = Arrays.asList(prompt.split(" "));

        if (prompts.size() > 2 || (prompts.size() == 2 && !prompts.get(1).equals("sorted"))) {
            throw new ListInvalidArgsException();
        }

        if (prompts.size() > 1 && prompts.get(1).equals("sorted")) {
            return new Response(ahmad.processor.task.TaskList.getStringList(Task::compareTimeAscending));
        }

        return new Response(ahmad.processor.task.TaskList.getStringList());
    }
}
