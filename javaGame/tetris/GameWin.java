package tetris;// 32fen

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.Timer;

public class GameWin extends JPanel implements ActionListener, KeyListener {
	JButton newGame = new JButton("��ʼ");
	JButton endGame = new JButton("�˳�");
	int score = 0, speed = 0, tScore = 0, temp = 0;
	GameAct[] act = new GameAct[4];
	GameAct[] actTemp = new GameAct[4];
	Random r = new Random();
	boolean start = false;
	int map[][] = new int[10][18];
	Timer t;
	JDialog dialog = new JDialog();
	JLabel label = new JLabel();
	JButton ok = new JButton("�ð�");

	public GameWin() {
		for (int i = 0; i < act.length; i++) {
			act[i] = new GameAct();
		}
		for (int i = 0; i < act.length; i++) {
			actTemp[i] = new GameAct();
		}
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 18; j++) {
				map[i][j] = 0;
			}
		}
		setLayout(new FlowLayout(FlowLayout.RIGHT));
		add(newGame);
		add(endGame);
		newGame.addActionListener(this);
		endGame.addActionListener(this);
		addKeyListener(this);
		dialog.setLayout(new GridLayout(2, 1));
		dialog.add(label);
		dialog.add(ok);
		ok.addActionListener(this);
		dialog.setSize(200, 100);
//		dialog.setLocationRelativeTo(null);
		dialog.setLocation(570, 250);
		dialog.setVisible(false);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawRect(9, 10, 200, 360);
		g.drawString("����" + score, 220, 60);
		g.drawLine(220, 65, 360, 65);
		g.drawString("�ٶ�" + speed, 220, 90);
		g.drawLine(220, 95, 360, 95);
		g.drawString("��һ������", 250, 120);
		g.drawString("����˹����", 220, 340);
		g.drawString("������", 220, 360);
		if (start) {
			g.setColor(new Color(255, 0, 0));// ��
			for (int i = 0; i < act.length; i++) {
				g.fillRect(10 + act[i].x * 20, 10 + act[i].y * 20, 20, 20);
			}
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 18; j++) {
					if (map[i][j] == 1) {
						g.fillRect(10 + i * 20, 10 + j * 20, 20, 20);
					}
				}
			}
			g.setColor(new Color(0, 0, 255));// ���ұ߻���һ������
			for (int i = 0; i < 4; i++) {
				g.fillRect(220 + actTemp[i].x * 20, 140 + actTemp[i].y * 20,
						20, 20);
			}
		}
	}

	public boolean newAct() {
		switch (temp) {
		case 0:
			act[0].x = 3;act[0].y = 0;// S
			act[1].x = 4;act[1].y = 0;
			act[2].x = 2;act[2].y = 1;
			act[3].x = 3;act[3].y = 1;
			break;
		case 1:
			act[0].x = 1;act[0].y = 0;// ----
			act[1].x = 2;act[1].y = 0;
			act[2].x = 3;act[2].y = 0;
			act[3].x = 4;act[3].y = 0;
			break;
		case 2:
			act[0].x = 2;act[0].y = 0;// O
			act[1].x = 3;act[1].y = 0;
			act[2].x = 2;act[2].y = 1;
			act[3].x = 3;act[3].y = 1;
			break;
		case 3:
			act[0].x = 2;act[0].y = 0;// __|
			act[1].x = 2;act[1].y = 1;
			act[2].x = 2;act[2].y = 2;
			act[3].x = 3;act[3].y = 2;
			break;
		case 4:
			act[0].x = 3;act[0].y = 0;// |__
			act[1].x = 3;act[1].y = 1;
			act[2].x = 3;act[2].y = 2;
			act[3].x = 2;act[3].y = 2;
			break;
		case 5:
			act[0].x = 3;act[0].y = 0;// _|_
			act[1].x = 2;act[1].y = 1;
			act[2].x = 3;act[2].y = 1;
			act[3].x = 4;act[3].y = 1;
			break;
		case 6:
			act[0].x = 2;act[0].y = 0;// Z
			act[1].x = 3;act[1].y = 0;
			act[2].x = 3;act[2].y = 1;
			act[3].x = 4;act[3].y = 1;
			break;
		}
		for (int i = 0; i < 4; i++) {
			if (maxYes(act[i].x, act[i].y)) {
				return false;
			}
		}
		return true;
	}

	private void nextAct() {
		switch (temp) {
		case 0:
			actTemp[0].x = 3;actTemp[0].y = 0;
			actTemp[1].x = 4;actTemp[1].y = 0;
			actTemp[2].x = 2;actTemp[2].y = 1;
			actTemp[3].x = 3;actTemp[3].y = 1;
			break;
		case 1:
			actTemp[0].x = 1;actTemp[0].y = 0;
			actTemp[1].x = 2;actTemp[1].y = 0;
			actTemp[2].x = 3;actTemp[2].y = 0;
			actTemp[3].x = 4;actTemp[3].y = 0;
			break;
		case 2:
			actTemp[0].x = 2;actTemp[0].y = 0;
			actTemp[1].x = 3;actTemp[1].y = 0;
			actTemp[2].x = 2;actTemp[2].y = 1;
			actTemp[3].x = 3;actTemp[3].y = 1;
			break;
		case 3:
			actTemp[0].x = 2;actTemp[0].y = 0;
			actTemp[1].x = 2;actTemp[1].y = 1;
			actTemp[2].x = 2;actTemp[2].y = 2;
			actTemp[3].x = 3;actTemp[3].y = 2;
			break;
		case 4:
			actTemp[0].x = 3;actTemp[0].y = 0;
			actTemp[1].x = 3;actTemp[1].y = 1;
			actTemp[2].x = 3;actTemp[2].y = 2;
			actTemp[3].x = 2;actTemp[3].y = 2;
			break;
		case 5:
			actTemp[0].x = 3;actTemp[0].y = 0;
			actTemp[1].x = 2;actTemp[1].y = 1;
			actTemp[2].x = 3;actTemp[2].y = 1;
			actTemp[3].x = 4;actTemp[3].y = 1;
			break;
		case 6:
			actTemp[0].x = 2;actTemp[0].y = 0;
			actTemp[1].x = 3;actTemp[1].y = 0;
			actTemp[2].x = 3;actTemp[2].y = 1;
			actTemp[3].x = 4;actTemp[3].y = 1;
			break;
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == newGame) {
			if (e.getActionCommand().equals("��ʼ")) {// *****
				newGame.setText("����");
				requestFocus(true); // *****
				start = true;
				temp = r.nextInt(7);
				if (!newAct()) {
					t = new Timer(1000 - (100 * speed), new myTimer());
					t.start();// ��ʼִ��ʱ�������������
					temp = r.nextInt(7);
					nextAct();
					repaint();
				} else {// ��Ϸʧ�ܵ����
					return;
				}
			} else {
				start = false;
				score = 0;
				tScore = 0;
				speed = 0;
				for (int i = 0; i < 10; i++) {
					for (int j = 0; j < 18; j++) {
						map[i][j] = 0;
					}
				}
				newGame.setText("��ʼ");
			}
		}
		if (e.getSource() == endGame) {
			System.exit(0);
		}
		if (e.getSource() == ok) {// �����ðɡ���������ٶȵ�����
			score = 0;
			tScore = 0;
			speed = 0;
			start = false;
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 18; j++) {
					map[i][j] = 0;
				}
			}
			newGame.setText("��ʼ");
			dialog.setVisible(false);
			repaint();
		}
	}

	public void move(int x, int y) {// һ��ͼ���ĸ���һ���ƶ�
		if (minYes(x, y)) {
			for (int i = 0; i < 4; i++) {
				act[i].x += x;
				act[i].y += y;
			}
		}
		repaint();
	}

	private int delete() {
		int line = 0;
		for (int i = 0; i < 18; i++) {
			int j;
			for (j = 0; j < 10; j++) {
				if (map[j][i] == 0) {
					break;
				}
			}
			if (j >= 10) {
				line += 1;
				if (i != 0) {
					for (int j2 = i - 1; j2 > 0; j2--) {// ��û��ȥ�Ĳ���������һ��
						for (int k = 0; k < 10; k++) {
							map[k][j2 + 1] = map[k][j2];
						}
					}
					for (int j2 = 0; j2 < 10; j2++) {
						map[0][j2] = 0;
					}
				}
			}
		}
		repaint();
		return line;
	}

	private void down() {
		if (minYes(0, 1)) {
			for (int i = 0; i < 4; i++) {
				act[i].y += 1;
			}
			repaint();
		} else {// ���������ʱ��
			t.stop();
			for (int i = 0; i < 4; i++) {
				map[act[i].x][act[i].y] = 1;
			}
			int line = delete();
			if (line != 0) {
				score = score + 10 * line;// ��������10��������������
				if (score - tScore >= 100) {// ÿ��100�֣��ٶȼ�һ
					tScore = score;
					if (speed <= 9) {
						speed += 1;
					}
				}
			}
			if (!newAct()) {// �ж����÷���������һ���µ�ͼ��
				temp = r.nextInt(7);
				nextAct();
				t.start();
			} else {// �ҵ���
				t.stop();
				label.setText("           ����ˣ���ķ�����" + score);
				dialog.setVisible(true);
			}
			repaint();
		}
	}

	public void up() {
		GameAct tt[] = new GameAct[4];
		for (int i = 0; i < tt.length; i++) {
			tt[i] = new GameAct();
			tt[i].x = act[i].x;
			tt[i].y = act[i].y;
		}
		int cx = (tt[0].x + tt[1].x + tt[2].x + tt[3].x) / 4;
		int cy = (tt[0].y + tt[1].y + tt[2].y + tt[3].y) / 4;
		for (int i = 0; i < tt.length; i++) {
			tt[i].x = cx + cy - act[i].y;
			tt[i].y = cy - cx + act[i].x;
		}
		for (int i = 0; i < tt.length; i++) {
			if (!maxYes(tt[i].x, tt[i].y)) {
				return;
			}
		}
		for (int i = 0; i < tt.length; i++) {
			act[i].x = tt[i].x;
			act[i].y = tt[i].y;
		}
		repaint();
	}

	public boolean minYes(int x, int y) {// �߽��ж�
		for (int i = 0; i < 4; i++) {
			if (!maxYes(act[i].x + x, act[i].y + y)) {
				return false;
			}
		}
		return true;
	}

	public boolean maxYes(int x, int y) {
		if (x < 0 || x >= 10 || y < 0 || y >= 18) {
			return false;
		}
		if (map[x][y] == 1) {
			return false;
		}
		return true;
	}

	public void keyPressed(KeyEvent e) {
		if (start) {
			switch (e.getKeyCode()) {// *****
			case KeyEvent.VK_DOWN:
				down();
				break;
			case KeyEvent.VK_UP:
				up();
				break;
			case KeyEvent.VK_LEFT:
				move(-1, 0);
				break;
			case KeyEvent.VK_RIGHT:
				move(1, 0);
				break;
			default:
				break;
			}
		}
	}

	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	public class myTimer implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (start) {
				down();
			}
		}
	}
}
