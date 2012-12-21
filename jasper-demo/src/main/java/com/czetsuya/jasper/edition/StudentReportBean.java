package com.czetsuya.jasper.edition;

import java.util.ArrayList;
import java.util.List;

import com.czetsuya.jasper.model.Student;

/**
 * @author Edward P. Legaspi
 * @since Dec 13, 2012
 */
public class StudentReportBean {

	public StudentReportBean() {

	}

	public static List<Student> createBeanCollection() {
		List<Student> students = new ArrayList<Student>();

		Student a = new Student();
		a.setName("Tifa Lockheart");
		a.setAge(19);
		a.setAddress("Final Fantasy VII");
		students.add(a);

		Student b = new Student();
		b.setName("Rinoa Heartilly");
		b.setAge(19);
		b.setAddress("Final Fantasy VIII");
		students.add(b);

		return students;
	}
}
