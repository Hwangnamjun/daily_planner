package mainframe;

import java.awt.*;
import javax.swing.*;

public class ProgressBar extends JFrame {
	
	JProgressBar pb = new JProgressBar();
    Label status;
	Container c3 = this.getContentPane();
	int count = 0;
    public ProgressBar() {
            c3.setLayout(new BorderLayout());
            status = new Label("");
            
            pb.setMinimum(0);
            pb.setMaximum(100);
            pb.setValue(0);
            //pb.setStringPainted(true);
            pb.setIndeterminate(true);

            //c3.add(pb,BorderLayout.NORTH);
            c3.add(status,BorderLayout.CENTER);

            setTitle("진행중");
            setSize(getPreferredSize());
            setLocationRelativeTo(null);
            setResizable(false);
            setVisible(true);
            setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }

    public void start_timer(int start_Num, int end_Num,String message) {

            try        {
                    for(count=start_Num; count<=end_Num; count++) {
                            Thread.sleep(50);
                            status.setText(" 진행중... "+count+" %"+" ( "+message + " )");
                            if(count == 100)
                            {
                                dispose();
                            }
                    }
            }
            catch (InterruptedException e) {}
    }
    public void end_timer()
    {
    	dispose();
    }

    public Dimension getPreferredSize() {
            return new Dimension(300, 100);
    }

    public static void main(String[] args) {
    	new ProgressBar();
    }
}