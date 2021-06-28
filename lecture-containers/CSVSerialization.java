import java.io.*;
import java.util.*;

class Record implements Serializable {
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

// A container object type, which we would serialize
class Records implements Serializable { 
	Map<String, Record> rcds;

	Records() {
		rcds = new HashMap<>(); 
	}
	void load(String fileName) throws IOException {
		String line;
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			while ((line = br.readLine()) != null) {
				Record rcd = new Record(line, "\t");
				rcds.put(rcd.name, rcd);
			}
		}
	}

	void save(String outFile) throws IOException {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(outFile))) {
			out.writeObject(this);
		}
	}

	static Records loadSer(String serFile) throws IOException, ClassNotFoundException {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(serFile))) {
			Records rcds = (Records) ois.readObject();
			return rcds;
		}
	}

	void showRecords() {
		for (Record r: rcds.values())
			System.out.println(r);
	}
}

class CSVSerialization {

	public static void main(String[] args) {
		try {
			Records rcds = new Records();
			rcds.load("data/students.csv");
			rcds.showRecords();
			rcds.save("data/students.ser");
			Records loadedRcds = Records.loadSer("data/students.ser");
			loadedRcds.showRecords();
		}
		catch (Exception ex) { ex.printStackTrace(); }		
	}
}
