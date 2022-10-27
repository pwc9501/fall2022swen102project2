package View;
import Controller.OpenUserInput;
import Random.randomName;
import Random.randomText;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GUI extends Application{
    
    private Button makeButton(String text){
        Button b = new Button(text);
        b.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        b.setTextAlignment(TextAlignment.CENTER);
        b.setTextFill(Color.WHITE);
        b.setBackground(new Background(new BackgroundFill(Color.BLUE, new CornerRadii(20), Insets.EMPTY)));
        return b;
    }

    private TextField makeTextField(String text){
        TextField t = new TextField();
        t.setPromptText(text);
        t.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        return t;
    }

    private Label makeLabel(){
        Label l = new Label();
        l.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        l.setMinHeight(40);
        l.setAlignment(Pos.CENTER);
        l.setBackground(new Background(new BackgroundFill(Color.GREY, new CornerRadii(5), Insets.EMPTY)));
        return l;
    }

    
    @Override
    public void start(Stage stage){

            BorderPane borderPane = new BorderPane();
            borderPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

            String content = "Ritche's Texts";
            Text title = new Text();
            title.setFont(Font.font("Impact", FontWeight.BOLD, 20));
            title.setFill(Color.ORANGE);
            title.setTextAlignment(TextAlignment.CENTER);

            borderPane.setTop(title);
         
            Animation typingAnimation = new Transition(){
                {
                    setCycleDuration(Duration.millis(2000));
                }
            
                protected void interpolate(double frac) {
                    final int length = content.length();
                    final int n = Math.round(length * (float) frac);
                    title.setText(content.substring(0, n));
                }
            };

            typingAnimation.play();


            VBox text = new VBox();
            text.setPadding(new Insets(10));
            for(int i = 0; i < 10; i++){
                Label l = makeLabel();
                text.getChildren().add(l);
                if(i != 9){
                    text.setMargin(l, new Insets(0, 0, 5, 0));
                }
            }

            borderPane.setCenter(text);
            
            HBox userControls = new HBox();
            userControls.setPadding(new Insets(10));
            
            TextField userTextField = makeTextField("Enter text");
            userControls.getChildren().add(userTextField);
            
            Button userSend =  makeButton("Send Text");
            userControls.getChildren().add(userSend);

            Button popUp =  makeButton("?");
            userControls.getChildren().add(popUp);
            
            Stage popUpStage = new Stage();
            popUp.setOnAction(new OpenUserInput(popUpStage));

            //popUpStage

            GridPane popUpPane = new GridPane();
            popUpPane.setPadding(new Insets(10));
            popUpPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
            
            TextField popUpTextField =  makeTextField("Enter text");
            Button randomText =  makeButton("Random Text");

            TextField popUpSenderField =  makeTextField("Enter Name");
            Button randomSender =  makeButton("Random Name");

            Button sendNow =  makeButton("Send Now");


            popUpPane.add(popUpTextField, 0, 1);
            popUpPane.add(randomText, 1, 1);

            popUpPane.add(popUpSenderField, 0, 0);
            popUpPane.add(randomSender, 1, 0);

            popUpPane.add(sendNow, 0, 2);
            
            EventHandler<ActionEvent> randomTextObserver = new randomTextField(popUpTextField);
            randomText.setOnAction(randomTextObserver);

            EventHandler<ActionEvent> randomNameObserver = new randomNameField(popUpSenderField);
            randomSender.setOnAction(randomNameObserver);

            EventHandler<ActionEvent> updateObserver = new UpdateHandler(text, popUpTextField);
            sendNow.setOnAction(updateObserver);

            /*
            GridPane gridPane = new GridPane();

            gridPane.setPadding(new Insets(10)); 
            
            gridPane.setVgap(5); 
            gridPane.setHgap(5);       
            
            gridPane.setAlignment(Pos.CENTER);

            TextField t = makeTextField("Enter message here");
            Button b = makeButton("Random Text");
            
            //work in progress
            TextField t2 = makeTextField("Enter name of receipient");
            Button b3 = makeButton("Random recipient");

            //work in progress
            TextField t3 = makeTextField("Enter date and time");


            Button b2 = makeButton("Send text");

            gridPane.add(t, 0, 0);
            gridPane.add(b, 1, 0);
            gridPane.add(t2, 0, 1);
            gridPane.add(b3, 1, 1);
            gridPane.add(t3, 0, 2);
            gridPane.add(b2, 1, 3);
            */
            //EventHandler<ActionEvent> randomTextObserver = new randomTextField(t);
            //b.setOnAction(randomTextObserver);

            EventHandler<ActionEvent> observer2 = new UpdateHandler(text, userTextField);
            userSend.setOnAction(observer2);

            borderPane.setBottom(userControls);

            stage.setScene(new Scene(borderPane, Double.MAX_VALUE, Double.MAX_VALUE));
            popUpStage.setScene(new Scene(popUpPane));
            stage.setTitle("Fake Text App");
            stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}

class UpdateHandler implements EventHandler<ActionEvent>{
    private VBox v;
    private TextField text;

    public UpdateHandler(VBox v, TextField text){
        this.v = v;
        this.text = text;
    }

    @Override
    public void handle(ActionEvent arg0) {
        String[] messages = new String[v.getChildren().size()];
        for(int i = 0; i < v.getChildren().size(); i++){
            messages[i] = ((Label) v.getChildren().get(i)).getText();
            ((Label) v.getChildren().get(i)).setText("");
        }
        ((Label) v.getChildren().get(0)).setText(text.getText());
        ((Label) v.getChildren().get(0)).setFont(new Font(20));
        for(int i = 1; i < v.getChildren().size(); i++){
            ((Label) v.getChildren().get(i)).setText(messages[i-1]);
            ((Label) v.getChildren().get(i)).setFont(new Font(20));
        }
    }
}

class randomNameField implements EventHandler<ActionEvent>{
    private TextField text;
    private randomName name;

    public randomNameField(TextField text){
        this.text = text;
        this.name = new randomName();
    }

    @Override
    public void handle(ActionEvent arg0){
        text.setText(name.randomTString());
    }
}
class randomTextField implements EventHandler<ActionEvent>{
    private TextField text;
    private randomText r;

    public randomTextField(TextField text){
        this.text = text;
        this.r = new randomText();
    }

    @Override
    public void handle(ActionEvent arg0) {
        // TODO Auto-generated method stub
        text.setText(r.randomTString());
    }
}