package weekProject;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class InputCheckEmailNum extends JFrame {
    private JPanel contentPane;
    private JTextField CheckNum;

    public InputCheckEmailNum() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 850, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
		contentPane.setBackground(new Color(255, 255, 255));
		setLocationRelativeTo(null);
        setContentPane(contentPane);

        JLabel checkLabel = new JLabel("인증번호 입력");
        checkLabel.setForeground(new Color(0, 128, 255));
        checkLabel.setFont(new Font("Dialog", Font.BOLD, 30));
        checkLabel.setBounds(315, 100, 220, 30);
        contentPane.add(checkLabel);

        CheckNum = new RoundJPasswordField(20);
        CheckNum.setBackground(SystemColor.control);
        CheckNum.setFont(new Font("굴림", Font.PLAIN, 20));
        CheckNum.setBounds(315, 153, 220, 30);
        contentPane.add(CheckNum);
        CheckNum.setColumns(10);

        JButton btnSubmit = new RoundJButton("확인");
        btnSubmit.setBorderPainted(false);
        btnSubmit.setBackground(new Color(0, 128, 255));
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setFont(new Font("굴림", Font.PLAIN, 20));
        btnSubmit.setBounds(356, 208, 138, 38);
        contentPane.add(btnSubmit);

        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String enteredCode = CheckNum.getText();
                if (enteredCode.equals("a1b1c1")) {
                	JOptionPane.showMessageDialog(null, "인증 성공!", "", 3);
                	ResetPassword frame = new ResetPassword();
					frame.setVisible(true);
					dispose();
                } else {
                	JOptionPane.showMessageDialog(null, "인증 실패. 다시 시도하세요.", "", 3);
                }
            }
        });
    }
    
    public class RoundJButton extends JButton {
	    private Shape shape;

	    public RoundJButton(String label) {
	        super(label);
	        setOpaque(false); // 투명 배경 설정
	    }

	    protected void paintComponent(Graphics g) {
	        g.setColor(getBackground());
	        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15); // 15는 둥근 경계의 반지름
	        super.paintComponent(g);
	    }

	    protected void paintBorder(Graphics g) {
	        // 아무것도 그리지 않음 (테두리를 그리지 않음)
	    }

	    public boolean contains(int x, int y) {
	        if (shape == null || !shape.getBounds().equals(getBounds())) {
	            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
	        }
	        return shape.contains(x, y);
	    }
	}
	
	public class RoundJPasswordField extends JPasswordField {
	    private Shape shape;

	    public RoundJPasswordField(int size) {
	        super(size);
	        setOpaque(false); // 투명 배경 설정
	    }

	    protected void paintComponent(Graphics g) {
	        g.setColor(getBackground());
	        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10); // 15는 둥근 경계의 반지름
	        super.paintComponent(g);
	    }
	    
	    protected void paintBorder(Graphics g) {
	        // 아무것도 그리지 않음 (테두리를 그리지 않음)
	    	
	    }


	    public boolean contains(int x, int y) {
	        if (shape == null || !shape.getBounds().equals(getBounds())) {
	            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
	        }
	        return shape.contains(x, y);
	    }
	}

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InputCheckEmailNum frame = new InputCheckEmailNum();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
