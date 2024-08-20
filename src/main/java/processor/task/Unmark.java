package processor.task;

import response.Response;

import java.util.Arrays;
import java.util.List;

public class Unmark {
  public static Response process(String prompt) {
    final java.util.List<String> prompts = Arrays.asList(prompt.split(" "));

    try {
      final int idx = Integer.parseInt(prompts.get(1)) - 1;
      TaskList.unmark(idx);
      return new Response(java.util.List.of(TaskList.getSpecificTask(idx)));
    } catch (NumberFormatException e) {
      return new Response(java.util.List.of("Invalid number!"));
    } catch (IndexOutOfBoundsException e) {
      return new Response(List.of("Index out of bounds!"));
    }

  }
}
