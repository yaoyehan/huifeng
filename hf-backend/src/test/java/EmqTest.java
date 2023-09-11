import com.huifeng.hfApplication;
import com.huifeng.emq.EmqClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : yyh
 * @create : 2022-10-08 - 0:11
 * @describe:
 */
@SpringBootTest(classes = hfApplication.class)
@RunWith(SpringRunner.class)
public class EmqTest {

    @Autowired
    private EmqClient emqClient;

    @Test
    public void testSend(){
        emqClient.connect();
        emqClient.publish("test_topic","test_content");
    }
}
