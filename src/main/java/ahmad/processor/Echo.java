package ahmad.processor;

import java.util.List;

import ahmad.response.Response;

/**
 * Echo processor class.
 */
public class Echo {
    /**
     * Returns a response, which is just an echo of the prompt.
     *
     * @param prompt The prompt given for the command.
     * @return An echo of the prompt.
     */
    public static Response process(String prompt) {
        return new Response(List.of(prompt));
    }
}
