package ahmad.processor;

import java.util.List;

import ahmad.exceptions.bye.ByeInvalidArgsException;
import ahmad.response.Response;

public class Exit {
    public static Response process(String prompt) throws ByeInvalidArgsException {
        if (prompt.length() != 3) {
            throw new ByeInvalidArgsException();
        }
        return new Response(List.of("Good Bye! See you soon..."), true);
    }
}
