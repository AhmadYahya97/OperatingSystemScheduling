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
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static javafx.scene.paint.Color.*;

public class SimpleSimon extends Application {
	private int sequenceLength = 1;
	private int speed = 1;

	@Override
	public void start(Stage stage) throws Exception {
		Simon simon = new Simon();

		Button play = new Button("Play");
		play.setStyle("-fx-font-size: 20px;");
		play.disableProperty().bind(simon.isPlayingProperty());
		play.setOnAction(event -> {
			simon.playSequence(sequenceLength, speed);
			sequenceLength++;
			speed *= 1.05;
		});

		VBox layout = new VBox(10, simon, play);
		layout.setPadding(new Insets(10));
		layout.setAlignment(Pos.CENTER);

		stage.setScene(new Scene(layout));
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

class Simon extends Group {
	private static final Random random = new Random(42);

	private final Color[] colors = { RED, GREEN, BLUE, ORANGE };

	private Map<Color, Segment> segmentMap = new HashMap<>();

	private ReadOnlyBooleanWrapper isPlaying = new ReadOnlyBooleanWrapper();

	public Simon() {
		for (int i = 0; i < colors.length; i++) {
			Segment segment = new Segment(colors[i], i * 90);
			getChildren().add(segment);
			segmentMap.put(colors[i], segment);
		}
	}

	public void playSequence(int sequenceLength, double speed) {
		Timeline timeline = new Timeline();

		for (int i = 0; i < sequenceLength; i++) {
			Color color = colors[random.nextInt(colors.length)];
			Segment segment = segmentMap.get(color);

			timeline.getKeyFrames().addAll(new KeyFrame(Duration.seconds(i), new KeyValue(segment.litProperty(), true)),
					new KeyFrame(Duration.seconds(i + 0.9), new KeyValue(segment.litProperty(), false)));
		}
		
		System.out.println("yes");

		isPlaying.set(true);
		timeline.setOnFinished(event -> isPlaying.set(false));
		timeline.setRate(speed);
		timeline.play();
	}

	public boolean isPlaying() {
		return isPlaying.get();
	}

	public ReadOnlyBooleanWrapper isPlayingProperty() {
		return isPlaying;
	}
}

class Segment extends Arc {
	private BooleanProperty lit = new SimpleBooleanProperty();
	private static final double RADIUS = 100;

	private static final ColorAdjust litEffect = new ColorAdjust(0, 0, 0.5, 0);
	private static final ColorAdjust unlitEffect = new ColorAdjust(0, 0, 0, 0);

	public Segment(Color color, double angleOffset) {
		super(RADIUS, RADIUS, RADIUS, RADIUS, angleOffset, 90);
		setFill(color);
		setType(ArcType.ROUND);

		setEffect(unlitEffect);
		lit.addListener((observable, oldValue, newValue) -> setEffect(lit.get() ? litEffect : unlitEffect));
	}

	public void setLit(boolean lit) {
		this.lit.set(lit);
	}

	public boolean isLit() {
		return lit.get();
	}

	public BooleanProperty litProperty() {
		return lit;
	}
}