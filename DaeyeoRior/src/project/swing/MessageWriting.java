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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import project.db.DB;

public class MessageWriting extends JFrame implements ActionListener {

	private JTextField writingTf;
	private JTextArea writingTa;
	private JButton okBtn, cancleBtn;
	private JScrollPane panScroll;
	private String name;
	private Font rollTitleFont, rollMainFont, balBtnFont;
	private Color colorMint;

	public MessageWriting(String setTitle, int width, int height) {
		
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

		JLabel title = new JLabel("제목 : ");
		
		title.setFont(rollTitleFont);
		
		writingTf = new JTextField(25);
		writingTa = new JTextArea();
		
		writingTf.setFont(rollTitleFont);
		writingTa.setFont(rollMainFont);
		writingTa.setLineWrap(true);
		//꽉차면 다음줄로 가게함
		
		okBtn = new JButton("보내기");
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
		//스크롤페인 선언
		panScroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		//스크롤바를 가져와서 사이즈를 0으로 설정

		center1.add(title);
		center1.add(writingTf);
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
				
				SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				//년-월-일로 형식변환
				
				String time = dayFormat.format(date);
				//작성시간
				
				SimpleDateFormat urlFormat = new SimpleDateFormat("yyMMddHHmmss");
				
				String url = Login.idTf.getText() + urlFormat.format(date);
				
				if(writingTf.getText().length() > 0 && writingTa.getText().length() > 0) {
					//제목과 내용에 입력된 값이 있다면
				
					if(Reading.reading.equals("Reading")) {
						//게시글에서 쪽지를 보냈으면
						DB.update("insert into message values ('" + writingTf.getText() + "', '" + name + "', '"
								+ MessageReading.name + "', '" + Login.idTf.getText() + "', '" + Reading.id + "', '"
								+ time + "', '" + url + "', '" + writingTa.getText() + "')" );
						//쪽지 정보를 DB에 저장
						
						JOptionPane.showMessageDialog(null, "쪽지를 보냈습니다.", "쪽지 전송 완료", 
								JOptionPane.INFORMATION_MESSAGE);
						//쪽지 전송 완료 정보창 띄움
						
					} else if(MessageReading.messageReading.equals("MessageReading")) {
						//답장으로 쪽지를 보냈으면
						DB.update("insert into message values ('" + writingTf.getText() + "', '" + name + "', '"
								+ MessageReading.name + "', '" + Login.idTf.getText() + "', '" + MessageReading.sendid + "', '"
								+ time + "', '" + url + "', '" + writingTa.getText() + "')" );
						//쪽지 정보를 DB에 저장
						
						JOptionPane.showMessageDialog(null, "쪽지를 보냈습니다.", "쪽지 전송 완료", 
								JOptionPane.INFORMATION_MESSAGE);
						//쪽지 전송 완료 정보창 띄움
					}
					
					dispose();
				}
		} else if(obj == cancleBtn) {
			dispose();
			//현재 창만 종료
		}
	}
}
