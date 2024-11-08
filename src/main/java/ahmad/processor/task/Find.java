package ahmad.processor.task;

import java.util.Arrays;
import java.util.List;

import ahmad.response.Response;

/**
 * Find processor class.
 */
public class Find {
    /**
     * Returns appropriate response for find command with given prompt.
     *
     * @param prompt Prompt/argument for find command.
     * @return The appropriate response for find command.
     */
    public static Response process(String prompt) {
        final List<String> prompts = Arrays.asList(prompt.split("find "));
        return new Response(TaskList.findTask(prompts.get(1)));
    }
}
