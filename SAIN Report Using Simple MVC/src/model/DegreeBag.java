package model;

import java.util.ArrayList;

public class DegreeBag {
	final ArrayList<Degree> degrees = new ArrayList<Degree>();

	// Two ways of adding people to the HashMap
	public void addDegree(Degree d){ 
		degrees.add(d);
	}
	
	public void removeDegree(Degree d){
		degrees.remove(d);
	}

	public Degree searchForDegree(String name) {
		for(int i = 0; i< degrees.size(); i++) {
			if(degrees.get(i).getTitle().equalsIgnoreCase(name)) {
				return degrees.get(i);
			}
		}
		return null;
	}
}
