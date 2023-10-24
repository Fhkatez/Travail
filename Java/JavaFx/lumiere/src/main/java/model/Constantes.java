package model;

import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Constantes {
    public static final int TAILLE_JEU = 5;
    public static final int TAILLE_CASE = 80;
    public static final String couleur_eteint = "00651FFF";
    public static final String couleur_allume = "8FFC8EFF";
    public static final int NB_LAMPE_ALEATOIRE = 8;

    public static final String[] CHEMAIN_IMAGE = new String[]{
            "/main/button_Jouer.png",            //0
            "/main/button_configurer.png"        //1
            ,"/main/button_aleatoire.png"        //2
            ,"/main/button_quitter.png"          //3
            ,"/main/jouer_ban.png"               //4
            ,"/main/configurer_ban.png"          //5
            ,"/main/aleatoire_ban.png"};         //6
}
