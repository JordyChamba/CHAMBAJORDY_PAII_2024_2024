package default_package;

public class Student {
	String name;
	String lastName;
	int age;
	
	public Student( String name, String lastName, int age) {
		// TODO Auto-generated constructor stub
		this.age=age;
		this.lastName=lastName;
		this.name=name;
		
	}
	
	public Student() {
		
	}
	
	@Override
	public String toString() {
	    return "Student{" +
	            ", name='" + name + '\'' +
	            ", lastName='" + lastName + '\'' +
	            ", age=" + age +
	            '}';
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}