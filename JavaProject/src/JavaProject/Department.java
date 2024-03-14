package JavaProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Department {
    private String id;
    private List<Teacher> teachers;
    private List<Staff> staff;
    private Teacher dean;

    public Department(String id) {
        this.id = id;
        this.teachers = new ArrayList<>();
        this.staff = new ArrayList<>();
        this.dean = null; // Initially no dean
    }

    public void addTeacher(Teacher teacher) {
        if (!teacher.departmentId.equals(id)) {
            throw new IllegalArgumentException("Teacher does not belong to this department.");
        }
        if (teachers.contains(teacher)) {
            throw new IllegalArgumentException("Teacher already exists in this department.");
        }
        teachers.add(teacher);
    }

    public void addStaff(Staff staffMember) {
        if (!staffMember.departmentId.equals(id)) {
            throw new IllegalArgumentException("Staff member does not belong to this department.");
        }
        if (staff.contains(staffMember)) {
            throw new IllegalArgumentException("Staff member already exists in this department.");
        }
        staff.add(staffMember);
    }

    public void setDean(Teacher dean) {
        if (!dean.departmentId.equals(id)) {
            throw new IllegalArgumentException("Dean must belong to this department.");
        }
        this.dean = dean;
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
}



