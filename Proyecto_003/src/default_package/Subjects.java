package default_package;

public class Subjects {
	String name;
	String description;
	int level;
	
	public Subjects(String name, String description, int level) {
		// TODO Auto-generated constructor stub
		this.level=level;
		this.description=description;
		this.name=name;
		
	}
	
	@Override
	public String toString() {
	    return "Subjects{" +
	            ", name='" + name + '\'' +
	            ", description='" + description + '\'' +
	            ", level=" + level +
	            '}';
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description ) {
		this.description = description;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}

