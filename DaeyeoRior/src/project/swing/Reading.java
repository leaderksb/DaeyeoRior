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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import project.db.DB;

public class Reading extends JFrame implements ActionListener {
	
	private JLabel title, dates;
	public static String name, id, address, reading = "null";
	private JTextArea txt;
	int img;
	private ImageIcon imgPik[] = {
			new ImageIcon(""), //�� ��
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
	private JLabel imgL;
	private JButton writingRetouchBtn, deleteBtn, messageBtn;
	private Font rollTitleFont, rollMainFont, rollAddFont, balBtnFont;
	private Color colorMint;

	public Reading(String setTitle, int width, int height) {
		
		JPanel panN = new JPanel();
		JPanel top = new JPanel();
		JPanel left = new JPanel();
		JPanel imgP = new JPanel();
		JPanel middle = new JPanel();		
		JPanel under = new JPanel();
		JPanel right = new JPanel();
		JPanel panC = new JPanel();
		JPanel panS = new JPanel();
		JPanel panA = new JPanel();
		
		colorMint = new Color(133, 197, 196);
		
		getContentPane().setBackground(Color.WHITE);
		panN.setBackground(Color.WHITE);
		panN.setBackground(Color.WHITE);
		top.setBackground(Color.WHITE);
		left.setBackground(Color.WHITE);
		imgP.setBackground(Color.WHITE);
		middle.setBackground(Color.WHITE);
		under.setBackground(Color.WHITE);
		right.setBackground(Color.WHITE);
		panC.setBackground(Color.WHITE);
		panS.setBackground(Color.WHITE);
		panA.setBackground(Color.WHITE);
		
		rollTitleFont = new Font("a������3", Font.PLAIN, 20);
		rollMainFont = new Font("a������2", Font.PLAIN, 18);
		rollAddFont = new Font("a������2", Font.PLAIN, 16);
		balBtnFont = new Font("a�߷�����", Font.PLAIN, 16);
		
		top.setLayout(new BorderLayout());
		middle.setLayout(new BorderLayout());
		panC.setLayout(new BorderLayout());
		panA.setLayout(new BorderLayout());
		left.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 150));
		right.setBorder(BorderFactory.createEmptyBorder(0, 150, 0, 0));
		panC.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panA.setBorder(BorderFactory.createEmptyBorder(30, 30, 15, 30));
		
		ResultSet rs = DB.select("select * from shares where address = '"
				+ Member.comm + "' union select * from exchange where address = '" + Member.comm + "'");
		//�÷��� ������ �÷��� ������Ÿ���� ���� �ΰ��� ���̺��� ���ļ� �ϳ��� ��� ����
		
		try {
			if(rs.next()) {
				if(rs.getString(1) != null) {
					title = new JLabel(rs.getString("title"), JLabel.CENTER);
					name = rs.getString("name");
					id = rs.getString("id");
					dates = new JLabel(rs.getString("dates"));
					address = rs.getString("address");
					img = rs.getInt("img");
					txt = new JTextArea(rs.getString("txt"));
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		JLabel titleL = new JLabel("���� : ");
		JLabel nameL = new JLabel("�ۼ��� : ");
		JLabel nameIdL = new JLabel(name + "(" + id + ")");
		JLabel datesL = new JLabel("�ۼ����� : ");
		JLabel addressL = new JLabel("���ּ� : " + address);
		imgL = new JLabel();
		
		writingRetouchBtn = new JButton("����");
		writingRetouchBtn.setForeground(colorMint);
		writingRetouchBtn.setFont(balBtnFont);
		writingRetouchBtn.setBorderPainted(false);
		//��ư �׵θ� ����
		writingRetouchBtn.setContentAreaFilled(false);
		//��ư ä��� ����
		writingRetouchBtn.setFocusPainted(false);
		//��ư ���ý� ����� �׵θ� ����

		deleteBtn = new JButton("����");
		deleteBtn.setForeground(colorMint);
		deleteBtn.setFont(balBtnFont);
		deleteBtn.setBorderPainted(false);
		//��ư �׵θ� ����
		deleteBtn.setContentAreaFilled(false);
		//��ư ä��� ����
		deleteBtn.setFocusPainted(false);
		//��ư ���ý� ����� �׵θ� ����
		
		messageBtn = new JButton("����");
		messageBtn.setForeground(colorMint);
		messageBtn.setFont(balBtnFont);
		messageBtn.setBorderPainted(false);
		//��ư �׵θ� ����
		messageBtn.setContentAreaFilled(false);
		//��ư ä��� ����
		messageBtn.setFocusPainted(false);
		//��ư ���ý� ����� �׵θ� ����
		
		writingRetouchBtn.addActionListener(this);
		deleteBtn.addActionListener(this);
		messageBtn.addActionListener(this);
		
		titleL.setFont(rollTitleFont);
		title.setFont(rollTitleFont);
		nameL.setFont(rollMainFont);
		nameIdL.setFont(rollMainFont);
		dates.setFont(rollMainFont);
		datesL.setFont(rollMainFont);
		addressL.setFont(rollAddFont);
		txt.setFont(rollMainFont);
		
		panN.add(titleL);
		panN.add(title);
		
		left.add(datesL);
		left.add(dates);
		
		under.add(addressL);
		
		if(Login.idTf.getText().equals(id)) {
			under.add(writingRetouchBtn);
			under.add(deleteBtn);
		}

		right.add(nameL);
		right.add(nameIdL);
		
		top.add(left, BorderLayout.WEST);
		top.add(right, BorderLayout.EAST);
		
		imgL.setIcon(imgPik[img]);
		//������ �ε��� ���� �̹����� ������
		
		imgP.add(imgL);
		txt.setLineWrap(true);
		//������ �����ٷ� ������
		txt.setEditable(false);
		//�ؽ�Ʈ �� �����Ұ�
		JScrollPane panScroll = new JScrollPane(txt, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//��ũ������ ����
		panScroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		//��ũ�ѹٸ� �����ͼ� ����� 0���� ����
		panScroll.setBorder(BorderFactory.createEmptyBorder());
		//��ũ������ �׵θ� ���ֱ�

		middle.add(imgP, BorderLayout.NORTH);
		middle.add(panScroll, BorderLayout.CENTER);
		
		panC.add(top, BorderLayout.NORTH);
		panC.add(middle, BorderLayout.CENTER);
		panC.add(under, BorderLayout.SOUTH);
		
		panA.add(panN, BorderLayout.NORTH);
		panA.add(panC, BorderLayout.CENTER);
		panA.add(messageBtn, BorderLayout.SOUTH);

		add(panA, BorderLayout.CENTER);
		
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
			//���� ȸ���� �ۼ��� ���̸�
			
			dispose();
			//���� â�� �ݰ�
			new writingRetouch("�ۼ���", 800, 800);
			//���� â�� ���
		}
		if(obj == messageBtn) {
			reading = "Reading"; 
			new MessageWriting("���� ������", 600, 600);
		} else if(obj == deleteBtn) {
			//���� ȸ���� �ۼ��� ���̸�
			
			int result = JOptionPane.showConfirmDialog(null, "���� ���� ���� �Ͻðڽ��ϱ�?", "���� ����",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			//���̾�α� ����â
			
			if(result == JOptionPane.OK_OPTION) {
				
				DB.update("delete from (select * from shares where address = '" + address + "')");
				DB.update("delete from (select * from exchange where address = '" + address + "')");
				//���� ���̺��� ���� �� �ּҰ��� �˻��Ͽ� ����
				
				JOptionPane.showMessageDialog(null, "���� ���� �Ǿ����ϴ�. \n ���ΰ�ħ �ϼ���", "���� �Ϸ�", 
						JOptionPane.INFORMATION_MESSAGE);
				//�ۻ��� �Ϸ� ����â ���
				
				dispose();
			}
		}
	}
}
