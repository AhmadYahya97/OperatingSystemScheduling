package application;

import java.awt.Color;

import com.jfoenix.controls.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class GenerateSceneController {
	public  boolean flag = true;
	
	
	public static boolean finished = false;
	
	
	public static JFXButton _generateButton;
	public static JFXTextField _processesNumber;
	public static JFXTextField _contextSwitchText;
	public static JFXTextField _DOM;
	public static JFXTextField _burstsNumbre;
	public static JFXTextField _minCPU;
	public static JFXTextField _maxCPU;
	public static JFXTextField _minIO;
	public static JFXTextField _maxIO;
	public static JFXTextField _minPrio;
	public static JFXTextField _maxPrio;
	public static JFXTextField _minArrival;
	public static JFXTextField _maxArrival;
	public static JFXTextField _alpha;
	public static JFXTextField _tau;

	@FXML
	private JFXButton generateButton;
	@FXML
	private JFXTextField processesNumber;
	@FXML
	private JFXTextField contextSwitchText;

	@FXML
	private JFXTextField DOM;

	@FXML
	private JFXTextField burstsNumbre;

	@FXML
	private JFXTextField minCPU;

	@FXML
	private JFXTextField maxCPU;

	@FXML
	private JFXTextField minIO;

	@FXML
	private JFXTextField maxIO;

	@FXML
	private JFXTextField minPrio;

	@FXML
	private JFXTextField maxPrio;

	@FXML
	private JFXTextField minArrival;

	@FXML
	private JFXTextField maxArrival;
	@FXML
	private JFXTextField alpha;
	@FXML
	private JFXTextField tau;

	@FXML
	private void generateFunction(ActionEvent event) {
		flag = true;
		try {
			int temp = Integer.parseInt(processesNumber.getText());
			if (temp <= 0) {
				throw new Exception();
			}
			processesNumber.setBackground(Background.EMPTY);
			_processesNumber = processesNumber;
			
		} catch (Exception e) {
			flag = false;
			//System.out.println("error in values");
			processesNumber.setStyle("-fx-background-color: #ff6347;");
		}
		try {
			int temp = Integer.parseInt(contextSwitchText.getText());
			if (temp < 0) {
				throw new Exception();
			}
			contextSwitchText.setBackground(Background.EMPTY);
			_contextSwitchText = contextSwitchText;
			
		} catch (Exception e) {
			flag = false;
			//System.out.println("error in values");
			contextSwitchText.setStyle("-fx-background-color: #ff6347;");
		}
		// -----

		try {
			int temp = Integer.parseInt(DOM.getText());
			if (temp <= 0) {
				throw new Exception();
			}
			DOM.setBackground(Background.EMPTY);
			_DOM = DOM;

		} catch (Exception e) {
			flag = false;
			//System.out.println("error in values");
			DOM.setStyle("-fx-background-color: #ff6347;");
		}

		// -----

		try {
			int temp = Integer.parseInt(burstsNumbre.getText());
			if (temp <= 0) {
				throw new Exception();
			}
			burstsNumbre.setBackground(Background.EMPTY);
			_burstsNumbre = burstsNumbre;

		} catch (Exception e) {
			flag = false;
			//System.out.println("error in values");
			burstsNumbre.setStyle("-fx-background-color: #ff6347;");
		}

		// -----
		try {
			int temp = Integer.parseInt(minCPU.getText());
			if (temp < 0) {
				throw new Exception();
			}
			minCPU.setBackground(Background.EMPTY);
			_minCPU = minCPU;

		} catch (Exception e) {
			flag = false;
			//System.out.println("error in values");
			minCPU.setStyle("-fx-background-color: #ff6347;");
		}

		// -----
		try {
			int temp = Integer.parseInt(maxCPU.getText());
			if (temp < 0) {
				throw new Exception();
			}
			if (flag) {
				if (temp < Integer.parseInt(minCPU.getText())) {
					throw new Exception();
				}
			} else {
				int temp2 = Integer.parseInt(minCPU.getText());
				if (temp < temp2) {
					throw new Exception();
				}
			}
			maxCPU.setBackground(Background.EMPTY);
			_maxCPU = maxCPU;

		} catch (Exception e) {
			flag = false;
			//System.out.println("error in values");
			maxCPU.setStyle("-fx-background-color: #ff6347;");
		}

		// -----
		try {
			int temp = Integer.parseInt(minArrival.getText());
			if (temp < 0) {
				throw new Exception();
			}
			minArrival.setBackground(Background.EMPTY);
			_minArrival = minArrival;

		} catch (Exception e) {
			flag = false;
			//System.out.println("error in values");
			minArrival.setStyle("-fx-background-color: #ff6347;");
		}

		// -----
		try {
			int temp = Integer.parseInt(maxArrival.getText());
			if (temp < 0) {
				throw new Exception();
			}
			if (flag) {
				if (temp < Integer.parseInt(minArrival.getText())) {
					throw new Exception();
				}
			} else {
				int temp2 = Integer.parseInt(minArrival.getText());
				if (temp < temp2) {
					throw new Exception();
				}
			}
			maxArrival.setBackground(Background.EMPTY);
			_maxArrival = maxArrival;

		} catch (Exception e) {
			flag = false;
			//System.out.println("error in values");
			maxArrival.setStyle("-fx-background-color: #ff6347;");
		}

		// -----
		try {
			int temp = Integer.parseInt(minPrio.getText());
			if (temp < 0) {
				throw new Exception();
			}
			minPrio.setBackground(Background.EMPTY);
			_minPrio = minPrio;

		} catch (Exception e) {
			flag = false;
			//System.out.println("error in values");
			minPrio.setStyle("-fx-background-color: #ff6347;");
		}

		// -----
		try {
			int temp = Integer.parseInt(maxPrio.getText());
			if (temp < 0) {
				throw new Exception();
			}

			if (flag) {
				if (temp < Integer.parseInt(minPrio.getText())) {
					throw new Exception();
				}
			} else {
				int temp2 = Integer.parseInt(minPrio.getText());
				if (temp < temp2) {
					throw new Exception();
				}
			}
			maxPrio.setBackground(Background.EMPTY);
			_maxPrio = maxPrio;

		} catch (Exception e) {
			flag = false;
			//System.out.println("error in values");
			maxPrio.setStyle("-fx-background-color: #ff6347;");
		}

		// -----
		try {
			int temp = Integer.parseInt(minIO.getText());
			if (temp < 0) {
				throw new Exception();
			}
			minIO.setBackground(Background.EMPTY);
			_minIO = minIO;

		} catch (Exception e) {
			flag = false;
			//System.out.println("error in values");
			minIO.setStyle("-fx-background-color: #ff6347;");
		}

		// -----
		try {
			int temp = Integer.parseInt(maxIO.getText());
			if (temp < 0) {
				throw new Exception();
			}
			if (flag) {
				if (temp < Integer.parseInt(minIO.getText())) {
					throw new Exception();
				}
			} else {
				int temp2 = Integer.parseInt(minIO.getText());
				if (temp < temp2) {
					throw new Exception();
				}
			}
			maxIO.setBackground(Background.EMPTY);
			_maxIO = maxIO;
		} catch (Exception e) {
			flag = false;
			//System.out.println("error in values");
			maxIO.setStyle("-fx-background-color: #ff6347;");
		}

		// -----
		try {
			int temp = Integer.parseInt(tau.getText());
			if (temp < 0) {
				throw new Exception();
			}
			tau.setBackground(Background.EMPTY);
			_tau = tau;
		} catch (Exception e) {
			flag = false;
			//System.out.println("error in values");
			tau.setStyle("-fx-background-color: #ff6347;");
		}

		try {
			double temp = Double.parseDouble(alpha.getText());
			if (temp < 0 || temp > 1) {
				throw new Exception();
			}
			alpha.setBackground(Background.EMPTY);
			_alpha = alpha;
		} catch (Exception e) {
			flag = false;
			//System.out.println("error in values");
			alpha.setStyle("-fx-background-color: #ff6347;");
		}

		if (flag) {
			//System.out.println("evreything is correct");
			finished = true;
			Stage stage = (Stage) generateButton.getScene().getWindow();
			stage.close();
			MainSceneController.showAlgorithms();
		}
		else
			finished = false;

	}

}
