package View;
import Controller.OpenUserInput;
import Controller.SendNowEvent;
import Controller.UpdateMessageField;
import Controller.UpdateSenderField;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;

public class GUI extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        HBox fldFakeMessage = new HBox();
        fldFakeMessage.prefWidthProperty().bind(stage.widthProperty());
        root.prefWidthProperty().bind(stage.widthProperty());
        
        
        Button openUserInput = new Button("?");
        fldFakeMessage.getChildren().addAll(
            new TextField(),
            new Button("Send"),
            openUserInput
            
        );
        root.setBottom(fldFakeMessage);

        VBox userInput = new VBox(25);
        userInput.setPadding(new Insets(55));
        VBox senderVBox = new VBox(5);
       
        HBox sender = new HBox();
        Button btnRandomizeSender = new Button("Randomize");
        TextField fldSender = new TextField();
        btnRandomizeSender.setOnAction(new UpdateSenderField(fldSender));
      
       
        
        sender.getChildren().addAll(
            fldSender,
            btnRandomizeSender 
            
            
        );
        senderVBox.getChildren().addAll( 
            makeLabel("Sender:"),
            sender
            
        );


        VBox messageVBox = new VBox(5);
    
        HBox message = new HBox();
        Button btnRandomizeMessage = new Button("Randomize");
        TextArea fldMessage = new TextArea();
       
        fldMessage.setMaxHeight(fldMessage.getHeight() / 1.5);
        btnRandomizeMessage.snapPositionY(fldMessage.getHeight()/2);
        btnRandomizeMessage.setOnAction(new UpdateMessageField(fldMessage));
        message.getChildren().addAll(
            fldMessage,
            btnRandomizeMessage
        );

        messageVBox.getChildren().addAll(
            makeLabel("Message:"),
            message
        );


        HBox deliveryTime = new HBox(25);
        deliveryTime.setAlignment(Pos.CENTER);
        Button btnSendNow = new Button("Send Now");
     
        Button btnScheduleSend = new Button("Schedule Send");
        btnSendNow.setMaxWidth(Double.MAX_VALUE);
        btnSendNow.setBackground(new Background(new BackgroundFill(Color.BLUE, new CornerRadii(20), Insets.EMPTY)));
        btnSendNow.setFont(new Font("Helvetica Neue", 15));
        btnSendNow.setTextFill(Color.WHITE);
        HBox.setHgrow(btnSendNow, Priority.ALWAYS);
        deliveryTime.getChildren().addAll(
            btnSendNow, 
            btnScheduleSend
            
        );
        
        userInput.getChildren().addAll(
            senderVBox,
            messageVBox,
            deliveryTime
        );
    
        Stage popUserInput = new Stage();
        popUserInput.initModality(Modality.APPLICATION_MODAL);
        popUserInput.initOwner(stage);
        popUserInput.setAlwaysOnTop(true);
        popUserInput.setScene(new Scene(userInput));
        openUserInput.setOnAction(new OpenUserInput(popUserInput));
        
        stage.setScene(new Scene(root));
        root.prefWidthProperty().bind(stage.getScene().widthProperty());
        btnSendNow.setOnAction(new SendNowEvent(popUserInput, root, fldMessage, fldSender));
        stage.setTitle("SMS");
        stage.show();
    }
    

    public Label makeLabel(String text) {
        Label lbl = new Label(text);
        lbl.setFont(new Font("Helvetica Neue", 15));
        return lbl;

    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
