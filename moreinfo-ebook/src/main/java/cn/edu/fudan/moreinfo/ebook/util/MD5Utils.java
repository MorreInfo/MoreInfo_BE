package cn.edu.fudan.moreinfo.ebook.util;



import java.security.MessageDigest;

public class MD5Utils {


  public static void main(String[] args) {

    //  99B26BE5F5F7AF4A576DFB6DF0DD38FF
    System.out.println(MD5EncodeUtf8("123456"));
  }

  private static String byteArrayToHexString(byte b[]) {
    StringBuffer resultSb = new StringBuffer();
    for (int i = 0; i < b.length; i++)
      resultSb.append(byteToHexString(b[i]));

    return resultSb.toString();
  }

  private static String byteToHexString(byte b) {
    int n = b;
    if (n < 0)
      n += 256;
    int d1 = n / 16;
    int d2 = n % 16;
    return hexDigits[d1] + hexDigits[d2];
  }

  /**
   * 返回大写MD5
   *
   * @param origin
   * @param charsetname
   * @return
   */
  private static String MD5Encode(String origin, String charsetname) {
    String resultString = null;
    try {
      resultString = origin;
      MessageDigest md = MessageDigest.getInstance("MD5");
      if (charsetname == null || "".equals(charsetname))
        resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
      else
        resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
    } catch (Exception exception) {
    }
    return resultString.toUpperCase();
  }


  /**
   * @param origin
   * @return
   */
  public static String MD5EncodeUtf8(String origin) {
    origin = origin + "323@#@$1234da";
    return MD5Encode(origin.trim(), "utf-8");
  }


  private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5",
      "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};




}