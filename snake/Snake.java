package snake;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Snake {
	JFrame frame;
	MyJpanel mjp;
	private static final int Boundary = 330;

	public Snake() {
		frame = new JFrame("�� �� �� ̰ �� ��");
		mjp = new MyJpanel();
		mjp.setFocusable(true); // ������役���������
		frame.getContentPane().add(mjp, BorderLayout.CENTER);
		frame.setSize(Boundary - 22, Boundary + 120);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null); // ���ھ���
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �ر��˳�
	}
}

/*----------------------------------------------------------------------------------*/

class MyJpanel extends JPanel implements ActionListener {
	Timer time;
	ArrayList<Point> list, listBody;
	private static final int Rect = 10; // �ߵĴ�С
	private static final int Boundary = 300; // ���Ĵ�С

	private static int x = 10; // ��ͷx��y����
	private static int y = 10;

	private static int dx = 10; // �ƶ�����
	private static int dy = 0;

	private static int fx = (int) (Math.random() * 10) * 30; // ʳ������
	private static int fy = (int) (Math.random() * 10) * 30;
	private boolean flagzy = false;
	private boolean flagsx = true;
	// �߳�ʼΪ�����ƶ�������flagzy==false
	// ���flagzy==false�����ܰ����Ҽ�
	// ���flagsx==true�����ܰ����¼�
	private boolean flagzt = true; // ��ͣ����
	private boolean flagwd = true; // �޵�ģʽ����
	private boolean flagss = true; // ����ģʽ����
	private String str1, str2, str3, str3_1 = "            - ����ģʽ -", str4_1 = "            - ����״̬ -", str4, str5, str6;
	private int snakebody, Snakebody;

	// private int speed=100;
	public MyJpanel() { // ��幹�췽��
		time = new Timer(100, this); // ������ʱ��
		time.start();
		list = new ArrayList<Point>();
		listBody = new ArrayList<Point>();
		list.add(new Point(x, y));
		listBody.add(list.get(0));
		snakebody = 1;
		Snakebody = 0;
		addKeyListener(new KeyAdapter() { // ���ü��̼���
			public void keyPressed(KeyEvent e) {
				// �����ͣ�˾Ͳ��ܸı䷽��
				if (flagzt) {
					// ------------------------------������ơ�-----------------------------------------
					if (flagsx) {
						if (e.getKeyCode() == KeyEvent.VK_DOWN) { // ��������ת
							dx = 0;
							dy = 10;
							flagsx = false;
							flagzy = true;
						}
						if (e.getKeyCode() == KeyEvent.VK_UP) { // ��������ת
							dx = 0;
							dy = -10;
							flagsx = false;
							flagzy = true;
						}
					}
					if (flagzy) {
						if (e.getKeyCode() == 37) { // ��������ת
							dx = -10;
							dy = 0;
							flagzy = false;
							flagsx = true;
						}
						if (e.getKeyCode() == 39) { // ��������ת
							dx = 10;
							dy = 0;
							flagzy = false;
							flagsx = true;
						}
					}
				}
				// ------------------------------������ơ�-----------------------------------------
				if (e.getKeyCode() == 80) { // P��ͣ
					if (flagzt) {
						time.stop();
						flagzt = false;
					} else {
						time.start();
						flagzt = true;
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_W) { // W�޵�
					if (flagwd) {
						flagwd = false;
						str3_1 = "            - �޵�ģʽ -";
					} else {
						flagwd = true;
						str3_1 = "            - ����ģʽ -";
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_S) {
					if (flagss) {
						flagss = false;
						str4_1 = "            - ����״̬-";
					} else {
						flagss = true;
						str4_1 = "            - ����״̬ -";
					}
				}
			}
		});
	}

	// -------------------------------���췽������-------------------------------
	public void paint(Graphics g) {
		// ���ñ���Ϊ��ɫ
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, Boundary, Boundary);
		// ���ñ߿�Ϊ��ɫ
		g.setColor(Color.RED);
		g.drawLine(0, 0, 0, Boundary);
		g.drawLine(0, 0, Boundary, 0);
		g.drawLine(Boundary, 0, Boundary, Boundary);
		g.drawLine(Boundary, Boundary, 0, Boundary);
		// ��������Ϊ���ɫ
		if (flagss)
			g.setColor(Color.MAGENTA);
		else
			g.setColor(new Color((int) (Math.random() * 255) + 1, (int) (Math.random() * 255) + 1,
					(int) (Math.random() * 255) + 1));
		g.fillRect(x, y, Rect, Rect);
		for (int i = 0; i < listBody.size(); i++) {
			g.fillRect(listBody.get(i).x, listBody.get(i).y, 9, 9);
		}
		// �������ʳ��
		g.setColor(Color.YELLOW);
		g.fillRect(fx, fy, Rect, Rect);

		str1 = "����˵������ �� �� �� ������С�߷���P����ͣ��Ϸ��";
		str2 = "                     W�������޵�ģʽ��S����������״̬������";
		// str2=" ��������һ�κ��� f(x,y)=x2+2x+3 ���ӣ�";
		str3 = "��ǰģʽ��" + str3_1;
		str4 = "��ǰ״̬��" + str4_1;
		str5 = "��ǰ�ȼ���            - " + ((snakebody / 10) + 1) + " �� -";
		str6 = "��ǰ�÷֣�            - " + Snakebody + " �� -";

		g.setColor(Color.pink);
		g.fillRect(0, Boundary + 1, Boundary, 120);
		g.setColor(Color.BLUE);
		g.drawString(str1, 5, Boundary + 20);
		g.drawString(str2, 5, Boundary + 40);
		g.drawLine(0, 344, Boundary, 344);
		g.setColor(Color.BLUE);
		g.setFont(new Font("����", Font.PLAIN, 16));
		g.drawString(str3, 5, Boundary + 60);
		g.drawString(str4, 5, Boundary + 80);
		g.drawString(str5, 5, Boundary + 100);
		g.drawString(str6, 5, Boundary + 120);

	}

	public void actionPerformed(ActionEvent e) {
		x += dx;
		y += dy;
		// �Ե�ʳ��
		if (x == fx && y == fy) {
			// �������ʳ��
			fx = (int) (Math.random() * 10) * 30;
			fy = (int) (Math.random() * 10) * 30;
			snakebody++;
			// һ�κ���
			Snakebody = snakebody * snakebody + 2 * snakebody + 3;

			// ���õȼ�
			/*
			 * if(snakebody==5){ speed=95; //time=new Timer(speed,this);
			 * 
			 * } if(snakebody==10){ speed=90; // time=new Timer(speed,this);
			 * 
			 * } if(snakebody==15){ speed=85; // time=new Timer(speed,this);
			 * 
			 * } time.start();
			 */
		}
		if (flagwd) { // ����ģʽ����
			// ײ���߽�����
			if (x < 0 || y < 0 || x > Boundary - Rect || y > Boundary - Rect) {
				time.stop();
				JOptionPane.showMessageDialog(null,
						"            " + "�� �֣�" + Snakebody + " �� !\n            �� �� �� ʼ �� �� ");
				x = 10;
				y = 10;

				dx = 10;
				dy = 0;

				snakebody = 1;
				Snakebody = 0;
				list.clear();
				listBody.clear();
				list.add(new Point(x, y));
				listBody.add(list.get(0));

				flagzy = false;
				flagsx = true;
				time.start();
			}
			// ײ��������
			for (int i = 0; i < listBody.size() - 1; i++) {
				for (int j = i + 1; j < listBody.size(); j++) {
					if (listBody.get(i).equals(listBody.get(j))) {
						time.stop();
						JOptionPane.showMessageDialog(null,
								"            " + "�� �֣�" + Snakebody + " �� !\n            �� �� �� ʼ �� �� ");
						x = 10;
						y = 10;

						dx = 10;
						dy = 0;

						snakebody = 1;
						Snakebody = 0;
						list.clear();
						listBody.clear();
						list.add(new Point(x, y));
						listBody.add(list.get(0));

						flagzy = false;
						flagsx = true;
						time.start();
					}
				}
			}
		} else { // �޵�ģʽ
			if (x > Boundary)
				x = 0;
			if (y > Boundary)
				y = 0;
			if (x < 0)
				x = Boundary;
			if (y < 0)
				y = Boundary;
		}
		addPoint(x, y);
		repaint();
	}

	public void addPoint(int xx, int yy) {
		// ��̬�ļ�¼���·������ƶ���������
		// ���������µ���
		if (list.size() < 100) {// �������Ϊ100
			list.add(new Point(xx, yy));
		} else {
			list.remove(0);
			list.add(new Point(xx, yy));
		}
		if (snakebody == 1) {
			listBody.remove(0);
			listBody.add(0, list.get(list.size() - 1));
		} else {
			listBody.clear();
			if (list.size() < snakebody) {
				for (int i = list.size() - 1; i > 0; i--)
					listBody.add(list.get(i));
			} else {
				for (int i = list.size() - 1; listBody.size() < snakebody; i--)
					listBody.add(list.get(i));
			}
		}
	}

	public static void main(String[] args) {
		new Snake();
	}
}
