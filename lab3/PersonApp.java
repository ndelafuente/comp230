// Lab 3 PersonApp
// Authors: Dillan Lopez and Nico de la Fuente
// Last updated: 01 September 2020

import java.util.Scanner;

public class PersonApp {

	public static void main(String args[]) {
		Person p1 = new Person();
		p1.setName("Dillan");
		p1.setAge(19);
		System.out.printf("p1: %s is %d years old \n", p1.getName(), p1.getAge());

		Person p2 = new Person("Nico", 18);	
		System.out.printf("p2: %s is %d years old \n\n", p2.getName(), p2.getAge());

		Person p2_copy = p2;
		if(p2.equals(p2_copy))
			System.out.println("p2 is the same as p2_copy");
		else
			System.out.println("p2 is not the same as p2_copy");

		if(p1.equals(p2))
			System.out.println("p1 is the same as p2");
		else
			System.out.println("p1 is not the same as p2");

	}
}
