import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class BottleOpener {
    public static void main(String[] args) {
        String spacer = "-----------------------------------\n";
        String botName = "BottleOpener";
        String greeting = "Hello! I'm " + botName + ".\n" + "What can I do for you?\n";
        String goodbye = "Bye! See you next time!\n";

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(spacer + greeting + spacer);

        int index = 0;
        ArrayList<Task> tasks = new ArrayList<Task>();
        boolean flag = true;

        while (flag) {
            String inp = "";

            try {
                inp = br.readLine();
            } catch (IOException e) {
                System.out.println(spacer + "Invalid entry!\n" + spacer);
            }

            String[] userInput = inp.split(" ", 2);

            String instruction = userInput[0].toLowerCase();

            switch (instruction) {
                case "bye":
                    flag = false;
                    System.out.println(spacer + goodbye + spacer);
                    break;
                case "list":
                    StringBuilder output = new StringBuilder();
                    for (int i = 0; i < tasks.size(); i++) {
                        output.append(String.format("%d. %s%n", i + 1, tasks.get(i)));
                    }
                    System.out.println(spacer + output + spacer);
                    break;
                case "mark":
                    try {
                        int num = Integer.parseInt(userInput[1]);
                        Task mod = tasks.get(num - 1).markAsDone();
                        tasks.set(num - 1, mod);
                        System.out.println(spacer + "Good job completing it!\n" + spacer);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(spacer + "Please tell me which task to mark!\n" + spacer);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(spacer + "Please provide an appropriate number!\n" + spacer);
                    } catch (NumberFormatException e) {
                        System.out.println(spacer + "Please provide an appropriate index!\n" + spacer);
                    }
                    break;
                case "unmark":
                    try {
                        int num = Integer.parseInt(userInput[1]);
                        Task mod = tasks.get(num - 1).markAsUndone();
                        tasks.set(num - 1, mod);
                        System.out.println(spacer + "I have added it back to the list!\n" + spacer);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(spacer + "Please tell me which task to mark!\n" + spacer);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(spacer + "Please provide an appropriate number!\n" + spacer);
                    } catch (NumberFormatException e) {
                        System.out.println(spacer + "Please provide an appropriate index!\n" + spacer);
                    }
                    break;
                default:
                    try {
                        String des = userInput[1];
                        switch (instruction) {
                            case "todo":
                                Task newTodo = new ToDo(des);
                                tasks.add(index, newTodo);
                                index++;
                                System.out.println(spacer + String.format("added: %s%n", newTodo) + spacer);
                                break;
                            case "deadline":
                                try {
                                    String[] activity = des.split(" /", 2);
                                    String action = activity[0];
                                    String due = activity[1];
                                    Task newDeadline = new Deadline(action, due);
                                    tasks.add(index, newDeadline);
                                    index++;
                                    System.out.println(spacer + String.format("added: %s%n", newDeadline) + spacer);
                                } catch (ArrayIndexOutOfBoundsException e) {
                                    System.out.println(spacer + "Please provide a deadline!\n" + spacer);
                                }
                                break;
                            case "event":
                                try {
                                    String[] activity = des.split(" /", 3);
                                    String action = activity[0];
                                    String start = activity[1];
                                    String end = activity[2];
                                    Task newEvent = new Event(action, start, end);
                                    tasks.add(index, newEvent);
                                    index++;
                                    System.out.println(spacer + String.format("added: %s%n", newEvent) + spacer);
                                } catch (ArrayIndexOutOfBoundsException e) {
                                    System.out.println(spacer + "Please provide start and end time!\n" + spacer);
                                }
                                break;
                            default:
                                System.out.println(spacer + "Invalid command!\n" + spacer);
                                break;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(spacer + "Please add a description!\n" + spacer);
                    }

            }

        }
    }
}
