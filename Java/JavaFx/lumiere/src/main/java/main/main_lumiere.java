package main;

import control.Control_Button;
import control.Control_Case;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Constantes;
import model.Jeu;
import vue.VueGraphique;
import vue.VueGridPane;

public class main_lumiere extends Application {

    @Override
    public void start(Stage stage){
        Jeu model = new Jeu();
        VueGridPane vueGridPane = new VueGridPane(model);
        VueGraphique vue = new VueGraphique(model,vueGridPane);
        Control_Case control_case = new Control_Case(model,vue);


        Control_Button control = new Control_Button(model,vue,control_case);

        
        for(int i = 0; i < vue.getButtons().length ; i++){
            vue.getButtons()[i].setOnAction(control);
        }

        Scene scene = new Scene(vue, Constantes.TAILLE_CASE*Constantes.TAILLE_JEU+8,Constantes.TAILLE_CASE*Constantes.TAILLE_JEU+100);

        stage.setTitle("Éteins la lumière");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

