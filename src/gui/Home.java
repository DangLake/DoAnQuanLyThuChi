package gui;

import java.util.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.toedter.calendar.JDateChooser;

import dao.GetThuChi;
import dao.budgetsDao;
import dao.categoriesDao;
import dao.transactionsDao;
import dao.userDao;
import model.budgets;
import model.categories;
import model.transactions;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import com.toedter.calendar.JYearChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JDayChooser;

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmNewMenuItem;
	private JTabbedPane tabbedPane;
	private JPanel Home;
	private JPanel Categories;
	private JPanel Transactions;
	private JPanel Baocao;
	private JPanel Budgets;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField txtTenDM;
	private JRadioButton rdbtnchi;
	private JRadioButton rdbtnthu;
	private JScrollPane scrollPane;
	private JTable tableCate;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JTextField txttien;
	private JTextField txtMota;
	private JComboBox<categories> comboBoxCateTran;
	private JButton btnthemTran;
	private JButton btnXoaTran;
	private JButton btnSuaTran;
	private JScrollPane scrollPane_1;
	private JTable tableTran;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_10;
	private JLabel lblNewLabel_11;
	private JComboBox<categories> comboxCateBud;
	private JTextField txtTienBud;
	private JScrollPane scrollPane_2;
	private JTable tableBud;
	private JButton btnThemBud;
	private JButton btnXoaBud;
	private JButton btnSuaBud;
	private JScrollPane scrollPane_4;
	private JTable tableBaocao;
	private JDateChooser dateChooserTran;
	private JDateChooser NgayBDBud;
	private JDateChooser NgayKTBud;
	private ButtonGroup groupCate;
	DefaultComboBoxModel<categories> cbbCate = new DefaultComboBoxModel<categories>();
	DefaultComboBoxModel<budgets> cbbBud = new DefaultComboBoxModel<budgets>();
	userDao uDao = new userDao();
	private List<categories> dsCate = new ArrayList<categories>();
	categoriesDao catDao = new categoriesDao();

	DefaultTableModel modelCate = new DefaultTableModel();
	DefaultTableModel modelTran = new DefaultTableModel();
	DefaultTableModel modelBud = new DefaultTableModel();
	DefaultTableModel modelNoti = new DefaultTableModel();
	DefaultTableModel modelBaocao = new DefaultTableModel();
	private List<transactions> dsTran = new ArrayList<transactions>();
	transactionsDao tranDao = new transactionsDao();
	budgetsDao budDao = new budgetsDao();
	private List<budgets> dsBud = new ArrayList<budgets>();
	
	private static int currentUserID;
	private JLabel lbthu;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_12;
	private JLabel lbChi;
	GetThuChi thuchidao = new GetThuChi();
	private JYearChooser yearChooser;
	private JMonthChooser monthChooser;
	private final JButton btnloc = new JButton("Loc");
	private JButton btnboloc;
	
	
	private DefaultCategoryDataset dataset;
	private JFreeChart chart;
	private CategoryPlot categoryPlot;
	private ChartPanel chartPanel;
	private JLabel lblSoDu;
	private JLabel lblNewLabel_2;
	
	
	
	

	public static int getCurrentUserID() {
		return currentUserID;
	}

	public static void setCurrentUserID(int UserID) {
		currentUserID = UserID;
	}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home(getCurrentUserID());
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
	public Home(int userID) {
		setTitle("Main");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 783, 530);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		currentUserID = userID;

		mnNewMenu = new JMenu("Setting");
		menuBar.add(mnNewMenu);

		mntmNewMenuItem = new JMenuItem("Log out");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "Log out?", "Thông báo", JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) {
					Login_Sign fm = new Login_Sign();
					dispose();
					fm.setVisible(true);
				} else {
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 770, 472);
		contentPane.add(tabbedPane);

		Home = new JPanel();
		tabbedPane.addTab("Home", null, Home, null);
		Home.setLayout(null);

		Categories = new JPanel();
		tabbedPane.addTab("Categories", null, Categories, null);
		Categories.setLayout(null);

		lblNewLabel = new JLabel("Tên danh mục");
		lblNewLabel.setBounds(51, 40, 85, 13);
		Categories.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Loại danh mục");
		lblNewLabel_1.setBounds(51, 86, 85, 13);
		Categories.add(lblNewLabel_1);

		txtTenDM = new JTextField();
		txtTenDM.setBounds(170, 37, 173, 19);
		Categories.add(txtTenDM);
		txtTenDM.setColumns(10);

		rdbtnchi = new JRadioButton("Chi tiêu");
		rdbtnchi.setBounds(173, 82, 103, 21);
		Categories.add(rdbtnchi);

		rdbtnthu = new JRadioButton("Thu nhập");
		rdbtnthu.setBounds(278, 82, 103, 21);
		Categories.add(rdbtnthu);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 199, 706, 209);
		Categories.add(scrollPane);

		tableCate = new JTable();
		tableCate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tableCate.getSelectedRow();
				if (index != -1) {
					categories ca = dsCate.get(index);
					txtTenDM.setText(ca.getName());
					if (ca.isType()) {
						rdbtnchi.setSelected(true);
					} else {
						rdbtnthu.setSelected(true);
					}
				}
			}
		});
		scrollPane.setViewportView(tableCate);

		btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtTenDM.getText();
				boolean type;
				if (rdbtnchi.isSelected()) {
					type = true;
				} else {
					type = false;
				}

				categories ca = new categories(name, type);
				if (catDao.save(ca, getCurrentUserID())) {
					dsCate.add(ca);
					String types;
					if (ca.isType()) {
						types = "Chi tieu";
					} else {
						types = "Thu nhap";
					}
					modelCate.addRow(new Object[] { ca.getCategory_id(), ca.getName(), types });
					cbbCate.addElement(ca);
					comboBoxCateTran.setModel(cbbCate);
					comboxCateBud.setModel(cbbCate);
					JOptionPane.showMessageDialog(null, "Lưu thành công");
					updateBaocaoModel();
					updateChart();
				} else {
					JOptionPane.showMessageDialog(null, "Lưu That bai");
				}
			}
		});
		btnThem.setBounds(510, 36, 85, 21);
		Categories.add(btnThem);

		btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = tableCate.getSelectedRow();
				if (index != -1) {
					categories ca = dsCate.get(index);
					if (catDao.delete(ca.getCategory_id(), getCurrentUserID())) {
						dsCate.remove(index);
						modelCate.removeRow(index);
						comboBoxCateTran.removeItem(ca);
						comboxCateBud.removeItem(ca);
						updateBaocaoModel();
						updateChart();
					} else {
						JOptionPane.showMessageDialog(null, "Xoa That bai");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Chon muc de xoa");
				}
			}
		});
		btnXoa.setBounds(510, 82, 85, 21);
		Categories.add(btnXoa);

		btnSua = new JButton("Sửa");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = tableCate.getSelectedRow();
				String name = txtTenDM.getText();
				boolean type;
				String types;
				if (rdbtnchi.isSelected()) {
					type = true;
					types = "Chi tieu";
				} else {
					type = false;
					types = "Thu nhap";
				}
				if (index != -1) {
					categories ca = dsCate.get(index);
					ca.setName(name);
					ca.setType(type);
					if (catDao.update(ca, getCurrentUserID())) {
						dsCate.set(index, ca);
						modelCate.setValueAt(name, index, 1);
						modelCate.setValueAt(types, index, 2);
						JOptionPane.showMessageDialog(null, "sua thanh cong");
						updateBaocaoModel();
						updateChart();
					} else {
						JOptionPane.showMessageDialog(null, "sua that bai");
					}
				} else {
					JOptionPane.showMessageDialog(null, "vui long chon 1");
				}
			}
		});
		btnSua.setBounds(510, 132, 85, 21);
		Categories.add(btnSua);

		Transactions = new JPanel();
		tabbedPane.addTab("Transactions", null, Transactions, null);
		Transactions.setLayout(null);

		lblNewLabel_3 = new JLabel("Amount");
		lblNewLabel_3.setBounds(47, 38, 77, 13);
		Transactions.add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel("Ngày thực hiện");
		lblNewLabel_4.setBounds(47, 76, 98, 13);
		Transactions.add(lblNewLabel_4);

		lblNewLabel_5 = new JLabel("Mô tả");
		lblNewLabel_5.setBounds(47, 116, 77, 13);
		Transactions.add(lblNewLabel_5);

		lblNewLabel_6 = new JLabel("Category");
		lblNewLabel_6.setBounds(47, 158, 77, 13);
		Transactions.add(lblNewLabel_6);

		txttien = new JTextField();
		txttien.setBounds(168, 35, 230, 19);
		Transactions.add(txttien);
		txttien.setColumns(10);

		txtMota = new JTextField();
		txtMota.setBounds(168, 113, 230, 34);
		Transactions.add(txtMota);
		txtMota.setColumns(10);

		comboBoxCateTran = new JComboBox<categories>();
		comboBoxCateTran.setBounds(168, 154, 230, 21);
		Transactions.add(comboBoxCateTran);

		btnthemTran = new JButton("Thêm");
		btnthemTran.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int tien = Integer.parseInt(txttien.getText());
				NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
				String formattedTienChi = currencyFormatter.format(tien);
				Date ngay = dateChooserTran.getDate();
				java.sql.Date sqlDate = new java.sql.Date(ngay.getTime());
				String mota = txtMota.getText();
				categories ca = (categories) comboBoxCateTran.getSelectedItem();
				transactions tran = new transactions(tien, ngay, mota, ca);
				if (tranDao.save(tran, getCurrentUserID())) {
					dsTran.add(tran);
					modelTran.addRow(new Object[] { tran.getTransaction_id(), formattedTienChi, sqlDate,
							tran.getDescription(), tran.getCat() });
					JOptionPane.showMessageDialog(null, "Lưu thành công");
					kiemtraVuotThu();
					updateBaocaoModel();
					KiemTraNganSachCat(getCurrentUserID(), tran.getCat().getCategory_id());
					updateChart();
					
				} else {
					JOptionPane.showMessageDialog(null, "Lưu that bai");
				}
			}
		});
		btnthemTran.setBounds(517, 34, 85, 21);
		Transactions.add(btnthemTran);

		btnXoaTran = new JButton("Xoa");
		btnXoaTran.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = tableTran.getSelectedRow();
				if (index != -1) {
					transactions tran = dsTran.get(index);
					if (tranDao.delete(tran.getTransaction_id(), getCurrentUserID())) {
						dsTran.remove(index);
						modelTran.removeRow(index);
						JOptionPane.showMessageDialog(null, "Xóa thành công");
						updateBaocaoModel();
						updateChart();
					} else {
						JOptionPane.showMessageDialog(null, "Xóa thất bại");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để xóa");
				}
			}
		});
		btnXoaTran.setBounds(517, 90, 85, 21);
		Transactions.add(btnXoaTran);

		btnSuaTran = new JButton("Sửa");
		btnSuaTran.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = tableTran.getSelectedRow();
				int tien = Integer.parseInt(txttien.getText());
				NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
				String formattedTienChi = currencyFormatter.format(tien);
				Date ngay = dateChooserTran.getDate();
				java.sql.Date sqlDate = new java.sql.Date(ngay.getTime());
				String mota = txtMota.getText();
				categories ca = (categories) comboBoxCateTran.getSelectedItem();
				if (index != -1) {
					transactions tran = dsTran.get(index);
					tran.setAmount(tien);
					tran.setDate(sqlDate);
					tran.setDescription(mota);
					tran.setCat(ca);
					if (tranDao.update(tran, getCurrentUserID())) {
						dsTran.set(index, tran);
						modelTran.setValueAt(formattedTienChi, index, 1);
						modelTran.setValueAt(sqlDate, index, 2);
						modelTran.setValueAt(mota, index, 3);
						modelTran.setValueAt(ca, index, 4);
						updateBaocaoModel();
						kiemtraVuotThu();
						KiemTraNganSachCat(getCurrentUserID(), tran.getCat().getCategory_id());
						updateChart();
					} else {
						JOptionPane.showMessageDialog(null, "sua that bai");
					}
				} else {
					JOptionPane.showMessageDialog(null, "vui long chon 1");
				}
			}
		});
		btnSuaTran.setBounds(517, 154, 85, 21);
		Transactions.add(btnSuaTran);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(65, 238, 627, 179);
		Transactions.add(scrollPane_1);

		tableTran = new JTable();
		tableTran.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tableTran.getSelectedRow();
				if (index != -1) {
					transactions tran = dsTran.get(index);
					txttien.setText(tran.getAmount() + "");
					dateChooserTran.setDate(tran.getDate());
					txtMota.setText(tran.getDescription());
					comboBoxCateTran.setSelectedItem(tran.getCat());
				}
			}
		});
		scrollPane_1.setViewportView(tableTran);

		dateChooserTran = new JDateChooser();
		dateChooserTran.setBounds(168, 70, 230, 19);
		Transactions.add(dateChooserTran);

		Baocao = new JPanel();
		tabbedPane.addTab("Báo cáo", null, Baocao, null);
		Baocao.setLayout(null);

		scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(10, 82, 745, 286);
		Baocao.add(scrollPane_4);

		tableBaocao = new JTable();
		scrollPane_4.setViewportView(tableBaocao);

		Budgets = new JPanel();
		tabbedPane.addTab("Budgets", null, Budgets, null);
		Budgets.setLayout(null);

		lblNewLabel_8 = new JLabel("Category");
		lblNewLabel_8.setBounds(50, 26, 62, 13);
		Budgets.add(lblNewLabel_8);

		lblNewLabel_9 = new JLabel("Amount");
		lblNewLabel_9.setBounds(50, 63, 62, 13);
		Budgets.add(lblNewLabel_9);

		lblNewLabel_10 = new JLabel("Start-date");
		lblNewLabel_10.setBounds(50, 103, 62, 13);
		Budgets.add(lblNewLabel_10);

		lblNewLabel_11 = new JLabel("End-date");
		lblNewLabel_11.setBounds(50, 137, 62, 13);
		Budgets.add(lblNewLabel_11);

		comboxCateBud = new JComboBox<categories>();
		comboxCateBud.setBounds(142, 22, 183, 21);
		Budgets.add(comboxCateBud);

		txtTienBud = new JTextField();
		txtTienBud.setBounds(142, 60, 183, 19);
		Budgets.add(txtTienBud);
		txtTienBud.setColumns(10);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(67, 248, 637, 171);
		Budgets.add(scrollPane_2);

		tableBud = new JTable();
		tableBud.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = tableBud.getSelectedRow();
				if (i != -1) {
					budgets bu = dsBud.get(i);
					comboxCateBud.setSelectedItem(bu.getCat());
					txtTienBud.setText(bu.getAmount() + "");
					NgayBDBud.setDate(bu.getStart_date());
					NgayKTBud.setDate(bu.getEnd_date());
				}
			}
		});
		scrollPane_2.setViewportView(tableBud);

		btnThemBud = new JButton("Them");
		btnThemBud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				categories ca = (categories) comboxCateBud.getSelectedItem();
				int tien = Integer.parseInt(txtTienBud.getText());
				NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
				String formattedTienChi = currencyFormatter.format(tien);
				Date ngaybd = NgayBDBud.getDate();
				Date ngaykt = NgayKTBud.getDate();
				java.sql.Date sqlDatebd = new java.sql.Date(ngaybd.getTime());
				java.sql.Date sqlDatekt = new java.sql.Date(ngaykt.getTime());
				budgets bd = new budgets(ca, tien, sqlDatebd, sqlDatekt);
				if (budDao.save(bd, getCurrentUserID())) {
					dsBud.add(bd);
					modelBud.addRow(
							new Object[] { bd.getBudget_id(), bd.getCat(), formattedTienChi, sqlDatebd, sqlDatekt });
					JOptionPane.showMessageDialog(null, "Lưu thành công");
					KiemTraNganSachCat(getCurrentUserID(), bd.getCat().getCategory_id());

				} else {
					JOptionPane.showMessageDialog(null, "Lưu that bai");
				}
			}
		});
		btnThemBud.setBounds(488, 26, 85, 21);
		Budgets.add(btnThemBud);

		btnXoaBud = new JButton("Xoa");
		btnXoaBud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = tableBud.getSelectedRow();
				if (index != -1) {
					budgets bu = dsBud.get(index);
					if (budDao.delete(bu.getBudget_id(), getCurrentUserID())) {
						dsBud.remove(index);
						modelBud.removeRow(index);
						JOptionPane.showMessageDialog(null, "Xóa thành công");
						txtTienBud.setText("");
					} else {
						JOptionPane.showMessageDialog(null, "Xóa thất bại");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để xóa");
				}
			}
		});
		btnXoaBud.setBounds(488, 80, 85, 21);
		Budgets.add(btnXoaBud);

		btnSuaBud = new JButton("Sua");
		btnSuaBud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = tableBud.getSelectedRow();
				categories ca = (categories) comboxCateBud.getSelectedItem();
				int tien = Integer.parseInt(txtTienBud.getText());
				NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
				String formattedTienChi = currencyFormatter.format(tien);
				Date ngaybd = NgayBDBud.getDate();
				Date ngaykt = NgayKTBud.getDate();
				java.sql.Date sqlDatebd = new java.sql.Date(ngaybd.getTime());
				java.sql.Date sqlDatekt = new java.sql.Date(ngaykt.getTime());
				if (i != -1) {
					budgets bu = dsBud.get(i);
					bu.setCat(ca);
					bu.setAmount(tien);
					bu.setStart_date(sqlDatebd);
					bu.setEnd_date(sqlDatekt);
					if (budDao.update(bu, getCurrentUserID())) {
						dsBud.set(i, bu);
						modelBud.setValueAt(ca, i, 1);
						modelBud.setValueAt(formattedTienChi, i, 2);
						modelBud.setValueAt(sqlDatebd, i, 3);
						modelBud.setValueAt(sqlDatekt, i, 4);
						JOptionPane.showMessageDialog(null, "sua thanh cong");
						KiemTraNganSachCat(getCurrentUserID(), bu.getCat().getCategory_id());

					} else {
						JOptionPane.showMessageDialog(null, "sua that bai");
					}
				} else {
					JOptionPane.showMessageDialog(null, "vui long chon 1");
				}
			}
		});
		btnSuaBud.setBounds(488, 133, 85, 21);
		Budgets.add(btnSuaBud);

		NgayBDBud = new JDateChooser();
		NgayBDBud.setBounds(142, 97, 183, 19);
		Budgets.add(NgayBDBud);

		NgayKTBud = new JDateChooser();
		NgayKTBud.setBounds(142, 131, 183, 19);
		Budgets.add(NgayKTBud);

		groupCate = new ButtonGroup();
		groupCate.add(rdbtnchi);
		groupCate.add(rdbtnthu);

		tableCate.setModel(modelCate);
		modelCate.addColumn("ID");
		modelCate.addColumn("Name");
		modelCate.addColumn("Type");

		dsCate = catDao.findAll(getCurrentUserID());
		for (categories cat : dsCate) {
			String types = cat.isType() ? "Chi tiêu" : "Thu nhập";
			modelCate.addRow(new Object[] { cat.getCategory_id(), cat.getName(), types });
			cbbCate.addElement(cat);
		}
		comboBoxCateTran.setModel(cbbCate);
		comboxCateBud.setModel(cbbCate);

		dsTran = tranDao.findAll(getCurrentUserID());
		tableTran.setModel(modelTran);
		modelTran.addColumn("ID");
		modelTran.addColumn("Amount");
		modelTran.addColumn("Date");
		modelTran.addColumn("Description");
		modelTran.addColumn("Cate-ID");

		for (transactions tran : dsTran) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String formattedDate = dateFormat.format(tran.getDate());
			NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
			String formattedTienChi = currencyFormatter.format(tran.getAmount());
			modelTran.addRow(new Object[] { tran.getTransaction_id(), formattedTienChi, formattedDate,
					tran.getDescription(), tran.getCat() });
		}
		dsBud = budDao.findAll(getCurrentUserID());
		tableBud.setModel(modelBud);
		modelBud.addColumn("ID");
		modelBud.addColumn("Cate-ID");
		modelBud.addColumn("Amount");
		modelBud.addColumn("Start-date");
		modelBud.addColumn("End-date");

		for (budgets bd : dsBud) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String formattedStDate = dateFormat.format(bd.getStart_date());
			String formattedEnDate = dateFormat.format(bd.getEnd_date());
			NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
			String formattedTienChi = currencyFormatter.format(bd.getAmount());
			modelBud.addRow(
					new Object[] { bd.getBudget_id(), bd.getCat(), formattedTienChi, formattedStDate, formattedEnDate });
		}

		tableBaocao.setModel(modelBaocao);

		lbthu = new JLabel("0");
		lbthu.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbthu.setBounds(482, 385, 183, 13);
		Baocao.add(lbthu);

		lblNewLabel_7 = new JLabel("Tong Chi:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_7.setBounds(377, 408, 111, 13);
		Baocao.add(lblNewLabel_7);

		lblNewLabel_12 = new JLabel("Tong Thu:");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_12.setBounds(377, 385, 111, 13);
		Baocao.add(lblNewLabel_12);

		lbChi = new JLabel("0");
		lbChi.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbChi.setBounds(482, 408, 183, 13);
		Baocao.add(lbChi);

		yearChooser = new JYearChooser();
		yearChooser.setBounds(529, 10, 46, 19);
		Baocao.add(yearChooser);

		monthChooser = new JMonthChooser();
		monthChooser.setBounds(408, 10, 96, 19);
		Baocao.add(monthChooser);
		btnloc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int year = yearChooser.getYear();
				int month = monthChooser.getMonth() + 1;
				List<transactions> dsFiltered = tranDao.findByMonthAndYear(getCurrentUserID(), month, year);
				modelBaocao.setRowCount(0);
				for (transactions tran : dsFiltered) {
					String type = tran.getCat().isType() ? "Chi tiêu" : "Thu nhập";
					NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
					String formattedTienChi = currencyFormatter.format(tran.getAmount());
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String ngayth = dateFormat.format(tran.getDate());
					modelBaocao.addRow(new Object[] {tran.getCat().getName(),type , tran.getDescription(), formattedTienChi,ngayth});
				}
				updateBaocaoModel1(dsFiltered);
			}
		});
		btnloc.setBounds(585, 10, 85, 19);
		Baocao.add(btnloc);
		
		btnboloc = new JButton("Bo loc");
		btnboloc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelBaocao.setRowCount(0);
				laydsThuChi();
				updateBaocaoModel1(dsTran);
			}
		});
		btnboloc.setBounds(585, 39, 85, 19);
		Baocao.add(btnboloc);
		
		lblSoDu = new JLabel("0 ₫");
		lblSoDu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSoDu.setBounds(153, 31, 164, 13);
		Baocao.add(lblSoDu);
		
		lblNewLabel_2 = new JLabel("So du hien tai");
		lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(39, 31, 104, 13);
		Baocao.add(lblNewLabel_2);

		modelBaocao.addColumn("Danh muc");
		modelBaocao.addColumn("Loai ");
		modelBaocao.addColumn("Ten giao dich");
		modelBaocao.addColumn("So tien");
		modelBaocao.addColumn("Ngay thuc hien");

		laydsThuChi();

		getThuCHi();
		showBarChart();
	}

	public void laydsThuChi() {
		for (categories ca : dsCate) {
			String danhmuc = ca.getName();
			String loai = ca.isType() ? "Chi tiêu" : "Thu nhập";
			for (transactions tra : dsTran) {
				if (tra.getCat().getCategory_id() == ca.getCategory_id()) {
					String tenGD = tra.getDescription();
					NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
					String formattedTienChi = currencyFormatter.format(tra.getAmount());
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String ngayth = dateFormat.format(tra.getDate());
					modelBaocao.addRow(new Object[] { danhmuc, loai, tenGD, formattedTienChi, ngayth });
				}
			}
		}
	}
	public void updateBaocaoModel() {
		// Clear the current data in the model
		modelBaocao.setRowCount(0);

		// Iterate through categories
		for (categories ca : dsCate) {
			String danhmuc = ca.getName();
			String loai = ca.isType() ? "Chi tiêu" : "Thu nhập";

			// Iterate through transactions
			for (transactions tra : dsTran) {
				// Check if the transaction belongs to the current category
				if (tra.getCat().getCategory_id() == ca.getCategory_id()) {
					String tenGD = tra.getDescription();
					NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
					String formattedTienChi = currencyFormatter.format(tra.getAmount());
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String ngayth = dateFormat.format(tra.getDate());
					// Add the updated transaction data to the model
					modelBaocao.addRow(new Object[] { danhmuc, loai, tenGD, formattedTienChi, ngayth });
				}
			}
		}
		getThuCHi();
		
	}

	public void getThuCHi() {
		int tienChi = thuchidao.getTienChi(getCurrentUserID());
		int tienThu = thuchidao.getTienThu(getCurrentUserID());
		// Format the amounts to Vietnamese currency format (VND)
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
		String formattedTienChi = currencyFormatter.format(tienChi);
		String formattedTienThu = currencyFormatter.format(tienThu);

		lbChi.setText(formattedTienChi);
		lbthu.setText(formattedTienThu);
		String formattedsodu = currencyFormatter.format(tienThu-tienChi);
		lblSoDu.setText(formattedsodu);
	}
	public void kiemtraVuotThu() {
		int tienChi = thuchidao.getTienChi(getCurrentUserID());
		int tienThu = thuchidao.getTienThu(getCurrentUserID());
		if(tienThu<tienChi) {
			JOptionPane.showMessageDialog(null, "Tiền chi vượt quá số tiền thu vào");
		}
		
	}
	private void updateBaocaoModel1(List<transactions> f) {
	    int totalIncome = 0;
	    int totalExpense = 0;
	    for (transactions tran : f) {
	        if (tran.getCat().isType()) {
	            totalExpense += tran.getAmount();
	        } else {
	            totalIncome += tran.getAmount();
	        }
	    }
	    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
	    String formattedTienChi = currencyFormatter.format(totalExpense);
	    String formattedTienThu = currencyFormatter.format(totalIncome);
	    lbChi.setText(formattedTienChi);
	    lbthu.setText(formattedTienThu);
	}
	public void KiemTraNganSachCat(int userID, int categoryID) {
	    int totalSpending = thuchidao.layTongChitheoCate(userID, categoryID);
	    int budget = thuchidao.LayNganSachCat(categoryID);
	    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
	    String formattedTienChi = currencyFormatter.format(totalSpending - budget);
	    if (totalSpending > budget) {
	        JOptionPane.showMessageDialog(null, "Số tiền chi cho danh mục Category có id= " + categoryID + " vượt quá ngân sách: " + (formattedTienChi) + " VND");
	    } 
	}

	public void showBarChart() {
	    dataset = new DefaultCategoryDataset();
	    int currentYear = Calendar.getInstance().get(Calendar.YEAR);

	    // Assuming you have a method to get transactions for the current year
	    List<transactions> transactions = thuchidao.getTransactionsByYear(getCurrentUserID(), currentYear);

	    // Maps to store total income and expenses per month
	    Map<Integer, Integer> incomeMap = new HashMap<>();
	    Map<Integer, Integer> expenseMap = new HashMap<>();

	    for (transactions tran : transactions) {
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(tran.getDate());
	        int month = cal.get(Calendar.MONTH)+1;

	        if (tran.getCat().isType()) {
	            expenseMap.put(month, expenseMap.getOrDefault(month, 0) + tran.getAmount());
	        } else {
	            incomeMap.put(month, incomeMap.getOrDefault(month, 0) + tran.getAmount());
	        }
	    }

	    for (int month = 1; month <= 12; month++) {
	        int income = incomeMap.getOrDefault(month, 0);
	        int expense = expenseMap.getOrDefault(month, 0);
	        dataset.addValue(income, "Income", String.valueOf(month));
	        dataset.addValue(expense, "Expense", String.valueOf(month));
	    }

	    chart = ChartFactory.createBarChart(
	            "Biểu đồ chi tiêu năm " + currentYear, "Tháng", "Số tiền", dataset,
	            PlotOrientation.VERTICAL, true, true, false);

	    categoryPlot = chart.getCategoryPlot();
	    chartPanel = new ChartPanel(chart);

	    Home.setLayout(new BorderLayout());
	    Home.add(chartPanel, BorderLayout.CENTER);
	    Home.revalidate();
	    Home.repaint();
	}
	public void updateChart() {
	    // Xóa dữ liệu hiện tại của biểu đồ
	    dataset.clear();

	    // Tính toán dữ liệu mới cho biểu đồ
	    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
	    List<transactions> transactions = thuchidao.getTransactionsByYear(getCurrentUserID(), currentYear);
	    Map<Integer, Integer> incomeMap = new HashMap<>();
	    Map<Integer, Integer> expenseMap = new HashMap<>();

	    for (transactions tran : transactions) {
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(tran.getDate());
	        int month = cal.get(Calendar.MONTH) + 1;

	        if (tran.getCat().isType()) {
	            expenseMap.put(month, expenseMap.getOrDefault(month, 0) + tran.getAmount());
	        } else {
	            incomeMap.put(month, incomeMap.getOrDefault(month, 0) + tran.getAmount());
	        }
	    }

	    for (int month = 1; month <= 12; month++) {
	        int income = incomeMap.getOrDefault(month, 0);
	        int expense = expenseMap.getOrDefault(month, 0);
	        dataset.addValue(income, "Income", String.valueOf(month));
	        dataset.addValue(expense, "Expense", String.valueOf(month));
	    }

	    // Vẽ lại biểu đồ
	    chartPanel.repaint();
	}

}
