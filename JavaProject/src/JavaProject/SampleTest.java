package JavaProject;

import JavaProject.Staff;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SampleTest {
    private String id;
    private List<Teacher> teachers;
    private List<Staff> staff;

    public SampleTest(String id) {
        this.id = id;
        this.teachers = new ArrayList<>();
        this.staff = new ArrayList<>();
    }

    public void loadDepartmentMembers() {
        String filename = "/Users/riteshsharma/Desktop/department.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Line read from file: " + line);
                String[] parts = line.split(",");
                if (parts.length >= 6) { // Ensure minimum required number of elements
                    String firstName = parts[0].substring(parts[0].indexOf(":") + 2);
                    String lastName = parts[1].substring(parts[1].indexOf(":") + 2);
                    int age = Integer.parseInt(parts[2].substring(parts[2].indexOf(":") + 2));
                    String address = parts[3].substring(parts[3].indexOf(":") + 2);
                    String gender = parts[4].substring(parts[4].indexOf(":") + 2);
                    String departmentId = parts[5].substring(parts[5].indexOf(":") + 2);

                    if (parts.length == 9) { // Teacher
                        String specialty = parts[6].substring(parts[6].indexOf(":") + 2);
                        String degree = parts[7].substring(parts[7].indexOf(":") + 2);
                        boolean isFullTime = Boolean.parseBoolean(parts[8].substring(parts[8].indexOf(":") + 2));
                        Teacher teacher = new Teacher(firstName, lastName, age, address, gender, departmentId, specialty, degree, isFullTime);
                        teachers.add(teacher);
                    } else if (parts.length == 8) { // Staff
                        String duty = parts[6].substring(parts[6].indexOf(":") + 2);
                        int workload = Integer.parseInt(parts[7].substring(parts[7].indexOf(":") + 2));
                        Staff staffMember = new Staff(firstName, lastName, age, gender, address, departmentId, duty, workload);
                        staff.add(staffMember);
                    }
                } else {
                    System.out.println("Invalid data format: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing integer: " + e.getMessage());
        }
    }


    public void writeDepartmentMembers() {
        String writeFileName = "/Users/riteshsharma/Desktop/department.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(writeFileName, true));
             Scanner scanner = new Scanner(System.in)) {

            // Accepting input for teacher
            System.out.println("Enter details for Teacher:");
            System.out.print("First Name: ");
            String firstName = scanner.nextLine();
            System.out.print("Last Name: ");
            String lastName = scanner.nextLine();
            System.out.print("Age: ");
            int age = Integer.parseInt(scanner.nextLine());
            System.out.print("Address: ");
            String address = scanner.nextLine();
            System.out.print("Gender: ");
            String gender = scanner.nextLine();
            System.out.print("Department ID: ");
            String departmentId = scanner.nextLine();
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

            // Accepting input for staff
            System.out.println("Enter details for Staff:");
            System.out.print("First Name: ");
            firstName = scanner.nextLine();
            System.out.print("Last Name: ");
            lastName = scanner.nextLine();
            System.out.print("Age: ");
            age = Integer.parseInt(scanner.nextLine());
            System.out.print("Gender: ");
            gender = scanner.nextLine();
            System.out.print("Address: ");
            address = scanner.nextLine();
            System.out.print("Department ID: ");
            departmentId = scanner.nextLine();
            System.out.print("Duty: ");
            String duty = scanner.nextLine();
            System.out.print("Workload: ");
            int workload = Integer.parseInt(scanner.nextLine());

            Staff staff = new Staff(firstName, lastName, age, gender, address, departmentId, duty, workload);
            writer.write(staff.toString());
            writer.newLine();

            System.out.println("New Data has been added to the file.");
        } catch (IOException e) {
            System.out.println("Error writing to the file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing integer: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SampleTest st = new SampleTest("IT");
        st.loadDepartmentMembers();
        st.writeDepartmentMembers();
    }
}
