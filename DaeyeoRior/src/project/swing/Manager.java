package project.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import project.db.DB;

public class Manager extends JFrame implements ActionListener, MouseListener {

	private JPanel center, panC;
	private JTextField srchTf, delTf;
	private JButton srchBtn, delBtn;
	int n;
	//��
	private JScrollPane panScroll;
	private Font rollTitleFont, rollMainFont, balBtnFont;
	private Color colorMint;

	public Manager(String setTitle, int width, int height) {
		
		((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		//����Ʈ���ο� ���ΰ��� ����
		
		JPanel panN = new JPanel();
		center = new JPanel();
		panC = new JPanel();
		JPanel panS = new JPanel();
		
		colorMint = new Color(133, 197, 196);
		
		getContentPane().setBackground(Color.WHITE);
		panN.setBackground(Color.WHITE);
		center.setBackground(Color.WHITE);
		panC.setBackground(Color.WHITE);
		panS.setBackground(Color.WHITE);
		
		rollTitleFont = new Font("a������3", Font.PLAIN, 18);
		rollMainFont = new Font("a������2", Font.PLAIN, 18);
		balBtnFont = new Font("a�߷�����", Font.PLAIN, 16);
		
		srchTf = new JTextField("���̵�� �˻��ϼ���.");
		srchTf.setToolTipText("���̵�� �˻��ϼ���.");
		srchTf.setPreferredSize(new Dimension(380, 35));
		srchTf.setFont(rollMainFont);
		
		srchTf.addMouseListener(this);
		//���콺������ �߰�
		
		srchBtn = new JButton("�˻�");
		srchBtn.setForeground(colorMint);
		srchBtn.setFont(balBtnFont);	
		srchBtn.setBorderPainted(false);
		//��ư �׵θ� ����
		srchBtn.setContentAreaFilled(false);
		//��ư ä��� ����
		srchBtn.setFocusPainted(false);
		//��ư ���ý� ����� �׵θ� ����
		
		srchBtn.addActionListener(this);

		panN.add(srchTf);
		panN.add(srchBtn);
		panN.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
		
		allTable();
		//ȭ���� ��ﶧ ��üȸ�� ���� ���
		
		panScroll = new JScrollPane(panC, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//��ũ������ ����
		panScroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		//��ũ�ѹٸ� �����ͼ� ����� 0���� ����
		panScroll.setBorder(BorderFactory.createEmptyBorder());
		//��ũ������ �׵θ� ���ֱ�
		
		delTf = new JTextField("���̵�� �����ϼ���.");
		delTf.setToolTipText("���̵�� �����ϼ���.");
		delTf.setPreferredSize(new Dimension(380, 35));
		delTf.setFont(rollMainFont);
		
		delTf.addMouseListener(this);
		//���콺������ �߰�
		
		delBtn = new JButton("ȸ������");
		delBtn.setForeground(colorMint);
		delBtn.setFont(balBtnFont);	
		delBtn.setBorderPainted(false);
		//��ư �׵θ� ����
		delBtn.setContentAreaFilled(false);
		//��ư ä��� ����
		delBtn.setFocusPainted(false);
		//��ư ���ý� ����� �׵θ� ����
		
		delBtn.addActionListener(this);
		
		panS.add(delTf);
		panS.add(delBtn);
		panS.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
		
		add(panN, BorderLayout.NORTH);
		add(panScroll, BorderLayout.CENTER);
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//â�� ������ ���α׷� ����
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == srchBtn) {
			ResultSet rs = DB.select("select * from information where id = '" + srchTf.getText() + "'");
			//���� srchTf�� �Էµ� ���̵� ���� ȸ�������� �˻�
			try {
				if(rs.next()) {
					//rs�� �˻��� ������ �ִٸ�
					
					center.removeAll();
					//center�� ������ ����
					
					schema();
					
					//��ü�ڵ带 ���̱����� hashMap ��� getString() ���
					JLabel role = new JLabel(rs.getString(1));
					JLabel name = new JLabel(rs.getString(2));
					JLabel id = new JLabel(rs.getString(3));
					JLabel pw = new JLabel(rs.getString(4));
					JLabel phone = new JLabel(rs.getString(5));
					
					role.setFont(rollMainFont);
					name.setFont(rollMainFont);
					id.setFont(rollMainFont);
					pw.setFont(rollMainFont);
					phone.setFont(rollMainFont);

					center.add(role);
					center.add(name);
					center.add(id);
					center.add(pw);
					center.add(phone);
					
					center.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
					center.setLayout(new GridLayout(2, 5, 10, 10));
					
					panC.add(center);
					panC.repaint();
					panC.revalidate();
					
					add(panScroll, BorderLayout.CENTER);
				} else {
					//�˻��� ������ ������
					
					JOptionPane.showMessageDialog(null, "�˻��� ȸ���� �������� �ʽ��ϴ�.", "�˻�����",
							JOptionPane.WARNING_MESSAGE);
					//�˻����� ���â
					
					center.removeAll();
					//center�� ������ ����
					
					allTable();
					//������ �� ��üȸ�� ���� ���
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else if(obj == delBtn) {
			int result = JOptionPane.showConfirmDialog(null, "�Էµ� ȸ���� ����Ż�� ��Ű�ðڽ��ϱ�?", "����Ż�� ����",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			//���̾�α� ����â
			if(result == JOptionPane.OK_OPTION) {
				DB.update("delete from information where id = '" + delTf.getText() + "'");
				//delTf�� �Էµ� ���̵� ���� ȸ�������� ����

				center.removeAll();
				//center�� ������ ����
				
				allTable();
				//������ �� ��üȸ�� ���� ���
				
				add(panScroll, BorderLayout.CENTER);
			} else if(result == JOptionPane.CANCEL_OPTION) {
				dispose();
			}
		}
	}
	
	public ArrayList<HashMap<String, String>> allRegister() {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		//ArrayList<���� Ÿ��>��  HashMap<���� Ÿ��, ���� Ÿ��>�� ��´�
		//�迭ó�� ������ ũ�⸦ ������ ���� �ƴϸ�, ����Ʈó�� ũ�⸦ �ʱ�ȭ���� �ʿ䰡 ����
		//�޸𸮰� ����ϴ��� �ڵ����� ArrayList ũ��� �������� �����
		
		ResultSet rs = DB.select("select * from information");
		try {
			while(rs.next()) {
				HashMap<String, String> information = new HashMap<String, String>();
				//HashMap<key, value>, ���� �Լ�<x, y>�� �����
				//key���� �ߺ� �����, value���� �ߺ� ���
				information.put("role", rs.getString("role"));
				information.put("name", rs.getString("name"));
				information.put("id", rs.getString("id"));
				information.put("pw", rs.getString("pw"));
				information.put("phone", rs.getString("phone"));
				
				list.add(information);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	private void allCount() {
		n = 1;
		//n�� �ʱ�ȭ
		ResultSet rs = DB.select("select count(*) from information");
		//���� information ���̺� �����ϴ� �� ����
		try {
			if(rs.next()) {
				n += rs.getInt(1);
				//ù��° ��Ű�� �� + ���̺� �����ϴ� �� ����
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void allTable() {
		//��üȸ�� ���� ���
		schema();
		
		for (HashMap<String, String> hashMap : allRegister()) {
			JLabel role = new JLabel(hashMap.get("role"));
			JLabel name = new JLabel(hashMap.get("name"));
			JLabel id = new JLabel(hashMap.get("id"));
			JLabel pw = new JLabel(hashMap.get("pw"));
			JLabel phone = new JLabel(hashMap.get("phone"));
			
			role.setFont(rollMainFont);
			name.setFont(rollMainFont);
			id.setFont(rollMainFont);
			pw.setFont(rollMainFont);
			phone.setFont(rollMainFont);
			
			center.add(role);
			center.add(name);
			center.add(id);
			center.add(pw);
			center.add(phone);
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
		JLabel ruleL = new JLabel("����");
		JLabel nameL = new JLabel("�̸�");
		JLabel idL = new JLabel("���̵�");
		JLabel pwL = new JLabel("��й�ȣ");
		JLabel phoneL = new JLabel("��ȭ��ȣ");
		
		ruleL.setFont(rollTitleFont);
		nameL.setFont(rollTitleFont);
		idL.setFont(rollTitleFont);
		pwL.setFont(rollTitleFont);
		phoneL.setFont(rollTitleFont);
		
		center.add(ruleL);
		center.add(nameL);
		center.add(idL);
		center.add(pwL);
		center.add(phoneL);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getComponent() == srchTf) {
			//srchTf�� ��������
			srchTf.setText("");
			//�˻�â ���� �����
		} else if(e.getComponent() == delTf) {
			//delTf�� ��������
			delTf.setText("");
			//����â ���� �����
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}
	
}
