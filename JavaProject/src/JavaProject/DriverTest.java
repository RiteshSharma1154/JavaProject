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

        // Compute expected payroll based on the provided logic
        double expectedPayroll = computeExpectedPayrollForFullTimeTeacher(teacher);

        // Calculate actual payroll using the method under test
        double actualPayroll = teacher.ComputePayRoll();

        // Assert that the actual payroll matches the expected payroll
        assertEquals(expectedPayroll, actualPayroll, "Payroll calculation for full-time teacher is incorrect");
    }

    @Test
    public void testComputePayRollForPartTimeTeacher() {
        // Create a part-time teacher object with a Bachelor degree
        Teacher teacher = new Teacher("Jane", "Smith", 28, "456 Elm St", "Female", "IT", "Physics", "Bachelor", false, true, 20);

        // Compute expected payroll based on the provided logic
        double expectedPayroll = computeExpectedPayrollForPartTimeTeacher(teacher);

        // Calculate actual payroll using the method under test
        double actualPayroll = teacher.ComputePayRoll();

        // Assert that the actual payroll matches the expected payroll
        assertEquals(expectedPayroll, actualPayroll, "Payroll calculation for part-time teacher is incorrect");
    }

    // Helper method to calculate expected payroll for a full-time teacher
    private double computeExpectedPayrollForFullTimeTeacher(Teacher teacher) {
        double DEGREE_RATE_PHD = 112;
        double DEGREE_RATE_MASTER = 82;
        double DEGREE_RATE_BACHELOR = 42;
        double degreeRate = 0;

        // Determine degree rate based on teacher's degree
        if ("PhD".equals(teacher.getDegree())) {
            degreeRate = DEGREE_RATE_PHD;
        } else if ("Master".equals(teacher.getDegree())) {
            degreeRate = DEGREE_RATE_MASTER;
        } else if ("Bachelor".equals(teacher.getDegree())) {
            degreeRate = DEGREE_RATE_BACHELOR;
        }

        // Calculate expected payroll for full-time teacher
        return (32 * degreeRate * 2) * 0.85;
    }

    // Helper method to calculate expected payroll for a part-time teacher
    private double computeExpectedPayrollForPartTimeTeacher(Teacher teacher) {
        double DEGREE_RATE_PHD = 112;
        double DEGREE_RATE_MASTER = 82;
        double DEGREE_RATE_BACHELOR = 42;
        double degreeRate = 0;
        int hoursWorked = 20;

        // Determine degree rate based on teacher's degree
        if ("PhD".equals(teacher.getDegree())) {
            degreeRate = DEGREE_RATE_PHD;
        } else if ("Master".equals(teacher.getDegree())) {
            degreeRate = DEGREE_RATE_MASTER;
        } else if ("Bachelor".equals(teacher.getDegree())) {
            degreeRate = DEGREE_RATE_BACHELOR;
        }

        // Calculate expected payroll for part-time teacher
        return (hoursWorked * degreeRate * 2) * 0.76;
    }
}
