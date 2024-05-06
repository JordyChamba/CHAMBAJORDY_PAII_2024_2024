package default_package;

public class Teacher {
	int id;
	String name;
	String lastName;
	int age;
	
	public Teacher(int id, String name, String lastName, int age) {
		// TODO Auto-generated constructor stub
		this.age=age;
		this.id=id;
		this.lastName=lastName;
		this.name=name;
		
	}
	
	@Override
	public String toString() {
	    return "Teacher{" +
	            "ide=" + id +
	            ", name='" + name + '\'' +
	            ", lastName='" + lastName + '\'' +
	            ", age=" + age +
	            '}';
	}

	public int getIde() {
		return id;
	}

	public void setIde(int ide) {
		this.id = ide;
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
