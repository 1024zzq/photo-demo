import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyTest {
    
    @Test
    public void test01(){
        String[] arr = new SimpleDateFormat("yyyyMMdd_HH:ss:mm").format(new Date()).split("_");
        System.out.println(arr[0]);
        System.out.println(arr[1]);
    }
}
