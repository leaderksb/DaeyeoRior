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
	//행
	private JScrollPane panScroll;
	private Font rollTitleFont, rollMainFont, balBtnFont;
	private Color colorMint;

	public Manager(String setTitle, int width, int height) {
		
		((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		//컨텐트페인에 내부공백 설정
		
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
		
		rollTitleFont = new Font("a뉴굴림3", Font.PLAIN, 18);
		rollMainFont = new Font("a뉴굴림2", Font.PLAIN, 18);
		balBtnFont = new Font("a발레리노", Font.PLAIN, 16);
		
		srchTf = new JTextField("아이디로 검색하세요.");
		srchTf.setToolTipText("아이디로 검색하세요.");
		srchTf.setPreferredSize(new Dimension(380, 35));
		srchTf.setFont(rollMainFont);
		
		srchTf.addMouseListener(this);
		//마우스리스너 추가
		
		srchBtn = new JButton("검색");
		srchBtn.setForeground(colorMint);
		srchBtn.setFont(balBtnFont);	
		srchBtn.setBorderPainted(false);
		//버튼 테두리 제거
		srchBtn.setContentAreaFilled(false);
		//버튼 채우기 제거
		srchBtn.setFocusPainted(false);
		//버튼 선택시 생기는 테두리 제거
		
		srchBtn.addActionListener(this);

		panN.add(srchTf);
		panN.add(srchBtn);
		panN.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
		
		allTable();
		//화면을 띄울때 전체회원 정보 출력
		
		panScroll = new JScrollPane(panC, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//스크롤페인 선언
		panScroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		//스크롤바를 가져와서 사이즈를 0으로 설정
		panScroll.setBorder(BorderFactory.createEmptyBorder());
		//스크롤페인 테두리 없애기
		
		delTf = new JTextField("아이디로 삭제하세요.");
		delTf.setToolTipText("아이디로 삭제하세요.");
		delTf.setPreferredSize(new Dimension(380, 35));
		delTf.setFont(rollMainFont);
		
		delTf.addMouseListener(this);
		//마우스리스너 추가
		
		delBtn = new JButton("회원삭제");
		delBtn.setForeground(colorMint);
		delBtn.setFont(balBtnFont);	
		delBtn.setBorderPainted(false);
		//버튼 테두리 제거
		delBtn.setContentAreaFilled(false);
		//버튼 채우기 제거
		delBtn.setFocusPainted(false);
		//버튼 선택시 생기는 테두리 제거
		
		delBtn.addActionListener(this);
		
		panS.add(delTf);
		panS.add(delBtn);
		panS.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
		
		add(panN, BorderLayout.NORTH);
		add(panScroll, BorderLayout.CENTER);
		add(panS, BorderLayout.SOUTH);
		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == srchBtn) {
			ResultSet rs = DB.select("select * from information where id = '" + srchTf.getText() + "'");
			//현재 srchTf에 입력된 아이디를 가진 회원정보를 검색
			try {
				if(rs.next()) {
					//rs에 검색된 정보가 있다면
					
					center.removeAll();
					//center의 정보를 삭제
					
					schema();
					
					//전체코드를 줄이기위해 hashMap 대신 getString() 사용
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
					//검색된 정보가 없으면
					
					JOptionPane.showMessageDialog(null, "검색된 회원이 존재하지 않습니다.", "검색실패",
							JOptionPane.WARNING_MESSAGE);
					//검색실패 경고창
					
					center.removeAll();
					//center의 정보를 삭제
					
					allTable();
					//삭제된 후 전체회원 정보 출력
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else if(obj == delBtn) {
			int result = JOptionPane.showConfirmDialog(null, "입력된 회원을 강제탈퇴 시키시겠습니까?", "강제탈퇴 여부",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			//다이얼로그 컨폼창
			if(result == JOptionPane.OK_OPTION) {
				DB.update("delete from information where id = '" + delTf.getText() + "'");
				//delTf에 입력된 아이디를 가진 회원정보를 삭제

				center.removeAll();
				//center의 정보를 삭제
				
				allTable();
				//삭제된 후 전체회원 정보 출력
				
				add(panScroll, BorderLayout.CENTER);
			} else if(result == JOptionPane.CANCEL_OPTION) {
				dispose();
			}
		}
	}
	
	public ArrayList<HashMap<String, String>> allRegister() {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		//ArrayList<담을 타입>에  HashMap<담을 타입, 담을 타입>을 담는다
		//배열처럼 고정된 크기를 가지는 것이 아니며, 리스트처럼 크기를 초기화해줄 필요가 없음
		//메모리가 허용하는한 자동으로 ArrayList 크기는 동적으로 변경됨
		
		ResultSet rs = DB.select("select * from information");
		try {
			while(rs.next()) {
				HashMap<String, String> information = new HashMap<String, String>();
				//HashMap<key, value>, 수학 함수<x, y>와 비슷함
				//key값은 중복 비허용, value값은 중복 허용
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
		//n값 초기화
		ResultSet rs = DB.select("select count(*) from information");
		//현재 information 테이블에 존재하는 행 개수
		try {
			if(rs.next()) {
				n += rs.getInt(1);
				//첫번째 스키마 줄 + 테이블에 존재하는 행 개수
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void allTable() {
		//전체회원 정보 출력
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
		//n값은 출력될 행 데이터의 개수

		panC.add(center);
		panC.repaint();
		panC.revalidate();
		
	}

	private void schema() {
		JLabel ruleL = new JLabel("권한");
		JLabel nameL = new JLabel("이름");
		JLabel idL = new JLabel("아이디");
		JLabel pwL = new JLabel("비밀번호");
		JLabel phoneL = new JLabel("전화번호");
		
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
			//srchTf가 눌렸으면
			srchTf.setText("");
			//검색창 값을 비워줌
		} else if(e.getComponent() == delTf) {
			//delTf가 눌렸으면
			delTf.setText("");
			//삭제창 값을 비워줌
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
