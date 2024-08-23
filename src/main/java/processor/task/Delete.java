package processor.task;

import exceptions.delete.DeleteIndexOutOfBoundsException;
import exceptions.delete.DeleteInvalidArgsException;
import exceptions.delete.DeleteInvalidNumberException;
import response.Response;

import java.util.Arrays;
import java.util.List;

public class Delete {
  public static Response process(String prompt) throws DeleteInvalidNumberException, DeleteIndexOutOfBoundsException, DeleteInvalidArgsException {
    final List<String> prompts = Arrays.asList(prompt.split("delete "));

    if (prompts.size() != 2) throw new DeleteInvalidArgsException();

    try {
      final int idx = Integer.parseInt(prompts.get(1)) - 1;
      final String oldTask = TaskList.getSpecificTask(idx);
      processor.task.TaskList.deleteTask(idx);
      return new Response(java.util.List.of("Got it! I have removed:\n  " + oldTask + "\n" + "You now have " + TaskList.getTaskCount() + " tasks!"));
    } catch (NumberFormatException e) {
      throw new DeleteInvalidNumberException(prompts.get(1));
    } catch (IndexOutOfBoundsException e) {
      throw new DeleteIndexOutOfBoundsException(prompts.get(1));
    }
  }
}
