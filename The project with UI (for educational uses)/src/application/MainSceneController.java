package application;

import javafx.animation.*;
import javafx.application.Application;
import javafx.beans.property.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import com.jfoenix.controls.*;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import application.Process;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.control.cell.ProgressBarTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class MainSceneController implements Initializable, ChangeListener {
	// public static CountDownLatch latch = new CountDownLatch(1);
	public static boolean continueProcessing = false;

	public static int _contextSwitchText;
	public static int _processesNumber;
	public static int _DOM;
	public static int _burstsNumbre;
	public static int _minCPU;
	public static int _maxCPU;
	public static int _minIO;
	public static int _maxIO;
	public static int _minPrio;
	public static int _maxPrio;
	public static int _minArrival;
	public static int _maxArrival;
	public static double _alpha;
	public static int _tau;
	Timeline timeline = new Timeline();
	boolean statusFlag = true;
	boolean generated = false;
	boolean flag = false;
	boolean flag2 = true;
	boolean isSelected = true;
	public static Pane algorithmPane2;
	@FXML
	VBox changesVbox;
	@FXML
	JFXButton logButton;
	@FXML
	JFXButton changesButton;
	@FXML
	VBox jobsVBOX;
	@FXML
	private Pane devicesPane;
	@FXML
	private Pane jobsPane;
	@FXML
	private Pane algorithmPane;
	@FXML
	private JFXButton nextButton;
	@FXML
	private JFXButton animate;
	@FXML
	private JFXButton jobsMenu;
	@FXML
	private JFXButton devicesMenu;
	boolean flag20 = true;

	@FXML
	private void showJobs(ActionEvent e) {
		if (!flag20) {
			flag20 = !flag20;
			JFXButton temp = new JFXButton();
			temp.setStyle(jobsMenu.getStyle());
			jobsMenu.setStyle(devicesMenu.getStyle());
			devicesMenu.setStyle(temp.getStyle());
			jobsPane.setVisible(flag20);
			devicesPane.setVisible(!flag20);
		}
	}

	@FXML
	Pane changesPane;

	@FXML
	private void showDevices(ActionEvent e) {
		if (flag20) {
			devicesPane.setVisible(flag20);
			jobsPane.setVisible(!flag20);
			flag20 = !flag20;
			JFXButton temp = new JFXButton();
			temp.setStyle(jobsMenu.getStyle());
			jobsMenu.setStyle(devicesMenu.getStyle());
			devicesMenu.setStyle(temp.getStyle());
		}
	}

	boolean floog = true;

	@FXML
	private void showLog(ActionEvent e) {
		if (!floog) {
			floog = true;
			JFXButton temp = new JFXButton();
			temp.setStyle(changesButton.getStyle());
			changesButton.setStyle(logButton.getStyle());
			logButton.setStyle(temp.getStyle());
			logField.setVisible(true);
			changesPane.setVisible(false);
		}
	}

	@FXML
	private void showChanges(ActionEvent e) {
		if (floog) {
			floog = false;
			JFXButton temp = new JFXButton();
			temp.setStyle(changesButton.getStyle());
			changesButton.setStyle(logButton.getStyle());
			logButton.setStyle(temp.getStyle());
			logField.setVisible(false);
			changesPane.setVisible(true);
		}

	}

	@FXML
	private ScrollPane devicesList;

	@FXML
	private VBox devicesVBOX;
	@FXML
	private ScrollPane readyList;
	@FXML
	VBox readyVBOX;
	@FXML
	private Label algoName;
	// @FXML
	// private JFXSlider speedBar;
	@FXML
	private Label systemTimeLabel;
	@FXML
	private JFXButton nextChangeButton;
	@FXML
	private JFXTextArea logField;
	@FXML
	private Pane menuPane;
	@FXML
	private Pane statusPane;
	@FXML
	private Label cpuUtilizationLabel;
	@FXML
	private JFXProgressBar totalProgress;
	@FXML
	private JFXProgressBar currentProgress;
	@FXML
	private Label throughputLabel;
	@FXML
	private JFXTextField quantumText;
	@FXML
	private Label TurnaroundLabel;
	@FXML
	private Label waitingLabdel;
	@FXML
	private JFXButton showMenu;
	// public FirstComeFirstServed obj;
	// public RoundRobin obj2;
	@FXML
	private ToggleGroup algorithms;

	@FXML
	private void enableQuantum(ActionEvent e) {
		quantumText.setDisable(false);
	}

	@FXML
	private void disableQuantum(ActionEvent e) {
		quantumText.setDisable(true);
		quantumText.setStyle("-fx-background-color: #F1EAD1 ;");
		quantumText.setText("");
	}

	@FXML
	private void showStatus(ActionEvent e) {
		if (statusFlag) {
			statusFlag = !statusFlag;
			statusPane.setLayoutY(235);
		} else {
			statusFlag = !statusFlag;
			statusPane.setLayoutY(525);
		}
	}

	public static void showAlgorithms() {
		algorithmPane2.setVisible(true);
	}

	@FXML
	JFXSlider speedBar;

	@FXML
	private void animation(ActionEvent ev) {
		System.out.println(speedBar.getValue());
		timeline.stop();

		ani(speedBar.getValue());
	}

	public void ani(double speed) {
		timeline = new Timeline();
		// System.out.println(speed);
		KeyFrame keyFrame = new KeyFrame(Duration.seconds(speed != 0 ? 1 / speed : Double.MAX_VALUE), e -> {
			// System.out.println("Speed === " + (100 - speedBar.getValue()) + "");
			// System.out.println("Speed === " + speed);

			String[][] status = object.performAnIteration(algorithmType, quantum);// NOTE
			systemTimeLabel.setText((object.getCurrentTime(algorithmType) - 1) + "");
			if (status[1][3] != null) {
				String[] s = status[1][3].split(",");
				// System.out.println(s[0]);
				HBox h = styleHbox(new HBox(), 87);
				h.getChildren().addAll(styleLabel(new Label(s[0]),100),styleLabel(new Label(s[2]),150),styleLabel(new Label(s[1]),100));
				changesVbox.getChildren().add(0,h);
			}
			if (status[1][1] != null) {
				displayReadyQueue();
				displayDeviceList();
				displayJobs();
				if (object.getRunningState(algorithmType) != null) {
					// System.out.println(object.getRunningState(algorithmType).getTotalProcessPercentage());
					processIDLabel.setText(object.getRunningState(algorithmType).pID + "");
					totalProgress.setProgress(object.getRunningState(algorithmType).getTotalProcessPercentage());
					currentProgress.setProgress(object.getRunningState(algorithmType).getBurstPercentage());

				} else {
					processIDLabel.setText("NULL");
					// clear both the burst and total progress
					totalProgress.setProgress(0);
					currentProgress.setProgress(0);
				}

				printingString(status[1][0]);
				// System.out.println(status[1][0]);
				cpuUtilizationLabel.setText(status[0][0]);
				throughputLabel.setText(status[0][1]);
				TurnaroundLabel.setText(status[0][2] + "");
				waitingLabdel.setText(status[0][3] + "");
			} else {

				if (object.getRunningState(algorithmType) != null) {

					processIDLabel.setText(object.getRunningState(algorithmType).pID + "");
					totalProgress.setProgress(object.getRunningState(algorithmType).getTotalProcessPercentage());
					currentProgress.setProgress(object.getRunningState(algorithmType).getBurstPercentage());

				} else {
					processIDLabel.setText("NULL");
					totalProgress.setProgress(0);
					currentProgress.setProgress(0);
				}

				printingString(status[1][0]);
				cpuUtilizationLabel.setText(status[0][0]);
				throughputLabel.setText(status[0][1]);
				TurnaroundLabel.setText(status[0][2] + "");
				waitingLabdel.setText(status[0][3] + "");
				nextButton.setDisable(true);
				timeline.stop();

			}

		});

		timeline.getKeyFrames().add(keyFrame);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();

	}

	@FXML
	private void nextStep(ActionEvent event) {
		String[][] status = object.performAnIteration(algorithmType, quantum);
		systemTimeLabel.setText((object.getCurrentTime(algorithmType) - 1) + "");
		if (status[1][3] != null) {
			String[] s = status[1][3].split(",");
			HBox h = styleHbox(new HBox(), 87);
			h.getChildren().addAll(styleLabel(new Label(s[0]),100),styleLabel(new Label(s[2]),150),styleLabel(new Label(s[1]),100));
			changesVbox.getChildren().add(0,h);
		}
		if (status[1][1] != null) {
			displayReadyQueue();// cool
			displayDeviceList();
			displayJobs();
			if (object.getRunningState(algorithmType) != null) {
				processIDLabel.setText(object.getRunningState(algorithmType).pID + "");
				totalProgress.setProgress(object.getRunningState(algorithmType).getTotalProcessPercentage());
				currentProgress.setProgress(object.getRunningState(algorithmType).getBurstPercentage());
			} else {
				processIDLabel.setText("NULL");
				totalProgress.setProgress(0);
				currentProgress.setProgress(0);
			}

			printingString(status[1][0]);
			cpuUtilizationLabel.setText(status[0][0]);
			throughputLabel.setText(status[0][1]);
			TurnaroundLabel.setText(status[0][2] + "");
			waitingLabdel.setText(status[0][3] + "");
		} else {

			if (object.getRunningState(algorithmType) != null) {
				processIDLabel.setText(object.getRunningState(algorithmType).pID + "");
				totalProgress.setProgress(object.getRunningState(algorithmType).getTotalProcessPercentage());
				currentProgress.setProgress(object.getRunningState(algorithmType).getBurstPercentage());
			} else {
				processIDLabel.setText("NULL");
				totalProgress.setProgress(0);
				currentProgress.setProgress(0);

			}

			printingString(status[1][0]);
			cpuUtilizationLabel.setText(status[0][0]);
			throughputLabel.setText(status[0][1]);
			TurnaroundLabel.setText(status[0][2] + "");
			waitingLabdel.setText(status[0][3] + "");
			nextButton.setDisable(true);
			nextChangeButton.setDisable(true);
		}
		object.setDisplayInfo2(false);

	}

	boolean mark = true;

	@FXML
	private void initialProcesses() throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("initialState.fxml"));
		Scene scene = new Scene(root);
		// scene.getStylesheets().add("application/application.css");
		Stage stage = new Stage();
		stage.setTitle("Initial Processes");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
		stage.setAlwaysOnTop(true);
	}

	@FXML
	private void showMenuFunc(ActionEvent event) {
		if (flag2) {
			flag2 = !flag2;
			showMenu.setText("HIDE MENU");
			menuPane.setLayoutX(-9);
			menuPane.setLayoutY(235);
		} else {
			flag2 = !flag2;
			showMenu.setText("SHOW MENU");
			menuPane.setLayoutX(-9);
			menuPane.setLayoutY(525);
		}
	}

	Queue<Process> jobQueue;
	public static PickAlgo object;
	static int algorithmType;
	int quantum;

	@FXML
	private void confirmAlgorithm(ActionEvent e) {
		boolean flag2002 = true;
		if (!quantumText.isDisable()) {
			try {
				quantum = Integer.parseInt(quantumText.getText());
				flag2002 = true;
				if (quantum <= _contextSwitchText) {
					throw new Exception();
				}
				quantumText.setStyle("-fx-background-color: #F1EAD1 ;");
			} catch (Exception eaa) {
				quantumText.setStyle("-fx-background-color: #ff6347;");
				flag2002 = false;
			}
		}
		if (flag2002) {
			algorithmPane2.setVisible(false);
			algorithmType = -1;
			isSelected = true;
			String selected = ((Labeled) algorithms.getSelectedToggle()).getText();
			if (selected.equalsIgnoreCase("FCFS")) {
				algorithmType = 0;
				algoName.setText("FCFS");
			} else if (selected.equalsIgnoreCase("Shortest Job First")) {
				algorithmType = 1;
				algoName.setText("Shortest Job First");
			} else if (selected.equalsIgnoreCase("Shortest Remaining Job First")) {
				algorithmType = 5;
				algoName.setText("Shortest Remaining Job First");

			} else if (selected.equalsIgnoreCase("Priority")) {
				algorithmType = 4;
				algoName.setText("Priority");

			} else if (selected.equalsIgnoreCase("Round Robin")) {
				algoName.setText("Round Robin (" + quantum + ")");
				algorithmType = 2;
			} else if (selected.equalsIgnoreCase("Exponantial Average SRJF")) {
				algoName.setText("Exponantial Average SRJF");
				algorithmType = 3;
			} else {
				isSelected = false;
			}
			if (isSelected) {
				object = new PickAlgo(algorithmType, jobQueue, _DOM, _alpha, _contextSwitchText);
				dJobs();
			}
		}
	}

	@FXML
	void generateFunction(ActionEvent event) throws Exception {
		quantumText.setStyle("-fx-background-color: #F1EAD1 ;");
		quantumText.setText("");
		Parent root = FXMLLoader.load(getClass().getResource("GenerateScene.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add("application/application.css");
		Stage stage = new Stage();
		stage.setTitle("Results");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
		stage.setOnHidden(e -> {
			if (GenerateSceneController.finished) {
				speedBar.setDisable(false);
				nextButton.setDisable(false);
				nextChangeButton.setDisable(false);
				animate.setDisable(false);
				init0.setDisable(false);
				GenerateSceneController.finished = false;
				generated = true;
				_processesNumber = Integer.parseInt(GenerateSceneController._processesNumber.getText());
				_contextSwitchText = Integer.parseInt(GenerateSceneController._contextSwitchText.getText());
				printingString("Processes Number : " + _processesNumber);
				_DOM = Integer.parseInt(GenerateSceneController._DOM.getText());
				printingString("DOM : " + _DOM);
				_burstsNumbre = Integer.parseInt(GenerateSceneController._burstsNumbre.getText());
				printingString("Burst Number : " + _burstsNumbre);
				_minCPU = Integer.parseInt(GenerateSceneController._minCPU.getText());
				_maxCPU = Integer.parseInt(GenerateSceneController._maxCPU.getText());
				printingString("CPU : " + _minCPU + " ~ " + _maxCPU);
				_minIO = Integer.parseInt(GenerateSceneController._minCPU.getText());
				_maxIO = Integer.parseInt(GenerateSceneController._maxIO.getText());
				printingString("IO : " + _minIO + " ~ " + _maxIO);
				_minPrio = Integer.parseInt(GenerateSceneController._minPrio.getText());
				_maxPrio = Integer.parseInt(GenerateSceneController._maxPrio.getText());
				printingString("Priority : " + _minPrio + " ~ " + _maxPrio);
				_minArrival = Integer.parseInt(GenerateSceneController._minArrival.getText());
				_maxArrival = Integer.parseInt(GenerateSceneController._maxArrival.getText());
				printingString("Arrival : " + _minArrival + " ~ " + _maxArrival);
				_alpha = Double.parseDouble(GenerateSceneController._alpha.getText());
				printingString("Alpha : " + _alpha);//
				_tau = Integer.parseInt(GenerateSceneController._tau.getText());
				printingString("Tau : " + _tau);

				jobQueue = new PriorityQueue<Process>(_processesNumber, new SortByArraivalTime());
				generateJobs(jobQueue, _processesNumber, false, _burstsNumbre, _minCPU, _maxCPU, _minIO, _maxIO,
						_minPrio, _maxPrio, _minArrival, _maxArrival, _tau);
			} else {
				generated = false;
			}
		});
	}

	public static String log = "";

	public void printingString(String string) {
		if (string.compareTo("") != 0) {
			log = string + "\n" + log;
			logField.setText(log);
		}
	}

	public static void generateJobs(Queue<Process> jobQueue, int _processesNumber, boolean typeOfRandomGenerator,
			int numberOfBursts, int minCPU, int maxCPU, int minIO, int maxIO, int minPriority, int maxPriority,
			int minArrival, int maxArrival, int initialTau) {

		for (int i = 0; i < _processesNumber; i++) {
			int numberOfCpuBursts;
			if (typeOfRandomGenerator)
				numberOfCpuBursts = randomGaussianInt(0.75 * numberOfBursts, numberOfBursts / 2, numberOfBursts);
			else {
				numberOfCpuBursts = randomBinomialInt(0.75 * numberOfBursts, numberOfBursts / 2, numberOfBursts);
			}

			int numberOfIoBursts = numberOfBursts - numberOfCpuBursts;

			Burst[] content = new Burst[numberOfBursts];

			content[0] = new Burst(true, ThreadLocalRandom.current().nextInt(minCPU, maxCPU + 1), 0);
			content[numberOfBursts - 1] = new Burst(true, ThreadLocalRandom.current().nextInt(minCPU, maxCPU + 1), 0);
			numberOfCpuBursts -= 2;
			for (int j = 1; j < numberOfBursts - 1; j++) {
				if (content[j - 1].type) {
					if (numberOfCpuBursts + 1 > numberOfIoBursts) {
						if (numberOfCpuBursts != 0
								&& (ThreadLocalRandom.current().nextInt(0, 2) == 0 || numberOfIoBursts == 0)) {
							content[j] = new Burst(true, ThreadLocalRandom.current().nextInt(minCPU, maxCPU + 1), 0);
							numberOfCpuBursts--;
						} else {
							content[j] = new Burst(false, ThreadLocalRandom.current().nextInt(minIO, maxIO + 1), 0);
							numberOfIoBursts--;
						}
					} else {
						content[j] = new Burst(false, ThreadLocalRandom.current().nextInt(minIO, maxIO + 1), 0);
						numberOfIoBursts--;
					}
				} else {
					content[j] = new Burst(true, ThreadLocalRandom.current().nextInt(minCPU, maxCPU + 1), 0);
					numberOfCpuBursts--;
				}

			}
			jobQueue.add(new Process(ThreadLocalRandom.current().nextInt(minPriority, maxPriority + 1),
					ThreadLocalRandom.current().nextInt(minArrival, maxArrival + 1), content, initialTau));
		}

	}

	public static int randomBinomialInt(double mean, int min, int max) {
		if (max < min || mean < min || mean > max) {
			throw new IllegalArgumentException();
		}
		int n = max - min;
		double p = ((double) (mean - min)) / n;
		int val = min;
		for (int i = 0; i < n; i++) {
			if (Math.random() <= p) {
				val++;
			}
		}
		return val;
	}

	public static int randomGaussianInt(double mean, int min, int max) {
		if (max < min || mean < min || mean > max) {
			throw new IllegalArgumentException();
		}
		int n = max - min;
		double p = ((double) (mean - min)) / n;
		double sd = n * p * (1 - p);
		int val = (int) (new Random().nextGaussian() * sd + mean + .5);
		if (val < min) {
			val = min;
		} else if (val > max) {
			val = max;
		}
		return val;
	}

	@FXML
	private void nextChange(ActionEvent e) {
		String[][] status = null;
		while (!object.getDisplayInfo2()) {
			status = object.performAnIteration(algorithmType, quantum);

		}
		systemTimeLabel.setText((object.getCurrentTime(algorithmType) - 1) + "");
		if (status[1][3] != null) {
			String[] s = status[1][3].split(",");
			HBox h = styleHbox(new HBox(), 87);
			h.getChildren().addAll(styleLabel(new Label(s[0]),100),styleLabel(new Label(s[2]),150),styleLabel(new Label(s[1]),100));
			changesVbox.getChildren().add(0,h);
		}
		if (status[1][1] != null) {
			displayReadyQueue();
			displayDeviceList();
			displayJobs();
			if (object.getRunningState(algorithmType) != null) {
				processIDLabel.setText(object.getRunningState(algorithmType).pID + "");
				totalProgress.setProgress(object.getRunningState(algorithmType).getTotalProcessPercentage());
				currentProgress.setProgress(object.getRunningState(algorithmType).getBurstPercentage());
			} else {
				processIDLabel.setText("NULL");
				totalProgress.setProgress(0);
				currentProgress.setProgress(0);
			}

			printingString(status[1][2]);
			// System.out.println(status[1][0]);
			cpuUtilizationLabel.setText(status[0][0]);
			throughputLabel.setText(status[0][1]);
			TurnaroundLabel.setText(status[0][2] + "");
			waitingLabdel.setText(status[0][3] + "");
		} else {

			if (object.getRunningState(algorithmType) != null) {
				processIDLabel.setText(object.getRunningState(algorithmType).pID + "");
				// update the progress to total
				// set it to
				// object.getRunningState(algorithmType).getTotalProcessPercentage();
				totalProgress.setProgress(object.getRunningState(algorithmType).getTotalProcessPercentage());
				// update the burst progress
				// set it to
				// object.getRunningState(algorithmType).getBurstPercentage();
				currentProgress.setProgress(object.getRunningState(algorithmType).getBurstPercentage());

			} else {
				processIDLabel.setText("NULL");
				// clear both the burst and total progress
				totalProgress.setProgress(0);
				currentProgress.setProgress(0);
			}

			printingString(status[1][2]);
			// System.out.println(status[1][0]);
			cpuUtilizationLabel.setText(status[0][0]);
			throughputLabel.setText(status[0][1]);
			TurnaroundLabel.setText(status[0][2] + "");
			waitingLabdel.setText(status[0][3] + "");
			nextChangeButton.setDisable(true);
			nextButton.setDisable(true);
		}
		object.setDisplayInfo2(false);
	}

	@FXML
	private Label processIDLabel;
	@FXML
	private JFXButton init0;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		changesVbox.setPadding(new Insets(0,0,0,26));
		speedBar.setDisable(true);
		logField.setVisible(true);
		floog = true;
		logField.setText("");
		changesPane.setVisible(false);
		init0.setDisable(true);
		animate.setDisable(true);
		jobsPane.setVisible(true);
		devicesPane.setVisible(false);
		speedBar.valueChangingProperty().addListener(this);
		algoName.setText("Not set");
		quantumText.setText("");
		algorithmPane2 = algorithmPane;
		algorithmPane2.setVisible(false);
		quantumText.setDisable(true);
		animate.setDisable(true);
		processIDLabel.setText("NULL");
		totalProgress.setProgress(0);
		currentProgress.setProgress(0);
		logField.setText("");
		nextButton.setDisable(true);
		nextChangeButton.setDisable(true);
		cpuUtilizationLabel.setText(null);
		throughputLabel.setText(null);
		TurnaroundLabel.setText(null);
		waitingLabdel.setText(null);
	}

	public void displayReadyQueue() {
		readyVBOX.getChildren().clear();
		Queue<Process> temp = object.getReadyQueue(algorithmType);
		Queue<Process> temp2;
		temp2 = new LinkedList<Process>();
		for (Process p : temp) {
			try {
				temp2.add((Process) p.clone());
			} catch (CloneNotSupportedException e1) {
				e1.printStackTrace();
			}
		}
		// yes
		while (!temp2.isEmpty()) {
			Process t = temp2.poll();
			HBox xx = styleHbox(new HBox(), 1);
			xx.setAlignment(Pos.CENTER_LEFT);
			xx.getChildren().addAll(styleLabel(new Label("Process [" + t.pID + "]"), 117),
					styleProgress(new JFXProgressBar(t.getTotalProcessPercentage())),
					styleIndicator(new JFXSpinner(t.getTotalProcessPercentage())));
			readyVBOX.getChildren().add(xx);
		}

	}

	public void displayDeviceList() {
		devicesVBOX.getChildren().clear();
		LinkedList<Process> temp = object.getDeviceList(algorithmType);
		LinkedList<Process> temp2;
		temp2 = new LinkedList<Process>();
		for (Process p : temp) {
			try {
				temp2.add((Process) p.clone());
			} catch (CloneNotSupportedException e1) {
				e1.printStackTrace();
			}
		}
		while (!temp2.isEmpty()) {
			Process t = temp2.poll();
			HBox xx = styleHbox(new HBox(), 1);
			xx.getChildren().addAll(styleLabel(new Label("Process [" + t.pID + "]"), 117),
					styleProgress(new JFXProgressBar(t.getBurstPercentage2())),
					styleIndicator(new JFXSpinner(t.getBurstPercentage2())));
			devicesVBOX.getChildren().add(xx);
		}
	}

	public void displayJobs() {
		if (!jobsVBOX.getChildren().isEmpty())
			if(!(object.getnumberOfChanges(algorithmType) > jobsVBOX.getChildren().size()))
				jobsVBOX.getChildren().remove(0, object.getnumberOfChanges(algorithmType));
			else
				jobsVBOX.getChildren().clear();
	}

	public void dJobs() {
		jobsVBOX.getChildren().clear();
		Queue<Process> temp = object.getJobQueue(algorithmType);
		Queue<Process> temp2;
		temp2 = new PriorityQueue<Process>(_processesNumber, new SortByArraivalTime());
		for (Process p : temp) {
			try {
				temp2.add((Process) p.clone());
			} catch (CloneNotSupportedException e1) {
				e1.printStackTrace();
			}
		}

		while (!temp2.isEmpty()) {
			Process t = temp2.poll();
			HBox xx = styleHbox(new HBox(), 0);
			xx.getChildren().addAll(styleLabel(new Label("Process [" + t.pID + "]"), 135),
					styleLabel(new Label(t.arrivalTime + ""), 135), styleLabel(new Label(t.priority + ""), 100),
					styleLabel(new Label(t.content.length + ""), 100));
			jobsVBOX.getChildren().add(xx);
		}
	}

	public HBox styleHbox(HBox b, int spacing) {
		b.setPadding(new Insets(0,0,26,0));
		b.setAlignment(Pos.CENTER_LEFT);
		b.setFillHeight(false);
		b.setMinHeight(67);
		b.setPrefHeight(67);
		b.setPrefWidth(334);
		b.setSpacing(spacing);
		return b;
	}

	public Label styleLabel(Label l, int width) {
		l.setAlignment(Pos.CENTER_LEFT);
		l.setPrefHeight(32);
		l.setPrefWidth(width);
		l.setFont(new Font("Open Sans", 15));
		l.setTextOverrun(OverrunStyle.CLIP);
		return l;
	}

	public JFXProgressBar styleProgress(JFXProgressBar p) {
		p.setPrefHeight(18);
		p.setPrefWidth(164);
		return p;
	}

	public JFXSpinner styleIndicator(JFXSpinner i) {
		i.setPrefHeight(30);
		i.setPrefWidth(31);
		return i;
	}

	@Override
	public void changed(ObservableValue arg0, Object arg1, Object arg2) {
		timeline.stop();
		ani(speedBar.getValue());
	}
}
