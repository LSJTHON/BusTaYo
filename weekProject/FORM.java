package weekProject;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FORM extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton Main_Ticketing;
    private JButton Main_Route;
    private JButton Main_Search;
    private JButton Main_Map;
    private JButton btnNewButton_4;

    public FORM() {
        setTitle("창원 시외버스 예매 시스템");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 850, 450);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 248, 255)); // Light Blue
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,
                (windowSize.height - frameSize.height) / 2);

        setContentPane(contentPane);
        contentPane.setLayout(null);

        Main_Ticketing = new RoundedButton("예매");
        Main_Ticketing.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
        Main_Ticketing.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MemoCalendar jFrame = new MemoCalendar();
                jFrame.mainFrame.setVisible(true);
                dispose();
            }
        });
        Main_Ticketing.setForeground(Color.WHITE);
        Main_Ticketing.setBackground(new Color(0, 128, 255)); // Dark Blue
        Main_Ticketing.setBounds(211, 173, 100, 100);
        contentPane.add(Main_Ticketing);

        Main_Route = new RoundedButton("노선표");
        Main_Route.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RouteMap jFrame = new RouteMap();
                jFrame.setVisible(true);
                dispose();
            }
        });
        
        Main_Route.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
        Main_Route.setForeground(Color.WHITE);
        Main_Route.setBackground(new Color(0, 128, 192)); // Dark Cyan
        Main_Route.setBounds(310, 173, 100, 100);
        contentPane.add(Main_Route);

        Main_Search = new RoundedButton("조회");
        Main_Search.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
        Main_Search.setForeground(Color.WHITE);
        Main_Search.setBackground(new Color(0, 128, 255)); // Dark Blue
        Main_Search.setBounds(410, 173, 100, 100);
        contentPane.add(Main_Search);

        Main_Map = new RoundedButton("오시는 길");
        
        Main_Map.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		Nosunpyo nsp = new Nosunpyo();
        		nsp.setVisible(true);
        		dispose();
        	}
        });
        Main_Map.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
        Main_Map.setForeground(Color.WHITE);
        Main_Map.setBackground(new Color(0, 128, 192)); // Dark Cyan
        Main_Map.setBounds(510, 173, 100, 100);
        
        contentPane.add(Main_Map);

        JLabel textPane = new JLabel();
        textPane.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 26));
        textPane.setText("창원 시외버스 터미널");
        textPane.setIcon(new ImageIcon(getClass().getResource("busImage.png")));
        textPane.setHorizontalAlignment(SwingConstants.CENTER);
        textPane.setBounds(154, 50, 542, 80);
        contentPane.add(textPane);

        btnNewButton_4 = new JButton("로그아웃");
        btnNewButton_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginForm backLogin = new loginForm();
                backLogin.setVisible(true);
                dispose();
            }
        });
        
        Main_Search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               reservationBusCheck frame = new reservationBusCheck();
            frame.setVisible(true);
            dispose();
            }
        });
        
        btnNewButton_4.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
        btnNewButton_4.setBackground(new Color(255, 255, 255)); // White
        btnNewButton_4.setBorderPainted(false);
        btnNewButton_4.setBounds(715, 10, 109, 30);
        contentPane.add(btnNewButton_4);
    }
}
