package testscene;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.URI;

public class UserProfilePage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel PanelRight;
	private JPanel PanelCenter;
	private JPanel MoneySpentT;
	private JPanel CurrentBalT;
	private JPanel RecentlyBT;
	private JPanel YouSpentT;
	private JPanel YourBalanceT;
	private JPanel TableHistory;
	private JLabel MyProfile;
	private JLabel MoneySpent;
	private JLabel CurrentB;
	private JLabel RecentlyYB;
	private JLabel WelcomeUser;
	private JLabel YouSpent1;
	private JLabel YourBalance1;
	private JLabel UserIcon;
	private JLabel MoneySpentLabel;
	private JLabel YourBalanceLabel;
	private JLabel lmageBack;
	private JPanel panel;

	public class RoundedButton extends JButton {
		private int cornerRadius;
		private Color borderColor = new Color(0xF7EF8A);

		public RoundedButton(String label, int radius) {
			super(label);
			this.cornerRadius = radius;
			setContentAreaFilled(false);
			setBorderPainted(false);
			setFocusPainted(false);
			setOpaque(false);
		}

		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			if (getModel().isPressed()) {
				g2.setColor(getBackground().darker());
			} else if (getModel().isRollover()) {
				g2.setColor(getBackground().brighter());
			} else {
				g2.setColor(getBackground());
			}

			g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

			g2.setColor(borderColor);
			g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);

			g2.dispose();

			super.paintComponent(g);
		}

		@Override
		protected void paintBorder(Graphics g) {

		}
	}

	/*
	 * public void refreshUserData() { double moneySpent =
	 * UserDataManager.getMoneySpent(); MoneySpentLabel.setText("£" +
	 * String.format("%.2f", moneySpent)); }
	 */

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserProfilePage frame = new UserProfilePage();
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
	public UserProfilePage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(2114, 2811, 1503, 982);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		RoundedButton Menu = new RoundedButton("Menu", 50);
		Menu.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 30));
		Menu.setForeground(new Color(247, 239, 138));
		Menu.setBackground(new Color(204, 27, 27));
		Menu.setBounds(13, 232, 253, 60);
		Menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserHomePage enter = new UserHomePage();
				dispose();

				enter.setVisible(true);

			}
		});

		RoundedButton Allergies = new RoundedButton("Allergies", 50);
		Allergies.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 30));
		Allergies.setForeground(new Color(247, 239, 138));
		Allergies.setBackground(new Color(204, 27, 27));
		Allergies.setBounds(13, 310, 253, 60);
		// Your code for RoundedButton creation

		// Add ActionListener to the Allergies button
		Allergies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Check if Desktop is supported by the platform
					if (Desktop.isDesktopSupported()) {
						Desktop desktop = Desktop.getDesktop();
						if (desktop.isSupported(Desktop.Action.BROWSE)) {
							// Construct a URI with the URL you want to open in the browser
							URI uri = new URI("https://en.wikipedia.org/wiki/Allergy");
							desktop.browse(uri);
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		RoundedButton SwitchRole = new RoundedButton("Switch the role", 50);
		SwitchRole.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 30));
		SwitchRole.setForeground(new Color(247, 239, 138));
		SwitchRole.setBackground(new Color(204, 27, 27));
		SwitchRole.setBounds(13, 780, 253, 60);
		SwitchRole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchRolePage enter = new SwitchRolePage();
				dispose();

				enter.setVisible(true);

			}
		});

		RoundedButton Exit = new RoundedButton("Exit", 50);
		Exit.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 30));
		Exit.setForeground(new Color(247, 239, 138));
		Exit.setBackground(new Color(204, 27, 27));
		Exit.setBounds(13, 880, 253, 60);
		Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage enter = new LoginPage();
				dispose();

				enter.setVisible(true);

			}
		});

		/*
		 * addWindowFocusListener(new WindowAdapter() {
		 * 
		 * @Override public void windowGainedFocus(WindowEvent e) { refreshUserData(); }
		 * });
		 */

		YouSpent1 = new JLabel();
		YouSpent1.setBorder(null);
		YouSpent1.setForeground(new Color(0, 0, 0));
		YouSpent1.setText("You Spent:");
		YouSpent1.setBackground(new Color(255, 255, 255));
		YouSpent1.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 40));
		YouSpent1.setBounds(530, 389, 219, 47);

		YourBalance1 = new JLabel();
		YourBalance1.setBorder(null);
		YourBalance1.setForeground(new Color(0, 0, 0));
		YourBalance1.setText("Your Balance:");
		YourBalance1.setBackground(new Color(255, 255, 255));
		YourBalance1.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 40));
		YourBalance1.setBounds(1045, 389, 276, 47);

		MyProfile = new JLabel();
		MyProfile.setBorder(null);
		MyProfile.setForeground(new Color(247, 239, 138));
		MyProfile.setText("My Profile");
		MyProfile.setBackground(new Color(13, 146, 118));
		MyProfile.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 60));
		MyProfile.setBounds(763, 132, 267, 75);

		MoneySpent = new JLabel();
		MoneySpent.setBorder(null);
		MoneySpent.setForeground(new Color(247, 239, 138));
		MoneySpent.setText("Money Spent");
		MoneySpent.setBackground(new Color(204, 27, 27));
		MoneySpent.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 45));
		MoneySpent.setBounds(491, 276, 295, 54);

		CurrentB = new JLabel();
		CurrentB.setBorder(null);
		CurrentB.setForeground(new Color(247, 239, 138));
		CurrentB.setText("Current Balance");
		CurrentB.setBackground(new Color(204, 27, 27));
		CurrentB.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 45));
		CurrentB.setBounds(1010, 276, 356, 41);

		RecentlyYB = new JLabel();
		RecentlyYB.setBorder(null);
		RecentlyYB.setForeground(new Color(247, 239, 138));
		RecentlyYB.setText("Recently you bought:");
		RecentlyYB.setBackground(new Color(13, 146, 118));
		RecentlyYB.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 40));
		RecentlyYB.setBounds(688, 719, 419, 48);

		WelcomeUser = new JLabel();
		WelcomeUser.setBorder(null);
		WelcomeUser.setForeground(new Color(247, 239, 138));
		WelcomeUser.setText("Welcome, User ...");
		WelcomeUser.setBackground(new Color(247, 239, 138));
		WelcomeUser.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 25));
		WelcomeUser.setBounds(35, 169, 213, 30);

		MoneySpentT = new JPanel();
		MoneySpentT.setBounds(391, 236, 470, 460);
		MoneySpentT.setBackground(new Color(204, 27, 27));

		YouSpentT = new JPanel();
		YouSpentT.setBounds(431, 366, 391, 306);
		YouSpentT.setBackground(new Color(255, 255, 255));

		YourBalanceT = new JPanel();
		YourBalanceT.setBounds(974, 365, 391, 306);
		YourBalanceT.setBackground(new Color(255, 255, 255));

		TableHistory = new JPanel();
		TableHistory.setBounds(431, 778, 933, 130);
		TableHistory.setBackground(new Color(255, 255, 255));

		MoneySpentT = new JPanel();
		MoneySpentT.setBounds(391, 236, 470, 460);
		MoneySpentT.setBackground(new Color(204, 27, 27));

		CurrentBalT = new JPanel();
		CurrentBalT.setBounds(934, 236, 470, 460);
		CurrentBalT.setBackground(new Color(204, 27, 27));

		RecentlyBT = new JPanel();
		RecentlyBT.setBounds(391, 712, 1013, 210);
		RecentlyBT.setBackground(new Color(204, 27, 27));

		PanelRight = new JPanel();
		PanelRight = new RoundedPanel(25, new Color(13, 146, 118)); // 30 is the corner radius
		PanelRight.setBounds(0, 0, 280, 955);

		PanelCenter = new JPanel();
		PanelCenter = new RoundedPanel(25, new Color(13, 146, 118)); // 30 is the corner radius
		PanelCenter.setBounds(317, 90, 1159, 850);

		lmageBack = new JLabel("New label");
		lmageBack.setIcon(
				new ImageIcon(UserProfilePage.class.getResource("/testscene/ImagesHMS/output-onlinepngtools.png")));
		lmageBack.setBounds(0, 0, 1503, 982);

		UserIcon = new JLabel("New label");
		UserIcon.setIcon(new ImageIcon(UserProfilePage.class
				.getResource("/testscene/ImagesHMS/7b93f6f63c57e7e568f98c79671af7f5_resized.png")));
		UserIcon.setBounds(77, 29, 125, 125);

		contentPane.add(YouSpent1);
		contentPane.add(YourBalance1);
		contentPane.add(TableHistory);
		contentPane.add(YourBalanceT);
		YourBalanceT.setLayout(null);

		YourBalanceLabel = new JLabel("£0.00");
		YourBalanceLabel.setForeground(new Color(52, 169, 81));
		YourBalanceLabel.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 35));
		YourBalanceLabel.setBounds(145, 165, 200, 37);
		YourBalanceT.add(YourBalanceLabel);
		contentPane.add(YouSpentT);
		YouSpentT.setLayout(null);

		MoneySpentLabel = new JLabel("£0.00");
		MoneySpentLabel.setForeground(new Color(52, 169, 81));
		MoneySpentLabel.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 35));
		MoneySpentLabel.setBounds(145, 165, 300, 37);
		YouSpentT.add(MoneySpentLabel);

		contentPane.add(UserIcon);
		contentPane.add(WelcomeUser);
		contentPane.add(RecentlyYB);
		contentPane.add(CurrentB);
		contentPane.add(MoneySpent);
		contentPane.add(MyProfile);
		contentPane.add(MoneySpentT);
		contentPane.add(CurrentBalT);
		contentPane.add(RecentlyBT);
		contentPane.add(Menu);
		contentPane.add(Allergies);
		contentPane.add(SwitchRole);
		contentPane.add(Exit);
		contentPane.add(PanelRight);
		contentPane.add(PanelCenter);
		

		contentPane.add(lmageBack);

	}
}
