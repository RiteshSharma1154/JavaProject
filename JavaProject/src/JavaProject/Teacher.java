package JavaProject;

public class Teacher extends Person implements PayRoll {
    private String specialty;
    private String degree;
    private boolean isFullTime;

    private boolean isPartime;

    private int hoursWorked;


    public Teacher(String firstName, String lastName, int age, String gender, String address, String departmentId, String specialty, String degree, Boolean isFullTime,Boolean isPartime,int hoursWorked) {
        super(firstName, lastName, age, gender, address, departmentId);
        this.specialty = specialty;
        this.degree = degree;
        this.isFullTime = isFullTime;
        this.isPartime = isPartime;
        this.hoursWorked = hoursWorked;
    }


    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setDegree(String degree) {
        this.specialty = specialty;
    }

    public String getDegree() {
        return degree;
    }


    public void setFullTime(boolean isFullTime) {
        this.isFullTime = isFullTime;
    }

    public boolean getFullTime() {
        return isFullTime;
    }


    @Override
    public String getCategory() {
        if (isFullTime) {
            return "Full - Time Teacher";
        } else {
            return "Part - Time Teacher";
        }
    }

    public String toString() {
        String teacherDetails = "Teacher Details: [" +
                super.toString() + ", " +
                "Specialty: " + specialty + ", " +
                "Degree: " + degree + ", " +
                "Full Time: " + isFullTime;

        // Add salary based on full-time or part-time status and degree
        if (isFullTime) {
            double salary = ComputePayRoll();
            teacherDetails += ", Salary (Full-Time";
            if ("PhD".equals(degree)) {
                teacherDetails += " PhD): $" + salary;
            } else if ("Master".equals(degree)) {
                teacherDetails += " Master): $" + salary;
            } else if ("Bachelor".equals(degree)) {
                teacherDetails += " Bachelor): $" + salary;
            } else {
                teacherDetails += " Unknown): N/A"; // Handle other degrees
            }
        } else {
            double salary = ComputePayRoll();
            teacherDetails += ", Salary (Part-Time): $" + salary;
        }

        teacherDetails += "]";
        return teacherDetails;
    }



    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Teacher teacher = (Teacher) obj;

        return isFullTime == teacher.isFullTime &&
                specialty.equals(teacher.specialty) &&
                degree.equals(teacher.degree);
    }

    public double ComputePayRoll() {
        double DEGREE_RATE_PHD = 112;
        double DEGREE_RATE_MASTER = 82;
        double DEGREE_RATE_BACHELOR = 42;
        double degreeRate = 0;

        // Determine degree rate based on teacher's degree
        if ("PhD".equals(getDegree())) {
            degreeRate = DEGREE_RATE_PHD;
        } else if ("Master".equals(getDegree())) {
            degreeRate = DEGREE_RATE_MASTER;
        } else if ("Bachelor".equals(getDegree())) {
            degreeRate = DEGREE_RATE_BACHELOR;
        }

        // Compute salary differently for full-time and part-time teachers
        if (isFullTime) {
            return (32 * degreeRate * 2) * 0.85; // Salary computation for full-time teachers
        } else {
            // Assuming hoursWorked is a class variable representing hours worked by part-time teachers
            return (hoursWorked * degreeRate * 2) * 0.76; // Salary computation for part-time teachers
        }
    }

    }



