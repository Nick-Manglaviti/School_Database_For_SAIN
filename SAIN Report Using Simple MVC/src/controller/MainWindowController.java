package controller;

import java.text.DecimalFormat;
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
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import model.Administrator;
import model.Course;
import model.CourseBag;
import model.CoursesTaken;
import model.Degree;
import model.DegreeBag;
import model.Faculty;
import model.Major;
import model.MajorBag;
import model.Person;
import model.PersonBag;
import model.Student;
import view.AddCourseButtonEventObject;
import view.AddCourseButtonListener;
import view.ChangeMajorButtonEventObject;
import view.ChangeMajorButtonListener;
import view.LoginScreen;
import view.LogoutButtonEventObject;
import view.LogoutButtonListener;
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
			DegreeBag degreeBag, CourseBag coursesBag, MainScreen mainWindow, Student studentModel) {
		// If you are a Student Signing in, this is your Controller
		// Bottom "TaskBar" for Student
		createStudentTaskbar(mainWindow);
		// STUDENT LOGOUT
		mainWindow.setLogoutButtonListener(new LogoutButtonListener() {
			@Override
			public void logoutButtonClicked(LogoutButtonEventObject ev) {
				mainWindow.getMainStage().close();
				LoginScreen newloginScreen = new LoginScreen();
				LoginWindowController controller = new LoginWindowController(
						personbag, majorBag, degreeBag, coursesBag, newloginScreen);
			}

		});
		// My Sain Report Event Handling
		mainWindow.setSainReportButtonListener(new SainReportButtonListener() {
			@Override
			public void sainReportButtonClicked(SainReportButtonEventObject ev) {
				// Set Header
				fillFields(mainWindow, studentModel);
				mainWindow.createSain();
			}

		});
		// What If Event Handling
		mainWindow.setWhatIfButtonListener(new WhatIfButtonListener() {
			@Override
			public void whatIfButtonClicked(WhatIfButtonEventObject ev) {
				Student tempStudent = new Student().clone(studentModel);

				List<String> choices = new ArrayList<>();
				for (int i = 0; i < majorBag.getMajors().size(); i++) {
					choices.add(majorBag.getMajors().get(i).toString());
				}

				ChoiceDialog<String> dialog = new ChoiceDialog<>(tempStudent
						.getMajor().toString(), choices);
				dialog.setTitle("Major Choice Dialog");
				dialog.setHeaderText("Majors");
				dialog.setContentText("Choose your new Major:");

				Optional<String> result = dialog.showAndWait();

				for (int i = 0; i < majorBag.getMajors().size(); i++) {
					if (dialog.getSelectedItem().equalsIgnoreCase(
							majorBag.getMajors().get(i).toString())) {
						// set new student credentials
						tempStudent.setMajor(majorBag.getMajors().get(i));
						tempStudent.setDegree(degreeBag
								.searchForDegreeByMajor(majorBag.getMajors()
										.get(i).getName()));
						tempStudent.setProgram(((String) tempStudent.getMajor()
								.getName() + " " + tempStudent.getDegree()
								.getTitle()));
						break;
					}
				}

				fillFields(mainWindow, tempStudent);
				mainWindow.createSain();

			}
		});
	}

	public MainWindowController(PersonBag personbag, MajorBag majorBag,
			DegreeBag degreeBag, CourseBag coursesBag, MainScreen mainWindow, Administrator adminModel) {
		// If you are a Administrator Signing in, this is your Controller
		// Bottom "TaskBar" for Student
		createAdminTaskbar(mainWindow);

		mainWindow.setLogoutButtonListener(new LogoutButtonListener() {
			@Override
			public void logoutButtonClicked(LogoutButtonEventObject ev) {
				mainWindow.getMainStage().close();
				LoginScreen newloginScreen = new LoginScreen();
				LoginWindowController controller = new LoginWindowController(
						personbag, majorBag, degreeBag, coursesBag, newloginScreen);
			}

		});

		// searchButton
		mainWindow.setSearchButtonListener(new SearchButtonListener() {
			@Override
			public void searchButtonClicked(SearchButtonEventObject ev) {
				TextInputDialog dialog = new TextInputDialog("Enter ID");
				dialog.setTitle("Search Student");
				dialog.setHeaderText("Please Enter the Student's ID");
				dialog.setContentText("Search ID: ");

				// Traditional way to get the response value.
				Optional<String> result = dialog.showAndWait();

				int id = Integer.parseInt(dialog.getResult());

				Student originalStudent = (Student) personbag.searchById(id);

				mainWindow.createSain();
				fillFields(mainWindow, originalStudent);
			}
		});
		
		
		// Add course
		mainWindow.setAddCourseButtonListener(new AddCourseButtonListener() {
			@Override
			public void addCourseButtonClicked(AddCourseButtonEventObject ev) {
				TextInputDialog dialogFS = new TextInputDialog("Enter ID");
				dialogFS.setTitle("Search Student");
				dialogFS.setHeaderText("Please Enter the Student's ID");
				dialogFS.setContentText("Search ID: ");

				// Traditional way to get the response value.
				Optional<String> resultFS = dialogFS.showAndWait();

				int id = Integer.parseInt(dialogFS.getResult());

				Student originalStudent = (Student) personbag.searchById(id);

				List<String> choices = new ArrayList<>();
				for (int i = 0; i < coursesBag.getCourses().size(); i++) {
					choices.add(coursesBag.getCourses().get(i).toStringForNeeded());
				}

				ChoiceDialog<String> dialogFM = new ChoiceDialog<>("Choose Course", choices);
				dialogFM.setTitle("Course Choice Dialog");
				dialogFM.setHeaderText("Add a Course for " + originalStudent.getfName());
				dialogFM.setContentText("Add Course:");

				Optional<String> resultMF = dialogFM.showAndWait();

				for (int i = 0; i < coursesBag.getCourses().size(); i++) {
					if (dialogFM.getSelectedItem().equalsIgnoreCase(
							coursesBag.getCourses().get(i).toStringForNeeded())) {
						
						List<String> grades = new ArrayList<>();
						grades.add("A");
						grades.add("B");
						grades.add("C");
						grades.add("D");
						grades.add("F");
						grades.add("W");
						

						ChoiceDialog<String> dialogGrades = new ChoiceDialog<>("IP", grades);
						dialogGrades.setTitle("Course Choice Dialog");
						dialogGrades.setHeaderText("Add A Grade For " + coursesBag.getCourses().get(i).getCourseName());
						dialogGrades.setContentText("Grade: ");
						
						Optional<String> resultGrade = dialogGrades.showAndWait();
						
						// set new student credentials
						CoursesTaken newCourseTaken = new CoursesTaken(coursesBag.getCourses().get(i).getCourseName(),coursesBag.getCourses().get(i).getCourseNum(),
								coursesBag.getCourses().get(i).getCoursetype(), coursesBag.getCourses().get(i).getCredits(), 
								coursesBag.getCourses().get(i).getSubject(), dialogGrades.getResult());
						
						originalStudent.getCourses().add(newCourseTaken);
						break;
					}
				}

				mainWindow.createSain();
				fillFields(mainWindow, originalStudent);

			}
		});
		
	

		// Change Major
		mainWindow.setChangeMajorButtonListener(new ChangeMajorButtonListener() {
			@Override
			public void changeMajorButtonClicked(ChangeMajorButtonEventObject ev) {
				TextInputDialog dialogFS = new TextInputDialog("Enter ID");
				dialogFS.setTitle("Search Student");
				dialogFS.setHeaderText("Please Enter the Student's ID");
				dialogFS.setContentText("Search ID: ");

				// Traditional way to get the response value.
				Optional<String> resultFS = dialogFS.showAndWait();

				int id = Integer.parseInt(dialogFS.getResult());

				Student originalStudent = (Student) personbag.searchById(id);

				List<String> choices = new ArrayList<>();
				for (int i = 0; i < majorBag.getMajors().size(); i++) {
					choices.add(majorBag.getMajors().get(i).toString());
				}

				ChoiceDialog<String> dialogFM = new ChoiceDialog<>(originalStudent
						.getMajor().toString(), choices);
				dialogFM.setTitle("Major Choice Dialog");
				dialogFM.setHeaderText("Change " + originalStudent.getfName()
						+ " to which major?");
				dialogFM.setContentText("Choose new Major:");

				Optional<String> resultMF = dialogFM.showAndWait();

				for (int i = 0; i < majorBag.getMajors().size(); i++) {
					if (dialogFM.getSelectedItem().equalsIgnoreCase(
							majorBag.getMajors().get(i).toString())) {
						// set new student credentials
						originalStudent.setMajor(majorBag.getMajors().get(i));
						originalStudent.setDegree(degreeBag
								.searchForDegreeByMajor(majorBag.getMajors()
										.get(i).getName()));
						originalStudent.setProgram(((String) originalStudent
								.getMajor().getName() + " " + originalStudent
								.getDegree().getTitle()));
						break;
					}
				}

				mainWindow.createSain();
				fillFields(mainWindow, originalStudent);

			}
		});

		// What IF
		mainWindow.setWhatIfButtonListener(new WhatIfButtonListener() {
			@Override
			public void whatIfButtonClicked(WhatIfButtonEventObject ev) {
				TextInputDialog dialogFS = new TextInputDialog("Enter ID");
				dialogFS.setTitle("Search Student");
				dialogFS.setHeaderText("Please Enter the Student's ID");
				dialogFS.setContentText("Search ID: ");

				// Traditional way to get the response value.
				Optional<String> resultFS = dialogFS.showAndWait();

				int id = Integer.parseInt(dialogFS.getResult());

				Student originalStudent = (Student) personbag.searchById(id);
				Student newStudent = new Student(originalStudent.getfName(),
						originalStudent.getlName(), originalStudent.getId(),
						originalStudent.getUsername(), originalStudent
								.getPassword(), originalStudent.getPhone(),
						originalStudent.getAddress(), originalStudent
								.getCampus(), originalStudent.getProgram(),
						originalStudent.getDegree(),
						originalStudent.getMajor(), originalStudent
								.getCourses(), originalStudent
								.getCumulativeGpa(), originalStudent
								.getMajorGPA(), originalStudent.getProgramGPA());

				List<String> choices = new ArrayList<>();
				for (int i = 0; i < majorBag.getMajors().size(); i++) {
					choices.add(majorBag.getMajors().get(i).toString());
				}

				ChoiceDialog<String> dialogFM = new ChoiceDialog<>(newStudent
						.getMajor().toString(), choices);
				dialogFM.setTitle("Major Choice Dialog");
				dialogFM.setHeaderText("Change " + newStudent.getfName()
						+ " to which major?");
				dialogFM.setContentText("Choose new Major:");

				Optional<String> resultMF = dialogFM.showAndWait();

				for (int i = 0; i < majorBag.getMajors().size(); i++) {
					if (dialogFM.getSelectedItem().equalsIgnoreCase(
							majorBag.getMajors().get(i).toString())) {
						// set new student credentials
						newStudent.setMajor(majorBag.getMajors().get(i));
						newStudent.setDegree(degreeBag
								.searchForDegreeByMajor(majorBag.getMajors()
										.get(i).getName()));
						originalStudent.setProgram(((String) newStudent
								.getMajor().getName() + " " + newStudent
								.getDegree().getTitle()));
						break;
					}
				}

				mainWindow.createSain();
				fillFields(mainWindow, newStudent);

			}
		});
	}

	public MainWindowController(PersonBag personbag, MajorBag majorBag,
			DegreeBag degreeBag, CourseBag coursesBag, MainScreen mainWindow, Faculty facultyModel) {
		// If you are a Faculty Signing in, this is your Controller
		createFacultyTaskbar(mainWindow);

		mainWindow.setLogoutButtonListener(new LogoutButtonListener() {
			@Override
			public void logoutButtonClicked(LogoutButtonEventObject ev) {
				mainWindow.getMainStage().close();
				LoginScreen newloginScreen = new LoginScreen();
				LoginWindowController controller = new LoginWindowController(
						personbag, majorBag, degreeBag, coursesBag, newloginScreen);
			}

		});

		// searchButton
		mainWindow.setSearchButtonListener(new SearchButtonListener() {
			@Override
			public void searchButtonClicked(SearchButtonEventObject ev) {
				TextInputDialog dialog = new TextInputDialog("Enter ID");
				dialog.setTitle("Search Student");
				dialog.setHeaderText("Please Enter the Student's ID");
				dialog.setContentText("Search ID: ");

				// Traditional way to get the response value.
				Optional<String> result = dialog.showAndWait();

				int id = Integer.parseInt(dialog.getResult());

				Student originalStudent = (Student) personbag.searchById(id);

				mainWindow.createSain();
				fillFields(mainWindow, originalStudent);
			}
		});

		// What IF
		mainWindow.setWhatIfButtonListener(new WhatIfButtonListener() {
			@Override
			public void whatIfButtonClicked(WhatIfButtonEventObject ev) {
				TextInputDialog dialogFS = new TextInputDialog("Enter ID");
				dialogFS.setTitle("Search Student");
				dialogFS.setHeaderText("Please Enter the Student's ID");
				dialogFS.setContentText("Search ID: ");

				// Traditional way to get the response value.
				Optional<String> resultFS = dialogFS.showAndWait();

				int id = Integer.parseInt(dialogFS.getResult());

				Student originalStudent = (Student) personbag.searchById(id);
				Student newStudent = new Student(originalStudent.getfName(),
						originalStudent.getlName(), originalStudent.getId(),
						originalStudent.getUsername(), originalStudent
								.getPassword(), originalStudent.getPhone(),
						originalStudent.getAddress(), originalStudent
								.getCampus(), originalStudent.getProgram(),
						originalStudent.getDegree(),
						originalStudent.getMajor(), originalStudent
								.getCourses(), originalStudent
								.getCumulativeGpa(), originalStudent
								.getMajorGPA(), originalStudent.getProgramGPA());

				List<String> choices = new ArrayList<>();
				for (int i = 0; i < majorBag.getMajors().size(); i++) {
					choices.add(majorBag.getMajors().get(i).toString());
				}

				ChoiceDialog<String> dialogFM = new ChoiceDialog<>(newStudent
						.getMajor().toString(), choices);
				dialogFM.setTitle("Major Choice Dialog");
				dialogFM.setHeaderText("Change " + newStudent.getfName()
						+ " to which major?");
				dialogFM.setContentText("Choose new Major:");

				Optional<String> resultMF = dialogFM.showAndWait();

				for (int i = 0; i < majorBag.getMajors().size(); i++) {
					if (dialogFM.getSelectedItem().equalsIgnoreCase(
							majorBag.getMajors().get(i).toString())) {
						// set new student credentials
						newStudent.setMajor(majorBag.getMajors().get(i));
						newStudent.setDegree(degreeBag
								.searchForDegreeByMajor(majorBag.getMajors()
										.get(i).getName()));
						originalStudent.setProgram(((String) newStudent
								.getMajor().getName() + " " + newStudent
								.getDegree().getTitle()));
						break;
					}
				}

				mainWindow.createSain();
				fillFields(mainWindow, newStudent);

			}
		});
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

		List<Course> majorCourses = new ArrayList<Course>(student.getMajor()
				.getCoursesList()); // array of courses

		List<String> majorTypes = new ArrayList<String>(student.getMajor()
				.getTypesArray()); // array of subject

		double sum = 0;
		// Check if the course is directly required
		for (int i = 0; i < tempList.size(); i++) {
			for (int j = 0; j < majorCourses.size(); j++) {
				if (tempList.get(i).getCourseName()
						.equalsIgnoreCase(majorCourses.get(j).getCourseName())) {
					returnList.add(tempList.get(i).toStringForRequired()
							+ "Major Required");
					sum += tempList.get(i).getCredits();
					tempList.remove(i);
					break;
				}
			}
		}
		// Check if the course is an elective subject
		for (int i = 0; i < tempList.size(); i++) {
			for (int j = 0; j < majorTypes.size(); j++) {
				if (tempList.get(i).getCoursetype()
						.equalsIgnoreCase(majorTypes.get(j))) {
					returnList.add(tempList.get(i).toStringForRequired()
							+ " Elective");
					sum += tempList.get(i).getCredits();
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

		// put the real into a temporary list that will remove items when taken
		List<CoursesTaken> tempList = new ArrayList<CoursesTaken>();
		for (int i = 0; i < list.size(); i++) {
			tempList.add(list.get(i));
		}
		ArrayList<String> returnList = new ArrayList<String>();

		List<Course> majorCourses = new ArrayList<Course>(); // array of courses
		for (int i = 0; i < student.getMajor().getCoursesList().size(); i++) {
			majorCourses.add(student.getMajor().getCoursesList().get(i));

		}

		List<String> majorTypes = new ArrayList<String>(student.getMajor()
				.getTypesArray()); // array of subject
		for (int i = 0; i < student.getMajor().getTypesArray().size(); i++) {
			majorTypes.add(student.getMajor().getTypesArray().get(i));

		}

		for (int i = 0; i < tempList.size(); i++) {
			// Check if a name matches
			for (int j = 0; j < majorCourses.size(); j++) {
				if (tempList.get(i).getCourseName()
						.equals(majorCourses.get(j).getCourseName())) {
					tempList.remove(i);
					break;
				}
			}
		}
		for (int i = 0; i < tempList.size(); i++) {
			// Check if the Subjects matches
			for (int j = 0; j < majorTypes.size(); j++) {
				if (tempList.get(i).getCoursetype()
						.equalsIgnoreCase(majorTypes.get(j))) {
					tempList.remove(i);
					break;
				}
			}
		}
		// Check if the course is an IP
		String name = "IP";
		for (int i = 0; i < tempList.size(); i++) {
			if (name.equalsIgnoreCase(tempList.get(i).getGrade())) {
				returnList.add(tempList.get(i).toStringForOther());
				tempList.remove(i);
			}
		}
		// Put into the return list with proper toString
		for (int i = 0; i < tempList.size(); i++) {
			returnList.add(i, tempList.get(i).toStringForOther());
		}
		return returnList;
	}

	//
	//
	// Check if Withdrawn
	public ArrayList<String> checkIfWithdrawn(Student student,
			List<CoursesTaken> list) {
		// put the real into a temporary list that will remove items when taken
		List<CoursesTaken> tempList = new ArrayList<CoursesTaken>();
		for (int i = 0; i < list.size(); i++) {
			tempList.add(list.get(i));
		}

		ArrayList<String> returnList = new ArrayList<String>();
		String grade1 = "W";
		String grade2 = "F";
		// Check If W
		for (int i = 0; i < tempList.size(); i++) {
			if (grade1.equalsIgnoreCase(tempList.get(i).getGrade())) {
				returnList.add(tempList.get(i).toStringForOther());
			} // Check If F
			else if (grade2.equalsIgnoreCase(tempList.get(i).getGrade())) {
				returnList.add(tempList.get(i).toStringForOther());
				tempList.remove(i);
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
		List<CoursesTaken> tempList = new ArrayList<CoursesTaken>();
		for (int i = 0; i < list.size(); i++) {
			tempList.add(list.get(i));
		}
		ArrayList<String> returnList = new ArrayList<String>();

		List<Course> tempMajorCourses = new ArrayList<Course>(student
				.getMajor().getCoursesList()); // array of courses
												// in the major

		List<String> tempMajorTypes = new ArrayList<String>(student.getMajor()
				.getTypesArray()); // array of subject
		// that are in the
		// major

		// Check if the course is directly required
		for (int i = 0; i < tempList.size(); i++) {
			String name = tempList.get(i).getCourseName();
			// Get a course name for students course
			for (int j = 0; j < tempMajorCourses.size(); j++) {
				if (name.equalsIgnoreCase(tempMajorCourses.get(j)
						.getCourseName())) {
					tempMajorCourses.remove(j);
					tempList.remove(i);
					break;
				}
			}
		}
		// Add the Courses Directly Needed
		for (int i = 0; i < tempMajorCourses.size(); i++) {
			returnList.add(i, tempMajorCourses.get(i).toStringForNeeded());
		}
		// Check if the course is an elective subject
		for (int i = 0; i < tempList.size(); i++) {

			String name = tempList.get(i).getCoursetype(); // get a course name
															// for students
															// course

			for (int j = 0; j < tempMajorTypes.size(); j++) {
				if (name.equalsIgnoreCase(tempMajorTypes.get(j))) {
					tempMajorTypes.remove(j);
					tempList.remove(i);
					break;
				}
			}
		}
		// Add the Courses Directly Needed
		for (int i = 0; i < tempMajorTypes.size(); i++) {
			returnList.add(i, tempMajorTypes.get(i).toString() + " Elective");
		}
		return returnList;
	}

	// Create Taskbars
	public void createStudentTaskbar(MainScreen mainWindow) {
		mainWindow
				.getBottom()
				.getChildren()
				.addAll(mainWindow.getLogoutButton(),
						mainWindow.getSainReportButton(),
						mainWindow.getWhatIfButton());
		mainWindow.getRoot().setBottom(mainWindow.getBottom());
	}

	public void createAdminTaskbar(MainScreen mainWindow) {
		mainWindow
				.getBottom()
				.getChildren()
				.addAll(mainWindow.getLogoutButton(),
						mainWindow.getWhatIfButton(),
						mainWindow.getSearchButton(),
						mainWindow.getAddCourseButton(),
						mainWindow.getChangeMajorButton() );
		mainWindow.getRoot().setBottom(mainWindow.getBottom());
	}

	public void createFacultyTaskbar(MainScreen mainWindow) {
		mainWindow
				.getBottom()
				.getChildren()
				.addAll(mainWindow.getLogoutButton(),
						mainWindow.getWhatIfButton(),
						mainWindow.getSearchButton());
		mainWindow.getRoot().setBottom(mainWindow.getBottom());
	}

	public void fillFields(MainScreen window, Student student) {
		DecimalFormat df = new DecimalFormat(".##");
		
		// Regular Sain setting
		String header = new String("Sain Report for " + student.getfName()
				+ " " + student.getlName());
		window.setHeaderL(header);

		window.setProgramT((student).getProgram());
		window.setDegreeT((student).getDegree().getTitle());
		window.setMajorT((student).getMajor().getName());
		window.setCumulativeGPAT((student).getCumulativeGpa());
		window.setPGPAT((student).getProgramGPA());
		window.setMGPAT((student).getMajorGPA());
		window.setCampusT((student).getCampus());
		;

		ObservableList<String> items1 = FXCollections
				.observableArrayList(checkIfRequired(student,
						(student).getCourses()));
		window.getReqCTV().setItems(items1);

		ObservableList<String> items2 = FXCollections
				.observableArrayList(checkIfOther(student,
						(student).getCourses()));
		window.getOtherCTV().setItems(items2);

		ObservableList<String> items3 = FXCollections
				.observableArrayList(checkIfWithdrawn(student,
						(student).getCourses()));
		window.getWithdrawCTV().setItems(items3);

		ObservableList<String> items4 = FXCollections
				.observableArrayList(checkIfCurrent(student,
						(student).getCourses()));
		window.getCurrentCTV().setItems(items4);

		ObservableList<String> items5 = FXCollections
				.observableArrayList(checkIfNeeded(student,
						(student).getCourses()));
		window.getNeededCV().setItems(items5);

		window.setMinGPAshow((student).getMajor().getMinGPA());
		window.setTotalCredsshow((student).getMajor().getTotalCredit());
		window.setMinNumshow((student).getDegree().getMinDegreeCred());
		window.disableTextFields();
		double sum = 0;
		for (int i = 0; i < student.getCourses().size(); i++) {
			if (student.getCourses().get(i).getGrade().equals("W")) {

			} else {
				sum = student.getCourses().get(i).getCredits() + sum;
			}
		}
		window.setTotalCredsTakenCShow(Double.toString(sum));
		window.setTotalCreditsDegreeShow(Double
				.toString(calculateTotalCredsDeg(student, student.getCourses())));
	}


	public double calculateTotalCredsDeg(Student student,
			List<CoursesTaken> list) {
		// put the real into a temporary list that will remove items when taken
		List<CoursesTaken> tempList = new ArrayList<CoursesTaken>(list);

		ArrayList<String> returnList = new ArrayList<String>();

		List<Course> majorCourses = new ArrayList<Course>(student.getMajor()
				.getCoursesList()); // array of courses

		List<String> majorTypes = new ArrayList<String>(student.getMajor()
				.getTypesArray()); // array of subject

		double sum = 0;
		// Check if the course is directly required
		for (int i = 0; i < tempList.size(); i++) {
			for (int j = 0; j < majorCourses.size(); j++) {
				if (tempList.get(i).getCourseName()
						.equalsIgnoreCase(majorCourses.get(j).getCourseName())) {
					sum = tempList.get(i).getCredits() + sum;
					returnList.add(tempList.get(i).toStringForRequired()
							+ "Major Required");
					tempList.remove(i);
					break;
				}
			}
		}
		// Check if the course is an elective subject
		for (int i = 0; i < tempList.size(); i++) {
			for (int j = 0; j < majorTypes.size(); j++) {
				if (tempList.get(i).getCoursetype()
						.equalsIgnoreCase(majorTypes.get(j))) {
					sum = tempList.get(i).getCredits() + sum;
					returnList.add(tempList.get(i).toStringForRequired()
							+ " Elective");
					tempList.remove(i);
					break;
				}
			}
		}

		return sum;
	}

}
