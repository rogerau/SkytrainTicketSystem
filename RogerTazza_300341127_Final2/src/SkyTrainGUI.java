import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SkyTrainGUI extends JFrame {

	private JPanel contentPane;

	private static Connection connection;
	private static Statement statement;
	private static ResultSet resultSet;

	private static final String MSA_FILE = "./SkytrainFare.accdb";
	private static final String JDBC_DRIVER = "net.ucanaccess.jdbc.UcanaccessDriver";
	private static final String DB_URL = "jdbc:ucanaccess://" + MSA_FILE;

	private static ArrayList<Station> stationList = new ArrayList<Station>();
	private JScrollPane sPane1;
	private JList<Station> list1;
	private JRadioButton rdbtn1;
	private JRadioButton rdbtn2;
	private JRadioButton rdbtn3;
	private ButtonGroup paymentMethod;
	private ButtonGroup typeTicketMethod;
	private JScrollPane sPane2;
	private JList<Station> list2;
	private JRadioButton rdbtn4;
	private JRadioButton rdbtn5;
	private JButton btn1;
	private JButton btn2;
	private JTextField txt2;
	private DefaultListModel<Station> model1;
	private DefaultListModel<Station> model2;
	private JTextField txt1;

	double total = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SkyTrainGUI frame = new SkyTrainGUI();
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
	public SkyTrainGUI() {

		setComponents();
		setEvents();
	}

	public void readDB() {
		initDB();
		String readQuery = "SELECT * FROM STATIONS";
		try {
			resultSet = statement.executeQuery(readQuery);
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String line = resultSet.getString(2);
				String name = resultSet.getString(3);
				int zone = resultSet.getInt(4);
				Station aStation = new Station(line, name, zone);
				stationList.add(aStation);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeDB();
	}

	public void closeDB() {

		try {
			if (statement != null) {
				statement.close();
			}
			if (resultSet != null) {
				resultSet.close();
			}

			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void initDB() {
		try {
			Class.forName(JDBC_DRIVER);
			try {
				connection = DriverManager.getConnection(DB_URL);
				statement = connection.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setEvents() {
		if (list1.getSelectedIndex() == -1 || list2.getSelectedIndex() == -1 || !rdbtn1.isSelected()
				|| !rdbtn2.isSelected() || !rdbtn3.isSelected() || !rdbtn4.isSelected() || !rdbtn5.isSelected()) {
			JOptionPane.showMessageDialog(null, "Warning!. You must select an option from the panel");
		}
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int selectedIndex1 = list1.getSelectedIndex();
				int selectedIndex2 = list2.getSelectedIndex();
				if (stationList.get(selectedIndex2).getZone() == 1 && stationList.get(selectedIndex1).getZone() == 1) {
					if (rdbtn1.isSelected()) {
						if (rdbtn4.isSelected()) {
							total += 3.05;
						} else {
							total += 2;
						}
					} else if (rdbtn2.isSelected()) {
						if (rdbtn4.isSelected()) {
							total += 3.05;
						} else {
							total += 0;
						}
					} else {
						if (rdbtn4.isSelected()) {
							total += 2.45;
						} else {
							total += 2;
						}
					}
				} else if (stationList.get(selectedIndex2).getZone() == 2
						&& stationList.get(selectedIndex1).getZone() == 1) {
					if (rdbtn1.isSelected()) {
						if (rdbtn4.isSelected()) {
							total += 4.35;
						} else {
							total += 3;
						}
					} else if (rdbtn2.isSelected()) {
						if (rdbtn4.isSelected()) {
							total += 4.35;
						} else {
							total += 0;
						}
					} else {
						if (rdbtn4.isSelected()) {
							total += 3.55;
						} else {
							total += 3;
						}
					}
				} else if (stationList.get(selectedIndex2).getZone() == 1
						&& stationList.get(selectedIndex1).getZone() == 3) {
					if (rdbtn1.isSelected()) {
						if (rdbtn4.isSelected()) {
							total += 5.9;
						} else {
							total += 4.05;
						}
					} else if (rdbtn2.isSelected()) {
						if (rdbtn4.isSelected()) {
							total += 5.90;
						} else {
							total += 0;
						}
					} else {
						if (rdbtn4.isSelected()) {
							total += 4.60;
						} else {
							total += 4.05;
						}
					}
				} else if (stationList.get(selectedIndex2).getZone() == 2
						&& stationList.get(selectedIndex1).getZone() == 2) {
					if (rdbtn1.isSelected()) {
						if (rdbtn4.isSelected()) {
							total += 4.35;
						} else {
							total += 3;
						}
					} else if (rdbtn2.isSelected()) {
						if (rdbtn4.isSelected()) {
							total += 4.35;
						} else {
							total += 0;
						}
					} else {
						if (rdbtn4.isSelected()) {
							total += 3.55;
						} else {
							total += 3;
						}
					}
				} else {
					if (rdbtn1.isSelected()) {
						if (rdbtn4.isSelected()) {
							total += 5.9;
						} else {
							total += 4.05;
						}
					} else if (rdbtn2.isSelected()) {
						if (rdbtn4.isSelected()) {
							total += 5.90;
						} else {
							total += 0;
						}
					} else {
						if (rdbtn4.isSelected()) {
							total += 4.60;
						} else {
							total += 4.05;
						}
					}
				}

				if (list2.getSelectedValue().toString().contains("YVR Airport")
						|| list2.getSelectedValue().toString().contains("Sea Island Centre")
						|| list2.getSelectedValue().toString().contains("Templeton")) {
					total += 5;
				}

				if (list1.getSelectedValue().toString().contains("YVR Airport")
						|| list1.getSelectedValue().toString().contains("Sea Island Centre")
						|| list1.getSelectedValue().toString().contains("Templeton")) {
					total += 0;
				}

				if ((list2.getSelectedValue().toString().contains("YVR Airport")
						&& list1.getSelectedValue().toString().contains("YVR Airport"))
						|| (list2.getSelectedValue().toString().contains("Sea Island Centre")
								&& list1.getSelectedValue().toString().contains("Sea Island Centre"))
						|| (list2.getSelectedValue().toString().contains("Templeton")
								&& list1.getSelectedValue().toString().contains("Templeton"))) {
					total = 0;
				}

				if (rdbtn4.isSelected()) {
					if (rdbtn1.isSelected()) {
						JOptionPane.showMessageDialog(null,
								"Type of ticket selected: " + rdbtn4.getText() + "\n" + "Payment method: "
										+ rdbtn1.getText() + "\n" + "From: " + stationList.get(selectedIndex2) + "\n"
										+ "To: " + stationList.get(selectedIndex1) + "\n" + "Fare price: $"
										+ String.format("%.2f", total));
					} else if (rdbtn2.isSelected()) {
						JOptionPane.showMessageDialog(null,
								"Type of ticket selected: " + rdbtn4.getText() + "\n" + "Payment method: "
										+ rdbtn2.getText() + "\n" + "From: " + stationList.get(selectedIndex2) + "\n"
										+ "To: " + stationList.get(selectedIndex1) + "\n" + "Fare price: $"
										+ String.format("%.2f", total));
					} else {
						JOptionPane.showMessageDialog(null,
								"Type of ticket selected: " + rdbtn4.getText() + "\n" + "Payment method: "
										+ rdbtn3.getText() + "\n" + "From: " + stationList.get(selectedIndex2) + "\n"
										+ "To: " + stationList.get(selectedIndex1) + "\n" + "Fare price: $"
										+ String.format("%.2f", total));
					}

				} else {
					if (rdbtn1.isSelected()) {
						JOptionPane.showMessageDialog(null,
								"Type of ticket selected: " + rdbtn5.getText() + "\n" + "Payment method: "
										+ rdbtn1.getText() + "\n" + "From: " + stationList.get(selectedIndex2) + "\n"
										+ "To: " + stationList.get(selectedIndex1) + "\n" + "Fare price: $"
										+ String.format("%.2f", total));
					} else if (rdbtn2.isSelected()) {
						JOptionPane.showMessageDialog(null,
								"Type of ticket selected: " + rdbtn5.getText() + "\n" + "Payment method: "
										+ rdbtn2.getText() + "\n" + "From: " + stationList.get(selectedIndex2) + "\n"
										+ "To: " + stationList.get(selectedIndex1) + "\n" + "Fare price: $"
										+ String.format("%.2f", total));
					} else {
						JOptionPane.showMessageDialog(null,
								"Type of ticket selected: " + rdbtn5.getText() + "\n" + "Payment method: "
										+ rdbtn3.getText() + "\n" + "From: " + stationList.get(selectedIndex2) + "\n"
										+ "To: " + stationList.get(selectedIndex1) + "\n" + "Fare price: $"
										+ String.format("%.2f", total));

					}
				}

			}

		});

		btn2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				paymentMethod.clearSelection();
				typeTicketMethod.clearSelection();
				list1.clearSelection();
				list2.clearSelection();
			}

		});
	}

	public void setComponents() {

		readDB();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 461, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		sPane1 = new JScrollPane();

		list1 = new JList<Station>();
		sPane1.setViewportView(list1);

		sPane2 = new JScrollPane();

		list2 = new JList<Station>();
		sPane2.setViewportView(list2);

		model1 = new DefaultListModel<Station>();
		model2 = new DefaultListModel<Station>();
		for (int i = 0; i < stationList.size(); i++) {
			model1.addElement(stationList.get(i));
			model2.addElement(stationList.get(i));
		}

		list1.setModel(model1);
		list2.setModel(model2);

		paymentMethod = new ButtonGroup();
		typeTicketMethod = new ButtonGroup();
		rdbtn1 = new JRadioButton("Cash Fare");
		paymentMethod.add(rdbtn1);

		rdbtn2 = new JRadioButton("Contactless Payment Fare");
		paymentMethod.add(rdbtn2);

		rdbtn3 = new JRadioButton("Stored Value");
		paymentMethod.add(rdbtn3);

		rdbtn4 = new JRadioButton("Adult");
		typeTicketMethod.add(rdbtn4);

		rdbtn5 = new JRadioButton("Concession");
		typeTicketMethod.add(rdbtn5);
		btn1 = new JButton("CHECK FARE");

		btn2 = new JButton("RESET");

		txt1 = new JTextField();
		txt1.setText("From:");
		txt1.setColumns(10);

		txt2 = new JTextField();
		txt2.setText("To:");
		txt2.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
						.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
								.createSequentialGroup().addGap(16)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup().addComponent(rdbtn4)
												.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(rdbtn5))
										.addGroup(gl_contentPane.createSequentialGroup().addComponent(rdbtn1)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(rdbtn2))))
								.addGroup(gl_contentPane.createSequentialGroup().addGap(39)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(txt1, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(sPane2, GroupLayout.PREFERRED_SIZE, 145,
														GroupLayout.PREFERRED_SIZE))))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(txt2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup().addGap(10).addComponent(rdbtn3))
								.addComponent(sPane1, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(77).addComponent(btn1).addGap(18)
								.addComponent(btn2)))
				.addContainerGap(62, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(39).addComponent(sPane1,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(txt1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(txt2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(sPane2, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(rdbtn1)
						.addComponent(rdbtn2).addComponent(rdbtn3))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(rdbtn4)
						.addComponent(rdbtn5))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btn1).addComponent(btn2))
				.addContainerGap(73, Short.MAX_VALUE)));

		contentPane.setLayout(gl_contentPane);
	}
}
