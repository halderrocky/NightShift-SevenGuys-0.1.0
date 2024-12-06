package edu.sdccd.cisc190.generalstuff;

import edu.sdccd.cisc190.generalstuff.PreLude;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainMenu {
    private final Scene scene;
    private int conviction; // stat for conviction
    private int madness;    // stat for madness
    private int secondsElapsed; // Timer counter

    public MainMenu(Stage primaryStage) {
        conviction = 0; // Initial conviction
        madness = 0;    // Initial madness
        secondsElapsed = 0; // Start timer at 0 seconds

        // Create the buttons and description text
        Button startButton = new Button("Unlock the door (+1 conviction, +1 madness)");
        Button exitButton = new Button("Don't Unlock the door");

        Text description = new Text(
                "You’re the new guy working in Seven Guys, a local burger shop that on the outside, is a fun and exciting place to eat at, " +
                        "filled with yummy food and a huge-ass party stage with cool party rooms, and of course, " +
                        "the main attraction: The High Fives. But behind closed doors, a different story appears.\n" +
                        "Debts haven't been paid, the kitchen has violated a bunch of health codes and laws, " +
                        "and signs of the animatronics… moving on their own, like they’re being controlled by spirits. " +
                        "Maybe they’re linked to the disappearing workers lately… " +
                        "But hey! with the new “Security Computer Auto Machine” (or S.C.A.M for short), " +
                        "they’re able to make sure everything is A-Ok!\nStanding in front of the Burger-plex. " +
                        "You realize you were never given a key to the place. " +
                        "“Lazy managers” you murmured as a note was plastered onto the back entrance.\n" +
                        "“Yeah we totally forgot to give you the key, it'll be under the rock!”\n" +
                        "Groaning you grab the key from under the rock and prepare to open the door. " +
                        "Suddenly, the unexplainable urge to do nothing was filling your mind. " +
                        "“Is this a tutorial?” (Pick the options presented to make your choice, choose wisely, well in this case you only have one but, you know, don’t fall too deep~)\n");
        description.setStyle("-fx-font-size: 9px; -fx-font-weight: bold;");

        // Action for unlocking the door: Increase conviction and madness, then transition to PreLude scene
        startButton.setOnAction(e -> {
            conviction += 1; // Increase conviction
            madness += 1;    // Increase madness

            // Pass conviction and madness to PreLude when transitioning
            PreLude preludeScene = new PreLude(primaryStage, conviction, madness);
            primaryStage.setScene(preludeScene.getScene());
        });

        // Action to exit the application
        exitButton.setOnAction(e -> primaryStage.close());

        // Create a text element to display stats
        Text stats = new Text("Conviction: " + conviction + " | Madness: " + madness);
        stats.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        // Timer display
        Text timer = new Text("Time: 0s");
        timer.setStyle("-fx-font-size: 14px; -fx-background-color: rgba(0, 0, 0, 0.5); -fx-text-fill: white; -fx-padding: 5px;");

        // Timer logic using a Timeline
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            secondsElapsed++;
            timer.setText("Time: " + secondsElapsed + "s");
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        // Create the BorderPane layout
        BorderPane layout = new BorderPane();

        // Set the description text at the top of the layout
        layout.setTop(description);

        // Set the buttons in the bottom region
        VBox buttonLayout = new VBox(10, startButton, exitButton);
        layout.setCenter(buttonLayout);

        // Set stats at the bottom-left and timer at the bottom-right
        StackPane bottomPane = new StackPane(stats, timer);
        StackPane.setAlignment(stats, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(timer, Pos.BOTTOM_RIGHT);
        layout.setBottom(bottomPane);

        // Align buttons to center within the center region
        BorderPane.setAlignment(buttonLayout, Pos.CENTER);

        // Create the scene with the BorderPane layout
        scene = new Scene(layout, 300, 400);

        // Resize behavior for timer positioning
        layout.widthProperty().addListener((obs, oldVal, newVal) -> timer.setLayoutX(layout.getWidth() - timer.getBoundsInParent().getWidth() - 10));
        layout.heightProperty().addListener((obs, oldVal, newVal) -> timer.setLayoutY(layout.getHeight() - timer.getBoundsInParent().getHeight() - 10));
    }

    // Getter for the scene
    public Scene getScene() {
        return scene;
    }
}
