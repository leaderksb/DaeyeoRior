package project.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import project.db.DB;

public class MessageReading extends JFrame implements ActionListener {

	private JButton replyBtn, cancleBtn, deleteBtn;
	private JScrollPane panScroll;
	public static String name, sendid, messageReading = "null";
	//�������� �̸��� ���̵� �� ����
	private JLabel title, dates;
	private String address;
	private JTextArea txt;
	private Font rollTitleFont, rollMainFont, rollAddFont, balBtnFont;
	private Color colorMint;
	
	public MessageReading(String setTitle, int width, int height) {
		JPanel panN = new JPanel();
		JPanel top = new JPanel();
		JPanel left = new JPanel();
		JPanel under = new JPanel();
		JPanel right = new JPanel();
		JPanel panC = new JPanel();
		JPanel panA = new JPanel();
		
		colorMint = new Color(133, 197, 196);
		
		getContentPane().setBackground(Color.WHITE);
		panN.setBackground(Color.WHITE);
		top.setBackground(Color.WHITE);
		left.setBackground(Color.WHITE);
		under.setBackground(Color.WHITE);
		right.setBackground(Color.WHITE);
		panC.setBackground(Color.WHITE);
		panA.setBackground(Color.WHITE);
		
		rollTitleFont = new Font("a������3", Font.PLAIN, 20);
		rollMainFont = new Font("a������2", Font.PLAIN, 18);
		rollAddFont = new Font("a������2", Font.PLAIN, 16);
		balBtnFont = new Font("a�߷�����", Font.PLAIN, 16);
		
		top.setLayout(new BorderLayout());
		panC.setLayout(new BorderLayout());
		panA.setLayout(new BorderLayout());
		left.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		right.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		panC.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panA.setBorder(BorderFactory.createEmptyBorder(30, 30, 15, 30));
		
		ResultSet rs = DB.select("select * from message where address = '" + MessageBox.comm + "' order by dates desc");
		//�÷��� ������ �÷��� ������Ÿ���� ���� �ΰ��� ���̺��� ���ļ� �ϳ��� ��� ����
		//�����ð��� ������������ �˻�
		
		try {
			if(rs.next()) {
				if(rs.getString(1) != null) {
					title = new JLabel(rs.getString("title"), JLabel.CENTER);
					name = rs.getString("sendname");
					sendid = rs.getString("sendid");
					dates = new JLabel(rs.getString("dates"));
					address = rs.getString("address");
					txt = new JTextArea(rs.getString("txt"));
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		JLabel titleL = new JLabel("���� : ");
		JLabel nameL = new JLabel("������ : ");
		JLabel nameIdL = new JLabel(name + "(" + sendid + ")");
		JLabel datesL = new JLabel("�����ð� : ");
		JLabel addressL = new JLabel("�����ּ� : " + address);
		
		replyBtn = new JButton("����");
		replyBtn.setForeground(colorMint);
		replyBtn.setFont(balBtnFont);
		replyBtn.setBorderPainted(false);
		//��ư �׵θ� ����
		replyBtn.setContentAreaFilled(false);
		//��ư ä��� ����
		replyBtn.setFocusPainted(false);
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
		
		replyBtn.addActionListener(this);
		deleteBtn.addActionListener(this);
		
		titleL.setFont(rollTitleFont);
		title.setFont(rollTitleFont);
		nameL.setFont(rollMainFont);
		nameIdL.setFont(rollMainFont);
		dates.setFont(rollMainFont);
		datesL.setFont(rollMainFont);
		addressL.setFont(rollAddFont);
		txt.setFont(rollMainFont);
		
		txt.setLineWrap(true);
		//������ �����ٷ� ������
		txt.setEditable(false);
		//�ؽ�Ʈ �� �����Ұ�
		panScroll = new JScrollPane(txt, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//��ũ������ ����
		panScroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		//��ũ�ѹٸ� �����ͼ� ����� 0���� ����
		panScroll.setBorder(BorderFactory.createEmptyBorder());
		//��ũ������ �׵θ� ���ֱ�

		panN.add(titleL);
		panN.add(title);
		
		left.add(datesL);
		left.add(dates);
		
		under.add(addressL);
		under.add(deleteBtn);

		right.add(nameL);
		right.add(nameIdL);
		
		top.add(left, BorderLayout.WEST);
		top.add(right, BorderLayout.EAST);
		
		panC.add(top, BorderLayout.NORTH);
		panC.add(panScroll, BorderLayout.CENTER);
		panC.add(under, BorderLayout.SOUTH);
		
		panA.add(panN, BorderLayout.NORTH);
		panA.add(panC, BorderLayout.CENTER);
		panA.add(replyBtn, BorderLayout.SOUTH);

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
		
		if(obj == replyBtn) {
			messageReading = "MessageReading";
			new MessageWriting("���� ������", 600, 600);
		} else if(obj == deleteBtn) {
			//���� ȸ���� �ۼ��� ���̸�
			
			int result = JOptionPane.showConfirmDialog(null, "���� ������ ���� �Ͻðڽ��ϱ�?", "���� ����",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			//���̾�α� ����â
			
			if(result == JOptionPane.OK_OPTION) {
				
				DB.update("delete from (select * from message where address = '" + address + "')");
				//�޽��� ���̺��� ���� ���� �ּҰ��� �˻��Ͽ� ����
				
				JOptionPane.showMessageDialog(null, "������ ���� �Ǿ����ϴ�.", "���� �Ϸ�", 
						JOptionPane.INFORMATION_MESSAGE);
				//���� ���� �Ϸ� ����â ���
				dispose();
			}
		} else if(obj == cancleBtn) {
			dispose();
			//���� â�� ����
		}
	}
}
