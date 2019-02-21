import java.util.Calendar;

public class test01 {
    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR);
        System.out.println("时间为："+hour);
    }
}
