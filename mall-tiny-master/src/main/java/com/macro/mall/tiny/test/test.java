package com.macro.mall.tiny.test;

import org.springframework.format.annotation.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

public class test {
    public test() {
    }

    /**
     * 格式化
     */
    public void oldFormat() {
        Date now = new Date();
        //format yyyy-MM-dd 年月日
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(now);
        System.out.printf(String.format("date format : %s"), date);

        //format HH:mm:ss 时（0-24）分秒
        SimpleDateFormat sdft = new SimpleDateFormat("HH:mm:ss");
        String time = sdft.format(now);
        System.out.printf(String.format("time format : %s"), time);

        //format yyyy-MM-dd HH:mm:ss
        SimpleDateFormat sdfdt = new SimpleDateFormat("HH:mm:ss");
        String datetime = sdfdt.format(now);
        System.out.printf(String.format("time format : %s"), sdfdt);
    }

    public void newFormat() {
        //format yyyy-MM-dd
        LocalDate date = LocalDate.now();
        System.out.println(String.format("date format : %s", date));

        //format HH:mm:ss
        LocalTime time = LocalTime.now().withNano(0);
        System.out.println(String.format("time format : %s", time));

        //format yyyy-MM-dd HH:mm:ss
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTimeStr = dateTime.format(dateTimeFormatter);
        System.out.printf("dateTime format : %s\n", dateTimeStr);
    }

    /**
     * 字符串转日期格式
     */
    public void oldStringToDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = sdf.parse("2021-01-26");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void newStringToDate() {
        LocalDate date = LocalDate.of(2021, 1, 26);
        LocalDate.parse("2021-01-26");
        System.out.println(date);

//        LocalDateTime dateTime = LocalDateTime.of(2021, 1, 26, 12, 12, 22);
//        LocalDateTime.parse("2021-01-26 12:12:22");
//        System.out.println(dateTime);

        LocalTime time = LocalTime.of(12, 12, 22);
        LocalTime.parse("12:12:22");
        System.out.println(time);
    }

    public void oldAfterDay(){
        //一周后的日期
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, 7);
        Date d = ca.getTime();
        String after = formatDate.format(d);
        System.out.println("一周后日期" + after);

        //算两个日期间隔多少天，计算间隔多少年，多少月类似
        String dates1 = "2021-12-23";
        String dates2 = "2021-02-26";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        try {
            date1 = format.parse(dates1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date2 = null;
        try {
            date2 = format.parse(dates2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int day = (int) ((date1.getTime() - date2.getTime()) / (1000 * 3600 *24));
        System.out.println(dates1 + "和" + dates2 + "相差" + day + "天");
    }

    public void newPushWeek() {
        //一周后的日期
        LocalDate localDate = LocalDate.now();
        //方法1
        LocalDate after = localDate.plus(1, ChronoUnit.WEEKS);
        //方法2
        LocalDate after2 = localDate.plusWeeks(1);
        System.out.println("一周后日期：" + after);

        //算两个日期间隔多少天，计算间隔多少年，多少月
        LocalDate date1 = LocalDate.parse("2021-02-26");
        LocalDate date2 = LocalDate.parse("2021-12-23");
        Period period = Period.between(date1, date2);
        System.out.println("date1 到 date2间隔："
                + period.getYears() + "年"
                + period.getMonths() + "月"
                + period.getDays() + "天");
        long day = date2.toEpochDay() - date1.toEpochDay();
        System.out.println(date1 + "和" + date2 + "相差" + day + "天");
    }

    public void getDay() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //获取当前月第一天
        Calendar c = Calendar.getInstance();
        //set(field, value)
        c.set(Calendar.DAY_OF_MONTH, 1);
        String first = format.format(c.getTime());
        System.out.println("first day:" + first);

        //获取当前月最后一天
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));;
        String last = format.format(ca.getTime());
        System.out.println("last day:" + last);

        //当年最后一天
        Calendar currCal = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, currCal.get(Calendar.YEAR));;
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date time = calendar.getTime();
        System.out.println("last day:" + format.format(time));
    }

    public void getDayNew() {
        LocalDate today = LocalDate.now();
        //获取当前月第一天：
        LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        //获取本月最后一天：
        LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        //取下一天:
        LocalDate nextDay = lastDayOfThisMonth.plusDays(1);
        //当年最后一天
        LocalDate lastday = today.with(TemporalAdjusters.lastDayOfYear());
        //2021年最后一个周日，如果用Calendar会非常复杂
        LocalDate lastMondayOf2021 = LocalDate.parse("2021-12-31").with(TemporalAdjusters.lastInMonth(DayOfWeek.MONDAY));
    }

    public static void main(String[] args) {
        test test = new test();
        test.newFormat();
//        test.oldFormat();
        test.newStringToDate();
//        Date date = new Date();
//        System.out.println("Date实例：" + date);
//        System.out.println("date.getTIme()：" + date.getTime());
//        System.out.println("LocalDateTime.now()：" + LocalDateTime.now());
//        System.out.println("LocalDate.now()：" + LocalDate.now());
//        System.out.println("LocalTime.now()：" + LocalTime.now().withNano(0));
        test.getDay();
    }
}
