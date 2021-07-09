package project.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import project.db.DB;

/**
 * ȸ�� �������� �⺻ �̹������� ��������
 * �����ϴ� ��� ȸ�� �������� �ٲ��,
 * �ٲ� ȸ���� �̹��� �ε��� ���� DB�� �����
 * ����� �̹��� �ε��� ���� �ʱ� �α��� ��
 * �������� �̹��� ������ ������
 */

public class Profile extends JFrame implements ItemListener {
	
	private ImageIcon imgPik[] = {
			new ImageIcon("images/blackM1.png"), 
			new ImageIcon("images/blackM2.png"), 
			new ImageIcon("images/blackW1.png"), 
			new ImageIcon("images/blackW2.png"), 
			new ImageIcon("images/blackW3.png"), 
			new ImageIcon("images/blueW.png"), 
			new ImageIcon("images/brownM.png"), 
			new ImageIcon("images/greenW.png"), 
			new ImageIcon("images/pinkW.png"), 
			new ImageIcon("images/redM.png"), 
			new ImageIcon("images/yellowW1.png"), 
			new ImageIcon("images/yellowW2.png")	 };
	private JRadioButton blackM1, blackM2, blackW1, blackW2, blackW3, blueW, brownM, greenW, pinkW, redM, yellowW1, yellowW2;
	private JLabel imgL;
	private Font rollImgFont;
	
	public Profile(String setTitle, int width, int height) {
		
		imgL = new JLabel(new ImageIcon("images/basic2.png"), JLabel.CENTER);
		JPanel center = new JPanel();
		
		getContentPane().setBackground(Color.WHITE);
		center.setBackground(Color.WHITE);
		
		rollImgFont = new Font("a������2", Font.PLAIN, 14);

		ButtonGroup profileBg = new ButtonGroup();
		//��ư �׷����
		blackM1 = new JRadioButton("�ݰ� ����");
		blackM2 = new JRadioButton("���� ����");
		blackW1 = new JRadioButton("���� ����");
		blackW2 = new JRadioButton("��� ����");
		blackW3 = new JRadioButton("���� ����");
		blueW = new JRadioButton("���� ����");
		brownM = new JRadioButton("���� ����");
		greenW = new JRadioButton("�丮 ����");
		pinkW = new JRadioButton("���� ����");
		redM = new JRadioButton("��� ����");
		yellowW1 = new JRadioButton("���� ����");
		yellowW2 = new JRadioButton("ȣ�� ����");
		
		blackM1.setBackground(Color.WHITE);
		blackM2.setBackground(Color.WHITE);
		blackW1.setBackground(Color.WHITE);
		blackW2.setBackground(Color.WHITE);
		blackW3.setBackground(Color.WHITE);
		blueW.setBackground(Color.WHITE);
		brownM.setBackground(Color.WHITE);
		greenW.setBackground(Color.WHITE);
		pinkW.setBackground(Color.WHITE);
		redM.setBackground(Color.WHITE);
		yellowW1.setBackground(Color.WHITE);
		yellowW2.setBackground(Color.WHITE);
		
		blackM1.setFont(rollImgFont);
		blackM2.setFont(rollImgFont);
		blackW1.setFont(rollImgFont);
		blackW2.setFont(rollImgFont);
		blackW3.setFont(rollImgFont);
		blueW.setFont(rollImgFont);
		brownM.setFont(rollImgFont);
		greenW.setFont(rollImgFont);
		pinkW.setFont(rollImgFont);
		redM.setFont(rollImgFont);
		yellowW1.setFont(rollImgFont);
		yellowW2.setFont(rollImgFont);
		
		blackM1.addItemListener(this);
		blackM2.addItemListener(this);
		blackW1.addItemListener(this);
		blackW2.addItemListener(this);
		blackW3.addItemListener(this);
		blueW.addItemListener(this);
		brownM.addItemListener(this);
		greenW.addItemListener(this);
		pinkW.addItemListener(this);
		redM.addItemListener(this);
		yellowW1.addItemListener(this);
		yellowW2.addItemListener(this);
		
		profileBg.add(blackM1);
		profileBg.add(blackM2);
		profileBg.add(blackW1);
		profileBg.add(blackW2);
		profileBg.add(blackW3);
		profileBg.add(blueW);
		profileBg.add(brownM);
		profileBg.add(greenW);
		profileBg.add(pinkW);
		profileBg.add(redM);
		profileBg.add(yellowW1);
		profileBg.add(yellowW2);
		
		center.add(blackM1);
		center.add(blackM2);
		center.add(blackW1);
		center.add(blackW2);
		center.add(blackW3);
		center.add(blueW);
		center.add(brownM);
		center.add(greenW);
		center.add(pinkW);
		center.add(redM);
		center.add(yellowW1);
		center.add(yellowW2);
		
		add(center, BorderLayout.CENTER);
		add(imgL, BorderLayout.SOUTH);
		
		setTitle(setTitle);
		//���� ����
		setSize(width, height);
		//ũ�� ����
		setResizable(false);
		//���Ƿ� ũ������ �Ұ�
		setLocationRelativeTo(this);
		//�߾� ��ġ
		setVisible(true);
		//â ����
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(blackM1.isSelected()) {
			imgL.setIcon(imgPik[0]);
			Member.profileBtn.setIcon(imgPik[0]);
			//���� ���õ� �̹����� ����
			DB.update("update information "
					+ "set img = 0 where id = '" + Login.idTf.getText() + "'");
			//���� ���õ� �̹��� �ε��� �� DB�� ����
		} else if(blackM2.isSelected()) {
			imgL.setIcon(imgPik[1]);
			Member.profileBtn.setIcon(imgPik[1]);
			//���� ���õ� �̹����� ����
			DB.update("update information "
					+ "set img = 1 where id = '" + Login.idTf.getText() + "'");
			//���� ���õ� �̹��� �ε��� �� DB�� ����
		} else if(blackW1.isSelected()) {
			imgL.setIcon(imgPik[2]);
			Member.profileBtn.setIcon(imgPik[2]);
			//���� ���õ� �̹����� ����
			DB.update("update information "
					+ "set img = 2 where id = '" + Login.idTf.getText() + "'");
			//���� ���õ� �̹��� �ε��� �� DB�� ����
		} else if(blackW2.isSelected()) {
			imgL.setIcon(imgPik[3]);
			Member.profileBtn.setIcon(imgPik[3]);
			//���� ���õ� �̹����� ����
			DB.update("update information "
					+ "set img = 3 where id = '" + Login.idTf.getText() + "'");
			//���� ���õ� �̹��� �ε��� �� DB�� ����
		} else if(blackW3.isSelected()) {
			imgL.setIcon(imgPik[4]);
			Member.profileBtn.setIcon(imgPik[4]);
			//���� ���õ� �̹����� ����
			DB.update("update information "
					+ "set img = 4 where id = '" + Login.idTf.getText() + "'");
			//���� ���õ� �̹��� �ε��� �� DB�� ����
		} else if(blueW.isSelected()) {
			imgL.setIcon(imgPik[5]);
			Member.profileBtn.setIcon(imgPik[5]);
			//���� ���õ� �̹����� ����
			DB.update("update information "
					+ "set img = 5 where id = '" + Login.idTf.getText() + "'");
			//���� ���õ� �̹��� �ε��� �� DB�� ����
		} else if(brownM.isSelected()) {
			imgL.setIcon(imgPik[6]);
			Member.profileBtn.setIcon(imgPik[6]);
			//���� ���õ� �̹����� ����
			DB.update("update information "
					+ "set img = 6 where id = '" + Login.idTf.getText() + "'");
			//���� ���õ� �̹��� �ε��� �� DB�� ����
		} else if(greenW.isSelected()) {
			imgL.setIcon(imgPik[7]);
			Member.profileBtn.setIcon(imgPik[7]);
			//���� ���õ� �̹����� ����
			DB.update("update information "
					+ "set img = 7 where id = '" + Login.idTf.getText() + "'");
			//���� ���õ� �̹��� �ε��� �� DB�� ����
		} else if(pinkW.isSelected()) {
			imgL.setIcon(imgPik[8]);
			Member.profileBtn.setIcon(imgPik[8]);
			//���� ���õ� �̹����� ����
			DB.update("update information "
					+ "set img = 8 where id = '" + Login.idTf.getText() + "'");
			//���� ���õ� �̹��� �ε��� �� DB�� ����
		} else if(redM.isSelected()) {
			imgL.setIcon(imgPik[9]);
			Member.profileBtn.setIcon(imgPik[9]);
			//���� ���õ� �̹����� ����
			DB.update("update information "
					+ "set img = 9 where id = '" + Login.idTf.getText() + "'");
			//���� ���õ� �̹��� �ε��� �� DB�� ����
		} else if(yellowW1.isSelected()) {
			imgL.setIcon(imgPik[10]);
			Member.profileBtn.setIcon(imgPik[10]);
			//���� ���õ� �̹����� ����
			DB.update("update information "
					+ "set img = 10 where id = '" + Login.idTf.getText() + "'");
			//���� ���õ� �̹��� �ε��� �� DB�� ����
		} else if(yellowW2.isSelected()) {
			imgL.setIcon(imgPik[11]);
			Member.profileBtn.setIcon(imgPik[11]);
			//���� ���õ� �̹����� ����
			DB.update("update information "
					+ "set img = 11 where id = '" + Login.idTf.getText() + "'");
			//���� ���õ� �̹��� �ε��� �� DB�� ����
		}
		
	}
}
