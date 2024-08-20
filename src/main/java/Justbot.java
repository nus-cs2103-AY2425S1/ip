import java.util.Scanner;

public class Justbot {
    public static void main(String[] args) {
        final String chatbotName = "JustBot";
        Task[] tasks = new Task[100];
        Scanner scanner = new Scanner(System.in);
        int tasksIndex = 0;
        String input = "";

        Commands.botIntro(chatbotName);

        while (!input.equals("bye")) {
            input = scanner.nextLine();
            String command = input.split(" ")[0];

            switch(command) {
                case "bye":
                    System.out.println("------------------------------------------");
                    Commands.bye();
                    return;
                case "list":
                    try {
                        if (tasksIndex == 0) {
                            throw new JustbotException("Hey man you have no tasks in your list!");
                        }
                        Commands.returnTaskList(tasks, tasksIndex);
                    } catch (JustbotException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "mark":
                    try {
                        int markNumber = Integer.parseInt(input.split(" ")[1]);
                        if (markNumber == 0) {
                            throw new JustbotException("Hey man there is no such task!");
                        }
                        Commands.markTask(tasks, markNumber);
                    } catch (JustbotException e) {
                        System.out.println(e.getMessage());
                    } catch (NumberFormatException e) {
                        System.out.println("Hey man please input a number for the task number!");
                    }catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Hey man the format is wrong. Make sure to follow the format:\n"
                                + "mark [task number]");
                    } catch (NullPointerException e) {
                        System.out.println("Hey man there is no such task!");
                    }
                    break;
                case "unmark":
                    try {
                        int unmarkNumber = Integer.parseInt(input.split(" ")[1]);
                        if (unmarkNumber == 0) {
                            throw new JustbotException("Hey man there is no such task!");
                        }
                        Commands.unmarkTask(tasks, unmarkNumber);
                    } catch (JustbotException e) {
                        System.out.println(e.getMessage());
                    } catch (NumberFormatException e) {
                        System.out.println("Hey man please input a number for the task number!");
                    }catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Hey man the format is wrong. Make sure to follow the format:\n"
                                + "unmark [task number]");
                    } catch (NullPointerException e) {
                        System.out.println("Hey man there is no such task!");
                    }
                    break;
                case "deadline":
                    try {
                        String[] splitPartsDeadline = input.split("/by");

                        if (splitPartsDeadline.length < 2) {
                            throw new JustbotException("Hey man you have provided me an invalid format for deadline.\n" +
                                    "Use the format: deadline [description] /by [deadline]");
                        }

                        String commandAndDescriptionDeadline = splitPartsDeadline[0].trim();
                        String by = splitPartsDeadline[1];
                        String deadlineDescription = commandAndDescriptionDeadline.substring(command.length()).trim();

                        if (deadlineDescription.isBlank() && by.isBlank()) {
                            throw new JustbotException("Hey man the description and deadline date cannot be blank!");
                        } else if (deadlineDescription.isBlank()) {
                            throw new JustbotException("Hey man the description cannot be blank!");
                        } else if (by.isBlank()) {
                            throw new JustbotException("Hey man the deadline date cannot be blank!");
                        }
                        Commands.addTask(tasks, tasksIndex, new Deadline(deadlineDescription, by));
                        tasksIndex += 1;
                    } catch (JustbotException e) {
                        System.out.println(e.getMessage());
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Hey man the format is wrong. Make sure to follow the format:\n"
                                + "deadline [description] /by [deadline]");
                    }
                    break;
                case "event":
                    try {
                        String[] splitPartsEvent = input.split("/from");

                        if (splitPartsEvent.length < 2) {
                            throw new JustbotException("Hey man you have provided me an invalid format for event.\n" +
                                    "Use the format: event [description] /from [start] /to [end]");
                        }
                        String commandAndDescriptionEvent = splitPartsEvent[0].trim();
                        String startAndEnd = splitPartsEvent[1].trim();
                        String eventDescription = commandAndDescriptionEvent.substring(command.length()).trim();
                        String eventStart = startAndEnd.split("/to")[0].trim();
                        String eventEnd = startAndEnd.split("/to")[1].trim();

                        if (eventDescription.isBlank() && eventStart.isBlank() && eventEnd.isBlank()) {
                            throw new JustbotException("Hey man the event and start/end cannot be blank!");
                        } else if (eventDescription.isBlank()) {
                            throw new JustbotException("Hey man the description cannot be blank!");
                        } else if (eventStart.isBlank()) {
                            throw new JustbotException("Hey man the start of the event cannot be blank!");
                        } else if (eventEnd.isBlank()) {
                            throw new JustbotException("Hey man the end of the event cannot be blank!");
                        }
                        Commands.addTask(tasks, tasksIndex, new Event(eventDescription, eventStart, eventEnd));
                        tasksIndex += 1;
                    } catch (JustbotException e) {
                        System.out.println(e.getMessage());
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Hey man the format is wrong. Make sure to follow the format:\n"
                                + "event [description] /from [start] /to [end]");
                    }
                    break;
                case "todo":
                    try {
                        String[] splitPartsTodo = input.split(" ", 2);

                        if (splitPartsTodo.length < 2) {
                            throw new JustbotException("Hey man you have provided me an invalid format for todo.\n" +
                                    "Use the format: todo [description]");
                        }

                        String description = splitPartsTodo[1];
                        if (description.isBlank()) {
                            throw new JustbotException("Hey man the description cannot be blank!");
                        }
                        Commands.addTask(tasks, tasksIndex, new Todo(description));
                        tasksIndex += 1;
                    } catch (JustbotException e) {
                        System.out.println(e.getMessage());
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Hey man the format is wrong. Make sure to follow the format:\n"
                                + "event [description] /from [start] /to [end]");
                    }
                    break;
                default:
                    System.out.println("Hey man please provide me with a valid command!");
                    System.out.println("------------------------------------------");
            }
        }
    }
}
