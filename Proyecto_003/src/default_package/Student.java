package default_package;

public class Student {
	int id;
	String name;
	String lastName;
	int age;
	
	public Student(int id, String name, String lastName, int age) {
		// TODO Auto-generated constructor stub
		this.age=age;
		this.id=id;
		this.lastName=lastName;
		this.name=name;
		
	}
	
	public Student() {
		
	}
	
	@Override
	public String toString() {
	    return "Student{" +
	            "ide=" + id +
	            ", name='" + name + '\'' +
	            ", lastName='" + lastName + '\'' +
	            ", age=" + age +
	            '}';
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
