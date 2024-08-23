import java.util.Scanner;
public class ChaCha {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        String greeting = "     ____________________________________________________________ \n" +
                "     Hello! I'm ChaCha the ChatBot. :) \n" +
                "     What can I do for you? \n" +
                "     ____________________________________________________________ \n";

        String exit = "     ____________________________________________________________ \n" +
                "     Bye. Hope to talk to you again soon! \n" +
                "     ____________________________________________________________";

        System.out.println(greeting);

        Scanner scanner = new Scanner(System.in);
        ListOfTask list = new ListOfTask();

        while (scanner.hasNextLine()) {
            String cmd = scanner.nextLine();

            if (cmd.equals("bye")) {
                System.out.println(exit);
                break;
            }

            else if (cmd.equals("list")) {
                System.out.println(list.printList());
            }

            else if (cmd.startsWith("todo")) {
                // potential exception when description is empty
                try {
                    if (cmd.length() <= 5) {
                        throw new ChaChaException("     ____________________________________________________________ \n" +
                                "     What task are you intending to add as a \'todo\'? \n" +
                                "     Please type again! \n" +
                                "     ____________________________________________________________ \n");
                    } else {
                        String description = cmd.substring(5);

                        System.out.println("     ____________________________________________________________ \n" +
                                "     Got it. I've added this task:\n" +
                                "       " + list.addToDo(description).printTask() + "\n" +
                                "     Now you have " + list.getTotal() + " tasks in the list.\n" +
                                "     ____________________________________________________________\n");
                    }
                } catch (ChaChaException e) {
                    System.out.println(e.toString());
                }
            }

            else if (cmd.startsWith("deadline")) {
                try {
                    if (cmd.length() <= 9) {
                        throw new ChaChaException("     ____________________________________________________________ \n" +
                                "     You are missing some info needed (task description, deadline). \n" +
                                "     Please type again! \n" +
                                "     ____________________________________________________________\n");
                    }
                    String temp = cmd.substring(9);
                    String[] arr = temp.split(" /");
                    if (arr.length < 2) {
                        // potential exception when arr does not have all elements needed
                        throw new ChaChaException("     ____________________________________________________________ \n" +
                                "     You are missing either the task description or deadline. \n" +
                                "     Please type again! \n" +
                                "     ____________________________________________________________ \n");
                    }
                    String description = arr[0];

                    if (!arr[1].startsWith("by")) {
                        // potential exception when deadline is not inputted well
                        throw new ChaChaException("     ____________________________________________________________ \n" +
                                "     Please type deadline in the form of \'by ...\'. \n" +
                                "     ____________________________________________________________ \n");
                    }
                    String date = arr[1].substring(3);

                    System.out.println("     ____________________________________________________________ \n" +
                            "     Got it. I've added this task:\n" +
                            "       " + list.addDeadline(description, date).printTask() + "\n" +
                            "     Now you have " + list.getTotal() + " tasks in the list.\n" +
                            "     ____________________________________________________________ \n");

                } catch (ChaChaException e) {
                    System.out.println(e.toString());
                }
            }

            else if (cmd.startsWith("event")) {
                try {
                    if (cmd.length() <= 6) {
                        // potential exception when cmd did not include any other info
                        throw new ChaChaException("     ____________________________________________________________ \n" +
                                "     You are missing some info needed (task description, start time, end time). \n" +
                                "     Please type again! \n" +
                                "     ____________________________________________________________ \n");
                    }
                    String temp = cmd.substring(6);

                    String[] arr = temp.split(" /");
                    if (arr.length < 3) {
                        // potential exception when arr does not have all elements needed
                        throw new ChaChaException("     ____________________________________________________________ \n" +
                                "     You are missing either the task description or start time or end time. \n" +
                                "     Please type again! \n" +
                                "     ____________________________________________________________ \n");
                    } else {
                        String description = arr[0];
                        if (!arr[1].startsWith("from")) {
                            // potential exception when start time is not inputted well
                            throw new ChaChaException("     ____________________________________________________________ \n" +
                                    "     Please type start time in the form of \'from ...\'. \n" +
                                    "     ____________________________________________________________ \n");
                        }

                        if (!arr[2].startsWith("to")) {
                            // potential exception when end time is not inputted well
                            throw new ChaChaException("     ____________________________________________________________ \n" +
                                    "     Please type end time in the form of \'to ...\'. \n" +
                                    "     ____________________________________________________________ \n");
                        }

                        String startTime = arr[1].substring(5);
                        String endTime = arr[2].substring(3);
                        System.out.println("     ____________________________________________________________ \n" +
                                "     Got it. I've added this task:\n" +
                                "       " + list.addEvent(description, startTime, endTime).printTask() + "\n" +
                                "     Now you have " + list.getTotal() + " tasks in the list.\n" +
                                "     ____________________________________________________________ \n");
                    }

                } catch (ChaChaException e) {
                    System.out.println(e.toString());
                }
            }

            else if (cmd.startsWith("mark")) {
                try {
                    if (cmd.length() <= 5) {
                        // potential exception when cmd missing index
                        throw new ChaChaException("     ____________________________________________________________ \n" +
                                "     You are missing the index of task you want to mark. \n" +
                                "     Please type again! \n" +
                                "     ____________________________________________________________ \n");
                    }

                    int index = Integer.parseInt(cmd.substring(5));
                    System.out.println(list.markDone(index));
                } catch (IndexOutOfBoundsException e) {
                    // potential exception when index given exceeds total number of tasks
                    System.out.println("     ____________________________________________________________ \n" +
                            "     I can't find such a task number... You don't enough tasks! \n" +
                            "     ____________________________________________________________ \n");
                } catch (NumberFormatException e) {
                    // potential exception when index is not int
                    System.out.println("     ____________________________________________________________ \n" +
                            "     The index does not seem to be a number... Please type again. \n" +
                            "     ____________________________________________________________ \n");
                } catch (ChaChaException e) {
                    System.out.println(e.toString());
                }

            }

            else if (cmd.startsWith("unmark")) {
                try {
                    if (cmd.length() <= 7) {
                        // potential exception when cmd missing index
                        throw new ChaChaException("     ____________________________________________________________ \n" +
                                "     You are missing the index of task you want to unmark. \n" +
                                "     Please type again! \n" +
                                "     ____________________________________________________________ \n");
                    }

                    int index = Integer.parseInt(cmd.substring(7));
                    System.out.println(list.markUndone(index));
                } catch (IndexOutOfBoundsException e) {
                    // potential exception when index given exceeds total number of tasks
                    System.out.println("     ____________________________________________________________ \n" +
                            "     I can't find such a task number... You don't enough tasks!\n" +
                            "     ____________________________________________________________ \n");
                } catch (NumberFormatException e) {
                    // potential exception when index is not int
                    System.out.println("     ____________________________________________________________ \n" +
                            "     The index does not seem to be a number... Please type again. \n" +
                            "     ____________________________________________________________ \n");
                } catch (ChaChaException e) {
                    System.out.println(e.toString());
                }
            }

            else if (cmd.startsWith("delete")) {
                try {
                    if (cmd.length() <= 7) {
                        // potential exception when cmd missing index
                        throw new ChaChaException("     ____________________________________________________________ \n" +
                                "     You are missing the index of task you want to delete. \n" +
                                "     Please type again! \n" +
                                "     ____________________________________________________________\n");
                    }

                    int index = Integer.parseInt(cmd.substring(7));
                    System.out.println("     ____________________________________________________________ \n" +
                            "     Okay! I've removed this task: \n" +
                            "       " + list.deleteTask(index).printTask() + "\n" +
                            "     Now you have " + list.getTotal() + " tasks in the list.\n" +
                            "     ____________________________________________________________ \n");
                } catch (IndexOutOfBoundsException e) {
                    // potential exception when index given exceeds total number of tasks
                    System.out.println("     ____________________________________________________________ \n" +
                            "     I can't find such a task number... You don't enough tasks! \n" +
                            "     ____________________________________________________________\n");
                } catch (NumberFormatException e) {
                    // potential exception when index is not int
                    System.out.println("     ____________________________________________________________ \n" +
                            "     The index does not seem to be a number... Please type again. \n" +
                            "     ____________________________________________________________\n");
                } catch (ChaChaException e) {
                    System.out.println(e.toString());
                }
            }

            else {
                System.out.println("     ____________________________________________________________ \n" +
                        "     Hmmm... I don't seem to understand this. Please input another command! \n" +
                        "     ____________________________________________________________\n");
            }

            scanner = new Scanner(System.in);
        }

    }
}
