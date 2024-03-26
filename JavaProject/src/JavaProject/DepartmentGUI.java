package JavaProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DepartmentGUI extends JFrame {
    private String id;
    private List<Teacher> teachers;
    private List<Staff> staff;

    public DepartmentGUI(String id) {
        super("Department Management System");
        this.id = id;
        this.teachers = new ArrayList<>();
        this.staff = new ArrayList<>();

        // Create GUI components
        JButton loadTeachersButton = new JButton("Load Teacher Details");
        JButton writeTeachersButton = new JButton("Write Teacher Details");
        JButton loadStaffButton = new JButton("Load Staff Details");
        JButton writeStaffButton = new JButton("Write Staff Details");

        // Add action listeners
        loadTeachersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadTeacherDetails();
            }
        });

        writeTeachersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                writeDataToTeachersFile();
            }
        });

        loadStaffButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadStaffDetails();
            }
        });

        writeStaffButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                writeDataToStaffFile();
            }
        });

        // Set up the layout
        setLayout(new GridLayout(4, 1));
        add(loadTeachersButton);
        add(writeTeachersButton);
        add(loadStaffButton);
        add(writeStaffButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }


    // Teacher Detail : Loading Information from the Text file
    public void loadTeacherDetails() {
        // Implement loading teacher details
        JOptionPane.showMessageDialog(this, "Loading teacher details...");

        String filename = "/Users/riteshsharma/Desktop/Teacher Details.txt";
        StringBuilder allLines = new StringBuilder(); // StringBuilder to accumulate lines
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Skip empty lines
                if (line.trim().isEmpty()) {
                    continue;
                }
                allLines.append("Line read from file: ").append(line).append("\n"); // Append current line
                System.out.println("Line read from file: " + line);
                JOptionPane.showMessageDialog(this, "Line read from file: " + allLines);
                String[] parts = line.split(",");
                if (parts.length >= 6) { // Ensure minimum required number of elements
                    String firstName = parts[0].substring(parts[0].indexOf(":") + 2).trim();
                    String lastName = parts[1].substring(parts[1].indexOf(":") + 2).trim();
                    int age = Integer.parseInt(parts[2].substring(parts[2].indexOf(":") + 2).trim());
                    String gender = parts[3].substring(parts[3].indexOf(":") + 2).trim();
                    String address = parts[4].substring(parts[4].indexOf(":") + 2).trim();
                    String departmentId = parts[5].substring(parts[5].indexOf(":") + 2).trim();

                    if (parts.length == 11) { // Teacher
                        String specialty = parts[6].substring(parts[6].indexOf(":") + 2).trim();
                        String degree = parts[7].substring(parts[7].indexOf(":") + 2).trim();
                        boolean isFullTime = Boolean.parseBoolean(parts[8].substring(parts[8].indexOf(":") + 2).trim());
                        boolean isPartTime = Boolean.parseBoolean(parts[9].substring(parts[9].indexOf(":") + 2).trim());
                        int hoursWorked = Integer.parseInt(parts[10].substring(parts[10].indexOf(":") + 2).trim());
                        Teacher teacher = new Teacher(firstName, lastName, age, address, gender, departmentId, specialty, degree, isFullTime,isPartTime,hoursWorked);
                        teachers.add(teacher);
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


    // Teacher Detail : Writing Information from the Text file
    public void writeDataToTeachersFile() {
        // Implement writing teacher details to file
        JOptionPane.showMessageDialog(this, "Writing teacher details to file...");

        String writeFileName = "/Users/riteshsharma/Desktop/Teacher Details.txt";

        // Create GUI components
        JTextField firstNameField = new JTextField(20);
        JTextField lastNameField = new JTextField(20);
        JTextField ageField = new JTextField(5);
        JTextField genderField = new JTextField(10);
        JTextField addressField = new JTextField(30);
        JTextField departmentIdField = new JTextField(10);
        JTextField specialtyField = new JTextField(20);
        JTextField degreeField = new JTextField(20);
        JCheckBox isFullTimeCheckBox = new JCheckBox("Is Full Time");
        JCheckBox isPartTImeCheckBox = new JCheckBox("Is Part Time");
        JTextField hoursWorkedField = new JTextField(10);

        JPanel inputPanel = new JPanel(new GridLayout(0, 2));
        inputPanel.add(new JLabel("First Name:"));
        inputPanel.add(firstNameField);
        inputPanel.add(new JLabel("Last Name:"));
        inputPanel.add(lastNameField);
        inputPanel.add(new JLabel("Age:"));
        inputPanel.add(ageField);
        inputPanel.add(new JLabel("Gender:"));
        inputPanel.add(genderField);
        inputPanel.add(new JLabel("Address:"));
        inputPanel.add(addressField);
        inputPanel.add(new JLabel("Department ID:"));
        inputPanel.add(departmentIdField);
        inputPanel.add(new JLabel("Specialty:"));
        inputPanel.add(specialtyField);
        inputPanel.add(new JLabel("Degree:"));
        inputPanel.add(degreeField);
        inputPanel.add(new JLabel("Is Full Time:"));
        inputPanel.add(isFullTimeCheckBox);
        inputPanel.add(new JLabel("Is Part Time:"));
        inputPanel.add(isPartTImeCheckBox);
        inputPanel.add(new JLabel("Hours Worked:"));
        inputPanel.add(hoursWorkedField);

        int result = JOptionPane.showConfirmDialog(this, inputPanel, "Enter Teacher Details", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            // Retrieve values from GUI components
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String gender = genderField.getText();
            String address = addressField.getText();
            String departmentId = departmentIdField.getText();
            String specialty = specialtyField.getText();
            String degree = degreeField.getText();
            boolean isFullTime = isFullTimeCheckBox.isSelected();
            boolean isPartTIme = isPartTImeCheckBox.isSelected();
            int hoursWorked = Integer.parseInt(hoursWorkedField.getText());

            try (BufferedReader reader = new BufferedReader(new FileReader(writeFileName))) {
                if (departmentIdExists(reader, departmentId)) {
                    JOptionPane.showMessageDialog(null, "Department ID already exists.");
                    throw new IllegalArgumentException("Department ID already exists.");

                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error reading from the file: " + e.getMessage());
                return;
            }

            // Write data to file
            writeTeacherToFile(writeFileName, firstName, lastName, age, gender, address, departmentId, specialty, degree, isFullTime,isPartTIme,hoursWorked);
        }
    }

    private void writeTeacherToFile(String fileName, String firstName, String lastName, int age, String gender, String address, String departmentId, String specialty, String degree, boolean isFullTime,boolean isPartTime,int hoursWorked) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            Teacher teacher = new Teacher(firstName, lastName, age, address, gender, departmentId, specialty, degree, isFullTime,isPartTime,hoursWorked);
            System.out.println(teacher.toString());
            writer.write(teacher.toString());
            writer.newLine();

            JOptionPane.showMessageDialog(this, "New Data has been added to the file.");
        } catch (IOException e) {
            System.out.println("Error writing to the file: " + e.getMessage());
        }
    }


    // Staff Detail : Loading Information from the Text file
    public void loadStaffDetails() {
        // Implement loading staff details
        JOptionPane.showMessageDialog(this, "Loading staff details...");
        //Display Staff Details from the file
        String filename = "/Users/riteshsharma/Desktop/Staff Details.txt";
        StringBuilder allLines = new StringBuilder(); // StringBuilder to accumulate lines
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if(line.trim().isEmpty())
                {
                    continue;
                }
                allLines.append("Line read from file: ").append(line).append("\n");
                System.out.println("Line read from file: " + line);
                JOptionPane.showMessageDialog(this, "Line read from file: " + allLines);
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


    // Staff Detail : Writing Information from the Text file
    public void writeDataToStaffFile() {
        // Implement writing staff details to file
        JOptionPane.showMessageDialog(this, "Writing staff details to file...");

        String writeFileName = "/Users/riteshsharma/Desktop/Staff Details.txt";
        // Create GUI components
        JTextField firstNameField = new JTextField(20);
        JTextField lastNameField = new JTextField(20);
        JTextField ageField = new JTextField(5);
        JTextField genderField = new JTextField(10);
        JTextField addressField = new JTextField(30);
        JTextField departmentIdField = new JTextField(10);
        JTextField dutyField = new JTextField(20);
        JTextField workloadField = new JTextField(10);

        JPanel inputPanel = new JPanel(new GridLayout(0, 2));
        inputPanel.add(new JLabel("First Name:"));
        inputPanel.add(firstNameField);
        inputPanel.add(new JLabel("Last Name:"));
        inputPanel.add(lastNameField);
        inputPanel.add(new JLabel("Age:"));
        inputPanel.add(ageField);
        inputPanel.add(new JLabel("Gender:"));
        inputPanel.add(genderField);
        inputPanel.add(new JLabel("Address:"));
        inputPanel.add(addressField);
        inputPanel.add(new JLabel("Department ID:"));
        inputPanel.add(departmentIdField);
        inputPanel.add(new JLabel("Duty:"));
        inputPanel.add(dutyField);
        inputPanel.add(new JLabel("WorkLoad:"));
        inputPanel.add(workloadField);

        int result = JOptionPane.showConfirmDialog(this, inputPanel, "Enter Staff Details", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {

            // Retrieve values from GUI components
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String gender = genderField.getText();
            String address = addressField.getText();
            String departmentId = departmentIdField.getText();
            String duty = dutyField.getText();
            int workload = Integer.parseInt(workloadField.getText());

            try (BufferedReader reader = new BufferedReader(new FileReader(writeFileName))) {
                if (departmentIdExists(reader, departmentId)) {
                    JOptionPane.showMessageDialog(null, "Department ID already exists.");
                    throw new IllegalArgumentException("Department ID already exists.");

                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error reading from the file: " + e.getMessage());
                return;
            }
            writeStaffToFile(writeFileName, firstName, lastName, age, gender, address, departmentId, duty, workload);


        }
    }

    private void writeStaffToFile(String fileName, String firstName, String lastName, int age, String gender, String address, String departmentId, String duty, int workload) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {

            Staff staff = new Staff(firstName, lastName, age, gender, address, departmentId, duty, workload);
            System.out.println(staff.toString());
            writer.write(staff.toString());
            writer.newLine();

            JOptionPane.showMessageDialog(this, "New Data has been added to the file.");
        } catch (IOException e) {
            System.out.println("Error writing to the file: " + e.getMessage());
        }
    }


    // Method to compare if the information in the file already exist
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


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DepartmentGUI("IT");
            }
        });
    }
}
