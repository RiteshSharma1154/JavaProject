package JavaProject;

public class FullTimeTeacher extends Teacher implements PayRoll
{
    private static final double DEGREE_RATE_PHD = 112;
    private static final double DEGREE_RATE_MASTER = 82;
    private static final double DEGREE_RATE_BACHELOR = 42;



    public FullTimeTeacher(String firstName, String lastName, int age, String gender, String address, String departmentId, String specialty, String degree, Boolean isFullTime) {
        super(firstName, lastName, age, gender, address, departmentId, specialty, degree, isFullTime);

    }

    @Override
    public double ComputePayRoll() {
        double degreeRate = 0;

        if ("PhD".equals(getDegree())) {
            degreeRate = DEGREE_RATE_PHD;
        } else if ("Master".equals(getDegree())) {
            degreeRate = DEGREE_RATE_MASTER;
        } else if ("Bachelor".equals(getDegree())) {
            degreeRate = DEGREE_RATE_BACHELOR;
        }

        return (32 * degreeRate * 2) * 0.75;
    }
}
