import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Storage {
	private static final String FILE_PATH = "./data/hana.txt";
	private static final Path FILE = Paths.get(FILE_PATH);
	private static final Path DIR = Paths.get("./data");
	private static ArrayList<Task> tasks;

	public Storage(ArrayList<Task> tasks) {
		Storage.tasks = tasks;
	}

	public void load() throws HanaException {
		if(!Files.exists(DIR)) {
			try {
				Files.createDirectories(DIR);
			} catch (IOException e) {
				throw new HanaException("Failed to create the directory for saving task");
			}
		}

		if(!Files.exists(FILE)) {
			try {
				Files.createFile(FILE);
			} catch (IOException e) {
				throw new HanaException("Failed to create the directory for saving task");
			}
		}

		try (BufferedReader br = Files.newBufferedReader(FILE)) {
			String line = br.readLine();
			while (line != null) {
				String[] parts = line.split(" \\| ");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
				Task task;
				switch (parts[0]) {
					case "T":
						task = new ToDo(parts[2]);
						break;
					case "D":
						task = new Deadline(parts[2], LocalDateTime.parse(parts[3], formatter));
						break;
					case "E":
						task = new Event(parts[2], LocalDateTime.parse(parts[3], formatter), LocalDateTime.parse(parts[3], formatter));
						break;
					default:
						System.out.println("Failed to read saved task. File may be corrupted. Skipping line");
						continue;
						// Fallthrough
				}
				task.setDone(parts[1].equals("1"));
				tasks.add(task);
				line = br.readLine();
			}
		} catch (IOException | DateTimeParseException e) {
			System.out.println("Failed to read saved tasks. File may be corrupted.");
		}
	}

	public void save() throws HanaException {
		try (BufferedWriter bw = Files.newBufferedWriter(FILE)) {
			for (Task task : tasks) {
				bw.write(task.fileString());
				bw.newLine();
			}
		} catch (IOException e) {
			throw new HanaException("Failed to save tasks.");
		}
	}
}
