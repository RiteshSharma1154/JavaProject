package JavaProject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Department {
    private String id;
    private List<Teacher> teachers;
    private List<Staff> staff;

    public Department(String id) {
        this.id = id;
        this.teachers = new ArrayList<>();
        this.staff = new ArrayList<>();
    }
    //Display Teacher Details from the file
    public void loadteacherdetails() {
        String filename = "/Users/riteshsharma/Desktop/Teacher Details.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Line read from file: " + line);
                String[] parts = line.split(",");
                if (parts.length >= 6) { // Ensure minimum required number of elements
                    String firstName = parts[0].substring(parts[0].indexOf(":") + 2);
                    String lastName = parts[1].substring(parts[1].indexOf(":") + 2);
                    int age = Integer.parseInt(parts[2].substring(parts[2].indexOf(":") + 2));
                    String gender = parts[3].substring(parts[3].indexOf(":") + 2);
                    String address = parts[4].substring(parts[4].indexOf(":") + 2);
                    String departmentId = parts[5].substring(parts[5].indexOf(":") + 2);

                    if (parts.length == 9) { // Teacher
                        String specialty = parts[6].substring(parts[6].indexOf(":") + 2);
                        String degree = parts[7].substring(parts[7].indexOf(":") + 2);
                        boolean isFullTime = Boolean.parseBoolean(parts[8].substring(parts[8].indexOf(":") + 2));
                        Teacher teacher = new Teacher(firstName, lastName, age, address, gender, departmentId, specialty, degree, isFullTime);
                        teachers.add(teacher);

                    }
                }
                else
                {
                    System.out.println("Invalid data format: " + line);
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("Error reading the file: " + e.getMessage());
        }
        catch (NumberFormatException e)
        {
            System.out.println("Error parsing integer: " + e.getMessage());
        }
    }



    //Write data to Teacher File
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









    //Display Staff Details from the file
    public void loadstaffdetails() {
        String filename = "/Users/riteshsharma/Desktop/Staff Details.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Line read from file: " + line);
                String[] parts = line.split(",");
                if (parts.length >= 6) { // Ensure minimum required number of elements
                    String firstName = parts[0].substring(parts[0].indexOf(":") + 2);
                    String lastName = parts[1].substring(parts[1].indexOf(":") + 2);
                    int age = Integer.parseInt(parts[2].substring(parts[2].indexOf(":") + 2));
                    String gender = parts[3].substring(parts[3].indexOf(":") + 2);
                    String address = parts[4].substring(parts[4].indexOf(":") + 2);
                    String departmentId = parts[5].substring(parts[5].indexOf(":") + 2);

                    if (parts.length == 8) { // Staff
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

    // Write data to Staff File
    public void writeDataToStaffFile() {
        String writeFileName = "/Users/riteshsharma/Desktop/Staff Details.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(writeFileName, true))) {
            Scanner scanner = new Scanner(System.in);

            // Accepting input for staff
            System.out.println("Enter details for Staff:");
            System.out.print("First Name: ");
            String firstName = scanner.nextLine();
            System.out.print("Last Name: ");
            String lastName = scanner.nextLine();
            System.out.print("Age: ");
            int age = Integer.parseInt(scanner.nextLine());
            System.out.print("Gender: ");
            String gender = scanner.nextLine();
            System.out.print("Address: ");
            String address = scanner.nextLine();
            System.out.print("Department ID: ");
            String departmentId = scanner.nextLine();


            try (BufferedReader reader = new BufferedReader(new FileReader(writeFileName))) {
                if (departmentIdExistsInStaff(reader, departmentId)) {
                    throw new IllegalArgumentException("Department ID already exists.");
                }
            }

            writeStaffToFile(writeFileName, firstName, lastName, age, gender, address, departmentId);
        } catch (IOException e) {
            System.out.println("Error writing to the file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing integer: " + e.getMessage());
        }
    }

    public boolean departmentIdExistsInStaff(BufferedReader reader, String departmentId) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println("Line read from file: " + line);
            String[] parts = line.split(",");
            if (parts.length >= 7) {
                String idFromFile = parts[6].substring(parts[6].indexOf(":") + 1);// Assuming department ID is at index 5
                System.out.println("THIS IS THE = "+idFromFile);

                System.out.println("ID from file: " + idFromFile);
                if (idFromFile.equals(departmentId)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void writeStaffToFile(String fileName, String firstName, String lastName, int age, String gender, String address, String departmentId) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Duty: ");
            String duty = scanner.nextLine();
            System.out.print("Workload: ");
            int workload = Integer.parseInt(scanner.nextLine());
            Staff staff = new Staff(firstName, lastName, age, gender, address, departmentId, duty, workload);
            System.out.println(staff.toString());
            writer.write(staff.toString());
            writer.newLine();

            System.out.println("New Data has been added to the file.");
        } catch (IOException e) {
            System.out.println("Error writing to the file: " + e.getMessage());
        }
    }





    public static void main(String[] args) {
        Department test2 = new Department("IT");
        //test2.loadteacherdetails();
        //test2.loadstaffdetails();
        //test2.writeDataToTeachersFile();
        test2.loadstaffdetails();
        test2.writeDataToStaffFile();

    }
}

