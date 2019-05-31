package websale.sale.utils;

import java.security.MessageDigest;

public class MD5 {
    public static String next(String key){
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','D','F'};
        try {
            byte[] byInput=key.getBytes();
            MessageDigest mdInst=MessageDigest.getInstance("MD5");
            mdInst.update(byInput);
            byte[] md=mdInst.digest();
            int j=md.length;
            char str[]=new char[j*2];
            int k=0;
            for (int i = 0; i <j ; i++) {
                byte bytei=md[i];
                str[k++]=hexDigits[bytei>>>4 &0xf];
                str[k++]=hexDigits[bytei& 0xf];
            }
            return new String(str);
        }catch (Exception e){
            return null;
        }
    }
}
