package controller;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import model.Administrator;
import model.Course;
import model.CoursesTaken;
import model.Faculty;
import model.Person;
import model.PersonBag;
import model.Student;
import view.MainScreen;
import view.SainReportButtonEventObject;
import view.SainReportButtonListener;
import view.WhatIfButtonEventObject;
import view.WhatIfButtonListener;

public class MainWindowController {

	// Main Window Controller
	
	public MainWindowController(PersonBag personbag, MainScreen mainWindow, Student studentModel) {
		// If you are a Student Signing in, this is your Controller
		// Bottom "TaskBar" for Student
		createStudentTaskbar(mainWindow);
		// My Sain Report Event Handling
	    mainWindow.setSainReportButtonListener(new SainReportButtonListener() {
		@Override
		public void sainReportButtonClicked(SainReportButtonEventObject ev) {
				// Set Header
				String header = new String("Sain Report for " + studentModel.getfName()+ " " + studentModel.getlName());
				mainWindow.setHeaderL(header);
				
				mainWindow.setProgramT((studentModel).getProgram());
				mainWindow.setDegreeT((studentModel).getDegree().getTitle());
				mainWindow.setMajorT((studentModel).getMajor().getName());
				mainWindow.setCumulativeGPAT((studentModel).getCumulativeGpa());
				mainWindow.setPGPAT((studentModel).getProgramGPA());
				mainWindow.setMGPAT((studentModel).getMajorGPA());
				mainWindow.setCampusT((studentModel).getCampus());;
				
				
				ObservableList<String> items1 =FXCollections.observableArrayList(checkIfRequired(studentModel, (studentModel).getCourses()));
				mainWindow.getReqCTV().setItems(items1);
				
				ObservableList<String> items2 =FXCollections.observableArrayList(checkIfOther(studentModel, (studentModel).getCourses()));
				mainWindow.getOtherCTV().setItems(items2);
				
				ObservableList<String> items3 =FXCollections.observableArrayList(checkIfWithdrawn(studentModel, (studentModel).getCourses()));
				mainWindow.getWithdrawCTV().setItems(items3);
				
				ObservableList<String> items4 =FXCollections.observableArrayList(checkIfCurrent(studentModel, (studentModel).getCourses()));
				mainWindow.getCurrentCTV().setItems(items4);
				
				ObservableList<String> items5 =FXCollections.observableArrayList(checkIfNeeded(studentModel, (studentModel).getCourses()));
				mainWindow.getNeededCV().setItems(items5);
				

				mainWindow.setMinGPAshow((studentModel).getMajor().getMinGPA());
				mainWindow.setTotalCredsshow((studentModel).getMajor().getTotalCredit());
				mainWindow.setMinNumshow((studentModel).getDegree().getMinDegreeCred());
				mainWindow.disableTextFields();
				mainWindow.createSain();
			} 
		
    	});
	    // What If Event Handling
	    mainWindow.setWhatIfButtonListener(new WhatIfButtonListener() {
		@Override
		public void whatIfButtonClicked(WhatIfButtonEventObject ev) {
				
		}
	    });
	}
	
	public MainWindowController(PersonBag personbag, MainScreen mainWindow, Administrator admminModel, Student studentModel) {
		// If you are a Administrator Signing in, this is your Controller
		// Bottom "TaskBar" for Student
		createAdminTaskbar(mainWindow);
	}
	
	public MainWindowController(PersonBag personbag, MainScreen mainWindow, Faculty facultyModel, Student studentModel) {
		// If you are a Administrator Signing in, this is your Controller
		
	}
	
	
	
	
	
	
	
	public ArrayList<String> checkIfRequired(Student student, List<CoursesTaken> list) {
	
		List<CoursesTaken> tempList = new ArrayList<CoursesTaken>(list); // put the real into a temporary list that will remove items when taken
		ArrayList<String> returnList = new ArrayList<String>();
		
		List<Course> majorCourses = new ArrayList<Course>(); // array of courses in the major
		majorCourses = student.getMajor().getCoursesList();
		
		List<String> majorTypes = new ArrayList<String>(); // array of subject that are in the major
		majorTypes = student.getMajor().getTypesArray();
		
		// Check if the course is directly required
		for(int i = 0; i < tempList.size(); i++) { 
			String name = tempList.get(i).getCourseName(); 
			// Get a course name for students course
			for(int j = 0; j < majorCourses.size(); j++) {
				if(name.equalsIgnoreCase(majorCourses.get(j).getCourseName())) {
					String majorRequired = (student.getMajor().getName() + " Major Required");
					tempList.get(i).setCoursetype(majorRequired);
					returnList.add(tempList.get(i).toStringForRequired());
					tempList.remove(i);
					break;
				}
			}
		}
		// Check if the course is an elective subject
		for(int i = 0; i < tempList.size(); i++) { 
			
				String name = tempList.get(i).getCoursetype(); // get a course name for students course
			
			for(int j = 0; j < majorTypes.size(); j++) {
				if(name.equalsIgnoreCase(majorTypes.get(j))) {
					String majorElective = (majorTypes.get(j));
					tempList.get(i).setCoursetype(majorElective);
					returnList.add(tempList.get(i).toStringForRequired());
					tempList.remove(i);
					break;
				}
			}
		}	
		return returnList;
	}
	
	public ArrayList<String> checkIfOther(Student student, List<CoursesTaken> list) {
		
		List<CoursesTaken> tempList = new ArrayList<CoursesTaken>(list); // put the real into a temporary list that will remove items when taken
		ArrayList<String> returnList = new ArrayList<String>();
		String name = "Other"; 
		
		// Check if the course is an Other
		for(int i = 0; i < tempList.size(); i++) { 
			if(name.equalsIgnoreCase(tempList.get(i).getCoursetype())) {
				returnList.add(tempList.get(i).toStringForOther());
				tempList.remove(i);
				break;
			}
			
		}
		return returnList;
	}
	
	public ArrayList<String> checkIfWithdrawn(Student student, List<CoursesTaken> list) {
		
		List<CoursesTaken> tempList = new ArrayList<CoursesTaken>(list); // put the real into a temporary list that will remove items when taken
		ArrayList<String> returnList = new ArrayList<String>();
		String grade1 = "W"; 
		String grade2 = "F"; 
		
		// Check if the course is an Other
		for(int i = 0; i < tempList.size(); i++) { 
			if(grade1.equalsIgnoreCase(tempList.get(i).getGrade())) {
				returnList.add(tempList.get(i).toStringForOther());
				tempList.remove(i);
				break;
			}
			if(grade2.equalsIgnoreCase(tempList.get(i).getGrade())) {
				returnList.add(tempList.get(i).toStringForOther());
				tempList.remove(i);
				break;
			}
			
		}
		return returnList;
	}
	
	public ArrayList<String> checkIfCurrent(Student student, List<CoursesTaken> list) {
		
		List<CoursesTaken> tempList = new ArrayList<CoursesTaken>(list); // put the real into a temporary list that will remove items when taken
		ArrayList<String> returnList = new ArrayList<String>();
		String name = "IP"; 
		
		// Check if the course is an IP
		for(int i = 0; i < tempList.size(); i++) { 
			if(name.equalsIgnoreCase(tempList.get(i).getGrade())) {
				returnList.add(tempList.get(i).toStringForOther());
				tempList.remove(i);
				break;
			}
		}
		return returnList;
	}
	
	public ArrayList<String> checkIfNeeded(Student student, List<CoursesTaken> list) {
		
		List<CoursesTaken> tempList = new ArrayList<CoursesTaken>(list); // put the real into a temporary list that will remove items when taken
		ArrayList<String> returnList = new ArrayList<String>();
		
		List<Course> majorCourses = new ArrayList<Course>(); // array of courses in the major
		majorCourses = student.getMajor().getCoursesList();
		
		List<String> majorTypes = new ArrayList<String>(); // array of subject that are in the major
		majorTypes = student.getMajor().getTypesArray();
		
		// Check if the course is directly required
		for(int i = 0; i < tempList.size(); i++) { 
			String name = tempList.get(i).getCourseName(); 
			// Get a course name for students course
			for(int j = 0; j < majorCourses.size(); j++) {
				if(!name.equalsIgnoreCase(majorCourses.get(j).getCourseName())) {
					returnList.add(majorCourses.get(i).toStringForNeeded());
					tempList.remove(i);
					break;
				}
			}
		}
		// Check if the course is an elective subject
		for(int i = 0; i < tempList.size(); i++) { 
			
				String name = tempList.get(i).getCoursetype(); // get a course name for students course
			
			for(int j = 0; j < majorTypes.size(); j++) {
				if(!name.equalsIgnoreCase(majorTypes.get(j))) {
					returnList.add(majorCourses.get(i).toStringForNeeded());
					tempList.remove(i);
					break;
				}
			}
		}	
		return returnList;
	}
	
	public double totalCredsAtSCCC(Student student, List<CoursesTaken> list) {
		return 0;
	}
	
	// Create Taskbars
	public void createStudentTaskbar(MainScreen mainWindow) {
		mainWindow.getBottom().getChildren().addAll(mainWindow.getSainReportButton(), mainWindow.getWhatIfButton());
		mainWindow.getRoot().setBottom(mainWindow.getBottom());
	}
	public void createAdminTaskbar(MainScreen mainWindow) {
		mainWindow.getBottom().getChildren().addAll(mainWindow.getSainReportButton(), mainWindow.getWhatIfButton(), 
				mainWindow.getSearchButton(), mainWindow.getSaveButton());
		mainWindow.getRoot().setBottom(mainWindow.getBottom());
	}
	public void createFacultyTaskbar(MainScreen mainWindow) {
		mainWindow.getBottom().getChildren().addAll(mainWindow.getSainReportButton(), mainWindow.getWhatIfButton(), 
				mainWindow.getSearchButton());
		mainWindow.getRoot().setBottom(mainWindow.getBottom());
	}
	
}
