package project.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import project.db.DB;

public class MessageBox extends JFrame implements ActionListener {

	private JPanel center, panC;
	private JButton address, refreshBtn;
	private JScrollPane panScroll;
	private Font rollTitleFont, rollMainFont, rollAddFont, balBtnFont;
	int n;
	public static Object comm;
	
	public MessageBox(String setTitle, int width, int height) {
		
		center = new JPanel();
		panC = new JPanel();
		
		Color colorMint = new Color(133, 197, 196);
		
		((JComponent) getContentPane()).setBackground(colorMint);
		center.setBackground(Color.WHITE);
		panC.setBackground(Color.WHITE);
		
		rollTitleFont = new Font("a������3", Font.PLAIN, 18);
		rollMainFont = new Font("a������2", Font.PLAIN, 18);
		rollAddFont = new Font("a������2", Font.PLAIN, 14);
		balBtnFont = new Font("a�߷�����", Font.PLAIN, 16);
		
		((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		refreshBtn = new JButton("���ΰ�ħ");
		refreshBtn.setForeground(Color.WHITE);
		refreshBtn.setFont(balBtnFont);
		refreshBtn.setBorderPainted(false);
		//��ư �׵θ� ����
		refreshBtn.setContentAreaFilled(false);
		//��ư ä��� ����
		refreshBtn.setFocusPainted(false);
		//��ư ���ý� ����� �׵θ� ����
		
		refreshBtn.addActionListener(this);
		
		panScroll = new JScrollPane(panC, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//��ũ������ ����
		panScroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		//��ũ�ѹٸ� �����ͼ� ����� 0���� ����
		panScroll.setBorder(BorderFactory.createEmptyBorder());
		//��ũ������ �׵θ� ���ֱ�
		
		messageTable();
		
		add(refreshBtn, BorderLayout.NORTH);
		add(panScroll, BorderLayout.CENTER);
		
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
		comm = e.getActionCommand();
		
		if(obj == refreshBtn) {
			center.removeAll();
			messageTable();
			
		} else if(comm != null) {
			//comm�� ���� ���� ���
			new MessageReading("���� ����", 600, 600);
			//���� ���� â�� ����
		}
	}

	public ArrayList<HashMap<String, String>> messageRegister() {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		//ArrayList<���� Ÿ��>��  HashMap<���� Ÿ��, ���� Ÿ��>�� ��´�
		//�迭ó�� ������ ũ�⸦ ������ ���� �ƴϸ�, ����Ʈó�� ũ�⸦ �ʱ�ȭ���� �ʿ䰡 ����
		//�޸𸮰� ����ϴ��� �ڵ����� ArrayList ũ��� �������� �����
		
		ResultSet rs = DB.select("select * from message where getid= '" + Login.idTf.getText() + "'");
		//���� �α��ε� ������� ������ �޾ƿ�
		
		try {
			while(rs.next()) {
				HashMap<String, String> message = new HashMap<String, String>();
				//HashMap<key, value>, ���� �Լ�<x, y>�� �����
				//key���� �ߺ� �����, value���� �ߺ� ���
				message.put("title", rs.getString("title"));
				message.put("sendname", rs.getString("sendname"));
				message.put("sendid", rs.getString("sendid"));
				message.put("dates", rs.getString("dates"));
				message.put("address", rs.getString("address"));
				
				list.add(message);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private void allCount() {
		n = 1;
		//n�� �ʱ�ȭ
		ResultSet rs = DB.select("select count(*) from message where getid= '" + Login.idTf.getText() + "'");
		//���� message ���̺� �α��ε� ������� ������ �����ϴ� �� ����
		try {
			if(rs.next()) {
				n += rs.getInt(1);
				//ù��° ��Ű�� �� + ���̺� �����ϴ� �� ����
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void messageTable() {
		//��üȸ�� ���� ���
		schema();
		
		for (HashMap<String, String> hashMap : messageRegister()) {
			JLabel title = new JLabel(hashMap.get("title"));
			JLabel sendname = new JLabel(hashMap.get("sendname"));
			JLabel dates = new JLabel(hashMap.get("dates"));
			address = new JButton(hashMap.get("address"));
			
			address.addActionListener(this);
			
			title.setFont(rollMainFont);
			sendname.setFont(rollMainFont);
			dates.setFont(rollMainFont);
			address.setFont(rollAddFont);
			
			center.add(title);
			center.add(sendname);
			center.add(dates);
			center.add(address);
		}
		
		allCount();
		center.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		center.setLayout(new GridLayout(n, 5, 10, 10));
		//n���� ��µ� �� �������� ����
	
		panC.add(center);
		panC.repaint();
		panC.revalidate();
		
	}

	private void schema() {
		JLabel titleL = new JLabel("����");
		JLabel sendnameL = new JLabel("������");
		JLabel datesL = new JLabel("�����ð�");
		JLabel addressL = new JLabel("�ּ�");
		
		titleL.setFont(rollTitleFont);
		sendnameL.setFont(rollTitleFont);
		datesL.setFont(rollTitleFont);
		addressL.setFont(rollTitleFont);
		
		center.add(titleL);
		center.add(sendnameL);
		center.add(datesL);
		center.add(addressL);
	}
	
}
