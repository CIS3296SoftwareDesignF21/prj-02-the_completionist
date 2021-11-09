import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class CalendarGUI{
    static JLabel labelMonth, labelYear;
    static JButton buttonPrev, buttonNext;
    static JTable tableCalendar;
    static JComboBox comboYear;
    static JFrame frameMain;
    static Container pane;
    static DefaultTableModel mtableCalendar;
    static JScrollPane stableCalendar;        
    static JPanel panelCalendar;
    static int actualYear, actualMonth, actualDay, currentYear, currentMonth;
    
    public static void main (String args[]){
        
        try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
        catch (ClassNotFoundException e) {}
        catch (InstantiationException e) {}
        catch (IllegalAccessException e) {}
        catch (UnsupportedLookAndFeelException e) {}
        
        //frame & pane layout
        frameMain = new JFrame ("The Completionist");           //create frame
        frameMain.setSize(500, 500); 
        pane = frameMain.getContentPane();                      //content pane
        pane.setLayout(null);                                   //null layout
        frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close when x is clicked
        
        //controls
        labelMonth = new JLabel ("January");
        comboYear = new JComboBox();
        
        mtableCalendar = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
        tableCalendar = new JTable(mtableCalendar);
        stableCalendar = new JScrollPane(tableCalendar);
        panelCalendar = new JPanel(null);
        
        
        comboYear.addActionListener(new comboYear_Action());
        
        //add controls to pane
        pane.add(panelCalendar);
        panelCalendar.add(labelMonth);
        
        panelCalendar.add(stableCalendar);
        
        //create bounds
        panelCalendar.setBounds(0, 0, 320, 335);
        labelMonth.setBounds(160-labelMonth.getPreferredSize().width/2, 25, 100, 25);
        comboYear.setBounds(230, 305, 80, 20);
        stableCalendar.setBounds(10, 50, 300, 250);
        
        //make frame visible
        frameMain.setResizable(false);
        frameMain.setVisible(true);
        
        //get actual day/month/year
        GregorianCalendar cal = new GregorianCalendar();
        actualDay = cal.get(GregorianCalendar.DAY_OF_MONTH);
        actualMonth = cal.get(GregorianCalendar.MONTH);
        actualYear = cal.get(GregorianCalendar.YEAR);
        currentMonth = actualMonth;
        currentYear = actualYear;
        
        //add days of week
        String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (int i=0; i<7; i++){
            mtableCalendar.addColumn(days[i]);
        }
        
        //set background
        tableCalendar.getParent().setBackground(tableCalendar.getBackground());
        
        //single cell selection
        tableCalendar.setColumnSelectionAllowed(true);
        tableCalendar.setRowSelectionAllowed(true);
        tableCalendar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        //set row & column count
        tableCalendar.setRowHeight(38);
        mtableCalendar.setColumnCount(7);
        mtableCalendar.setRowCount(6);
        
        //fill table
        for (int i=actualYear-100; i<=actualYear+100; i++){
            comboYear.addItem(String.valueOf(i));
        }
        
        //refresh calendar
        refreshCalendar (actualMonth, actualYear);
    }
    
    
    public static void refreshCalendar(int month, int year){
        
        String[] months =  {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        int numDays, monthStart;
        
        
        labelMonth.setText(months[month]);
        labelMonth.setBounds(160-labelMonth.getPreferredSize().width/2, 25, 180, 25);
        comboYear.setSelectedItem(String.valueOf(year));
        
        
        for (int i=0; i<6; i++){
            for (int j=0; j<7; j++){
                mtableCalendar.setValueAt(null, i, j);
            }
        }
        
        //get number of days & first day of month
        GregorianCalendar cal = new GregorianCalendar(year, month, 1);
        numDays = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        monthStart = cal.get(GregorianCalendar.DAY_OF_WEEK);
        
        //draw calendar
        for (int i=1; i<=numDays; i++){
            int row = new Integer((i+monthStart-2)/7);
            int column  =  (i+monthStart-2)%7;
            mtableCalendar.setValueAt(i, row, column);
        }
        
        //apply renderer for drawing
        tableCalendar.setDefaultRenderer(tableCalendar.getColumnClass(0), new tableCalendarRenderer());
    }
    
    
    static class tableCalendarRenderer extends DefaultTableCellRenderer{
        public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column){
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            
            setBackground(new Color(255, 255, 255));
            
            if ((value != null) && (Integer.parseInt(value.toString()) == actualDay && currentMonth == actualMonth && currentYear == actualYear) )
                setBackground(new Color(200, 200, 255));
                
            setBorder(null);
            setForeground(Color.black);
            return this;
        }
    }
   
    
    static class comboYear_Action implements ActionListener{
        public void actionPerformed (ActionEvent e){
            if (comboYear.getSelectedItem() != null){
                String b = comboYear.getSelectedItem().toString();
                currentYear = Integer.parseInt(b);
                refreshCalendar(currentMonth, currentYear);
            }
        }
    }
}
