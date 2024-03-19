package JavaProject;
import java.io.*;
import java.util.Scanner;

public class SampleTest {
    private String id;

    public SampleTest(String id) {
        this.id = id;
    }

    public void writeDataToTeachersFile() {
        String writeFileName = "/Users/riteshsharma/Desktop/Teacher Details.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(writeFileName))) {

            Scanner inputScanner = new Scanner(System.in); // Scanner for user input
            System.out.println("Enter details for Teacher:");
            System.out.print("First Name: ");
            String firstName = inputScanner.nextLine();
            System.out.print("Last Name: ");
            String lastName = inputScanner.nextLine();
            System.out.print("Age: ");
            int age = Integer.parseInt(inputScanner.nextLine());
            System.out.print("Gender: ");
            String gender = inputScanner.nextLine();
            System.out.print("Address: ");
            String address = inputScanner.nextLine();
            System.out.print("Department ID: ");
            String departmentId = inputScanner.nextLine();
            //System.out.println(departmentIdExists(reader, departmentId));

            if (departmentIdExists(reader, departmentId)) {
                throw new IllegalArgumentException("Department ID already exists.");
            }

            // Now, we'll call another method to write the data to the file
         writeTeacherToFile(writeFileName, firstName, lastName, age, gender, address, departmentId);

        } catch (IOException e) {
            System.out.println("Error reading from the file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing integer: " + e.getMessage());
        }
    }

    public boolean departmentIdExists(BufferedReader reader, String departmentId) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println("Line read from file: " + line);
            String[] parts = line.split(",");
            if (parts.length >= 7) {
                String idFromFile = parts[6].substring(parts[6].indexOf(":") + 1); // Assuming department ID is at index 5
                System.out.println("ID from file: " + idFromFile);
                if (idFromFile.equals(departmentId)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void writeTeacherToFile(String fileName, String firstName, String lastName, int age, String gender, String address, String departmentId) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Specialty: ");
            String specialty = scanner.nextLine();
            System.out.print("Degree: ");
            String degree = scanner.nextLine();
            System.out.print("Is Full Time (true/false): ");
            boolean isFullTime = Boolean.parseBoolean(scanner.nextLine());

            Teacher teacher = new Teacher(firstName, lastName, age, address, gender, departmentId, specialty, degree, isFullTime);
            System.out.println(teacher.toString());
            writer.write(teacher.toString());
            writer.newLine();

            System.out.println("New Data has been added to the file.");
        } catch (IOException e) {
            System.out.println("Error writing to the file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SampleTest st = new SampleTest("IT");
        st.writeDataToTeachersFile();
    }
}
