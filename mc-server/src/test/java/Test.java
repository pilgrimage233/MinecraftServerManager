import com.github.t9t.minecraftrconclient.RconClient;
import com.ruoyi.server.common.DomainToIp;

/**
 * ClassName: Test <br>
 * Description:
 * date: 2024/1/2 01:07 <br>
 *
 * @author ~~~ <br>
 * @since JDK 1.8
 */

public class Test {
    public static void main(String[] args) {
        RconClient open = RconClient.open(DomainToIp.domainToIp("host.mcpeach.cc"), 25575, "20021129");
        String list = open.sendCommand("list");
        System.err.println(list);
    }
}
