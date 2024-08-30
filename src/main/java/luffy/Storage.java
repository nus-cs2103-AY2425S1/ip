package luffy;

import java.io.*;


public class Storage {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveToFile(TaskList taskList) throws IOException {

        int counter = 1;
        File saveFile = new File(this.filePath);
        FileWriter fileStream = new FileWriter(saveFile);
        BufferedWriter fileInfo = new BufferedWriter(fileStream);

        for (Task task : taskList.getTasks()) {
            fileInfo.write(String.format("%s%n", task.dataToSave()));
        }

        fileInfo.flush();
        fileInfo.close();
    }

    public TaskList loadFromFile() throws FileNotFoundException {

        TaskList loadedTasks = new TaskList();
        BufferedReader fileReader = new BufferedReader(new FileReader(this.filePath));

        try {
            String currentLine = fileReader.readLine();
            while (currentLine != null) {

                String[] taskInfo = currentLine.split("\\|");
                String taskToDo = taskInfo[2].trim();
                boolean isDone = Integer.parseInt(taskInfo[1].trim()) == 1;

                switch (taskInfo[0].trim()) {
                    case "TO-DO":
                        loadedTasks.addTask(new Todo(taskToDo, isDone));
                        break;
                    case "DEADLINE":
                        String deadline = taskInfo[3].trim().substring(4);
                        loadedTasks.addTask(new Deadline(taskToDo, deadline, isDone));
                        break;
                    case "EVENT":
                        String start = taskInfo[3].trim().substring(7);
                        String end = taskInfo[4].trim().substring(5);
                        loadedTasks.addTask(new Event(taskToDo, start, end, isDone));
                        break;
                }
                currentLine = fileReader.readLine();
            }
        } catch (IOException e) {
            System.out.println("File has not content");
        }
        return loadedTasks;
    }
}
