package processor.task;

import response.Response;

import java.util.Arrays;
import java.util.List;

public class Add {
  public static Response todo(String prompt) {
    final List<String> prompts = Arrays.asList(prompt.split("todo "));
    final Task newTask = Task.of("todo", prompts.get(1));
    processor.task.TaskList.addTask(newTask);
    return new Response(java.util.List.of("added: " + newTask));
  }

  public static Response deadline(String prompt) {
    final List<String> prompts = Arrays.asList(prompt.split("deadline "));
    final Task newTask = Task.of("deadline", prompts.get(1));
    processor.task.TaskList.addTask(newTask);
    return new Response(java.util.List.of("added: " + newTask));
  }

  public static Response event(String prompt) {
    final List<String> prompts = Arrays.asList(prompt.split("event "));
    final Task newTask = Task.of("event", prompts.get(1));
    processor.task.TaskList.addTask(newTask);
    return new Response(java.util.List.of("added: " + newTask));
  }
}
