package project.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import project.db.DB;

public class writingRetouch extends JFrame implements ActionListener {
	
	private String imgStr[] = { "�̹��� ����", "������ǰ", "ȭ��� ��ǰ", "ħ��", "å��", 
			"������", "����", "����", "Ŀư", "å��", "���", "�ſ�", "��ǰ", "����", "��Ź"};
	public static String name, id, address, reading = "null";
//	int img;
	private ImageIcon imgPik[] = {
			new ImageIcon("images/basic1.jpg"), 
			new ImageIcon("images/appliances.jpg"), 
			new ImageIcon("images/bathroom.jpg"), 
			new ImageIcon("images/bed.jpg"), 
			new ImageIcon("images/bookshelf.jpg"), 
			new ImageIcon("images/cabinet.jpg"), 
			new ImageIcon("images/chair.jpg"), 
			new ImageIcon("images/closet.jpg"), 
			new ImageIcon("images/curtain.jpg"), 
			new ImageIcon("images/desk.jpg"), 
			new ImageIcon("images/hanger.jpg"), 
			new ImageIcon("images/mirror.jpg"), 
			new ImageIcon("images/prop.jpg"), 
			new ImageIcon("images/sofa.jpg"), 
			new ImageIcon("images/table.jpg") };
	private JComboBox<String> imgCb;
	private JLabel imgL;
	private JTextField writingTf;
	private JTextArea writingTa;
	private JButton writingRetouchBtn, cancleBtn;
	private JScrollPane panScroll;
	private Font rollTitleFont, rollMainFont, balBtnFont;
	private Color colorMint;

	public writingRetouch(String setTitle, int width, int height) {
		
		JPanel center1 = new JPanel();
		JPanel center2 = new JPanel();
		JPanel panC = new JPanel();
		JPanel panS = new JPanel();
		
		colorMint = new Color(133, 197, 196);
		
		center1.setBackground(Color.WHITE);
		center2.setBackground(Color.WHITE);
		panC.setBackground(Color.WHITE);
		panS.setBackground(Color.WHITE);
		
		rollTitleFont = new Font("a������3", Font.PLAIN, 20);
		rollMainFont = new Font("a������2", Font.PLAIN, 18);
		balBtnFont = new Font("a�߷�����", Font.PLAIN, 16);
		
		panC.setLayout(new BorderLayout());
		center1.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
		panC.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));
		panS.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		
		imgCb = new JComboBox<String>(imgStr);
		imgCb.addActionListener(this);
		
		ResultSet rs = DB.select("select * from shares where address = '"
				+ Member.comm + "' union select * from exchange where address = '" + Member.comm + "'");
		//�÷��� ������ �÷��� ������Ÿ���� ���� �ΰ��� ���̺��� ���ļ� �ϳ��� ��� ����
		
		try {
			if(rs.next()) {
				if(rs.getString(1) != null) {
					writingTf = new JTextField(rs.getString("title"));
//					img = rs.getInt("img");
					writingTa = new JTextArea(rs.getString("txt"));
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		JLabel title = new JLabel("���� : ");
		
		title.setFont(rollTitleFont);
		
		imgL = new JLabel(imgPik[0]);
		
		writingTf.setFont(rollTitleFont);
		writingTa.setFont(rollMainFont);
		writingTa.setLineWrap(true);
		//������ �����ٷ� ������
		
		writingRetouchBtn  = new JButton("����");
		writingRetouchBtn.setForeground(colorMint);
		writingRetouchBtn.setFont(balBtnFont);
		writingRetouchBtn.setBorderPainted(false);
		//��ư �׵θ� ����
		writingRetouchBtn.setContentAreaFilled(false);
		//��ư ä��� ����
		writingRetouchBtn.setFocusPainted(false);
		//��ư ���ý� ����� �׵θ� ����
		
		cancleBtn = new JButton("���");
		cancleBtn.setForeground(colorMint);
		cancleBtn.setFont(balBtnFont);
		cancleBtn.setBorderPainted(false);
		//��ư �׵θ� ����
		cancleBtn.setContentAreaFilled(false);
		//��ư ä��� ����
		cancleBtn.setFocusPainted(false);
		//��ư ���ý� ����� �׵θ� ����
		
		writingRetouchBtn.addActionListener(this);
		cancleBtn.addActionListener(this);
		
		panScroll = new JScrollPane(writingTa, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
		//��ũ�������� ����
		panScroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		//��ũ�ѹٸ� �����ͼ� ����� 0���� ����
		
		center1.add(imgCb);
		center1.add(title);
		center1.add(writingTf);
		
		center2.add(imgL);

		panC.add(center1, BorderLayout.NORTH);
		panC.add(panScroll, BorderLayout.CENTER);
		panC.add(center2, BorderLayout.SOUTH);
		panS.add(writingRetouchBtn);
		panS.add(cancleBtn);
		
		add(panC, BorderLayout.CENTER);
		add(panS, BorderLayout.SOUTH);
		
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
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == writingRetouchBtn) {
			//���� �Ϸ� ��ư�� ������
			
			if(writingTf.getText().length() > 0 && writingTa.getText().length() > 0) {
				//����� ���뿡 �Էµ� ���� �ִٸ�
			
				DB.update("update shares set title = '" + writingTf.getText() + "', img = '" 
						+ imgCb.getSelectedIndex() + "', txt = '" + writingTa.getText() +"'where address = '" + Member.comm + "'");
				DB.update("update exchange set title = '" + writingTf.getText() + "', img = '" 
						+ imgCb.getSelectedIndex() + "', txt = '" + writingTa.getText() +"' where address = '" + Member.comm + "'");
				//���� ���̺��� ���� �� �ּҰ��� �˻��Ͽ� ������Ʈ
					
				JOptionPane.showMessageDialog(null, "���� ���� �Ǿ����ϴ�. \n ���ΰ�ħ �ϼ���.", "�ۼ��� �Ϸ�", 
						JOptionPane.INFORMATION_MESSAGE);
				//�ۼ��� �Ϸ� ����â ���
				
				dispose();
				
				new Reading("�ۿ���", 1200, 800);
				//�ۿ��� â�� ���
			}
		} else if(obj == imgCb) {
			int index = imgCb.getSelectedIndex();
			//�޺��ڽ����� ������ �ε��� ���� ������
			imgL.setIcon(imgPik[index]);
			//������ �ε��� ���� �̹����� ������
		} else if(obj == cancleBtn) {
			dispose();
			//���� â�� ����
			
			new Reading("�ۿ���", 1200, 800);
			//�ۿ��� â�� ����
		}
	}
}
