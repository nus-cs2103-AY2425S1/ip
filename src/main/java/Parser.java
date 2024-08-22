class Parser {
    public static Command parse(String userInput) {
        if (userInput.equalsIgnoreCase("bye")) {
            return friday -> friday.stop();
        } else if (userInput.equalsIgnoreCase("list")) {
            return friday -> friday.listTasks();
        } else if (userInput.startsWith("mark ")) {
            return parseMarkCommand(userInput);
        } else if (userInput.startsWith("unmark ")) {
            return parseUnmarkCommand(userInput);
        } else if (userInput.startsWith("delete ")) {
            return parseDeleteCommand(userInput);
        } else if (userInput.startsWith("todo ")) {
            return parseTodoCommand(userInput);
        } else if (userInput.startsWith("deadline ")) {
            return parseDeadlineCommand(userInput);
        } else if (userInput.startsWith("event ")) {
            return parseEventCommand(userInput);
        } else {
            return friday -> System.out.println(
                    "    ____________________________________________________________\n" +
                            "     OOPS!!! I'm sorry, but I don't understand the command :-(\n" +
                            "    ____________________________________________________________"
            );
        }
    }

    private static Command parseMarkCommand(String userInput) {
        try {
            int taskIndex = Integer.parseInt(userInput.substring(5).trim()) - 1;
            return friday -> friday.markTaskAsDone(taskIndex);
        } catch (NumberFormatException e) {
            return friday -> System.out.println(
                    "    ____________________________________________________________\n" +
                            "     OOPS!!! Invalid input! Please enter a valid task number after 'mark'.\n" +
                            "    ____________________________________________________________"
            );
        }
    }

    private static Command parseUnmarkCommand(String userInput) {
        try {
            int taskIndex = Integer.parseInt(userInput.substring(7).trim()) - 1;
            return friday -> friday.unmarkTaskAsDone(taskIndex);
        } catch (NumberFormatException e) {
            return friday -> System.out.println(
                    "    ____________________________________________________________\n" +
                            "     OOPS!!! Invalid input! Please enter a valid task number after 'unmark'.\n" +
                            "    ____________________________________________________________"
            );
        }
    }

    private static Command parseDeleteCommand(String userInput) {
        try {
            int taskIndex = Integer.parseInt(userInput.substring(7).trim()) - 1;
            return friday -> friday.deleteTask(taskIndex);
        } catch (NumberFormatException e) {
            return friday -> System.out.println(
                    "    ____________________________________________________________\n" +
                            "     OOPS!!! Invalid input! Please enter a valid task number after 'delete'.\n" +
                            "    ____________________________________________________________"
            );
        }
    }

    private static Command parseTodoCommand(String userInput) {
        String description = userInput.substring(5).trim();
        if (description.isEmpty()) {
            return friday -> System.out.println(
                    "    ____________________________________________________________\n" +
                            "     OOPS!!! The description of a todo cannot be empty.\n" +
                            "    ____________________________________________________________"
            );
        }
        return friday -> friday.addTask(new Todo(description));
    }

    private static Command parseDeadlineCommand(String userInput) {
        String[] parts = userInput.substring(9).split(" /by ");
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            return friday -> System.out.println(
                    "    ____________________________________________________________\n" +
                            "     OOPS!!! Invalid deadline format! Use 'deadline <description> /by <time>'.\n" +
                            "    ____________________________________________________________"
            );
        }
        String description = parts[0].trim();
        String by = parts[1].trim();
        return friday -> friday.addTask(new Deadline(description, by));
    }

    private static Command parseEventCommand(String userInput) {
        String[] parts = userInput.substring(6).split(" /from | /to ");
        if (parts.length < 3 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
            return friday -> System.out.println(
                    "    ____________________________________________________________\n" +
                            "     OOPS!!! Invalid event format! Use 'event <description> /from <start time> /to <end time>'.\n" +
                            "    ____________________________________________________________"
            );
        }
        String description = parts[0].trim();
        String from = parts[1].trim();
        String to = parts[2].trim();
        return friday -> friday.addTask(new Event(description, from, to));
    }
}

