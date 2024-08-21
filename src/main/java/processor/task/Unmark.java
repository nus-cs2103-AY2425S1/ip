package processor.task;

import exceptions.unmark.UnmarkIndexOutOfBoundsException;
import exceptions.unmark.UnmarkInvalidArgsException;
import exceptions.unmark.UnmarkInvalidNumberException;
import response.Response;

import java.util.Arrays;
import java.util.List;

public class Unmark {
  public static Response process(String prompt) throws UnmarkInvalidArgsException, UnmarkInvalidNumberException, UnmarkIndexOutOfBoundsException {
    final java.util.List<String> prompts = Arrays.asList(prompt.split(" "));

    if (prompts.size() != 2) throw new UnmarkInvalidArgsException();

    try {
      final int idx = Integer.parseInt(prompts.get(1)) - 1;
      TaskList.unmark(idx);
      return new Response(java.util.List.of(TaskList.getSpecificTask(idx)));
    } catch (NumberFormatException e) {
      throw new UnmarkInvalidNumberException(prompts.get(1));
    } catch (IndexOutOfBoundsException e) {
      throw new UnmarkIndexOutOfBoundsException(prompts.get(1));
    }

  }
}
