package main;

import affichage.*;
import animals.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;

public class Core {

	public static void main(String[] args) throws InterruptedException{
		Birds bird=new BirdJaune(100,420);
		bird=new AntiGravitation(bird);
		bird=new SpeedX2(bird);
		Pig pig=new Pig();
		pig.radomPig();
		Affichage aff=new Affichage(800,600);
		Environement env=new Environement();
		aff.setEnv(env);
		aff.setBird(bird);
		aff.setPig(pig);
		Frame frame = new Frame("Oiseau pas content");
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                System.exit(0);
            }
        });

        frame.add(aff);
        frame.pack();
        frame.setVisible(true);
	}
	
}
