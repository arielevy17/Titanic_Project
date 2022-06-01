import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class MainPanel extends JPanel {

    private final int WITH_FRAME = 1300;
    private final int HIGTH_FRAME = 1000;
    private final int START_X_LABELS = 20;
    private final int START_Y_BOXES = 40;
    private final int BOXES_WITH = 80;
    private final int LABELS_WITH = 150;
    private final int BOXES_LABEL_FONT_HIGTH = 30;
    private final int DISTANCE_BETWEEN_BOXES = 40;
    private final int X_Y_START = 0;


    private JComboBox<String> pClassBox;
    private JComboBox<String> sexComboBox;
    private JComboBox<String> portsComboBox;
    private List<Passenger> passengerList = new ArrayList<>();
    private List<Passenger> filterList = new ArrayList<>();
    private JTextField minPassengerNumber;
    private JTextField passengerNameField;
    private JTextField sibSpNumberField;
    private JTextField parchNumberField;
    private JTextField ticketNumberField;
    private JTextField minCostTicketField;
    private JTextField cabinNunField;
    private ImageIcon backGround;


    public MainPanel(int x, int y, int width, int height) {
        File file = new File(Constants.PATH_TO_DATA_FILE); //this is the path to the data file
        try {
            Scanner scanner = new Scanner(file);
            int i = 0;

            while (scanner.hasNextLine()) {     // חילוץ מידע מהקובץ
                String passenger = scanner.nextLine();
                if (i != 0) {      // ששורה הראשונה עם שמות הפרמטרים לא תיכנס
                    Passenger passengerObject = new Passenger(passenger);
                    passengerList.add(passengerObject);
                }
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //  הגדרות החלון
        this.setLayout(null);
        this.setBounds(X_Y_START, X_Y_START, WITH_FRAME, HIGTH_FRAME);
        backGround =new ImageIcon("שירותי ספנות.jpg");
        repaint();
        //JLabel image=new JLabel(titanicImage);
       // image.setBounds(X_Y_START,X_Y_START,WITH_FRAME,HIGTH_FRAME);


        // הגדרת pclass combo box + jlabel
        JLabel pclassLabel = new JLabel("Select Passenger Class: ");
        pclassLabel.setBounds(START_X_LABELS, START_Y_BOXES, LABELS_WITH, BOXES_LABEL_FONT_HIGTH);
        this.add(pclassLabel);
        this.pClassBox = new JComboBox<>(Constants.PASSENGER_CLASS_OPTIONS);
        this.pClassBox.setBounds(pclassLabel.getX() + pclassLabel.getWidth() + DISTANCE_BETWEEN_BOXES, START_Y_BOXES, BOXES_WITH, BOXES_LABEL_FONT_HIGTH);
        this.add(this.pClassBox);

        // הגדרת sex combo box + jlabel
        JLabel wichSexLabel = new JLabel("Select Passenger Sex: ");
        wichSexLabel.setBounds(START_X_LABELS, pclassLabel.getY() + DISTANCE_BETWEEN_BOXES, LABELS_WITH, BOXES_LABEL_FONT_HIGTH);
        this.add(wichSexLabel);
        String[] sexComboBoxOptions = {"All", "Male", "Female"};
        this.sexComboBox = new JComboBox<String>(sexComboBoxOptions);
        this.sexComboBox.setBounds(wichSexLabel.getX() + wichSexLabel.getWidth() + DISTANCE_BETWEEN_BOXES, wichSexLabel.getY(), BOXES_WITH, BOXES_LABEL_FONT_HIGTH);
        this.add(this.sexComboBox);

        // הגדרת port combo box + jlabel
        JLabel wichPortLabel = new JLabel("Select Passenger Port: ");
        wichPortLabel.setBounds(START_X_LABELS, sexComboBox.getY() + DISTANCE_BETWEEN_BOXES, LABELS_WITH, BOXES_LABEL_FONT_HIGTH);
        this.add(wichPortLabel);
        String[] PortsComboBoxOptions = {"All", "Southampton", "Queenstown", "Cherbourg"};
        this.portsComboBox = new JComboBox<String>(PortsComboBoxOptions);
        this.portsComboBox.setBounds(START_X_LABELS + wichPortLabel.getWidth() + DISTANCE_BETWEEN_BOXES, wichPortLabel.getY(), BOXES_WITH, BOXES_LABEL_FONT_HIGTH);
        this.add(this.portsComboBox);

        // minPassengerNumber text field + jlabel
        JLabel fromWichPassengerNumberLabel = new JLabel("From Wich Passenger Number To Chek: ");
        fromWichPassengerNumberLabel.setBounds(START_X_LABELS, wichPortLabel.getY() + DISTANCE_BETWEEN_BOXES, LABELS_WITH * 2, BOXES_LABEL_FONT_HIGTH);
        this.add(fromWichPassengerNumberLabel);
        this.minPassengerNumber = new JTextField();
        minPassengerNumber.setBounds(START_X_LABELS + fromWichPassengerNumberLabel.getWidth(), fromWichPassengerNumberLabel.getY(), BOXES_WITH, BOXES_LABEL_FONT_HIGTH);
        this.add(minPassengerNumber);

        //   שם נוסע - textFiled+label
        JLabel passengerNameLabel = new JLabel("write passenger name : ");
        passengerNameLabel.setBounds(START_X_LABELS,fromWichPassengerNumberLabel.getY()+DISTANCE_BETWEEN_BOXES,LABELS_WITH, BOXES_LABEL_FONT_HIGTH);
        this.add(passengerNameLabel);
        this.passengerNameField = new JTextField();
        passengerNameField.setBounds(START_X_LABELS+passengerNameLabel.getWidth()+DISTANCE_BETWEEN_BOXES, passengerNameLabel.getY(), BOXES_WITH*2, BOXES_LABEL_FONT_HIGTH);
        this.add(passengerNameField);

        //   מספר אחים/בני זוג - textFiled+label
        JLabel sibSpNumberLabel = new JLabel("write passenger brother/partners : ");
        sibSpNumberLabel.setBounds(START_X_LABELS,passengerNameLabel.getY()+DISTANCE_BETWEEN_BOXES,LABELS_WITH*2, BOXES_LABEL_FONT_HIGTH);
        this.add(sibSpNumberLabel);
        this.sibSpNumberField = new JTextField();
        sibSpNumberField.setBounds(START_X_LABELS+sibSpNumberLabel.getWidth(), sibSpNumberLabel.getY(), BOXES_WITH, BOXES_LABEL_FONT_HIGTH);
        this.add(sibSpNumberField);

        //   מספר הורים/ילדים - textFiled+label
        JLabel parchNumberLabel = new JLabel("write passenger parents/kids : ");
        parchNumberLabel.setBounds(START_X_LABELS,sibSpNumberLabel.getY()+DISTANCE_BETWEEN_BOXES,LABELS_WITH*2, BOXES_LABEL_FONT_HIGTH);
        this.add(parchNumberLabel);
        this.parchNumberField = new JTextField();
        parchNumberField.setBounds(START_X_LABELS+sibSpNumberLabel.getWidth(), parchNumberLabel.getY(), BOXES_WITH, BOXES_LABEL_FONT_HIGTH);
        this.add(parchNumberField);

        //   מספר כרטיס - textFiled+label
        JLabel ticketNumberLabel = new JLabel("write ticket number: ");
        ticketNumberLabel.setBounds(START_X_LABELS,parchNumberLabel.getY()+DISTANCE_BETWEEN_BOXES,LABELS_WITH, BOXES_LABEL_FONT_HIGTH);
        this.add(ticketNumberLabel);
        this.ticketNumberField = new JTextField();
        ticketNumberField.setBounds(START_X_LABELS+ticketNumberLabel.getWidth()+DISTANCE_BETWEEN_BOXES, ticketNumberLabel.getY(), BOXES_WITH, BOXES_LABEL_FONT_HIGTH);
        this.add(ticketNumberField);

        //   מחיר כרטיס מינימאלי - textFiled+label
        JLabel minTicketCostLabel = new JLabel("write ticket min cost: ");
        minTicketCostLabel.setBounds(START_X_LABELS,ticketNumberLabel.getY()+DISTANCE_BETWEEN_BOXES,LABELS_WITH, BOXES_LABEL_FONT_HIGTH);
        this.add(minTicketCostLabel);
        this.minCostTicketField = new JTextField();
        minCostTicketField.setBounds(START_X_LABELS+minTicketCostLabel.getWidth()+DISTANCE_BETWEEN_BOXES, minTicketCostLabel.getY(), BOXES_WITH, BOXES_LABEL_FONT_HIGTH);
        this.add(minCostTicketField);

        //   מספר תא - textFiled+label
        JLabel cabinNumLabel = new JLabel("write cabin number: ");
        cabinNumLabel.setBounds(START_X_LABELS,minTicketCostLabel.getY()+DISTANCE_BETWEEN_BOXES,LABELS_WITH, BOXES_LABEL_FONT_HIGTH);
        this.add(cabinNumLabel);
        this.cabinNunField = new JTextField();
        cabinNunField.setBounds(START_X_LABELS+cabinNumLabel.getWidth()+DISTANCE_BETWEEN_BOXES, cabinNumLabel.getY(), BOXES_WITH, BOXES_LABEL_FONT_HIGTH);
        this.add(cabinNunField);

        //   הודעת חירום
        JTextArea message= new JTextArea("Urgent message !!\n" +
                "Following the sinking of our flagship - the Titanic.\n" +
                "We publish the passengers' details, and in addition we have set up unique result filters for you" +"\n in order for you to locate your loved ones.");
        message.setBounds(START_X_LABELS,cabinNunField.getY()+(DISTANCE_BETWEEN_BOXES*3),WITH_FRAME, BOXES_LABEL_FONT_HIGTH*4);
        Font fontMessage=new Font("",Font.BOLD,20);
        message.setFont(fontMessage);
        message.setBackground(Color.RED);
        this.add(message);

    }

                          //   מתודות ל - combo boxes-

    public List<Passenger> declerativeSelectSex() {    //  פונקציה לבחירת מין
        List<Passenger> exList = new ArrayList<>();
        if (sexComboBox.getSelectedItem().equals("All")) {   //  הכל
           exList= filterList;
        }
        if (sexComboBox.getSelectedItem().equals("Male")) {   //  גברים
            exList=filterList.stream().filter(passenger -> passenger.getSex().equals("male")).collect(Collectors.toList());
        }
        else if (sexComboBox.getSelectedItem().equals("Female")) {    //  נשים
            exList=filterList.stream().filter(passenger -> passenger.getSex().equals("female")).collect(Collectors.toList());
        }
        filterList=exList;
        return filterList;
    }

    public List<Passenger> declerativeSelectClass () {  //  פונקציה לבחירת מחלקת הנוסע
        filterList.clear();
        if (this.pClassBox.getSelectedItem().equals("All")) {  //  הכל
            filterList = passengerList;
        }
        else if (this.pClassBox.getSelectedItem().equals("1st")) {  // מחלקה ראשונה
            filterList = passengerList.stream().filter(passenger -> passenger.getpClass() == (1)).collect(Collectors.toList());
        }
        else if (this.pClassBox.getSelectedItem().equals("2nd")) {  //  מחלקה שנייה
            filterList = passengerList.stream().filter(passenger -> passenger.getpClass() == (2)).collect(Collectors.toList());
        }
        else if (this.pClassBox.getSelectedItem().equals("3rd")) {  //  מחלקה שלישית
            filterList = passengerList.stream().filter(passenger -> passenger.getpClass() == (3)).collect(Collectors.toList());
        }
        return filterList;
    }

    public List<Passenger> declerativeSelectPort(){   //  פונקציה לבחירת נמל הנוסע
        List<Passenger> exList = new ArrayList<>();

        if (portsComboBox.getSelectedItem().equals("All")) {   //  הכל (ללא סינון נמל)
            exList = filterList;
        }

       else if (pClassBox.getSelectedItem().equals("1st")|| pClassBox.getSelectedItem().equals("All")) {   //ישנם נוסעים ממחלקה מס' אחת שהנמל מהם יצאו אינו ידוע!!
           for (int i = 0; i < filterList.size(); i++) {
               try {
                   filterList.get(i).getEmbarked().isEmpty();
               } catch (NullPointerException n) {
                   filterList.remove(i);
               }
           }
       }

               if (portsComboBox.getSelectedItem().equals("Southampton")) {   //  נמל סאות'המפטון
                   exList = filterList.stream().filter(passenger -> passenger.getEmbarked().equals("S")).collect(Collectors.toList());
               } else if (portsComboBox.getSelectedItem().equals("Queenstown")) {    //  נמל קווינטאון
                   exList = filterList.stream().filter(passenger -> passenger.getEmbarked().equals("Q")).collect(Collectors.toList());
               } else if (portsComboBox.getSelectedItem().equals("Cherbourg")) {    //  נמל צ'רבורג
                   exList = filterList.stream().filter(passenger -> passenger.getEmbarked().equals("C")).collect(Collectors.toList());
               }

        filterList=exList;
        return filterList;
    }


    public List<Passenger> fromWichPassengerNumberToChek(){   //  חיפוש לפי מספר נוסע מינימאלי

        int num=0;
        if (!minPassengerNumber.getText().equals("")) {
            try {              //  תפיסת חריגה עקב הקלדה שגויה של המשתמש
                num = Integer.valueOf(minPassengerNumber.getText()); //  המרה מSTRING ל INT

                if (num < 1 || num > 891) {  // מספר שאינו קיים, גדוך או קטן מדיי
                    JFrame wrongMessage = new JFrame();
                    wrongMessage.setSize(WITH_FRAME / 5, HIGTH_FRAME / 4);
                    JLabel wrongMessageText=new JLabel("this passenger number is not exist!");
                    wrongMessageText.setVisible(true);
                    wrongMessage.add(wrongMessageText);
                    wrongMessage.setVisible(true);
                    System.out.println("this passenger number is not exist!");
                }

                int finalNum = num;
                filterList = filterList.stream().filter(passenger -> passenger.getId() >= finalNum).collect(Collectors.toList());

            } catch (Exception e) { //  כאשר לא הוזן מספר בתיבת הטקסט
                JFrame wrongMessage = new JFrame();
                wrongMessage.setSize(WITH_FRAME / 5, HIGTH_FRAME / 4);
                JLabel wrongMessageText=new JLabel("your min passenger number is illegal!");
                wrongMessageText.setVisible(true);
                wrongMessage.add(wrongMessageText);
                wrongMessage.setVisible(true);
                System.out.println("your min passenger number is illegal!");
            }
        }
        return filterList;
        }

       public List<Passenger> selectByPassengerName(){
           List<Passenger> exList = new ArrayList<>();
           if (!passengerNameField.getText().equals("")){
            for (int i=0;i<filterList.size();i++){
               String [] nameParts= filterList.get(i).getName().split(" ");
               for (int j=0;j<nameParts.length;j++){
                   if (nameParts[j].equals(passengerNameField.getText())){
                       exList.add(filterList.get(i));
                   }
               }
            }
            filterList=exList;
        }
        return filterList;
       }

       public List<Passenger> selectBySibSp() {   //  חיפוש לפי מספר אחים/ בני זוג
           int num=0;
           if (!sibSpNumberField.getText().equals("")) {
               try {              //  תפיסת חריגה עקב הקלדה שגויה של המשתמש
                   num = Integer.valueOf(sibSpNumberField.getText());

                   if (num < 0) {
                       JFrame wrongMessage = new JFrame();
                       wrongMessage.setSize(WITH_FRAME / 5, HIGTH_FRAME / 4);
                       JLabel wrongMessageText=new JLabel("your number shuld by bigger then 0!");
                       wrongMessageText.setVisible(true);
                       wrongMessage.add(wrongMessageText);
                       wrongMessage.setVisible(true);
                       System.out.println("this passenger number is not exist!");
                   }

                   int finalNum = num;
                       //   מתודת המיון
                   filterList = filterList.stream().filter(passenger -> passenger.getSibSp() == finalNum).collect(Collectors.toList());

               } catch (Exception e) {  //  כאשר לא הוזן מספר בתיבת הטקסט
                   JFrame wrongMessage = new JFrame();
                   wrongMessage.setSize(WITH_FRAME / 5, HIGTH_FRAME / 4);
                   JLabel wrongMessageText=new JLabel("your number is illegal!");
                   wrongMessageText.setVisible(true);
                   wrongMessage.add(wrongMessageText);
                   wrongMessage.setVisible(true);
                   System.out.println("your min passenger number is illegal!");
               }
           }
           return filterList;
       }

    public List<Passenger> selectByParch() {   //  חיפוש לפי מספר אחים/ בני זוג
        int num=0;
        if (!parchNumberField.getText().equals("")) {
            try {              //  תפיסת חריגה עקב הקלדה שגויה של המשתמש
                num = Integer.valueOf(parchNumberField.getText());

                if (num < 0) {
                    JFrame wrongMessage = new JFrame();
                    wrongMessage.setSize(WITH_FRAME / 5, HIGTH_FRAME / 4);
                    JLabel wrongMessageText=new JLabel("your number shuld by bigger then 0!");
                    wrongMessageText.setVisible(true);
                    wrongMessage.add(wrongMessageText);
                    wrongMessage.setVisible(true);
                    System.out.println("this passenger number is not exist!");
                }

                int finalNum = num;
                //   מתודת המיון
                filterList = filterList.stream().filter(passenger -> passenger.getParch() == finalNum).collect(Collectors.toList());

            } catch (Exception e) {  //  כאשר לא הוזן מספר בתיבת הטקסט
                JFrame wrongMessage = new JFrame();
                wrongMessage.setSize(WITH_FRAME / 5, HIGTH_FRAME / 4);
                JLabel wrongMessageText=new JLabel("your number is illegal!");
                wrongMessageText.setVisible(true);
                wrongMessage.add(wrongMessageText);
                wrongMessage.setVisible(true);
                System.out.println("your min passenger number is illegal!");
            }
        }
        return filterList;
    }

    public List<Passenger> selectByTicketNum(){  //  בחירה לפי מספר כרטיס
        if (!ticketNumberField.getText().equals("")){
            filterList = filterList.stream().filter(passenger -> passenger.getTicketNum().equals(ticketNumberField.getText())).collect(Collectors.toList());
        }
        return filterList;
    }

    public List<Passenger> selectByMinCostTicket(){   //  חיפוש לפי מחיר כרטיס מינימאלי

        float num=0;
        if (!minCostTicketField.getText().equals("")) {
            try {              //  תפיסת חריגה עקב הקלדה שגויה של המשתמש
                num = Float.valueOf(minCostTicketField.getText()); //  המרה מSTRING ל INT

                if (num < 0) {  // מספר שאינו קיים, קטן מדיי
                    JFrame wrongMessage = new JFrame();
                    wrongMessage.setSize(WITH_FRAME / 5, HIGTH_FRAME / 4);
                    JLabel wrongMessageText=new JLabel("your cost shuld be bigger then 0!");
                    wrongMessageText.setVisible(true);
                    wrongMessage.add(wrongMessageText);
                    wrongMessage.setVisible(true);
                    System.out.println("this passenger number is not exist!");
                }

                float finalNum = num;  //  מתודת הסינון
                filterList = filterList.stream().filter(passenger -> passenger.getFare() >= finalNum).collect(Collectors.toList());

            } catch (Exception e) { //  כאשר לא הוזן מספר בתיבת הטקסט
                JFrame wrongMessage = new JFrame();
                wrongMessage.setSize(WITH_FRAME / 5, HIGTH_FRAME / 4);
                JLabel wrongMessageText=new JLabel("your min cost ticket is illegal!");
                wrongMessageText.setVisible(true);
                wrongMessage.add(wrongMessageText);
                wrongMessage.setVisible(true);
                System.out.println("your min passenger number is illegal!");
            }
        }
        return filterList;
    }

    public List<Passenger> selectByCabinNum(){  //  בחירה לפי מספר כרטיס
        if (!cabinNunField.getText().equals("")){
            filterList = filterList.stream().filter(passenger -> passenger.getCabin().equals(cabinNunField.getText())).collect(Collectors.toList());
        }
        return filterList;
    }

    public List<Passenger> selectBySurvived(){   //   מתודת מיון ע"י שרידות
        filterList= filterList.stream().filter(passenger -> passenger.getSurvived()==1).collect(Collectors.toList());
        return filterList;
    }

    public List<Passenger> getFilterList() {
        return filterList;
    }

   /* protected void painComponent(Graphics g){
        super.paintComponent(g);
            this.backGround.paintIcon(this,g,X_Y_START,X_Y_START);
        doDrawing(g);
        Toolkit.getDefaultToolkit().sync();
    }
    private void doDrawing(Graphics g) {  // מתודת גרפיקה שמציירת את התמונה- בלעדיה אין תמונה-חשוב מאוד!!!
        Graphics2D g2d = (Graphics2D) g;
    }
    */

    // מתודות ציור(גרפיקה)
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.backGround.paintIcon(this, g, X_Y_START, X_Y_START);
        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {  // מתודת גרפיקה שמציירת את התמונה- בלעדיה אין תמונה-חשוב מאוד!!!
        Graphics2D g2d = (Graphics2D) g;

    }

}


