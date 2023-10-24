package controleur;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import model.Constantes;
import model.Jeu;
import vue.VueGraphique;

import java.util.Random;

public class Control_Clavier implements EventHandler<KeyEvent> {
    Jeu model;
    VueGraphique vue;


    public Control_Clavier(Jeu j, VueGraphique v){
        this.model = j;
        this.vue = v;
    }


    @Override
    public void handle(KeyEvent keyEvent) {
//        System.out.println(keyEvent.getCode().getName());
        String direction_key = keyEvent.getCode().getName();
        Constantes.Direction direction = null;
        switch (direction_key) {
            case "Right" -> {
                direction = Constantes.Direction.DROITE;
            }
            case "Down" -> {
                direction = Constantes.Direction.BAS;
            }
            case "Left" -> {
                direction = Constantes.Direction.GAUCHE;
            }
            case "Up" -> {
                direction = Constantes.Direction.HAUT;
            }
            case "R" -> {
                this.model.init_Jeu();
                this.vue.initialisatoin();
            }
        }

        if(direction_key.equals("Q") || direction_key.equals("Esc")){
            System.exit(0);
        }



        if(direction != null){
            model.joue_un_coup(direction);
            if ((new Random().nextInt(20)) == 1){

//                System.out.println("creer!");

                model.creerUneMurEtAjout();
                vue.update();
            }
        }

        vue.update();
    }
}
