package by.gsu.epamlab.tags;
import java.sql.Date;

import javax.servlet.jsp.tagext.TagSupport;

import by.gsu.epamlab.utils.Constants;

public class DateTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	private String when;
	String [] days = {"sunday","monday","tuesday","wednesday","thursday","friday","saturday"};
	String [] months = {"January","February","March","April","May","June","July","August","September","October","November","December"};
	
	public int doStartTag(){
		try {
			 this.pageContext.getOut().print(customDate());
		} catch (Exception e) {
		}
		return SKIP_BODY;
	}
	
    public int doEndTag(){
		return EVAL_PAGE;
	}
    
    private String customDate(){
    	long curTime;
    	if(when.equals(Constants.TODAY)){
    	curTime = System.currentTimeMillis();	
    	}else{
    	curTime = System.currentTimeMillis() + 24*60*60*1000;
    	}            
		Date curDate = new Date(curTime);
		String month = months[curDate.getMonth()];
		String dayStr = days[curDate.getDay()];
		int dayNum = curDate.getDate();
		return dayNum+Constants.BLANK+month+Constants.BLANK+dayStr;
    }

	public String getWhen() {
		return when;
	}

	public void setWhen(String when) {
		this.when = when;
	}
    

}
