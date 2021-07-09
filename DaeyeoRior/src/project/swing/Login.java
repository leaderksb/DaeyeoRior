package project.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import project.db.DB;

public class Login extends JFrame implements ActionListener, MouseListener, KeyListener {

	private JButton loginBtn, joinBtn;
	public static JTextField idTf;
	//�ٸ� Ŭ���������� ��밡��
	private JPasswordField pwPf;
	private Font rollLabFont, rollTfFont, balBtnFont;
	private Color colorMint, colorDownMint;
	
	public Login(String setTitle, int width, int height) {

		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
			//����� UI ����
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}

		JPanel panN = new JPanel();
		JPanel center = new JPanel();
		JPanel panC = new JPanel();
		JPanel panS = new JPanel();
		JPanel panA = new JPanel();
		
		setLayout(new BorderLayout());
		panN.setLayout(new BorderLayout());
		center.setLayout(new GridLayout(2, 2));
		panC.setLayout(new BorderLayout());
		panA.setLayout(new BorderLayout());
		//���̾ƿ� ����
		center.setBorder(BorderFactory.createEmptyBorder(10, 8, 10, 0));
		panC.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 8));
		panA.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));
		//�� �� �� �� ���ΰ��� ����
		
		colorMint = new Color(133, 197, 196);
		colorDownMint = new Color(109, 180, 176);
		
		panN.setBackground(Color.WHITE);
		panC.setBackground(Color.WHITE);
		center.setBackground(Color.WHITE);
		panS.setBackground(Color.WHITE);
		panA.setBackground(Color.WHITE);
		//���� ����
		
		rollLabFont = new Font("a������3", Font.PLAIN, 15);
		rollTfFont = new Font("a������2", Font.PLAIN, 15);
		balBtnFont = new Font("a�߷�����", Font.PLAIN, 16);
        
		JLabel logo = new JLabel(new ImageIcon("images/logo.jpg"));
		//�ΰ� �̹���
		
		panN.add(logo);

		panA.add(panN, BorderLayout.NORTH);

		idTf = new JTextField();
		idTf.setPreferredSize(new Dimension(88, 35));
		pwPf = new JPasswordField();
		pwPf.setPreferredSize(new Dimension(88, 35));

		JLabel idL = new JLabel("���̵�", JLabel.CENTER);
		
		idL.setFont(rollLabFont);
		idTf.setFont(rollTfFont);
		
		center.add(idL);
		center.add(idTf);
		
		JLabel pwL = new JLabel("��й�ȣ", JLabel.CENTER);
		
		pwL.setFont(rollLabFont);
		pwPf.setFont(rollTfFont);
		
		idTf.addKeyListener(this);
		pwPf.addKeyListener(this);
		//Ű������ �߰�
		
		center.add(pwL);
		center.add(pwPf);
		
		loginBtn = new JButton(new ImageIcon("images/loginBtn1.jpg"));
		loginBtn.setPressedIcon(new ImageIcon("images/loginBtn2.jpg"));
		//��ư�� ��������
		loginBtn.setBorderPainted(false);
		//��ư �׵θ� ����
		loginBtn.setContentAreaFilled(false);
		//��ư ä��� ����
		loginBtn.setFocusPainted(false);
		//��ư ���ý� ����� �׵θ� ����
		
		loginBtn.addActionListener(this);
		
		panC.add(center, BorderLayout.WEST);
		panC.add(loginBtn, BorderLayout.CENTER);

		panA.add(panC, BorderLayout.CENTER);
		
		joinBtn = new JButton("ȸ������");
		joinBtn.setForeground(colorMint);
		joinBtn.setFont(balBtnFont);	
		joinBtn.setBorderPainted(false);
		//��ư �׵θ� ����
		joinBtn.setContentAreaFilled(false);
		//��ư ä��� ����
		joinBtn.setFocusPainted(false);
		//��ư ���ý� ����� �׵θ� ����
		
		joinBtn.addActionListener(this);
		joinBtn.addMouseListener(this);
		
		panS.add(joinBtn);
		
		panA.add(panS, BorderLayout.SOUTH);
		
		add(panA);
		
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
	public static void main(String[] args) {
		DB.db();
		new Login("�뿩����", 300, 420);
		//������ �ҷ�����
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == loginBtn) {
			if(chkIdPw() == true) {
				chkRole();
			} else {
				JOptionPane.showMessageDialog(null, "���̵� �Ǵ� ��й�ȣ�� �ùٸ��� �ʽ��ϴ�!", "�α��� ����", 
						JOptionPane.ERROR_MESSAGE);
				//�α��� ������ �ùٸ��� ������ ���â�� ���
			}
		} else if(obj == joinBtn) {
			new SignUp("ȸ������", 300, 250);
			//ȸ������ �������� ���
		}
	}
	
	private boolean chkIdPw() {
		//���̵�, ��� �˻�
		boolean chk = true;
		ResultSet rs = DB.select("select * from information where id = '" 
				+ idTf.getText() + "' and pw = '" + pwPf.getText() + "'");
		
		try {
			if(rs.next()) {
				chk = true;
			} else {
				chk = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chk;
	}
	
	private void chkRole() {
		ResultSet rs = DB.select("select role from information where id = '" + idTf.getText() + "'");
		
		try {
			if(rs.next()) {
				if(rs.getString(1).equals("manager")) {
					dispose();
					new Manager("������������", 800, 800);
					//������ �����̸� �������������� ���
					//������ ������ �����ͺ��̽����� ���� �ο�
					
				} else if(rs.getString(1).equals("member")){
					dispose();
					new Member("�뿩����", 1200, 800);
					//��� �����̸� ����������� ���
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		//���콺�� �ش� ������Ʈ ���� ������ ������
		if(joinBtn == e.getComponent()) {
			joinBtn.setForeground(colorDownMint);
		}
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		//���콺�� �ش� ������Ʈ ���� ������ ������
		if(joinBtn == e.getComponent()) {
			joinBtn.setForeground(colorMint);
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		//����Ű ������ ��ư Ŭ��
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			Toolkit.getDefaultToolkit().beep();
			loginBtn.doClick(); 
	    }
	}
	@Override
	public void keyReleased(KeyEvent e) {

	}
	@Override
	public void keyTyped(KeyEvent e) {

	}
	
}
