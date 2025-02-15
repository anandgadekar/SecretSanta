package model;

import java.util.Objects;

public class Employee {
	private String name;
	private String email;

	public Employee(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Employee employee = (Employee) obj;
		return Objects.equals(email, employee.email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(email);
	}

	@Override
	public String toString() {
		return name + " (" + email + ")";
	}
}
