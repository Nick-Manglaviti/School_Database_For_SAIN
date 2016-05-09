package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import model.Administrator;
import model.Course;
import model.CoursesTaken;
import model.DegreeBag;
import model.Faculty;
import model.Major;
import model.MajorBag;
import model.Person;
import model.PersonBag;
import model.Student;
import view.MainScreen;
import view.SainReportButtonEventObject;
import view.SainReportButtonListener;
import view.SearchButtonEventObject;
import view.SearchButtonListener;
import view.WhatIfButtonEventObject;
import view.WhatIfButtonListener;

public class MainWindowController {

	// Main Window Controller

	public MainWindowController(PersonBag personbag, MajorBag majorBag,
			DegreeBag degreeBag, MainScreen mainWindow, Student studentModel) {
		// If you are a Student Signing in, this is your Controller
		// Bottom "TaskBar" for Student
		createStudentTaskbar(mainWindow);
		// My Sain Report Event Handling
		mainWindow.setSainReportButtonListener(new SainReportButtonListener() {
			@Override
			public void sainReportButtonClicked(SainReportButtonEventObject ev) {
				// Set Header
				String header = new String("Sain Report for "
						+ studentModel.getfName() + " "
						+ studentModel.getlName());
				mainWindow.setHeaderL(header);

				mainWindow.setProgramT((studentModel).getProgram());
				mainWindow.setDegreeT((studentModel).getDegree().getTitle());
				mainWindow.setMajorT((studentModel).getMajor().getName());
				mainWindow.setCumulativeGPAT((studentModel).getCumulativeGpa());
				mainWindow.setPGPAT((studentModel).getProgramGPA());
				mainWindow.setMGPAT((studentModel).getMajorGPA());
				mainWindow.setCampusT((studentModel).getCampus());
				;

				ObservableList<String> items1 = FXCollections
						.observableArrayList(checkIfRequired(studentModel,
								(studentModel).getCourses()));
				mainWindow.getReqCTV().setItems(items1);

				ObservableList<String> items2 = FXCollections
						.observableArrayList(checkIfOther(studentModel,
								(studentModel).getCourses()));
				mainWindow.getOtherCTV().setItems(items2);

				ObservableList<String> items3 = FXCollections
						.observableArrayList(checkIfWithdrawn(studentModel,
								(studentModel).getCourses()));
				mainWindow.getWithdrawCTV().setItems(items3);

				ObservableList<String> items4 = FXCollections
						.observableArrayList(checkIfCurrent(studentModel,
								(studentModel).getCourses()));
				mainWindow.getCurrentCTV().setItems(items4);

				ObservableList<String> items5 = FXCollections
						.observableArrayList(checkIfNeeded(studentModel,
								(studentModel).getCourses()));
				mainWindow.getNeededCV().setItems(items5);

				mainWindow.setMinGPAshow((studentModel).getMajor().getMinGPA());
				mainWindow.setTotalCredsshow((studentModel).getMajor()
						.getTotalCredit());
				mainWindow.setMinNumshow((studentModel).getDegree()
						.getMinDegreeCred());
				mainWindow.disableTextFields();
				mainWindow.createSain();
			}

		});
		// What If Event Handling
		mainWindow.setWhatIfButtonListener(new WhatIfButtonListener() {
			@Override
			public void whatIfButtonClicked(WhatIfButtonEventObject ev) {
				Student tempPerson = new Student();
				tempPerson = studentModel;
				
				List<String> choices = new ArrayList<>();
				for(int i = 0; i < majorBag.getMajors().size(); i++) {
					choices.add(majorBag.getMajors().get(i).toString());
				}
				
				ChoiceDialog<String> dialog = new ChoiceDialog<>( tempPerson.getMajor().toString(), choices);
				dialog.setTitle("Major Choice Dialog");
				dialog.setHeaderText("Majors");
				dialog.setContentText("Choose your new Major:");
				
				Optional<String> result = dialog.showAndWait();
				
				for(int i = 0; i < majorBag.getMajors().size(); i++) {
					if(dialog.getSelectedItem().equalsIgnoreCase(majorBag.getMajors().get(i).toString())) {
						tempPerson.setMajor(majorBag.getMajors().get(i));
						break;
					}
				}
				
				
				
				// Regular Sain setting
				String header = new String("Sain Report for "
						+ studentModel.getfName() + " "
						+ studentModel.getlName());
				mainWindow.setHeaderL(header);

				mainWindow.setProgramT((tempPerson).getProgram());
				mainWindow.setDegreeT((tempPerson).getDegree().getTitle());
				mainWindow.setMajorT((tempPerson).getMajor().getName());
				mainWindow.setCumulativeGPAT((tempPerson).getCumulativeGpa());
				mainWindow.setPGPAT((tempPerson).getProgramGPA());
				mainWindow.setMGPAT((tempPerson).getMajorGPA());
				mainWindow.setCampusT((tempPerson).getCampus());
				;

				ObservableList<String> items1 = FXCollections
						.observableArrayList(checkIfRequired(tempPerson,
								(tempPerson).getCourses()));
				mainWindow.getReqCTV().setItems(items1);

				ObservableList<String> items2 = FXCollections
						.observableArrayList(checkIfOther(tempPerson,
								(tempPerson).getCourses()));
				mainWindow.getOtherCTV().setItems(items2);

				ObservableList<String> items3 = FXCollections
						.observableArrayList(checkIfWithdrawn(tempPerson,
								(tempPerson).getCourses()));
				mainWindow.getWithdrawCTV().setItems(items3);

				ObservableList<String> items4 = FXCollections
						.observableArrayList(checkIfCurrent(tempPerson,
								(studentModel).getCourses()));
				mainWindow.getCurrentCTV().setItems(items4);

				ObservableList<String> items5 = FXCollections
						.observableArrayList(checkIfNeeded(tempPerson,
								(tempPerson).getCourses()));
				mainWindow.getNeededCV().setItems(items5);

				mainWindow.setMinGPAshow((tempPerson).getMajor().getMinGPA());
				mainWindow.setTotalCredsshow((tempPerson).getMajor()
						.getTotalCredit());
				mainWindow.setMinNumshow((tempPerson).getDegree()
						.getMinDegreeCred());
				mainWindow.disableTextFields();
				mainWindow.createSain();

			}
		});
	}

	public MainWindowController(PersonBag personbag, MajorBag majorBag,
			DegreeBag degreeBag, MainScreen mainWindow,
			Administrator admminModel, Student studentModel) {
		// If you are a Administrator Signing in, this is your Controller
		// Bottom "TaskBar" for Student
		createAdminTaskbar(mainWindow);

		// What If Event Handling
		mainWindow.setSearchButtonListener(new SearchButtonListener() {
			@Override
			public void searchButtonClicked(SearchButtonEventObject ev) {
				
			}
		});
	}

	public MainWindowController(PersonBag personbag, MajorBag majorBag,
			DegreeBag degreeBag, MainScreen mainWindow, Faculty facultyModel,
			Student studentModel) {
		// If you are a Administrator Signing in, this is your Controller

	}

	//
	// These Methods sift through the CoursesTaken Array and put them in the
	// appropriate ListView
	//
	// Check If Required
	public ArrayList<String> checkIfRequired(Student student,
			List<CoursesTaken> list) {
		// put the real into a temporary list that will remove items when taken
		List<CoursesTaken> tempList = new ArrayList<CoursesTaken>(list);
		ArrayList<String> returnList = new ArrayList<String>();

		List<Course> majorCourses = new ArrayList<Course>(); // array of courses
																// in the major
		majorCourses = student.getMajor().getCoursesList();

		List<String> majorTypes = new ArrayList<String>(); // array of subject
															// that are in the
															// major
		majorTypes = student.getMajor().getTypesArray();

		// Check if the course is directly required
		for (int i = 0; i < tempList.size(); i++) {
			String name = tempList.get(i).getCourseName();
			// Get a course name for students course
			for (int j = 0; j < majorCourses.size(); j++) {
				if (name.equalsIgnoreCase(majorCourses.get(j).getCourseName())) {
					String majorRequired = (student.getMajor().getName() + " Major Required");
					tempList.get(i).setCoursetype(majorRequired);
					returnList.add(tempList.get(i).toStringForRequired());
					tempList.remove(i);
					break;
				}
			}
		}
		// Check if the course is an elective subject
		for (int i = 0; i < tempList.size(); i++) {
			// get a course name for students course
			String name = tempList.get(i).getCoursetype();
			for (int j = 0; j < majorTypes.size(); j++) {
				if (name.equalsIgnoreCase(majorTypes.get(j))) {
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

	//
	//
	// Check if Other
	public ArrayList<String> checkIfOther(Student student,
			List<CoursesTaken> list) {

		List<CoursesTaken> tempList = new ArrayList<CoursesTaken>(list);
		ArrayList<String> returnList = new ArrayList<String>();
		String name = "Other";

		// Check if the course is an Other
		for (int i = 0; i < tempList.size(); i++) {
			if (name.equalsIgnoreCase(tempList.get(i).getCoursetype())) {
				returnList.add(tempList.get(i).toStringForOther());
				tempList.remove(i);
				break;
			}

		}
		return returnList;
	}

	//
	//
	// Check if Withdrawn
	public ArrayList<String> checkIfWithdrawn(Student student,
			List<CoursesTaken> list) {
		// put the real into a temporary list that will remove items when taken
		List<CoursesTaken> tempList = new ArrayList<CoursesTaken>(list);
		ArrayList<String> returnList = new ArrayList<String>();
		String grade1 = "W";
		String grade2 = "F";
		// Check If W
		for (int i = 0; i < tempList.size(); i++) {
			if (grade1.equalsIgnoreCase(tempList.get(i).getGrade())) {
				returnList.add(tempList.get(i).toStringForOther());
				tempList.remove(i);
				break;
			} // Check If F
			if (grade2.equalsIgnoreCase(tempList.get(i).getGrade())) {
				returnList.add(tempList.get(i).toStringForOther());
				tempList.remove(i);
				break;
			}

		}
		return returnList;
	}

	//
	// Check if Course is Current
	public ArrayList<String> checkIfCurrent(Student student,
			List<CoursesTaken> list) {
		// put the real into a temporary list that will remove items when taken
		List<CoursesTaken> tempList = new ArrayList<CoursesTaken>(list);
		ArrayList<String> returnList = new ArrayList<String>();
		String name = "IP";

		// Check if the course is an IP
		for (int i = 0; i < tempList.size(); i++) {
			if (name.equalsIgnoreCase(tempList.get(i).getGrade())) {
				returnList.add(tempList.get(i).toStringForOther());
				tempList.remove(i);
				break;
			}
		}
		return returnList;
	}

	//
	//
	// Check If Needed
	public ArrayList<String> checkIfNeeded(Student student,
			List<CoursesTaken> list) {
		// put the real into a temporary list that will remove items when taken
		List<CoursesTaken> tempList = new ArrayList<CoursesTaken>(list);
		ArrayList<String> returnList = new ArrayList<String>();

		List<Course> majorCourses = new ArrayList<Course>(); // array of courses
																// in the major
		majorCourses = student.getMajor().getCoursesList();

		List<String> majorTypes = new ArrayList<String>(); // array of subject
															// that are in the
															// major
		majorTypes = student.getMajor().getTypesArray();

		// Check if the course is directly required
		for (int i = 0; i < tempList.size(); i++) {
			String name = tempList.get(i).getCourseName();
			// Get a course name for students course
			for (int j = 0; j < majorCourses.size(); j++) {
				if (!name.equalsIgnoreCase(majorCourses.get(j).getCourseName())) {
					returnList.add(majorCourses.get(j).toStringForNeeded());
					tempList.remove(i);
					break;
				}
			}
		}
		// Check if the course is an elective subject
		for (int i = 0; i < tempList.size(); i++) {

			String name = tempList.get(i).getCoursetype(); // get a course name
															// for students
															// course

			for (int j = 0; j < majorTypes.size(); j++) {
				if (!name.equalsIgnoreCase(majorTypes.get(j))) {
					returnList.add(majorCourses.get(j).toStringForNeeded());
					tempList.remove(i);
					break;
				}
			}
		}
		return returnList;
	}

	// Find Total Credits Taken at SCC
	public double totalCredsAtSCCC(Student student, List<CoursesTaken> list) {
		return 0;
	}

	// Create Taskbars
	public void createStudentTaskbar(MainScreen mainWindow) {
		mainWindow
				.getBottom()
				.getChildren()
				.addAll(mainWindow.getSainReportButton(),
						mainWindow.getWhatIfButton());
		mainWindow.getRoot().setBottom(mainWindow.getBottom());
	}

	public void createAdminTaskbar(MainScreen mainWindow) {
		mainWindow
				.getBottom()
				.getChildren()
				.addAll(mainWindow.getSainReportButton(),
						mainWindow.getWhatIfButton(),
						mainWindow.getSearchButton(),
						mainWindow.getSaveButton());
		mainWindow.getRoot().setBottom(mainWindow.getBottom());
	}

	public void createFacultyTaskbar(MainScreen mainWindow) {
		mainWindow
				.getBottom()
				.getChildren()
				.addAll(mainWindow.getSainReportButton(),
						mainWindow.getWhatIfButton(),
						mainWindow.getSearchButton());
		mainWindow.getRoot().setBottom(mainWindow.getBottom());
	}

}
