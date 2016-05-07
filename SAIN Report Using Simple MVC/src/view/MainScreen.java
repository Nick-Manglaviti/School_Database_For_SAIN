package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
	private BorderPane root;
	
	//Top Pane
	private VBox topPane;
	private HBox headerBox;
	private HBox infoBox;
	private VBox infoLabelBox;
	private VBox infoTextFieldBox;
	
	
	// Center Pane
	private VBox centerPane;
	// Courses Taken Views
	private HBox coursesTakenBox; 
	private VBox rctBox;	//Required Courses Taken
	private VBox octBox;	// Other Courses Taken
	private VBox cwfBox;	// Courses Withdrawn/Failed
	private VBox ccBox;		//Current Courses taking Box 
	// Courses Needed
	private HBox coursesNeededBox;
	private VBox cnBox;
	// Summary Box
	private HBox summaryBox;
	private VBox summaryLeftBox;
	private VBox summaryLeftLabelsBox;
	private VBox summaryLeftTextFieldsBox;
	private VBox summaryRightBox;
	private VBox summaryRightLabelsBox;
	private VBox summaryRightTextFieldsBox;
	
	// Bottom Box
	private VBox bottom;
	
	// Buttons 
	private Button whatIfButton;
	private Button sainReportButton;
	// Listeners
	private SainReportButtonListener sainReportButtonListener;
	private WhatIfButtonListener whatIfButtonListener;
	
	
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
	//ListViews
	private ListView reqCTV = new ListView();
	private ListView otherCTV = new ListView();
	private ListView withdrawCTV = new ListView();
	private ListView currentCTV = new ListView();
	private ListView neededCV = new ListView();
	
	
	
	
	public MainScreen() {
		root = new BorderPane();
		Stage mainStage = new Stage();
		mainStage.setTitle("MySCCC Home Screen");
		textFieldsResize();
		
		// bottom "TaskBar" for the main view
		sainReportButton = new Button("My Sain");
		whatIfButton = new Button("What If Analysis");
		bottom = new VBox(10);
		bottom.setPadding(new Insets(10, 10, 10, 10));
		bottom.setAlignment(Pos.BASELINE_CENTER);
		bottom.getChildren().addAll(sainReportButton, whatIfButton);
		root.setBottom(bottom);
		
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
		
		mainStage.setScene(new Scene(root, 700, 700));
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
		coursesTakenBox.getChildren().addAll(rctBox, octBox, cwfBox, ccBox);
		// Courses Needed View
		cnBox = new VBox();
		cnBox.getChildren().addAll(neededC, neededCV);
		coursesNeededBox = new HBox();
		coursesNeededBox.getChildren().addAll(rctBox, octBox, cwfBox, ccBox);
		// Summary View
		summaryLeftLabelsBox = new VBox();
		summaryLeftLabelsBox.getChildren().addAll(minGPA,totalCreds,minNum);
		summaryLeftTextFieldsBox = new VBox();
		summaryLeftTextFieldsBox.getChildren().addAll(minGPAshow,totalCredsshow,minNumshow);
		summaryLeftBox = new VBox();
		summaryLeftBox.getChildren().addAll(summaryLeftLabelsBox, summaryLeftTextFieldsBox);
		summaryBox = new HBox();
		summaryBox.getChildren().addAll(summaryLeftBox);
		centerPane = new VBox();
		centerPane.getChildren().addAll(coursesTakenBox, coursesNeededBox, summaryBox);
		root.setCenter(centerPane);
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
	public void setBottom(VBox bottom) {
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
		majorT.setText(string);
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
	public void setTotalCredsTaken(Label totalCredsTaken) {
		this.totalCredsTaken = totalCredsTaken;
	}
	public void setTotalCredsTakenshow(Label totalCredsTakenshow) {
		this.totalCredsTakenshow = totalCredsTakenshow;
	}
	public void setTotalTransferCreds(Label totalTransferCreds) {
		this.totalTransferCreds = totalTransferCreds;
	}
	public void setTotalTransferCredsshow(Label totalTransferCredsshow) {
		this.totalTransferCredsshow = totalTransferCredsshow;
	}
	public void setTotalCredsDeg(Label totalCredsDeg) {
		this.totalCredsDeg = totalCredsDeg;
	}
	public void setTotalCredsDegshow(Label totalCredsDegshow) {
		this.totalCredsDegshow = totalCredsDegshow;
	}

	
}
