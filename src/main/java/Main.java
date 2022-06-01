import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class Main extends JFrame {

    public static void main(String[] args) {
        new Main();
    }

    private final int WITH_FRAME= 1300;
    private final int HIGTH_FRAME= 1000;
    private final int RESULT_FRAME_WITH= 800;
    private final int RESULT_FRAME_HIGTH= 400;
    private final int WITH_BUTTON= 80;
    private final int HIGTH_BUTTON= 40;
    private final int X_Y_START= 0;
    private final int FONT_SIZE= 37;
    private int filesCounter=0;

    public Main() {
        this.setTitle("Titanic Passengers Data");
        this.setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBounds(X_Y_START,X_Y_START,WITH_FRAME,HIGTH_FRAME);
        MainPanel mainPanel= new MainPanel(X_Y_START, X_Y_START, WITH_FRAME, HIGTH_FRAME);
        this.add(mainPanel);
        this.setVisible(true);

        // כפתור חיפוש
        JButton scherchButton = new JButton("serch");
        scherchButton .setBounds(WITH_FRAME/2,(HIGTH_FRAME/6)*4,WITH_BUTTON,HIGTH_BUTTON);
        scherchButton.setBackground(Color.GREEN);
        scherchButton.setVisible(true);
        scherchButton.requestFocus();
        scherchButton.addActionListener((event -> {
            filesCounter++;
            mainPanel.declerativeSelectClass();
            mainPanel.declerativeSelectSex();
            mainPanel.declerativeSelectPort();
            mainPanel.fromWichPassengerNumberToChek();
            mainPanel.selectByPassengerName();
            mainPanel.selectBySibSp();
            mainPanel.selectByParch();
            mainPanel.selectByTicketNum();
            mainPanel.selectByMinCostTicket();
              //  חלון עם הפורמט הסופי
            JFrame resultFrame = new JFrame();
            resultFrame.setSize(RESULT_FRAME_WITH, RESULT_FRAME_HIGTH);
            int allCritetrionSize=mainPanel.selectByCabinNum().size(); // סינון אחרון של הרשימה (ללא שרידות)
            int allCritetrionSizeWithSurvive=mainPanel.selectBySurvived().size(); // סינון הרשימה הסופית כולל שרידות
            JLabel resultFrameText=new JLabel("Total Rows: "+allCritetrionSize+"( "+allCritetrionSizeWithSurvive+" survived, "+(allCritetrionSize-allCritetrionSizeWithSurvive)+" did not.)");
            Font resultFrameTextFont=new Font("",Font.BOLD,FONT_SIZE);
            resultFrameText.setFont(resultFrameTextFont);
            resultFrameText.setVisible(true);
            resultFrame.add(resultFrameText);
            resultFrame.setVisible(true);
            scherchButton.requestFocus();
            //    TODO: מתודת המיון לא תקינה

            List<Passenger> myList=mainPanel.getFilterList();
            Collections.sort(myList);// מיון לפי שם נוסע
            //  להתאמת מיקום במחשבך שלך, הזן בערך path את הכתובת של המיקום בו תרצה לשמור את הכתובת
            String path="C:\\Users\\lenovo\\Desktop\\Titanic-Project\\csv."+filesCounter+".csv";
            String ansFormatText="PassengerId,Survived,Pclass,Name,Sex,Age,SibSp,Parch,Ticket,Fare,Cabin,Embarked\n";
            for (int i=0;i<myList.size();i++){
                ansFormatText +=myList.get(i).getId()+","
                        +myList.get(i).getSurvived()+","
                        +myList.get(i).getpClass()+","
                        +myList.get(i).getName()+","
                        +myList.get(i).getSex()+","
                        +myList.get(i).getAge()+","
                        +myList.get(i).getSibSp()+","
                        +myList.get(i).getParch()+","
                        +myList.get(i).getTicketNum()+","
                        +myList.get(i).getFare()+","
                        +myList.get(i).getCabin()+","
                        +myList.get(i).getEmbarked()+"\n";

            }
            System.out.println(ansFormatText);
            wirteToFile(ansFormatText,path); // יצירת קובץ חדש!

        }));
        this.add(scherchButton);
        scherchButton.requestFocus();

          //      כפתור יצירת סטטיסטיקה
        JButton staticButton = new JButton("create static");
        staticButton .setBounds((WITH_FRAME/2)-(scherchButton.getWidth()/2),(HIGTH_FRAME/5)*4,WITH_BUTTON*2,HIGTH_BUTTON);
        staticButton.setBackground(Color.yellow);
        staticButton.setVisible(true);
        staticButton.requestFocus();
        staticButton.addActionListener((event -> {
            String path="C:\\Users\\lenovo\\Desktop\\Titanic-Project\\Statistic.txt";
        String statisticAns="אחוזי השרדות לפי מחלקה:\n ראשונה- 62.9%\n שנייה- 45.6% \n שלישית- 24.2% \n " +
                "אחוזי השרדות לפי מין: \n זכר- 18.8% \n נקבה- 74.2% \n" +
                "אחוזי השרדות לפי גילאים:\n גילאי 0-10: 59.3% \n גילאי 11-20: 37% \n גילאי 21-30: 36% \n גילאי 31-40: 43.8% \n גילאי 41-50: 38.5% \n גילאי 50+ : 33.3% " +
                "אחוזי השרדות לפי האם יש בן משפחה על הסיפון: \n כן: 50.5% \n לא: 30.3% \n" +
                "אחוזי השרדות לפי מחיר כרטיס: \n " +
                "0-10 פאונד: 19.9%  \n " +
                "11-30 פאונד: 43.1% \n " +
                "30 פאונד ומעלה: 58.1% \n " +
                "אחוזי השרדות לפי נמל יציאה: \n נמל סאות'המפטון: 33.6% \n נמל קווינסטאון: 38.9% \n נמל צ'רבורג: 55.3% \n " ;
            System.out.println(statisticAns);
            wirteToFile(statisticAns,path);
       }));
        this.add(staticButton);
        staticButton.requestFocus();


  }
    public static void wirteToFile (String text,String path){ // כתיבת קובץ טקסט
        try {
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write(text);
            fileWriter.close();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}