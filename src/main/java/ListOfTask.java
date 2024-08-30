import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ListOfTask {
    private ArrayList<Task> tasks;

    public ListOfTask() {
        this.tasks = new ArrayList<Task>();
    }

    public void copyFile(BufferedReader file) throws IOException {
        try {
            String line;
            while ((line = file.readLine()) != null) {
                String[] arr = line.split(" \\| ");
                boolean isDone = (arr[1].equals("1"));

                if (arr[0].equals("T")) {
                    this.tasks.add(new ToDoTask(arr[2], isDone));

                } else if (arr[0].equals("D")) {
                    this.tasks.add(new DeadlineTask(arr[2], isDone, LocalDate.parse(arr[3])));

                } else if (arr[0].equals("E")) {
                    String[] timings = arr[4].split("-");
                    String startTime = timings[0];
                    String endTime = timings[1];
                    this.tasks.add(new EventTask(arr[2], isDone, LocalDate.parse(arr[3]), startTime, endTime));
                }
            }
        } finally {
            file.close();
        }
    }

    public int getTotal() {
        return this.tasks.size();
    }

    public Task addToDo(String description, FileWriter file) {
        try {
            Task task = new ToDoTask(description, false);
            this.tasks.add(task);

            String text = "T | 0 | " + description + "\n";
            file.append(text);
            file.flush();
            return task;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Task addDeadline(String description, LocalDate date, FileWriter file) {
        try {
            Task task = new DeadlineTask(description, false, date);
            this.tasks.add(task);

            String text = "D | 0 | " + description + " | " + date.toString() + "\n";
            file.append(text);
            file.flush();
            return task;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Task addEvent(String description, LocalDate date, String start, String end, FileWriter file) {
        try {
            Task task = new EventTask(description, false, date, start, end);
            this.tasks.add(task);

            String text = "E | 0 | " + description +
                    " | " + date.toString() + " | " + start + "-" + end + "\n";
            file.append(text);
            file.flush();
            return task;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    public Task deleteTask(int i, Path filePath) {
//        try {
//            File taskFile = new File(String.valueOf(filePath));
//            FileWriter overwrittenFile = new FileWriter(taskFile);
//
//            for (int j = 0; j < this.tasks.size(); j++) {
//                if (j == i - 1) {
//                    continue;
//                }
//                Task task = this.tasks.get(j);
//
//                String description = task.description;
//                String status = (task.isDone ? "1" : "0");
//
//                if (task.date == null && task.startTime == null) {
//                    String text = "T | " + status + " | " + description + "\n";
//                    overwrittenFile.write(text);
//                } else if (task.date != null && task.startTime == null) {
//                    LocalDate date = task.date;
//                    String text = "D | " + status + " | " + description +
//                            " | " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + "\n";
//                    overwrittenFile.write(text);
//                } else {
//                    LocalDate date = task.date;
//                    String startTime = task.startTime;
//                    String endTime = task.endTime;
//                    String text = "E | " + status + " | " + description +
//                            " | " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
//                            " | " + startTime + " | " + endTime + "\n";
//                    overwrittenFile.write(text);
//                }
//            }
//
//            overwrittenFile.flush();
//            return this.tasks.remove(i - 1);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    public String markDone(int i, Path filePath) {
//        try {
//            Task markedTask = this.tasks.get(i - 1).markDone();
//            File taskFile = new File(String.valueOf(filePath));
//            FileWriter overwrittenFile = new FileWriter(taskFile);
//
//            for (int j = 0; j < this.tasks.size(); j++) {
//                Task task = this.tasks.get(j);
//
//                String description = task.description;
//                String status = (task.isDone ? "1" : "0");
//
//                if (task.date == null && task.startTime == null) {
//                    String text = "T | " + status + " | " + description + "\n";
//                    overwrittenFile.write(text);
//                } else if (task.date != null && task.startTime == null) {
//                    LocalDate date = task.date;
//                    String text = "D | " + status + " | " + description +
//                            " | " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + "\n";
//                    overwrittenFile.write(text);
//                } else {
//                    LocalDate date = task.date;
//                    String startTime = task.startTime;
//                    String endTime = task.endTime;
//                    String text = "E | " + status + " | " + description +
//                            " | " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
//                            " | " + startTime + " | " + endTime + "\n";
//                    overwrittenFile.write(text);
//                }
//            }
//            overwrittenFile.flush();
//
//            return "     ____________________________________________________________ \n" +
//                    markedTask + "\n" +
//                    "     ____________________________________________________________ \n";
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public String markUndone(int i, Path filePath) {
//        try {
//            Task unmarkedTask = this.tasks.get(i - 1).markUndone();
//            File taskFile = new File(String.valueOf(filePath));
//            FileWriter overwrittenFile = new FileWriter(taskFile);
//
//            for (int j = 0; j < this.tasks.size(); j++) {
//                Task task = this.tasks.get(j);
//
//                String description = task.description;
//                String status = (task.isDone ? "1" : "0");
//
//                if (task.date == null && task.startTime == null) {
//                    String text = "T | " + status + " | " + description + "\n";
//                    overwrittenFile.write(text);
//                } else if (task.date != null && task.startTime == null) {
//                    String date = task.date.toString();
//                    String text = "D | " + status + " | " + description +
//                            " | " + date + "\n";
//                    overwrittenFile.write(text);
//                } else {
//                    String date = task.date.toString();
//                    String startTime = task.startTime;
//                    String endTime = task.endTime;
//                    String text = "E | " + status + " | " + description +
//                            " | " + date + " | " + startTime + " | " + endTime + "\n";
//                    overwrittenFile.write(text);
//                }
//            }
//            overwrittenFile.flush();
//
//            return "     ____________________________________________________________ \n" +
//                    unmarkedTask + "\n" +
//                    "     ____________________________________________________________ \n";
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public String printList() {
//        String output = "     ____________________________________________________________ \n" +
//                "     Here are the tasks in your list: \n";
//        for (int i = 0; i < this.tasks.size(); i++) {
//            int label = i + 1;
//            output += "     " + label + ". " + this.tasks.get(i).printTask() + "\n";
//        }
//
//        return output +
//                "     ____________________________________________________________ \n";
//    }
}
