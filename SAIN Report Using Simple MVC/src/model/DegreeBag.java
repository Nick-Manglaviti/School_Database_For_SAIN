package model;

import java.util.ArrayList;

public class DegreeBag {
	private ArrayList<Degree> degrees = new ArrayList<Degree>();

	// Two ways of adding people to the HashMap
	public void addDegree(Degree d){ 
		degrees.add(d);
	}
	
	public void removeDegree(Degree d){
		degrees.remove(d);
	}

	public Degree searchForDegreeByName(String name) {
		for(int i = 0; i< degrees.size(); i++) {
			if(degrees.get(i).getTitle().equalsIgnoreCase(name)) {
				return degrees.get(i);
			}
		}
		return null;
	}
	public Degree searchForDegreeByMajor(String name) {
		for(int i = 0; i< degrees.size(); i++) {
			for(int j = 0; j < degrees.get(i).getMajors().size(); j++) {
				if(degrees.get(i).getMajors().get(j).getName().equalsIgnoreCase(name)) {
					return degrees.get(i);
				}
			}
		}
		return null;
	}
}
