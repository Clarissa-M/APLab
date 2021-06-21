import java.io.*;
import java.util.*;
import java.nio.charset.*;

class Student {
	static final int MAX_CHARS = 244;
	static final int SIZE = 256;
	int roll; // ordered 1, 2...
	int age;
	float grade;
	byte[] name;

	Student(String line) {
		String[] tokens = line.split("\t");
		this.roll = Integer.parseInt(tokens[0]);
		this.age = Integer.parseInt(tokens[2]); 
		this.grade = Float.parseFloat(tokens[3]);
		if (tokens[1].length() > MAX_CHARS/2) {
			System.err.println("name can't be greater than 122 characters");
			System.exit(1);
		}
		byte[] name = tokens[1].getBytes(Charset.forName("UTF-8"));
		this.name = new byte[MAX_CHARS];
		System.arraycopy(name, 0, this.name, 0, name.length); 
	}

	Student(RandomAccessFile f, int roll) throws IOException {
		f.seek(SIZE * (roll-1)); // roll is 1 based index
		roll = f.readInt();
		age = f.readInt();
		grade = f.readFloat();
		this.name = new byte[MAX_CHARS];
		f.read(this.name);
	}

	void save(RandomAccessFile f) throws IOException {
		f.writeInt(roll);
		f.writeInt(age);
		f.writeFloat(grade);
		f.write(name);
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(new String(name, StandardCharsets.UTF_8)).append(", ");
		sb.append(roll).append(", ");
		sb.append(age).append(", ");
		sb.append(grade);
		return sb.toString();
	}
}

class StudentRepo {
	static List<Student> loadFromText(String file) throws IOException {
		String line;
		List<Student> students = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			while ((line = br.readLine()) != null)
				students.add(new Student(line));
		}	
		return students;
	}

	static void saveBin(String file, List<Student> students) throws IOException {
		try (RandomAccessFile f = new RandomAccessFile(file, "rw")) { 
			for (Student s: students)
				s.save(f);
		}
	}

	public static void main(String[] args) {
		StudentRepo sr = new StudentRepo();

		try {
			List<Student> students = StudentRepo.loadFromText("data/students_with_roll.txt");
			StudentRepo.saveBin("data/students_with_roll.dat", students); 	

			RandomAccessFile f = new RandomAccessFile("data/students_with_roll.dat", "r");

			System.out.println((new Student(f, 3)).toString());
			System.out.println((new Student(f, 1)).toString());
			
			f.close();
		}
		catch (Exception ex) { ex.printStackTrace(); }
	}
}

