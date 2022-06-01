public class Passenger implements Comparable<Passenger> {
    private int id;
    private int survived;
    private int pClass;
    private String name;
    private String sex;
    private float age;
    private int sibSp;
    private  int parch;
    private String ticketNum;
    private float fare;
    private String cabin;
    private String embarked;

    public Passenger(String lineData) {
        String[] dataItem = lineData.split(",");
        this.id = Integer.valueOf(dataItem[0]);  //  המרה לint מsring
       this.survived=Integer.valueOf(dataItem[1]);
        this.pClass = Integer.valueOf(dataItem[2]);
        this.name = getFormattedName(dataItem[3], dataItem[4]);
        this.sex = dataItem[5];
        if (dataItem[6].equals("")) {
            age = -1;
        } else {
            this.age = Float.valueOf(dataItem[6]);
        }
        this.sibSp = Integer.valueOf(dataItem[7]);
        this.parch = Integer.valueOf(dataItem[8]);
        this.ticketNum = dataItem[9];
        this.fare = Float.valueOf(dataItem[10]);
        if (dataItem[11].equals("")) {
            cabin = "unknown port";
        } else {
            this.cabin = dataItem[11];
        }
        try {  //  חריגה לאלו שאין להם נמל-ולכן תא המערך הזה לא קיים(כי הוא בסוף)
            this.embarked = dataItem[12];

        } catch (Exception e){
            System.out.println("unknown port");
        }
    }


    public static String getFormattedName(String word1,String word2){ // המתודה המחזירה שם בפורמט המבוקש
        String ans;
        String [] ansNameParts =word2.split("\\.");  // פיצול שם הפרטי ע"י נקודה
        ans = ansNameParts[1]+"  "+word1;
        return ans;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSurvived() {
        return survived;
    }

    public void setSurvived(int survived) {
        this.survived = survived;
    }

    public int getpClass() {
        return pClass;
    }

    public void setpClass(int pClass) {
        this.pClass = pClass;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public float getAge() {
        return age;
    }

    public void setAge(float age) {
        this.age = age;
    }

    public int getSibSp() {
        return sibSp;
    }

    public void setSibSp(int sibSp) {
        this.sibSp = sibSp;
    }

    public int getParch() {
        return parch;
    }

    public void setParch(int parch) {
        this.parch = parch;
    }

    public String getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(String ticketNum) {
        this.ticketNum = ticketNum;
    }

    public float getFare() {
        return fare;
    }

    public void setFare(float fare) {
        this.fare = fare;
    }

    public String getCabin() {
        return cabin;
    }

    public void setCabin(String cabin) {
        this.cabin = cabin;
    }

    public String getEmbarked() {
        return embarked;
    }

    public void setEmbarked(String embarked) {
        this.embarked = embarked;
    }


    @Override // מתודת מיון
    public int compareTo(Passenger o) {
        return this.getName().compareTo(o.getName());
    }
}
