package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainScreen extends Stage {
	// First Scene
	private BorderPane root;
	private Button whatIfButton;
	private Button sainReportButton;
	private VBox bottom;
	private SainReportButtonListener sainReportButtonListener;
	private WhatIfButtonListener whatIfButtonListener;
	
	// Sain Stuff
	Label programL;
	Label programT;
	Label degreeL;
	Label degreeT;
	Label majorL;
	Label majorT;
	Label cumulativeGPAL;
	Label cumulativeGPAT;
	Label pGPAL;
	Label pGPAT;
	Label mGPAL;
    Label mGPAT;
	Label campusL;
	Label entered;
	Label enteredL;
	Label enterT;
	Label catalogT;
	Label catalogF;
	Label evalTermL;
	Label evalTermF;
	Label reqCT;
	TableView reqCTV;
	Label otherCT;
	TableView otherCTV;
	Label withdrawCT;
	TableView withdrawCTV;
	Label currentCT;
	TableView currentCTV;
	Label minGPA;
	Label minGPAshow;
	Label totalCreds;
	Label totalCredsshow;
	Label minNum;
    Label minNumshow;
	Label totalCredsTaken;
	Label totalCredsTakenshow;
	Label totalTransferCreds;
	Label totalTransferCredsshow;
	Label totalCredsDeg;
	Label totalCredsDegshow;
	
	public MainScreen() {
		root = new BorderPane();
		Stage mainStage = new Stage();
		mainStage.setTitle("MySCCC Home Screen");
		
		
		sainReportButton = new Button("My Sain");
		whatIfButton = new Button("What If Analysis");
		
		bottom = new VBox(10);
		bottom.setPadding(new Insets(10, 10, 10, 10));
		bottom.setAlignment(Pos.BASELINE_CENTER);
		bottom.getChildren().addAll(sainReportButton, whatIfButton);
		
		root.setBottom(bottom);
		
		sainReportButton.setOnAction(e -> {
			SainReportButtonEventObject ev = new SainReportButtonEventObject(this);
			if (sainReportButtonListener != null) {
				sainReportButtonListener.sainReportButtonClicked(ev);
			}
		});
		
		whatIfButton.setOnAction(e -> {
			WhatIfButtonEventObject ev = new WhatIfButtonEventObject(this);
			if (whatIfButtonListener != null) {
				whatIfButtonListener.whatIfButtonClicked(ev);
			}
		});
		
		mainStage.setScene(new Scene(root, 700, 700));
		mainStage.show();
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
	public void setEntered(Label entered) {
		this.entered = entered;
	}
	public void setEnteredL(Label enteredL) {
		this.enteredL = enteredL;
	}
	public void setEnterT(Label enterT) {
		this.enterT = enterT;
	}
	public void setCatalogT(Label catalogT) {
		this.catalogT = catalogT;
	}
	public void setCatalogF(Label catalogF) {
		this.catalogF = catalogF;
	}
	public void setEvalTermL(Label evalTermL) {
		this.evalTermL = evalTermL;
	}
	public void setEvalTermF(Label evalTermF) {
		this.evalTermF = evalTermF;
	}
	public void setReqCT(Label reqCT) {
		this.reqCT = reqCT;
	}
	public void setReqCTV(TableView reqCTV) {
		this.reqCTV = reqCTV;
	}
	public void setOtherCT(Label otherCT) {
		this.otherCT = otherCT;
	}
	public void setOtherCTV(TableView otherCTV) {
		this.otherCTV = otherCTV;
	}
	public void setWithdrawCT(Label withdrawCT) {
		this.withdrawCT = withdrawCT;
	}
	public void setWithdrawCTV(TableView withdrawCTV) {
		this.withdrawCTV = withdrawCTV;
	}
	public void setCurrentCT(Label currentCT) {
		this.currentCT = currentCT;
	}
	public void setCurrentCTV(TableView currentCTV) {
		this.currentCTV = currentCTV;
	}
	public void setMinGPA(Label minGPA) {
		this.minGPA = minGPA;
	}
	public void setMinGPAshow(double d) {
		String string = Double.toString(d);
		minGPA.setText(string);
	}
	public void setTotalCreds(Label totalCreds) {
		this.totalCreds = totalCreds;
	}
	public void setTotalCredsshow(double d) {
		String string = Double.toString(d);
		totalCreds.setText(string);
	}
	public void setMinNum(Label minNum) {
		this.minNum = minNum;
	}
	public void setMinNumshow(Label minNumshow) {
		this.minNumshow = minNumshow;
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
