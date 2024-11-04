package testscene;

import javax.swing.*;
import java.awt.*;
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

public class SwitchRolePage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel table;
	private JTextField Password;
	private JTextField Username;
	private JLabel SwitchRole;
	private JLabel User;
	private JLabel Lock;
	private JLabel PasswordField;
	private JLabel GoBack;
	private JLabel lmageBack;
	private JLabel UsernameFiled;
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
					SwitchRolePage frame = new SwitchRolePage();
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
	public SwitchRolePage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(2114, 2811, 1503, 982);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(null);

		setContentPane(contentPane);

		Username = new JTextField();
		Username.setBorder(null);
		Username.setMargin(new Insets(0, 0, 0, 0));
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

		Username.setForeground(new Color(0, 0, 0, 20));
		Username.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 40));
		Username.setBounds(948, 422, 464, 86);

		Password = new JPasswordField();
		Password.setBorder(null);// no borders
		Password.setMargin(new Insets(0, 0, 0, 0));
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

		Password.setForeground(new Color(0, 0, 0, 20)); // Color of text
		Password.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 40));// Size of text
		Password.setBounds(948, 541, 464, 86);
		Password.setColumns(10);

		RoundedButton Submit = new RoundedButton("Submit", 25);
		Submit.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 40));
		Submit.setForeground(new Color(13, 146, 118));
		Submit.setBackground(new Color(187, 226, 236));
		Submit.setBounds(871, 750, 560, 92);
		Submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String query = "SELECT * FROM admin WHERE email = ? AND password = ?";
					Connection connect = database.connectDB();
					PreparedStatement prepare = connect.prepareStatement(query);

					prepare.setString(1, Username.getText());
					prepare.setString(2, Password.getText());

					ResultSet result = prepare.executeQuery();

					if (result.next()) {
						// Valid login
						JOptionPane.showMessageDialog(null, "Login Successful!", "Success",
								JOptionPane.INFORMATION_MESSAGE);
						AdminMainPage1 enter = new AdminMainPage1();
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

		// Table
		table = new JPanel();
		table = new RoundedPanel(25, new Color(13, 146, 118)); // 30 is the corner radius
		table.setBounds(798, 0, 705, 982);

		GoBack = new JLabel();
		GoBack.setBorder(null);
		GoBack.setForeground(new Color(0, 0, 238));
		GoBack.setText("Return Back");
		GoBack.setBackground(new Color(13, 146, 118));
		GoBack.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 18));
		GoBack.setBounds(1320, 639, 158, 22);

		GoBack.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				UserProfilePage enter = new UserProfilePage();
				dispose(); // Close the current window
				enter.setVisible(true);
			}
		});

		SwitchRole = new JLabel();
		SwitchRole.setBorder(null);
		SwitchRole.setBackground(new Color(13, 146, 118));
		SwitchRole.setForeground(new Color(247, 239, 138));
		SwitchRole.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 65));
		SwitchRole.setText("Switch the Role");
		SwitchRole.setBounds(925, 141, 452, 74);

		lmageBack = new JLabel("New label");
		lmageBack.setIcon(
				new ImageIcon(SwitchRolePage.class.getResource("/testscene/ImagesHMS/output-onlinepngtools.png")));
		lmageBack.setBounds(0, 0, 1503, 982);

		User = new JLabel("New label");
		User.setIcon(new ImageIcon(
				SwitchRolePage.class.getResource("/testscene/ImagesHMS/9a398f3e136e1dd1c12461c5df9edb99_resized.png")));
		User.setBounds(888, 445, 45, 45);

		Lock = new JLabel("New label");
		Lock.setIcon(new ImageIcon(
				SwitchRolePage.class.getResource("/testscene/ImagesHMS/1322b0855131be29fa83712df611202f_resized.png")));
		Lock.setBounds(888, 558, 45, 45);

		UsernameFiled = new JLabel("New label");
		UsernameFiled.setIcon(
				new ImageIcon(SwitchRolePage.class.getResource("/testscene/ImagesHMS/rounded_rectangle_fixed.png")));
		UsernameFiled.setBounds(872, 419, 560, 92);

		PasswordField = new JLabel("New label");
		PasswordField.setIcon(
				new ImageIcon(SwitchRolePage.class.getResource("/testscene/ImagesHMS/rounded_rectangle_fixed.png")));
		PasswordField.setBounds(872, 538, 560, 92);

		PasswordRevealIcon = new JLabel("New label");
		PasswordRevealIcon.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// Toggle visibility of the password field
				((JPasswordField) Password).setEchoChar(((JPasswordField) Password).getEchoChar() == 0 ? '●' : 0);
			}
		});

		PasswordRevealIcon.setVerticalTextPosition(SwingConstants.TOP);
		PasswordRevealIcon.setVerticalAlignment(SwingConstants.TOP);
		PasswordRevealIcon.setBounds(1370, 564, 40, 40);
		PasswordRevealIcon.setIcon(
				new ImageIcon(SwitchRolePage.class.getResource("/testscene/ImagesHMS/output-onlinepngtools-2-2.png")));

		contentPane.add(PasswordRevealIcon);
		contentPane.add(User);
		contentPane.add(Lock);
		contentPane.add(Password);
		contentPane.add(Username);
		contentPane.add(Submit);
		contentPane.add(SwitchRole);
		contentPane.add(GoBack);
		contentPane.add(PasswordField);
		contentPane.add(UsernameFiled);
		contentPane.add(table);
		contentPane.add(lmageBack);

	}
}
