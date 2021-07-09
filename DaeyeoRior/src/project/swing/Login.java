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
	//다른 클래스에서도 사용가능
	private JPasswordField pwPf;
	private Font rollLabFont, rollTfFont, balBtnFont;
	private Color colorMint, colorDownMint;
	
	public Login(String setTitle, int width, int height) {

		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
			//룩앤필 UI 적용
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
		//레이아웃 설정
		center.setBorder(BorderFactory.createEmptyBorder(10, 8, 10, 0));
		panC.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 8));
		panA.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));
		//상 좌 하 우 내부공백 설정
		
		colorMint = new Color(133, 197, 196);
		colorDownMint = new Color(109, 180, 176);
		
		panN.setBackground(Color.WHITE);
		panC.setBackground(Color.WHITE);
		center.setBackground(Color.WHITE);
		panS.setBackground(Color.WHITE);
		panA.setBackground(Color.WHITE);
		//배경색 설정
		
		rollLabFont = new Font("a뉴굴림3", Font.PLAIN, 15);
		rollTfFont = new Font("a뉴굴림2", Font.PLAIN, 15);
		balBtnFont = new Font("a발레리노", Font.PLAIN, 16);
        
		JLabel logo = new JLabel(new ImageIcon("images/logo.jpg"));
		//로고 이미지
		
		panN.add(logo);

		panA.add(panN, BorderLayout.NORTH);

		idTf = new JTextField();
		idTf.setPreferredSize(new Dimension(88, 35));
		pwPf = new JPasswordField();
		pwPf.setPreferredSize(new Dimension(88, 35));

		JLabel idL = new JLabel("아이디", JLabel.CENTER);
		
		idL.setFont(rollLabFont);
		idTf.setFont(rollTfFont);
		
		center.add(idL);
		center.add(idTf);
		
		JLabel pwL = new JLabel("비밀번호", JLabel.CENTER);
		
		pwL.setFont(rollLabFont);
		pwPf.setFont(rollTfFont);
		
		idTf.addKeyListener(this);
		pwPf.addKeyListener(this);
		//키리스너 추가
		
		center.add(pwL);
		center.add(pwPf);
		
		loginBtn = new JButton(new ImageIcon("images/loginBtn1.jpg"));
		loginBtn.setPressedIcon(new ImageIcon("images/loginBtn2.jpg"));
		//버튼을 눌렀을때
		loginBtn.setBorderPainted(false);
		//버튼 테두리 제거
		loginBtn.setContentAreaFilled(false);
		//버튼 채우기 제거
		loginBtn.setFocusPainted(false);
		//버튼 선택시 생기는 테두리 제거
		
		loginBtn.addActionListener(this);
		
		panC.add(center, BorderLayout.WEST);
		panC.add(loginBtn, BorderLayout.CENTER);

		panA.add(panC, BorderLayout.CENTER);
		
		joinBtn = new JButton("회원가입");
		joinBtn.setForeground(colorMint);
		joinBtn.setFont(balBtnFont);	
		joinBtn.setBorderPainted(false);
		//버튼 테두리 제거
		joinBtn.setContentAreaFilled(false);
		//버튼 채우기 제거
		joinBtn.setFocusPainted(false);
		//버튼 선택시 생기는 테두리 제거
		
		joinBtn.addActionListener(this);
		joinBtn.addMouseListener(this);
		
		panS.add(joinBtn);
		
		panA.add(panS, BorderLayout.SOUTH);
		
		add(panA);
		
		setTitle(setTitle);
		//제목 설정
		setSize(width, height);
		//크기 설정
		setResizable(false);
		//임의로 크기조절 불가
		setLocationRelativeTo(this);
		//중앙 배치
		setVisible(true);
		//창 띄우기
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//창을 닫을시 프로그램 종료
	}
	public static void main(String[] args) {
		DB.db();
		new Login("대여리어", 300, 420);
		//생성자 불러오기
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == loginBtn) {
			if(chkIdPw() == true) {
				chkRole();
			} else {
				JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 올바르지 않습니다!", "로그인 실패", 
						JOptionPane.ERROR_MESSAGE);
				//로그인 정보가 올바르지 않으면 경고창을 띄움
			}
		} else if(obj == joinBtn) {
			new SignUp("회원가입", 300, 250);
			//회원가입 페이지를 띄움
		}
	}
	
	private boolean chkIdPw() {
		//아이디, 비번 검사
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
					new Manager("관리자페이지", 800, 800);
					//관리자 역할이면 관리자페이지를 띄움
					//관리자 역할은 데이터베이스에서 직접 부여
					
				} else if(rs.getString(1).equals("member")){
					dispose();
					new Member("대여리어", 1200, 800);
					//멤버 역할이면 멤버페이지를 띄움
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
		//마우스가 해당 컴포넌트 영역 안으로 들어오면
		if(joinBtn == e.getComponent()) {
			joinBtn.setForeground(colorDownMint);
		}
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		//마우스가 해당 컴포넌트 영역 밖으로 나가면
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
		//엔터키 눌릴시 버튼 클릭
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
