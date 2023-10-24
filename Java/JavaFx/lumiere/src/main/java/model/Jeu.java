package model;

import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

import static model.Constantes.TAILLE_CASE;
import static model.Constantes.TAILLE_JEU;

public class Jeu {
    private Rectangle[][] cases_rec;
    private Position[][] cases_pos;

    //Jouer Configurer aleatoire quitter
    private BackgroundImage[] images;
    private int nb_deplacement;


    public Jeu(){
        nb_deplacement = 0;
        images = new BackgroundImage[Constantes.CHEMAIN_IMAGE.length];
        cases_rec = new Rectangle[TAILLE_JEU][TAILLE_JEU];
        cases_pos = new Position[TAILLE_JEU][TAILLE_JEU];

        for(int x = 0; x < TAILLE_JEU ; x++ ){
            for (int y = 0; y < TAILLE_JEU;y++){
                Rectangle r = new Rectangle(TAILLE_CASE,TAILLE_CASE, Color.web(Constantes.couleur_eteint));
                r.setStrokeWidth(2);
                r.setArcWidth(15);
                r.setArcHeight(15);
                r.setStroke(Color.rgb(0,0,0));

                cases_rec[y][x] = r;
                cases_pos[y][x] = new Position(x,y);
            }
        }

//        FileInputStream fis = null;
        InputStream is = null;
        for(int i = 0; i < Constantes.CHEMAIN_IMAGE.length; i++){


//            try{
//                fis = new FileInputStream(Constantes.CHEMAIN_IMAGE[i]);
//                BackgroundImage bgi = new BackgroundImage(new Image(fis),
//                        BackgroundRepeat.NO_REPEAT,
//                        BackgroundRepeat.NO_REPEAT,
//                        BackgroundPosition.DEFAULT,
//                        new BackgroundSize(60,55,false,false,
//                                false,false));
//
//                images[i] = bgi;
//
//            }catch (FileNotFoundException f){
//                System.out.println(Constantes.CHEMAIN_IMAGE[i]+" n'est pas trouvee");
//            }

            is = this.getClass().getResourceAsStream(Constantes.CHEMAIN_IMAGE[i]);
            Image image;

//            System.out.println(is);
            if(is != null){
                image = new Image(is);
                BackgroundImage bgi = new BackgroundImage(image,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.DEFAULT,
                        new BackgroundSize(60,55,false,false,
                                false,false));
                images[i] = bgi;
            }

        }

        try{
            if(is != null){
                is.close();
            }
        }catch (IOException ioe){
            ioe.getStackTrace();
        }



//        try {
//            fis.close();
//        }catch (IOException ioe){
//            ioe.getStackTrace();
//        }






        this.init();


    }

    public void init(){

        for(int x = 0; x < TAILLE_JEU ; x++ ){
            for (int y = 0; y < TAILLE_JEU;y++){
                cases_pos[y][x].setAllume(false);
            }
        }

        //allumer aleatoire
        Random random = new Random();
//        int nb_lampe_allume = random.nextInt(9)+1;
        int nb_lampe_allume = Constantes.NB_LAMPE_ALEATOIRE;
        for(int i = 0; i < nb_lampe_allume+1 ; i++){
            int lampe_x = random.nextInt(TAILLE_JEU);
            int lampe_y = random.nextInt(TAILLE_JEU);
            cases_rec[lampe_y][lampe_x].setFill(Color.web(Constantes.couleur_allume));
            cases_pos[lampe_y][lampe_x].setAllume(true);
        }

        //cliquer [0][0] pour gagner le jeu
//        this.cheat_game();

    }

    public void cliquer_case(Position p){
        int x = p.getX();
        int y = p.getY();

//        System.out.println("x:"+x+"/ y: "+y);


        cases_pos[y][x].setAllume(!cases_pos[y][x].isAllume());

        //up
        if(y-1 >= 0){
//            System.out.println("up");
            cases_pos[y-1][x].setAllume(!cases_pos[y-1][x].isAllume());
        }
        //down
        if(y+1 < TAILLE_JEU){
//            System.out.println("down");
            cases_pos[y+1][x].setAllume(!cases_pos[y+1][x].isAllume());
        }
        //left
        if(x-1 >= 0){
//            System.out.println("left");
            cases_pos[y][x-1].setAllume(!cases_pos[y][x-1].isAllume());
        }
        //right
        if(x+1 < TAILLE_JEU){
//            System.out.println("right");
            cases_pos[y][x+1].setAllume(!cases_pos[y][x+1].isAllume());
        }

        this.nb_deplacement++;

    }

    public void configurer_case(Position p){
        int x = p.getX();
        int y = p.getY();
        cases_pos[y][x].setAllume(!cases_pos[y][x].isAllume());
    }


    public void cheat_game(){
        for(int x = 0; x < TAILLE_JEU ; x++ ){
            for (int y = 0; y < TAILLE_JEU;y++){
                cases_pos[y][x].setAllume(true);
            }
        }

        cases_pos[0][0].setAllume(false);
        cases_pos[0][1].setAllume(false);
        cases_pos[1][0].setAllume(false);
    }

    public int siJeufini(){ //0 nonfini // 1 gagner // 2 echec
        boolean tousAllume = true;
        boolean touseteint = true;
        boolean a = true;
        boolean e = true;

        for(int x = 0; x < TAILLE_JEU ; x++ ){
            for (int y = 0; y < TAILLE_JEU;y++){
                if(cases_pos[y][x].isAllume() && e){
                    touseteint = false;
                    e = false;
                }

                if(!cases_pos[y][x].isAllume() && a){
                    tousAllume = false;
                    a = false;
                }
            }
        }

        if(tousAllume){
            return 1;
        }else if(touseteint){
            return 2;
        }else {
            return 0;
        }

    }

    public Position[][] getCases_pos() {
        return cases_pos;
    }

    public Rectangle[][] getCases_rec() {
        return cases_rec;
    }

    public BackgroundImage[] getImages() {
        return images;
    }

    public int getNb_deplacement() {
        return nb_deplacement;
    }

    public void setNb_deplacement(int nb_deplacement) {
        this.nb_deplacement = nb_deplacement;
    }
}
