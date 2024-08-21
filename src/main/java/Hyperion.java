import java.util.ArrayList;
import java.util.Scanner;

public class Hyperion {
    public static void main(String[] args) {
        String solidLine = "_".repeat(50) + "\n";
        String greet1 = "Hello! I'm Hyperion\n";
        String greet2 = "What can I do for you?\n";
        String exit = "Bye. Hope to see you again soon!";
        // initialize an arraylist to store all the tasks
        ArrayList<Task> allUserInputs = new ArrayList<>();
        // greets the user
        System.out.println(solidLine + greet1 + greet2 + solidLine);
        // obtains user input and exits only when user types bye
        String input = "";
        Scanner scannerObj = new Scanner(System.in);
        do {
            try {
                input = scannerObj.nextLine();
                System.out.print(solidLine);
                if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < allUserInputs.size(); i++) {
                        String index = String.format("%d. ", i + 1);
                        System.out.println(index + allUserInputs.get(i).toString());
                    }
                    System.out.print(solidLine);
                } else if (input.startsWith("mark") || input.startsWith("unmark")) {
                    String[] stringsInInput = input.split(" ");
                    if (stringsInInput.length == 1) {
                        throw new EmptyDescriptionException(stringsInInput[0]);
                    }
                    String command = " ";
                    int index = -1;
                    for (int i = 0; i < stringsInInput.length; i++) {
                        if (i == 0) {
                            command = stringsInInput[i];
                        }
                        if (i == 1) {
                            index = Integer.parseInt(stringsInInput[i]) - 1;
                        }
                    }
                    if (command.equals("mark")) {
                        if (index >= 0 && index < allUserInputs.size()) {
                            allUserInputs.get(index).markAsDone();
                            System.out.println(
                                    "Nice! I've marked this task as done:\n " +
                                            allUserInputs.get(index).toString());
                        }
                    }
                    if (command.equals("unmark")) {
                        if (index >= 0 && index < allUserInputs.size()) {
                            allUserInputs.get(index).markAsNotDone();
                            System.out.println(
                                    "OK, I've marked this task as not done yet:\n " +
                                            allUserInputs.get(index).toString());
                        }
                    }
                    System.out.print(solidLine);
                } else {
                    String str1 = "Got it. I've added this task:\n ";
                    String str2 = String.format("Now you have %d tasks in the list", allUserInputs.size() + 1);
                    if (input.startsWith("todo")) {
                        // should do split also
                        String[] inputArray = input.split(" ", 2);
                        if (inputArray.length == 1 && inputArray[0].equals("todo")) {
                            throw new EmptyDescriptionException("todo");
                        }
                        if (inputArray.length == 2) {
                            ToDos current = new ToDos(inputArray[1]);
                            allUserInputs.add(current);
                            System.out.println(str1 + current.toString() + "\n" + str2);
                            System.out.print(solidLine);
                        } else {
                            throw new InvalidSyntaxException("todo");
                        }

                    } else if (input.startsWith("deadline")) {
                        String removeHead = input.substring(8);
                        String[] inputArray = removeHead.split("/by");
                        if (inputArray.length == 1 && inputArray[0].equals("deadline")) {
                            throw new EmptyDescriptionException("deadline");
                        }
                        if (inputArray.length == 2) {
                            Deadlines current = new Deadlines(inputArray[0].trim(), inputArray[1].trim());
                            allUserInputs.add(current);
                            System.out.println(str1 + current.toString() + "\n" + str2);
                            System.out.print(solidLine);
                        } else {
                            throw new InvalidSyntaxException("deadline");
                        }
                    } else if (input.startsWith("event")) {
                        String removeHead = input.substring(5);
                        String[] firstSplit = removeHead.split("/from", 2);
                        if (firstSplit.length == 1 && firstSplit[0].trim().equals("event")) {
                            throw new EmptyDescriptionException("event");
                        }
                        if (firstSplit.length == 2) {
                            String[] secondSplit = firstSplit[1].split("/to", 2);
                            if (secondSplit.length == 2) {
                                String desc = firstSplit[0].trim();
                                String start = secondSplit[0].trim();
                                String end = secondSplit[1].trim();
                                Events current = new Events(desc, start, end);
                                allUserInputs.add(current);
                                System.out.println(str1 + current.toString() + "\n" + str2);
                            } else {
                                throw new InvalidSyntaxException("event");
                            }
                            System.out.print(solidLine);
                        } else {
                            throw new InvalidSyntaxException("event");
                        }
                    } else if (input.startsWith("delete")) {
                        String[] inputArray = input.split(" ", 2);
                        if (inputArray.length == 1) {
                            throw new EmptyDescriptionException("delete");
                        }
                        if (inputArray.length == 2) {
                            int index = Integer.parseInt(inputArray[1]) - 1;
                            String s1 = "Noted. I've removed this task:";
                            String s2 = String.format("Now you have %d tasks in the list", allUserInputs.size() - 1);
                            System.out.println(s1 + "\n" + " " + allUserInputs.get(index).toString() + "\n" + s2);
                            allUserInputs.remove(index);
                        }
                    } else {
                        if (!input.equals("bye")) {
                            throw new CommandNotFoundException(input);
                        }
                    }
                }
            } catch (CommandNotFoundException | CommandFoundButInvalidException e) {
                System.out.println(e.getMessage());
                System.out.print(solidLine);
            } catch (NumberFormatException e) {
                System.out.println("You have typed strings in places that expects numbers");
                System.out.print(solidLine);
            }
        } while (!input.equals("bye"));
        System.out.println(exit + "\n" + solidLine);
    }
}
