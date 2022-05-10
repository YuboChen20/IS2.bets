package gui;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class Render extends DefaultTableCellRenderer{
	
	private JLabel label=new JLabel();
	@Override
	public Component getTableCellRendererComponent( JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		
		if(value instanceof JButton) {
			JButton btn = (JButton) value;
			return btn;
		} /*else if(value instanceof ImageIcon) {
			ImageIcon ii=(ImageIcon) value;
			label.setIcon(ii);
			return label;
		}
		*/
		
		return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	}

}
