package JavaProject;

public class Staff extends Person implements  PayRoll {
    private String duty;
    private int workload;

    public Staff(String firstName, String lastName, int age, String gender, String address,String departmentId, String duty, int workload) {
        super(firstName, lastName, age, gender, address, departmentId);
        this.duty = duty;
        this.workload = workload;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getDuty() {
        return duty;
    }

    public void setWorkload(String workload) {
        this.duty = duty;
    }

    public int getWorkload() {
        return workload;
    }


    @Override
    public String getCategory() {
        return "Staff";
    }

    public String toString() {
        return "Staff Details: [" +
                super.toString() + ", " +
                "Duty: " + duty + ", " +
                "Workload (hours/week): " + workload + ", " +
                "Salary: $" + ComputePayRoll() + "]";
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
        Staff staff = (Staff) obj;
        return workload == staff.workload &&
                duty.equals(staff.duty);
    }

    public double ComputePayRoll() {
        // Maximum number of hours a staff member can work in a week
        int MAX_HOURS_PER_WEEK = 40;

        // Calculate workload based on maximum hours per week
        double workload = Math.min(MAX_HOURS_PER_WEEK, this.workload);

        // Calculate salary using the provided formula
        return (workload * 32 * 2) * 0.75;
    }
}
