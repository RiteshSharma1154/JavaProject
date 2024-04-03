package JavaProject;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DriverTest {

    @Test
    void testWriteTeacherToFile() {
        Department department = new Department("IT");
        String fileName = "/Users/riteshsharma/Desktop/TestTeacherDetails.txt";
        String firstName = "John";
        String lastName = "Doe";
        int age = 35;
        String gender = "Male";
        String address = "123 Main St";
        String departmentId = "IT";
        String specialty = "Computer Science";
        String degree = "PhD";
        boolean isFullTime = true;
        boolean isPartTime = false;
        int hoursWorked = 40;

        department.writeTeacherToFile(fileName, firstName, lastName, age, gender, address, departmentId,
                specialty, degree, isFullTime, isPartTime, hoursWorked);

        // Read the written data and check if it matches with the input information
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(firstName) && line.contains(lastName) && line.contains(String.valueOf(age)) &&
                        line.contains(gender) && line.contains(address) && line.contains(departmentId) &&
                        line.contains(specialty) && line.contains(degree) && line.contains(String.valueOf(isFullTime)) &&
                        line.contains(String.valueOf(isPartTime)) && line.contains(String.valueOf(hoursWorked))) {
                    Assertions.assertTrue(true);
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    void testWriteStaffToFile() {
        Department department = new Department("IT");
        String fileName = "/Users/riteshsharma/Desktop/TestStaffDetails.txt";
        String firstName = "Jane";
        String lastName = "Doe";
        int age = 30;
        String gender = "Female";
        String address = "456 Oak St";
        String departmentId = "HR";
        String duty = "Admin";
        int workload = 35;

        department.writeStaffToFile(fileName, firstName, lastName, age, gender, address, departmentId,
                duty, workload);

        // Read the written data and check if it matches with the input information
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(firstName) && line.contains(lastName) && line.contains(String.valueOf(age)) &&
                        line.contains(gender) && line.contains(address) && line.contains(departmentId) &&
                        line.contains(duty) && line.contains(String.valueOf(workload))) {
                    Assertions.assertTrue(true);
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assertions.fail("Failed to write staff information to file.");
    }


    @Test
    public void testComputePayRollForFullTimeTeacher() {
        // Create a full-time teacher object with a PhD degree
        Teacher teacher = new Teacher("John", "Doe", 35, "123 Main St", "Male", "IT", "Math", "PhD", true, false, 40);

        // Hard code expected payroll value for full-time teacher with a PhD degree
        double expectedPayroll = 32 * 112 * 2 * 0.85;

        // Calculate actual payroll using the method under test
        double actualPayroll = teacher.ComputePayRoll();

        // Assert that the actual payroll matches the expected payroll
        assertEquals(expectedPayroll, actualPayroll, "Payroll calculation for full-time teacher is incorrect");
    }

    @Test
    public void testComputePayRollForPartTimeTeacher() {
        // Create a part-time teacher object with a Bachelor degree
        Teacher teacher = new Teacher("Jane", "Smith", 28, "456 Elm St", "Female", "IT", "Physics", "Bachelor", false, true, 20);

        // Hard code expected payroll value for part-time teacher with a Bachelor degree
        double expectedPayroll = 20 * 42 * 2 * 0.76;

        // Calculate actual payroll using the method under test
        double actualPayroll = teacher.ComputePayRoll();

        // Assert that the actual payroll matches the expected payroll
        assertEquals(expectedPayroll, actualPayroll, "Payroll calculation for part-time teacher is incorrect");
    }
}
