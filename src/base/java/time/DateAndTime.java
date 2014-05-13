package base.java.time;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.IsoFields;

/**
 * @version 
 * @author xiehai
 * @date 2014年3月25日 下午4:55:54 
 */
public class DateAndTime {
	public static void main(String[] args) {
//		dateAndTime();
		calculate();
//		timeAndDateSegment();
//		timeZone();
	}
	
	/**
	 * 日期时间创建解析格式化
	 */
	public static void dateAndTime(){
		//获得当前日期
		LocalDate ld = LocalDate.now();
//		LocalDate lld = LocalDate.of(2016, 2, 31);//会报错 2月没有31号
		//创建一个日期
		LocalDate lld = LocalDate.of(2015, 2, 22);
		System.out.println(ld);
		System.out.println(lld);
		//日期格式化
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY/MM/dd");
		System.out.println(ld.format(dtf));
		
		//获得当前时间
		LocalTime lt = LocalTime.now();
//		LocalTime llt = LocalTime.of(22, 61, 20);//会报错 分钟在0-59范围
		//创建一个时间
		LocalTime llt = LocalTime.of(22, 30, 11);
		System.out.println(lt);
		System.out.println(llt);
		//格式化时间
		dtf = DateTimeFormatter.ofPattern("HH/mm/ss");
		System.out.println(lt.format(dtf));
		
		//获得当前日期时间
		LocalDateTime ldt = LocalDateTime.now();
		//创建一个日期时间
		LocalDateTime lldt = LocalDateTime.of(2014, 3, 25, 17, 03, 33);
		System.out.println(ldt);
		System.out.println(lldt);
		//日期时间格式化
		dtf = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
		System.out.println(ldt.format(dtf));
		
		//日期时间转换为日期和时间
		lt = ldt.toLocalTime();
		ld = ldt.toLocalDate();
		System.out.println(ld + " " + lt);
		
		//通过字符串解析时间和日期
		ld = LocalDate.parse("2013-05-30");
		lt = LocalTime.parse("22:15:09");
		System.out.println(ld + " " + lt);
		//通过解析一个已知的日期字符串 重新格式化
		lld = LocalDate.parse("2013-05-22 12:11:10", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		System.out.println(DateTimeFormatter.ofPattern("YYYY/MM/dd").format(lld));
		
		//getFields可以获得对应的字段
		//get()类似以前的API
		//两个Field类 ChronoField IsoFields
		System.out.println(ld.get(ChronoField.YEAR) +" "+ ld.get(IsoFields.DAY_OF_QUARTER));
	}
	
	/**
	 * 日期时间计算
	 */
	public static void calculate(){
		LocalDateTime ldt = LocalDateTime.now();
		LocalDate ld = LocalDate.now();
		LocalTime lt = ldt.toLocalTime();
		System.out.println("----------------withXXXX----------------");
		//只修改ldt的年份 其他字段不变
		//类似还有其他的方法withMonth withHour等等
		LocalDateTime ldt1 = ldt.withYear(2017);
		System.out.println(ldt + " " + ldt1);
		//从ld年初开始第111天
		LocalDate ld1 = ld.withDayOfYear(111);
		System.out.println(ld + " " + ld1);
		LocalTime lt1 = lt.withSecond(22);
		System.out.println(lt + " " + lt1);
		
		System.out.println("----------------isXXXX----------------");
		//比较时间日期
		//以时间为例 其他类似
		System.out.println(lt);
		LocalTime lt2 = lt.withSecond(1);
		System.out.println(lt2.isAfter(lt));
		System.out.println(lt2.isBefore(lt1));
		//是否支持某个字段
		System.out.println(lt2.isSupported(ChronoField.YEAR));
		System.out.println(lt2.isSupported(ChronoUnit.HOURS));
		
		System.out.println("----------------plus and minus----------------");
		//时间日期的加减
		//以日期为例 其他类似
		System.out.println(ld);
		//加1年1个月1周1天
		LocalDate ld2 = ld.plusDays(1)
				.plusMonths(1)
				.plusYears(1)
				.plusWeeks(1);
		System.out.println(ld2);
		//加3年2个月
		LocalDate ld3 = ld.plus(3, ChronoUnit.YEARS)
				.plus(2, ChronoUnit.MONTHS);
		System.out.println(ld3);
		//minus和plus类似
		//减去1年1个月1周1天
		LocalDate ld4 = ld.minusYears(1)
				.minusMonths(1)
				.minusWeeks(1)
				.minusDays(1);
		System.out.println(ld4);
		//减去1年2个月3天
		LocalDate ld5 = ld.minus(1, ChronoUnit.YEARS)
				.minus(2, ChronoUnit.MONTHS)
				.minus(3, ChronoUnit.DAYS);
		System.out.println(ld5);
		
		System.out.println("----------------toXXXX----------------");
		//时间日期到其他类型的转换
		//以时间日期为例 其他类似
		System.out.println(ldt.toLocalDate()); 
		System.out.println(ldt.toLocalTime());
		System.out.println(ld.toEpochDay());
		System.out.println(lt.toSecondOfDay());
		
		System.out.println("----------------atXXXX----------------");
		LocalDateTime ldt2 = ld.atTime(1, 2, 3);//日期加上时间
		System.out.println(ldt2);
		LocalDateTime ldt3 = lt.atDate(ld2);//时间加上日期
		System.out.println(ldt3);
		
		System.out.println("----------------compare----------------");
		//计算两个日期的间隔
		ldt = LocalDateTime.parse("2013-01-01 12:22:22", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		ldt2 = LocalDateTime.parse("2013-01-04 13:13:15", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		long minus = ldt.toEpochSecond(ZoneOffset.of("+0")) - ldt2.toEpochSecond(ZoneOffset.of("+0"));
		//可以将Duration转为天 小时
		System.out.println(Duration.of(minus, ChronoUnit.SECONDS));
	}
	
	/**
	 * 时间日期段
	 */
	public static void timeAndDateSegment(){
		//瞬间 只有秒毫秒
		Instant instant = Instant.now();
		System.out.println(instant);
		//同样Instant可以进行plus minus withXX操作 类似时间日期
		
		//持续时间 天 小时 分钟 秒 毫秒
		//实现了TemporalAmount接口 可用于plus minus
		Duration duration = Duration.of(200, ChronoUnit.MINUTES);
		System.out.println(duration);
		//持续时间为1111111111秒
		Duration duration2 = Duration.of(1111111111, ChronoUnit.SECONDS);
		System.out.println(duration2.toDays());//将持续时间转换为天
		System.out.println(duration2.toHours());//将持续时间转换为小时 秒 分钟等
		//同样Duration可以进行plus minus withXX操作 类似时间日期
		
		//时间段 年月日
		//实现了TemporalAmount接口 可用于plus minus
		Period period = Period.of(1, 1, 1);
		//可以单独用年月日周来创建时间段
		Period period2 = Period.ofWeeks(2);
		System.out.println(period2);
		System.out.println(period);
		LocalDateTime ldt = LocalDateTime.now().plus(period);//加上1年1月1日
		LocalDateTime ldt2 = LocalDateTime.now().plus(duration);//加上200分钟
		System.out.println(ldt + " " + ldt2);
	}
	
	/**
	 * 时区相关
	 */
	public static void timeZone(){
		//一个ZoneId为shanghai的时区日期时间
		ZonedDateTime zdt = ZonedDateTime.now();
		System.out.println(zdt);
		
		//创建ZoneId
		//州 国家/城市 Australia America Africa Asia Europe Pacific
		ZoneId id = ZoneId.of("Asia/Shanghai");
		id = ZoneId.of("America/Chicago");
		System.out.println(id);
		
		//获得某个城市的当前时间
		id = ZoneId.of("America/Chicago");
		zdt = ZonedDateTime.now(id);
		System.out.println(zdt);
		
		//ZoneDateTime有LocalDateTime一样的所有操作
		//此处不一一列举
		//如果你希望代表一个日期和时间不依赖特定服务器环境, 
		//你就应该使用ZonedDateTime
		
		//时间偏移量
		ZoneOffset zo = ZoneOffset.of("+1");
		System.out.println(zo.getId());
		zdt = ZonedDateTime.now(zo);
		System.out.println(zdt);
	}
}
