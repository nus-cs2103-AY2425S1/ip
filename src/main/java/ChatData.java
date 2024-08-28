import java.io.*;
import java.util.ArrayList;

class ChatData {
    private final String filename;

    public ChatData(String filename) {
        this.filename = filename;
    }

    public void save(ArrayList<Task> chatHistory) {
        try {
            FileWriter fileWriter = new FileWriter(this.filename, false);
            for (Task task : chatHistory) {
                try {
                    fileWriter.write(task.toString() + "\n");
                } catch (IOException e) {
                    throw new ChatHistoryFileMissingException();
                }
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            throw new ChatHistoryFileMissingException();
        }
    }

    public ArrayList<Task> toList() {
        ArrayList<Task> list = new ArrayList<Task>();
        try {
            FileReader fileReader = new FileReader(this.filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (true) {
                try {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    } else {
                        list.add(Task.of(line));
                    }
                } catch (IOException e) {
                    throw new ChatHistoryFileMissingException();
                }
            }
            fileReader.close();
            return list;
        } catch (IOException e) {
            throw new ChatHistoryFileMissingException();
        }
    }
}
