package JavaProject;

public class Teacher extends Person
{
    private String  specialty;
    private String degree;
    private boolean isFullTime;

    public Teacher(String firstName, String lastName, int age, String gender, String address, String departmentId, String specialty, String degree, Boolean isFullTime) {
        super(firstName, lastName, age, gender, address, departmentId);
        this.specialty = specialty;
        this.degree = degree;
        this.isFullTime = isFullTime;
    }



    public void setSpecialty(String specialty)
    {
        this.specialty = specialty;
    }
    public String getSpecialty()
    {
        return specialty;
    }

    public void setDegree(String degree)
    {
        this.specialty = specialty;
    }
    public String getDegree()
    {
        return degree;
    }


    public void setFullTime(boolean isFullTime)
    {
        this.isFullTime = isFullTime;
    }
    public boolean getFullTime()
    {
        return isFullTime;
    }


    @Override
    public String getCategory() {
        if(isFullTime)
        {
            return "Full - Time Teacher";
        }else {
            return "Part - Time Teacher";
        }
    }

    public String toString() {
        return "Teacher{" +
                "specialty='" + specialty + '\'' +
                ", degree='" + degree + '\'' +
                ", isFullTime=" + isFullTime +
                super.toString() +
                '}';
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


}
