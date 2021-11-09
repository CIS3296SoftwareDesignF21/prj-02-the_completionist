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
        
        //Prepare frame
        frameMain = new JFrame ("The Completionist"); //Create frame
        frameMain.setSize(500, 500); //Set size to 400x400 pixels
        pane = frameMain.getContentPane(); //Get content pane
        pane.setLayout(null); //Apply null layout
        frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Close when X is clicked
        
        //Create controls
        labelMonth = new JLabel ("January");
        labelYear = new JLabel ("Change year:");
        comboYear = new JComboBox();
        buttonPrev = new JButton ("<-");
        buttonNext = new JButton ("->");
        mtableCalendar = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
        tableCalendar = new JTable(mtableCalendar);
        stableCalendar = new JScrollPane(tableCalendar);
        panelCalendar = new JPanel(null);
        
        //Set border
        //panelCalendar.setBorder(BorderFactory.createTitledBorder("Calendar"));
        
        //Register action listeners
        buttonPrev.addActionListener(new buttonPrev_Action());
        buttonNext.addActionListener(new buttonNext_Action());
        comboYear.addActionListener(new comboYear_Action());
        
        //Add controls to pane
        pane.add(panelCalendar);
        panelCalendar.add(labelMonth);
        //panelCalendar.add(labelYear);
        //panelCalendar.add(comboYear);
        //panelCalendar.add(buttonPrev);
        //panelCalendar.add(buttonNext);
        panelCalendar.add(stableCalendar);
        
        //Set bounds
        panelCalendar.setBounds(0, 0, 320, 335);
        labelMonth.setBounds(160-labelMonth.getPreferredSize().width/2, 25, 100, 25);
        labelYear.setBounds(10, 305, 80, 20);
        comboYear.setBounds(230, 305, 80, 20);
        buttonPrev.setBounds(10, 25, 50, 25);
        buttonNext.setBounds(260, 25, 50, 25);
        stableCalendar.setBounds(10, 50, 300, 250);
        
        //Make frame visible
        frameMain.setResizable(false);
        frameMain.setVisible(true);
        
        //Get actual month/year
        GregorianCalendar cal = new GregorianCalendar(); //Create calendar
        actualDay = cal.get(GregorianCalendar.DAY_OF_MONTH); //Get day
        actualMonth = cal.get(GregorianCalendar.MONTH); //Get month
        actualYear = cal.get(GregorianCalendar.YEAR); //Get year
        currentMonth = actualMonth; //Match month and year
        currentYear = actualYear;
        
        //Add headers
        String[] headers = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}; //All headers
        for (int i=0; i<7; i++){
            mtableCalendar.addColumn(headers[i]);
        }
        
        tableCalendar.getParent().setBackground(tableCalendar.getBackground()); //Set background
        
        //No resize/reorder
        tableCalendar.getTableHeader().setResizingAllowed(false);
        tableCalendar.getTableHeader().setReorderingAllowed(false);
        
        //Single cell selection
        tableCalendar.setColumnSelectionAllowed(true);
        tableCalendar.setRowSelectionAllowed(true);
        tableCalendar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        //Set row/column count
        tableCalendar.setRowHeight(38);
        mtableCalendar.setColumnCount(7);
        mtableCalendar.setRowCount(6);
        
        //Populate table
        for (int i=actualYear-100; i<=actualYear+100; i++){
            comboYear.addItem(String.valueOf(i));
        }
        
        //Refresh calendar
        refreshCalendar (actualMonth, actualYear); //Refresh calendar
    }
    
    public static void refreshCalendar(int month, int year){
        //Variables
        String[] months =  {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        int numDays, monthStart; //Number Of Days, Start Of Month
        
        //Allow/disallow buttons
        buttonPrev.setEnabled(true);
        buttonNext.setEnabled(true);
        if (month == 0 && year <= actualYear-10){buttonPrev.setEnabled(false);} //Too early
        if (month == 11 && year >= actualYear+100){buttonNext.setEnabled(false);} //Too late
        labelMonth.setText(months[month]); //Refresh the month label (at the top)
        labelMonth.setBounds(160-labelMonth.getPreferredSize().width/2, 25, 180, 25); //Re-align label with calendar
        comboYear.setSelectedItem(String.valueOf(year)); //Select the correct year in the combo box
        
        //Clear table
        for (int i=0; i<6; i++){
            for (int j=0; j<7; j++){
                mtableCalendar.setValueAt(null, i, j);
            }
        }
        
        //Get first day of month and number of days
        GregorianCalendar cal = new GregorianCalendar(year, month, 1);
        numDays = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        monthStart = cal.get(GregorianCalendar.DAY_OF_WEEK);
        
        //Draw calendar
        for (int i=1; i<=numDays; i++){
            int row = new Integer((i+monthStart-2)/7);
            int column  =  (i+monthStart-2)%7;
            mtableCalendar.setValueAt(i, row, column);
        }
        
        //Apply renderers
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
   
    static class buttonPrev_Action implements ActionListener{
        public void actionPerformed (ActionEvent e){
            if (currentMonth == 0){ //Back one year
                currentMonth = 11;
                currentYear -= 1;
            }
            else{ //Back one month
                currentMonth -= 1;
            }
            refreshCalendar(currentMonth, currentYear);
        }
    }
    static class buttonNext_Action implements ActionListener{
        public void actionPerformed (ActionEvent e){
            if (currentMonth == 11){ //Foward one year
                currentMonth = 0;
                currentYear += 1;
            }
            else{ //Foward one month
                currentMonth += 1;
            }
            refreshCalendar(currentMonth, currentYear);
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
