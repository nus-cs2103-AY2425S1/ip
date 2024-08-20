package processor.task;

import response.Response;

public class Add {
  public static Response process(String prompt) {
    final Task newTask = new Task(prompt);
    processor.task.TaskList.addTask(newTask);
    return new Response(java.util.List.of("added: " + newTask));
  }
}
