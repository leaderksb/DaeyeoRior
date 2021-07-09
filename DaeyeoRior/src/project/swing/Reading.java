package project.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import project.db.DB;

public class Reading extends JFrame implements ActionListener {
	
	private JLabel title, dates;
	public static String name, id, address, reading = "null";
	private JTextArea txt;
	int img;
	private ImageIcon imgPik[] = {
			new ImageIcon(""), //빈 값
			new ImageIcon("images/appliances.jpg"), 
			new ImageIcon("images/bathroom.jpg"), 
			new ImageIcon("images/bed.jpg"), 
			new ImageIcon("images/bookshelf.jpg"), 
			new ImageIcon("images/cabinet.jpg"), 
			new ImageIcon("images/chair.jpg"), 
			new ImageIcon("images/closet.jpg"), 
			new ImageIcon("images/curtain.jpg"), 
			new ImageIcon("images/desk.jpg"), 
			new ImageIcon("images/hanger.jpg"), 
			new ImageIcon("images/mirror.jpg"), 
			new ImageIcon("images/prop.jpg"), 
			new ImageIcon("images/sofa.jpg"), 
			new ImageIcon("images/table.jpg") };
	private JLabel imgL;
	private JButton writingRetouchBtn, deleteBtn, messageBtn;
	private Font rollTitleFont, rollMainFont, rollAddFont, balBtnFont;
	private Color colorMint;

	public Reading(String setTitle, int width, int height) {
		
		JPanel panN = new JPanel();
		JPanel top = new JPanel();
		JPanel left = new JPanel();
		JPanel imgP = new JPanel();
		JPanel middle = new JPanel();		
		JPanel under = new JPanel();
		JPanel right = new JPanel();
		JPanel panC = new JPanel();
		JPanel panS = new JPanel();
		JPanel panA = new JPanel();
		
		colorMint = new Color(133, 197, 196);
		
		getContentPane().setBackground(Color.WHITE);
		panN.setBackground(Color.WHITE);
		panN.setBackground(Color.WHITE);
		top.setBackground(Color.WHITE);
		left.setBackground(Color.WHITE);
		imgP.setBackground(Color.WHITE);
		middle.setBackground(Color.WHITE);
		under.setBackground(Color.WHITE);
		right.setBackground(Color.WHITE);
		panC.setBackground(Color.WHITE);
		panS.setBackground(Color.WHITE);
		panA.setBackground(Color.WHITE);
		
		rollTitleFont = new Font("a뉴굴림3", Font.PLAIN, 20);
		rollMainFont = new Font("a뉴굴림2", Font.PLAIN, 18);
		rollAddFont = new Font("a뉴굴림2", Font.PLAIN, 16);
		balBtnFont = new Font("a발레리노", Font.PLAIN, 16);
		
		top.setLayout(new BorderLayout());
		middle.setLayout(new BorderLayout());
		panC.setLayout(new BorderLayout());
		panA.setLayout(new BorderLayout());
		left.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 150));
		right.setBorder(BorderFactory.createEmptyBorder(0, 150, 0, 0));
		panC.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panA.setBorder(BorderFactory.createEmptyBorder(30, 30, 15, 30));
		
		ResultSet rs = DB.select("select * from shares where address = '"
				+ Member.comm + "' union select * from exchange where address = '" + Member.comm + "'");
		//컬럼의 개수와 컬럼의 데이터타입이 같은 두개의 테이블을 합쳐서 하나의 결과 도출
		
		try {
			if(rs.next()) {
				if(rs.getString(1) != null) {
					title = new JLabel(rs.getString("title"), JLabel.CENTER);
					name = rs.getString("name");
					id = rs.getString("id");
					dates = new JLabel(rs.getString("dates"));
					address = rs.getString("address");
					img = rs.getInt("img");
					txt = new JTextArea(rs.getString("txt"));
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		JLabel titleL = new JLabel("제목 : ");
		JLabel nameL = new JLabel("작성자 : ");
		JLabel nameIdL = new JLabel(name + "(" + id + ")");
		JLabel datesL = new JLabel("작성일자 : ");
		JLabel addressL = new JLabel("글주소 : " + address);
		imgL = new JLabel();
		
		writingRetouchBtn = new JButton("수정");
		writingRetouchBtn.setForeground(colorMint);
		writingRetouchBtn.setFont(balBtnFont);
		writingRetouchBtn.setBorderPainted(false);
		//버튼 테두리 제거
		writingRetouchBtn.setContentAreaFilled(false);
		//버튼 채우기 제거
		writingRetouchBtn.setFocusPainted(false);
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
		
		messageBtn = new JButton("쪽지");
		messageBtn.setForeground(colorMint);
		messageBtn.setFont(balBtnFont);
		messageBtn.setBorderPainted(false);
		//버튼 테두리 제거
		messageBtn.setContentAreaFilled(false);
		//버튼 채우기 제거
		messageBtn.setFocusPainted(false);
		//버튼 선택시 생기는 테두리 제거
		
		writingRetouchBtn.addActionListener(this);
		deleteBtn.addActionListener(this);
		messageBtn.addActionListener(this);
		
		titleL.setFont(rollTitleFont);
		title.setFont(rollTitleFont);
		nameL.setFont(rollMainFont);
		nameIdL.setFont(rollMainFont);
		dates.setFont(rollMainFont);
		datesL.setFont(rollMainFont);
		addressL.setFont(rollAddFont);
		txt.setFont(rollMainFont);
		
		panN.add(titleL);
		panN.add(title);
		
		left.add(datesL);
		left.add(dates);
		
		under.add(addressL);
		
		if(Login.idTf.getText().equals(id)) {
			under.add(writingRetouchBtn);
			under.add(deleteBtn);
		}

		right.add(nameL);
		right.add(nameIdL);
		
		top.add(left, BorderLayout.WEST);
		top.add(right, BorderLayout.EAST);
		
		imgL.setIcon(imgPik[img]);
		//선택한 인덱스 값의 이미지를 가져옴
		
		imgP.add(imgL);
		txt.setLineWrap(true);
		//꽉차면 다음줄로 가게함
		txt.setEditable(false);
		//텍스트 값 수정불가
		JScrollPane panScroll = new JScrollPane(txt, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//스크롤페인 선언
		panScroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		//스크롤바를 가져와서 사이즈를 0으로 설정
		panScroll.setBorder(BorderFactory.createEmptyBorder());
		//스크롤페인 테두리 없애기

		middle.add(imgP, BorderLayout.NORTH);
		middle.add(panScroll, BorderLayout.CENTER);
		
		panC.add(top, BorderLayout.NORTH);
		panC.add(middle, BorderLayout.CENTER);
		panC.add(under, BorderLayout.SOUTH);
		
		panA.add(panN, BorderLayout.NORTH);
		panA.add(panC, BorderLayout.CENTER);
		panA.add(messageBtn, BorderLayout.SOUTH);

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
		
		if(obj == writingRetouchBtn) {
			//현재 회원이 작성한 글이면
			
			dispose();
			//현재 창을 닫고
			new writingRetouch("글수정", 800, 800);
			//수정 창을 띄움
		}
		if(obj == messageBtn) {
			reading = "Reading"; 
			new MessageWriting("쪽지 보내기", 600, 600);
		} else if(obj == deleteBtn) {
			//현재 회원이 작성한 글이면
			
			int result = JOptionPane.showConfirmDialog(null, "현재 글을 삭제 하시겠습니까?", "삭제 여부",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			//다이얼로그 컨폼창
			
			if(result == JOptionPane.OK_OPTION) {
				
				DB.update("delete from (select * from shares where address = '" + address + "')");
				DB.update("delete from (select * from exchange where address = '" + address + "')");
				//각각 테이블에서 현재 글 주소값을 검색하여 삭제
				
				JOptionPane.showMessageDialog(null, "글이 삭제 되었습니다. \n 새로고침 하세요", "삭제 완료", 
						JOptionPane.INFORMATION_MESSAGE);
				//글삭제 완료 정보창 띄움
				
				dispose();
			}
		}
	}
}
