package testscene;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import testscene.database;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class AdminMainPage1 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel TableLeft;
	private JPanel TableRight;
	private JPanel TableCenter;
	private JTable ContentPanel;
	private JPanel PanelMenuTool;
	private JTextField SearchBar;
	private JTextField productNameField;
	private JTextField stockField;
	private JTextField priceField;
	private JComboBox statusField;
	private JLabel WelcomeAdmin;
	private JLabel UserIcon;
	private JLabel SearchIcon;
	private JLabel SearchBarPanel;
	private JLabel ImageIcon;
	private JLabel lmageBack;
	private JLabel Allergies;
	private JScrollPane scrollPane;
	private int idCounter = 1;
	private ResultSet result;

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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMainPage1 frame = new AdminMainPage1();
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
	String[] options = { "-Options-", "Available", "Not Available" };

	public AdminMainPage1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(2114, 2811, 1503, 982);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);

		// Define the table model with infinite rows
		DefaultTableModel model = new DefaultTableModel(
				new Object[] { "ID", "Product Name", "Stock", "Price", "Status", }, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// Make all cells editable
				return false;
			}

			@Override
			public void setValueAt(Object aValue, int row, int column) {
				super.setValueAt(aValue, row, column);
				if (row == getRowCount() - 1) {
					// Add a new row if the last row is edited
					addRow(new Object[] { null, null, null, null, null });
				}
			}
		};

		// Initialize the table with one empty row
		model.addRow(new Object[] { null, null, null, null, null });

		ContentPanel = new JTable(model);
		ContentPanel.setGridColor(Color.BLACK);
		ContentPanel.getTableHeader().setDefaultRenderer((TableCellRenderer) new HeaderRenderer());

		DefaultTableModel model1 = (DefaultTableModel) ContentPanel.getModel();
		model1.setRowCount(0); // Clear existing data

		String query = "SELECT * FROM FoodCompleted"; // Adjust the query as needed
		Connection connect = database.connectDB(); // Make sure this method returns a valid connection

		try {
			PreparedStatement prepare = connect.prepareStatement(query);
			ResultSet result = prepare.executeQuery();

			while (result.next()) {
				// Assuming your table columns are productName, stock, price, status in this
				// order
				int id = result.getInt("ID");
				String productName = result.getString("Name");
				int stock = result.getInt("Quantity");
				String price = result.getString("Price");
				String status = result.getString("Available");

				model1.addRow(new Object[] { id, productName, stock, price, status }); // Adjust the array as per your
																						// table structure
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error loading data from database: " + e.getMessage(), "Database Error",
					JOptionPane.ERROR_MESSAGE);
		} finally {
			try {
				if (connect != null)
					connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		scrollPane = new JScrollPane(ContentPanel);
		scrollPane.setBounds(355, 187, 700, 700);

		scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(scrollPane);

		RoundedButton MyProfile = new RoundedButton("My Profile", 50);
		MyProfile.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 30));
		MyProfile.setForeground(new Color(255, 255, 255));
		MyProfile.setBackground(new Color(29, 200, 244));
		MyProfile.setBounds(13, 220, 253, 60);
		MyProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserProfilePage enter = new UserProfilePage();
				dispose();

				enter.setVisible(true);

			}
		});

		Allergies = new JLabel("     Admin Panel");
		Allergies.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 30));
		Allergies.setForeground(new Color(255, 255, 255));
		Allergies.setBackground(new Color(29, 200, 244));
		Allergies.setOpaque(true);
		Allergies.setBounds(13, 310, 253, 60);

		RoundedButton SwitchRole = new RoundedButton("Switch the role", 50);
		SwitchRole.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 30));
		SwitchRole.setForeground(new Color(255, 255, 255));
		SwitchRole.setBackground(new Color(29, 200, 244));
		SwitchRole.setBounds(13, 795, 253, 60);
		// Signup.setBorder(roundedBorder);
		SwitchRole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserProfilePage enter = new UserProfilePage();
				dispose();

				enter.setVisible(true);

			}
		});

		RoundedButton Exit = new RoundedButton("Exit", 50);
		Exit.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 30));
		Exit.setForeground(new Color(255, 255, 255));
		Exit.setBackground(new Color(204, 27, 27));
		Exit.setBounds(13, 885, 253, 60);
		// Signup.setBorder(roundedBorder);
		Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage enter = new LoginPage();
				dispose();

				enter.setVisible(true);

			}
		});

		RoundedButton Insert = new RoundedButton("Insert", 50);
		Insert.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 30));
		Insert.setForeground(new Color(255, 255, 255));
		Insert.setBackground(new Color(29, 200, 244));
		Insert.setBounds(1207, 456, 229, 71);
		// Signup.setBorder(roundedBorder);
		Insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Create a file chooser
				JFileChooser fileChooser = new JFileChooser();
				// Set the current directory to user's home or any specific path
				fileChooser.setCurrentDirectory(new java.io.File("."));
				// Filter the files to show only images
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "jpg", "png", "gif", "bmp");
				fileChooser.setFileFilter(filter);
				// Set to false if you want to allow multiple file selections
				fileChooser.setMultiSelectionEnabled(false);

				// Show open dialog; this method does not return until the dialog is closed
				int returnValue = fileChooser.showOpenDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					java.io.File selectedFile = fileChooser.getSelectedFile();
					// Create an ImageIcon from the selected file's path
					ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
					// Resize the ImageIcon to fit the JLabel
					Image image = imageIcon.getImage().getScaledInstance(ImageIcon.getWidth(), ImageIcon.getHeight(),
							Image.SCALE_SMOOTH);
					// Set the resized ImageIcon as the icon of the JLabel
					ImageIcon.setIcon(new ImageIcon(image));
				}
			}
		});

		productNameField = new JTextField();
		productNameField.setText("Enter the Product Name");
		productNameField.addFocusListener(new FocusListener() {

			public void focusGained(FocusEvent e) {
				if (productNameField.getText().equals("Enter the Product Name")) {
					productNameField.setText("");
					productNameField.setForeground(Color.BLACK);
				}
			}

			public void focusLost(FocusEvent e) {
				if (productNameField.getText().equals("")) {
					productNameField.setText("Enter the Product Name");
					productNameField.setForeground(new Color(0, 0, 0, 20));
				}
			}
		});
		productNameField.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
		productNameField.setForeground(new Color(0, 0, 0, 50));
		productNameField.setBounds(13, 400, 253, 30);

		stockField = new JTextField();
		stockField.setText("Enter the Quantity");
		stockField.addFocusListener(new FocusListener() {

			public void focusGained(FocusEvent e) {
				if (stockField.getText().equals("Enter the Quantity")) {
					stockField.setText("");
					stockField.setForeground(Color.BLACK);
				}
			}

			public void focusLost(FocusEvent e) {
				if (stockField.getText().equals("")) {
					stockField.setText("Enter the Quantity");
					stockField.setForeground(new Color(0, 0, 0, 20));
				}
			}
		});
		stockField.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
		stockField.setForeground(new Color(0, 0, 0, 50));
		stockField.setBounds(13, 440, 253, 30); // Adjust the Y value to position below the productNameField

		priceField = new JTextField();
		priceField.setText("Enter the Price");
		priceField.addFocusListener(new FocusListener() {

			public void focusGained(FocusEvent e) {
				if (priceField.getText().equals("Enter the Price")) {
					priceField.setText("");
					priceField.setForeground(Color.BLACK);
				}
			}

			public void focusLost(FocusEvent e) {
				if (priceField.getText().equals("")) {
					priceField.setText("Enter the Price");
					priceField.setForeground(new Color(0, 0, 0, 20));
				}
			}
		});
		priceField.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
		priceField.setForeground(new Color(0, 0, 0, 50));
		priceField.setBounds(13, 480, 253, 30); // Adjust the Y value to position below the stockField

		statusField = new JComboBox(options);
		statusField.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
		// Set bounds as per your layout requirements
		statusField.setBounds(13, 520, 253, 30); // Adjust the Y value to position below the priceField
		statusField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == statusField) {
					System.out.print(statusField.getSelectedItem());
				}
			}
		});

		RoundedButton Remove = new RoundedButton("Remove", 20);
		Remove.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 30));
		Remove.setForeground(new Color(255, 255, 255));
		Remove.setBackground(new Color(203, 203, 49));
		Remove.setBounds(1144, 751, 165, 71);
		Remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				productNameField.setText("");
				stockField.setText("");
				priceField.setText("");
				statusField.setSelectedIndex(0); // Assuming the first item is the "select" or similar

				ImageIcon.setIcon(null);
			}
		});

		RoundedButton Add = new RoundedButton("Add", 20);
		Add.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 30));
		Add.setForeground(new Color(255, 255, 255));
		Add.setBackground(new Color(89, 190, 28));
		Add.setBounds(1336, 751, 165, 71);

		Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String productName = productNameField.getText().trim();
				String stock = stockField.getText().trim();
				String price = priceField.getText().trim();
				String status = statusField.getSelectedItem().toString().trim();

				if (!productName.isEmpty() && !stock.isEmpty() && !price.isEmpty() && !status.isEmpty()) {
					String insertProductSQL = "INSERT INTO FoodCompleted (Name, Quantity, Price, Available) VALUES (?, ?, ?, ?)";
					Connection connect = database.connectDB();
					try {
						PreparedStatement prepare = connect.prepareStatement(insertProductSQL,
								Statement.RETURN_GENERATED_KEYS);
						prepare.setString(1, productName);
						prepare.setString(2, stock);
						prepare.setString(3, price);
						prepare.setString(4, status);

						int rowsAffected = prepare.executeUpdate();
						if (rowsAffected > 0) {
							System.out.println("Insert successful");

							// Retrieve the generated ID
							try (ResultSet generatedKeys = prepare.getGeneratedKeys()) {
								if (generatedKeys.next()) {
									long id = generatedKeys.getLong(1); // Retrieve the generated ID

									// Now add this ID along with other product details to the table
									DefaultTableModel model = (DefaultTableModel) ContentPanel.getModel();
									model.addRow(new Object[] { id, productName, stock, price, status });
								} else {
									throw new SQLException("Creating product failed, no ID obtained.");
								}
							}
						}

						// Clear the input fields
						productNameField.setText("");
						stockField.setText("");
						priceField.setText("");
						statusField.setSelectedIndex(-1); // Reset the JComboBox selection
					} catch (SQLException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error while inserting product: " + e1.getMessage(),
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please enter all fields.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}

			public void updatemenu() {

			}
		});

		RoundedButton Delete = new RoundedButton("Delete", 20);
		Delete.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 30));
		Delete.setForeground(new Color(255, 255, 255));
		Delete.setBackground(new Color(204, 27, 27));
		Delete.setBounds(1144, 849, 165, 71);
		// Signup.setBorder(roundedBorder);
		Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = ContentPanel.getSelectedRow();
				if (selectedRow >= 0) {
					Object id = ContentPanel.getValueAt(selectedRow, 0); // Assuming ID is in the first column
					String deleteSQL = "DELETE FROM FoodCompleted WHERE id = ?";

					try (Connection connect = database.connectDB();
							PreparedStatement prepare = connect.prepareStatement(deleteSQL)) {

						prepare.setObject(1, id);
						int affectedRows = prepare.executeUpdate();

						if (affectedRows > 0) {
							System.out.println("Delete successful");
							((DefaultTableModel) ContentPanel.getModel()).removeRow(selectedRow);
						}
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}
			}
		});

		RoundedButton Change = new RoundedButton("Update", 20);
		Change.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 30));
		Change.setForeground(new Color(255, 255, 255));
		Change.setBackground(new Color(190, 28, 174));
		Change.setBounds(1336, 849, 165, 71);
		Change.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = ContentPanel.getSelectedRow();
				if (selectedRow >= 0) {
					Object id = ContentPanel.getValueAt(selectedRow, 0); // ID from table
					String productName = productNameField.getText();
					String stock = stockField.getText();
					String price = priceField.getText();
					String status = statusField.getSelectedItem().toString();

					String updateSQL = "UPDATE FoodCompleted SET Name = ?, Quantity = ?, Price = ?, Available = ? WHERE id = ?";

					try (Connection connect = database.connectDB();
							PreparedStatement prepare = connect.prepareStatement(updateSQL)) {

						prepare.setString(1, productName);
						prepare.setInt(2, Integer.parseInt(stock));
						prepare.setDouble(3, Double.parseDouble(price));
						prepare.setString(4, status);
						prepare.setObject(5, id);

						int affectedRows = prepare.executeUpdate();

						if (affectedRows > 0) {
							System.out.println("Update successful");

							// Manually update the JTable row
							DefaultTableModel model = (DefaultTableModel) ContentPanel.getModel();
							model.setValueAt(productName, selectedRow, 1); // Assuming column 1 is productName
							model.setValueAt(stock, selectedRow, 2); // Assuming column 2 is stock
							model.setValueAt(price, selectedRow, 3); // Assuming column 3 is price
							model.setValueAt(status, selectedRow, 4); // Assuming column 4 is status
						}
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}
			}
		});

		SearchBar = new JTextField();
		SearchBar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				performSearch();
			}
		});
		SearchBar.setBorder(null); // no borders
		SearchBar.setMargin(new Insets(0, 0, 0, 0));
		SearchBar.setText("Search"); // Removed extra spaces for simplicity
		SearchBar.setForeground(new Color(0, 0, 0, 35)); // Adjusted for visibility
		SearchBar.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 40)); // Size of text
		SearchBar.setBounds(490, 31, 450, 61);
		SearchBar.setColumns(10);

		// Add FocusListener
		SearchBar.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (SearchBar.getText().trim().equals("Search")) {
					SearchBar.setText(""); // Clear "Search" when focus is gained
					SearchBar.setForeground(Color.BLACK); // Set to a more visible color
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (SearchBar.getText().trim().isEmpty()) {
					SearchBar.setText("Search"); // Restore "Search" if user hasn't entered anything
					SearchBar.setForeground(new Color(0, 0, 0, 35)); // Restore placeholder text color
				}
			}
		});

		WelcomeAdmin = new JLabel();
		WelcomeAdmin.setBorder(null);
		WelcomeAdmin.setForeground(new Color(255, 255, 255));
		WelcomeAdmin.setText("Welcome, Admin");
		WelcomeAdmin.setBackground(new Color(12, 119, 194));
		WelcomeAdmin.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 25));
		WelcomeAdmin.setBounds(35, 169, 213, 30);

		PanelMenuTool = new RoundedPanel(50, new Color(178, 178, 178));
		PanelMenuTool.setBounds(1176, 160, 289, 274);

		TableLeft = new RoundedPanel(25, new Color(2, 118, 194));
		TableLeft.setBounds(0, 0, 280, 958);

		TableRight = new RoundedPanel(25, new Color(2, 118, 194));
		TableRight.setBounds(1120, 111, 383, 843);

		TableCenter = new RoundedPanel(50, new Color(2, 118, 194));
		TableCenter.setBounds(311, 160, 789, 756);

		lmageBack = new JLabel("New label");
		lmageBack.setIcon(
				new ImageIcon(AdminMainPage1.class.getResource("/testscene/ImagesHMS/output-onlinepngtools.png")));
		lmageBack.setBounds(0, 0, 1503, 982);

		UserIcon = new JLabel("New label");
		UserIcon.setIcon(new ImageIcon(
				AdminMainPage1.class.getResource("/testscene/ImagesHMS/7b93f6f63c57e7e568f98c79671af7f5_resized.png")));
		UserIcon.setBounds(77, 29, 125, 125);

		SearchIcon = new JLabel("New label");
		SearchIcon.setIcon(new ImageIcon(AdminMainPage1.class
				.getResource("/testscene/ImagesHMS/f3012b96902d816d28e1503545a493ed-2_resized.png")));
		SearchIcon.setBounds(443, 42, 40, 40);

		contentPane.add(UserIcon);
		contentPane.add(SearchIcon);
		contentPane.add(MyProfile);
		contentPane.add(Allergies);
		contentPane.add(SwitchRole);
		contentPane.add(Exit);

		contentPane.add(Insert);
		contentPane.add(Remove);
		contentPane.add(Add);
		contentPane.add(Delete);
		contentPane.add(Change);

		contentPane.add(productNameField);
		contentPane.add(stockField);
		contentPane.add(priceField);
		contentPane.add(statusField);
		contentPane.add(WelcomeAdmin);
		contentPane.add(PanelMenuTool);
		PanelMenuTool.setLayout(null);

		ImageIcon = new JLabel("            Place an Image ");
		ImageIcon.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		ImageIcon.setBounds(10, 7, 269, 259);
		PanelMenuTool.add(ImageIcon);

		SearchBarPanel = new JLabel("New label");
		SearchBarPanel.setIcon(
				new ImageIcon(AdminMainPage1.class.getResource("/testscene/ImagesHMS/rounded_rectangle-8.png")));
		SearchBarPanel.setBounds(424, 29, 563, 65);

		contentPane.add(SearchBar);
		contentPane.add(SearchBarPanel);
		contentPane.add(TableLeft);
		contentPane.add(TableRight);
		contentPane.add(TableCenter);
		contentPane.add(lmageBack);
	}

	class HeaderRenderer implements TableCellRenderer {
		DefaultTableCellRenderer renderer;

		public HeaderRenderer() {
			renderer = (DefaultTableCellRenderer) ContentPanel.getTableHeader().getDefaultRenderer();
			renderer.setHorizontalAlignment(JLabel.CENTER);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			JLabel headerComponent = (JLabel) renderer.getTableCellRendererComponent(table, value, isSelected, hasFocus,
					row, column);

			// Draw a line at the bottom of each column header
			headerComponent.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
			return headerComponent;
		}
	}

	private void performSearch() {
		String searchText = SearchBar.getText().trim();
		DefaultTableModel model = (DefaultTableModel) ContentPanel.getModel();
		model.setRowCount(0); // Clear existing data

		String query = "SELECT * FROM FoodCompleted WHERE Name LIKE ?";
		try (Connection connect = database.connectDB(); PreparedStatement prepare = connect.prepareStatement(query)) {

			prepare.setString(1, "%" + searchText + "%"); // Set search text
			ResultSet result = prepare.executeQuery();

			while (result.next()) {
				int id = result.getInt("ID");
				String productName = result.getString("Name");
				int stock = result.getInt("Quantity");
				String price = result.getString("Price");
				String status = result.getString("Available");

				model.addRow(new Object[] { id, productName, stock, price, status });
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error performing search: " + ex.getMessage(), "Database Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
