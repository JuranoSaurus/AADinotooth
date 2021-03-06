package gif.dino.dinotooth;

import java.util.Calendar;

/**
 * Created by jool on 15. 10. 2..
 */
public class DateTimeHelper {

    //-----------싱글톤 객체 생성을 위한 준비 시작---------
    private static DateTimeHelper current;

    public static DateTimeHelper getInstance() {
        if (current == null) {
            current = new DateTimeHelper();
        }
        return current;
    }
    public static void freeInstance() {
        //객체 에 null을 대입하면 메모리에서 삭제된다.
        current = null;
    }

    //기본 생성자를 private 로 은닉하게 되면 new 를 통한 객체 생성이 금지된다.
    private DateTimeHelper() {
        super();
    }
    //-------------싱글톤 객체 생성을 위한 준비 끝------------

    /**현재 날짜를 배열로 리턴한다.*/
    public int[] getDate() {
        Calendar calendar = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH) + 1;
        int dd = calendar.get(Calendar.DAY_OF_MONTH);

        int[] result = {yy, mm, dd};
        return result;
    }

}
