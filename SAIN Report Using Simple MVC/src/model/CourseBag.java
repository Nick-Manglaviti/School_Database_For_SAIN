package model;

import java.util.ArrayList;

public class CourseBag {
	private ArrayList<Course> courses = new ArrayList<Course>();

	// Two ways of adding people to the HashMap
	public void addCourse(Course c){ 
		courses.add(c);
	}
	
	public void removeCourse(Major c){
		courses.remove(c);
	}

	public Course searchForCourse(String name) {
		for(int i = 0; i<courses.size(); i++) {
			if(courses.get(i).getCourseName().equalsIgnoreCase(name)) {
				return courses.get(i);
			}
		}
		return null;
	}

	public ArrayList<Course> getCourses() {
		return courses;
	}

	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}
}
