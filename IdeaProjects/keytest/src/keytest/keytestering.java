package keytest;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class keytestering extends JFrame implements KeyListener {

	JTextField keyText = new JTextField(80);
	JLabel keyLabel = new JLabel("Press fire button");
	ImageIcon icon = new ImageIcon(getClass().getResource("blackcolor.png"));
	ImageIcon icon2 = new ImageIcon(getClass().getResource("yellowcolor.jpg"));
	ImageIcon icon3 = new ImageIcon(getClass().getResource("redcolor.png"));
	ImageIcon icon4 = new ImageIcon(getClass().getResource("whitecolor.png"));
	ArrayList<JLabel> picarray = new ArrayList<JLabel>();
	JLabel picl = new JLabel(icon3);
	JLabel picl2 = new JLabel(icon2);
	JLabel score = new JLabel("Score: 0");
	JLabel scoreBackground = new JLabel(icon4);
	JFrame fr = new JFrame();
	Random r = new Random();
	int x = 0;
	int y = 0;
	int direction = 1;
	boolean gameOn = false;
	boolean treatOnField = false;
	int miliseconds = 0;
	int seconds = 0;
	int x1 = 0;
	int y1 = 0;
	int index = 0;
	
	keytestering (){
		keyText.addKeyListener(this);
		fr.setSize(435,478);
		fr.setVisible(true);
		fr.setLocationRelativeTo(null);
		fr.setLayout(null);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.setResizable(true);
		keyText.setBounds(398,500,50,50);
		
		picl.setBounds(-40,-40,20,20);
		fr.add(picl);
		
		fr.add(keyLabel);
		fr.add(keyText);
		
		score.setBounds(190,420,420,20);
		score.setHorizontalTextPosition(JLabel.RIGHT);
		scoreBackground.setBounds(0,420,420,20);
		
		fr.add(score);
		fr.add(scoreBackground);
		
		
		picarray.add(0, new JLabel(icon));
		picarray.get(0).addKeyListener(this);
		picarray.get(0).setBounds(0,0,20,20);
		fr.add(picarray.get(0));
	}
	
	public void keyTyped(KeyEvent e){
		
	}
	public void keyPressed(KeyEvent e){
		//starts game
		if(gameOn == false){
			gameOn = true;
			clock();
		}
		//creates a treat on the field since there isn't one there initially
		if(treatOnField == false){
			randomTreat();
			
		}
		int keyCode = e.getKeyCode();
		//checks if key pressed matches an arrow key
		if(keyCode == KeyEvent.VK_LEFT && direction != 1){
			
			if(index == 0){
				direction = 3;
			}
			if(index > 0){
				if(picarray.get(0).getY() != picarray.get(1).getY()){
					direction = 3;
				}
			}
			picl.repaint();
			
		}
		else if(keyCode == KeyEvent.VK_RIGHT && direction != 3){
			if(index == 0){
				direction = 1;
				}
				if(index > 0){
					if(picarray.get(0).getY() != picarray.get(1).getY()){
						direction = 1;
					}
				}
				picl.repaint();
		}
		else if(keyCode == KeyEvent.VK_DOWN && direction != 2){
			if(index == 0){
				direction = 4;
				}
				if(index > 0){
					if(picarray.get(0).getX() != picarray.get(1).getX()){
						direction = 4;
					}
				}
				picl.repaint();
		}
		else if(keyCode == KeyEvent.VK_UP && direction != 4){
			if(index == 0){
				direction = 2;
				}
				if(index > 0){
					if(picarray.get(0).getX() != picarray.get(1).getX()){
						direction = 2;
					}
				}
				picl.repaint();
		}
		
	}
	public void keyReleased(KeyEvent e){
		
	}
public void clock(){
		
		Thread t = new Thread(
				new Runnable(){
				
				@Override
				public void run() {
					
					while(gameOn){
						//checks if head location equals treat location
						picl.setBounds(-40,-40,20,20);
						if(picarray.get(0).getX() == picl2.getX() && picarray.get(0).getY() == picl2.getY() && treatOnField){
							index++;
							picarray.add(index, new JLabel(icon));
							picarray.get(index).setBounds(picarray.get(index-1).getX(), picarray.get(index-1).getY(),20,20);
							fr.add(picarray.get(index));
							randomTreat();
							score.setText("Score: " + index);
							
						}
						//performs these actions every 150 milliseconds
						if(miliseconds == 150){
							miliseconds = 0;
							//limits gameplay to within the borders
							if(picarray.get(0).getX()+21 > 419 && direction == 1){
								System.out.println("xd u lost");
								gameStop();
								picl.setBounds(picarray.get(0).getX(),picarray.get(0).getY(),20,20);
							}
							else if(picarray.get(0).getX()-21 < 0 && direction == 3){
								System.out.println("xd u lost");
								gameStop();
								picl.setBounds(picarray.get(0).getX(),picarray.get(0).getY(),20,20);
							}
							else if(picarray.get(0).getY()-21 < 0 && direction == 2){
								System.out.println("xd u lost");
								gameStop();
								picl.setBounds(picarray.get(0).getX(),picarray.get(0).getY(),20,20);
							}
							else if(picarray.get(0).getY()+21 > 419 && direction == 4){
								System.out.println("xd u lost");
								gameStop();
								picl.setBounds(picarray.get(0).getX(),picarray.get(0).getY(),20,20);
							}
							
							//moves block in the direction of arrow key and moves all following blocks to the location of the block before it
							if(direction == 1 && gameOn){
								x+=21;
								for(int i = picarray.size()-1; i>0; i--){
								
								picarray.get(i).setBounds(picarray.get(i-1).getX(),picarray.get(i-1).getY(),20,20);
								}
								picarray.get(0).setBounds(picarray.get(0).getX()+21,picarray.get(0).getY(),20,20);
								
							}
							else if(direction == 2 && gameOn){
								y-=21;
								for(int i = picarray.size()-1; i>0; i--){
								
								picarray.get(i).setBounds(picarray.get(i-1).getX(),picarray.get(i-1).getY(),20,20);
								}
								picarray.get(0).setBounds(picarray.get(0).getX(),picarray.get(0).getY()-21,20,20);
								
							}
							else if(direction == 3 && gameOn){
								
								
								for(int i = picarray.size()-1; i>0; i--){
								
								picarray.get(i).setBounds(picarray.get(i-1).getX(),picarray.get(i-1).getY(),20,20);
								}
								picarray.get(0).setBounds(picarray.get(0).getX()-21,picarray.get(0).getY(),20,20);
								
							}
							else if(direction == 4 && gameOn){
								y+=21;
								
								for(int i = picarray.size()-1; i>0; i--){
								
								picarray.get(i).setBounds(picarray.get(i-1).getX(),picarray.get(i-1).getY(),20,20);
								}
								picarray.get(0).setBounds(picarray.get(0).getX(),picarray.get(0).getY()+21,20,20);
								
							}
							//loss conditions
							for(int i = 1; i<picarray.size(); i++){
								if(picarray.get(0).getX() == picarray.get(i).getX() && picarray.get(0).getY() == picarray.get(i).getY()){
									System.out.println("xd u lost");
									gameStop();
									picl.setBounds(picarray.get(0).getX(),picarray.get(0).getY(),20,20);
									//fr.repaint();
								}
							}
							
						}
						miliseconds++;
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							//TODO AUTOGENERATED CATCH BLOCK
						e.printStackTrace();
						}
					}
				}
			});
		t.start();
		
		t = null;
		
	}
	//creates a treat in a random square on the grid
	public void randomTreat(){
		System.out.println("ran treat");
		treatOnField = true;
		int rando = r.nextInt(20);
		int rando2 = r.nextInt(20);
		picl2.setBounds(rando*21,rando2*21,20,20);
		fr.add(picl2);
		fr.repaint();
	}
	//stops the game
	public void gameStop(){
		gameOn = false;
	}
}
