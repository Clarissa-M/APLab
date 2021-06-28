import java.io.*;
import java.util.*;

class Record {
	String name;
	int age;
	float grade;

	Record(String line, String delimiter) {
		String[] tokens = line.split(delimiter);
		this.name = tokens[0];
		this.age = Integer.parseInt(tokens[1]); 
		this.grade = Float.parseFloat(tokens[2]); 
	}

	public String toString() { return name + ", " + age + ", " + grade; }
}

class CSVReader {
	String fileName;
	Map<String, Record> rcds;

	CSVReader(String fileName) { this.fileName = fileName; rcds = new HashMap<>(); }

	void load() throws IOException {
		String line;
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			while ((line = br.readLine()) != null) {
				Record rcd = new Record(line, "\t");
				rcds.put(rcd.name, rcd);
			}
		}
	}
	void showRecords() {
		for (Record r: rcds.values())
			System.out.println(r);
	}

	public static void main(String[] args) {
		try {
			CSVReader csvReader = new CSVReader("data/students.csv");
			csvReader.load();
			csvReader.showRecords();
		}
		catch (IOException ex) { ex.printStackTrace(); }		
	}
}
