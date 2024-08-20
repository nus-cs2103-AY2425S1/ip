package processor.task;

import response.Response;

import java.util.Arrays;
import java.util.List;

public class Mark {
  public static Response process(String prompt) {
    final List<String> prompts = Arrays.asList(prompt.split(" "));

    try {
      final int idx = Integer.parseInt(prompts.get(1)) - 1;
      TaskList.mark(idx);
      return new Response(List.of(TaskList.getSpecificTask(idx)));
    } catch (NumberFormatException e) {
      return new Response(List.of("Invalid number!"));
    } catch (IndexOutOfBoundsException e) {
      return new Response(List.of("Index out of bounds!"));
    }

  }
}
