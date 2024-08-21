package processor.task;

import exceptions.mark.MarkIndexOutOfBoundsException;
import exceptions.mark.MarkInvalidArgsException;
import exceptions.mark.MarkInvalidNumberException;
import response.Response;

import java.util.Arrays;
import java.util.List;

public class Mark {
  public static Response process(String prompt) throws MarkInvalidArgsException, MarkInvalidNumberException, MarkIndexOutOfBoundsException {
    final List<String> prompts = Arrays.asList(prompt.split(" "));

    if (prompts.size() != 2) throw new MarkInvalidArgsException();

    try {
      final int idx = Integer.parseInt(prompts.get(1)) - 1;
      TaskList.mark(idx);
      return new Response(List.of(TaskList.getSpecificTask(idx)));
    } catch (NumberFormatException e) {
      throw new MarkInvalidNumberException(prompts.get(1));
    } catch (IndexOutOfBoundsException e) {
      throw new MarkIndexOutOfBoundsException(prompts.get(1));
    }
  }
}
