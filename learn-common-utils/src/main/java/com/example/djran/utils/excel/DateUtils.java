package com.example.djran.utils.excel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created on 2018/3/2
 * 日期,时间工具类
 * @author d.djran@gmail.com
 */
@Slf4j
public class DateUtils {
    /**
     * 日期
     */
    public final static String DEFAILT_DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 日期时间
     */
    public final static String DEFAILT_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /**
     * 时间
     */
    public final static String DEFAULT_TIME_PATTERN = "HH:mm:ss";
    /**
     * 每天的毫秒数
     */
    public final static long MILLIS_IN_DAY = 1000 * 60 * 60 * 24;

    public static final String DEFAULT_YEAR_FORMAT = "yyyy";
    public static final String DEFAULT_MONTH_FORMAT = "MM";
    public static final String DEFAULT_HOUR_FORMAT = "HH";
    public static final long MILLISECONDS_A_DAY = 24 * 3600 * 1000;
    public static final long MILLISECONDS_A_HOUR = 3600 * 1000;
    public static final long MILLISECONDS_A_SECOND = 1000;
    private static final String DEFAULT_DATEFULLTIME_FORMAT = "yyyyMMddHHmmss";
    /**
     * Get the previous time, from how many days to now.
     *
     * @param days How many days.
     * @return The new previous time.
     */
    public static Date previous(int days) {
        return new Date(System.currentTimeMillis() - days * 3600000L * 24L);
    }
    /**
     * 将日期转换为具体格式
     * @param formatString
     * @param date
     * @return
     * @throws ParseException
     */
    public static String formatDate2Str(String formatString, Date date) throws ParseException {
        if (date == null) {
            date = new Date();
        }
        if (StringUtils.isEmpty(formatString)){
            formatString = DateUtils.DEFAILT_DATE_PATTERN;
        }
        return DateUtils.date2String(formatString, date);
    }
    /**
     * 转换日期字符串得到指定格式的日期类型
     *
     * @param formatString 需要转换的格式字符串
     * @param targetDate   需要转换的时间
     * @return
     * @throws ParseException
     */
    public static final Date string2Date(String formatString, String targetDate) throws ParseException {
        if (StringUtils.isEmpty(targetDate)){
            return null;
        }
        SimpleDateFormat format = null;
        Date result = null;
        format = new SimpleDateFormat(formatString);
        try {
            result = format.parse(targetDate);
        } catch (ParseException pe) {
            log.error("\n-----DateUtils-string2Date Exception--"+pe.getMessage()+"\n");
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }
        return result;
    }
    /**
     * 转换日期得到指定格式的日期字符串
     *
     * @param formatString 需要把目标日期格式化什么样子的格式。例如,yyyy-MM-dd HH:mm:ss
     * @param targetDate   目标日期
     * @return
     */
    public static String date2String(String formatString, Date targetDate) {
        SimpleDateFormat format = null;
        String result = null;
        if (targetDate != null) {
            format = new SimpleDateFormat(formatString);
            result = format.format(targetDate);
        } else {
            return null;
        }
        return result;
    }
    /**
     * 比较日期大小
     *
     * @param date1
     * @param date2
     * @return int; 1:DATE1>DATE2;
     */
    public static int compare_date(String date1, String date2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            log.error("\n-----DateUtils-compare_date Exception--"+e.getMessage()+"\n");
            e.printStackTrace();
        }
        return 0;
    }
    /**
     * 日期比较
     * <p>
     * 判断当前时间是否在时间date2之前 <br/>
     * 时间格式 2005-4-21 16:16:34 <br/>
     * 添加人：胡建国
     *
     * @return
     */
    public static boolean isDateBefore(String date2) {
        if (date2 == null) {
            return false;
        }
        try {
            Date date1 = new Date();
            DateFormat df = DateFormat.getDateTimeInstance();
            return date1.before(df.parse(date2));
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * 判断参数时间是否为当天
     * @param date
     * @param formatStr
     * @return
     */
    public static boolean equalDate(String date,String formatStr) {
        try {
            String nowDate = date2String(formatStr, new Date());
            Date d1 = string2Date(formatStr, nowDate);
            Date d2 = string2Date(formatStr, date);
            return d1.equals(d2);
        } catch (ParseException e) {
            log.error("\n-----DateUtils-compare_date Exception--"+e.getLocalizedMessage()+"\n");
            return false;
        }
    }
    /**
     * 获取上个月开始时间
     *
     * @param currentDate 当前时间
     * @return 上个月的第一天
     */
    public static Date getLastMonthFirstDayDate(Calendar currentDate) {
        Calendar result = Calendar.getInstance();
        result.set(currentDate.get(Calendar.YEAR), (currentDate
                .get(Calendar.MONTH)) - 1, result
                .getActualMinimum(Calendar.DATE), 0, 0, 0);
        return result.getTime();
    }
    /**
     * 获取上个月结束时间
     *
     * @param currentDate 当前时间
     * @return 上个月最后一天
     */
    public static Date getLastMonthLastDayDate(Calendar currentDate) {
        Calendar result = currentDate;
        // result.set(currentDate.get(Calendar.YEAR), currentDate
        // .get(Calendar.MONTH) - 1);
        result.set(Calendar.DATE, 1);
        result.add(Calendar.DATE, -1);
        return result.getTime();
    }

    /**
     * 获取指定月份的第一天
     *
     * @param currentDate
     * @return
     */
    public static Date getSpecifiedMonthFirstDayDate(Calendar currentDate) {
        Calendar result = Calendar.getInstance();
        result.set(currentDate.get(Calendar.YEAR), (currentDate
                .get(Calendar.MONTH)), result.getActualMinimum(Calendar.DATE));
        return result.getTime();
    }
    /**
     * 获取两个时间的时间间隔（天）
     *
     * @param beginDate 开始时间
     * @param endDate   结束时间
     * @return
     */
    public static int getDaysBetween(Calendar beginDate, Calendar endDate) {
        if (beginDate.after(endDate)) {
            Calendar swap = beginDate;
            beginDate = endDate;
            endDate = swap;
        }
        int days = endDate.get(Calendar.DAY_OF_YEAR)
                - beginDate.get(Calendar.DAY_OF_YEAR) + 1;
        int year = endDate.get(Calendar.YEAR);
        if (beginDate.get(Calendar.YEAR) != year) {
            beginDate = (Calendar) beginDate.clone();
            do {
                days += beginDate.getActualMaximum(Calendar.DAY_OF_YEAR);
                beginDate.add(Calendar.YEAR, 1);
            } while (beginDate.get(Calendar.YEAR) != year);
        }
        return days;
    }
    /**
     * 获取两个时间的时间间隔(月份)
     *
     * @param beginDate 开始时间
     * @param endDate   结束时间
     * @return
     */
    public static int getMonthsBetween(Date beginDate, Date endDate) {
        if (beginDate.after(endDate)) {
            Date swap = beginDate;
            beginDate = endDate;
            endDate = swap;
        }
        int months = endDate.getMonth() - beginDate.getMonth();
        int years = endDate.getYear() - beginDate.getYear();

        months += years * 12;

        return months;
    }
    /**
     * 获取两个时间内的工作日
     *
     * @param beginDate 开始时间
     * @param endDate   结束时间
     * @return
     */
    public static int getWorkingDay(Calendar beginDate, Calendar endDate) {
        int result = -1;
        if (beginDate.after(endDate)) {
            Calendar swap = beginDate;
            beginDate = endDate;
            endDate = swap;
        }
        int charge_start_date = 0;
        int charge_end_date = 0;
        int stmp;
        int etmp;
        stmp = 7 - beginDate.get(Calendar.DAY_OF_WEEK);
        etmp = 7 - endDate.get(Calendar.DAY_OF_WEEK);
        if (stmp != 0 && stmp != 6) {
            charge_start_date = stmp - 1;
        }
        if (etmp != 0 && etmp != 6) {
            charge_end_date = etmp - 1;
        }
        result = (getDaysBetween(getNextMonday(beginDate),
                getNextMonday(endDate)) / 7)
                * 5 + charge_start_date - charge_end_date;
        return result;
    }
    /**
     * 获得日期的下一个星期一的日期
     *
     * @param date 任意时间
     * @return
     */
    public static Calendar getNextMonday(Calendar date) {
        Calendar result = null;
        result = date;
        do {
            result = (Calendar) result.clone();
            result.add(Calendar.DATE, 1);
        } while (result.get(Calendar.DAY_OF_WEEK) != 2);
        return result;
    }
    /**
     * 根据当前给定的日期获取当前天是星期几(中国版的)
     *
     * @param date 任意时间
     * @return
     */
    public static String getChineseWeek(Calendar date) {
        final String dayNames[] = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五",
                "星期六"};
        int dayOfWeek = date.get(Calendar.DAY_OF_WEEK);
        return dayNames[dayOfWeek - 1];

    }
    /**
     * 获取两个日期之间的休息时间
     *
     * @param beginDate 开始时间
     * @param endDate   结束时间
     * @return
     */
    public static int getHolidays(Calendar beginDate, Calendar endDate) {
        return getDaysBetween(beginDate, endDate)
                - getWorkingDay(beginDate, endDate);

    }
    /**
     * 获取当前月分的最大天数
     *
     * @param currentDate 当前时间
     * @return
     */
    public static Date getMaxDate(Calendar currentDate) {
        Calendar result = Calendar.getInstance();
        result.set(currentDate.get(Calendar.YEAR), currentDate
                .get(Calendar.MONTH), currentDate
                .getActualMaximum(Calendar.DATE));
        return result.getTime();
    }
    /**
     * 加减天数  把日期往后增加 num 天.整数往后推,负数往前移动
     *
     * @param num
     * @param Date
     * @return
     */
    public static Date addDay(int num, Date Date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Date);
        calendar.add(Calendar.DATE, num);
        // 这个时间就是日期往后推一天的结果
        return calendar.getTime();
    }
    /**
     * 计算两端时间的小时差
     *
     * @param begin
     * @param end
     * @return
     */
    public static int getHour(Date begin, Date end) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(begin);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(end);
        Long millisecond = c2.getTimeInMillis() - c1.getTimeInMillis();
        Long hour = millisecond / 1000 / 60 / 60;
        Long minute = (millisecond / 1000 / 60) % 60;
        if (minute >= 30) {
            hour++;
        }

        return hour.intValue();
    }

    /**
     * 获取某年某月的结束日期
     * @param yearNum
     * @param monthNum
     * @return
     * @throws ParseException
     */
    public static String getYearMonthEndDay(int yearNum, int monthNum)
            throws ParseException {

        // 分别取得当前日期的年、月、日
        String tempYear = Integer.toString(yearNum);
        String tempMonth = Integer.toString(monthNum);
        String tempDay = "31";
        if (tempMonth.equals("1") || tempMonth.equals("3")
                || tempMonth.equals("5") || tempMonth.equals("7")
                || tempMonth.equals("8") || tempMonth.equals("10")
                || tempMonth.equals("12")) {
            tempDay = "31";
        }
        if (tempMonth.equals("4") || tempMonth.equals("6")
                || tempMonth.equals("9") || tempMonth.equals("11")) {
            tempDay = "30";
        }
        if (tempMonth.equals("2")) {
            if (isLeapYear(yearNum)) {
                tempDay = "29";
            } else {
                tempDay = "28";
            }
        }
        String tempDate = tempYear + "-" + tempMonth + "-" + tempDay;
        // setDateFormat(tempDate,"yyyy-MM-dd");
        return tempDate;
    }

    /**
     * 判断某年是否为闰年
     * @param yearNum
     * @return
     */
    public static boolean isLeapYear(int yearNum) {
        boolean isLeep = false;
        /** 判断是否为闰年，赋值给一标识符flag */
        if ((yearNum % 4 == 0) && (yearNum % 100 != 0)) {
            isLeep = true;
        } else if (yearNum % 400 == 0) {
            isLeep = true;
        } else {
            isLeep = false;
        }
        return isLeep;
    }

    /**
     * 当前时间加上days.
     * @param date
     * @param days
     * @return
     */
    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    /**
     * 当前时间加上minutes.
     * @param date
     * @param minutes
     * @return
     */
    public static Date addMinutes(Date date, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minutes);
        return cal.getTime();
    }

    /**
     * 当前时间加上seconds.
     * @param date
     * @param seconds
     * @return
     */
    public static Date addSeconds(Date date, int seconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, seconds);
        return cal.getTime();
    }

    /**
     * 当前时间加上months.
     */
    public static Date addMonths(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }
    /**
     * 当前时间加上years.
     *
     * @param date
     * @param years
     * @return
     */
    public static Date addYears(Date date, int years) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, years);
        return cal.getTime();
    }
    /**
     * 获取当前年份.
     *
     * @return
     */
    public static int getYear() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR);
    }

    /**
     * 获取当前月.
     *
     * @return
     */
    public static int getMonth() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.MONTH) + 1;
    }
    /**
     * 获取指定月的最小时间.
     *
     * @param date
     * @return
     */
    public static Date getMinDateByMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
        return cal.getTime();
    }

    /**
     * 获取指定月的最大时间.
     *
     * @param date
     * @return
     */
    public static Date getMaxDateByMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        return cal.getTime();
    }



}
