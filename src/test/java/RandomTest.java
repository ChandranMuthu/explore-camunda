import org.apache.commons.lang3.builder.ToStringExclude;

import java.util.Random;

public class RandomTest {


    public void test()
    {
        Random random = new Random();

        //The if condition is added to test the retry mechanism
        int number = random.nextInt(900) + 100;

        System.out.println(number);
    }
}
