package JavaProject;

public abstract class Person
{
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String address;
    protected String departmentId;

    public Person(String firstName, String lastName, int age, String gender, String address, String departmentId)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this. address = address;
        this.departmentId = departmentId;
    }


    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    public String getFirstName()
    {
        return firstName;
    }

    public void setLastName(String lastName)
    {
        this.firstName = firstName;
    }
    public String getLastName()
    {
        return lastName;
    }

    public void setAge(int age)
    {
        this.firstName = firstName;
    }
    public int setAge()
    {
        return age;
    }


    public void setGender(String gender)
    {
        this.firstName = firstName;
    }
    public String getGender()
    {
        return gender;
    }

    public void setAddress(String address)
    {
        this.firstName = firstName;
    }
    public String getAddress()
    {
        return address;
    }

    public void setDepartmentId(String departmentId)
    {
        this.departmentId = departmentId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public abstract String getCategory();


    public String toString() {
        return
                "First Name :" + firstName + "," + " "+
                "Last Name :" + lastName + "," + " "+
                "Age :" + age + "," + " "+
                ",Gender : " + gender + "," + " "+
                "Address : " + address + "," + " "+
                "Department :" + departmentId  + " ";
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
        {
            return true;
        }
        if(obj == null || getClass() != obj.getClass())
        {
            return false;
        }

        Person person = (Person)obj;

        return age == person.age &&
                firstName.equals(person.firstName) &&
                lastName.equals(person.lastName) &&
                gender.equals(person.gender) &&
                address.equals(person.address) &&
                departmentId.equals(person.departmentId);

    }

}

