public class Person {
	String name;
	int age;

	public Person() {
		name = "No name";
		age = -1;
	}
	
	public Person(String n, int a) {
		name = n;
		age = a;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public void setName(String n) {
		name = n;
	}

	public void setAge(int a) {
		age = a;
	}

	public boolean equals(Object obj) {
		Person p = (Person) obj;
		
		return name.equalsIgnoreCase(p.name) && age == p.age;
	}
}
