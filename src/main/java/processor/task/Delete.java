package processor.task;

import response.Response;

import java.util.Arrays;
import java.util.List;

public class Delete {
  public static Response process(String prompt) {
    final List<String> prompts = Arrays.asList(prompt.split("delete "));

    final int idx = Integer.valueOf(prompts.get(1)) - 1;
    final String oldTask = TaskList.getSpecificTask(idx);
    processor.task.TaskList.deleteTask(idx);
    return new Response(java.util.List.of("Got it! I have removed:\n  " + oldTask + "\n" + "You now have " + TaskList.getTaskCount() + " tasks!"));
  }
}
