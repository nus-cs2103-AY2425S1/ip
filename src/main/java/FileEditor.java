import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileEditor {
	private File file;


	public FileEditor(String filePath) {
		this.file = new File(filePath);
	}

	public void save(TaskList taskList) throws IOException {
		try {
			FileWriter fw = new FileWriter(file);
			String output = taskList.toFileFormat();
			fw.write(output);
			fw.flush();
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		}
	}

}
