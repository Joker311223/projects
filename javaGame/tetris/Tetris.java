package tetris;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;//�һ�Source->Ogranize Imports�������������
import javax.swing.JPanel;
import javax.swing.Timer;

public class Tetris extends JFrame {
	private int x = 0, y = 0;
	int blockType = 0;// 7��ͼ��
	int turnState = 0;// һ��ͼ�εĲ�ͬ״̬
	private final int shapes[][][] = new int[][][] {
			// I
			{ { 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0 },
					{ 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0 } },
			// S
			{ { 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
					{ 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 } },
			// Z
			{ { 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 } },
			// J
			{ { 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
			// O
			{ { 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
			// L
			{ { 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 }, { 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
			// T
			{ { 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 } } };

	public Tetris() {
		TetrisPanel tpanel = new TetrisPanel();
		add(tpanel);
		addKeyListener(tpanel);
	}

	private class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			y++;
			repaint();
		}
	}

	private class TetrisPanel extends JPanel implements KeyListener {
		private int map[][] = new int[13][23];

		public void add() {
			int i = 0;
			for (int a = 0; a < 4; a++)
				for (int b = 0; b < 4; b++) {
					if (map[x + 1 + b][y + a] == 0)
						map[x + 1 + b][y + a] = shapes[blockType][turnState][i];
					i++;
				}
		}

		public void right() {
			if (!isCollied(x + 1, y))
				x++; // ����ģ��ˢ�º����repaint()����ʹ״̬�ĸı����ֳ���
			repaint();
		}

		public void left() {
			if (!isCollied(x - 1, y))
				x--;
			repaint();
		}

		public void turn() {// ��תͼ��
			int temp = turnState;
			turnState = (turnState + 1) % 4;
			if (!isCollied(x, y))
				turnState = temp; // ���ر任ǰ��״̬
			repaint();
		}

		public void down() {
			if (!isCollied(x, y + 1))
				y++;
			else {
				add();
				deletLine();
				newBlock();
			}
			repaint();
		}

		int score = 0;

		public void deletLine() { // ����һ�� ******
			int c = 0;
			for (int b = 0; b < 22; b++)
				for (int a = 0; a < 11; a++)
					if (map[a][b] == 1) {
						c++;
						if (c == 10) {
							score += 10;
							for (int d = b; d > 0; d--)
								for (int e = 0; e > 11; e++) {
									map[e][d] = map[e][d - 1];
								}
						}
					}
			c = 0;// c�ڱ���һ�к����
		}

		public void keyTyped(KeyEvent e) {
		}

		public void keyReleased(KeyEvent e) {
		}

		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP: // ���ϼ�,��תͼ��
				turn();
				break;
			case KeyEvent.VK_DOWN: // ���¼�
				down();
				break;
			case KeyEvent.VK_LEFT: // �����
				left();
				break;
			case KeyEvent.VK_RIGHT: // ���Ҽ�
				right();
				break;
			}
		}

		public TetrisPanel() {
			cleanMap();
			drawWall();
			newBlock();
			Timer timer = new Timer(1000, new TimerListener());
			timer.start();
		}

		public void cleanMap() {// ����ѶѺõ�����
			for (int i = 1; i < 11; i++)
				for (int j = 0; j < 21; j++) {
					map[i][j] = 0;
				}
		}

		public void newBlock() {
			blockType = (int) (Math.random() * 1000) % 7;// 0��6֮�������������ѡ��һ��ͼ��
			turnState = (int) (Math.random() * 1000) % 4;// 0��4֮�������������ѡ��ͼ�ε�һ��״̬
			x = 4;
			y = 0;
		}

		public void drawWall() {// ����ǽ�����Ҫ��
			for (int i = 0; i < 12; i++) {
				map[i][21] = 2;
			}
			for (int j = 0; j < 22; j++) {
				map[0][j] = 2;
				map[11][j] = 2;
			}
		}

		public boolean isCollied(int x, int y) {// ��ײ��� *****
			for (int a = 0; a < 4; a++)
				for (int b = 0; b < 4; b++) {
					if ((shapes[blockType][turnState][a * 4 + b] == 1) && (map[x + 1 + b][y + a] == 1)) { // ��ͼ������
						return true;
					} else if ((shapes[blockType][turnState][a * 4 + b] == 1) && (map[x + 1 + b][y + a] == 2)) { // ��ǽ������
						return true;
					}

				}
			return false;
		}

		public void paintComponent(Graphics g) {// ��������
			super.paintComponent(g);// ���ø���ķ�������Ĭ�ϵĵ�ɫ�������
			for (int j = 0; j < 22; j++)
				for (int i = 0; i < 12; i++) {
					if (map[i][j] == 2) {
						g.drawRect(i * 10, j * 10, 10, 10);// ������(����)
					}
					if (map[i][j] == 1) {
						g.fillRect(i * 10, j * 10, 10, 10);// ������(ʵ��)
					}
				}
			for (int i = 0; i < 16; i++) {
				if (shapes[blockType][turnState][i] == 1) {
					g.fillRect((x + 1 + i % 4) * 10, (y + i / 4) * 10, 10, 10);// ������(ʵ��)
																				// *********
				}
			}
		}
	}

	public static void main(String[] args) {
		Tetris frame = new Tetris();
		JMenuBar menubar = new JMenuBar();
		frame.setJMenuBar(menubar);// ���ò˵���
		JMenu help = new JMenu("����");
		JMenuItem about = help.add("����"); // *
		JMenu game = new JMenu("��Ϸ");
		JMenuItem newgame = game.add("����Ϸ");
		JMenuItem pause = game.add("��ͣ");
		JMenuItem goon = game.add("����");
		JMenuItem exit = game.add("�˳�");
		menubar.add(help);
		menubar.add(game);

		frame.setTitle("����˹����");// ������Ŀ
		// frame.setLocationRelativeTo(null);// ���ο��κ����꣬ʹ���ڲ�ͣ�����Ͻ�
		frame.setSize(250, 300);
		frame.setLocation(450, 150);
		frame.setVisible(true);
		frame.setResizable(false);// ���ܵ������ڴ�С
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

}
