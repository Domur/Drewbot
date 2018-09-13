package keytest;


import javax.swing.JFrame;

public class mainclass {

public JFrame Frame;

public static void main(String[] args0){
	keytestering g = new keytestering();
}

public mainclass(){
	setupFrame();

	Frame.setVisible(true);
}

public void setupFrame(){
	Frame = new JFrame("White Tile Game");
	Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	Frame.setBounds(0,0,600,600);
	Frame.setLayout(null);
}}