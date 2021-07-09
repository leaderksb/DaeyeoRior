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
		
		rollTitleFont = new Font("a뉴굴림3", Font.PLAIN, 18);
		rollMainFont = new Font("a뉴굴림2", Font.PLAIN, 18);
		rollAddFont = new Font("a뉴굴림2", Font.PLAIN, 14);
		balBtnFont = new Font("a발레리노", Font.PLAIN, 16);
		
		((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		refreshBtn = new JButton("새로고침");
		refreshBtn.setForeground(Color.WHITE);
		refreshBtn.setFont(balBtnFont);
		refreshBtn.setBorderPainted(false);
		//버튼 테두리 제거
		refreshBtn.setContentAreaFilled(false);
		//버튼 채우기 제거
		refreshBtn.setFocusPainted(false);
		//버튼 선택시 생기는 테두리 제거
		
		refreshBtn.addActionListener(this);
		
		panScroll = new JScrollPane(panC, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//스크롤페인 선언
		panScroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		//스크롤바를 가져와서 사이즈를 0으로 설정
		panScroll.setBorder(BorderFactory.createEmptyBorder());
		//스크롤페인 테두리 없애기
		
		messageTable();
		
		add(refreshBtn, BorderLayout.NORTH);
		add(panScroll, BorderLayout.CENTER);
		
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
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		comm = e.getActionCommand();
		
		if(obj == refreshBtn) {
			center.removeAll();
			messageTable();
			
		} else if(comm != null) {
			//comm의 값이 있을 경우
			new MessageReading("받은 쪽지", 600, 600);
			//받은 쪽지 창을 띄운다
		}
	}

	public ArrayList<HashMap<String, String>> messageRegister() {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		//ArrayList<담을 타입>에  HashMap<담을 타입, 담을 타입>을 담는다
		//배열처럼 고정된 크기를 가지는 것이 아니며, 리스트처럼 크기를 초기화해줄 필요가 없음
		//메모리가 허용하는한 자동으로 ArrayList 크기는 동적으로 변경됨
		
		ResultSet rs = DB.select("select * from message where getid= '" + Login.idTf.getText() + "'");
		//현재 로그인된 사용자의 쪽지만 받아옴
		
		try {
			while(rs.next()) {
				HashMap<String, String> message = new HashMap<String, String>();
				//HashMap<key, value>, 수학 함수<x, y>와 비슷함
				//key값은 중복 비허용, value값은 중복 허용
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
		//n값 초기화
		ResultSet rs = DB.select("select count(*) from message where getid= '" + Login.idTf.getText() + "'");
		//현재 message 테이블에 로그인된 사용자의 쪽지가 존재하는 행 개수
		try {
			if(rs.next()) {
				n += rs.getInt(1);
				//첫번째 스키마 줄 + 테이블에 존재하는 행 개수
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void messageTable() {
		//전체회원 정보 출력
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
		//n값은 출력될 행 데이터의 개수
	
		panC.add(center);
		panC.repaint();
		panC.revalidate();
		
	}

	private void schema() {
		JLabel titleL = new JLabel("제목");
		JLabel sendnameL = new JLabel("보낸이");
		JLabel datesL = new JLabel("보낸시간");
		JLabel addressL = new JLabel("주소");
		
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
