package testscene;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.InputVerifier;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

class RoundedPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Color backgroundColor;
	private int cornerRadius;

	public RoundedPanel(int radius, Color bgColor) {
		super();
		cornerRadius = radius;
		backgroundColor = bgColor;
		setOpaque(false); // Make the panel transparent
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.setColor(backgroundColor);
		graphics.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
	}
}

class RoundedButton extends JButton {
	private static final long serialVersionUID = 1L;
	private int cornerRadius; // Corner radius for rounded corners

	public RoundedButton(String label, int radius) {
		super(label);
		cornerRadius = radius;
		setContentAreaFilled(false);
		setBorderPainted(false);
		setFocusPainted(false);
		setOpaque(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Button is pressed
		if (getModel().isPressed()) {
			g2.setColor(getBackground().darker());
		} else if (getModel().isRollover()) {
			g2.setColor(getBackground().brighter());
		} else {
			g2.setColor(getBackground());
		}

		g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

		super.paintComponent(g);
	}

	@Override
	protected void paintBorder(Graphics g) {
		// No border is painted
	}

	@Override
	public boolean contains(int x, int y) {
		// This method could be overridden to match the rounded shape if needed
		return super.contains(x, y);
	}

}

public class LoginPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel MainPanel_1;
	private JTextField Password;
	private JTextField Username;
	private JLabel SignInTo;
	private JLabel ForgotPass;
	private JLabel TextAccHave;
	private JLabel lmageBack;
	private JLabel lmage;
	private JLabel User;
	private JLabel Lock;
	private JLabel RectngleUser;
	private JLabel PasswordRectangle;
	private JLabel PasswordRevealIcon;
	private Connection connect;
	private PreparedStatement prepare;
	private ResultSet result;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public LoginPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(2114, 2811, 1503, 982);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(null);

		Username = new JTextField();
		Username.setText("Username");

		Username.addFocusListener(new FocusListener() {

			public void focusGained(FocusEvent e) {
				if (Username.getText().equals("Username")) {
					Username.setText("");
					Username.setForeground(Color.BLACK);
				}
			}

			public void focusLost(FocusEvent e) {
				if (Username.getText().equals("")) {
					Username.setText("Username");
					Username.setForeground(new Color(0, 0, 0, 20));
				}
			}
		});

		Username.setInputVerifier(new InputVerifier() {
			public boolean verify(JComponent input) {
				String text = ((JTextField) input).getText();
				if (text.isEmpty() || text.equals("Email")) {
					return true; // Consider empty field as not needing verification here
				}
				String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
				if (text.matches(emailPattern)) {
					return true; // Email is valid
				} else {
					JOptionPane.showMessageDialog(input, "Please enter a valid email address.", "Invalid Email",
							JOptionPane.ERROR_MESSAGE);
					return false; // Email is not valid
				}
			}
		});

		Username.setBorder(null);
		Username.setMargin(new Insets(0, 0, 0, 0));
		Username.setForeground(new Color(0, 0, 0, 20));
		Username.setToolTipText("Please, enter your Email");
		Username.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 40));
		Username.setBounds(150, 426, 462, 84);

		Password = new JPasswordField();
		Password.setText("Password");
		Password.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {

				if (Password.getText().equals("Password")) {
					Password.setText("");
					Password.setForeground(Color.BLACK);
				}

			}

			public void focusLost(FocusEvent e) {
				if (Password.getText().isEmpty()) {
					Password.setText("Password");
					Password.setForeground(new Color(0, 0, 0, 20));
				}
			}
		});
		Password.setToolTipText("Please, enter your Password");
		Password.setBorder(null);
		Password.setMargin(new Insets(0, 0, 0, 0));
		Password.setForeground(new Color(0, 0, 0, 20));
		Password.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 40));
		Password.setBounds(150, 539, 462, 84);
		Password.setColumns(10);

		RoundedButton Signin = new RoundedButton("Sign in", 25); // 20 is the corner radius
		Signin.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 40));
		Signin.setForeground(new Color(13, 146, 118));
		Signin.setBackground(new Color(187, 226, 236));
		Signin.setBounds(74, 750, 558, 90);

		Signin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String query = "SELECT * FROM user WHERE email = ? AND password = ?";
					Connection connect = database.connectDB();
					PreparedStatement prepare = connect.prepareStatement(query);

					prepare.setString(1, Username.getText());
					prepare.setString(2, Password.getText());

					ResultSet result = prepare.executeQuery();

					if (result.next()) {
						// Valid login
						JOptionPane.showMessageDialog(null, "Login Successful!", "Success",
								JOptionPane.INFORMATION_MESSAGE);
						UserHomePage enter = new UserHomePage();
						dispose();
						enter.setVisible(true); // Shows the user home page

					} else {
						// Invalid login
						JOptionPane.showMessageDialog(null, "Incorrect username or password.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

				} catch (SQLException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "An error occurred while accessing the database.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}

		});

		// Don't have an account? Create an account
		TextAccHave = new JLabel();
		TextAccHave.setBorder(null);
		TextAccHave.setText("Don't have an account? Create an account");
		TextAccHave.setForeground(new Color(0, 0, 238));
		TextAccHave.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 18));
		TextAccHave.setBackground(new Color(13, 146, 118));
		TextAccHave.setBounds(185, 858, 360, 22);
		TextAccHave.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				SignInPage enter = new SignInPage();

				enter.setVisible(true);
			}
		});

//Forgot Password?

		ForgotPass = new JLabel();
		ForgotPass.setBorder(null);
		ForgotPass.setForeground(new Color(0, 0, 238));
		ForgotPass.setText("Forgot Password?");
		ForgotPass.setBackground(new Color(13, 146, 118));
		ForgotPass.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 18));
		ForgotPass.setBounds(463, 639, 158, 22);

		ForgotPass.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				RecoveryPage enter = new RecoveryPage();

				enter.setVisible(true);
			}
		});

		SignInTo = new JLabel();
		SignInTo.setBorder(null);
		SignInTo.setBackground(new Color(13, 146, 118));
		SignInTo.setForeground(new Color(247, 239, 138));
		SignInTo.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 50));
		SignInTo.setText("Sign in to your Account");
		SignInTo.setBounds(97, 141, 513, 57);

		MainPanel_1 = new JPanel();
		MainPanel_1 = new RoundedPanel(25, new Color(13, 146, 118)); // 30 is the corner radius
		MainPanel_1.setBounds(0, 0, 705, 954);

		lmageBack = new JLabel("New label");
		lmageBack.setIcon(new ImageIcon(LoginPage.class.getResource("/testscene/ImagesHMS/output-onlinepngtools.png")));
		lmageBack.setBounds(0, 0, 1503, 982);

		lmage = new JLabel("New label");
		lmage.setIcon(new ImageIcon(LoginPage.class.getResource("/testscene/ImagesHMS/goldt_resized.png")));
		lmage.setBounds(274, 223, 160, 160);

		User = new JLabel("New label");
		User.setIcon(new ImageIcon(
				LoginPage.class.getResource("/testscene/ImagesHMS/9a398f3e136e1dd1c12461c5df9edb99_resized.png")));
		User.setBounds(90, 445, 45, 45);

		Lock = new JLabel("New label");
		Lock.setIcon(new ImageIcon(
				LoginPage.class.getResource("/testscene/ImagesHMS/1322b0855131be29fa83712df611202f_resized.png")));
		Lock.setBounds(90, 558, 45, 45);

		RectngleUser = new JLabel("New label");
		RectngleUser.setIcon(
				new ImageIcon(LoginPage.class.getResource("/testscene/ImagesHMS/rounded_rectangle_fixed.png")));
		RectngleUser.setBounds(74, 423, 558, 90);

		PasswordRectangle = new JLabel("New label");
		PasswordRectangle.setIcon(
				new ImageIcon(LoginPage.class.getResource("/testscene/ImagesHMS/rounded_rectangle_fixed.png")));
		PasswordRectangle.setBounds(74, 537, 558, 90);

		PasswordRevealIcon = new JLabel("New label");
		PasswordRevealIcon.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// Toggle visibility of the password field
				((JPasswordField) Password).setEchoChar(((JPasswordField) Password).getEchoChar() == 0 ? '●' : 0);
			}
		});

		PasswordRevealIcon.setVerticalTextPosition(SwingConstants.TOP);
		PasswordRevealIcon.setVerticalAlignment(SwingConstants.TOP);
		PasswordRevealIcon.setBounds(570, 562, 40, 40);
		contentPane.add(PasswordRevealIcon);
		PasswordRevealIcon.setIcon(
				new ImageIcon(LoginPage.class.getResource("/testscene/ImagesHMS/output-onlinepngtools-2-2.png")));

		contentPane.add(Lock);
		contentPane.add(User);
		contentPane.add(Signin);
		contentPane.add(TextAccHave);
		contentPane.add(SignInTo);
		contentPane.add(ForgotPass);
		contentPane.add(lmage);
		contentPane.add(Username);
		contentPane.add(Password);
		contentPane.add(PasswordRectangle);
		contentPane.add(RectngleUser);
		contentPane.add(MainPanel_1);
		MainPanel_1.setLayout(null);
		contentPane.add(lmageBack);
		setContentPane(contentPane);

	}

}
