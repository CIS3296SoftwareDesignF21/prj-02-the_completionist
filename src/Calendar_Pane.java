import javafx.application.Application;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;

public class Calendar_Pane extends Application {
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
    static Calendar_Pane cal2 = new Calendar_Pane();
    //static to_do_list toDoList = new to_do_list();
    @Override
    public void start(Stage stage) throws FileNotFoundException{

        //Calendar cal2 = new Calendar();
        //cal2.addTask();
        //cal2.printCalendar();

        //to_do_list toDoList = new to_do_list();
        //toDoList.add_task( new Task("do Homework", "11/1/2021", 1, "true"));
        //toDoList.add_task( new Task("workout", "11/16/2021", 3, "true"));
        //toDoList.add_task( new Task("meet with project group", "12/17/2021", 2, "true"));
        //toDoList.print();


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
                xCoord = -50;
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


    static class buttonDays_Action implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            //get the currentMonth & currentYear from where calendar is, day from button clicked
            //toString formatting and compare to every task date --- example format: 11/9/21
            //print if equal

            String dateToString;

            //ArrayList<Task> toDoList2 = toDoList.getList();


            if(e.getSource() == buttonDays[1])
            {
                dateToString = Integer.toString((currentMonth+1)) + "/" + "1" + "/" +Integer.toString(currentYear);
                //for(int i = 0; i < toDoList.getSize(); i++){
                    //if( dateToString.equals(toDoList2.get(i).getDate()) )
                        //System.out.println(toDoList2.get(i));
                //}
            }

            else if(e.getSource() == buttonDays[2]){
                dateToString = Integer.toString((currentMonth+1)) + "/" + "2" + "/" +Integer.toString(currentYear);
                /*
                for(int i = 0; i < toDoList.getSize(); i++){
                    if( dateToString.equals(toDoList2.get(i).getDate()) )
                        System.out.println(toDoList2.get(i));
                }

                 */
            }

            else if(e.getSource() == buttonDays[3]){
                dateToString = Integer.toString((currentMonth+1)) + "/" + "3" + "/" +Integer.toString(currentYear);
                /*
                for(int i = 0; i < toDoList.getSize(); i++){
                    if( dateToString.equals(toDoList2.get(i).getDate()) )
                        System.out.println(toDoList2.get(i));
                }

                 */
            }

            else if(e.getSource() == buttonDays[4]){
                dateToString = Integer.toString((currentMonth+1)) + "/" + "4" + "/" +Integer.toString(currentYear);
                /*
                for(int i = 0; i < toDoList.getSize(); i++){
                    if( dateToString.equals(toDoList2.get(i).getDate()) )
                        System.out.println(toDoList2.get(i));
                }

                 */
            }

            else if(e.getSource() == buttonDays[5]){
                dateToString = Integer.toString((currentMonth+1)) + "/" + "5" + "/" +Integer.toString(currentYear);
                /*
                for(int i = 0; i < toDoList.getSize(); i++){
                    if( dateToString.equals(toDoList2.get(i).getDate()) )
                        System.out.println(toDoList2.get(i));
                }

                 */
            }

            else if(e.getSource() == buttonDays[6]){
                dateToString = Integer.toString((currentMonth+1)) + "/" + "6" + "/" +Integer.toString(currentYear);
                /*
                for(int i = 0; i < toDoList.getSize(); i++){
                    if( dateToString.equals(toDoList2.get(i).getDate()) )
                        System.out.println(toDoList2.get(i));
                }

                 */
            }

            else if(e.getSource() == buttonDays[7]){
                dateToString = Integer.toString((currentMonth+1)) + "/" + "7" + "/" +Integer.toString(currentYear);
                /*
                for(int i = 0; i < toDoList.getSize(); i++){
                    if( dateToString.equals(toDoList2.get(i).getDate()) )
                        System.out.println(toDoList2.get(i));
                }

                 */
            }

            else if(e.getSource() == buttonDays[8]){
                dateToString = Integer.toString((currentMonth+1)) + "/" + "8" + "/" +Integer.toString(currentYear);
                /*
                for(int i = 0; i < toDoList.getSize(); i++){
                    if( dateToString.equals(toDoList2.get(i).getDate()) )
                        System.out.println(toDoList2.get(i));
                }

                 */
            }

            else if(e.getSource() == buttonDays[9]){
                dateToString = Integer.toString((currentMonth+1)) + "/" + "9" + "/" +Integer.toString(currentYear);
                /*
                for(int i = 0; i < toDoList.getSize(); i++){
                    if( dateToString.equals(toDoList2.get(i).getDate()) )
                        System.out.println(toDoList2.get(i));
                }

                 */
            }

            else if(e.getSource() == buttonDays[10]){
                dateToString = Integer.toString((currentMonth+1)) + "/" + "10" + "/" +Integer.toString(currentYear);
                /*
                for(int i = 0; i < toDoList.getSize(); i++){
                    if( dateToString.equals(toDoList2.get(i).getDate()) )
                        System.out.println(toDoList2.get(i));
                }

                 */
            }

            else if(e.getSource() == buttonDays[11]){
                dateToString = Integer.toString((currentMonth+1)) + "/" + "11" + "/" +Integer.toString(currentYear);
                /*
                for(int i = 0; i < toDoList.getSize(); i++){
                    if( dateToString.equals(toDoList2.get(i).getDate()) )
                        System.out.println(toDoList2.get(i));
                }

                 */
            }

            else if(e.getSource() == buttonDays[12]){
                dateToString = Integer.toString((currentMonth+1)) + "/" + "12" + "/" +Integer.toString(currentYear);
                /*
                for(int i = 0; i < toDoList.getSize(); i++){
                    if( dateToString.equals(toDoList2.get(i).getDate()) )
                        System.out.println(toDoList2.get(i));
                }

                 */
            }

            else if(e.getSource() == buttonDays[13]){
                dateToString = Integer.toString((currentMonth+1)) + "/" + "13" + "/" +Integer.toString(currentYear);
                /*
                for(int i = 0; i < toDoList.getSize(); i++){
                    if( dateToString.equals(toDoList2.get(i).getDate()) )
                        System.out.println(toDoList2.get(i));
                }

                 */
            }

            else if(e.getSource() == buttonDays[14]){
                dateToString = Integer.toString((currentMonth+1)) + "/" + "14" + "/" +Integer.toString(currentYear);
                /*
                for(int i = 0; i < toDoList.getSize(); i++){
                    if( dateToString.equals(toDoList2.get(i).getDate()) )
                        System.out.println(toDoList2.get(i));
                }

                 */
            }

            else if(e.getSource() == buttonDays[15]){
                dateToString = Integer.toString((currentMonth+1)) + "/" + "15" + "/" +Integer.toString(currentYear);
                /*
                for(int i = 0; i < toDoList.getSize(); i++){
                    if( dateToString.equals(toDoList2.get(i).getDate()) )
                        System.out.println(toDoList2.get(i));
                }

                 */
            }

            else if(e.getSource() == buttonDays[16]){
                dateToString = Integer.toString((currentMonth+1)) + "/" + "16" + "/" +Integer.toString(currentYear);
                /*
                for(int i = 0; i < toDoList.getSize(); i++){
                    if( dateToString.equals(toDoList2.get(i).getDate()) )
                        System.out.println(toDoList2.get(i));
                }

                 */
            }

            else if(e.getSource() == buttonDays[17]){
                dateToString = Integer.toString((currentMonth+1)) + "/" + "17" + "/" +Integer.toString(currentYear);
                /*
                for(int i = 0; i < toDoList.getSize(); i++){
                    if( dateToString.equals(toDoList2.get(i).getDate()) )
                        System.out.println(toDoList2.get(i));
                }

                 */
            }

            else if(e.getSource() == buttonDays[18]){
                dateToString = Integer.toString((currentMonth+1)) + "/" + "18" + "/" +Integer.toString(currentYear);
                /*
                for(int i = 0; i < toDoList.getSize(); i++){
                    if( dateToString.equals(toDoList2.get(i).getDate()) )
                        System.out.println(toDoList2.get(i));
                }

                 */
            }

            else if(e.getSource() == buttonDays[19]){
                dateToString = Integer.toString((currentMonth+1)) + "/" + "19" + "/" +Integer.toString(currentYear);
                /*
                for(int i = 0; i < toDoList.getSize(); i++){
                    if( dateToString.equals(toDoList2.get(i).getDate()) )
                        System.out.println(toDoList2.get(i));
                }

                 */
            }

            else if(e.getSource() == buttonDays[20]){
                dateToString = Integer.toString((currentMonth+1)) + "/" + "20" + "/" +Integer.toString(currentYear);
                /*
                for(int i = 0; i < toDoList.getSize(); i++){
                    if( dateToString.equals(toDoList2.get(i).getDate()) )
                        System.out.println(toDoList2.get(i));
                }

                 */
            }

            else if(e.getSource() == buttonDays[21]){
                dateToString = Integer.toString((currentMonth+1)) + "/" + "21" + "/" +Integer.toString(currentYear);
                /*
                for(int i = 0; i < toDoList.getSize(); i++){
                    if( dateToString.equals(toDoList2.get(i).getDate()) )
                        System.out.println(toDoList2.get(i));
                }

                 */
            }

            else if(e.getSource() == buttonDays[22]){
                dateToString = Integer.toString((currentMonth+1)) + "/" + "22" + "/" +Integer.toString(currentYear);
                /*
                for(int i = 0; i < toDoList.getSize(); i++){
                    if( dateToString.equals(toDoList2.get(i).getDate()) )
                        System.out.println(toDoList2.get(i));
                }

                 */
            }

            else if(e.getSource() == buttonDays[23]){
                dateToString = Integer.toString((currentMonth+1)) + "/" + "23" + "/" +Integer.toString(currentYear);
                /*
                for(int i = 0; i < toDoList.getSize(); i++){
                    if( dateToString.equals(toDoList2.get(i).getDate()) )
                        System.out.println(toDoList2.get(i));
                }

                 */
            }

            else if(e.getSource() == buttonDays[24]){
                dateToString = Integer.toString((currentMonth+1)) + "/" + "24" + "/" +Integer.toString(currentYear);
                /*
                for(int i = 0; i < toDoList.getSize(); i++){
                    if( dateToString.equals(toDoList2.get(i).getDate()) )
                        System.out.println(toDoList2.get(i));
                }

                 */
            }

            else if(e.getSource() == buttonDays[25]){
                dateToString = Integer.toString((currentMonth+1)) + "/" + "25" + "/" +Integer.toString(currentYear);
                /*
                for(int i = 0; i < toDoList.getSize(); i++){
                    if( dateToString.equals(toDoList2.get(i).getDate()) )
                        System.out.println(toDoList2.get(i));
                }

                 */
            }

            else if(e.getSource() == buttonDays[26]){
                dateToString = Integer.toString((currentMonth+1)) + "/" + "26" + "/" +Integer.toString(currentYear);
                /*
                for(int i = 0; i < toDoList.getSize(); i++){
                    if( dateToString.equals(toDoList2.get(i).getDate()) )
                        System.out.println(toDoList2.get(i));
                }

                 */
            }

            else if(e.getSource() == buttonDays[27]){
                dateToString = Integer.toString((currentMonth+1)) + "/" + "27" + "/" +Integer.toString(currentYear);
                /*
                for(int i = 0; i < toDoList.getSize(); i++){
                    if( dateToString.equals(toDoList2.get(i).getDate()) )
                        System.out.println(toDoList2.get(i));
                }

                 */
            }

            else if(e.getSource() == buttonDays[28]){
                dateToString = Integer.toString((currentMonth+1)) + "/" + "28" + "/" +Integer.toString(currentYear);
                /*
                for(int i = 0; i < toDoList.getSize(); i++){
                    if( dateToString.equals(toDoList2.get(i).getDate()) )
                        System.out.println(toDoList2.get(i));
                }

                 */
            }

            else if(e.getSource() == buttonDays[29]){
                dateToString = Integer.toString((currentMonth+1)) + "/" + "29" + "/" +Integer.toString(currentYear);
                /*
                for(int i = 0; i < toDoList.getSize(); i++){
                    if( dateToString.equals(toDoList2.get(i).getDate()) )
                        System.out.println(toDoList2.get(i));
                }

                 */
            }

            else if(e.getSource() == buttonDays[30]){
                dateToString = Integer.toString((currentMonth+1)) + "/" + "30" + "/" +Integer.toString(currentYear);
                /*
                for(int i = 0; i < toDoList.getSize(); i++){
                    if( dateToString.equals(toDoList2.get(i).getDate()) )
                        System.out.println(toDoList2.get(i));
                }

                 */
            }

            else if(e.getSource() == buttonDays[31]){
                dateToString = Integer.toString((currentMonth+1)) + "/" + "31" + "/" +Integer.toString(currentYear);
                /*
                for(int i = 0; i < toDoList.getSize(); i++){
                    if( dateToString.equals(toDoList2.get(i).getDate()) )
                        System.out.println(toDoList2.get(i));
                }

                 */
            }
        }
    }
}
