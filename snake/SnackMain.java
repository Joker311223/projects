package snake;

import javax.swing.*;

public class SnackMain extends JFrame {
	public SnackMain() {
		super("̰����");
		SnackWin win = new SnackWin();
		add(win);
		setSize(435, 380);
		setLocation(450, 150);// ���ڳ���λ��
		setVisible(true);
		setResizable(false);// ���ܵ������ڴ�С
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new SnackMain();
	}

}
