package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.ResourceBundle;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import application.Process;
import application.initialStateController.Data;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;

public class initialStateController implements Initializable {

	@FXML
	private VBox vbox;
	static ArrayList<Process> array = new ArrayList<>();
	@FXML
	private JFXTreeTableView<Data> table;
	public ArrayList<Temp> arraylist = new ArrayList<>();
	public class Data extends RecursiveTreeObject<Data>{
		 public Data(String pid, String arrivalTime, String prio) {
			this.pid = new SimpleStringProperty(pid);
			this.arrivalTime = new SimpleStringProperty(arrivalTime);
			this.prio = new  SimpleStringProperty(prio);
		}

		StringProperty pid,arrivalTime,prio;
	}
	public class Temp{
		int id;
		Burst[] i;
		public Temp(Burst[] i, int id) {
			this.i = i;
			this.id = id;
		}
		
	}
	public void create() {
		JFXTreeTableColumn<Data, String> p = new JFXTreeTableColumn<>("Process ID");
		p.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Data,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Data, String> arg0) {
				return arg0.getValue().getValue().pid;
			}
		});
		JFXTreeTableColumn<Data, String> a = new JFXTreeTableColumn<>("Arrival Time");
		a.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Data,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Data, String> arg0) {
				return arg0.getValue().getValue().arrivalTime;
			}
		});
		JFXTreeTableColumn<Data, String> prio = new JFXTreeTableColumn<>("Priority");
		prio.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Data,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Data, String> arg0) {
				return arg0.getValue().getValue().prio;
			}
		});
		ObservableList<Data> data = FXCollections.observableArrayList();
		LinkedList<Process> temp = MainSceneController.object.getInitialJobQueue(MainSceneController.algorithmType);
		PriorityQueue<Process> temp2;
		temp2 = new PriorityQueue<Process>(MainSceneController._processesNumber, new SortByArraivalTime());
		for (Process p1 : temp) {
			try {
				temp2.add((Process) p1.clone());
			} catch (CloneNotSupportedException e1) {
				e1.printStackTrace();
			}
		}
		int index = 0;
		while(!temp2.isEmpty()) {
			Process t = temp2.poll();
			data.add(new Data("Process ["+t.pID+"]",t.arrivalTime+"",t.priority+""));
			arraylist.add(new Temp(t.content,index));
			index++;
			}
		final TreeItem<Data> root  = new RecursiveTreeItem<Data>(data,RecursiveTreeObject::getChildren);
		table.getColumns().setAll(p,a,prio);
		table.setRoot(root);
		table.setShowRoot(false);
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		create();
	}
	@FXML
	Label waitLabel;
	@FXML
	Label taLabel;
	@FXML
	private void showBursts(MouseEvent e) {
		int i = Integer.parseInt(table.getSelectionModel().getSelectedItems().get(0).getValue().pid.get().replaceAll("\\D+",""));
		vbox.getChildren().clear();
		Queue<Process> temp;
		try {
			temp = MainSceneController.object.getJobQueue(MainSceneController.algorithmType);
			for(Process p:temp) {
				if(p.pID == i) {
					waitLabel.setText(p.getW8ing()+"");
					taLabel.setText(p.getTA()+"");
					for(int j = 0;j<p.content.length;j++) {
						vbox.getChildren().add(style(p.content[j]));
					}
					throw new Exception();
				}
			}
			temp = MainSceneController.object.getDeviceList(MainSceneController.algorithmType);
			for(Process p:temp) {
				if(p.pID == i) {
					waitLabel.setText(p.getW8ing()+"");
					taLabel.setText(p.getTA()+"");
					for(int j = 0;j<p.content.length;j++) {
						vbox.getChildren().add(style(p.content[j]));
					}
					throw new Exception();
				}
			}
			temp = MainSceneController.object.getReadyQueue(MainSceneController.algorithmType);
			for(Process p:temp) {
				if(p.pID == i) {
					waitLabel.setText(p.getW8ing()+"");
					taLabel.setText(p.getTA()+"");
					for(int j = 0;j<p.content.length;j++) {
						vbox.getChildren().add(style(p.content[j]));
					}
					throw new Exception();
				}
			}
			temp = MainSceneController.object.getInitialJobQueue(MainSceneController.algorithmType);
			for(Process p:temp) {
				if(p.pID == i) {
					waitLabel.setText(p.getW8ing()+"");
					taLabel.setText(p.getTA()+"");
					for(int j = 0;j<p.content.length;j++) {
						vbox.getChildren().add(style(p.content[j]));
					}
					throw new Exception();
				}
			}
		} catch (Exception e1) {
		}
	}
	private Label style(Burst burst) {
		Label l = new Label();
		l.setAlignment(Pos.CENTER);
		l.setMinHeight(45);
		l.setPrefWidth(vbox.getPrefWidth());
		l.setFont(new Font("Open Sans",15));
		l.setTextOverrun(OverrunStyle.CLIP);
		if(burst.type) {
			l.setStyle("-fx-background-color: #F1EAD1 ;");
			l.setText("CPU ["+burst.timeNeeded+"]");
		} else {
			l.setStyle("-fx-background-color: #9fcccb ;");
			l.setText("I/O ["+burst.timeNeeded+"]");
		}
		return l;
	}
	}

















