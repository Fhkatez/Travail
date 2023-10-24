package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public class Jeu {
    ArrayList<Position> serpent;
    ArrayList<Position> murs;
    Position pomme;
    boolean mort;

    ArrayList<Rectangle> serpent_rec;
    Rectangle pomme_rec;

    public void init_Jeu(){
        this.serpent = new ArrayList<Position>(0);
        this.murs = new ArrayList<Position>(0);
        this.mort = false;

        pomme_rec = new Rectangle(Constantes.Taille_Case,Constantes.Taille_Case,Color.web(Constantes.pomme_couleur));
        this.serpent_rec = new ArrayList<Rectangle>(0);

        this.initSerpent();

        this.initMur();

        this.nouvellePosition_pomme();


    }

    public Jeu(){
        this.init_Jeu();
    }

    public void nouvellePosition_pomme(){
        Random r = new Random();
        int x_pomme;
        int y_pomme;
        do{
            x_pomme = r.nextInt(Constantes.Taille_Colonnes);
            y_pomme = r.nextInt(Constantes.Taille_Lignes);
        }while (siPdansSerpent(new Position(x_pomme,y_pomme)) || siPdansMur(new Position(x_pomme,y_pomme)));

        this.pomme = new Position(x_pomme,y_pomme);
    }

    private boolean position_autorisee(Position position){
        int x = position.getX();
        int y = position.getY();
        if(x >= 0 && x < Constantes.Taille_Colonnes && y >= 0 && y < Constantes.Taille_Lignes && !this.siPdansMur(new Position(x,y))
        && !this.siPdansSerpent(new Position(x,y))){
            return true;


        }else{
            return false;
        }
    }

public void joue_un_coup(Constantes.Direction direction){
    if(!mort){
        int x = serpent.get(0).getX();
        int y = serpent.get(0).getY();
        switch (direction){
            case BAS -> y++;
            case DROITE -> x++;
            case GAUCHE -> x--;
            case HAUT -> y--;
        }

        Position position_d = new Position(x,y);

        Position position_dernier = new Position(serpent.get(serpent.size()-1).getX(),serpent.get(serpent.size()-1).getY());

        if (this.position_autorisee(position_d)){
            for(int i = serpent.size()-1; i > 0 ; i--){
                serpent.get(i).setX(serpent.get(i-1).getX());
                serpent.get(i).setY(serpent.get(i-1).getY());
            }

            serpent.get(0).setX(x);
            serpent.get(0).setY(y);

            if(pomme.getX() == x && pomme.getY() == y){
                //ajoutserpent()
                this.ajoutserpent(position_dernier);
                this.nouvellePosition_pomme();

                //System.out.println(serpent);
            }

        }else{
            //terminer le jeu
            this.mort = true;
            System.out.println("game over, presse R to restart");

        }

    }else{
        System.out.println("game over, presse R to restart");
    }



}

    public void ajoutserpent(Position p){
        boolean first = (this.serpent.size() == 0);
        this.serpent.add(p);

        Rectangle r = new Rectangle(Constantes.Taille_Case,Constantes.Taille_Case);
        if(first){
            r.setFill(Color.web(Constantes.tete_couleur));
        }else{
            r.setFill(Color.web(Constantes.corps_couleur));
        }

        serpent_rec.add(r);
    }

    public void ajoutmurs(Position p){
        this.murs.add(p);
    }



    @Override
    public String toString() {
        String[][] plat = new String[Constantes.Taille_Lignes][Constantes.Taille_Colonnes+2];
        for(int i = 0; i < plat.length ; i++){
            for(int j = 0; j < plat[0].length;j++){
                plat[i][j] = " ";
            }
        }

        String s = "";

        for(Position p : serpent){
            int x = p.getX()+1;
            int y = p.getY();
            plat[y][x] = "S";
        }

        for (Position p : murs){
            int x = p.getX()+1;
            int y = p.getY();
            plat[y][x] = "M";
        }

        for(int i = 0;i<plat.length;i++){
            plat[i][0] = "|";
            plat[i][26] = "|";
        }

        String top = "-------------------------";
        s = top+"\n";

        for(int i = 0; i < plat.length ; i++){
            for(int j = 0; j < plat[0].length;j++){
                s += plat[i][j];
            }
            s += "\n";
        }

        s += top;

        return s;
    }

    public boolean siPdansMur(Position p){
        boolean resultat = false;
        for(Position i : this.murs){
            if(i.equals(p)){
                resultat = true;
            }
        }
        return resultat;
    }

    public boolean siPdansSerpent(Position p){
        boolean resultat = false;
        for(Position i : this.serpent){
            if(i.equals(p)){
                resultat = true;
            }
        }
        return resultat;
    }



    public ArrayList<Position> getSerpent() {
        return serpent;
    }

    public ArrayList<Position> getMurs() {
        return murs;
    }

    public Position getPomme() {
        return pomme;
    }

    public ArrayList<Rectangle> getSerpent_rec() {
        return serpent_rec;
    }

    public Rectangle getPomme_rec() {
        return pomme_rec;
    }

    public void creerUneMurEtAjout(){
        Random r= new Random();
        int x_mur;
        int y_mur;
        do{
            x_mur = r.nextInt(Constantes.Taille_Colonnes);
            y_mur = r.nextInt(Constantes.Taille_Lignes);
        }while (this.siPdansSerpent(new Position(x_mur,y_mur)));
        this.ajoutmurs(new Position(x_mur,y_mur));
    }

    public void initMur(){
        Random r = new Random();
        int nb_mur = r.nextInt(10);
        for(int i = 0; i < nb_mur ; i++){
            creerUneMurEtAjout();
        }
    }

    public void initSerpent(){
        Random r = new Random();
        int x_serpent = r.nextInt(5,20);
        int y_serpent = r.nextInt(5,15);
        int direction = r.nextInt(4);

//        System.out.println(direction);

        //tete
        this.ajoutserpent(new Position(x_serpent,y_serpent));

        switch (direction){
            case 0 ->{ //up
                for (int i = 0; i < 3 ; i++){
                    this.ajoutserpent(new Position(x_serpent,--y_serpent));
                }
            }
            case 1 ->{ //down
                for (int i = 0; i < 3 ; i++){
                    this.ajoutserpent(new Position(x_serpent,++y_serpent));
                }
            }
            case 2 ->{ //left
                for (int i = 0; i < 3 ; i++){
                    this.ajoutserpent(new Position(--x_serpent,y_serpent));
                }
            }
            case 3 ->{ //right
                for (int i = 0; i < 3 ; i++){
                    this.ajoutserpent(new Position(++x_serpent,y_serpent));
                }
            }
        }



    }

//    //test
//    public static void main(String[] args) {
//        ArrayList<Integer> list1 = new ArrayList<Integer>(0);
//        list1.add(2);
//        ArrayList<Integer> list2 = new ArrayList<Integer>(list1);
//        list1.add(3);
//        System.out.println(list2);
//
//    }
}
