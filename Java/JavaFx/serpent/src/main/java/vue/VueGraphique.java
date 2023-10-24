package vue;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import model.Constantes;
import model.Jeu;
import model.Position;

import java.util.Collection;

public class VueGraphique extends GridPane{
    Jeu model;
    int serpent_length;
    int nb_murs;

    public VueGraphique(Jeu model){
        this.model = model;

        this.setHgap(-1);
        this.setVgap(-1);


        this.initialisatoin();

    }

    private static void bouge_rectangle(Rectangle rectangle, Position position){
        GridPane.setColumnIndex(rectangle,position.getX());
        GridPane.setRowIndex(rectangle,position.getY());
    }

    //modifie private en public
    public void initialisatoin(){

        //plat
        for(int i = 0;i < 20;i++){
            for(int j = 0; j < 25; j++){
                Rectangle r = new Rectangle(Constantes.Taille_Case,Constantes.Taille_Case);
                r.setFill(Color.web(Constantes.terre_couleur));
                r.setStroke(Color.BLACK);
                this.add(r,j,i);
            }
        }

        //serpent
       for(int i = 0; i < model.getSerpent().size() ; i++){
           this.add(model.getSerpent_rec().get(i),model.getSerpent().get(i).getX(),model.getSerpent().get(i).getY());
       }

       this.add(model.getPomme_rec(),model.getPomme().getX(),model.getPomme().getY());

       //init serpent_length
       this.serpent_length = model.getSerpent().size();

       //mur
        for(int i = 0; i < model.getMurs().size() ; i++){
            this.add(new Rectangle(Constantes.Taille_Case,Constantes.Taille_Case,Color.web(Constantes.mur_couleur)),model.getMurs().get(i).getX(),model.getMurs().get(i).getY());
        }

        //init nb_mur
        this.nb_murs = model.getMurs().size();
    }

    public void update(){
        for(int i = 0;i < model.getSerpent().size() ; i++){
            bouge_rectangle(model.getSerpent_rec().get(i),model.getSerpent().get(i));
        }

        if(serpent_length != model.getSerpent().size()){
            this.add(model.getSerpent_rec().get(serpent_length),model.getSerpent().get(serpent_length).getX(),model.getSerpent().get(serpent_length).getY());
            serpent_length = model.getSerpent().size();
        }

        //System.out.println("x:"+model.getPomme().getX()+"/ y:"+model.getPomme().getY());
        bouge_rectangle(model.getPomme_rec(),model.getPomme());

        if(nb_murs != model.getMurs().size()){
            this.add(new Rectangle(Constantes.Taille_Case,Constantes.Taille_Case,Color.web(Constantes.mur_couleur)),model.getMurs().get(nb_murs).getX(),model.getMurs().get(nb_murs).getY());
            this.nb_murs = model.getMurs().size();
        }


    }

}
