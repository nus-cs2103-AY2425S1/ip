import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    String filepath;
    public Storage(String filePath) {
        this.filepath = filePath;
    }
    protected ArrayList<Tasks> load() throws NoFileException{
        File file = new File(filepath);
        ArrayList<Tasks> list = new ArrayList<>();
        if (!file.exists()) {
            throw new NoFileException("");
        } else {
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String task = scanner.nextLine();
                    String[] splitted = task.split("\\|");
                    if(splitted.length < 3 || splitted.length > 6) {
                        continue;
                    }
                    boolean isDone = splitted[1].trim().equals("1");
                    Tasks newTask = null;
                    switch (splitted[0].trim()) {
                        case "T":
                            newTask = new ToDos(splitted[2].trim());
                            if(isDone) {
                                newTask.markDone();
                            }
                            break;
                        case "D":
                            String[]dateTime = splitted[3].split("T");
                            LocalDateTime dates = LocalDateTime.of(LocalDate.parse(dateTime[0].trim()), LocalTime.parse(dateTime[1].trim()));
                            newTask = new Deadlines(splitted[2].trim(), dates);
                            if(isDone) {
                                newTask.markDone();
                            }
                            break;
                        case "E":
                            String[] startDateTime = splitted[3].split("T");
                            String[] endDateTime = splitted[4].split("T");
                            LocalDateTime startDate = LocalDateTime.of(LocalDate.parse(startDateTime[0].trim()), LocalTime.parse(startDateTime[1].trim()));
                            LocalDateTime endDate = LocalDateTime.of(LocalDate.parse(endDateTime[0].trim()), LocalTime.parse(endDateTime[1].trim()));

                            newTask = new Events(splitted[2].trim(), startDate,
                                    endDate);
                            if(isDone) {
                                newTask.markDone();
                            }
                            break;
                        default:
                            break;
                    }
                    list.add(newTask);
                }
            } catch (IOException e) {
                System.out.println("Error has occurred while reading the file");
            }
        }
        return list;
    }

    protected void saveItem(ArrayList<Tasks> list) {
        try (FileWriter writer = new FileWriter("./data/beeboo.txt")) {
            for (Tasks task : list) {
                writer.write(task.saveFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Unable to create file");
        }
    }

        protected static void createFile() {
           Path path = Paths.get("./data");
           if(Files.notExists(path)) {
               try{
                   Files.createDirectories(path);
               } catch (IOException e) {
                   System.out.println("Unable to create directory");
               }
           }
        }
    }

