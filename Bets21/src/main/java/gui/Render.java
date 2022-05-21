package gui;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class Render extends DefaultTableCellRenderer{
	
	@Override
	public Component getTableCellRendererComponent( JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		
		if(value instanceof JButton) {
			JButton btn = (JButton) value;
			return btn;
		}
			
		if (isSelected && table.getSelectedColumn()==column && table.getSelectedRow()==row
				&& !(table.getSelectedColumn()<2 || 4<table.getSelectedColumn())) {
			Color myBlue=new Color(0, 23, 255,150);
            this.setBackground(Color.cyan);
            this.setBackground(myBlue);
            this.setText(table.getValueAt(row, column).toString());
            return this;
        }else if(!isSelected && (table.getSelectedColumn()!=column && table.getSelectedRow()!=row)) {
        	this.setBackground(Color.white);
        }else if(isSelected && (table.getSelectedColumn()<2 || 4<table.getSelectedColumn()) )
        		this.setBackground(Color.white);
       
		return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	}
	
	
	       
	  

}