package ahmad.processor;

import ahmad.exceptions.bye.ByeInvalidArgsException;
import ahmad.response.Response;

import java.util.List;

/**
 * Exit processor class.
 */
public class Exit {
  /**
   * Returns a resposne based on the prompt for an exit command.
   *
   * @param prompt Prompt/argument for exit command.
   * @return Appropriate response for exit command.
   * @throws ByeInvalidArgsException If prompt/argument is invalid/non-empty.
   */
  public static Response process(String prompt) throws ByeInvalidArgsException {
    if (prompt.length() != 3) throw new ByeInvalidArgsException();
    return new Response(List.of("Good Bye! See you soon..."), true);
  }
}
