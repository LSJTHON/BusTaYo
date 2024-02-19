package weekProject;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Shape;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class SignUpFrame extends JFrame {

	private RoundJTextField idField, nameField, emailField;
	private RoundJPasswordField passwordField, confirmPasswordField;
	private JLabel idLabel, nameLabel, passwordLabel, confirmPasswordLabel, emailLabel;
	private RoundJButton checkDuplicateButton, signUpButton;

	public SignUpFrame() {
		getContentPane().setBackground(new Color(255, 255, 255));
		setBackground(Color.WHITE);
		setTitle("회원가입");
		setSize(747, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		idLabel = new JLabel("아이디 (20자 이내 영문, 숫자 입력 / 한글 x)");
		idLabel.setFont(new Font("던파 비트비트체 v2", Font.BOLD, 15));
		idLabel.setHorizontalAlignment(JLabel.CENTER);
		
		nameLabel = new JLabel("이름");
		nameLabel.setFont(new Font("던파 비트비트체 v2", Font.BOLD, 15));
		nameLabel.setHorizontalAlignment(JLabel.CENTER);
		
		passwordLabel = new JLabel("비밀번호 (20자 이내 영문, 숫자, 특수문자 포함)");
		passwordLabel.setFont(new Font("던파 비트비트체 v2", Font.BOLD, 15));
		passwordLabel.setHorizontalAlignment(JLabel.CENTER);
		
		confirmPasswordLabel = new JLabel("비밀번호 확인");
		confirmPasswordLabel.setFont(new Font("던파 비트비트체 v2", Font.BOLD, 15));
		confirmPasswordLabel.setHorizontalAlignment(JLabel.CENTER);
		
		emailLabel = new JLabel("이메일");
		emailLabel.setFont(new Font("던파 비트비트체 v2", Font.BOLD, 15));
		emailLabel.setHorizontalAlignment(JLabel.CENTER);
		
		idField = new RoundJTextField(20);
		idField.setBackground(SystemColor.control);
		// idField에 적용되는 Document를 AbstractDocument로 형변환 하고 IdDocumentFilter메서드로 특정 규칙 사용
		((AbstractDocument) idField.getDocument()).setDocumentFilter(new IdDocumentFilter());
		nameField = new RoundJTextField(20);
		nameField.setBackground(SystemColor.control);
		passwordField = new RoundJPasswordField(20);
        passwordField.setBackground(SystemColor.control);
		confirmPasswordField = new RoundJPasswordField(20);
		confirmPasswordField.setBackground(SystemColor.control);
		emailField = new RoundJTextField(25);
		emailField.setBackground(SystemColor.control);
		checkDuplicateButton = new RoundJButton("아이디 중복 체크");
		checkDuplicateButton.setFont(new Font("던파 비트비트체 v2", Font.BOLD, 15));
		
		checkDuplicateButton.setBackground(new Color(0, 128, 255));
		checkDuplicateButton.setForeground(Color.WHITE);
		// 아이디 중복 체크
		checkDuplicateButton.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// 아이디 빈칸 체크
		        if (idField.getText().trim().isEmpty()) {
		            JOptionPane.showMessageDialog(null, "아이디를 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
		            return;
		        }
		        
				// 아이디 중복 체크
		        if (isIdDuplicated(idField.getText().trim())) {
		            JOptionPane.showMessageDialog(null, "이미 사용 중인 아이디입니다. 다른 아이디를 선택해주세요.", "경고", JOptionPane.WARNING_MESSAGE);
		            return;
		        } else {
		        	JOptionPane.showMessageDialog(null, "사용가능한 아이디 입니다.", "", JOptionPane.PLAIN_MESSAGE);
		        }
			}
		});
		
		signUpButton = new RoundJButton("회원가입");
		signUpButton.setBackground(new Color(0, 128, 255));
		signUpButton.setForeground(Color.WHITE);
		signUpButton.setFont(new Font("던파 비트비트체 v2", Font.BOLD, 15));
		
		signUpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// 아이디 빈칸 체크
				if (idField.getText().trim().isEmpty()) {
		            JOptionPane.showMessageDialog(null, "아이디를 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
		            return;
		        }
				
		        // 아이디 중복 체크
		        if (isIdDuplicated(idField.getText().trim())) {
		            JOptionPane.showMessageDialog(null, "이미 사용 중인 아이디입니다. 다른 아이디를 선택해주세요.", "경고", JOptionPane.WARNING_MESSAGE);
		            return;
		        } 
				
				// 이름 빈칸 체크 
				if (nameField.getText().trim().isEmpty()) {
		            JOptionPane.showMessageDialog(null, "이름을 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
		            return;
		        }
		        
				// 이름 한글인지 검사
				if (!isKorean(nameField.getText().trim())) {
		            JOptionPane.showMessageDialog(null, "이름은 한글만 입력 가능합니다.", "경고", JOptionPane.WARNING_MESSAGE);
		            return;
		        }
				
				// 비밀번호 빈칸 체크
				if (new String(passwordField.getPassword()).trim().isEmpty()) {
		            JOptionPane.showMessageDialog(null, "비밀번호를 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
		            return;
		        }
		        
		        // 패스워드 영문, 숫자, 특수문자 포함인지 체크
				PasswordVerifier passwordVerifier = new PasswordVerifier();
				if (!passwordVerifier.verify(passwordField)) {
					return;
				}

				// 비밀번호 확인 빈칸 체크
		        if (new String(confirmPasswordField.getPassword()).trim().isEmpty()) {
		            JOptionPane.showMessageDialog(null, "비밀번호 확인을 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
		            return;
		        }

				// 비밀번호, 비밀번호 확인 입력값이 일치한지 체크
				String password = new String(passwordField.getPassword());
				String confirmPassword = new String(confirmPasswordField.getPassword());

				if (!password.equals(confirmPassword)) {
					JOptionPane.showMessageDialog(null, "비밀번호와 비밀번호 확인이 일치하지 않습니다.", "경고", JOptionPane.WARNING_MESSAGE);
					return;
				}

		        // 이메일 빈칸 체크
		        if (emailField.getText().trim().isEmpty()) {
		            JOptionPane.showMessageDialog(null, "이메일을 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
		            return;
		        }
				
				// 이메일 형식 체크
		        if (!isValidEmail(emailField.getText().trim())) {
		            JOptionPane.showMessageDialog(null, "올바른 이메일 형식이 아닙니다.", "경고", JOptionPane.WARNING_MESSAGE);
		            return;
		        }

				// 회원가입 처리 로직을 여기에 추가
				String id = idField.getText();
				String name = nameField.getText();
				String email = emailField.getText();

				plusUser();
			}
		});

		getContentPane().setLayout(new GridLayout(6, 2, 5, 5));
		getContentPane().add(idLabel);
		getContentPane().add(idField);
		getContentPane().add(nameLabel);
		getContentPane().add(nameField);
		getContentPane().add(passwordLabel);
		getContentPane().add(passwordField);
		getContentPane().add(confirmPasswordLabel);
		getContentPane().add(confirmPasswordField);
		getContentPane().add(emailLabel);
		getContentPane().add(emailField);
		getContentPane().add(checkDuplicateButton);
		getContentPane().add(signUpButton);
	}

	private void plusUser() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// mysql DB에 연결
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://222.119.100.89:3382/bustickets", // mysql 위치										
					"bustickets", // mysql 아이디
					"1234" // mysql 비밀번호
			);

			// 사용자 입력값 가져오기
			String id = idField.getText();
			String name = nameField.getText();
			String password = new String(passwordField.getPassword());
			String email = emailField.getText();

			// SQL 쿼리 작성
			String sql = "INSERT INTO users (username, password, name, email) VALUES (?, ?, ?, ?)";

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.setString(1, id);
				preparedStatement.setString(2, password);
				preparedStatement.setString(3, name);
				preparedStatement.setString(4, email);

				// 쿼리 실행
				int affectedRows = preparedStatement.executeUpdate();

				if (affectedRows > 0) {
					JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
					// 회원가입이 완료되면 현재 창을 닫고 로그인 페이지를 엽니다.
					dispose(); // 현재 창 닫기
					// 로그인 페이지 열기
					// loginPage();
				} else {
					JOptionPane.showMessageDialog(null, "회원가입에 실패했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
				}
			}

			// 연결해제
			connection.close();
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}

	}

	// 아이디에 입력되는 형식을 20자 이내 영어나 숫자만 허용하는 메소드
	private class IdDocumentFilter extends DocumentFilter {
		// 문자길이 20자로 변수 생성
		private static final int MAX_LENGTH = 20;

		// 입력할때 적용되는 메소드
		@Override
		public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
				throws BadLocationException {
			if (fb.getDocument().getLength() + string.length() <= MAX_LENGTH && string.matches("[a-zA-Z0-9]+")) {
				super.insertString(fb, offset, string, attr);
			}
		}

		// 재입력 메소드
		@Override
		public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
				throws BadLocationException {
			if (fb.getDocument().getLength() - length + text.length() <= MAX_LENGTH && text.matches("[a-zA-Z0-9]+")) {
				super.replace(fb, offset, length, text, attrs);
			}
		}
	}
	
	// 입력된 아이디가 db에 있는 아이디랑 중복인지 체크
	private boolean isIdDuplicated(String id) {
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        // mysql DB에 연결
	        Connection connection = DriverManager.getConnection(
	                "jdbc:mysql://222.119.100.89:3382/bustickets", // mysql 위치
	                "bustickets", // mysql 아이디
	                "1234" // mysql 비밀번호
	        );

	        // SQL 쿼리 작성
	        String sql = "SELECT COUNT(*) FROM users WHERE username = ?";

	        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	            preparedStatement.setString(1, id);

	            // 쿼리 실행
	            var resultSet = preparedStatement.executeQuery();

	            if (resultSet.next()) {
	                int count = resultSet.getInt(1);
	                return count > 0; // 중복된 아이디가 있으면 true 반환
	            }
	        }

	        // 연결해제
	        connection.close();
	    } catch (ClassNotFoundException | SQLException ex) {
	        ex.printStackTrace();
	    }

	    return false; // 에러 발생 시 기본적으로 중복된 아이디가 없다고 가정
	}
	
	// 이름에 한글만 입력되는 메소드
	private boolean isKorean(String input) {
	    return input.matches("^[ㄱ-ㅎ가-힣]*$");
	}
	
	// 비밀번호가 특정 조건에 일치하는지 검사하는 메소드
	private class PasswordVerifier extends InputVerifier {
	    private static final int MAX_LENGTH = 20;

	    @Override
	    public boolean verify(JComponent input) {
	        JPasswordField passwordField = (JPasswordField) input;
	        char[] password = passwordField.getPassword();

	        // 비밀번호는 영문, 숫자, 특수문자 중 하나 이상을 포함해야 합니다.
	        boolean hasLetter = false;
	        boolean hasDigit = false;
	        boolean hasSpecialChar = false;

	        for (char ch : password) {
	            if (Character.isLetter(ch)) {
	                hasLetter = true;
	            } else if (Character.isDigit(ch)) {
	                hasDigit = true;
	            } else if (isSpecialCharacter(ch)) {
	                hasSpecialChar = true;
	            }
	        }

	        boolean isValid = hasLetter && hasDigit && hasSpecialChar;

	        if (!isValid) {
	            JOptionPane.showMessageDialog(null, "비밀번호는 영문, 숫자, 특수문자를 하나 이상 포함해야 합니다.", "경고",
	                    JOptionPane.WARNING_MESSAGE);
	            passwordField.selectAll();
	            passwordField.requestFocusInWindow();
	        }

	        return isValid && password.length <= MAX_LENGTH;
	    }

	    // 특수문자 여부를 확인하는 메소드
	    private boolean isSpecialCharacter(char ch) {
	        String specialCharacters = "!@#$%^&*()-=_+`{}|;:'\"<>,.?/\\[]\\\\";
	        return specialCharacters.indexOf(ch) != -1;
	    }
	}

	
	// 이메일이 이메일형식으로 입력되었는지 체크
	private boolean isValidEmail(String email) {
	    // 이메일 형식 정규표현식
	    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	    return email.matches(emailRegex);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new SignUpFrame().setVisible(true);
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
	public class RoundJTextField extends JTextField {
	    private Shape shape;

	    public RoundJTextField(int size) {
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
	
	
}