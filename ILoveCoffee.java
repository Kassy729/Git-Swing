package swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class ILoveCoffeeTest{
	private OrderPanel orderPanel = new OrderPanel();
	private int i;
	private ActionEvent z;
	ButtonPanel buttonPanel = new ButtonPanel();
	ButtonPanel.Modal m;
	
	private JFrame frame = new JFrame();
	ILoveCoffeeTest(){		
		frame.setSize(870,700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("ILoveCoffee");
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);
		
		
		frame.add(buttonPanel, BorderLayout.CENTER);
		frame.add(orderPanel, BorderLayout.EAST);
		
		frame.setVisible(true);
		
		for(i=0; i<buttonPanel.MENU_SIZE; i++) {
			buttonPanel.orderButton[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent y) {
					z = y;
					for(int i=0; i<buttonPanel.MENU_SIZE; i++) {
						if(buttonPanel.orderButton[i] == y.getSource()) {
							m = buttonPanel.new Modal(frame, orderPanel.area);
							m.setLocationRelativeTo(null);
							m.setVisible(true);
//							for(int r=0; r<buttonPanel.MENU_SIZE; r++) {
//								buttonPanel.orderButton[r].setEnabled(false);
//							}
							orderPanel.area.setEnabled(false);
							orderPanel.order.setEnabled(false);
							orderPanel.cancle.setEnabled(false);
							orderPanel.reset.setEnabled(false);
						}
					}
				}
			});
		}
	}
	
	class ButtonPanel extends JPanel{
		JButton[]orderButton;
		private String[]menu = {"에스프레소", "아메리카노", "카푸치노   ", "카페라떼   ", "바닐라라떼", "모카라떼   ", "핫초코라떼", "오렌지소다", "멜론소다   "};
		private int[]price = {2000, 2500, 3000, 3000, 3500, 3500, 3000, 4000, 4000};
		private final int MENU_SIZE = menu.length;
		
		public ButtonPanel() {
			setLayout(new GridLayout(0,3));
			orderButton = new JButton[MENU_SIZE];
			
			for(int i=0; i<MENU_SIZE; i++) {
				orderButton[i] = new JButton(menu[i]);
				add(orderButton[i]);
			}	
			
			orderButton[0].setIcon(new ImageIcon("/Users/kassy/케이시/사진/에스프레소.jpeg"));
			orderButton[1].setIcon(new ImageIcon("/Users/kassy/케이시/사진/아메리카노.jpeg"));
			orderButton[2].setIcon(new ImageIcon("/Users/kassy/케이시/사진/카푸치노.jpeg"));
			orderButton[3].setIcon(new ImageIcon("/Users/kassy/케이시/사진/카페라떼.jpeg"));
			orderButton[4].setIcon(new ImageIcon("/Users/kassy/케이시/사진/바닐라라떼.jpeg"));
			orderButton[5].setIcon(new ImageIcon("/Users/kassy/케이시/사진/모카라떼.jpeg"));
			orderButton[6].setIcon(new ImageIcon("/Users/kassy/케이시/사진/핫초코.jpeg"));
			orderButton[7].setIcon(new ImageIcon("/Users/kassy/케이시/사진/오렌지소다.jpeg"));
			orderButton[8].setIcon(new ImageIcon("/Users/kassy/케이시/사진/메론소다.jpeg"));	
		}
		class Modal extends JDialog{
			private int count = 0;
			public Modal(Window parent, TextArea area) {
				setSize(400,260);
				setLayout(new BorderLayout());
				ButtonPanel buttonPanel = new ButtonPanel();
				
				JButton plusButton = new JButton("추가");
				JButton minusButton = new JButton("빼기");
				JButton okayButton = new JButton("주문하기");
				JTextField textField = new JTextField(10);
				textField.setFont(new Font("Serif", Font.BOLD | Font.ITALIC,100));
				
				plusButton.addActionListener(new ActionListener() {	
					@Override
					public void actionPerformed(ActionEvent e) {
						count++;
						textField.setText(count + " ");
					}
				});
				
				minusButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(count > 0) {
							count--;
							textField.setText(count + " ");
						}else {
							return;
						}
					}
				});
				
				okayButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(count > 0) {	
							for(int i=0; i<MENU_SIZE; i++) {
								if(orderButton[i] == z.getSource()) {
									area.append("    " + menu[i] + "       " + price[i] + "           " + count + "           " + price[i]*count + "\n");
									
//									for(int r=0; r<buttonPanel.MENU_SIZE; r++) {
//										buttonPanel.orderButton[r].setEnabled(true);
//									}
									orderPanel.area.setEnabled(true);
									orderPanel.order.setEnabled(true);
									orderPanel.cancle.setEnabled(true);
									orderPanel.reset.setEnabled(true);
									
									setVisible(false);
								}
							}
						}
					}	
				});
				add(textField, BorderLayout.CENTER);
				add(plusButton, BorderLayout.WEST);
				add(minusButton, BorderLayout.EAST);
				add(okayButton, BorderLayout.SOUTH);
			}
		}
	}
	
	class OrderPanel extends JPanel{
		public TextArea area;
		private JButton order;
		private JButton reset;
		private JButton cancle;
		private JPanel orderJPanel = new JPanel();
		
		public OrderPanel() {
			this.setLayout(new BorderLayout());
			this.area = new TextArea(38,36);
			this.area.setText("     상품명            단가           수량          합계\n\n");
			this.add(area, BorderLayout.NORTH);
			this.order = new JButton("주문");
			this.reset = new JButton("초기화");
			this.cancle = new JButton("취소");
		
			
			this.orderJPanel.add(order);
			this.orderJPanel.add(reset);
			this.orderJPanel.add(cancle);
			this.add(orderJPanel, BorderLayout.SOUTH);
			
			order.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, area.getText() + "\n" +"주문되었습니다!\n" + "이용해주셔서 감사합니다\n" + "곧 마일리지 적립 서비스도 가능합니다!");
				}
			});
			
			reset.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {	
					area.setText("     상품명            단가           수량          합계\n\n");
					area.append(null);
				}
			});
			
			cancle.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);	
				}
			});
	}	
	}
}
public class ILoveCoffee {
	public static void main(String[] args) {
		ILoveCoffeeTest coffee = new ILoveCoffeeTest();
	}
}