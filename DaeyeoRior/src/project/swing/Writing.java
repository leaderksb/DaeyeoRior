package project.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import project.db.DB;

public class Writing extends JFrame implements ActionListener {
	
	private String boardPik[] = {"나눔게시판", "교환게시판"};
	private JComboBox<String> boardCb;
	private String imgStr[] = { "이미지 선택", "가전제품", "화장실 용품", "침대", "책장", 
			"수납장", "의자", "옷장", "커튼", "책상", "행거", "거울", "소품", "소파", "식탁"};
	private ImageIcon imgPik[] = {
			new ImageIcon("images/basic1.jpg"), 
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
	//이미지 세로 값은 350으로 통일
	private JComboBox<String> imgCb;
	private JLabel imgL;
	private JTextField writingTf;
	private JTextArea writingTa;
	private JButton okBtn, cancleBtn;
	private JScrollPane panScroll;
	private String name;
	private Font rollTitleFont, rollMainFont, balBtnFont;
	private Color colorMint;
	public static int refresh;
	//새로고침 여부를 알려줄 변수 선언

	public Writing(String setTitle, int width, int height) {
		
		JPanel center1 = new JPanel();
		JPanel center2 = new JPanel();
		JPanel panC = new JPanel();
		JPanel panS = new JPanel();
		
		colorMint = new Color(133, 197, 196);
		
		center1.setBackground(Color.WHITE);
		center2.setBackground(Color.WHITE);
		panC.setBackground(Color.WHITE);
		panS.setBackground(Color.WHITE);
		
		rollTitleFont = new Font("a뉴굴림3", Font.PLAIN, 20);
		rollMainFont = new Font("a뉴굴림2", Font.PLAIN, 18);
		balBtnFont = new Font("a발레리노", Font.PLAIN, 16);
		
		panC.setLayout(new BorderLayout());
		center1.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
		panC.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));
		panS.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		
		boardCb = new JComboBox<String>(boardPik);
		boardCb.addActionListener(this);
		
		imgCb = new JComboBox<String>(imgStr);
		imgCb.addActionListener(this);
		
		JLabel title = new JLabel("제목 : ");
		
		title.setFont(rollTitleFont);
		
		imgL = new JLabel(imgPik[0]);
		
		writingTf = new JTextField(30);
		writingTa = new JTextArea();
		
		writingTf.setFont(rollTitleFont);
		writingTa.setFont(rollMainFont);
		writingTa.setLineWrap(true);
		//꽉차면 다음줄로 가게함
		
		okBtn = new JButton("등록");
		okBtn.setForeground(colorMint);
		okBtn.setFont(balBtnFont);
		okBtn.setBorderPainted(false);
		//버튼 테두리 제거
		okBtn.setContentAreaFilled(false);
		//버튼 채우기 제거
		okBtn.setFocusPainted(false);
		//버튼 선택시 생기는 테두리 제거
		
		cancleBtn = new JButton("취소");
		cancleBtn.setForeground(colorMint);
		cancleBtn.setFont(balBtnFont);
		cancleBtn.setBorderPainted(false);
		//버튼 테두리 제거
		cancleBtn.setContentAreaFilled(false);
		//버튼 채우기 제거
		cancleBtn.setFocusPainted(false);
		//버튼 선택시 생기는 테두리 제거
		
		okBtn.addActionListener(this);
		cancleBtn.addActionListener(this);
		
		panScroll = new JScrollPane(writingTa, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
		//스크롤페인을 선언
		panScroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		//스크롤바를 가져와서 사이즈를 0으로 설정
		
		center1.add(boardCb);
		center1.add(imgCb);
		center1.add(title);
		center1.add(writingTf);
		
		center2.add(imgL);

		panC.add(center1, BorderLayout.NORTH);
		panC.add(panScroll, BorderLayout.CENTER);
		panC.add(center2, BorderLayout.SOUTH);
		panS.add(okBtn);
		panS.add(cancleBtn);
		
		add(panC, BorderLayout.CENTER);
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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == okBtn) {
			String boardName = (String) boardCb.getSelectedItem();
			//선택된 게시판 값을 가져옴
			
			if(writingTf.getText().length() > 0 && writingTa.getText().length() > 0) {
				//제목과 내용에 입력된 값이 있다면
				
				if(boardName.equals("나눔게시판")) {
					//나눔게시판이 선택됐다면
					
					ResultSet rs = DB.select("select name from information where id ='" 
					+ Login.idTf.getText() + "'");
					
					try {
						if(rs.next()) {
							name = rs.getString(1);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					Date date = new Date();
					//현재시간
					
					SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");
					//년-월-일로 형식변환
					
					String time = dayFormat.format(date);
					//작성시간
					
					SimpleDateFormat urlFormat = new SimpleDateFormat("yyMMddHHmmss");
					
					String url = Login.idTf.getText() + urlFormat.format(date);
					
					DB.update("insert into shares values ('" + writingTf.getText() + "', '" 
							+ name + "', '" + Login.idTf.getText() + "', '" + time + "', '" + url
							+ "', '" + imgCb.getSelectedIndex() + "', '" + writingTa.getText() + "')");
					//글정보를 DB에 저장
					
					JOptionPane.showMessageDialog(null, "글이 등록 되었습니다. \n 새로고침 하세요.", "글쓰기 완료", 
							JOptionPane.INFORMATION_MESSAGE);
					//글쓰기 완료 정보창 띄움
					
					dispose();
				} else if(boardName.equals("교환게시판")) {
					//교환게시판이 선택됐다면
					
					ResultSet rs = DB.select("select name from information where id ='" 
					+ Login.idTf.getText() + "'");
					
					try {
						if(rs.next()) {
							name = rs.getString(1);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					Date date = new Date();
					//현재시간
					
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					//년-월-일로 형식변환
					
					String time = format.format(date);
					//작성시간
					
					SimpleDateFormat urlFormat = new SimpleDateFormat("yyMMddHHmmss");
					
					String url = Login.idTf.getText() + urlFormat.format(date);
					
					DB.update("insert into exchange values ('" + writingTf.getText() + "', '" 
							+ name + "', '" + Login.idTf.getText() + "', '" + time + "', '" + url 
							+ "', '" + imgCb.getSelectedIndex() + "', '" + writingTa.getText() + "')");
					//글정보를 DB에 저장
					
					JOptionPane.showMessageDialog(null, "글이 등록 되었습니다. \n 새로고침 하세요.", "글쓰기 완료", 
							JOptionPane.INFORMATION_MESSAGE);
					//글쓰기 완료 정보창 띄움
					
					dispose();
				}
			}
		} else if(obj == imgCb) {
			int index = imgCb.getSelectedIndex();
			//콤보박스에서 선택한 인덱스 값을 가져옴
			imgL.setIcon(imgPik[index]);
			
			//선택한 인덱스 값의 이미지를 가져옴
		} else if(obj == cancleBtn) {
			dispose();
			//현재 창만 종료
		}
	}
}
