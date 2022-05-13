package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

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
		}*/
			
		if (isSelected && table.getSelectedColumn()==column && table.getSelectedRow()==row) {
			Color myBlue=new Color(0, 23, 255,150);
            this.setBackground(Color.cyan);
            this.setBackground(myBlue);
            this.setText(table.getValueAt(row, column).toString());
            return this;
        }else if(!isSelected && (table.getSelectedColumn()!=column && table.getSelectedRow()!=row)) {
        	this.setBackground(Color.white);
        }
       
		return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	}
	
	
	       
	  

}