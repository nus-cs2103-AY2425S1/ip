package chappy.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import chappy.task.Deadline;
import chappy.task.Event;
import chappy.task.Task;
import chappy.task.Todo;

import java.io.FileWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.lang.NullPointerException;

import java.util.ArrayList;

public class Storage {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns ArrayList of user's saved list of tasks.
     * List of tasks are loaded from File object located at
     * filePath of this Storage object.
     * Does not return if exception thrown when file is missing, empty or
     * parsing empty/bad JSON
     *
     * @return User's saved list of tasks.
     * @throws IOException if File object is missing, empty or JSON object(s)
     * are null/bad.
     */
    public ArrayList<Task> loadFromDisk() throws IOException, ParseException {
        File file = new File(this.filePath);
        ArrayList<Task> userInputArray = new ArrayList<Task>();
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            throw new IOException("File does not exist. Creating file.");
        }
        if (file.length() <= 0) {
            throw new IOException("File is empty.");
        }

        JSONParser jsonParser = new JSONParser();

        FileReader fileReader = new FileReader(filePath);
        JSONArray jsonArray = (JSONArray) jsonParser.parse(fileReader);

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject o = (JSONObject) jsonArray.get(i);
            try {
                Task t;
                if (o.get("type").equals(Parser.Command.TODO.getKeyword())) {
                    t = Todo.fromJson(o);
                    userInputArray.add(t);
                } else if (o.get("type").equals(Parser.Command.DEADLINE.getKeyword())) {
                    t = Deadline.fromJson(o);
                    userInputArray.add(t);
                } else if (o.get("type").equals(Parser.Command.EVENT.getKeyword())) {
                    t = Event.fromJson(o);
                    userInputArray.add(t);
                }
            } catch (NullPointerException e) {
                throw new IOException("Error parsing JSON objects.");
            }


        }
        return userInputArray;
    }

    /**
     * Saves user's list of tasks to file located at
     * filePath of this Storage object.
     * @param userTaskList user's list of tasks.
     * @throws IOException if error when writing to File object.
     */
    public void saveToDisk(ArrayList<Task> userTaskList) throws IOException {
        try {
            JSONArray jsonArray = new JSONArray();
            FileWriter file = new FileWriter(this.filePath);
            for (int i = 0; i < userTaskList.size(); i++) {
                jsonArray.add(userTaskList.get(i).toJson());
            }
            file.write(jsonArray.toJSONString());
            file.close();
        } catch (IOException e) {
            System.out.println("oh SIR! There was an error saving data to the saved file!");
            System.out.println(e.getMessage());
        }
    }

}
