package project.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import project.db.DB;

/**
 * È¸¿ø°¡ÀÔ Á¤º¸¸¦ SQL°ú ¿¬µ¿
 * ¾ÆÀÌµð °ª ¿Ü¿¡ ´Ù¸¥ Á¤º¸¸¸ ¼öÁ¤°¡´É
 */

public class InfoRetouch extends JFrame implements ActionListener, MouseListener {
	private JLabel idTf;
	private JTextField nameTf, phoneTf;
	private JPasswordField pwTf;
	private JButton okBtn, cancleBtn;
	private Font rollLabFont, rollTfFont, balBtnFont;
	private Color colorMint, colorDownMint;

	public InfoRetouch(String setTitle, int width, int height) {
		
		JPanel panN = new JPanel();
		JPanel panS = new JPanel();
		
		((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		//ÄÁÅÙÆ®ÆäÀÎ¿¡ ³»ºÎ°ø¹é ¼³Á¤
		
		colorMint = new Color(133, 197, 196);
		colorDownMint = new Color(109, 180, 176);
		
		((JComponent) getContentPane()).setBackground(Color.WHITE);
		panN.setBackground(Color.WHITE);
		panS.setBackground(Color.WHITE);
		
		rollLabFont = new Font("a´º±¼¸²3", Font.PLAIN, 15);
		rollTfFont = new Font("a´º±¼¸²2", Font.PLAIN, 15);
		balBtnFont = new Font("a¹ß·¹¸®³ë", Font.PLAIN, 16);
		
		panN.setLayout(new GridLayout(5, 2));
		
		JLabel nameL = new JLabel("ÀÌ¸§");
		JLabel idL = new JLabel("¾ÆÀÌµð");
		JLabel pwL = new JLabel("ºñ¹Ð¹øÈ£");
		JLabel phoneL = new JLabel("ÀüÈ­¹øÈ£");
		
		nameL.setFont(rollLabFont);
		idL.setFont(rollLabFont);
		pwL.setFont(rollLabFont);
		phoneL.setFont(rollLabFont);
		
		nameTf = new JTextField(10);
		idTf = new JLabel(Login.idTf.getText());
		//»ç¿ëÀÚ ¾ÆÀÌµð °ª ¼öÁ¤ºÒ°¡
		pwTf = new JPasswordField(10);
		phoneTf = new JTextField(10);
		
		nameTf.setFont(rollTfFont);
		idTf.setFont(rollTfFont);
		pwTf.setFont(rollTfFont);
		phoneTf.setFont(rollTfFont);
		
		nameTf.setToolTipText("ÀÌ¸§Àº ¿µ¹® ¶Ç´Â ÇÑ±Û Á¦ÇÑ, 10ÀÚÀÌÇÏ");
		idTf.setToolTipText("¾ÆÀÌµð´Â ¼öÁ¤ºÒ°¡");
		pwTf.setToolTipText("ºñ¹Ð¹øÈ£´Â ¿µ¹® ¼ýÀÚ Á¶ÇÕ, 8ÀÚÀÌ»ó 12ÀÚÀÌÇÏ");
		phoneTf.setToolTipText("ÀüÈ­¹øÈ£´Â ¼ýÀÚ·Î Á¦ÇÑ, 12ÀÚÀÌÇÏ");

		okBtn = new JButton("Á¤º¸¼öÁ¤");
		okBtn.setForeground(colorMint);
		okBtn.setFont(balBtnFont);
		okBtn.setBorderPainted(false);
		//¹öÆ° Å×µÎ¸® Á¦°Å
		okBtn.setContentAreaFilled(false);
		//¹öÆ° Ã¤¿ì±â Á¦°Å
		okBtn.setFocusPainted(false);
		//¹öÆ° ¼±ÅÃ½Ã »ý±â´Â Å×µÎ¸® Á¦°Å
		
		okBtn.addActionListener(this);
		okBtn.addMouseListener(this);
		
		cancleBtn = new JButton("Ãë¼Ò");
		cancleBtn.setForeground(colorMint);
		cancleBtn.setFont(balBtnFont);
		cancleBtn.setBorderPainted(false);
		//¹öÆ° Å×µÎ¸® Á¦°Å
		cancleBtn.setContentAreaFilled(false);
		//¹öÆ° Ã¤¿ì±â Á¦°Å
		cancleBtn.setFocusPainted(false);
		//¹öÆ° ¼±ÅÃ½Ã »ý±â´Â Å×µÎ¸® Á¦°Å
		
		cancleBtn.addActionListener(this);
		cancleBtn.addMouseListener(this);
		
		panN.add(nameL);
		panN.add(nameTf);
		panN.add(idL);
		panN.add(idTf);
		panN.add(pwL);
		panN.add(pwTf);
		panN.add(phoneL);
		panN.add(phoneTf);
		panS.add(okBtn);
		panS.add(cancleBtn);
		
		add(panN, BorderLayout.NORTH);
		add(panS, BorderLayout.SOUTH);
		
		setTitle(setTitle);
		//Á¦¸ñ ¼³Á¤
		setSize(width, height);
		//Å©±â ¼³Á¤
		setResizable(false);
		//ÀÓÀÇ·Î Å©±âÁ¶Àý ºÒ°¡
		setLocationRelativeTo(this);
		//Áß¾Ó ¹èÄ¡
		setVisible(true);
		//Ã¢ ¶ç¿ì±â
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == okBtn) {
			int result = JOptionPane.showConfirmDialog(null, "ÀÔ·ÂÇÏ½Å Á¤º¸·Î Á¤º¸¼öÁ¤ ÇÏ½Ã°Ú½À´Ï±î?", "È¸¿ø°¡ÀÔ ¿©ºÎ",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			//´ÙÀÌ¾ó·Î±× ÄÁÆûÃ¢
			if(result == JOptionPane.OK_OPTION) {
				validation();
			}else if(result == JOptionPane.CANCEL_OPTION) {
				dispose();
				//ÇöÀç Ã¢¸¸ Á¤»óÁ¾·á
			}
		} else if(obj == cancleBtn) {
			dispose();
			//ÇöÀç Ã¢¸¸ Á¤»óÁ¾·á
		}
	}

	private void validation() {
		if(chkEngAndKor(nameTf.getText()) == true && nameTf.getText().length() > 0 && nameTf.getText().length() <= 10) {
			//ÀÔ·ÂµÈ °ªÀÌ ÀÖ°í ÀÔ·ÂµÈ °ªÀÌ 10ÀÚÀÌÇÏ¸é
				if(chkEngAndNum(pwTf.getText()) == true && pwTf.getText().length() >= 8 && pwTf.getText().length() <= 12) {
					//ÀÔ·ÂµÈ °ªÀÌ 8ÀÚÀÌ»ó 12ÀÌÇÏ¸é
					if(chkOnlyNum(phoneTf.getText()) == true && phoneTf.getText().length() > 0 && phoneTf.getText().length() <= 12) {
						//ÀÔ·ÂµÈ °ªÀÌ ÀÖ°í ÀÔ·ÂµÈ °ªÀÌ 12ÀÚÀÌÇÏ¸é
						//DB µ¥ÀÌÅÍ ³Ö¾îÁÜ
							DB.update("update information set name = '" + nameTf.getText() + "', pw = '" + pwTf.getText()
									+ "', phone = '" + phoneTf.getText() + "'" + "where id = '" + Login.idTf.getText() + "'");
							//¸ðµç if¹®À» Åë°úÇß´Ù¸é
							JOptionPane.showMessageDialog(null, "Á¤º¸¼öÁ¤ÀÌ ¿Ï·áµÇ¾ú½À´Ï´Ù.", "Á¤º¸¼öÁ¤ ¿Ï·á", 
									JOptionPane.INFORMATION_MESSAGE);
							//Á¤º¸¼öÁ¤ ¿Ï·á Á¤º¸Ã¢ ¶ç¿ò
							dispose();
					} else if(chkOnlyNum(phoneTf.getText()) == false || phoneTf.getText().length() < 0 || phoneTf.getText().length() > 12) {
						JOptionPane.showMessageDialog(null, "ÀüÈ­¹øÈ£´Â ¼ýÀÚ·Î Á¦ÇÑ, 12ÀÚÀÌÇÏ", "´Ù½Ã½Ãµµ ÇÏ¼¼¿ä!", 
								JOptionPane.INFORMATION_MESSAGE);
						//ÀüÈ­¹øÈ£ Á¤º¸°¡ ¿Ã¹Ù¸£Áö ¾ÊÀ¸¸é Á¤º¸Ã¢À» ¶ç¿ò
						}
				} else if(chkEngAndNum(pwTf.getText()) == false || pwTf.getText().length() < 8 || pwTf.getText().length() > 12) {
					JOptionPane.showMessageDialog(null, "ºñ¹Ð¹øÈ£´Â ¿µ¹® ¼ýÀÚ Á¶ÇÕ, 8ÀÚÀÌ»ó 12ÀÚÀÌÇÏ", "´Ù½Ã½Ãµµ ÇÏ¼¼¿ä!", 
							JOptionPane.INFORMATION_MESSAGE);
					//ºñ¹Ð¹øÈ£ Á¤º¸°¡ ¿Ã¹Ù¸£Áö ¾ÊÀ¸¸é Á¤º¸Ã¢À» ¶ç¿ò
					}
		} else if(chkEngAndKor(nameTf.getText()) == false || nameTf.getText().length() < 0 || nameTf.getText().length() > 10) {
			JOptionPane.showMessageDialog(null, "ÀÌ¸§Àº ¿µ¹® ¶Ç´Â ÇÑ±Û·Î Á¦ÇÑ, 10ÀÚÀÌÇÏ", "´Ù½Ã½Ãµµ ÇÏ¼¼¿ä!", 
					JOptionPane.INFORMATION_MESSAGE);
			//ÀÌ¸§ Á¤º¸°¡ ¿Ã¹Ù¸£Áö ¾ÊÀ¸¸é Á¤º¸Ã¢À» ¶ç¿ò
		}
	}
	
	public boolean chkOnlyNum(String input) {
		//¼ýÀÚ °Ë»ç
		return Pattern.matches("^[0-9]*$", input);
	}
	
	public boolean chkOnlyEng(String input) {
		//¿µ¹®ÀÚ °Ë»ç
		return Pattern.matches("^[a-zA-Z]*$", input);
	}
	
	public boolean chkEngAndNum(String input) {
		//¿µ¹®ÀÚ ¶Ç´Â ¼ýÀÚ °Ë»ç
		return Pattern.matches("^[0-9a-zA-Z]*$", input);
	}
	
	public boolean chkEngAndKor(String input) {
		//¿µ¹®ÀÚ ¶Ç´Â ÇÑ±Û °Ë»ç
		return Pattern.matches("^[a-zA-Z°¡-ÆR]*$", input);
	}
	

	@Override
	public void mouseEntered(MouseEvent e) {
		//¸¶¿ì½º°¡ ÇØ´ç ÄÄÆ÷³ÍÆ® ¿µ¿ª ¾ÈÀ¸·Î µé¾î¿À¸é
		if(okBtn == e.getComponent()) {
			okBtn.setForeground(colorDownMint);
		} else if(cancleBtn == e.getComponent()) {
			cancleBtn.setForeground(colorDownMint);
		}
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		//¸¶¿ì½º°¡ ÇØ´ç ÄÄÆ÷³ÍÆ® ¿µ¿ª ¹ÛÀ¸·Î ³ª°¡¸é
		if(okBtn == e.getComponent()) {
			okBtn.setForeground(colorMint);
		} else if(cancleBtn == e.getComponent()) {
			cancleBtn.setForeground(colorMint);
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

}
