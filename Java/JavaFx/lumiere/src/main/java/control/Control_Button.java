package control;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.shape.Rectangle;
import model.Constantes;
import model.Jeu;
import vue.VueGraphique;

public class Control_Button implements EventHandler<ActionEvent> {
    private VueGraphique vue;
    private Jeu model;
    private Control_Case control_case;
    private boolean lock;


    public Control_Button(Jeu j, VueGraphique v, Control_Case c) {
        this.vue = v;
        this.model = j;
        this.control_case = c;
        this.lock = false;

        activate_case_opcite();

    }

    public void activate_case_opcite() {
        for (int x = 0; x < Constantes.TAILLE_JEU; x++) {
            for (int y = 0; y < Constantes.TAILLE_JEU; y++) {
                model.getCases_rec()[y][x].setOnMouseMoved(mouseEvent -> {
                    Rectangle r = (Rectangle) mouseEvent.getSource();
                    r.setOpacity(0.6);
                });

                model.getCases_rec()[y][x].setOnMouseExited(mouseEvent -> {
                    Rectangle r = (Rectangle) mouseEvent.getSource();
                    r.setOpacity(1);
                });
            }
        }
    }

    public void dactivate_case_opcite() {
        for (int x = 0; x < Constantes.TAILLE_JEU; x++) {
            for (int y = 0; y < Constantes.TAILLE_JEU; y++) {
                model.getCases_rec()[y][x].setOnMouseMoved(null);
                model.getCases_rec()[y][x].setOnMouseExited(null);
            }
        }
    }

    @Override
    public void handle(ActionEvent actionEvent) {

//        System.out.println(actionEvent.getSource());
//        System.out.println(((Button)actionEvent.getSource()).getText());

        int condition = Integer.parseInt(((Button) actionEvent.getSource()).getText());

        switch (condition) {
            // 0 jouer
            case 0 -> {
                if (!lock) {
                    this.control_case.setStop(false);
                    this.control_case.setMode_configurer(false);
                    this.vue.getVueGridPane().setOnMouseClicked(control_case);
                    lock = true;

                    this.vue.getButtons()[1].setBackground(new Background(this.model.getImages()[5]));
                    this.vue.getButtons()[2].setBackground(new Background(this.model.getImages()[6]));
                }
            }

            // 1 configurer
            case 1 -> {
                if (!lock) {
                    this.control_case.setStop(false);
                    this.control_case.setMode_configurer(true);
                    this.vue.getVueGridPane().setOnMouseClicked(control_case);
                }
            }

            // 2 aleatoire
            case 2 -> {
                if (!lock) {
                    this.model.init();
                    this.vue.getVueGridPane().update();


                }
            }

            // 3 quitter
            case 3 -> {
                this.model.setNb_deplacement(0);
                this.vue.getVueGridPane().setOnMouseClicked(null);
                this.vue.update_texte();
                lock = false;

                this.vue.getButtons()[1].setBackground(new Background(this.model.getImages()[1]));
                this.vue.getButtons()[2].setBackground(new Background(this.model.getImages()[2]));
                this.vue.getButtons()[0].setBackground(new Background(this.model.getImages()[0]));

                if(this.vue.siLanceFenetre()){
                    this.model.init();
                    this.vue.getVueGridPane().update();
                    this.vue.setLanceFenetre(false);
                }
            }
        }


    }
}
