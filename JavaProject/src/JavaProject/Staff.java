package JavaProject;

public class Staff extends Person {
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
        return "Staff Details : [" + " "+
                super.toString()+ "," + " "+
                "Duty :" + duty + "," + " "+
                "Workload :" + workload + " "+
                "]";
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
}
