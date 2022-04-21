package mainframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import bean.TD005_bean;

public class settingView extends JFrame{


	int  length = 6;
	JPanel[] arrayPanel = new JPanel[length];
	String[] textMessage = new String[]
			{
					"진도율 월 목표","노브랜드 휴무여부","주차차량대수","백화점 행사","동업계 테마","전체 매출 차트 활성화"
			};
	JCheckBox cb1 = new JCheckBox();
	JCheckBox cb2 = new JCheckBox();
	JTextField tf1_1 = new JTextField();
	JTextField tf1_2 = new JTextField();
	JTextField tf1_3 = new JTextField();
	JTextField tf2_1 = new JTextField();
	JTextField tf2_2 = new JTextField();
	JTextField tf3_1 = new JTextField();
	JTextField tf3_2 = new JTextField();
	JTextField tf4_1 = new JTextField();
	JTextField tf4_2 = new JTextField();
	JTextField tf5_1 = new JTextField();
	JTextField tf5_2 = new JTextField();
	JLabel jtext = new JLabel("전년도일자 ex)20210101");
	JLabel startDT = new JLabel("시작일자");
	JLabel endDT = new JLabel("종료일자");

	Panel p2 = new Panel();
	Panel p3 = new Panel();
	Panel p8 = new Panel();
	Panel p9 = new Panel();
	Container c2 = this.getContentPane();
	boolean status = true;
	
	public static void main(String[] args) {
		new settingView();
	}

	public settingView(){

		c2.setLayout(new BorderLayout());
		p2.setLayout(new GridLayout(0,1));
		c2.add(p2,BorderLayout.CENTER);
		c2.add(p3,BorderLayout.SOUTH);

		for (int i = 0; i < length; i++) 
		{
			arrayPanel[i] = new JPanel();
			arrayPanel[i].setBorder(new LineBorder(Color.GREEN,1));
			arrayPanel[i].setLayout(new GridLayout());
			arrayPanel[i].add(new JLabel(textMessage[i]));
			if(i == 0)
			{
				Panel p4 = new Panel();
				p4.setLayout(new GridLayout(0,1));
				p4.add(new JLabel("백화점"));
				p4.add(tf1_1);
				p4.add(new JLabel("남창원"));
				p4.add(tf1_2);
				p4.add(new JLabel("금곡점"));
				p4.add(tf1_3);
				arrayPanel[i].add(p4);
			}
			else if(i == 1)
			{
				arrayPanel[i].add(cb1);
			}
			else if(i == 2)
			{
				Panel p5 = new Panel();
				p5.setLayout(new GridLayout(0,1));
				p5.add(new JLabel("전년"));
				p5.add(tf2_1);
				p5.add(new JLabel("금년"));
				p5.add(tf2_2);
				arrayPanel[i].add(p5);	
			}
			else if(i == 3)
			{
				Panel p6 = new Panel();
				p6.setLayout(new GridLayout(0,1));
				p6.add(new JLabel("행사명"));
				p6.add(tf3_1);
				p6.add(new JLabel("기간"));
				p6.add(tf3_2);
				arrayPanel[i].add(p6);
			}
			else if(i == 4)
			{
				Panel p7 = new Panel();
				p7.setLayout(new GridLayout(0,1));
				p7.add(new JLabel("행사명"));
				p7.add(tf4_1);
				p7.add(new JLabel("기간"));
				p7.add(tf4_2);
				arrayPanel[i].add(p7);
			}
			else if(i == 5)
			{
				arrayPanel[i].add(cb2);
			}
			p2.add(arrayPanel[i]);
		}
		
		cb2.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				 ischecked(e.getStateChange());
			}});
		
		JButton btn3 = new JButton();
		btn3.setText("확인");
		p3.add(btn3);
		
		btn3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				TD005_bean tde = new TD005_bean();
				tde.setParking_d(tf2_1.getText());
				int a = new createData().datacheck(tde);
				String text = "";
				if(a == 1)
				{
					text = "작업성공";
				}
				else
				{
					text = "작업실패";
				}
				JOptionPane.showMessageDialog(null, text,"Message",JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
		});
		
		setTitle("설정");
		setSize(450,700);
		setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
	}
	
	public void ischecked(int check) {
		
		if(check == 1)
		{
			p8.setLayout(new GridLayout(0,1));
			p9.setLayout(new GridLayout(1,0));
			p8.add(jtext);
			p8.add(p9);
			p9.add(startDT);
			p9.add(tf5_1);
			p9.add(endDT);
			p9.add(tf5_2);
			p2.add(p8);
			c2.revalidate();
		}
		else
		{
			p2.remove(p8);
			p8.remove(p9);
			p9.remove(tf5_1);
			p9.remove(tf5_2);
			p9.remove(startDT);
			p9.remove(endDT);
			p8.remove(jtext);
			c2.revalidate();
		}
	}
}
