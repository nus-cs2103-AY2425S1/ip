public class Parser {
    private TaskManager taskManager;
    private Ui ui;

    public Parser(Ui ui, TaskManager taskManager) {
        this.ui = ui;
        this.taskManager = taskManager;
    }
    public void parse(TaskManager taskManager) {
        while (true) {
            String input = ui.readCommand();
            String[] items = input.split(" ", 2);
            String command = items[0];

            try {
                switch (command) {
                case "bye":
                    ui.printLines("Bye. Hope to see you again soon!");
                    return;
                case "list":
                    taskManager.listTasks();
                    break;
                case "delete":
                    if (items.length != 2) {
                        throw new CommandFormatException("Aw... delete command must have just 2 arguments: the command, and the task number.");
                    }
                    try {
                        Integer i = Integer.parseInt(items[1]);
                        if (i > 0 && i < taskManager.getTaskSize() + 1) {
                            taskManager.deleteTask(i);
                        } else {
                            throw new NoSuchTaskException("Aw, that task doesn't exist. Try again!");
                        }
                    } catch (NumberFormatException e) {
                        throw new CommandFormatException("Aw... delete command must be followed by an integer");
                    } catch (IndexOutOfBoundsException e) {
                        throw new NoSuchTaskException("Aw, that task doesn't exist. Try again!");
                    } catch (Exception e) {
                        throw new CommandFormatException("Aw... delete command must have just 2 arguments: the command, and the task number.");
                    }
                    break;
                case "mark":
                    if (items.length != 2) {
                        throw new CommandFormatException("Aw... mark command must have just 2 arguments: the command, and the task number.");
                    }
                    try {
                        Integer i = Integer.parseInt(items[1]);
                        if (i > 0 && i <= taskManager.getTaskSize() + 1) {
                            taskManager.completeTask(i);
                        } else {
                            throw new NoSuchTaskException("Aw, that task doesn't exist. Try again!");
                        }
                    } catch (NumberFormatException e) {
                        throw new CommandFormatException("Aw... mark command must be followed by an integer");
                    } catch (IndexOutOfBoundsException e) {
                        throw new NoSuchTaskException("Aw, that task doesn't exist. Try again!");
                    }
                    break;
                case "unmark":
                    if (items.length != 2) {
                        throw new CommandFormatException("Aw... unmark command must have 2 arguments: the command and the task number.");
                    }
                    try {
                        Integer i = Integer.parseInt(items[1]);
                        if (i > 0 && i <= taskManager.getTaskSize() + 1) {
                            taskManager.incompleteTask(i);
                        } else {
                            throw new NoSuchTaskException("Aw, that task doesn't exist. Try again!");
                        }
                    } catch (NumberFormatException e) {
                        throw new CommandFormatException("Aw... unmark command must be followed by an integer");
                    } catch (IndexOutOfBoundsException e) {
                        throw new NoSuchTaskException("Aw, that task doesn't exist. Try again!");
                    }
                    break;
                case "todo":
                    if (items.length != 2) {
                        throw new CommandFormatException("Aw... todo command must contain 2 arguments: todo and the task at hand!");
                    }
                    try {
                        taskManager.addTask(new TodoTask(items[1], false));
                    } catch (Exception e) {
                        throw new CommandFormatException("Aw... todo command must contain 2 arguments: todo and the task at hand!");
                    }
                    break;
                case "deadline":
                    if (items.length != 2) {
                        throw new CommandFormatException("Aw... your deadline command is incomplete. Try this: deadline {task} /by {yyyy-MM-dd HHmm}");
                    }
                    try {
                        if (items[1].contains("/by")) {
                            String[] deadlineString = items[1].split(" /by ", 2);
                            if (deadlineString.length == 2) {
                                String datePattern = "\\d{4}-\\d{2}-\\d{2} \\d{4}";
                                if (deadlineString[1].matches(datePattern)) {
                                    taskManager.addTask(new DeadlineTask(deadlineString[0], deadlineString[1], false));
                                } else {
                                    throw new CommandFormatException("Aw... the date format must be yyyy-MM-dd HHmm");
                                }
                            } else {
                                throw new CommandFormatException("Aw... your deadline command must contain the task, /by, and the deadline.");
                            }
                        } else {
                            throw new CommandFormatException("Aw... your deadline command doesn't have a deadline date set!");
                        }
                    } catch (Exception e) {
                        throw new CommandFormatException("Aw... your deadline command is incorrect. Try this: deadline {task} /by {yyyy-MM-dd HHmm}");
                    }
                    break;
                case "event":
                    if (items.length != 2) {
                        throw new CommandFormatException("Aw your event command is incomplete. Try this: event {event} /from {from} /to {to}");
                    }
                    try {
                        if (items[1].contains("/from")) {
                            String[] eventString = items[1].split(" /from ", 2);
                            if (eventString.length == 2) {
                                String taskString = eventString[0];
                                if (eventString[1].contains("/to")) {
                                    String[] dates = eventString[1].split(" /to ", 2);
                                    if (dates.length == 2) {
                                        String fromDate = dates[0];
                                        String toDate = dates[1];
                                        taskManager.addTask(new EventTask(taskString, fromDate, toDate, false));
                                    } else {
                                        throw new CommandFormatException("Aw... you might be missing a from or to date!");
                                    }
                                } else {
                                    throw new CommandFormatException("Aw... you might be missing the to date!");
                                }
                            } else {
                                throw new CommandFormatException("Aw... you might be missing the task description and /from date!");
                            }
                        } else {
                            throw new CommandFormatException("Aw... you might be missing the /from date!");
                        }
                    } catch (Exception e) {
                        throw new CommandFormatException("Aw... your event command might be incorrect. Try this: event {event} /from {from} /to {to}");
                    }
                    break;
                default:
                    ui.printLines("Sorry bro, no clue what you're saying!");
                    break;
                }
            } catch (NoSuchTaskException | CommandFormatException e) {
                ui.printLines(e.getMessage());
            }
        }
    }
}
