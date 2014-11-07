package sample.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.testng.Assert.*;
import org.testng.annotations.*;

public class TestNGParameterTest {
	private SimpleDateFormat simpleDateFormat;

    @DataProvider(name = "testParam")
    public static String[][]  getParamters() {
        String[][] params = {
                { "2011-07-01 00:30:59", "yyyyMMdd", "20110701" },
                { "2011-07-01 00:30:59", "yyyy年MM月dd日", "2011年07月01日" },
                { "2011-07-01 00:30:59", "HH时mm分ss秒", "00时30分59秒" } };
		 return params;
	}

    @Test(dataProvider = "testParam")
    public void testSimpleDateFormat(String date,String dateformat,String expectedDate) throws ParseException{
    	SimpleDateFormat df  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date d = df.parse(date);
    	simpleDateFormat = new SimpleDateFormat(dateformat);
    	String result = simpleDateFormat.format(d);
        assertEquals(expectedDate, result);
    }

}
