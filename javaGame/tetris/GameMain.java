package tetris;
import javax.swing.*;

public class GameMain extends JFrame {
	public GameMain(){
		super("����˹����");
		GameWin g = new GameWin();
		add(g);
		setSize(380,410);
		setLocation(450,150);
		setVisible(true);
		setResizable(false);// ���ܵ������ڴ�С
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new GameMain();
	}
}
