package processor.task;

import response.Response;

public class List {
  public static Response process(String prompt) {
    return new Response(processor.task.TaskList.getStringList());
  }
}
