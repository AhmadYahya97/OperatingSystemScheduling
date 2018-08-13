package application;
//package application;
//
//import javafx.application.Application;
//import javafx.geometry.Pos;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.scene.control.ProgressBar;
//import javafx.scene.control.ProgressIndicator;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
//public class testMain extends Application {
//
//	final Float[] values = new Float[] { 0f, 0.1f, 0.2f, .3f, .4f, 0.5f, 0.6f, .7f, .8f, 0.9f, 1.0f };
//	final Label[] labels = new Label[values.length];
//	final ProgressBar[] pbs = new ProgressBar[values.length];
//	final ProgressIndicator[] pins = new ProgressIndicator[values.length];
//	final HBox hbs[] = new HBox[values.length];
//
//	@Override
//	public void start(Stage stage) {
//		Group root = new Group();
//		Scene scene = new Scene(root, 300, 150);
//		// scene.getStylesheets().add("progresssample/Style.css");
//		stage.setScene(scene);
//		stage.setTitle("Progress Controls");
//
//		for (int i = 0; i < values.length; i++) {
//			final Label label = labels[i] = new Label();
//			label.setText("progress:" + values[i]);
//
//			final ProgressBar pb = pbs[i] = new ProgressBar();
//			pb.setProgress(values[i]);
//
//			final ProgressIndicator pin = pins[i] = new ProgressIndicator();
//			pin.setProgress(values[i]);
//			final HBox hb = hbs[i] = new HBox();
//			hb.setSpacing(5);
//			hb.setAlignment(Pos.CENTER);
//			hb.getChildren().addAll(label, pb, pin);
//		}
//
//		final VBox vb = new VBox();
//		vb.setSpacing(5);
//		vb.getChildren().addAll(hbs);
//		ScrollPane sideBarScroller = new ScrollPane(vb);
//		sideBarScroller.setFitToWidth(true);
//		scene.setRoot(sideBarScroller);
//		stage.show();
//	}
//
//	public static void main(String[] args) {
//		launch(args);
//	}
//}