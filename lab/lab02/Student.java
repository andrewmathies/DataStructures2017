// H343 / Spring 2017 Lab Mini-Assignments 02 Andrew Mathies awmathie

public class Student {
    //fields
    public String name = "Unknown";
    private String department = "Unknown";

    //methods
    public Student(String name) {
	this.name = name;
    }
    public void setName(String name) {
	this.name = name;
    }
    public void setDepartment(String department) {
	this.department = department;
    }
    public String getDepartment() {
	return department;
    }
    public void display() {
	System.out.println("Student Information");
	System.out.println("Name: " + name);
	System.out.println("Department: " + department);
    }

    //main
    public static void main(String[] argv) {
	String name = "Chris";

	Student s1 = new Student(name);
	s1.setDepartment("CSCI");
	
	Student[] students = new Student[2];
	students[0] = s1;
	students[1] = new Student("Jo");
	students[1].setDepartment("Jazz");

	for (int i = 0; i < 2; i++)
	    students[i].display();
    }
}
