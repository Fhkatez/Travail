package control;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.Constantes;
import model.Jeu;
import model.Position;
import vue.VueGraphique;

public class Control_Case implements EventHandler<MouseEvent> {
    private Jeu model;
    private VueGraphique vue;
    private boolean mode_configurer;
    private boolean stop;


    public Control_Case(Jeu j, VueGraphique v) {
        this.stop = false;
        this.model = j;
        this.vue = v;
        this.mode_configurer = false;
    }


    @Override
    public void handle(MouseEvent mouseEvent) {
        //System.out.println(mouseEvent.getSceneX()+"/"+mouseEvent.getSceneY());

        boolean arret = false;
        double scene_x = mouseEvent.getSceneX();
        double scene_y = mouseEvent.getSceneY();
        int count = 0;
        double temp = 0;
        int x;
        int y;

        while (count * Constantes.TAILLE_CASE < scene_x) {
            count++;
        }
        x = count - 1;
        if (x < 0) {
            x = 0;
        } else if (x >= Constantes.TAILLE_JEU) {
            x = 4;
        }

        count = 0;
        while (count * Constantes.TAILLE_CASE < scene_y) {
            count++;
        }
        y = count - 1;
        if (y < 0) {
            y = 0;
        } else if (y >= Constantes.TAILLE_JEU) {
            y = 4;
        }

//        System.out.println("x:"+x+"/ y: "+y);

        if (mode_configurer) {
            model.configurer_case(new Position(x, y));
            vue.getVueGridPane().update();

        } else {
            if (!stop) {

                model.cliquer_case(new Position(x, y));
                vue.update_texte();
                vue.getVueGridPane().update();
                int fini = model.siJeufini();
                if (fini == 1) {
                    this.stop = true;
                    this.vue.lancer_fenetre("Vous avez gagne!");
                } else if (fini == 2) {
                    this.stop = true;
                    this.vue.lancer_fenetre("Vous avez perdu...");
                }

            }
        }
    }

    public void setMode_configurer(boolean mode_configurer) {
        this.mode_configurer = mode_configurer;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public boolean isStop() {
        return stop;
    }
}
