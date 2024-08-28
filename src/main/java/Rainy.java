import java.util.*;
import java.io.*;

public class Rainy {
    public static void main(String[] args) throws InvalidIndexException, InvalidMarkAndUnmarkException {
        System.out.println("Hello! I am RAINY - Responsive, Automated, Intelligence Network for You.");
        System.out.println("I am a digital assistant designed to help you keep track of your day.");
        System.out.println("So, what can I do for you today?");
        TaskTracker tm = new TaskTracker();
        Scanner sc = new Scanner(System.in);

        try {
            File newFile = new File("src/main/java/Rainy.txt");
            Scanner fileScan = new Scanner(newFile);
            int trace = 0;
            while (fileScan.hasNext()) {
                String oldData = fileScan.nextLine();
                if (trace > 0){
                    if (oldData.charAt(8) == 'T') {
                        tm.updateListToDo(oldData.substring(11));
                    } else if (oldData.charAt(8) == 'D') {
                        String updatedOldData = oldData.substring(11, oldData.length() - 1);
                        String[] deadlineSplit = updatedOldData.split(" \\(");
                        tm.updateListDeadline(deadlineSplit[0] + " ", deadlineSplit[1]);
                    } else {
                        String updatedOldData = oldData.substring(11, oldData.length() - 1);
                        String[] eventSplit = updatedOldData.split(" \\(");
                        System.out.println(eventSplit[0]);
                        System.out.println(eventSplit[1]);
                        tm.updateListEvent(eventSplit[0] + " ", eventSplit[1].split(" from ")[0], eventSplit[1].split(" from ")[1]);
                    }
                    if (oldData.charAt(4) == 'X') {
                        tm.markDone(trace - 1);
                    }
                }
                trace++;
            }
            System.out.println("Use me to track your ToDos/Deadlines/Events!");
        } catch (FileNotFoundException e) {
            System.out.println("Use me to track your ToDos/Deadlines/Events!");
        }
        tm.receivedFirstInput();
        String messages = sc.nextLine();
        String[] input = messages.split(" ");
        String[] splitByTask = messages.split("/");
        String message = input[0];
        int count;
        if ((message.equals("mark") || message.equals("unmark") || message.equals("delete")) && input.length == 2) {
            try {
                count = Integer.parseInt(input[1]);
            } catch (Exception e) {
                count = -1;
            }
        } else {
            count = -1;
        }
        Instructions instruction;
        try {
            instruction = Instructions.valueOf(message.toUpperCase());
        } catch(Exception e) {
            instruction = Instructions.INVALID;
        }


        while(instruction != Instructions.BYE) {
            switch (instruction) {
                case LIST:
                    System.out.println(tm.getList());
                    break;
                case MARK:
                    if (tm.getCounter() > 0) {
                        try {
                            File f = new File("src/main/java/Rainy.txt");
                            f.createNewFile();
                            FileWriter fw = new FileWriter("src/main/java/Rainy.txt");
                            fw.write(tm.getList());
                            fw.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (count != -1) {
                        tm.markDone(count - 1);
                    } else {
                        System.out.println("Please indicate the category of your task (ToDo, Deadline, or Event) before providing a description of it.");
                    }
                    break;
                case UNMARK:
                    if (tm.getCounter() > 0) {
                        try {
                            File f = new File("src/main/java/Rainy.txt");
                            f.createNewFile();
                            FileWriter fw = new FileWriter("src/main/java/Rainy.txt");
                            fw.write(tm.getList());
                            fw.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (count != -1) {
                        tm.unmarkDone(count - 1);
                    } else {
                        System.out.println("Please indicate the category of your task (ToDo, Deadline, or Event) before providing a description of it.");
                    }
                    break;
                case DELETE:
                    if (tm.getCounter() > 0) {
                        try {
                            File f = new File("src/main/java/Rainy.txt");
                            f.createNewFile();
                            FileWriter fw = new FileWriter("src/main/java/Rainy.txt");
                            fw.write(tm.getList());
                            fw.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (count != -1) {
                        tm.delete(count - 1);
                    } else {
                        System.out.println("Please indicate the category of your task (ToDo, Deadline, or Event) before providing a description of it.");
                    }
                    break;
                case TODO:
                    if (input.length == 1) {
                        System.out.println("You need to provide a description of your ToDo task, please try again!");
                    } else {
                        tm.updateListToDo(splitByTask[0].substring(5));
                    }
                    break;
                case DEADLINE:
                    if (input.length == 1) {
                        System.out.println("You need to provide a description of your Deadline, please try again!");
                    } else if (splitByTask.length == 1) {
                        System.out.println("Please provide an end date for your Deadline!");
                    } else if (splitByTask.length < 4) {
                        System.out.println("Please input a date in the format MM/DD/YYYY!");
                    }
                    else {
                        tm.updateListDeadline(splitByTask[0].substring(9), "" + splitByTask[3].substring(0, 4) + "-" + splitByTask[2] + "-" + splitByTask[1].substring(3, 5) + " " + splitByTask[3].substring(5, 9));
                    }
                    break;
                case EVENT:
                    if (input.length == 1) {
                        System.out.println("You need to provide a description of your Event, please try again!");
                    } else if (splitByTask.length < 5) {
                        System.out.println("Please provide a proper start time and end time for your Event!");
                    } else {
                        tm.updateListEvent(splitByTask[0].substring(6), splitByTask[3].substring(0, 4) + "-" + splitByTask[2] + "-" + splitByTask[1].substring(3, 5), splitByTask[4]);
                    }
                    break;
                case INVALID:
                    System.out.println("Please indicate the category of your task (ToDo, Deadline, or Event) before providing a description of it.");
                    break;
            }
            messages = sc.nextLine();
            input = messages.split(" ");
            splitByTask = messages.split("/");
            message = input[0];
            if ((message.equals("mark") || message.equals("unmark") || message.equals("delete")) && input.length == 2) {
                try {
                    count = Integer.parseInt(input[1]);
                } catch (Exception e) {
                    count = -1;
                }
            } else {
                count = -1;
            }
            try {
                instruction = Instructions.valueOf(message.toUpperCase());
            } catch (Exception e) {
                instruction = Instructions.INVALID;
            }
        }
        if (tm.getCounter() > 0) {
            try {
                File f = new File("src/main/java/Rainy.txt");
                f.createNewFile();
                FileWriter fw = new FileWriter("src/main/java/Rainy.txt");
                fw.write(tm.getList());
                fw.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }
        System.out.println("Goodbye! Have a nice day ahead!!");

    }
}
