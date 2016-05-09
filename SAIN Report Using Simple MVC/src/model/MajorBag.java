package model;

import java.util.ArrayList;



public class MajorBag {
	private ArrayList<Major> majors = new ArrayList<Major>();

	// Two ways of adding people to the HashMap
	public void addMajor(Major m){ 
		majors.add(m);
	}
	
	public void removeMajor(Major m){
		majors.remove(m);
	}

	public Major searchForMajor(String name) {
		for(int i = 0; i<majors.size(); i++) {
			if(majors.get(i).getName().equalsIgnoreCase(name)) {
				return majors.get(i);
			}
		}
		return null;
	}

	public ArrayList<Major> getMajors() {
		return majors;
	}

	public void setMajors(ArrayList<Major> majors) {
		this.majors = majors;
	}

	
}
