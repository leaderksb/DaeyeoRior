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
		
		rollTitleFont = new Font("a������3", Font.PLAIN, 20);
		rollMainFont = new Font("a������2", Font.PLAIN, 18);
		balBtnFont = new Font("a�߷�����", Font.PLAIN, 16);
		
		panC.setLayout(new BorderLayout());
		center1.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
		panC.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));
		panS.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

		JLabel title = new JLabel("���� : ");
		
		title.setFont(rollTitleFont);
		
		writingTf = new JTextField(25);
		writingTa = new JTextArea();
		
		writingTf.setFont(rollTitleFont);
		writingTa.setFont(rollMainFont);
		writingTa.setLineWrap(true);
		//������ �����ٷ� ������
		
		okBtn = new JButton("������");
		okBtn.setForeground(colorMint);
		okBtn.setFont(balBtnFont);
		okBtn.setBorderPainted(false);
		//��ư �׵θ� ����
		okBtn.setContentAreaFilled(false);
		//��ư ä��� ����
		okBtn.setFocusPainted(false);
		//��ư ���ý� ����� �׵θ� ����
		
		cancleBtn = new JButton("���");
		cancleBtn.setForeground(colorMint);
		cancleBtn.setFont(balBtnFont);
		cancleBtn.setBorderPainted(false);
		//��ư �׵θ� ����
		cancleBtn.setContentAreaFilled(false);
		//��ư ä��� ����
		cancleBtn.setFocusPainted(false);
		//��ư ���ý� ����� �׵θ� ����
		
		okBtn.addActionListener(this);
		cancleBtn.addActionListener(this);
		
		panScroll = new JScrollPane(writingTa, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
		//��ũ������ ����
		panScroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		//��ũ�ѹٸ� �����ͼ� ����� 0���� ����

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
				//����ð�
				
				SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				//��-��-�Ϸ� ���ĺ�ȯ
				
				String time = dayFormat.format(date);
				//�ۼ��ð�
				
				SimpleDateFormat urlFormat = new SimpleDateFormat("yyMMddHHmmss");
				
				String url = Login.idTf.getText() + urlFormat.format(date);
				
				if(writingTf.getText().length() > 0 && writingTa.getText().length() > 0) {
					//����� ���뿡 �Էµ� ���� �ִٸ�
				
					if(Reading.reading.equals("Reading")) {
						//�Խñۿ��� ������ ��������
						DB.update("insert into message values ('" + writingTf.getText() + "', '" + name + "', '"
								+ MessageReading.name + "', '" + Login.idTf.getText() + "', '" + Reading.id + "', '"
								+ time + "', '" + url + "', '" + writingTa.getText() + "')" );
						//���� ������ DB�� ����
						
						JOptionPane.showMessageDialog(null, "������ ���½��ϴ�.", "���� ���� �Ϸ�", 
								JOptionPane.INFORMATION_MESSAGE);
						//���� ���� �Ϸ� ����â ���
						
					} else if(MessageReading.messageReading.equals("MessageReading")) {
						//�������� ������ ��������
						DB.update("insert into message values ('" + writingTf.getText() + "', '" + name + "', '"
								+ MessageReading.name + "', '" + Login.idTf.getText() + "', '" + MessageReading.sendid + "', '"
								+ time + "', '" + url + "', '" + writingTa.getText() + "')" );
						//���� ������ DB�� ����
						
						JOptionPane.showMessageDialog(null, "������ ���½��ϴ�.", "���� ���� �Ϸ�", 
								JOptionPane.INFORMATION_MESSAGE);
						//���� ���� �Ϸ� ����â ���
					}
					
					dispose();
				}
		} else if(obj == cancleBtn) {
			dispose();
			//���� â�� ����
		}
	}
}
