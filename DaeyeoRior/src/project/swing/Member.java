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
	//행
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
		
		rollTitleFont = new Font("a뉴굴림3", Font.PLAIN, 18);
		rollMainFont = new Font("a뉴굴림2", Font.PLAIN, 18);
		rollAddFont = new Font("a뉴굴림2", Font.PLAIN, 14);
		balInforFont = new Font("a발레리나", Font.PLAIN, 14);
		balBtnFont = new Font("a발레리노", Font.PLAIN, 18);
		
		panN.add(new JLabel(new ImageIcon("images/logo.jpg")));
		//로고 이미지
		
		refreshBtn = new JButton(new ImageIcon("images/refreshBtn1.jpg"));
		refreshBtn.setPressedIcon(new ImageIcon("images/refreshBtn2.jpg"));
		//버튼을 눌렀을때
		refreshBtn.setBorderPainted(false);
		//버튼 테두리 제거
		refreshBtn.setContentAreaFilled(false);
		//버튼 채우기 제거
		refreshBtn.setFocusPainted(false);
		//버튼 선택시 생기는 테두리 제거
		
		refreshBtn.addActionListener(this);
		
		srchTf = new JTextField("제목으로 검색하세요.");
		srchTf.setToolTipText("제목으로 검색하세요.");
		srchTf.setPreferredSize(new Dimension(380, 35));
		srchTf.setFont(rollMainFont);
		
		srchTf.addMouseListener(this);
		//마우스리스너 추가
		
		srchBtn = new JButton(new ImageIcon("images/srchBtn1.jpg"));
		srchBtn.setPressedIcon(new ImageIcon("images/srchBtn2.jpg"));
		//버튼을 눌렀을때
		srchBtn.setBorderPainted(false);
		//버튼 테두리 제거
		srchBtn.setContentAreaFilled(false);
		//버튼 채우기 제거
		srchBtn.setFocusPainted(false);
		//버튼 선택시 생기는 테두리 제거

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
		//스크롤페인 선언
		panScroll1.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		//스크롤바를 가져와서 사이즈를 0으로 설정
		panScroll1.setBorder(BorderFactory.createEmptyBorder());
		//스크롤페인 테두리 없애기
		panScroll1.setBackground(colorDownWhite);
		
		tabBoard.add("나눔", panScroll1);
		
		exchangeTable();
		
		panScroll2 = new JScrollPane(boardExchange, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		//스크롤페인 선언
		panScroll2.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		//스크롤바를 가져와서 사이즈를 0으로 설정
		panScroll2.setBorder(BorderFactory.createEmptyBorder());
		//스크롤페인 테두리 없애기
		panScroll2.setBackground(colorDownWhite);
		
		tabBoard.add("교환", panScroll2);
		
		tabBoard.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		
		panC.add(tabBoard, BorderLayout.CENTER);
		
		msg1 = new JLabel("< " + Login.idTf.getText() + " > Welcome !", JLabel.CENTER);
		//DB로 로그인한 사용자에게 환영메시지 전달
		JLabel msg2 = new JLabel("[ RENTAL INTERIOR ]", JLabel.CENTER);
		JLabel msg3 = new JLabel("★ DaeyeoRior ★", JLabel.CENTER);
		
		msg1.setFont(balInforFont);
		msg2.setFont(balInforFont);
		msg3.setFont(balInforFont);
		
		ResultSet rs = DB.select("select img from information where id = '" + Login.idTf.getText() + "'");
		//현재 로그인 된 회원의 이미지 값 가져옴
		
		try {
			if(rs.next()) {
				profileBtn = new JButton(imgPik[rs.getInt(1)]);
				//이미지 사이즈 128
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		infoRetouchBtn = new JButton("개인정보 수정");
		infoRetouchBtn.setForeground(colorBrown);
		infoRetouchBtn.setFont(balBtnFont);
		infoRetouchBtn.setBorderPainted(false);
		//버튼 테두리 제거
		infoRetouchBtn.setContentAreaFilled(false);
		//버튼 채우기 제거
		infoRetouchBtn.setFocusPainted(false);
		//버튼 선택시 생기는 테두리 제거
		
		writingBtn = new JButton("글쓰기");
		writingBtn.setForeground(colorBrown);
		writingBtn.setFont(balBtnFont);
		writingBtn.setBorderPainted(false);
		//버튼 테두리 제거
		writingBtn.setContentAreaFilled(false);
		//버튼 채우기 제거
		writingBtn.setFocusPainted(false);
		//버튼 선택시 생기는 테두리 제거
		
		messageBtn = new JButton(new ImageIcon("images/messageBtn1.png"));
		messageBtn.setPressedIcon(new ImageIcon("images/messageBtn2.png"));
		//버튼을 눌렀을때
		messageBtn.setBorderPainted(false);
		//버튼 테두리 제거
		messageBtn.setContentAreaFilled(false);
		//버튼 채우기 제거
		messageBtn.setFocusPainted(false);
		//버튼 선택시 생기는 테두리 제거
		
		profileBtn.setBorderPainted(false);
		//버튼 테두리 제거
		profileBtn.setContentAreaFilled(false);
		//버튼 채우기 제거
		profileBtn.setFocusPainted(false);
		//버튼 선택시 생기는 테두리 제거
		
		profileBtn.addActionListener(this);
		infoRetouchBtn.addActionListener(this);
		infoRetouchBtn.addMouseListener(this);
		writingBtn.addActionListener(this);
		writingBtn.addMouseListener(this);
		messageBtn.addActionListener(this);
		
		east1.setLayout(new GridLayout(5, 1));
		east2.setLayout(new BorderLayout());
		panE.setLayout(new GridLayout(3, 1));
		//이미지 파일과 사이즈를 맞추기 위해 GridLayout 적용
		
		east1.add(msg1, BorderLayout.NORTH);
		east1.add(msg2, BorderLayout.NORTH);
		east1.add(msg3, BorderLayout.NORTH);
		east1.add(infoRetouchBtn, BorderLayout.CENTER);
		east1.add(writingBtn, BorderLayout.SOUTH);
		
		east2.add(messageBtn, BorderLayout.CENTER);
		//여백을 채우기 위해 광고를 삽입
		
		panE.add(profileBtn, BorderLayout.NORTH);
		panE.add(east1, BorderLayout.CENTER);
		panE.add(east2, BorderLayout.SOUTH);
		
		panE.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 20));
		
		add(panN, BorderLayout.NORTH);
		panA.add(panC, BorderLayout.CENTER);
		panA.add(panE, BorderLayout.EAST);
		add(panA, BorderLayout.CENTER);
		
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
		comm = e.getActionCommand();
		
		if(obj == srchBtn) {
			if(tabBoard.getSelectedIndex() == 0) {
				//나눔게시판이 선택됐다면
				board0.removeAll();
				sharesSrchTable();
			} else if(tabBoard.getSelectedIndex() == 1) {
				//교환게시판이 선택됐다면
				board1.removeAll();
				exchangeSrchTable();
			}
		} else if(obj == profileBtn) {
			new Profile("프로필 이미지 선택", 300, 300);
		} else if(obj == infoRetouchBtn) {
			new InfoRetouch("개인정보 수정", 300, 250);
		} else if(obj == writingBtn) {
			//글쓰기 버튼을 누르면
			new Writing("글쓰기", 800, 800);
			//글쓰기 창 띄움
		} else if(obj == refreshBtn) {
			//새로고침
			board0.removeAll();
			board1.removeAll();

			sharesTable();
			exchangeTable();
		}  else if(obj == messageBtn) {
			new MessageBox("쪽지함", 1000, 400);
		} else if(comm != null) {
			//comm의 값이 있을 경우
			new Reading("글열람", 1200, 800);
			//글열람 창을 띄움
		}
	}
	
	/**
	 * 초기화면 및 새로고침 기능
	 */

	private void sharesTable() {
		JLabel writingTitleL1 = new JLabel("제목");
		JLabel writerNameL1 = new JLabel("작성자");
		JLabel writingDayL1 = new JLabel("작성일");
		JLabel writingAddL1 = new JLabel("주소");
		
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
		//n은 검색된 데이터의 개수
		
		boardShares.add(board0);
		boardShares.repaint();
		boardShares.revalidate();
	}

	private void exchangeTable() {
		JLabel writingTitleL2 = new JLabel("제목");
		JLabel writerNameL2 = new JLabel("작성자");
		JLabel writingDayL2 = new JLabel("작성일");
		JLabel writingAddL2 = new JLabel("주소");
		
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
		//m은 검색된 데이터의 개수
	
		boardExchange.add(board1);
		boardExchange.repaint();
		boardExchange.revalidate();
	}

	public ArrayList<HashMap<String, String>> sharesRegister() {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		//ArrayList<담을 타입>에  HashMap<담을 타입, 담을 타입>을 담는다
		//배열처럼 고정된 크기를 가지는 것이 아니며, 리스트처럼 크기를 초기화해줄 필요가 없음
		//메모리가 허용하는한 자동으로 ArrayList 크기는 동적으로 변경됨
		
		ResultSet rs = DB.select("select * from shares order by dates desc");
		//글쓰기한 날짜의 내림차순으로 검색
		try {
			while(rs.next()) {
				HashMap<String, String> shares = new HashMap<String, String>();
				//HashMap<key, value>, 수학 함수<x, y>와 비슷함
				//key값은 중복 비허용, value값은 중복 허용
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
		//ArrayList<담을 타입>에  HashMap<담을 타입, 담을 타입>을 담는다
		//배열처럼 고정된 크기를 가지는 것이 아니며, 리스트처럼 크기를 초기화해줄 필요가 없음
		//메모리가 허용하는한 자동으로 ArrayList 크기는 동적으로 변경됨
		
		ResultSet rs = DB.select("select * from exchange order by dates desc");
		//글쓰기한 날짜의 내림차순으로 검색
		try {
			while(rs.next()) {
				HashMap<String, String> exchange = new HashMap<String, String>();
				//HashMap<key, value>, 수학 함수<x, y>와 비슷함
				//key값은 중복 비허용, value값은 중복 허용
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
		//n값 초기화
		ResultSet rs = DB.select("select count(*) from shares");
		//현재 shares 테이블에 존재하는 행 개수
		try {
			if(rs.next()) {
				n += rs.getInt(1);
				//첫번째 스키마 줄 + 테이블에 존재하는 행 개수
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void exchangeCount() {
		m = 1;
		//m값 초기화
		ResultSet rs = DB.select("select count(*) from exchange");
		//현재 exchange 테이블에 존재하는 행 개수
		try {
			if(rs.next()) {
				m += rs.getInt(1);
				//첫번째 스키마 줄 + 테이블에 존재하는 행 개수
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 글제목 검색기능
	 */
	
	
	private void sharesSrchTable() {
		JLabel writingTitleL1 = new JLabel("제목");
		JLabel writerNameL1 = new JLabel("작성자");
		JLabel writingDayL1 = new JLabel("작성일");
		JLabel writingAddL1 = new JLabel("주소");
		
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
			//행이 컬럼만 있다면
			JOptionPane.showMessageDialog(null, "검색된 글이 없습니다!", "검색 실패", 
					JOptionPane.INFORMATION_MESSAGE);
			//검색된 글이 없다는 정보창 띄움
			
			//새로고침
			board0.removeAll();

			sharesTable();
		} else {
			board0.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
			board0.setLayout(new GridLayout(n, 4, 10, 10));
			//n은 검색된 데이터의 개수
			
			boardShares.add(board0);
			boardShares.repaint();
			boardShares.revalidate();
		}
		
	}

	private void exchangeSrchTable() {
		JLabel writingTitleL2 = new JLabel("제목");
		JLabel writerNameL2 = new JLabel("작성자");
		JLabel writingDayL2 = new JLabel("작성일");
		JLabel writingAddL2 = new JLabel("주소");
		
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
			//행이 컬럼만 있다면
			JOptionPane.showMessageDialog(null, "검색된 글이 없습니다!", "검색 실패", 
					JOptionPane.INFORMATION_MESSAGE);
			//검색된 글이 없다는 정보창 띄움
			
			//새로고침
			board1.removeAll();

			exchangeTable();
		} else {
			board1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
			board1.setLayout(new GridLayout(m, 4, 10, 10));
			//m은 검색된 데이터의 개수
		
			boardExchange.add(board1);
			boardExchange.repaint();
			boardExchange.revalidate();
		}
	}

	public ArrayList<HashMap<String, String>> sharesSrchRegister() {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		//ArrayList<담을 타입>에  HashMap<담을 타입, 담을 타입>을 담는다
		//배열처럼 고정된 크기를 가지는 것이 아니며, 리스트처럼 크기를 초기화해줄 필요가 없음
		//메모리가 허용하는한 자동으로 ArrayList 크기는 동적으로 변경됨
		
		ResultSet rs = DB.select("select * from shares where title like '%" + srchTf.getText() + "%' order by dates desc");
		//글쓰기한 날짜의 내림차순으로 검색
		try {
			while(rs.next()) {
				HashMap<String, String> shares = new HashMap<String, String>();
				//HashMap<key, value>, 수학 함수<x, y>와 비슷함
				//key값은 중복 비허용, value값은 중복 허용
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
		//ArrayList<담을 타입>에  HashMap<담을 타입, 담을 타입>을 담는다
		//배열처럼 고정된 크기를 가지는 것이 아니며, 리스트처럼 크기를 초기화해줄 필요가 없음
		//메모리가 허용하는한 자동으로 ArrayList 크기는 동적으로 변경됨
		
		ResultSet rs = DB.select("select * from exchange where title like '%" + srchTf.getText() + "%' order by dates desc");
		//글쓰기한 날짜의 내림차순으로 검색
		try {
			while(rs.next()) {
				HashMap<String, String> exchange = new HashMap<String, String>();
				//HashMap<key, value>, 수학 함수<x, y>와 비슷함
				//key값은 중복 비허용, value값은 중복 허용
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
		//n값 초기화
		ResultSet rs = DB.select("select count(*) from shares where title like '%" + srchTf.getText() + "%' order by dates desc");
		//현재 shares 테이블 검색한 값이 존재하는 행 개수
		try {
			if(rs.next()) {
				n += rs.getInt(1);
				//첫번째 스키마 줄 + 테이블에 존재하는 행 개수
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void exchangeSrchCount() {
		m = 1;
		//m값 초기화
		ResultSet rs = DB.select("select count(*) from exchange where title like '%" + srchTf.getText() + "%' order by dates desc");
		//현재 exchange 테이블에 검색한 값이 존재하는 행 개수
		try {
			if(rs.next()) {
				m += rs.getInt(1);
				//첫번째 스키마 줄 + 테이블에 존재하는 행 개수
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getComponent() == srchTf) {
			//srchTf가 눌렸으면
			srchTf.setText("");
			//검색창 값을 비워줌
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//마우스가 해당 컴포넌트 영역 안으로 들어오면
		if(infoRetouchBtn == e.getComponent()) {
			infoRetouchBtn.setForeground(colorDownBrown);
		} else if(writingBtn == e.getComponent()) {
			writingBtn.setForeground(colorDownBrown);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		//마우스가 해당 컴포넌트 영역 밖으로 나가면
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
