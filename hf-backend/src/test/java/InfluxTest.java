import com.fasterxml.jackson.core.JsonProcessingException;
import com.huifeng.hfApplication;
import com.huifeng.dto.QuotaInfo;
import com.huifeng.dto.TrendPoint;
import com.huifeng.dto.TrendPoint2;
import com.huifeng.influx.InfluxRepository;
import com.huifeng.service.QuotaService;
import com.huifeng.service.ReportService;
import com.huifeng.util.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author : yyh
 * @create : 2022-11-29 - 0:54
 * @describe:
 */
@SpringBootTest(classes = hfApplication.class)
@RunWith(SpringRunner.class)
public class InfluxTest {

    @Autowired
    private InfluxRepository influxDBRepository;
    @Autowired
    private QuotaService quotaService;
    @Autowired
    private ReportService reportService;

    @Test
    public void testAdd(){
        QuotaInfo quotaInfo=new QuotaInfo();
        quotaInfo.setDeviceId("123456");
        quotaInfo.setQuotaId("1");
        quotaInfo.setQuotaName("温度");
        quotaInfo.setReferenceValue("0-10");
        quotaInfo.setUnit("摄氏度");
        quotaInfo.setAlarm("1");
        quotaInfo.setValue(11D);
        influxDBRepository.add(quotaInfo);
    }

    @Test
    public void testFindLast(){
        List<QuotaInfo> quotaList = quotaService.getLastQuotaList("100008");
        try {
            String json = JsonUtil.serialize(quotaList);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAlarmTrend(){

        List<TrendPoint> trendPointList = reportService.getAlarmTrend("2022-11-01", "2022-11-30", 3);

        for(TrendPoint trendPoint:trendPointList){
            try {
                System.out.println(JsonUtil.serialize(trendPoint));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void testQuotaTrend(){
        List<TrendPoint2> trendPoint2List = reportService.getQuotaTrend("2022-11-01 00:00:00", "2022-11-30 23:59:59"
                , "1", "100001", 3);
        for( TrendPoint2 trendPoint2:trendPoint2List ){
            try {
                System.out.println( JsonUtil.serialize(trendPoint2) );
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }
}
