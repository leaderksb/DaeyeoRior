package project.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import project.db.DB;

/**
 * 회원 프로필의 기본 이미지들을 선택해줌
 * 선택하는 즉시 회원 프로필이 바뀌며,
 * 바뀐 회원의 이미지 인덱스 값이 DB에 저장됨
 * 저장된 이미지 인덱스 값이 초기 로그인 후
 * 보여지는 이미지 값으로 설정됨
 */

public class Profile extends JFrame implements ItemListener {
	
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
	private JRadioButton blackM1, blackM2, blackW1, blackW2, blackW3, blueW, brownM, greenW, pinkW, redM, yellowW1, yellowW2;
	private JLabel imgL;
	private Font rollImgFont;
	
	public Profile(String setTitle, int width, int height) {
		
		imgL = new JLabel(new ImageIcon("images/basic2.png"), JLabel.CENTER);
		JPanel center = new JPanel();
		
		getContentPane().setBackground(Color.WHITE);
		center.setBackground(Color.WHITE);
		
		rollImgFont = new Font("a뉴굴림2", Font.PLAIN, 14);

		ButtonGroup profileBg = new ButtonGroup();
		//버튼 그룹생성
		blackM1 = new JRadioButton("금강 남성");
		blackM2 = new JRadioButton("마린 남성");
		blackW1 = new JRadioButton("비취 여성");
		blackW2 = new JRadioButton("흑요 여성");
		blackW3 = new JRadioButton("수정 여성");
		blueW = new JRadioButton("파즈 여성");
		brownM = new JRadioButton("진주 남성");
		greenW = new JRadioButton("페리 여성");
		pinkW = new JRadioButton("가넷 여성");
		redM = new JRadioButton("루비 남성");
		yellowW1 = new JRadioButton("오팔 여성");
		yellowW2 = new JRadioButton("호박 여성");
		
		blackM1.setBackground(Color.WHITE);
		blackM2.setBackground(Color.WHITE);
		blackW1.setBackground(Color.WHITE);
		blackW2.setBackground(Color.WHITE);
		blackW3.setBackground(Color.WHITE);
		blueW.setBackground(Color.WHITE);
		brownM.setBackground(Color.WHITE);
		greenW.setBackground(Color.WHITE);
		pinkW.setBackground(Color.WHITE);
		redM.setBackground(Color.WHITE);
		yellowW1.setBackground(Color.WHITE);
		yellowW2.setBackground(Color.WHITE);
		
		blackM1.setFont(rollImgFont);
		blackM2.setFont(rollImgFont);
		blackW1.setFont(rollImgFont);
		blackW2.setFont(rollImgFont);
		blackW3.setFont(rollImgFont);
		blueW.setFont(rollImgFont);
		brownM.setFont(rollImgFont);
		greenW.setFont(rollImgFont);
		pinkW.setFont(rollImgFont);
		redM.setFont(rollImgFont);
		yellowW1.setFont(rollImgFont);
		yellowW2.setFont(rollImgFont);
		
		blackM1.addItemListener(this);
		blackM2.addItemListener(this);
		blackW1.addItemListener(this);
		blackW2.addItemListener(this);
		blackW3.addItemListener(this);
		blueW.addItemListener(this);
		brownM.addItemListener(this);
		greenW.addItemListener(this);
		pinkW.addItemListener(this);
		redM.addItemListener(this);
		yellowW1.addItemListener(this);
		yellowW2.addItemListener(this);
		
		profileBg.add(blackM1);
		profileBg.add(blackM2);
		profileBg.add(blackW1);
		profileBg.add(blackW2);
		profileBg.add(blackW3);
		profileBg.add(blueW);
		profileBg.add(brownM);
		profileBg.add(greenW);
		profileBg.add(pinkW);
		profileBg.add(redM);
		profileBg.add(yellowW1);
		profileBg.add(yellowW2);
		
		center.add(blackM1);
		center.add(blackM2);
		center.add(blackW1);
		center.add(blackW2);
		center.add(blackW3);
		center.add(blueW);
		center.add(brownM);
		center.add(greenW);
		center.add(pinkW);
		center.add(redM);
		center.add(yellowW1);
		center.add(yellowW2);
		
		add(center, BorderLayout.CENTER);
		add(imgL, BorderLayout.SOUTH);
		
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
	public void itemStateChanged(ItemEvent e) {
		if(blackM1.isSelected()) {
			imgL.setIcon(imgPik[0]);
			Member.profileBtn.setIcon(imgPik[0]);
			//현재 선택된 이미지로 설정
			DB.update("update information "
					+ "set img = 0 where id = '" + Login.idTf.getText() + "'");
			//현재 선택된 이미지 인덱스 값 DB에 저장
		} else if(blackM2.isSelected()) {
			imgL.setIcon(imgPik[1]);
			Member.profileBtn.setIcon(imgPik[1]);
			//현재 선택된 이미지로 설정
			DB.update("update information "
					+ "set img = 1 where id = '" + Login.idTf.getText() + "'");
			//현재 선택된 이미지 인덱스 값 DB에 저장
		} else if(blackW1.isSelected()) {
			imgL.setIcon(imgPik[2]);
			Member.profileBtn.setIcon(imgPik[2]);
			//현재 선택된 이미지로 설정
			DB.update("update information "
					+ "set img = 2 where id = '" + Login.idTf.getText() + "'");
			//현재 선택된 이미지 인덱스 값 DB에 저장
		} else if(blackW2.isSelected()) {
			imgL.setIcon(imgPik[3]);
			Member.profileBtn.setIcon(imgPik[3]);
			//현재 선택된 이미지로 설정
			DB.update("update information "
					+ "set img = 3 where id = '" + Login.idTf.getText() + "'");
			//현재 선택된 이미지 인덱스 값 DB에 저장
		} else if(blackW3.isSelected()) {
			imgL.setIcon(imgPik[4]);
			Member.profileBtn.setIcon(imgPik[4]);
			//현재 선택된 이미지로 설정
			DB.update("update information "
					+ "set img = 4 where id = '" + Login.idTf.getText() + "'");
			//현재 선택된 이미지 인덱스 값 DB에 저장
		} else if(blueW.isSelected()) {
			imgL.setIcon(imgPik[5]);
			Member.profileBtn.setIcon(imgPik[5]);
			//현재 선택된 이미지로 설정
			DB.update("update information "
					+ "set img = 5 where id = '" + Login.idTf.getText() + "'");
			//현재 선택된 이미지 인덱스 값 DB에 저장
		} else if(brownM.isSelected()) {
			imgL.setIcon(imgPik[6]);
			Member.profileBtn.setIcon(imgPik[6]);
			//현재 선택된 이미지로 설정
			DB.update("update information "
					+ "set img = 6 where id = '" + Login.idTf.getText() + "'");
			//현재 선택된 이미지 인덱스 값 DB에 저장
		} else if(greenW.isSelected()) {
			imgL.setIcon(imgPik[7]);
			Member.profileBtn.setIcon(imgPik[7]);
			//현재 선택된 이미지로 설정
			DB.update("update information "
					+ "set img = 7 where id = '" + Login.idTf.getText() + "'");
			//현재 선택된 이미지 인덱스 값 DB에 저장
		} else if(pinkW.isSelected()) {
			imgL.setIcon(imgPik[8]);
			Member.profileBtn.setIcon(imgPik[8]);
			//현재 선택된 이미지로 설정
			DB.update("update information "
					+ "set img = 8 where id = '" + Login.idTf.getText() + "'");
			//현재 선택된 이미지 인덱스 값 DB에 저장
		} else if(redM.isSelected()) {
			imgL.setIcon(imgPik[9]);
			Member.profileBtn.setIcon(imgPik[9]);
			//현재 선택된 이미지로 설정
			DB.update("update information "
					+ "set img = 9 where id = '" + Login.idTf.getText() + "'");
			//현재 선택된 이미지 인덱스 값 DB에 저장
		} else if(yellowW1.isSelected()) {
			imgL.setIcon(imgPik[10]);
			Member.profileBtn.setIcon(imgPik[10]);
			//현재 선택된 이미지로 설정
			DB.update("update information "
					+ "set img = 10 where id = '" + Login.idTf.getText() + "'");
			//현재 선택된 이미지 인덱스 값 DB에 저장
		} else if(yellowW2.isSelected()) {
			imgL.setIcon(imgPik[11]);
			Member.profileBtn.setIcon(imgPik[11]);
			//현재 선택된 이미지로 설정
			DB.update("update information "
					+ "set img = 11 where id = '" + Login.idTf.getText() + "'");
			//현재 선택된 이미지 인덱스 값 DB에 저장
		}
		
	}
}
