import java.io.*;
import java.util.Calendar;

public class Message{
	//ini

	private String day;
	private String month;
	private String year;
	private String hour;
	private String minute;
	private String second;
	private String filename;
	private static BufferedWriter bw;

	Message(){
		//constructor

		// Get the current date.
		Calendar calendar = Calendar.getInstance();
		// Get the current day, month, year, hour, minute, second.
		day = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
		month = Integer.toString(calendar.get(Calendar.MONTH));
		year = Integer.toString(calendar.get(Calendar.YEAR));
		hour = Integer.toString(calendar.get(Calendar.HOUR));
		minute = Integer.toString(calendar.get(Calendar.MINUTE));
		second = Integer.toString(calendar.get(Calendar.SECOND));
		// Construct a unique filename with the calendar variables.
		filename = "logs/"
				 + "day" + day
				 + "month" + month
				 + "year" + year
				 + "hour" + hour
				 + "minute" + minute
				 + "second" + second
				 + ".txt";
		// Create a file if it does not already exists.
		File file = new File(filename);

		// Buffer write to a file for efficiency. 
		// True means append new strings to the file.
		try{
			bw = new BufferedWriter(new FileWriter(filename, true));
		} catch(IOException e) {
			System.err.println(e);
		}
	}

	// Chat Server log call from MultiThread.
	public void log(String temp){
		try{
			// Write to text file.
			bw.write(temp);
			// Write a new line to text file.
			bw.newLine();
			// Flush the file.
			bw.flush();
			//bw.close();
		} catch(IOException e) {
			System.err.println(e);
		}
	}
}