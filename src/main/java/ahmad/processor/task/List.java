package ahmad.processor.task;

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
   * @throws ListInvalidArgsException If the prompt is invalid.
   */
  public static Response process(String prompt) throws ListInvalidArgsException {
    if (prompt.length() != 4) throw new ListInvalidArgsException();
    return new Response(ahmad.processor.task.TaskList.getStringList());
  }
}
