package ahmad.processor.task;

import java.util.Arrays;

import ahmad.exceptions.unmark.UnmarkIndexOutOfBoundsException;
import ahmad.exceptions.unmark.UnmarkInvalidArgsException;
import ahmad.exceptions.unmark.UnmarkInvalidNumberException;
import ahmad.response.Response;

/**
 * Unmark processor class.
 */
public class Unmark {
    /**
     * Returns a response based on the prompt for an unmark command.
     *
     * @param prompt Prompt/argument for unmark command.
     * @return Appropriate response for unmark command.
     * @throws UnmarkInvalidArgsException      If prompt/argument is invalid.
     * @throws UnmarkInvalidNumberException    If number/index is invalid.
     * @throws UnmarkIndexOutOfBoundsException If index is out of bounds.
     */
    public static Response process(String prompt) throws UnmarkInvalidArgsException, UnmarkInvalidNumberException,
            UnmarkIndexOutOfBoundsException {
        final java.util.List<String> prompts = Arrays.asList(prompt.split(" "));

        if (prompts.size() != 2) {
            throw new UnmarkInvalidArgsException();
        }

        try {
            final int idx = Integer.parseInt(prompts.get(1)) - 1;
            TaskList.unmark(idx);
            return new Response(java.util.List.of(TaskList.getSpecificTask(idx)), false, true);
        } catch (NumberFormatException e) {
            throw new UnmarkInvalidNumberException(prompts.get(1));
        } catch (IndexOutOfBoundsException e) {
            throw new UnmarkIndexOutOfBoundsException(prompts.get(1));
        }

    }
}
