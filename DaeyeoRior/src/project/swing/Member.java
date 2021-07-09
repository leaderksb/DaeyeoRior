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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import project.db.DB;

public class Member extends JFrame implements ActionListener, MouseListener {

	private JTextField srchTf;
	public static JButton profileBtn;
	private JButton srchBtn, infoRetouchBtn, writingBtn, refreshBtn, messageBtn;
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
	private JLabel msg1;
	int n, m;
	//��
	private JTabbedPane tabBoard;
	private JPanel board0, board1, boardShares, boardExchange;
	private JScrollPane panScroll1, panScroll2;
	private Font rollTitleFont, rollMainFont, rollAddFont, balInforFont, balBtnFont;
	private Color colorBrown, colorDownBrown;
	
	public static Object comm;
	
	public Member(String setTitle, int width, int height) {
		
		((JComponent) getContentPane()).setBackground(Color.WHITE);
		
		JPanel panN = new JPanel();
		JPanel panSrch = new JPanel();
		board0 = new JPanel();
		board1 = new JPanel();
		boardShares = new JPanel();
		boardExchange = new JPanel();
		JPanel panC = new JPanel();
		JPanel east1 = new JPanel();
		JPanel east2 = new JPanel();
		JPanel panE = new JPanel();
		JPanel panA = new JPanel();
		
		Color colorDownWhite = new Color(252, 252, 252);
		Color colorMint = new Color(133, 197, 196);
		colorBrown = new Color(115, 44, 29);
		colorDownBrown = new Color(102, 48, 29);

		panN.setBackground(Color.WHITE);
		panSrch.setBackground(Color.WHITE);
		board0.setBackground(Color.WHITE);
		board1.setBackground(Color.WHITE);
		boardShares.setBackground(Color.WHITE);
		boardExchange.setBackground(Color.WHITE);
		board0.setBackground(colorDownWhite);
		board1.setBackground(colorDownWhite);
		boardShares.setBackground(colorDownWhite);
		boardExchange.setBackground(colorDownWhite);
		panC.setBackground(Color.WHITE);
		east1.setBackground(Color.WHITE);
		east2.setBackground(Color.WHITE);
		panE.setBackground(Color.WHITE);
		panA.setBackground(Color.WHITE);
		
		rollTitleFont = new Font("a������3", Font.PLAIN, 18);
		rollMainFont = new Font("a������2", Font.PLAIN, 18);
		rollAddFont = new Font("a������2", Font.PLAIN, 14);
		balInforFont = new Font("a�߷�����", Font.PLAIN, 14);
		balBtnFont = new Font("a�߷�����", Font.PLAIN, 18);
		
		panN.add(new JLabel(new ImageIcon("images/logo.jpg")));
		//�ΰ� �̹���
		
		refreshBtn = new JButton(new ImageIcon("images/refreshBtn1.jpg"));
		refreshBtn.setPressedIcon(new ImageIcon("images/refreshBtn2.jpg"));
		//��ư�� ��������
		refreshBtn.setBorderPainted(false);
		//��ư �׵θ� ����
		refreshBtn.setContentAreaFilled(false);
		//��ư ä��� ����
		refreshBtn.setFocusPainted(false);
		//��ư ���ý� ����� �׵θ� ����
		
		refreshBtn.addActionListener(this);
		
		srchTf = new JTextField("�������� �˻��ϼ���.");
		srchTf.setToolTipText("�������� �˻��ϼ���.");
		srchTf.setPreferredSize(new Dimension(380, 35));
		srchTf.setFont(rollMainFont);
		
		srchTf.addMouseListener(this);
		//���콺������ �߰�
		
		srchBtn = new JButton(new ImageIcon("images/srchBtn1.jpg"));
		srchBtn.setPressedIcon(new ImageIcon("images/srchBtn2.jpg"));
		//��ư�� ��������
		srchBtn.setBorderPainted(false);
		//��ư �׵θ� ����
		srchBtn.setContentAreaFilled(false);
		//��ư ä��� ����
		srchBtn.setFocusPainted(false);
		//��ư ���ý� ����� �׵θ� ����

		srchBtn.addActionListener(this);
		
		panSrch.add(refreshBtn);
		panSrch.add(srchTf);
		panSrch.add(srchBtn);
		
		panC.setLayout(new BorderLayout());
		panA.setLayout(new BorderLayout());
		panA.add(panSrch, BorderLayout.NORTH);
		
		tabBoard = new JTabbedPane();
		
		tabBoard.setBackground(colorMint);
		tabBoard.setForeground(colorDownBrown);
		tabBoard.setFont(rollTitleFont);
		
		sharesTable();
		
		panScroll1 = new JScrollPane(boardShares, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
		//��ũ������ ����
		panScroll1.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		//��ũ�ѹٸ� �����ͼ� ����� 0���� ����
		panScroll1.setBorder(BorderFactory.createEmptyBorder());
		//��ũ������ �׵θ� ���ֱ�
		panScroll1.setBackground(colorDownWhite);
		
		tabBoard.add("����", panScroll1);
		
		exchangeTable();
		
		panScroll2 = new JScrollPane(boardExchange, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		//��ũ������ ����
		panScroll2.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		//��ũ�ѹٸ� �����ͼ� ����� 0���� ����
		panScroll2.setBorder(BorderFactory.createEmptyBorder());
		//��ũ������ �׵θ� ���ֱ�
		panScroll2.setBackground(colorDownWhite);
		
		tabBoard.add("��ȯ", panScroll2);
		
		tabBoard.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		
		panC.add(tabBoard, BorderLayout.CENTER);
		
		msg1 = new JLabel("< " + Login.idTf.getText() + " > Welcome !", JLabel.CENTER);
		//DB�� �α����� ����ڿ��� ȯ���޽��� ����
		JLabel msg2 = new JLabel("[ RENTAL INTERIOR ]", JLabel.CENTER);
		JLabel msg3 = new JLabel("�� DaeyeoRior ��", JLabel.CENTER);
		
		msg1.setFont(balInforFont);
		msg2.setFont(balInforFont);
		msg3.setFont(balInforFont);
		
		ResultSet rs = DB.select("select img from information where id = '" + Login.idTf.getText() + "'");
		//���� �α��� �� ȸ���� �̹��� �� ������
		
		try {
			if(rs.next()) {
				profileBtn = new JButton(imgPik[rs.getInt(1)]);
				//�̹��� ������ 128
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		infoRetouchBtn = new JButton("�������� ����");
		infoRetouchBtn.setForeground(colorBrown);
		infoRetouchBtn.setFont(balBtnFont);
		infoRetouchBtn.setBorderPainted(false);
		//��ư �׵θ� ����
		infoRetouchBtn.setContentAreaFilled(false);
		//��ư ä��� ����
		infoRetouchBtn.setFocusPainted(false);
		//��ư ���ý� ����� �׵θ� ����
		
		writingBtn = new JButton("�۾���");
		writingBtn.setForeground(colorBrown);
		writingBtn.setFont(balBtnFont);
		writingBtn.setBorderPainted(false);
		//��ư �׵θ� ����
		writingBtn.setContentAreaFilled(false);
		//��ư ä��� ����
		writingBtn.setFocusPainted(false);
		//��ư ���ý� ����� �׵θ� ����
		
		messageBtn = new JButton(new ImageIcon("images/messageBtn1.png"));
		messageBtn.setPressedIcon(new ImageIcon("images/messageBtn2.png"));
		//��ư�� ��������
		messageBtn.setBorderPainted(false);
		//��ư �׵θ� ����
		messageBtn.setContentAreaFilled(false);
		//��ư ä��� ����
		messageBtn.setFocusPainted(false);
		//��ư ���ý� ����� �׵θ� ����
		
		profileBtn.setBorderPainted(false);
		//��ư �׵θ� ����
		profileBtn.setContentAreaFilled(false);
		//��ư ä��� ����
		profileBtn.setFocusPainted(false);
		//��ư ���ý� ����� �׵θ� ����
		
		profileBtn.addActionListener(this);
		infoRetouchBtn.addActionListener(this);
		infoRetouchBtn.addMouseListener(this);
		writingBtn.addActionListener(this);
		writingBtn.addMouseListener(this);
		messageBtn.addActionListener(this);
		
		east1.setLayout(new GridLayout(5, 1));
		east2.setLayout(new BorderLayout());
		panE.setLayout(new GridLayout(3, 1));
		//�̹��� ���ϰ� ����� ���߱� ���� GridLayout ����
		
		east1.add(msg1, BorderLayout.NORTH);
		east1.add(msg2, BorderLayout.NORTH);
		east1.add(msg3, BorderLayout.NORTH);
		east1.add(infoRetouchBtn, BorderLayout.CENTER);
		east1.add(writingBtn, BorderLayout.SOUTH);
		
		east2.add(messageBtn, BorderLayout.CENTER);
		//������ ä��� ���� ���� ����
		
		panE.add(profileBtn, BorderLayout.NORTH);
		panE.add(east1, BorderLayout.CENTER);
		panE.add(east2, BorderLayout.SOUTH);
		
		panE.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 20));
		
		add(panN, BorderLayout.NORTH);
		panA.add(panC, BorderLayout.CENTER);
		panA.add(panE, BorderLayout.EAST);
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//â�� ������ ���α׷� ����
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		comm = e.getActionCommand();
		
		if(obj == srchBtn) {
			if(tabBoard.getSelectedIndex() == 0) {
				//�����Խ����� ���õƴٸ�
				board0.removeAll();
				sharesSrchTable();
			} else if(tabBoard.getSelectedIndex() == 1) {
				//��ȯ�Խ����� ���õƴٸ�
				board1.removeAll();
				exchangeSrchTable();
			}
		} else if(obj == profileBtn) {
			new Profile("������ �̹��� ����", 300, 300);
		} else if(obj == infoRetouchBtn) {
			new InfoRetouch("�������� ����", 300, 250);
		} else if(obj == writingBtn) {
			//�۾��� ��ư�� ������
			new Writing("�۾���", 800, 800);
			//�۾��� â ���
		} else if(obj == refreshBtn) {
			//���ΰ�ħ
			board0.removeAll();
			board1.removeAll();

			sharesTable();
			exchangeTable();
		}  else if(obj == messageBtn) {
			new MessageBox("������", 1000, 400);
		} else if(comm != null) {
			//comm�� ���� ���� ���
			new Reading("�ۿ���", 1200, 800);
			//�ۿ��� â�� ���
		}
	}
	
	/**
	 * �ʱ�ȭ�� �� ���ΰ�ħ ���
	 */

	private void sharesTable() {
		JLabel writingTitleL1 = new JLabel("����");
		JLabel writerNameL1 = new JLabel("�ۼ���");
		JLabel writingDayL1 = new JLabel("�ۼ���");
		JLabel writingAddL1 = new JLabel("�ּ�");
		
		writingTitleL1.setFont(rollTitleFont);
		writerNameL1.setFont(rollTitleFont);
		writingDayL1.setFont(rollTitleFont);
		writingAddL1.setFont(rollTitleFont);
		
		board0.add(writingTitleL1);
		board0.add(writerNameL1);
		board0.add(writingDayL1);
		board0.add(writingAddL1);
		
		for (HashMap<String, String> hashMap : sharesRegister()) {
			JLabel writingTitle1 = new JLabel(hashMap.get("writingTitle1"));
			JLabel writerName1 = new JLabel(hashMap.get("writerName1"));
			JLabel writingDay1 = new JLabel(hashMap.get("writingDay1"));
			JButton writingAdd1 = new JButton(hashMap.get("writingAdd1"));
			
			writingTitle1.setFont(rollMainFont);
			writerName1.setFont(rollMainFont);
			writingDay1.setFont(rollMainFont);
			writingAdd1.setFont(rollAddFont);
			
			writingAdd1.addActionListener(this);

			board0.add(writingTitle1);
			board0.add(writerName1);
			board0.add(writingDay1);
			board0.add(writingAdd1);
		}
		
		sharesCount();
		board0.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		board0.setLayout(new GridLayout(n, 4, 10, 10));
		//n�� �˻��� �������� ����
		
		boardShares.add(board0);
		boardShares.repaint();
		boardShares.revalidate();
	}

	private void exchangeTable() {
		JLabel writingTitleL2 = new JLabel("����");
		JLabel writerNameL2 = new JLabel("�ۼ���");
		JLabel writingDayL2 = new JLabel("�ۼ���");
		JLabel writingAddL2 = new JLabel("�ּ�");
		
		writingTitleL2.setFont(rollTitleFont);
		writerNameL2.setFont(rollTitleFont);
		writingDayL2.setFont(rollTitleFont);
		writingAddL2.setFont(rollTitleFont);
		
		board1.add(writingTitleL2);
		board1.add(writerNameL2);
		board1.add(writingDayL2);
		board1.add(writingAddL2);
		
		for (HashMap<String, String> hashMap : exchangeRegister()) {
			JLabel writingTitle2 = new JLabel(hashMap.get("writingTitle2"));
			JLabel writerName2 = new JLabel(hashMap.get("writerName2"));
			JLabel writingDay2 = new JLabel(hashMap.get("writingDay2"));
			JButton writingAdd2 = new JButton(hashMap.get("writingAdd2"));
			
			writingTitle2.setFont(rollMainFont);
			writerName2.setFont(rollMainFont);
			writingDay2.setFont(rollMainFont);
			writingAdd2.setFont(rollAddFont);
			
			writingAdd2.addActionListener(this);

			board1.add(writingTitle2);
			board1.add(writerName2);
			board1.add(writingDay2);
			board1.add(writingAdd2);
		}
		
		exchangeCount();
		board1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		board1.setLayout(new GridLayout(m, 4, 10, 10));
		//m�� �˻��� �������� ����
	
		boardExchange.add(board1);
		boardExchange.repaint();
		boardExchange.revalidate();
	}

	public ArrayList<HashMap<String, String>> sharesRegister() {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		//ArrayList<���� Ÿ��>��  HashMap<���� Ÿ��, ���� Ÿ��>�� ��´�
		//�迭ó�� ������ ũ�⸦ ������ ���� �ƴϸ�, ����Ʈó�� ũ�⸦ �ʱ�ȭ���� �ʿ䰡 ����
		//�޸𸮰� ����ϴ��� �ڵ����� ArrayList ũ��� �������� �����
		
		ResultSet rs = DB.select("select * from shares order by dates desc");
		//�۾����� ��¥�� ������������ �˻�
		try {
			while(rs.next()) {
				HashMap<String, String> shares = new HashMap<String, String>();
				//HashMap<key, value>, ���� �Լ�<x, y>�� �����
				//key���� �ߺ� �����, value���� �ߺ� ���
				shares.put("writingTitle1", rs.getString("title"));
				shares.put("writerName1", rs.getString("name"));
				shares.put("writingDay1", rs.getString("dates"));
				shares.put("writingAdd1", rs.getString("address"));
				
				list.add(shares);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<HashMap<String, String>> exchangeRegister() {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		//ArrayList<���� Ÿ��>��  HashMap<���� Ÿ��, ���� Ÿ��>�� ��´�
		//�迭ó�� ������ ũ�⸦ ������ ���� �ƴϸ�, ����Ʈó�� ũ�⸦ �ʱ�ȭ���� �ʿ䰡 ����
		//�޸𸮰� ����ϴ��� �ڵ����� ArrayList ũ��� �������� �����
		
		ResultSet rs = DB.select("select * from exchange order by dates desc");
		//�۾����� ��¥�� ������������ �˻�
		try {
			while(rs.next()) {
				HashMap<String, String> exchange = new HashMap<String, String>();
				//HashMap<key, value>, ���� �Լ�<x, y>�� �����
				//key���� �ߺ� �����, value���� �ߺ� ���
				exchange.put("writingTitle2", rs.getString("title"));
				exchange.put("writerName2", rs.getString("name"));
				exchange.put("writingDay2", rs.getString("dates"));
				exchange.put("writingAdd2", rs.getString("address"));
				
				list.add(exchange);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private void sharesCount() {
		n = 1;
		//n�� �ʱ�ȭ
		ResultSet rs = DB.select("select count(*) from shares");
		//���� shares ���̺� �����ϴ� �� ����
		try {
			if(rs.next()) {
				n += rs.getInt(1);
				//ù��° ��Ű�� �� + ���̺� �����ϴ� �� ����
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void exchangeCount() {
		m = 1;
		//m�� �ʱ�ȭ
		ResultSet rs = DB.select("select count(*) from exchange");
		//���� exchange ���̺� �����ϴ� �� ����
		try {
			if(rs.next()) {
				m += rs.getInt(1);
				//ù��° ��Ű�� �� + ���̺� �����ϴ� �� ����
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * ������ �˻����
	 */
	
	
	private void sharesSrchTable() {
		JLabel writingTitleL1 = new JLabel("����");
		JLabel writerNameL1 = new JLabel("�ۼ���");
		JLabel writingDayL1 = new JLabel("�ۼ���");
		JLabel writingAddL1 = new JLabel("�ּ�");
		
		writingTitleL1.setFont(rollTitleFont);
		writerNameL1.setFont(rollTitleFont);
		writingDayL1.setFont(rollTitleFont);
		writingAddL1.setFont(rollTitleFont);
		
		board0.add(writingTitleL1);
		board0.add(writerNameL1);
		board0.add(writingDayL1);
		board0.add(writingAddL1);
		
		for (HashMap<String, String> hashMap : sharesSrchRegister()) {
			JLabel writingTitle1 = new JLabel(hashMap.get("writingTitle1"));
			JLabel writerName1 = new JLabel(hashMap.get("writerName1"));
			JLabel writingDay1 = new JLabel(hashMap.get("writingDay1"));
			JButton writingAdd1 = new JButton(hashMap.get("writingAdd1"));
			
			writingTitle1.setFont(rollMainFont);
			writerName1.setFont(rollMainFont);
			writingDay1.setFont(rollMainFont);
			writingAdd1.setFont(rollAddFont);
			
			writingAdd1.addActionListener(this);

			board0.add(writingTitle1);
			board0.add(writerName1);
			board0.add(writingDay1);
			board0.add(writingAdd1);
		}
		
		sharesSrchCount();
		if(n == 1) {
			//���� �÷��� �ִٸ�
			JOptionPane.showMessageDialog(null, "�˻��� ���� �����ϴ�!", "�˻� ����", 
					JOptionPane.INFORMATION_MESSAGE);
			//�˻��� ���� ���ٴ� ����â ���
			
			//���ΰ�ħ
			board0.removeAll();

			sharesTable();
		} else {
			board0.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
			board0.setLayout(new GridLayout(n, 4, 10, 10));
			//n�� �˻��� �������� ����
			
			boardShares.add(board0);
			boardShares.repaint();
			boardShares.revalidate();
		}
		
	}

	private void exchangeSrchTable() {
		JLabel writingTitleL2 = new JLabel("����");
		JLabel writerNameL2 = new JLabel("�ۼ���");
		JLabel writingDayL2 = new JLabel("�ۼ���");
		JLabel writingAddL2 = new JLabel("�ּ�");
		
		writingTitleL2.setFont(rollTitleFont);
		writerNameL2.setFont(rollTitleFont);
		writingDayL2.setFont(rollTitleFont);
		writingAddL2.setFont(rollTitleFont);
		
		board1.add(writingTitleL2);
		board1.add(writerNameL2);
		board1.add(writingDayL2);
		board1.add(writingAddL2);
		
		for (HashMap<String, String> hashMap : exchangeSrchRegister()) {
			JLabel writingTitle2 = new JLabel(hashMap.get("writingTitle2"));
			JLabel writerName2 = new JLabel(hashMap.get("writerName2"));
			JLabel writingDay2 = new JLabel(hashMap.get("writingDay2"));
			JButton writingAdd2 = new JButton(hashMap.get("writingAdd2"));
			
			writingTitle2.setFont(rollMainFont);
			writerName2.setFont(rollMainFont);
			writingDay2.setFont(rollMainFont);
			writingAdd2.setFont(rollAddFont);
			
			writingAdd2.addActionListener(this);

			board1.add(writingTitle2);
			board1.add(writerName2);
			board1.add(writingDay2);
			board1.add(writingAdd2);
		}
		
		exchangeSrchCount();
		if(m == 1) {
			//���� �÷��� �ִٸ�
			JOptionPane.showMessageDialog(null, "�˻��� ���� �����ϴ�!", "�˻� ����", 
					JOptionPane.INFORMATION_MESSAGE);
			//�˻��� ���� ���ٴ� ����â ���
			
			//���ΰ�ħ
			board1.removeAll();

			exchangeTable();
		} else {
			board1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
			board1.setLayout(new GridLayout(m, 4, 10, 10));
			//m�� �˻��� �������� ����
		
			boardExchange.add(board1);
			boardExchange.repaint();
			boardExchange.revalidate();
		}
	}

	public ArrayList<HashMap<String, String>> sharesSrchRegister() {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		//ArrayList<���� Ÿ��>��  HashMap<���� Ÿ��, ���� Ÿ��>�� ��´�
		//�迭ó�� ������ ũ�⸦ ������ ���� �ƴϸ�, ����Ʈó�� ũ�⸦ �ʱ�ȭ���� �ʿ䰡 ����
		//�޸𸮰� ����ϴ��� �ڵ����� ArrayList ũ��� �������� �����
		
		ResultSet rs = DB.select("select * from shares where title like '%" + srchTf.getText() + "%' order by dates desc");
		//�۾����� ��¥�� ������������ �˻�
		try {
			while(rs.next()) {
				HashMap<String, String> shares = new HashMap<String, String>();
				//HashMap<key, value>, ���� �Լ�<x, y>�� �����
				//key���� �ߺ� �����, value���� �ߺ� ���
				shares.put("writingTitle1", rs.getString("title"));
				shares.put("writerName1", rs.getString("name"));
				shares.put("writingDay1", rs.getString("dates"));
				shares.put("writingAdd1", rs.getString("address"));
				
				list.add(shares);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<HashMap<String, String>> exchangeSrchRegister() {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		//ArrayList<���� Ÿ��>��  HashMap<���� Ÿ��, ���� Ÿ��>�� ��´�
		//�迭ó�� ������ ũ�⸦ ������ ���� �ƴϸ�, ����Ʈó�� ũ�⸦ �ʱ�ȭ���� �ʿ䰡 ����
		//�޸𸮰� ����ϴ��� �ڵ����� ArrayList ũ��� �������� �����
		
		ResultSet rs = DB.select("select * from exchange where title like '%" + srchTf.getText() + "%' order by dates desc");
		//�۾����� ��¥�� ������������ �˻�
		try {
			while(rs.next()) {
				HashMap<String, String> exchange = new HashMap<String, String>();
				//HashMap<key, value>, ���� �Լ�<x, y>�� �����
				//key���� �ߺ� �����, value���� �ߺ� ���
				exchange.put("writingTitle2", rs.getString("title"));
				exchange.put("writerName2", rs.getString("name"));
				exchange.put("writingDay2", rs.getString("dates"));
				exchange.put("writingAdd2", rs.getString("address"));
				
				list.add(exchange);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private void sharesSrchCount() {
		n = 1;
		//n�� �ʱ�ȭ
		ResultSet rs = DB.select("select count(*) from shares where title like '%" + srchTf.getText() + "%' order by dates desc");
		//���� shares ���̺� �˻��� ���� �����ϴ� �� ����
		try {
			if(rs.next()) {
				n += rs.getInt(1);
				//ù��° ��Ű�� �� + ���̺� �����ϴ� �� ����
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void exchangeSrchCount() {
		m = 1;
		//m�� �ʱ�ȭ
		ResultSet rs = DB.select("select count(*) from exchange where title like '%" + srchTf.getText() + "%' order by dates desc");
		//���� exchange ���̺� �˻��� ���� �����ϴ� �� ����
		try {
			if(rs.next()) {
				m += rs.getInt(1);
				//ù��° ��Ű�� �� + ���̺� �����ϴ� �� ����
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getComponent() == srchTf) {
			//srchTf�� ��������
			srchTf.setText("");
			//�˻�â ���� �����
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//���콺�� �ش� ������Ʈ ���� ������ ������
		if(infoRetouchBtn == e.getComponent()) {
			infoRetouchBtn.setForeground(colorDownBrown);
		} else if(writingBtn == e.getComponent()) {
			writingBtn.setForeground(colorDownBrown);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		//���콺�� �ش� ������Ʈ ���� ������ ������
		if(infoRetouchBtn == e.getComponent()) {
			infoRetouchBtn.setForeground(colorBrown);
		} else if(writingBtn == e.getComponent()) {
			writingBtn.setForeground(colorBrown);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
}
