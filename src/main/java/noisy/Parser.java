package noisy;

import java.time.LocalDate;

public class Parser {

    public LocalDate parseDate(String date) {
        return LocalDate.parse(date);
    }

    public int parseInput(String input, TaskList taskList) {
        if (input.equals("bye")) {
            return 0;
        }

        if (input.equals("list")) {
            return 1;
        }

        if (input.startsWith("mark")) {
            return 2;
        }


        Task task = null;
        try {
            switch (input.split(" ")[0]) {
                case "todo":
                    if (input.split(" ", 2).length < 2) {
                        throw new NoisyException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    return 3;
                case "noisy.Deadline":
                    return 4;
                case "noisy.Event":
                    return 5;
                case "delete":
                    return 6;
                default:
                    throw new NoisyException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (NoisyException e) {
            System.out.println(e);
        }
        return 7;
    }
}
