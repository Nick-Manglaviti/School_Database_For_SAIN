package controller;

import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import model.Person;
import model.PersonBag;
import model.Student;
import view.MainScreen;
import view.SainReportButtonEventObject;
import view.SainReportButtonListener;
import view.WhatIfButtonEventObject;
import view.WhatIfButtonListener;

public class MainWindowController {

	// Main Window Button Events
	public MainWindowController(PersonBag personbag, MainScreen mainWindow, Person personModel) {
		
		// My Sain Report Event Handling
	    mainWindow.setSainReportButtonListener(new SainReportButtonListener() {
		@Override
		public void sainReportButtonClicked(SainReportButtonEventObject ev) {
			if(personModel instanceof Student) {
				mainWindow.setProgramT(((Student) personModel).getProgram());
				mainWindow.setDegreeT(((Student) personModel).getDegree().getTitle());
				mainWindow.setMajorT(((Student) personModel).getMajor().getName());
				mainWindow.setCumulativeGPAT(((Student) personModel).getCumulativeGpa());
				mainWindow.setPGPAT(((Student) personModel).getProgramGPA());
				mainWindow.setMGPAT(((Student) personModel).getMajorGPA());
				TableView reqCTV;
				TableView otherCTV;
				TableView withdrawCTV;
				TableView currentCTV;
				TableView neededC;
				mainWindow.setMinGPAshow(((Student) personModel).getMajor().getMinGPA());
				mainWindow.setTotalCredsshow(((Student) personModel).getMajor().getTotalCredit());
				mainWindow.setMinNumshow(((Student) personModel).getDegree().getMinDegreeCred());
				mainWindow.setTotalCredsTakenshow();
				mainWindow.setTotalTransferCredsshow();
				mainWindow.setTotalCredsDegshow();
			}
		}
    	});
	    
	    
	    // What If Event Handling
	    mainWindow.setWhatIfButtonListener(new WhatIfButtonListener() {
		@Override
		public void whatIfButtonClicked(WhatIfButtonEventObject ev) {
				
		}
	    });
	}
}
