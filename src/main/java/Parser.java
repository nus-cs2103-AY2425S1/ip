import java.util.Optional;

public class Parser {
    public static Optional<Command> parse(String fullCommand) {
        String[] commandArray = fullCommand.split(" ", 2);
        try {
            switch (commandArray[0]) {
                case "list":
                    return Optional.of(new ListCommand());
                case "todo":
                    return Optional.of(new AddCommand(CommandVerb.TODO, commandArray[1]));
                case "deadline":
                    return Optional.of(new AddCommand(CommandVerb.DEADLINE, commandArray[1]));
                case "event":
                    return Optional.of(new AddCommand(CommandVerb.EVENT, commandArray[1]));
                case "mark":
                    return Optional.of(new ModifyCommand(CommandVerb.MARK, commandArray[1]));
                case "unmark":
                    return Optional.of(new ModifyCommand(CommandVerb.UNMARK, commandArray[1]));
                case "delete":
                    return Optional.of(new ModifyCommand(CommandVerb.DELETE, commandArray[1]));
                case "bye":
                    return Optional.of(new ByeCommand());
                default:
                    throw new InstructionInvalid();
            }
        } catch (InstructionInvalid e) {
            System.out.println("Invalid Instruction. Only valid Instructions are list, todo, deadline, event, mark, unmark, delete");
            return Optional.empty();
        }
    }
}

/*
            Task taskToAdd;
            try {
                FileWriter taskListWriter;
                switch (commandArray[0]) {
                    case "list":
                        String listContents = printList(taskList, taskList.size());
                        System.out.println(horiLine + "\n" + listContents + horiLine);
                        break;
                    case "todo":
                        taskToAdd = new Todo(commandArray[1]);
                        taskList.add(taskToAdd);
                        System.out.println(horiLine + "\nGot it. I've added this task:\n" + taskToAdd + "\n Now you have " + taskList.size() + " tasks in the list.\n" + horiLine);
                        taskListWriter = new FileWriter("./savedTaskList.txt", true);
                        taskListWriter.write("\n" + taskToAdd.saveToFileFormat());
                        taskListWriter.close();
                        break;
                    case "deadline":
                        String[] deadlineArray = commandArray[1].split(" /by ");
                        try {
                            taskToAdd = new Deadline(deadlineArray[0], deadlineArray[1]);
                            taskList.add(taskToAdd);
                            System.out.println(horiLine + "\nGot it. I've added this task:\n" + taskToAdd + "\n Now you have " + taskList.size() + " tasks in the list.\n" + horiLine);
                            taskListWriter = new FileWriter("./savedTaskList.txt", true);
                            taskListWriter.write("\n" + taskToAdd.saveToFileFormat());
                            taskListWriter.close();
                            break;
                        } catch (IOException e) {
                            System.out.println("Problems creating an instance of FileWriter");
                        } catch (DateTimeParseException e) {
                            System.out.println("Deadline format is wrong during input. Please follow yyyy-mm-dd format");
                        }
                        break;
                    case "event":
                        try {
                            String[] eventArray = commandArray[1].split(" /from | /to ");
                            taskToAdd = new Event(eventArray[0], eventArray[1], eventArray[2]);
                            taskList.add(taskToAdd);
                            System.out.println(horiLine + "\nGot it. I've added this task:\n" + taskToAdd + "\n Now you have " + taskList.size() + " tasks in the list.\n" + horiLine);
                            taskListWriter = new FileWriter("./savedTaskList.txt", true);
                            taskListWriter.write("\n" + taskToAdd.saveToFileFormat());
                            taskListWriter.close();
                        }  catch (IOException e) {
                            System.out.println("Problems creating an instance of FileWriter");
                        } catch (DateTimeParseException e) {
                            System.out.println("Start & end time format is wrong during input. Please follow yyyy-mm-dd + T + hh:mm format. E.g. 2007-12-04T22:00");
                        }
                        break;
                    case "mark":
                        int taskMarkIndex = Integer.parseInt(commandArray[1]) - 1; // can try
                        taskList.get(taskMarkIndex).markDone(); // need to check if it works
                        System.out.println(horiLine + "\nNice! I've marked this task as done:\n" + taskList.get(taskMarkIndex) + "\n" + horiLine);
                        taskListWriter = new FileWriter("./savedTaskList.txt", false);
                        taskListWriter.write(writeToFile(taskList, taskList.size()));
                        taskListWriter.close();
                        break;
                    case "unmark":
                        int taskUnmarkIndex = Integer.parseInt(commandArray[1]) - 1; // can try
                        taskList.get(taskUnmarkIndex).unmarkDone();
                        System.out.println(horiLine + "\nOK, I've marked this task as not done yet:\n" + taskList.get(taskUnmarkIndex) + "\n" + horiLine);
                        taskListWriter = new FileWriter("./savedTaskList.txt", false);
                        taskListWriter.write(writeToFile(taskList, taskList.size()));
                        taskListWriter.close();
                        break;
                    case "delete":
                        int taskDeleteIndex = Integer.parseInt(commandArray[1]) - 1; // can try
                        Task taskToDelete = taskList.get(taskDeleteIndex);
                        taskList.remove(taskDeleteIndex);
                        System.out.println(horiLine + "\nNoted. I've removed this task:\n" + taskToDelete + "\n Now you have " + taskList.size() + " tasks in the list.\n" + horiLine);
                        taskListWriter = new FileWriter("./savedTaskList.txt", false);
                        taskListWriter.write(writeToFile(taskList, taskList.size()));
                        taskListWriter.close(); // ensure data is written on the spot
                        break;
                    case "bye":


                    default:
                        throw new InstructionInvalid();
                }
            } catch (InstructionInvalid e) {
                System.out.println("Invalid Instruction. Only valid Instructions are list, todo, deadline, event, mark, unmark, delete");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("There's an issue in the instruction format");
            } catch (NullPointerException e) { // only happen in mark and unmark I think due to TaskList dynamic allocation specified
                System.out.println("There aren't so many tasks. Please if the task number is keyed in correctly. To see all tasks, type list");
            } catch (NumberFormatException e) {
                System.out.println("Please state the task number in INTEGER. Definitely not the task name");
            } catch (IOException e) {
                System.out.println("Problems creating an instance of FileWriter");
            }
    }*/

