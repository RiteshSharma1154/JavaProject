package JavaProject;

public class MainMethod {
    public static void main(String[] args) {
        // Create instances of FullTimeTeacher, PartTimeTeacher, and Staff
        FullTimeTeacher fullTimeTeacher = new FullTimeTeacher("John", "Doe", 35, "Male", "123 Main St", "Math", "PhD", true);
        Staff staff = new Staff("Bob", "Johnson", 40, "Male", "789 Pine St", "Administration", 20);

        // Display information about each person
        System.out.println(fullTimeTeacher);
        System.out.println(staff);

        // Calculate and display the payroll for the full-time teacher
        double fullTimeTeacherPayroll = fullTimeTeacher.ComputePayRoll();
        System.out.println("Full-Time Teacher Payroll: $" + fullTimeTeacherPayroll);
    }
}