package processor.task;

import exceptions.todo.TodoEmptyNameException;
import response.Response;

import java.util.Arrays;
import java.util.List;

public class Add {
  private static Response process(Task newTask) {
    processor.task.TaskList.addTask(newTask);
    return new Response(java.util.List.of("Got it! I have added:\n  " + newTask + "\n" + "You now have " + TaskList.getTaskCount() + " tasks!"));
  }

  public static Response todo(String prompt) throws TodoEmptyNameException {
    final List<String> prompts = Arrays.asList(prompt.split("todo "));
    if (prompt.replaceAll("\\s+", "").length() == 4) {
      throw new TodoEmptyNameException();
    }
    final Task newTask = Task.of("todo", prompts.get(1));
    return Add.process(newTask);
  }

  public static Response deadline(String prompt) {
    final List<String> prompts = Arrays.asList(prompt.split("deadline "));
    final Task newTask = Task.of("deadline", prompts.get(1));
    return Add.process(newTask);
  }

  public static Response event(String prompt) {
    final List<String> prompts = Arrays.asList(prompt.split("event "));
    final Task newTask = Task.of("event", prompts.get(1));
    return Add.process(newTask);
  }
}
