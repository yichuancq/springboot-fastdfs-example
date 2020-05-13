import com.example.fastdfs.MyApplication;
import com.example.fastdfs.client.FastDFSClientUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MyApplication.class)
public class TestFastDFS {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private FastDFSClientUtil dfsClient;

    @Test
    public void deleteFile() {
        try {
            final String filePath = "http://192.168.0.100/group1/M00/00/00/wKgAZF67vTyASu1NADwVpXqxSLQ621.JPG";
            dfsClient.delFile(filePath);
            logger.info("删除文件{} 成功！", filePath);
        } catch (Exception e) {
            // 文件不存在报异常 ： com.github.tobato.fastdfs.exception.FdfsServerException: 错误码：2，错误信息：找不到节点或文件
            e.printStackTrace();
        }

    }
}
