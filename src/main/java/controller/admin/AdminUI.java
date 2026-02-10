package controller.admin;

import java.awt.EventQueue;
import java.awt.Color;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.LoginUI;

import java.util.List;

import vo.OrderDetailVO;
import vo.OrderDetailVODaoImpl;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private JTable table;
    private DefaultTableModel model;

    private JTextField txtUserNo;
    private JTextField txtProductNo;
    private JTextField txtMin;
    private JTextField txtMax;
    private JLabel lblTotalAmount;
    private OrderDetailVODaoImpl dao = new OrderDetailVODaoImpl();
    private JTextField txtOrderNo;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AdminUI frame = new AdminUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public AdminUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 520, 520);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // ====== 上方條件區 ======
        JLabel lblUser = new JLabel("客戶編號");
        lblUser.setBounds(10, 45, 60, 20);
        contentPane.add(lblUser);

        txtUserNo = new JTextField();
        txtUserNo.setBounds(70, 45, 80, 21);
        contentPane.add(txtUserNo);

        JButton btnUserQuery = new JButton("查詢");
        btnUserQuery.setBounds(155, 45, 65, 23);
        contentPane.add(btnUserQuery);

        JLabel lblProduct = new JLabel("產品編號");
        lblProduct.setBounds(10, 75, 60, 20);
        contentPane.add(lblProduct);

        txtProductNo = new JTextField();
        txtProductNo.setBounds(70, 75, 80, 21);
        contentPane.add(txtProductNo);

        JButton btnProductQuery = new JButton("查詢");
        btnProductQuery.setBounds(155, 75, 65, 23);
        contentPane.add(btnProductQuery);

        JLabel lblRange = new JLabel("金額區間（小~大）");
        lblRange.setBounds(240, 45, 120, 20);
        contentPane.add(lblRange);

        txtMin = new JTextField();
        txtMin.setBounds(360, 45, 60, 21);
        contentPane.add(txtMin);

        txtMax = new JTextField();
        txtMax.setBounds(425, 45, 60, 21);
        contentPane.add(txtMax);

        JButton btnRangeQuery = new JButton("查詢");
        btnRangeQuery.setBounds(360, 75, 125, 23);
        contentPane.add(btnRangeQuery);

        JButton btnAll = new JButton("所有訂單");
        btnAll.setBounds(240, 75, 110, 23);
        contentPane.add(btnAll);
        
        JButton btnClear = new JButton("清空");
        btnClear.setBounds(380, 14, 80, 23); 
        contentPane.add(btnClear);

        // ====== 中間列表區 JTable ======
        JPanel panel = new JPanel();
        panel.setBackground(new Color(128, 128, 128));
        panel.setBounds(10, 110, 486, 360);
        panel.setLayout(null);
        contentPane.add(panel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 466, 321);
        panel.add(scrollPane);

        model = new DefaultTableModel(
                new Object[][] {},
                new String[] { "訂單號", "客戶編號", "產品編號", "產品名稱", "單價", "數量", "小計", "時間" }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // 不可編輯
            }
        };

        table = new JTable(model);
        scrollPane.setViewportView(table);
        
        lblTotalAmount = new JLabel("查詢總金額：0");
        lblTotalAmount.setBounds(186, 340, 300, 20);
        panel.add(lblTotalAmount);
        
        JLabel lblUser_1 = new JLabel("訂單編號");
        lblUser_1.setBounds(10, 12, 60, 20);
        contentPane.add(lblUser_1);
        
        txtOrderNo = new JTextField();
        txtOrderNo.setBounds(70, 12, 80, 21);
        contentPane.add(txtOrderNo);
        
        JButton btnOrderQuery_1 = new JButton("查詢");
        btnOrderQuery_1.setBounds(155, 12, 65, 23);
        contentPane.add(btnOrderQuery_1);
        
        JButton btnNewButton = new JButton("回登入頁面");
        btnNewButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		LoginUI loginui=new LoginUI();
				loginui.setVisible(true);
				dispose();
        	}
        });
        btnNewButton.setBounds(240, 14, 110, 23);
        contentPane.add(btnNewButton);
        
        btnOrderQuery_1.addActionListener(e -> {
            String orderName = txtOrderNo.getText().trim();
            if (orderName.isEmpty()) return;
            List<OrderDetailVO> list = dao.selectLinesByOrderNo(orderName);
            showLines(list);
        });

       

        // ====== 事件：所有訂單 ======
        btnAll.addActionListener(e -> {
            List<OrderDetailVO> list = dao.selectAllLines();
            showLines(list);
        });

        // ====== 事件：客戶編號查詢 ======
        btnUserQuery.addActionListener(e -> {
            String userNo = txtUserNo.getText().trim();
            if (userNo.isEmpty()) return;
            List<OrderDetailVO> list = dao.selectLinesByUserNo(userNo);
            showLines(list);
        });

       

        // ====== 事件：產品編號查詢 ======
        btnProductQuery.addActionListener(e -> {
            String productNo = txtProductNo.getText().trim();
            if (productNo.isEmpty()) return;
            List<OrderDetailVO> list = dao.selectLinesByProductNo(productNo);
            showLines(list);
        });

        // ====== 事件：金額區間（用每行小計 line_total 篩，超快） ======
        btnRangeQuery.addActionListener(e -> {
            String sMin = txtMin.getText().trim();
            String sMax = txtMax.getText().trim();
            if (sMin.isEmpty() || sMax.isEmpty()) return;

            int min, max;
            try {
                min = Integer.parseInt(sMin);
                max = Integer.parseInt(sMax);
            } catch (Exception ex) {
                return;
            }

            List<OrderDetailVO> list = dao.selectLinesByLineTotalRange(min, max);
            showLines(list);
        });

        btnClear.addActionListener(e -> {

            //  清空查詢欄位
        	txtOrderNo.setText("");
            txtUserNo.setText("");
            txtProductNo.setText("");
            txtMin.setText("");
            txtMax.setText("");

            //  清空表格
            model.setRowCount(0);

            //  重設總金額顯示
            lblTotalAmount.setText("查詢總金額：0");
        });
        // 預設載入全部
        List<OrderDetailVO> list = dao.selectAllLines();
        showLines(list);
    }

    // 把資料塞進 JTable
    private void showLines(List<OrderDetailVO> list) {
    	 model.setRowCount(0); // ✅ 每次查詢先清空表格
 
    	int sum = 0; // ✅ 加總用

    	    if (list == null || list.isEmpty()) {
    	        lblTotalAmount.setText("查詢總金額：0");
    	        return;
    	    }

    	    for (OrderDetailVO vo : list) {
    	        int price = vo.getProductPrice();
    	        int qty = vo.getAmounts();
    	        int lineTotal = price * qty;

    	        sum += lineTotal; // ✅ 累加

    	        model.addRow(new Object[] {
    	                vo.getOrderNo(),
    	                vo.getUserNo(),
    	                vo.getProductNo(),
    	                vo.getProductName(),
    	                price,
    	                qty,
    	                lineTotal,
    	                vo.getOrderCreatedAt()
    	        });
    	    }

    	    // ✅ 最後顯示加總
    	    lblTotalAmount.setText("查詢總金額：" + sum);
    	}
  
}