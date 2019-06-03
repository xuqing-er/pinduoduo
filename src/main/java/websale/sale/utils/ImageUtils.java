package websale.sale.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;

public class ImageUtils {
    public static String saveFile(MultipartFile filedata, HttpServletRequest request) {
        // 根据配置文件获取服务器图片存放路径
        String newFileName = String.valueOf( System.currentTimeMillis());
        /* 构建文件目录 */
        String classPath = System.getProperty("user.dir");
        String saveFilePath = "/src/main/resources/static/img/";

        String filename=filedata.getOriginalFilename();
        String extensionName = filename.substring(filename.lastIndexOf(".") + 1);
        String fileFullName=newFileName+"."+extensionName;

        try {
            String imgPath = classPath + saveFilePath + fileFullName;
            FileOutputStream out = new FileOutputStream(imgPath);
            out.write(filedata.getBytes());
            out.flush();
            out.close();
            return "/img/"+fileFullName;
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
