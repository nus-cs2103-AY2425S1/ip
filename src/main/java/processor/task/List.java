package processor.task;

import exceptions.list.ListInvalidArgsException;
import response.Response;

public class List {
  public static Response process(String prompt) throws ListInvalidArgsException {
    if (prompt.length() != 4) throw new ListInvalidArgsException();
    return new Response(processor.task.TaskList.getStringList());
  }
}
