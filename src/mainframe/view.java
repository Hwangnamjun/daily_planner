package mainframe;

import java.awt.Container;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class view extends JFrame{
	
	public static void main(String[] args) {
		new view();
	}
	
	
	public view() 
	{
		Container c1 = this.getContentPane();
		Panel p1 = new Panel();
		p1.setLayout(null);
		JButton btn1 = new JButton("오늘자 데이터 생성");
		JButton btn2 = new JButton("생성 데이터 확인");

		btn1.setBounds(125, 250, 150, 50);
		btn2.setBounds(300, 250, 150, 50);

		c1.add(p1);
		p1.add(btn1);
		p1.add(btn2);
		
		setSize(600, 400);
		setLocationRelativeTo(null);
		setTitle("일계표");
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new settingView();
			}
		});
	}
}













