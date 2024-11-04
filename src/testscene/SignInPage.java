package testscene;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.InputVerifier;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.Insets;

public class SignInPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel Panel;
	private JTextField GivenName;
	private JTextField FamilyName;
	private JTextField Email;
	private JTextField Password1;
	private JLabel SignUp;
	private JLabel HaveanAcc;
	private JLabel EmailPanel;
	private JLabel PasswordLabel;
	private JLabel GivenNameL;
	private JLabel FamilyNameL;
	private JLabel lmageBack;
	private JLabel Lock;
	private JLabel PasswordRevealIcon;
	private JLabel EmailIcon;

	private boolean isValidEmail(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:gmail\\.com|yahoo\\.com|outlook\\.com)$";
		Pattern pattern = Pattern.compile(emailRegex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignInPage frame = new SignInPage();
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
	public SignInPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(2114, 2811, 1503, 982);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		RoundedButton SignupButton = new RoundedButton("Sign up", 25);
		SignupButton.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 40));
		SignupButton.setForeground(new Color(13, 146, 118));
		SignupButton.setBackground(new Color(187, 226, 236));
		SignupButton.setBounds(334, 750, 836, 87);

		SignupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Retrieve text from fields within the actionPerformed method to ensure they
				// are up-to-date
				String givenNameText = GivenName.getText();
				String familyNameText = FamilyName.getText();
				String emailText = Email.getText();
				String passwordText = new String(((JPasswordField) Password1).getPassword());

				// Check if any of the fields are empty or not correctly filled
				if (givenNameText.isEmpty() || givenNameText.equals("Given Name")) {
					JOptionPane.showMessageDialog(SignupButton, "Please enter your given name.", "Missing Information",
							JOptionPane.ERROR_MESSAGE);
				} else if (familyNameText.isEmpty() || familyNameText.equals("Family Name")) {
					JOptionPane.showMessageDialog(SignupButton, "Please enter your family name.", "Missing Information",
							JOptionPane.ERROR_MESSAGE);
				} else if (!(emailText.contains("@gmail.com") || emailText.contains("@yahoo.com")
						|| emailText.contains("@outlook"))) {
					JOptionPane.showMessageDialog(SignupButton,
							"Please enter an email address from Gmail, Yahoo, or Outlook.", "Invalid Email",
							JOptionPane.ERROR_MESSAGE);
				} else if (passwordText.isEmpty()) {
					JOptionPane.showMessageDialog(SignupButton, "Password cannot be empty.", "Missing Information",
							JOptionPane.ERROR_MESSAGE);
				} else if (passwordText.contains(" ")) {
					JOptionPane.showMessageDialog(SignupButton, "Password cannot contain spaces.", "Invalid Password",
							JOptionPane.ERROR_MESSAGE);
				} else if (passwordText.length() < 9 || passwordText.length() > 16) {
					JOptionPane.showMessageDialog(SignupButton, "Password must be between 9 to 16 characters.",
							"Invalid Password", JOptionPane.ERROR_MESSAGE);
				} else {
					// All inputs are valid, proceed with the signup process
					// Proceed with database insertion

					try {
						Connection connect = database.connectDB();

						String register = "INSERT INTO user (firstname, lastname, email, password) VALUES (?, ?, ?, ?)";
						PreparedStatement prepare = connect.prepareStatement(register);

						prepare.setString(1, givenNameText);
						prepare.setString(2, familyNameText);
						prepare.setString(3, emailText);
						prepare.setString(4, passwordText); // Consider hashing the password before storing

						int affectedRows = prepare.executeUpdate();
						if (affectedRows > 0) {

							JOptionPane.showMessageDialog(SignupButton, "Registration Successful.", "Success",
									JOptionPane.INFORMATION_MESSAGE);
							LoginPage enter = new LoginPage();
							dispose();
							enter.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(SignupButton, "Registration Failed.", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					} catch (SQLException ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(SignupButton, "Database error occurred.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

				}
			}
		});

		GivenName = new JTextField();
		GivenName.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				if (GivenName.getText().equals("Given Name")) {
					GivenName.setText("");
					GivenName.setForeground(Color.BLACK);
				}
			}

			public void focusLost(FocusEvent e) {
				if (GivenName.getText().equals("")) {
					GivenName.setText("Given Name");
					GivenName.setForeground(new Color(0, 0, 0, 20));
				}
			}
		});
		GivenName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				// Allow letters, space, backspace, and delete key
				if (!(Character.isLetter(c) || Character.isSpaceChar(c) || c == KeyEvent.VK_BACK_SPACE
						|| c == KeyEvent.VK_DELETE)) {
					e.consume(); // Ignore this key event
					JOptionPane.showMessageDialog(GivenName, "Please enter only letters.", "Invalid Input",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		GivenName.setBorder(null);
		GivenName.setMargin(new Insets(0, 0, 0, 0));
		GivenName.setText("Given Name");
		GivenName.setForeground(new Color(0, 0, 0, 20));
		GivenName.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 40));
		GivenName.setBounds(356, 324, 359, 82);

		FamilyName = new JTextField();
		FamilyName.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				if (FamilyName.getText().equals("Family Name")) {
					FamilyName.setText("");
					FamilyName.setForeground(Color.BLACK);
				}
			}

			public void focusLost(FocusEvent e) {
				if (FamilyName.getText().equals("")) {
					FamilyName.setText("Family Name");
					FamilyName.setForeground(new Color(0, 0, 0, 20));
				}
			}
		});

		FamilyName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				// Allow letters, space, backspace, and delete key
				if (!(Character.isLetter(c) || Character.isSpaceChar(c) || c == KeyEvent.VK_BACK_SPACE
						|| c == KeyEvent.VK_DELETE)) {
					e.consume(); // Ignore this key event
					JOptionPane.showMessageDialog(GivenName, "Please enter only letters.", "Invalid Input",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		FamilyName.setBorder(null);// no borders
		FamilyName.setMargin(new Insets(0, 0, 0, 0));
		FamilyName.setText("Family Name");
		FamilyName.setForeground(new Color(0, 0, 0, 20)); // Color of text
		FamilyName.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 40));// Size of text
		FamilyName.setBounds(793, 324, 359, 82);
		FamilyName.setColumns(10);

		Email = new JTextField();
		Email.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				if (Email.getText().equals("Email")) {
					Email.setText("");
					Email.setForeground(Color.BLACK);
				}
			}

			public void focusLost(FocusEvent e) {
				if (Email.getText().equals("")) {
					Email.setText("Email");
					Email.setForeground(new Color(0, 0, 0, 20));
				}
			}

		});

		Email.setInputVerifier(new InputVerifier() {
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

		Email.setBorder(null);
		Email.setMargin(new Insets(0, 0, 0, 0));
		Email.setText("Email");
		Email.setForeground(new Color(0, 0, 0, 20));
		Email.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 40));
		Email.setBounds(416, 435, 730, 82);

		// Username.setBorder(roundedBorder);

		Password1 = new JPasswordField();
		Password1.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				if (Password1.getText().equals("Password")) {
					Password1.setText("");
					Password1.setForeground(Color.BLACK);
				}
			}

			public void focusLost(FocusEvent e) {
				if (Password1.getText().equals("")) {
					Password1.setText("Password");
					Password1.setForeground(new Color(0, 0, 0, 20));
				}
			}
		});

		Password1.setBorder(null);// no borders
		Password1.setMargin(new Insets(0, 0, 0, 0));
		Password1.setText("Password");
		Password1.setForeground(new Color(0, 0, 0, 20)); // Color of text
		Password1.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 40));// Size of text
		Password1.setBounds(416, 545, 730, 82);
		Password1.setColumns(10);
		// Password.setBorder(roundedBorder);

		SignUp = new JLabel();
		SignUp.setBorder(null);
		SignUp.setBackground(new Color(13, 146, 118));
		SignUp.setForeground(new Color(247, 239, 138));
		SignUp.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 50));
		SignUp.setText("Sign up for an Account");
		SignUp.setBounds(503, 141, 505, 57);

		HaveanAcc = new JLabel();
		HaveanAcc.setBorder(null);
		HaveanAcc.setForeground(new Color(0, 0, 238));
		HaveanAcc.setText("Already have an account? Sign in");
		HaveanAcc.setBackground(new Color(13, 146, 118));
		HaveanAcc.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 18));
		HaveanAcc.setBounds(612, 860, 281, 20);

		HaveanAcc.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				LoginPage enter = new LoginPage();

				enter.setVisible(true);
			}
		});

		Panel = new JPanel();
		Panel = new RoundedPanel(25, new Color(13, 146, 118)); // 30 is the corner radius
		Panel.setBounds(225, 0, 1054, 955);

		lmageBack = new JLabel("New label");
		lmageBack
				.setIcon(new ImageIcon(SignInPage.class.getResource("/testscene/ImagesHMS/output-onlinepngtools.png")));
		lmageBack.setBounds(0, 0, 1503, 982);

		Lock = new JLabel("New label");
		Lock.setIcon(new ImageIcon(
				SignInPage.class.getResource("/testscene/ImagesHMS/1322b0855131be29fa83712df611202f_resized.png")));
		Lock.setBounds(350, 562, 45, 45);

		EmailIcon = new JLabel("New label");
		EmailIcon.setIcon(new ImageIcon(
				SignInPage.class.getResource("/testscene/ImagesHMS/892f21022672805c62b5b3d823611953_resized.png")));
		EmailIcon.setBounds(349, 454, 45, 45);

		EmailPanel = new JLabel("New label");
		EmailPanel.setIcon(new ImageIcon(
				SignInPage.class.getResource("/testscene/ImagesHMS/rounded_rectangle_with_black_borders.png")));
		EmailPanel.setBounds(334, 432, 836, 87);

		PasswordLabel = new JLabel("New label");
		PasswordLabel.setIcon(new ImageIcon(
				SignInPage.class.getResource("/testscene/ImagesHMS/rounded_rectangle_with_black_borders.png")));
		PasswordLabel.setBounds(334, 542, 836, 87);

		GivenNameL = new JLabel("New label");
		GivenNameL.setIcon(new ImageIcon(
				SignInPage.class.getResource("/testscene/ImagesHMS/rounded_rectangle_398x87_with_border.png")));
		GivenNameL.setBounds(334, 322, 400, 87);

		FamilyNameL = new JLabel("New label");
		FamilyNameL.setIcon(new ImageIcon(
				SignInPage.class.getResource("/testscene/ImagesHMS/rounded_rectangle_398x87_with_border.png")));
		FamilyNameL.setBounds(771, 322, 398, 87);

		PasswordRevealIcon = new JLabel("New label");
		PasswordRevealIcon.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// Toggle visibility of the password field
				((JPasswordField) Password1).setEchoChar(((JPasswordField) Password1).getEchoChar() == 0 ? '●' : 0);
			}
		});

		PasswordRevealIcon.setVerticalTextPosition(SwingConstants.TOP);
		PasswordRevealIcon.setVerticalAlignment(SwingConstants.TOP);
		PasswordRevealIcon.setBounds(1115, 564, 40, 40);

		PasswordRevealIcon.setIcon(
				new ImageIcon(SignInPage.class.getResource("/testscene/ImagesHMS/output-onlinepngtools-2-2.png")));

		contentPane.add(PasswordRevealIcon);
		contentPane.add(EmailIcon);
		contentPane.add(Lock);
		contentPane.add(HaveanAcc);
		contentPane.add(FamilyName);
		contentPane.add(GivenName);
		contentPane.add(SignupButton);
		contentPane.add(SignUp);
		contentPane.add(Email);
		contentPane.add(FamilyNameL);
		contentPane.add(GivenNameL);
		contentPane.add(Password1);
		contentPane.add(EmailPanel);
		contentPane.add(PasswordLabel);
		contentPane.add(Panel);
		contentPane.add(lmageBack);

	}

}
