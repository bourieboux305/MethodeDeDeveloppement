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
		Birds bird=new BirdRed(100,400);
		Affichage aff=new Affichage(800,600);
		aff.setBird(bird);
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
