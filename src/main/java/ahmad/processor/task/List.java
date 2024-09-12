package ahmad.processor.task;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;

import ahmad.exceptions.list.ListInvalidArgsException;
import ahmad.response.Response;

/**
 * List processor class.
 */
public class List {
    /**
     * Returns a response based on the prompt for a list command.
     *
     * @param prompt Prompt/argument for list command.
     * @return Appropriate response for list command.
     * @throws ListInvalidArgsException If the prompt is invalid.
     */
    public static Response process(String prompt) throws ListInvalidArgsException {
        java.util.List<String> prompts = Arrays.asList(prompt.split(" "));


        if (prompts.size() >= 2) {
            try {
                java.util.ArrayList<Function<java.util.List<Task>, java.util.List<Task>>> processors =
                        new ArrayList<>(prompts.subList(1, prompts.size()).stream()
                                                .<Function<java.util.List<Task>,
                                                        java.util.List<Task>>>map(arg -> {
                                                    switch (arg) {
                                                    case "sorted":
                                                        return TaskList::sortAscendingTime;
                                                    case "deadline":
                                                        return TaskList.filterList(TaskType.Deadline);
                                                    default:
                                                        throw new UncheckedIOException(new IOException());
                                                    }
                                                }).toList());

                return new Response(ahmad.processor.task.TaskList.getStringList(
                        processors));
            } catch (UncheckedIOException e) {
                throw new ListInvalidArgsException();
            }
//            prompts.subList(1, prompts.size()).stream().reduce(new ArrayList<Function<java.util.List<Task>,
//                    java.util.List<Task>>>(), (acc, arg) -> {
//                switch (prompts.get(1)) {
//                case "sorted":
//                    return new ahmad.processor.task.TaskList.getStringList(TaskList::sortAscendingTime));
//                case "deadline":
//                    if (prompts.size() == 3 && prompts.get(2).equals("sorted"))
//                        return new Response(ahmad.processor.task.TaskList.getStringList(TaskList.filterList(TaskType.Deadline),
//                                TaskList::sortAscendingTime));
//                default:
//                    throw new ListInvalidArgsException();
//                }
//            });
        }

        return new Response(ahmad.processor.task.TaskList.getStringList());
    }
}
