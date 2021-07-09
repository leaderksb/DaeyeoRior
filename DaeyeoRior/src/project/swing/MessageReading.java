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
	//보낸이의 이름과 아이디 값 저장
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
		
		rollTitleFont = new Font("a뉴굴림3", Font.PLAIN, 20);
		rollMainFont = new Font("a뉴굴림2", Font.PLAIN, 18);
		rollAddFont = new Font("a뉴굴림2", Font.PLAIN, 16);
		balBtnFont = new Font("a발레리노", Font.PLAIN, 16);
		
		top.setLayout(new BorderLayout());
		panC.setLayout(new BorderLayout());
		panA.setLayout(new BorderLayout());
		left.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		right.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		panC.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panA.setBorder(BorderFactory.createEmptyBorder(30, 30, 15, 30));
		
		ResultSet rs = DB.select("select * from message where address = '" + MessageBox.comm + "' order by dates desc");
		//컬럼의 개수와 컬럼의 데이터타입이 같은 두개의 테이블을 합쳐서 하나의 결과 도출
		//보낸시간의 내림차순으로 검색
		
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
		
		JLabel titleL = new JLabel("제목 : ");
		JLabel nameL = new JLabel("보낸이 : ");
		JLabel nameIdL = new JLabel(name + "(" + sendid + ")");
		JLabel datesL = new JLabel("보낸시간 : ");
		JLabel addressL = new JLabel("쪽지주소 : " + address);
		
		replyBtn = new JButton("답장");
		replyBtn.setForeground(colorMint);
		replyBtn.setFont(balBtnFont);
		replyBtn.setBorderPainted(false);
		//버튼 테두리 제거
		replyBtn.setContentAreaFilled(false);
		//버튼 채우기 제거
		replyBtn.setFocusPainted(false);
		//버튼 선택시 생기는 테두리 제거
		
		deleteBtn = new JButton("삭제");
		deleteBtn.setForeground(colorMint);
		deleteBtn.setFont(balBtnFont);
		deleteBtn.setBorderPainted(false);
		//버튼 테두리 제거
		deleteBtn.setContentAreaFilled(false);
		//버튼 채우기 제거
		deleteBtn.setFocusPainted(false);
		//버튼 선택시 생기는 테두리 제거
		
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
		//꽉차면 다음줄로 가게함
		txt.setEditable(false);
		//텍스트 값 수정불가
		panScroll = new JScrollPane(txt, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//스크롤페인 선언
		panScroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		//스크롤바를 가져와서 사이즈를 0으로 설정
		panScroll.setBorder(BorderFactory.createEmptyBorder());
		//스크롤페인 테두리 없애기

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
		
		if(obj == replyBtn) {
			messageReading = "MessageReading";
			new MessageWriting("답장 보내기", 600, 600);
		} else if(obj == deleteBtn) {
			//현재 회원이 작성한 글이면
			
			int result = JOptionPane.showConfirmDialog(null, "현재 쪽지를 삭제 하시겠습니까?", "삭제 여부",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			//다이얼로그 컨폼창
			
			if(result == JOptionPane.OK_OPTION) {
				
				DB.update("delete from (select * from message where address = '" + address + "')");
				//메시지 테이블에서 현재 쪽지 주소값을 검색하여 삭제
				
				JOptionPane.showMessageDialog(null, "쪽지가 삭제 되었습니다.", "삭제 완료", 
						JOptionPane.INFORMATION_MESSAGE);
				//쪽지 삭제 완료 정보창 띄움
				dispose();
			}
		} else if(obj == cancleBtn) {
			dispose();
			//현재 창만 종료
		}
	}
}
