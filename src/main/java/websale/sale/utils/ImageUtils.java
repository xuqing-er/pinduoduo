package websale.sale.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;

public class ImageUtils {
    public static String saveFile(MultipartFile filedata, HttpServletRequest request) {
        String pathval = request.getSession().getServletContext().getRealPath("/")+"WEB-INF/";
        // 根据配置文件获取服务器图片存放路径
        String newFileName = String.valueOf( System.currentTimeMillis());
        String classPath = System.getProperty("user.dir");
        String saveFilePath = "img";
        /* 构建文件目录 */
        File fileDir = new File(classPath + "/src/main/resources/static/" + saveFilePath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        String filename=filedata.getOriginalFilename();
        System.out.println(filename);
        String extensionName = filename.substring(filename.lastIndexOf(".") + 1);
        try {
            String imgPath = classPath + "/src/main/resources/static/" + saveFilePath + "/img5.jpg" ;
            FileOutputStream out = new FileOutputStream(imgPath);
            out.write(filedata.getBytes());
            out.flush();
            out.close();
            return imgPath;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private void deleteFile(String oldPic) {
      /* 构建文件目录 */
        File fileDir = new File(oldPic);
        if (fileDir.exists()) {
            fileDir.delete();
        }

    }
}
