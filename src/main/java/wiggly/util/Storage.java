package wiggly.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import wiggly.exception.WigglyException;
import wiggly.task.Task;
import wiggly.task.TaskList;

public class Storage {
	private final File file;


	public Storage(String filePath) {
		this.file = new File(filePath);
	}

	public void save(TaskList taskList) {
		try {
			FileWriter fw = new FileWriter(file);
			String output = taskList.toFileFormat();
			fw.write(output);
			fw.flush();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	public ArrayList<Task> load() throws WigglyException, FileNotFoundException {
		ArrayList<Task> tasks = new ArrayList<>();

		if (file.exists()) {
			Scanner s = new Scanner(file);
			int count = 0;

			while (s.hasNextLine()) {
				String line = s.nextLine();

				try {
					tasks.add(Task.createFromData(line));
				} catch (IllegalArgumentException e) {
					throw new IllegalArgumentException(e.getMessage() + " at line " + count);
				} finally {
					count++;
				}
			}
		} else {
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}

		return tasks;
	}

}
