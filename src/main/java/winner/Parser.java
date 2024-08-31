package winner;

public class Parser {

    public static void parseInput(String input, TaskList taskList) throws WinnerException {
        if (input.matches("(?i)hi|hello")) {
            Ui.applyTemplate(Ui.hiAgain());

        } else if (input.matches("(?i).*\\b+todo\\b+.*")) {
            String description = input.split("todo", 2)[1].trim().toLowerCase();
            if (description.isEmpty()) {
                throw new WinnerException("""
                        Expected format for adding todo task:
                        todo (task)""");
            }
            String msg = taskList.addToDo(description);
            Ui.applyTemplate(msg);

        } else if (input.matches("(?i).*\\b+deadline\\b+.*")) {
            String[] parts = input.split("(?i)\\b+deadline\\b+ | \\bby\\b");
            if (parts.length != 3 || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
                throw new WinnerException("""
                        Expected format for adding deadline task:
                        deadline (task) by (dd/mm/yyyy) at (time - 24 hour format)""");
            }
            String msg = taskList.addDeadline(parts[1].trim().toLowerCase(), parts[2]);
            Ui.applyTemplate(msg);

        } else if (input.matches("(?i).*\\b+event\\b+.*")) {
            String[] parts = input.split("(?i)\\b+event\\b+ | \\bfrom\\b | \\bto\\b");
            if (parts.length != 4 || parts[1].trim().isEmpty()
                    || parts[2].trim().isEmpty() || parts[3].trim().isEmpty()) {
                throw new WinnerException("""
                        Expected format for adding event task:
                        event (task) from (start) to (end)""");
            }
            String description = parts[1].trim().toLowerCase(); //TaskList
            String start = parts[2].trim().toLowerCase();
            String end = parts[3].trim().toLowerCase();
            String msg = taskList.addEvent(description, start, end);
            Ui.applyTemplate(msg);

        } else if (input.matches("(?i).*\\b+list\\b+.*")) {
            String msg = taskList.listTasks();
            Ui.applyTemplate(msg);

        } else if (input.matches("(?i).*\\b+mark\\b+.*")) {
            String numberString = input.replaceAll("[^0-9]", "");
            if (numberString.isEmpty()) {
                throw new WinnerException("Please input a task number instead.");
            }
            int taskNumber = Integer.parseInt(numberString);
            if (taskNumber < 1 || taskNumber > taskList.getNoOfTasks()) {
                throw new WinnerException("""
                        Oh no! I cannot mark this task as done because the number is invalid.
                        Expected format for marking tasks as done:
                        mark (task number)""");
            }
            String msg = taskList.markTaskAsDone(taskNumber);
            Ui.applyTemplate(msg);

        } else if (input.matches("(?i).*\\b+unmark\\b+.*")) {
            String numberString = input.replaceAll("[^0-9]", "");
            if (numberString.isEmpty()) {
                throw new WinnerException("Please input a task number instead.");
            }
            int taskNumber = Integer.parseInt(numberString);
            if (taskNumber < 1 || taskNumber > taskList.getNoOfTasks()) {
                throw new WinnerException("""
                        Oh no! I cannot unmark this done task because the number is invalid.
                        Expected format for unmarking done tasks:
                        unmark (task number)""");
            }
            String msg = taskList.unmarkDoneTask(taskNumber);
            Ui.applyTemplate(msg);

        } else if (input.matches("(?i).*\\b+delete\\b+.*")) {
            String numberString = input.replaceAll("[^0-9]", "");
            if (numberString.isEmpty()) {
                throw new WinnerException("Please input a task number instead.");
            }
            int taskNumber = Integer.parseInt(numberString);
            if (taskNumber < 1 || taskNumber > taskList.getNoOfTasks()) {
                throw new WinnerException("""
                        Oh no! I cannot remove this task from the list because the number is invalid.
                        Expected format for removing tasks:
                        delete (task number)""");
            }
            String msg = taskList.deleteTask(taskNumber);
            Ui.applyTemplate(msg);

        } else if (input.matches("(?i).*bye.*")) {
            Ui.winnerSaysBye();
        } else {
            throw new WinnerException("""
                    Sorry, I do not know what that means
                    ⡴⠒⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⠉⠳⡆⠀
                    ⣇⠰⠉⢙⡄⠀⠀⣴⠖⢦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣆⠁⠙⡆
                    ⠘⡇⢠⠞⠉⠙⣾⠃⢀⡼⠀⠀⠀⠀⠀⠀⠀⢀⣼⡀⠄⢷⣄⣀⠀⠀⠀⠀⠀⠀⠀⠰⠒⠲⡄⠀⣏⣆⣀⡍
                    ⠀⢠⡏⠀⡤⠒⠃⠀⡜⠀⠀⠀⠀⠀⢀⣴⠾⠛⡁⠀⠀⢀⣈⡉⠙⠳⣤⡀⠀⠀⠀⠘⣆⠀⣇⡼⢋⠀⠀⢱
                    ⠀⠘⣇⠀⠀⠀⠀⠀⡇⠀⠀⠀⠀⡴⢋⡣⠊⡩⠋⠀⠀⠀⠣⡉⠲⣄⠀⠙⢆⠀⠀⠀⣸⠀⢉⠀⢀⠿⠀⢸
                    ⠀⠀⠸⡄⠀⠈⢳⣄⡇⠀⠀⢀⡞⠀⠈⠀⢀⣴⣾⣿⣿⣿⣿⣦⡀⠀⠀⠀⠈⢧⠀⠀⢳⣰⠁⠀⠀⠀⣠⠃
                    ⠀⠀⠀⠘⢄⣀⣸⠃⠀⠀⠀⡸⠀⠀⠀⢠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣆⠀⠀⠀⠈⣇⠀⠀⠙⢄⣀⠤⠚⠁⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀⢠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡄⠀⠀⠀⢹⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡀⠀⠀⢘⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⢰⣿⣿⣿⡿⠛⠁⠀⠉⠛⢿⣿⣿⣿⣧⠀⠀⣼⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡀⣸⣿⣿⠟⠀⠀⠀⠀⠀⠀⠀⢻⣿⣿⣿⡀⢀⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⡇⠹⠿⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢿⡿⠁⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⣤⣞⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢢⣀⣠⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀ ⠀⠀⠀⠀⠀⠀⠀⠙⠲⢤⣀⣀⠀⢀⣀⣀⠤⠒⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀""");
        }
    }
}
