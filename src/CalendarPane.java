import java.io.FileNotFoundException;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.JLayeredPane;

public class CalendarPane
{
    
    static Container pane;
    static DefaultTableModel tableCalendarModel;
    static JButton buttonNext, buttonPrev;
    static JFrame frameMain;
    static JLabel labelMonth, labelYear;     
    static JPanel panelCalendar;
    static JScrollPane stableCalendar; 
    static JTable tableCalendar;
    static int actualDay, actualMonth, actualYear, currentMonth, currentYear;
    
    static JButton buttonDays[] = new JButton[32];
    Calendar cal2 = Calendar.getInstance();
    
    public void main()
    {
        //prep frame
        frameMain = new JFrame("The Completionist");
        frameMain.setSize(750, 750);
        pane = frameMain.getContentPane();
        
        //navigation
        labelMonth = new JLabel("January");
        labelYear = new JLabel("1970");
        
        buttonNext = new JButton(">>");
        buttonPrev = new JButton("<<");
        
        tableCalendarModel = new DefaultTableModel()
        {   //makes the calendar uneditable by user
            public boolean isCalendarEditable()
            {
                return false;
            }
        };
        
        tableCalendar = new JTable(tableCalendarModel);
        stableCalendar = new JScrollPane(tableCalendar);
        panelCalendar = new JPanel(null);
        
        //set boundary
        panelCalendar.setBounds(0, 0, 335, 335);
        labelMonth.setBounds(150, 25, 100, 25);
        labelYear.setBounds(350, 25, 100, 25);
        buttonNext.setBounds(260, 25, 50, 25);
        buttonPrev.setBounds(10, 25, 50, 25);
        stableCalendar.setBounds(10, 50, 400, 400);
        frameMain.setVisible(true);
        
        //add controls to screen
        pane.add(panelCalendar);
        
        //this is done here so the buttons are overlaid on the calendar instead of behind
        for(int i = 0; i < 32; i++){
            //buttonDays[i] = new JButton(Integer.toString(i));
            buttonDays[i] = new JButton("   ");
            //buttonDays[i].setBounds(200, 10*i, 55, 25);//x coord,y coord, x size, y size
            panelCalendar.add(buttonDays[i]);
            buttonDays[i].setEnabled(true);
            buttonDays[i].setContentAreaFilled(false);//makes the button see-through
            buttonDays[i].addActionListener(new buttonDays_Action());
        }
        
        
        panelCalendar.add(stableCalendar);
        panelCalendar.add(labelMonth);
        panelCalendar.add(labelYear);
        panelCalendar.add(buttonNext);
        panelCalendar.add(buttonPrev);
        
        //get actual month/year
        GregorianCalendar cal = new GregorianCalendar();
        actualDay = cal.get(GregorianCalendar.DAY_OF_MONTH);
        actualMonth = cal.get(GregorianCalendar.MONTH);
        currentMonth = actualMonth;
        actualYear = cal.get(GregorianCalendar.YEAR);
        currentYear = actualYear;
                
        //action listeners
        buttonNext.addActionListener(new buttonNext_Action());
        buttonPrev.addActionListener(new buttonPrev_Action());
        
        //add weekday headers
        String[] weekHeaders = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (int i = 0; i < 7; i++)
        {
            tableCalendarModel.addColumn(weekHeaders[i]);
        }
        
        //specifies single calendar cell selection
        tableCalendar.setColumnSelectionAllowed(true);
        tableCalendar.setRowSelectionAllowed(true);
        tableCalendar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        //set row & column count
        tableCalendar.setRowHeight(50);
        tableCalendarModel.setRowCount(6);
        tableCalendarModel.setColumnCount(7);
        
        
        //refresh the calendar with actual month & year
        refreshCalendar(actualMonth, actualYear);
    }
    
    
    static class buttonNext_Action implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            //go forward one year checkj
            if (currentMonth == 11)
            {
                currentMonth = 0;
                currentYear += 1;
            }
            else
            {
                currentMonth += 1;
            }
            
            refreshCalendar(currentMonth, currentYear);
        }
    }
    
   
    static class buttonPrev_Action implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {
            //go back one year checkj
            if (currentMonth == 0)
            {
                currentMonth = 11;
                currentYear -= 1;
            }
            else
            {
                currentMonth -= 1;
            }
            
            refreshCalendar(currentMonth, currentYear);
        }
    }
    
    
    public static void refreshCalendar(int month, int year)
    {
        int numDays, firstDayOfMonth;
        
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        
        buttonNext.setEnabled(true);
        buttonPrev.setEnabled(true);
        
        labelMonth.setText(months[month]);
        labelMonth.setBounds(160, 25, 180, 25);
        
        labelYear.setText(Integer.toString(year));
        labelYear.setBounds(350, 25, 180, 25);
        
        for(int i = 0; i < 6; i++)
        {
            for(int j = 0; j < 7; j++)
            {
                tableCalendarModel.setValueAt(null, i, j);
            }
        }
        
        //get number of days & first day of month
        GregorianCalendar cal = new GregorianCalendar(year, month, 1);
        numDays = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        firstDayOfMonth = cal.get(GregorianCalendar.DAY_OF_WEEK);
            
        int row, column, xCoord = (firstDayOfMonth-1)*50-30, yCoord = 80;
        
        //draw days of the month, 1-31 onto gui
        for(int i = 1; i <= numDays; i++)
        {
            if ( (i > 1) && ( ((firstDayOfMonth + i - 2) % 7) == 0 ) )
            {
                yCoord += 50;
                xCoord = -42;
            }
            xCoord += 55;
            row = new Integer((i + firstDayOfMonth - 2) / 7);
            column = (i + firstDayOfMonth - 2) % 7;
            tableCalendarModel.setValueAt(i, row, column);
            buttonDays[i].setBounds(xCoord, yCoord, 55, 25);//x coord,y coord, x size, y size
        }
        tableCalendar.setDefaultRenderer(tableCalendar.getColumnClass(0), new tableCalendarRenderer());
    }
    
    
    static class tableCalendarRenderer extends DefaultTableCellRenderer
    {
        public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column){
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            
            //default background color
            setBackground(new Color(255, 255, 255));
            
            //unique color for todays date
            if( (value != null) && ( (Integer.parseInt(value.toString()) == actualDay) && (currentMonth == actualMonth) && (currentYear == actualYear) ) )
                setBackground(new Color(200, 200, 255));
                
            setBorder(null);
            setForeground(Color.black);
            return this;
        }
    }
    
    
    class buttonDays_Action implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {            
            ArrayList<Task>[] buttonMonth = cal2.getJan();
            
            int monthClicked = currentMonth+1;
            
            switch (monthClicked){
                case 1: buttonMonth = cal2.getJan();
                        break;
                        
                case 2: buttonMonth = cal2.getFeb();
                        break;
                    
                case 3: buttonMonth = cal2.getMar();
                        break;
                        
                case 4: buttonMonth = cal2.getApril();
                        break;
                    
                case 5: buttonMonth = cal2.getMay();
                        break;
                        
                case 6: buttonMonth = cal2.getJune();
                        break;
                    
                case 7: buttonMonth = cal2.getJul();
                        break;
                        
                case 8: buttonMonth = cal2.getAug();
                        break;
                    
                case 9: buttonMonth = cal2.getSept();
                        break;
                        
                case 10: buttonMonth = cal2.getOct();
                         break;
                    
                case 11: buttonMonth = cal2.getNov();
                         break;
                        
                case 12: buttonMonth = cal2.getDec();
                         break;
                    
                default: break;
                    
            }
            
            
           
            if(e.getSource() == buttonDays[1])
            {
                System.out.println("Tasks for the day: " + buttonMonth[0]);
            }
                
            else if(e.getSource() == buttonDays[2]){
                System.out.println("Tasks for the day: " + buttonMonth[1]);     
            }
                
            else if(e.getSource() == buttonDays[3]){
                System.out.println("Tasks for the day: " + buttonMonth[2]); 
            }
                
            else if(e.getSource() == buttonDays[4]){
                System.out.println("Tasks for the day: " + buttonMonth[3]);  
            }
                
            else if(e.getSource() == buttonDays[5]){
                System.out.println("Tasks for the day: " + buttonMonth[4]);               
            }
                
            else if(e.getSource() == buttonDays[6]){
                System.out.println("Tasks for the day: " + buttonMonth[5]);    
            }
                
            else if(e.getSource() == buttonDays[7]){
                System.out.println("Tasks for the day: " + buttonMonth[6]);   
            }
                
            else if(e.getSource() == buttonDays[8]){
                System.out.println("Tasks for the day: " + buttonMonth[7]); 
            }
                
            else if(e.getSource() == buttonDays[9]){
                System.out.println("Tasks for the day: " + buttonMonth[8]); 
            }
                
            else if(e.getSource() == buttonDays[10]){
                System.out.println("Tasks for the day: " + buttonMonth[9]); 
            }
                
            else if(e.getSource() == buttonDays[11]){
                System.out.println("Tasks for the day: " + buttonMonth[10]); 
            }
                
            else if(e.getSource() == buttonDays[12]){
                System.out.println("Tasks for the day: " + buttonMonth[11]);   
            }
                
            else if(e.getSource() == buttonDays[13]){
                System.out.println("Tasks for the day: " + buttonMonth[12]);
            }
                
            else if(e.getSource() == buttonDays[14]){
                System.out.println("Tasks for the day: " + buttonMonth[13]); 
            }
                
            else if(e.getSource() == buttonDays[15]){
                System.out.println("Tasks for the day: " + buttonMonth[14]);  
            }
                
            else if(e.getSource() == buttonDays[16]){
                System.out.println("Tasks for the day: " + buttonMonth[15]);
            }
                
            else if(e.getSource() == buttonDays[17]){
                System.out.println("Tasks for the day: " + buttonMonth[16]);   
            }
                
            else if(e.getSource() == buttonDays[18]){
                System.out.println("Tasks for the day: " + buttonMonth[17]);    
            }
                
            else if(e.getSource() == buttonDays[19]){
                System.out.println("Tasks for the day: " + buttonMonth[18]);    
            }
                
            else if(e.getSource() == buttonDays[20]){
                System.out.println("Tasks for the day: " + buttonMonth[19]);   
            }
                
            else if(e.getSource() == buttonDays[21]){
                System.out.println("Tasks for the day: " + buttonMonth[20]);    
            }
                
            else if(e.getSource() == buttonDays[22]){
                System.out.println("Tasks for the day: " + buttonMonth[21]);           
            }
                
            else if(e.getSource() == buttonDays[23]){
                System.out.println("Tasks for the day: " + buttonMonth[22]);   
            }
                
            else if(e.getSource() == buttonDays[24]){
                System.out.println("Tasks for the day: " + buttonMonth[23]);   
            }
                
            else if(e.getSource() == buttonDays[25]){
                System.out.println("Tasks for the day: " + buttonMonth[24]);   
            }
                
            else if(e.getSource() == buttonDays[26]){
                System.out.println("Tasks for the day: " + buttonMonth[25]);    
            }
                
            else if(e.getSource() == buttonDays[27]){
                System.out.println("Tasks for the day: " + buttonMonth[26]);   
            }
                
            else if(e.getSource() == buttonDays[28]){
                System.out.println("Tasks for the day: " + buttonMonth[27]);     
            }
                
            else if(e.getSource() == buttonDays[29]){
                System.out.println("Tasks for the day: " + buttonMonth[28]);
            }
                
            else if(e.getSource() == buttonDays[30]){
                System.out.println("Tasks for the day: " + buttonMonth[29]);  
            }
                
            else if(e.getSource() == buttonDays[31]){
                System.out.println("Tasks for the day: " + buttonMonth[30]);      
            }
        }
    }
}