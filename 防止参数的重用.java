// 如果一个类是不变的，那么它往往就是可重用的
//某个可变类的实例在运行时并不会变化，那么这个实例也可以被重用

/**
 * 方法一被调用则初始化calender, Timezone, date.
 * 耗时32000ms
 */
public class Person{
    private final Date birthDay;
    //Other fields, methods and constructors omitted
    //DON'T DO THIS
    public boolean isBabyBoomer(){
      //Umeccessary alloction of expensive object
      Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
      gmtCal.set(1946,Calendar.JANUARY,1,0,0,0);
      Date boomStart = gmtCal.getTime();
      gmtCal.set(1965,Calendar.JANUARY,1,0,0,0);
      Date boomEnd = gmtCal.getTime();
      return birthDay.compareTo(boomStart) >= 0 && birthDate.compareTo(boomEnd) < 0;
    }
  }
  /**
   * 只初始化一次
   * 耗时130ms
   */
  public class Person {
    private final Date birthDate;
    // Other fields, methods, and constructor omitted
    /**
     * The starting and ending dates of the baby boom.
     */
    private static final Date BOOM_START;
    private static final Date BOOM_END;
    static {
        Calendar gmtCal =
            Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
        BOOM_START = gmtCal.getTime();
        gmtCal.set(1965, Calendar.JANUARY, 1, 0, 0, 0);
        BOOM_END = gmtCal.getTime();
    }
    public boolean isBabyBoomer() {
        return birthDate.compareTo(BOOM_START) >= 0 &&
               birthDate.compareTo(BOOM_END)   <  0;
    } 
}