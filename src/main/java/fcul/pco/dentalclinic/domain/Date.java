package fcul.pco.dentalclinic.domain;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

/**
* The Date class represents a date composed of year, month, day in month hour
* and minute.
* @author fc42008
* @author fc42208
*/
public class Date implements Comparable<Date>{
	
	// ATRIBUTES
	private int day;
	private int month;
	private int year;
	private int hour;
	private int min;
	
	// START DATE
	private static final Date STARTDATE = new Date(2000, 1, 1, 0 ,0);
	// NUMBER OF DAYS SINCE START DATE
	private static final int STARTDATEINT = STARTDATE.intValue();
	
	// HOLIDAY DATES IN PORTUGAL
	private static final Date [] HOLIDAYS = {
		new Date(1,1),
		new Date(4,25),
		new Date(5,1),
		new Date(6,10),
		new Date(8,15),
		new Date(10,5),
		new Date(11,1),
		new Date(12,1),
		new Date(12,8),
		new Date(12,25)
	};
	
	/**
	 * Tells if year is a leap year.
	 * @param year
	 * @return True if year is leap year. False if year is not a leap year.
	 */
	private static boolean isLeapYear(int year){
		if(year % 4 == 0 && year % 100 == 0) return true;
		return false;
	}
	
	/**
	 * Number of days in a month.
	 * @param month
	 * @param year
	 * @requires month <= 12 && 1 <= month
	 */
	private static int daysInMonth(int month, int year){
		
		switch(month) {
		case 1: return 31;
		case 2: if(isLeapYear(year)) return 29;
				else return 28;
		case 3: return 31;
		case 4:	return 30;
		case 5: return 31;
		case 6: return 30;
		case 7: return 31;
		case 8: return 31;
		case 9: return 30;
		case 10: return 31;
		case 11: return 30;
		case 12: return 31;
		default: return 0;
		}
	}
	
	/**
     * Returns the name of a date d 's week day
	 */
	public static String nameOfDay(Date d){	
		switch(d.dayOfWeek()) {
		case 0: return "Segundafeira :";
		case 1: return "Tercafeira :";
		case 2: return "Quartafeira :";
		case 3: return "Quintafeira :";
		case 4: return "Sextafeira :";
		case 5: return "Sábado :";
		case 6: return "Domingo :";
		default: return null;
		}
	}
	
	/**
	 * Determines whether a doctor can work during this date
	 */
	private static boolean isWorkDate(Date d){
		if(d.isHoliday() || d.dayOfWeek() == 5 || d.dayOfWeek() == 6 
				|| d.hour < 9 || d.hour >= 18 || (d.hour>=12 && d.hour<14))
			return false;
		return true;
	}
	
	
	
	
	/**
	 * Number of days a year has.
	 * @param year
	 */
	private static int daysInYear(int year){
		if(isLeapYear(year)) return 366;
		return 365;
	}

	/**
	 * Returns the present date 
	 */
	public static Date getCurrentDate() {
		LocalDateTime now =LocalDateTime.now();
		return new Date(now.getYear(), now.getMonthValue(), now.getDayOfMonth(), 
				now.getHour(), now.getMinute());
	}
	
	/**
	 * @return a date with the day after getCurrentDate() at 09:00
	 * @ensures the date is in the toString() format
	 */
	public static Date getTomorrowMorning() {		
		return getMorningAfterDay(getCurrentDate());
	}

	/**
	 * @return a date containing the day after d1 at 9 hours and 0 minutes
	 */
	public static Date getMorningAfterDay(Date d1) {
		int y,m,d;
		
		if(d1.day == daysInMonth(d1.month, d1.year)){
			if(d1.month == 12)	{	
				y=d1.year+1;
				m=1;
			}
			else {
				y=d1.year;
				m=d1.month+1;
			}
			d=1;
		}
		else {
			y=d1.year;
			m=d1.month;
			d=d1.day+1;
		}		
		return new Date(y, m, d, 9, 0);
	}
	
	
	
	/**
	 * Compares two dates
	 */
	public static int compareDates(Date d1, Date d2) {
		return d1.compareTo(d2);
	}
	
	/**
	 * Creates a date from a string 
	 * @return toString() format
	 * @param s
	 */
	public static Date fromString(String s) {
		String[] d = new String[5];
		d=s.split("/");
		return new Date(Integer.parseInt(d[0]),
						Integer.parseInt(d[1]),
						Integer.parseInt(d[2]),
						Integer.parseInt(d[3]),
						Integer.parseInt(d[4]));
	}
	
	
	/**
	 * Prints a list of Date objects in the toString() format with a
	 * frame around it through use of Utils.tableToString()
	 */
	public static String dateListToString(List<Date> dateList) {
		List<List<String>> table = new ArrayList<List<String>>();
		int i=1;
		for (Date d : dateList) {
			ArrayList<String> row = new ArrayList<String>();
			row.add(String.valueOf(i));
			row.add(String.valueOf(d.toString()));
			table.add(row);
			i++;
		}
		return Utils.tableToString(table);
	}
	
	
	/**
	* Creates a Date instance. Arguments must specify a valid date.
	* @param y. The year
	* @param mo. The month (January is 1, February is 2 etc..)
	* @param d. The day in the month
	* @param h. The hour in 0..23 format
	* @param mi. The minutes
	* @ensures getYear() == y && getMonth() == mo && getDay() == d && getHour() == h && getMinute() && getMin() == mi
	*/
	public Date (int y,int mo, int d, int h, int mi){
		this.day=d;
		this.month=mo;
		this.year=y;
		this.hour=h;
		this.min=mi;
	}
	
	/**
	* Make a new date. The hour and minute are set to zero.
	* @param y
	* @param mo
	* @param d
	* @ensures getYear() == y && getMonth() == mo && getDay() == d && getHour() == 0 && getMinute() == 0
	*/
	private Date(int mo, int d){
		this(0,mo,d,0,0);
	}
	
	/**
	 * Compares another Date day to this Date day.
	 * @param other
	 * @return true if month and day are the same for Date and the other. false if else.
	 */
	private boolean sameDay(Date other){
		if(this.month == other.month && this.day == other.day) return true;
		return false;
	}
	
	
	/**
	 * Tells if Date is an Holiday.
	 * @return true if Date is holiday. Else is false.
	 */
	public boolean isHoliday(){
		for(int i=0; i<HOLIDAYS.length; i++){
			if(this.sameDay(HOLIDAYS[i])) return true;
		}
		return false;
	}
	
	/**
	 * Number of minutes passed in Date since StartDate.
	 */
	private int intValue() {
		int value = min;
		value += hour * 60;
		value += (day - 1) * 1440;
		for (int m = 1; m < month; m++) {
			value += daysInMonth(m, year) * 1440;
		}
		for (int y = STARTDATE.year; y < year; y++) {
			value += daysInYear(y) * 1440;
		}
		return value - STARTDATEINT;
	}
	
	
	/**
	 * Number of days passed in Date since StarDate.
	 */
	private int daysSinceStart(){
		return this.intValue()/(24*60);
	}
	
	/**
	 * Number of the day of the week in Date. Monday is 0, Tuesday is 1 ...
	 */
	public int dayOfWeek(){
		return (this.daysSinceStart()+2) % 7;
	}
	
	/**
	 * Sets the date's year.
	 * @param y. A value for the year.
	 */
	public void setYear(int y){
		this.year=y;
	}
	
	/**
	 * Sets the date's month.
	 * @param mo. A value for the month.
	 */
	public void setMonth (int mo){
		this.month=mo;
	}
	
	/**
	 * Sets the date's day.
	 * @param d. A value for the day.
	 */
	public void setDay (int d){
		this.day=d;
	}
	
	/**
	 * Sets the date's hour.
	 * @param h. A value for the hour.
	 */
	public void setHour(int h){
		this.hour=h;
	}
	
	/**
	 * Sets the date's minute.
	 * @param mi. A value for the minutes.
	 */
	public void setMin(int mi){
		this.min=mi;
	}
	
	/**
	 * Gets the Date's year.
	 */
	public int getYear(){
		return this.year;
	}
	
	/**
	 * Gets the Date's month.
	 */
	public int getMonth(){
		return this.month;
	}
	
	/**
	 * Gets the Date's day.
	 */
	public int getDay (){
		return this.day;
	}

	
	/**
	 * Gets the Date's hour.
	 */
	public int getHour(){
		return this.hour;
	}
	
	/**
	 * Gets the Date's minutes.
	 */
	public int getMin(){
		return this.min;
	}
	
	/**
	 * Writes the date to a string in the y/m/d/h/min format
	 */
	public String toString() {
		return year+"/"+month+"/"+day+"/"+hour+"/"+min;
	}

	/**
	 * compares this date to another one, if later return 1, if same return 0, if earlier return -1
	 */
	public int compareTo(Date d2) {
		if (this.daysSinceStart()>d2.daysSinceStart())	return 1;
		if (this.daysSinceStart()==d2.daysSinceStart()) return 0;
		return -1;
	}
	

	
	/**
	 * 
	 * @return a list of 10 dates starting from this date and incrementing the minutes by
	 * the value of every. This method skips lunch hours (12-14) as well as holidays, weekends
	 * and non-work hours (<9 || >18)
	 */
	public List<Date> makeSmartDateList(int every, List<Date> exclude){
		List<Date> l = new ArrayList<Date>();
		
		int h=this.hour;
		int m=this.min;
		Date d = this;
		
		while (l.size() < 10) {			
			//the candidate for the smart list
			d = new Date(d.year, d.month, d.day, h, m);
			
			//tests to see if date belongs to exclude
			boolean isNotIncluded=true;
			for (Date dList : exclude)
				if(dList.compareTo(d) == 0)
					isNotIncluded=false;
			
			//add date to the smart list if the date can be worked on and if it is not a member 
			//of exclude
			if(isWorkDate(d) && isNotIncluded)
				l.add(d);
			
			m=m  + every;
			//incrementing the hour
			if(m>=60)
			{
				m=m%60;
				h++;
			}
			//incrementing the day
			if(h>=18)
			{
				d= getMorningAfterDay(d);
				h=d.hour;
				m=d.min;
			}
		}
		
		return l;
	}
}
