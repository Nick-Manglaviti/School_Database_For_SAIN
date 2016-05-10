package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainScreen extends Stage {
	// All the Boxes
	
	// Root
	private BorderPane root = new BorderPane();
	private Stage mainStage = new Stage(); // depending on the person Logged in
	
	//Top Pane
	private HBox headerBox= new HBox();
	private HBox infoBox = new HBox();
	private VBox infoLabelBox = new VBox();
	private VBox infoTextFieldBox = new VBox();
	
	
	// Center Pane
	private VBox centerPane = new VBox();
	// Courses Taken Views
	private HBox coursesTakenBox = new HBox(); 
	private VBox rctBox = new VBox();	//Required Courses Taken
	private VBox octBox = new VBox();	// Other Courses Taken
	private VBox cwfBox = new VBox();	// Courses Withdrawn/Failed
	private VBox ccBox = new VBox();		//Current Courses taking Box 
	// Courses Needed
	private HBox coursesNeededBox = new HBox();
	private VBox cnBox = new VBox();
	// Summary Box
	private HBox summaryBox = new HBox();
	private HBox summaryLeftBox = new HBox();
	private VBox summaryLeftLabelsBox = new VBox();
	private VBox summaryLeftTextFieldsBox = new VBox();
	private HBox summaryRightBox = new HBox();
	private VBox summaryRightLabelsBox = new VBox();
	private VBox summaryRightTextFieldsBox = new VBox();
	
	// Bottom Box
	private HBox bottom = new HBox(10);
	
	// Buttons 
	private Button sainReportButton = new Button("Generate Sain Report");
	private Button searchButton = new Button("Search For Student");
	private Button addCourseButton = new Button("Add Course");
	private Button whatIfButton = new Button("What If Analysis");
	private Button logoutButton = new Button("Logout");
	private Button changeMajorButton = new Button("Change Student Major");
	// Listeners
	private SainReportButtonListener sainReportButtonListener;
	private WhatIfButtonListener whatIfButtonListener;
	private SearchButtonListener searchButtonListener;
	private AddCourseButtonListener addCourseButtonListener;
	private LogoutButtonListener logoutButtonListener;
	private ChangeMajorButtonListener changeMajorButtonListener;
	
	
	// Labels
	private Label headerL = new Label();
	private Label programL = new Label("Program: ");
	private Label degreeL = new Label("Degree: ");
	private Label majorL = new Label("Major: ");
	private Label cumulativeGPAL = new Label("Cumulative GPA: ");
	private Label pGPAL = new Label("Program GPA: ");
	private Label mGPAL = new Label("Major GPA: ");
	private Label campusL = new Label("Campus: ");
	private Label reqCT = new Label("Required Courses Taken ");
	private Label otherCT = new Label("Other Courses Taken ");
	private Label withdrawCT = new Label("Courses Withdrawn ");
	private Label currentCT = new Label("Current Courses Taking ");
	private Label neededC = new Label("Courses Needed ");
	private Label minGPA = new Label("Minimum GPA: ");
	private Label totalCreds = new Label("Total Credits Required: ");
	private Label minNum = new Label("Minimum Number of Degree Credits: ");
	private Label totalCredsTakenC = new Label("Total Credits at SCCC");
	private Label totalCreditsDegree = new Label("Total Credits Toward Degree: ");
	
	//TextFields
	private TextField programT = new TextField();
	private TextField degreeT = new TextField();
	private TextField majorT = new TextField();
	private TextField cumulativeGPAT = new TextField();
	private TextField pGPAT = new TextField();
	private TextField mGPAT = new TextField();
	private TextField campusT = new TextField();
	private TextField minGPAshow = new TextField();
	private TextField totalCredsshow = new TextField();
	private TextField minNumshow = new TextField();
	private TextField totalCredsTakenCShow = new TextField();
	private TextField totalCreditsDegreeShow = new TextField();
	//ListViews
	private ListView reqCTV = new ListView();
	private ListView otherCTV = new ListView();
	private ListView withdrawCTV = new ListView();
	private ListView currentCTV = new ListView();
	private ListView neededCV = new ListView();
	
	
	
	
	public MainScreen() {
		root = new BorderPane(); // Set the Root, the rest is made by controller 
		mainStage.setTitle("MySCCC Home Screen");
		textFieldsResize();
		centerPane.setPadding(new Insets(10, 10, 10, 10));
		centerPane.setAlignment(Pos.CENTER);
		bottom.setPadding(new Insets(10, 10, 10, 10));
		bottom.setAlignment(Pos.BASELINE_CENTER);
		
		// Button Action Handling
		// SAIN Button
		sainReportButton.setOnAction(e -> {
			SainReportButtonEventObject ev = new SainReportButtonEventObject(this);
			if (sainReportButtonListener != null) {
				sainReportButtonListener.sainReportButtonClicked(ev);
			}
		});
		// What If Button
		whatIfButton.setOnAction(e -> {
			WhatIfButtonEventObject ev = new WhatIfButtonEventObject(this);
			if (whatIfButtonListener != null) {
				whatIfButtonListener.whatIfButtonClicked(ev);
			}
		});
		
		searchButton.setOnAction(e -> {
			SearchButtonEventObject ev = new SearchButtonEventObject(this);
			if (searchButtonListener != null) {
				searchButtonListener.searchButtonClicked(ev);
			}
		});
		addCourseButton.setOnAction(e -> {
			AddCourseButtonEventObject ev = new AddCourseButtonEventObject(this);
			if (addCourseButtonListener != null) {
				addCourseButtonListener.addCourseButtonClicked(ev);
			}
		});
		logoutButton.setOnAction(e -> {
			LogoutButtonEventObject ev = new LogoutButtonEventObject(this);
			if (logoutButtonListener != null) {
				logoutButtonListener.logoutButtonClicked(ev);
			}
		});
		changeMajorButton.setOnAction(e -> {
			ChangeMajorButtonEventObject ev = new ChangeMajorButtonEventObject(this);
			if (changeMajorButtonListener != null) {
				changeMajorButtonListener.changeMajorButtonClicked(ev);
			}
		});
		
		mainStage.setScene(new Scene(root, 1000, 700));
		mainStage.show();
	}
	
	public void disableTextFields() { // If its a student, the controller uses this method to disable the TextFields
		programT.setDisable(true);
		degreeT .setDisable(true);
		majorT.setDisable(true);
		cumulativeGPAT.setDisable(true);
		pGPAT.setDisable(true);
		mGPAT.setDisable(true);
		campusT.setDisable(true);
		minGPAshow.setDisable(true);
		totalCredsshow.setDisable(true);
		minNumshow.setDisable(true);
		totalCredsTakenCShow.setDisable(true);
		totalCreditsDegreeShow.setDisable(true);
	}

	
	public void textFieldsResize() { // Sets the size of the TextFields
		int width = 150;
		programT.setMaxWidth(width);
		degreeT.setMaxWidth(width);		
		majorT.setMaxWidth(width);
		cumulativeGPAT.setMaxWidth(width);
		pGPAT.setMaxWidth(width);
		mGPAT.setMaxWidth(width);
		campusT.setMaxWidth(width);
		minGPAshow.setMaxWidth(width);
		totalCredsshow.setMaxWidth(width);
		minNumshow.setMaxWidth(width);
	}
	
	public void createSain() {
	
		// Top Pane for SAIN Report
		headerBox = new HBox();
		headerL.setAlignment(Pos.CENTER);
		headerBox.getChildren().addAll(headerL);
		infoLabelBox = new VBox(8);
		infoLabelBox.getChildren().addAll(programL, degreeL, majorL, cumulativeGPAL, pGPAL, mGPAL, campusL);
		infoTextFieldBox = new VBox();
		infoTextFieldBox.getChildren().addAll(programT, degreeT, majorT, cumulativeGPAT, pGPAT, mGPAT, campusT);
		infoBox = new HBox();
		infoBox.getChildren().addAll(infoLabelBox, infoTextFieldBox);
		
		VBox topPane = new VBox();
		topPane.getChildren().addAll(headerBox, infoBox);
		root.setTop(topPane);
		
		// Center Pane
		// Courses Taken Views
		rctBox = new VBox();
		rctBox.getChildren().addAll(reqCT,reqCTV);
		octBox = new VBox();
		octBox.getChildren().addAll(otherCT, otherCTV);
		cwfBox = new VBox();
		cwfBox.getChildren().addAll(withdrawCT, withdrawCTV);
		ccBox = new VBox();
		ccBox.getChildren().addAll(currentCT, currentCTV);
		coursesTakenBox = new HBox();
		coursesTakenBox.setAlignment(Pos.CENTER);
		coursesTakenBox.getChildren().addAll(rctBox, octBox, cwfBox, ccBox);
		// Courses Needed View
		cnBox = new VBox();
		cnBox.getChildren().addAll(neededC, neededCV);
		coursesNeededBox = new HBox();
		coursesNeededBox.setAlignment(Pos.CENTER);
		coursesNeededBox.getChildren().addAll(cnBox);
		// Summary View
		summaryLeftLabelsBox = new VBox(8);
		summaryLeftLabelsBox.getChildren().addAll(minGPA,totalCreds,minNum);
		summaryLeftTextFieldsBox = new VBox();
		summaryLeftTextFieldsBox.getChildren().addAll(minGPAshow,totalCredsshow,minNumshow);
		summaryLeftBox = new HBox();
		summaryLeftBox.getChildren().addAll(summaryLeftLabelsBox, summaryLeftTextFieldsBox);
		summaryRightLabelsBox = new VBox(8);
		summaryRightLabelsBox.getChildren().addAll(totalCredsTakenC,totalCreditsDegree);
		summaryRightTextFieldsBox = new VBox();
		summaryRightTextFieldsBox.getChildren().addAll(totalCredsTakenCShow,totalCreditsDegreeShow);
		summaryRightBox = new HBox();
		summaryRightBox.getChildren().addAll(summaryRightLabelsBox, summaryRightTextFieldsBox);
		summaryBox = new HBox();
		summaryBox.getChildren().addAll(summaryLeftBox, summaryRightBox);
		centerPane = new VBox();
		centerPane.getChildren().addAll(coursesTakenBox, coursesNeededBox, summaryBox);
		root.setCenter(centerPane);
	}
	
	
	

	public Button getChangeMajorButton() {
		return changeMajorButton;
	}

	public void setChangeMajorButton(Button changeMajorButton) {
		this.changeMajorButton = changeMajorButton;
	}

	public ChangeMajorButtonListener getChangeMajorButtonListener() {
		return changeMajorButtonListener;
	}

	public void setChangeMajorButtonListener(
			ChangeMajorButtonListener changeMajorButtonListener) {
		this.changeMajorButtonListener = changeMajorButtonListener;
	}

	public Stage getMainStage() {
		return mainStage;
	}

	public void setMainStage(Stage mainStage) {
		this.mainStage = mainStage;
	}

	public Button getLogoutButton() {
		return logoutButton;
	}

	public void setLogoutButton(Button logoutButton) {
		this.logoutButton = logoutButton;
	}

	public LogoutButtonListener getLogoutButtonListener() {
		return logoutButtonListener;
	}

	public void setLogoutButtonListener(LogoutButtonListener logoutButtonListener) {
		this.logoutButtonListener = logoutButtonListener;
	}

	public SearchButtonListener getSearchButtonListener() {
		return searchButtonListener;
	}

	public void setSearchButtonListener(SearchButtonListener searchButtonListener) {
		this.searchButtonListener = searchButtonListener;
	}

	public AddCourseButtonListener getAddCourseButtonListener() {
		return addCourseButtonListener;
	}

	public void setAddCourseButtonListener(AddCourseButtonListener addCourseButtonListener) {
		this.addCourseButtonListener = addCourseButtonListener;
	}

	public SainReportButtonListener getSainReportButtonListener() {
		return sainReportButtonListener;
	}

	public WhatIfButtonListener getWhatIfButtonListener() {
		return whatIfButtonListener;
	}

	public Button getSainReportButton() {
		return sainReportButton;
	}

	public Button getWhatIfButton() {
		return whatIfButton;
	}

	public void setSearchButton(Button searchButton) {
		this.searchButton = searchButton;
	}

	public void setAddCourseButton(Button addCourseButton) {
		this.addCourseButton = addCourseButton;
	}

	public Button getSearchButton() {
		return searchButton;
	}

	public Button getAddCourseButton() {
		return addCourseButton;
	}
	public HBox getBottom() {
		return bottom;
	}

	public TextField getTotalCredsTakenCShow() {
		return totalCredsTakenCShow;
	}

	public void setTotalCredsTakenCShow(String string) {
		this.totalCredsTakenCShow.setText(string);
	}

	public TextField getTotalCreditsDegreeShow() {
		return totalCreditsDegreeShow;
	}

	public void setTotalCreditsDegreeShow(String sum2) {
		this.totalCreditsDegreeShow.setText(sum2);
	}


	public ListView getNeededCV() {
		return neededCV;
	}

	public void setNeededCV(ListView neededCV) {
		this.neededCV = neededCV;
	}

	public void setHeaderL(String string) {
		this.headerL.setText(string);
	}
	public void setSainReportButtonListener(SainReportButtonListener sainReportButtonListener){
		this.sainReportButtonListener = sainReportButtonListener;
	}
	public void setWhatIfButtonListener(WhatIfButtonListener whatIfButtonListener){
		this.whatIfButtonListener = whatIfButtonListener;
	}
	public void setRoot(BorderPane root) {
		this.root = root;
	}
	public BorderPane getRoot() {
		return root;
	}
	public void setWhatIfButton(Button whatIfButton) {
		this.whatIfButton = whatIfButton;
	}
	public void setSainReportButton(Button sainReportButton) {
		this.sainReportButton = sainReportButton;
	}
	public void setBottom(HBox bottom) {
		this.bottom = bottom;
	}
	public void setProgramL(Label programL) {
		this.programL = programL;
	}
	public void setProgramT(String string) {
		programT.setText(string);
	}
	public void setDegreeL(Label degreeL) {
		this.degreeL = degreeL;
	}
	public void setDegreeT(String string) {
		degreeT.setText(string);
	}
	public void setMajorL(Label majorL) {
		this.majorL = majorL;
	}
	public void setMajorT(String string) {
		majorT.setText(string);
	}
	public void setCumulativeGPAL(Label cumulativeGPAL) {
		this.cumulativeGPAL = cumulativeGPAL;
	}
	public void setCumulativeGPAT(double d) {
		String string = Double.toString(d);
		cumulativeGPAT.setText(string);
	}
	public void setpGPAL(Label pGPAL) {
		this.pGPAL = pGPAL;
	}
	public void setPGPAT(double d) {
		String string = Double.toString(d);
		pGPAT.setText(string);
	}
	public void setMGPAL(Label mGPAL) {
		this.mGPAL = mGPAL;
	}
	public void setMGPAT(double d) {
		String string = Double.toString(d);
		mGPAT.setText(string);
	}
	public void setCampusL(Label campusL) {
		this.campusL = campusL;
	}
	public TextField getCampusT() {
		return campusT;
	}

	public void setCampusT(String string) {
		campusT.setText(string);
	}



	public void setReqCT(Label reqCT) {
		this.reqCT = reqCT;
	}
	public void setMinGPA(Label minGPA) {
		this.minGPA = minGPA;
	}
	// get listviews
	public ListView getReqCTV() {
		return reqCTV;
	}
	public void setReqCTV(ListView reqCTV) {
		this.reqCTV = reqCTV;
	}
	public ListView getOtherCTV() {
		return otherCTV;
	}
	public void setOtherCTV(ListView otherCTV) {
		this.otherCTV = otherCTV;
	}
	public ListView getWithdrawCTV() {
		return withdrawCTV;
	}
	public void setWithdrawCTV(ListView withdrawCTV) {
		this.withdrawCTV = withdrawCTV;
	}
	public ListView getCurrentCTV() {
		return currentCTV;
	}
	public void setCurrentCTV(ListView currentCTV) {
		this.currentCTV = currentCTV;
	}
	public void setMinGPAshow(double d) {
		String string = Double.toString(d);
		minGPAshow.setText(string);
	}
	public void setTotalCreds(Label totalCreds) {
		this.totalCreds = totalCreds;
	}
	public void setTotalCredsshow(double d) {
		String string = Double.toString(d);
		totalCredsshow.setText(string);
	}
	public void setMinNum(Label minNum) {
		this.minNum = minNum;
	}
	public void setMinNumshow(double d) {
		String string = Double.toString(d);
		minNumshow.setText(string);
	}

	


	
	
}
