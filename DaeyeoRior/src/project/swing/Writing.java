package project.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

public class Writing extends JFrame implements ActionListener {
	
	private String boardPik[] = {"�����Խ���", "��ȯ�Խ���"};
	private JComboBox<String> boardCb;
	private String imgStr[] = { "�̹��� ����", "������ǰ", "ȭ��� ��ǰ", "ħ��", "å��", 
			"������", "����", "����", "Ŀư", "å��", "���", "�ſ�", "��ǰ", "����", "��Ź"};
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
	//�̹��� ���� ���� 350���� ����
	private JComboBox<String> imgCb;
	private JLabel imgL;
	private JTextField writingTf;
	private JTextArea writingTa;
	private JButton okBtn, cancleBtn;
	private JScrollPane panScroll;
	private String name;
	private Font rollTitleFont, rollMainFont, balBtnFont;
	private Color colorMint;
	public static int refresh;
	//���ΰ�ħ ���θ� �˷��� ���� ����

	public Writing(String setTitle, int width, int height) {
		
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
		
		boardCb = new JComboBox<String>(boardPik);
		boardCb.addActionListener(this);
		
		imgCb = new JComboBox<String>(imgStr);
		imgCb.addActionListener(this);
		
		JLabel title = new JLabel("���� : ");
		
		title.setFont(rollTitleFont);
		
		imgL = new JLabel(imgPik[0]);
		
		writingTf = new JTextField(30);
		writingTa = new JTextArea();
		
		writingTf.setFont(rollTitleFont);
		writingTa.setFont(rollMainFont);
		writingTa.setLineWrap(true);
		//������ �����ٷ� ������
		
		okBtn = new JButton("���");
		okBtn.setForeground(colorMint);
		okBtn.setFont(balBtnFont);
		okBtn.setBorderPainted(false);
		//��ư �׵θ� ����
		okBtn.setContentAreaFilled(false);
		//��ư ä��� ����
		okBtn.setFocusPainted(false);
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
		
		okBtn.addActionListener(this);
		cancleBtn.addActionListener(this);
		
		panScroll = new JScrollPane(writingTa, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
		//��ũ�������� ����
		panScroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		//��ũ�ѹٸ� �����ͼ� ����� 0���� ����
		
		center1.add(boardCb);
		center1.add(imgCb);
		center1.add(title);
		center1.add(writingTf);
		
		center2.add(imgL);

		panC.add(center1, BorderLayout.NORTH);
		panC.add(panScroll, BorderLayout.CENTER);
		panC.add(center2, BorderLayout.SOUTH);
		panS.add(okBtn);
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
		
		if(obj == okBtn) {
			String boardName = (String) boardCb.getSelectedItem();
			//���õ� �Խ��� ���� ������
			
			if(writingTf.getText().length() > 0 && writingTa.getText().length() > 0) {
				//����� ���뿡 �Էµ� ���� �ִٸ�
				
				if(boardName.equals("�����Խ���")) {
					//�����Խ����� ���õƴٸ�
					
					ResultSet rs = DB.select("select name from information where id ='" 
					+ Login.idTf.getText() + "'");
					
					try {
						if(rs.next()) {
							name = rs.getString(1);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					Date date = new Date();
					//����ð�
					
					SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");
					//��-��-�Ϸ� ���ĺ�ȯ
					
					String time = dayFormat.format(date);
					//�ۼ��ð�
					
					SimpleDateFormat urlFormat = new SimpleDateFormat("yyMMddHHmmss");
					
					String url = Login.idTf.getText() + urlFormat.format(date);
					
					DB.update("insert into shares values ('" + writingTf.getText() + "', '" 
							+ name + "', '" + Login.idTf.getText() + "', '" + time + "', '" + url
							+ "', '" + imgCb.getSelectedIndex() + "', '" + writingTa.getText() + "')");
					//�������� DB�� ����
					
					JOptionPane.showMessageDialog(null, "���� ��� �Ǿ����ϴ�. \n ���ΰ�ħ �ϼ���.", "�۾��� �Ϸ�", 
							JOptionPane.INFORMATION_MESSAGE);
					//�۾��� �Ϸ� ����â ���
					
					dispose();
				} else if(boardName.equals("��ȯ�Խ���")) {
					//��ȯ�Խ����� ���õƴٸ�
					
					ResultSet rs = DB.select("select name from information where id ='" 
					+ Login.idTf.getText() + "'");
					
					try {
						if(rs.next()) {
							name = rs.getString(1);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					Date date = new Date();
					//����ð�
					
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					//��-��-�Ϸ� ���ĺ�ȯ
					
					String time = format.format(date);
					//�ۼ��ð�
					
					SimpleDateFormat urlFormat = new SimpleDateFormat("yyMMddHHmmss");
					
					String url = Login.idTf.getText() + urlFormat.format(date);
					
					DB.update("insert into exchange values ('" + writingTf.getText() + "', '" 
							+ name + "', '" + Login.idTf.getText() + "', '" + time + "', '" + url 
							+ "', '" + imgCb.getSelectedIndex() + "', '" + writingTa.getText() + "')");
					//�������� DB�� ����
					
					JOptionPane.showMessageDialog(null, "���� ��� �Ǿ����ϴ�. \n ���ΰ�ħ �ϼ���.", "�۾��� �Ϸ�", 
							JOptionPane.INFORMATION_MESSAGE);
					//�۾��� �Ϸ� ����â ���
					
					dispose();
				}
			}
		} else if(obj == imgCb) {
			int index = imgCb.getSelectedIndex();
			//�޺��ڽ����� ������ �ε��� ���� ������
			imgL.setIcon(imgPik[index]);
			
			//������ �ε��� ���� �̹����� ������
		} else if(obj == cancleBtn) {
			dispose();
			//���� â�� ����
		}
	}
}
