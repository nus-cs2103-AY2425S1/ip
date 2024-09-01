package ahmad.processor.task;

import java.util.Arrays;
import java.util.List;

import ahmad.exceptions.delete.DeleteIndexOutOfBoundsException;
import ahmad.exceptions.delete.DeleteInvalidArgsException;
import ahmad.exceptions.delete.DeleteInvalidNumberException;
import ahmad.response.Response;

/**
 * Delete processor class.
 */
public class Delete {
    /**
     * Returns a response based on the prompt for a delete command.
     *
     * @param prompt Prompt/argument for delete command.
     * @return Appropriate response for delete command
     * @throws DeleteInvalidNumberException    If index given is not a number.
     * @throws DeleteIndexOutOfBoundsException If index given is out of bounds.
     * @throws DeleteInvalidArgsException      If argument is invalid.
     */
    public static Response process(String prompt) throws DeleteInvalidNumberException, DeleteIndexOutOfBoundsException, DeleteInvalidArgsException {
        final List<String> prompts = Arrays.asList(prompt.split("delete "));

        if (prompts.size() != 2) throw new DeleteInvalidArgsException();

        try {
            final int idx = Integer.parseInt(prompts.get(1)) - 1;
            final String oldTask = TaskList.getSpecificTask(idx);
            ahmad.processor.task.TaskList.deleteTask(idx);
            return new Response(java.util.List.of("Got it! I have removed:\n  " + oldTask + "\n" + "You now have " + TaskList.getTaskCount() + " tasks!"), false, true);
        } catch (NumberFormatException e) {
            throw new DeleteInvalidNumberException(prompts.get(1));
        } catch (IndexOutOfBoundsException e) {
            throw new DeleteIndexOutOfBoundsException(prompts.get(1));
        }
    }
}
