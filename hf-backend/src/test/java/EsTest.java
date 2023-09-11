import com.fasterxml.jackson.core.JsonProcessingException;
import com.huifeng.hfApplication;
import com.huifeng.dto.DeviceDTO;
import com.huifeng.dto.DeviceLocation;
import com.huifeng.es.ESRepository;
import com.huifeng.util.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author : yyh
 * @create : 2022-10-07 - 22:41
 * @describe:
 */
@SpringBootTest(classes = hfApplication.class)
@RunWith(SpringRunner.class)
public class EsTest {

    @Autowired
    private ESRepository esRepository;

    @Test
    public void testAdd(){
        DeviceDTO deviceDTO=new DeviceDTO();
        deviceDTO.setDeviceId("123456");
        deviceDTO.setStatus(true);
        deviceDTO.setAlarm(false);
        deviceDTO.setLevel(0);
        deviceDTO.setAlarmName("");
        deviceDTO.setOnline(true);
        deviceDTO.setTag("");
        esRepository.addDevices(deviceDTO);
    }

    @Test
    public void testSearchById(){

        DeviceDTO deviceDTO = esRepository.searchDeviceById("123456");
        try {
            String json = JsonUtil.serialize(deviceDTO);
            System.out.println(json);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testAlarm(){
        DeviceDTO deviceDTO=new DeviceDTO();
        deviceDTO.setDeviceId("123456");
        deviceDTO.setAlarm(true);
        deviceDTO.setLevel(1);
        deviceDTO.setAlarmName("温度过高");

        esRepository.updateDevicesAlarm(deviceDTO);

    }

    @Test
    public void testOnline(){
        esRepository.updateOnline("123456",false);
    }

    @Test
    public void testCount(){

        Long allDeviceCount = esRepository.getAllDeviceCount();//设备总数
        System.out.println("设备总数："+allDeviceCount);

        Long offlineCount = esRepository.getOfflineCount();//离线设备数量
        System.out.println("离线设备："+offlineCount);

        Long alarmCount = esRepository.getAlarmCount();//告警设备数量
        System.out.println("告警设备："+alarmCount);

    }

    /**
     * 测试es地理位置搜索方法  搜索指定经纬度一定范围内的坐标
     */
    @Test
    public void testGEO(){
        List<DeviceLocation> deviceLocations = esRepository.searchDeviceLocation(40.722, -73.989, 10);
        try {
            System.out.println(JsonUtil.serialize(deviceLocations));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
