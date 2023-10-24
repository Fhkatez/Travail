package vue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Constantes;
import model.Jeu;

public class VueGraphique extends VBox {
    private boolean lanceFenetre;
    private VueGridPane vueGridPane;
    private Jeu model;
    private Button[] buttons;
    private Text nb_deplacement_texte;

    public VueGraphique(Jeu j, VueGridPane vgp){
        lanceFenetre = false;
        this.buttons = new Button[Constantes.CHEMAIN_IMAGE.length-3]; // -3 car 3 images ban
        this.vueGridPane = vgp;
        this.model = j;
        this.setSpacing(10);
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(0,20,10,20));
        hBox.setSpacing(7);

        Rectangle r_texte = new Rectangle(90,55);
        r_texte.setStroke(Color.BLACK);
        r_texte.setFill(Color.WHITE);
        r_texte.setArcHeight(15);
        r_texte.setArcWidth(15);
        nb_deplacement_texte = new Text("0");
        nb_deplacement_texte.setFont(Font.font(20));

        StackPane sp_texte = new StackPane();
        sp_texte.getChildren().addAll(r_texte,nb_deplacement_texte);
        //Button
        for(int i = 0;i < buttons.length ; i++){
            Button button = new Button(""+i);
            button.setFont(Font.font(0));
            button.setPrefHeight(55);
            button.setPrefWidth(60);
            button.setBackground(new Background(this.model.getImages()[i]));
            buttons[i] = button;
        }

        hBox.getChildren().addAll(buttons[0],sp_texte);

        for(int i = 1 ; i < buttons.length; i++){
            hBox.getChildren().add(buttons[i]);
        }

        this.getChildren().addAll(vgp,hBox);

    }

    public void update_texte(){
        nb_deplacement_texte.setText(""+model.getNb_deplacement());
    }

    public void lancer_fenetre(String texte){

        lanceFenetre = true;

        this.buttons[0].setBackground(new Background(this.model.getImages()[4]));


        Stage stage = new Stage();
        Text message = new Text(texte);
        message.setFont(Font.font(15));
        Button button = new Button("OK!");
        button.setOnAction(actionEvent -> {
            stage.close();
        });
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(20,20,20,20));
        vBox.setSpacing(10);
        vBox.getChildren().addAll(message,button);
        stage.setScene(new Scene(vBox,200,200));
        stage.show();
    }


    public VueGridPane getVueGridPane() {
        return vueGridPane;
    }

    public Button[] getButtons() {
        return buttons;
    }

    public boolean siLanceFenetre() {
        return lanceFenetre;
    }

    public void setLanceFenetre(boolean b){
        this.lanceFenetre = b;
    }
}
