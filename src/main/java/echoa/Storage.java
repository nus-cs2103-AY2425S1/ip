package echoa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * The Storage class encapsulates fields and methods which involves the file
 * the program is retrieving and saving data into.
 */

public class Storage {
    private String filePath;
    private File file;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");


    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * The method creates a LocalDateTime with the given dateString and timeString.
     *
     * @param dateString String representation of date.
     * @param timeString Time representation of date.
     * @return LocalDateTime of the dateString and timeString.
     * @throws DateTimeParseException when dateString and timeString are not in the correct format.
     */
    public static LocalDateTime createDateTime(String dateString, String timeString) throws DateTimeParseException {
        LocalDate date = LocalDate.parse(dateString, DATE_FORMATTER);
        LocalTime time = LocalTime.parse(timeString, TIME_FORMATTER);
        return LocalDateTime.of(date, time);
    }

    /**
     * Sets ups the file to be used.
     * If the filepath exists, the file will be extracted and returned.
     * Else, any necessary folders and files will be created, and returned.
     * Returns the file to input data into.
     *
     * @return File that is going to be read and written into.
     */
    public void setUpFile() {
        File f = new File(filePath);

        File parentDir = f.getParentFile();
        if (!parentDir.exists()) {
            if (!parentDir.mkdirs()) {
                f = null;
            }
        }

        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                f = null;
            }
        }

        this.file = f;
    }

    /**
     * The method loads the relevant tasks into the taskList
     * based on the given file.
     *
     * @param taskList taskList is where information are loaded into.
     * @throws FileNotFoundException when the File is not found.
     */
    public void loadInformation(TaskList taskList) throws FileNotFoundException {

        Scanner fileReader = new Scanner(file);

        while (fileReader.hasNextLine()) {
            String t = fileReader.nextLine();
            String[] content = t.split(" \\| ");

            String type = content[0];
            boolean isDone = content[1].equals("1");
            String description = content[2];

            switch (type) {
            case "T":
                taskList.addTask(new ToDo(description, isDone));
                break;
            case "D":
                String[] dateAndTime = content[3].split(" ", 2);
                String date = dateAndTime[0];
                String time = dateAndTime[1];
                taskList.addTask(new Deadline(description, isDone, createDateTime(date, time)));
                break;
            case "E":
                String[] startDateAndTime = content[3].split(" ", 2);
                String startDate = startDateAndTime[0];
                String startTime = startDateAndTime[1];
                String[] endDateAndTime = content[4].split(" ", 2);
                String endDate = endDateAndTime[0];
                String endTime = endDateAndTime[1];
                taskList.addTask(new Event(description, isDone, createDateTime(startDate, startTime), createDateTime(endDate,  endTime)));
                break;
            }
        }
    }

    /**
     * The method loops through the taskList and updates the given file.
     *
     * @param  taskList TaskList is the reference for which contents are written from.
     * @throws IOException when there are issues writing into the file.
     */
    public void handleChange(TaskList taskList) throws IOException {
        FileWriter fw1 = new FileWriter(file);

        if (taskList.getSize() == 0) {
            return;
        }

        fw1.write(taskList.getSpecificTask(0).toText() + System.lineSeparator());
        fw1.close();

        FileWriter fw2 = new FileWriter(file, true);
        for (int i = 1; i < taskList.getSize(); i++) {
            fw2.write(taskList.getSpecificTask(i).toText() + System.lineSeparator());

        }
        fw2.close();
    }
}
