package testscene;

import javax.mail.MessagingException;
import javax.swing.*;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RecoveryPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel MainPanel;
	private JTextField TemporaryPassword;
	private JTextField NewPassword;
	private JTextField Email;
	private JLabel Recovery;
	private JLabel EmailIcon;
	private JLabel LockIcon;
	private JLabel TPIcon;
	private JLabel EmailBorder;
	private JLabel TempPasswordBorder;
	private JLabel NewPasswordBorder;
	private JLabel ReturnBack;
	private JLabel lmageBack;
	private JLabel CheckEmailIcon;

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
					RecoveryPage frame = new RecoveryPage();
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
	public RecoveryPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(2114, 2811, 1503, 982);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		RoundedButton Submit = new RoundedButton("Submit", 25); // 25 is the corner radius
		Submit.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 40));
		Submit.setForeground(new Color(13, 146, 118));
		Submit.setBackground(new Color(187, 226, 236));
		Submit.setBounds(334, 750, 836, 87);

		Submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Directly retrieve the text from the JTextField components
				String userEmail = Email.getText().trim(); // Assuming Email is your JTextField for the user's email
				String tempPassword = TemporaryPassword.getText().trim(); // Retrieve the temporary password
				String newPassword = NewPassword.getText().trim(); // Assuming NewPassword is your JTextField for the
																	// new password

				// Check if the email and new password fields are not default values or empty,
				// and if the temporary password is exactly 16 characters long
				if (!userEmail.equals("Email") && !userEmail.isEmpty() && !newPassword.equals("New Password")
						&& !newPassword.isEmpty() && tempPassword.length() == 16) {

					// Proceed with updating the password in the database
					String updateSQL = "UPDATE user SET password = ? WHERE email = ?";

					try (Connection connect = database.connectDB();
							PreparedStatement prepare = connect.prepareStatement(updateSQL)) {

						prepare.setString(1, newPassword);
						prepare.setString(2, userEmail);

						int affectedRows = prepare.executeUpdate();

						if (affectedRows > 0) {
							System.out.println("Password update successful");
							// Navigate to the login page
							LoginPage enter = new LoginPage();
							dispose(); // Close the current window
							enter.setVisible(true); // Show the login page
						} else {
							System.out.println("Password update failed");
							JOptionPane.showMessageDialog(null, "Password update failed.", "Update Error",
									JOptionPane.ERROR_MESSAGE);
						}
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				} else {
					// Notify user to fill in all fields properly, including the specific check for
					// the temporary password length
					JOptionPane.showMessageDialog(null,
							"Please fill in all fields correctly and ensure the temporary password is vvalid.",
							"Input Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		Email = new JTextField();
		Email.setText("Email");

		Email.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {

				if (Email.getText().equals("Email")) {
					Email.setText("");
					Email.setForeground(Color.BLACK);
				}

			}

			public void focusLost(FocusEvent e) {
				if (Email.getText().isEmpty()) {
					Email.setText("Email");
					Email.setForeground(new Color(0, 0, 0, 20));
				}
			}
		});
		Email.setMargin(new Insets(0, 0, 0, 0));
		Email.setForeground(new Color(0, 0, 0, 20));
		Email.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 40));
		Email.setBounds(411, 320, 740, 81);
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

		Email.setBorder(null);// no borders

		TemporaryPassword = new JTextField();
		TemporaryPassword.setText("Temporary Password");

		TemporaryPassword.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {

				if (TemporaryPassword.getText().equals("Temporary Password")) {
					TemporaryPassword.setText("");
					TemporaryPassword.setForeground(Color.BLACK);
				}

			}

			public void focusLost(FocusEvent e) {
				if (TemporaryPassword.getText().isEmpty()) {
					TemporaryPassword.setText("Temporary Password");
					TemporaryPassword.setForeground(new Color(0, 0, 0, 20));
				}
			}
		});

		TemporaryPassword.setBorder(null);
		TemporaryPassword.setMargin(new Insets(0, 0, 0, 0));
		TemporaryPassword.setForeground(new Color(0, 0, 0, 20));
		TemporaryPassword.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 40));
		TemporaryPassword.setBounds(411, 435, 740, 81);

		NewPassword = new JPasswordField();
		NewPassword.setText("New Password");
		NewPassword.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {

				if (NewPassword.getText().equals("New Password")) {
					NewPassword.setText("");
					NewPassword.setForeground(Color.BLACK);
				}

			}

			public void focusLost(FocusEvent e) {
				if (NewPassword.getText().isEmpty()) {
					NewPassword.setText("New Password");
					NewPassword.setForeground(new Color(0, 0, 0, 20));
				}
			}
		});
		NewPassword.setBorder(null);// no borders
		NewPassword.setMargin(new Insets(0, 0, 0, 0));
		NewPassword.setForeground(new Color(0, 0, 0, 20)); // Color of text
		NewPassword.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 40));// Size of text
		NewPassword.setBounds(411, 545, 740, 81);
		NewPassword.setColumns(10);

		JLabel PasswordReveal = new JLabel("New label");
		PasswordReveal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Toggle visibility of the password field
				((JPasswordField) NewPassword).setEchoChar(((JPasswordField) NewPassword).getEchoChar() == 0 ? '●' : 0);
			}
		});

		PasswordReveal.setVerticalTextPosition(SwingConstants.TOP);
		PasswordReveal.setVerticalAlignment(SwingConstants.TOP);
		PasswordReveal.setBounds(1115, 565, 40, 40);
		contentPane.add(PasswordReveal);
		PasswordReveal.setIcon(
				new ImageIcon(RecoveryPage.class.getResource("/testscene/ImagesHMS/output-onlinepngtools-2-2.png")));

		MainPanel = new JPanel();
		MainPanel = new RoundedPanel(25, new Color(13, 146, 118));
		MainPanel.setBounds(225, 0, 1054, 982);

		lmageBack = new JLabel("New label");
		lmageBack.setIcon(
				new ImageIcon(RecoveryPage.class.getResource("/testscene/ImagesHMS/output-onlinepngtools.png")));
		lmageBack.setBounds(0, 0, 1503, 982);

		TPIcon = new JLabel("New label");
		TPIcon.setIcon(new ImageIcon(
				RecoveryPage.class.getResource("/testscene/ImagesHMS/22edcb4e683980c62614c79694b102cc_resized-3.png")));
		TPIcon.setBounds(337, 447, 67, 50);

		EmailIcon = new JLabel("New label");
		EmailIcon.setIcon(new ImageIcon(
				RecoveryPage.class.getResource("/testscene/ImagesHMS/892f21022672805c62b5b3d823611953_resized.png")));
		EmailIcon.setBounds(350, 339, 45, 45);

		LockIcon = new JLabel("New label");
		LockIcon.setIcon(new ImageIcon(
				RecoveryPage.class.getResource("/testscene/ImagesHMS/1322b0855131be29fa83712df611202f_resized.png")));
		LockIcon.setBounds(347, 563, 45, 45);

		contentPane.add(TPIcon);
		contentPane.add(LockIcon);
		contentPane.add(EmailIcon);

		Recovery = new JLabel();
		Recovery.setBorder(null);
		Recovery.setBackground(new Color(13, 146, 118));
		Recovery.setForeground(new Color(247, 239, 138));
		Recovery.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 50));
		Recovery.setText("Recovery Page");
		Recovery.setBounds(561, 141, 342, 57);
		contentPane.add(Recovery);
		contentPane.add(Submit);
		contentPane.add(NewPassword);
		contentPane.add(TemporaryPassword);
		contentPane.add(Email);

		NewPasswordBorder = new JLabel("New label");
		NewPasswordBorder.setIcon(new ImageIcon(
				RecoveryPage.class.getResource("/testscene/ImagesHMS/rounded_rectangle_with_black_borders.png")));
		NewPasswordBorder.setBounds(334, 542, 836, 87);
		contentPane.add(NewPasswordBorder);

		TempPasswordBorder = new JLabel("New label");
		TempPasswordBorder.setIcon(new ImageIcon(
				RecoveryPage.class.getResource("/testscene/ImagesHMS/rounded_rectangle_with_black_borders.png")));
		TempPasswordBorder.setBounds(334, 432, 836, 87);
		contentPane.add(TempPasswordBorder);

		EmailBorder = new JLabel("New label");
		EmailBorder.setBackground(new Color(255, 255, 255));
		EmailBorder.setIcon(new ImageIcon(
				RecoveryPage.class.getResource("/testscene/ImagesHMS/rounded_rectangle_with_black_borders.png")));
		EmailBorder.setBounds(334, 317, 836, 87);
		contentPane.add(EmailBorder);
		contentPane.add(MainPanel);
		MainPanel.setLayout(null);

		CheckEmailIcon = new JLabel("New label");
		CheckEmailIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					String query = "SELECT * FROM user WHERE email = ?";
					Connection connect = database.connectDB();
					PreparedStatement prepare = connect.prepareStatement(query);

					prepare.setString(1, Email.getText()); // Email.getText() must be the user's email address

					ResultSet result = prepare.executeQuery();

					if (result.next()) {
						// If user is found, proceed to send an email with a temporary password
						String tempPassword = PasswordGeneration.generatePassword(16); // Generate temporary password

						// Prepare email content
						String recipientEmail = Email.getText(); // Assuming Email.getText() gives the user's email
						String subject = "Your Temporary Password";
						String emailBody = "Dear User,\n\nWe have generated a temporary password for your account as requested. Please find your temporary password below. We strongly recommend changing this password immediately after logging in to ensure the security of your account.\n\nTemporary Password: "
								+ tempPassword
								+ "\n\nShould you have any questions or require further assistance, please do not hesitate to contact our support team.\n\nBest Regards,\n Restaraunt HMS Support Team";

						// Utilize EmailUtility to send the email
						EmailUtility.sendEmail(recipientEmail, subject, emailBody);

						JOptionPane.showMessageDialog(null, "A temporary password has been sent to your email.",
								"Success", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Email address not found.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "An error occurred while accessing the database.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} catch (MessagingException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Failed to send temporary password email.", "Email Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		CheckEmailIcon.setIcon(new ImageIcon(RecoveryPage.class.getResource(
				"/testscene/ImagesHMS/green-check-mark-web-design-blog-line-logo-symbol-circle-png-clipart-thumbnail-removebg-preview.png")));
		CheckEmailIcon.setBounds(969, 321, 70, 70);
		MainPanel.add(CheckEmailIcon);

		ReturnBack = new JLabel("Return Back");
		ReturnBack.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				LoginPage enter = new LoginPage();
				dispose(); // Close the current window
				enter.setVisible(true); // Show the login page
			}
		});
		ReturnBack.setForeground(new Color(0, 0, 238));
		ReturnBack.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 18));
		ReturnBack.setBounds(830, 645, 100, 16);
		MainPanel.add(ReturnBack);

		contentPane.add(lmageBack);

	}
}
