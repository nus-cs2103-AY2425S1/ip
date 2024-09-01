package ahmad.processor.task;

import java.util.Arrays;
import java.util.List;

import ahmad.exceptions.mark.MarkIndexOutOfBoundsException;
import ahmad.exceptions.mark.MarkInvalidArgsException;
import ahmad.exceptions.mark.MarkInvalidNumberException;
import ahmad.response.Response;

/**
 * Mark processor class.
 */
public class Mark {
    /**
     * Returns a response based on the prompt for a mark command.
     *
     * @param prompt Prompt/argument for mark command.
     * @return Appropriate response for mark command.
     * @throws MarkInvalidArgsException      If prompt/argument is invalid.
     * @throws MarkInvalidNumberException    If given index is invalid.
     * @throws MarkIndexOutOfBoundsException If given index is out of bounds.
     */
    public static Response process(String prompt) throws MarkInvalidArgsException, MarkInvalidNumberException, MarkIndexOutOfBoundsException {
        final List<String> prompts = Arrays.asList(prompt.split(" "));

        if (prompts.size() != 2) throw new MarkInvalidArgsException();

        try {
            final int idx = Integer.parseInt(prompts.get(1)) - 1;
            TaskList.mark(idx);
            return new Response(List.of(TaskList.getSpecificTask(idx)), false, true);
        } catch (NumberFormatException e) {
            throw new MarkInvalidNumberException(prompts.get(1));
        } catch (IndexOutOfBoundsException e) {
            throw new MarkIndexOutOfBoundsException(prompts.get(1));
        }
    }
}
