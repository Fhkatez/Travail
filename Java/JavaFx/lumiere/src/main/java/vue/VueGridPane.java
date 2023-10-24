package vue;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model.Constantes;
import model.Jeu;

import static model.Constantes.TAILLE_JEU;

public class VueGridPane extends GridPane {
    private Jeu model;

    public VueGridPane(Jeu model){
        this.model = model;

        this.setHgap(-1);
        this.setVgap(-1);

        for(int y = 0; y < TAILLE_JEU ; y++){
            for(int x =0; x < TAILLE_JEU ; x++){
                this.add(model.getCases_rec()[y][x],x,y);
            }
        }

    }

    public void update(){
        for(int y = 0; y < TAILLE_JEU;y++){
            for(int x = 0; x < TAILLE_JEU;x++){
                boolean allume = model.getCases_pos()[y][x].isAllume();
                if(allume){
                    model.getCases_rec()[y][x].setFill(Color.web(Constantes.couleur_allume));
                }else{
                    model.getCases_rec()[y][x].setFill(Color.web(Constantes.couleur_eteint));
                }
            }
        }
    }







}
