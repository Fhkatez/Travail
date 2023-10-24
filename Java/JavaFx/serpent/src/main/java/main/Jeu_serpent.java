package main;

import controleur.Control_Clavier;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Jeu;
import vue.VueGraphique;

public class Jeu_serpent extends Application {
    @Override
    public void start(Stage stage){
        Jeu model = new Jeu();

        VueGraphique vg = new VueGraphique(model);
        Scene scene = new Scene(vg, 519,415);

        Control_Clavier cc = new Control_Clavier(model,vg);
        scene.setOnKeyReleased(cc);

        stage.setScene(scene);
        stage.show();
    }

}
