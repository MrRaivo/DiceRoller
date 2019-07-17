package DiceRoller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.Random;

public class Main extends Application implements EventHandler<ActionEvent> {

    private TextField diceAmount;
    private ComboBox<String> diceType;
    private TextField modifier;
    private TextArea output;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Dice Roller");

        diceAmount = new TextField("1");
        diceAmount.setPrefWidth(30.0);

        diceType = new ComboBox<>();
        diceType.getItems().addAll("d2",
                "d4",
                "d6",
                "d8",
                "d10",
                "d12",
                "d20",
                "d100");
        diceType.getSelectionModel().selectFirst();

        Label plus = new Label("+");

        modifier = new TextField("0");
        modifier.setPrefWidth(30.0);

        Button rollButton = new Button("Roll!");
        rollButton.setOnAction(this);

        output = new TextArea();
        output.setEditable(false);
        output.setPrefWidth(100.0);
        output.setWrapText(true);

        GridPane grid = new GridPane();
        grid.setHgap(10.0);
        grid.setVgap(10.0);
        grid.setPadding(new Insets(10,10,10,10));
        grid.add(diceAmount, 0,0);
        grid.add(diceType, 1,0);
        grid.add(plus, 2, 0);
        grid.add(modifier, 3, 0);
        grid.add(rollButton, 4, 0);
        grid.add(output, 0, 1, 5,4);

        Group root = new Group();
        root.getChildren().addAll(grid);

        primaryStage.setScene(new Scene(root, 240, 240));
        primaryStage.show();
    }

    @Override
    public void handle(ActionEvent event) {

        boolean errorFlag = false;
        int newDiceAmount = 0;
        int newDiceType;
        int diceLand = 0;
        int newModifier = 0;
        Random rand = new Random();


        if(diceAmount.getText().isEmpty()){
            output.appendText("Please enter the amount of dice to roll.\n");
            errorFlag = true;
        } else if(diceAmount.getText().matches("[0-9]*") &&
                Integer.valueOf(diceAmount.getText()) > 0 ||
                diceAmount.getText().isEmpty()){
            newDiceAmount = Integer.valueOf(diceAmount.getText());
        } else {
            output.appendText("The number of dice must be a positive integer.\n");
            errorFlag = true;
        }

        if(modifier.getText().isEmpty()){
            output.appendText("Please enter a modifier.\n");
            errorFlag = true;
        } else if(modifier.getText().matches("-?[0-9]*")){
            newModifier = Integer.valueOf(modifier.getText());
        } else if (modifier.getText() == null || modifier.getText().trim().isEmpty() || modifier.getText().equals("")) {
            output.appendText("Please add a modifier.");
        } else {
            output.appendText("The modifier must be an integer.\n");
            errorFlag = true;
        }

        if(!errorFlag) {
            for (int i = 0; i < newDiceAmount; i++) {
                switch (diceType.getValue()) {
                    case "d2":
                        newDiceType = rand.nextInt(2) + 1;
                        diceLand += newDiceType;
                        output.appendText("(" + newDiceType + ")" + " + ");
                        break;
                    case "d4":
                        newDiceType = rand.nextInt(4) + 1;
                        diceLand += newDiceType;
                        output.appendText("(" + newDiceType + ")" + " + ");
                        break;
                    case "d6":
                        newDiceType = rand.nextInt(6) + 1;
                        diceLand += newDiceType;
                        output.appendText("(" + newDiceType + ")" + " + ");
                        break;
                    case "d8":
                        newDiceType = rand.nextInt(8) + 1;
                        diceLand += newDiceType;
                        output.appendText("(" + newDiceType + ")" + " + ");
                        break;
                    case "d10":
                        newDiceType = rand.nextInt(10) + 1;
                        diceLand += newDiceType;
                        output.appendText("(" + newDiceType + ")" + " + ");
                        break;
                    case "d12":
                        newDiceType = rand.nextInt(12) + 1;
                        diceLand += newDiceType;
                        output.appendText("(" + newDiceType + ")" + " + ");
                        break;
                    case "d20":
                        newDiceType = rand.nextInt(20) + 1;
                        diceLand += newDiceType;
                        output.appendText("(" + newDiceType + ")" + " + ");
                        break;
                    case "d100":
                        newDiceType = rand.nextInt(100) + 1;
                        diceLand += newDiceType;
                        output.appendText("(" + newDiceType + ")" + " + ");
                        break;
                }
            }
        }

        if(!errorFlag) {
            output.appendText(newModifier + " for a total of " +
                    (diceLand + newModifier) + "\n");
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
