package project.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
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
 * ȸ������ ������ SQL�� ����
 * �̸��� ���� �Ǵ� �ѱ۷� ����, 10������
 * ���̵�� ���� ���� ����, �ߺ��� �����, 10������
 * ��й�ȣ�� ���� ���� ����, 8���̻� 12������
 * ��ȭ��ȣ�� ���ڷ� ����, 12������
 */

public class SignUp extends JFrame implements ActionListener, MouseListener {
	
	private JTextField nameTf, idTf, phoneTf;
	private JPasswordField pwTf;
	private JButton okBtn, cancleBtn;
	private Font rollLabFont, rollTfFont, balBtnFont;
	private Color colorMint, colorDownMint;

	public SignUp(String setTitle, int width, int height) {
		
		JPanel panN = new JPanel();
		JPanel panS = new JPanel();
		
		((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		//����Ʈ���ο� ���ΰ��� ����
		
		colorMint = new Color(133, 197, 196);
		colorDownMint = new Color(109, 180, 176);

		((JComponent) getContentPane()).setBackground(Color.WHITE);
		panN.setBackground(Color.WHITE);
		panS.setBackground(Color.WHITE);
		
		rollLabFont = new Font("a������3", Font.PLAIN, 15);
		rollTfFont = new Font("a������2", Font.PLAIN, 15);
		balBtnFont = new Font("a�߷�����", Font.PLAIN, 16);
		
		panN.setLayout(new GridLayout(5, 2));
		
		JLabel nameL = new JLabel("�̸�");
		JLabel idL = new JLabel("���̵�");
		JLabel pwL = new JLabel("��й�ȣ");
		JLabel phoneL = new JLabel("��ȭ��ȣ");
		
		nameL.setFont(rollLabFont);
		idL.setFont(rollLabFont);
		pwL.setFont(rollLabFont);
		phoneL.setFont(rollLabFont);
		
		nameTf = new JTextField(10);
		idTf = new JTextField(10);
		pwTf = new JPasswordField(10);
		phoneTf = new JTextField(10);
		
		nameTf.setFont(rollTfFont);
		idTf.setFont(rollTfFont);
		pwTf.setFont(rollTfFont);
		phoneTf.setFont(rollTfFont);
		
		nameTf.setToolTipText("�̸��� ���� �Ǵ� �ѱ۷� ����, 10������");
		idTf.setToolTipText("���̵�� ���� ���� ����, �ߺ��� �����, 10������");
		pwTf.setToolTipText("��й�ȣ�� ���� ���� ����, 8���̻� 12������");
		phoneTf.setToolTipText("��ȭ��ȣ�� ���ڷ� ����, 12������");
		
		okBtn = new JButton("ȸ������");
		okBtn.setForeground(colorMint);
		okBtn.setFont(balBtnFont);
		okBtn.setBorderPainted(false);
		//��ư �׵θ� ����
		okBtn.setContentAreaFilled(false);
		//��ư ä��� ����
		okBtn.setFocusPainted(false);
		//��ư ���ý� ����� �׵θ� ����
		
		okBtn.addActionListener(this);
		okBtn.addMouseListener(this);
		
		cancleBtn = new JButton("���");
		cancleBtn.setForeground(colorMint);
		cancleBtn.setFont(balBtnFont);
		cancleBtn.setBorderPainted(false);
		//��ư �׵θ� ����
		cancleBtn.setContentAreaFilled(false);
		//��ư ä��� ����
		cancleBtn.setFocusPainted(false);
		//��ư ���ý� ����� �׵θ� ����
		
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
		//���� ����
		setSize(width, height);
		//ũ�� ����
		setResizable(false);
		//���Ƿ� ũ������ �Ұ�
		setLocationRelativeTo(this);
		//�߾� ��ġ
		setVisible(true);
		//â ����
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == okBtn) {
			int result = JOptionPane.showConfirmDialog(null, "�Է��Ͻ� ������ ȸ������ �Ͻðڽ��ϱ�?", "ȸ������ ����",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			//���̾�α� ����â
			if(result == JOptionPane.OK_OPTION) {
				validation();
			}else if(result == JOptionPane.CANCEL_OPTION) {
				dispose();
				//���� â�� ��������
			}
		} else if(obj == cancleBtn) {
			dispose();
			//���� â�� ��������
		}
	}

	private void validation() {
		if(chkEngAndKor(nameTf.getText()) == true && nameTf.getText().length() > 0 && nameTf.getText().length() <= 10) {
			//�Էµ� ���� �ְ� �Էµ� ���� 10�����ϸ�
			if(chkEngAndNum(idTf.getText()) == true && idTf.getText().length() > 0 && idTf.getText().length() <= 10) {
				//�Էµ� ���� �ְ� ���̵� �ߺ��� ������ �Էµ� ���� 10�����ϸ�
				if(chkEngAndNum(pwTf.getText()) == true && pwTf.getText().length() >= 8 && pwTf.getText().length() <= 12) {
					//�Էµ� ���� 8���̻� 12���ϸ�
					if(chkOnlyNum(phoneTf.getText()) == true && phoneTf.getText().length() > 0 && phoneTf.getText().length() <= 12) {
						//�Էµ� ���� �ְ� �Էµ� ���� 12�����ϸ�
						//DB ������ �־���
						if (chkId(idTf.getText()) == true) {
							String sql = "insert into information values ('member', '"
								+ nameTf.getText() + "', '" + idTf.getText() + "', '" + pwTf.getText() + "', '" + phoneTf.getText() + "', null)";
							DB.update(sql);
							
							//��� if���� ����ߴٸ�
							JOptionPane.showMessageDialog(null, "ȸ�������� �Ϸ�Ǿ����ϴ�.", "ȸ������ �Ϸ�", 
									JOptionPane.INFORMATION_MESSAGE);
							//ȸ������ �Ϸ� ����â ���
							dispose();
						} else if(chkId(idTf.getText()) == false){
							JOptionPane.showMessageDialog(null, "�̹� ����ϰ� �ִ� ���̵��Դϴ�!", "���̵� �ߺ�", 
									JOptionPane.WARNING_MESSAGE);
							//�ߺ� ���̵� ���â
							nameTf.setText("");
							idTf.setText("");
							pwTf.setText("");
							phoneTf.setText("");
							nameTf.requestFocus();
						}
					} else if(chkOnlyNum(phoneTf.getText()) == false || phoneTf.getText().length() < 0 || phoneTf.getText().length() > 12) {
						JOptionPane.showMessageDialog(null, "��ȭ��ȣ�� ���ڷ� ����, 12������", "�ٽýõ� �ϼ���!", 
								JOptionPane.INFORMATION_MESSAGE);
						//��ȭ��ȣ ������ �ùٸ��� ������ ����â�� ���
						}
				} else if(chkEngAndNum(pwTf.getText()) == false || pwTf.getText().length() < 8 || pwTf.getText().length() > 12) {
					JOptionPane.showMessageDialog(null, "��й�ȣ�� ���� ���� ����, 8���̻� 12������", "�ٽýõ� �ϼ���!", 
							JOptionPane.INFORMATION_MESSAGE);
					//��й�ȣ ������ �ùٸ��� ������ ����â�� ���
					}
			} else if(chkEngAndNum(idTf.getText()) == false || idTf.getText().length() < 0 || idTf.getText().length() > 10) {
				JOptionPane.showMessageDialog(null, "���̵�� ���� ���� ����, �ߺ��� �����, 10������", "�ٽýõ� �ϼ���!", 
						JOptionPane.INFORMATION_MESSAGE);
				//���̵� ������ �ùٸ��� ������ ����â�� ���
				}
		} else if(chkEngAndKor(nameTf.getText()) == false || nameTf.getText().length() < 0 || nameTf.getText().length() > 10) {
			JOptionPane.showMessageDialog(null, "�̸��� ���� �Ǵ� �ѱ۷� ����, 10������", "�ٽýõ� �ϼ���!", 
					JOptionPane.INFORMATION_MESSAGE);
			//�̸� ������ �ùٸ��� ������ ����â�� ���
		}
	}
	
	public boolean chkOnlyNum(String input) {
		//���� �˻�
		return Pattern.matches("^[0-9]*$", input);
	}
	
	public boolean chkOnlyEng(String input) {
		//������ �˻�
		return Pattern.matches("^[a-zA-Z]*$", input);
	}
	
	public boolean chkEngAndNum(String input) {
		//������ �Ǵ� ���� �˻�
		return Pattern.matches("^[0-9a-zA-Z]*$", input);
	}
	
	public boolean chkEngAndKor(String input) {
		//������ �Ǵ� �ѱ� �˻�
		return Pattern.matches("^[a-zA-Z��-�R]*$", input);
	}
	
	public boolean chkId(String id) {
		//���̵� �ߺ� �˻�
		boolean chk = true;
		ResultSet count = DB.select("select count(id) from information " + 
				"where id = '" + id + "'");
		
		try {
			if (count.next()) {
				if(count.getInt(1) == 1) {
					chk = false;
				} else {
					chk = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chk;
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		//���콺�� �ش� ������Ʈ ���� ������ ������
		if(okBtn == e.getComponent()) {
			okBtn.setForeground(colorDownMint);
		} else if(cancleBtn == e.getComponent()) {
			cancleBtn.setForeground(colorDownMint);
		}
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		//���콺�� �ش� ������Ʈ ���� ������ ������
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
