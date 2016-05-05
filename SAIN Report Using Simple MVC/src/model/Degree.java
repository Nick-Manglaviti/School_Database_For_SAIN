package model;

import java.util.List;



public class Degree {

		//Data Fields
		private String title;
		private List<Major> majors;
		private double minDegreeCred;
		
		//Getters and Setters
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public List<Major> getMajors() {
			return majors;
		}
		public void setMajors(List<Major> majors) {
			this.majors = majors;
		}
		public double getMinDegreeCred() {
			return minDegreeCred;
		}
		public void setMinDegreeCred(double minDegreeCred) {
			this.minDegreeCred = minDegreeCred;
		}
		
		//Constructors
		public Degree(String title, List<Major> majors, double minDegreeCred) {
			this.title = title;
			this.majors = majors;
			this.minDegreeCred = minDegreeCred;
		}
		public Degree() {
			// No Argument Constructor
		}
}
