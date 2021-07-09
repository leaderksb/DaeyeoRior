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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import project.db.DB;

public class writingRetouch extends JFrame implements ActionListener {
	
	private String imgStr[] = { "이미지 선택", "가전제품", "화장실 용품", "침대", "책장", 
			"수납장", "의자", "옷장", "커튼", "책상", "행거", "거울", "소품", "소파", "식탁"};
	public static String name, id, address, reading = "null";
//	int img;
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
	private JComboBox<String> imgCb;
	private JLabel imgL;
	private JTextField writingTf;
	private JTextArea writingTa;
	private JButton writingRetouchBtn, cancleBtn;
	private JScrollPane panScroll;
	private Font rollTitleFont, rollMainFont, balBtnFont;
	private Color colorMint;

	public writingRetouch(String setTitle, int width, int height) {
		
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
		
		imgCb = new JComboBox<String>(imgStr);
		imgCb.addActionListener(this);
		
		ResultSet rs = DB.select("select * from shares where address = '"
				+ Member.comm + "' union select * from exchange where address = '" + Member.comm + "'");
		//컬럼의 개수와 컬럼의 데이터타입이 같은 두개의 테이블을 합쳐서 하나의 결과 도출
		
		try {
			if(rs.next()) {
				if(rs.getString(1) != null) {
					writingTf = new JTextField(rs.getString("title"));
//					img = rs.getInt("img");
					writingTa = new JTextArea(rs.getString("txt"));
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		JLabel title = new JLabel("제목 : ");
		
		title.setFont(rollTitleFont);
		
		imgL = new JLabel(imgPik[0]);
		
		writingTf.setFont(rollTitleFont);
		writingTa.setFont(rollMainFont);
		writingTa.setLineWrap(true);
		//꽉차면 다음줄로 가게함
		
		writingRetouchBtn  = new JButton("수정");
		writingRetouchBtn.setForeground(colorMint);
		writingRetouchBtn.setFont(balBtnFont);
		writingRetouchBtn.setBorderPainted(false);
		//버튼 테두리 제거
		writingRetouchBtn.setContentAreaFilled(false);
		//버튼 채우기 제거
		writingRetouchBtn.setFocusPainted(false);
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
		
		writingRetouchBtn.addActionListener(this);
		cancleBtn.addActionListener(this);
		
		panScroll = new JScrollPane(writingTa, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
		//스크롤페인을 선언
		panScroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		//스크롤바를 가져와서 사이즈를 0으로 설정
		
		center1.add(imgCb);
		center1.add(title);
		center1.add(writingTf);
		
		center2.add(imgL);

		panC.add(center1, BorderLayout.NORTH);
		panC.add(panScroll, BorderLayout.CENTER);
		panC.add(center2, BorderLayout.SOUTH);
		panS.add(writingRetouchBtn);
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
		
		if(obj == writingRetouchBtn) {
			//수정 완료 버튼이 눌리면
			
			if(writingTf.getText().length() > 0 && writingTa.getText().length() > 0) {
				//제목과 내용에 입력된 값이 있다면
			
				DB.update("update shares set title = '" + writingTf.getText() + "', img = '" 
						+ imgCb.getSelectedIndex() + "', txt = '" + writingTa.getText() +"'where address = '" + Member.comm + "'");
				DB.update("update exchange set title = '" + writingTf.getText() + "', img = '" 
						+ imgCb.getSelectedIndex() + "', txt = '" + writingTa.getText() +"' where address = '" + Member.comm + "'");
				//각각 테이블에서 현재 글 주소값을 검색하여 업데이트
					
				JOptionPane.showMessageDialog(null, "글이 수정 되었습니다. \n 새로고침 하세요.", "글수정 완료", 
						JOptionPane.INFORMATION_MESSAGE);
				//글수정 완료 정보창 띄움
				
				dispose();
				
				new Reading("글열람", 1200, 800);
				//글열람 창을 띄움
			}
		} else if(obj == imgCb) {
			int index = imgCb.getSelectedIndex();
			//콤보박스에서 선택한 인덱스 값을 가져옴
			imgL.setIcon(imgPik[index]);
			//선택한 인덱스 값의 이미지를 가져옴
		} else if(obj == cancleBtn) {
			dispose();
			//현재 창만 종료
			
			new Reading("글열람", 1200, 800);
			//글열람 창을 띄운다
		}
	}
}
