package processor.task;

import response.Response;

public class Add {
  public static Response process(String prompt) {
    final Task newTask = Task.of("Todo", prompt);
    processor.task.TaskList.addTask(newTask);
    return new Response(java.util.List.of("added: " + newTask));
  }
}
