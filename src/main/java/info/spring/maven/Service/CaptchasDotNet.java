package info.spring.maven.Service;

import java.util.Random;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.print.DocFlavor.STRING;
import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class CaptchasDotNet  implements ICaptchasService{
  // Required Parameters
  private String client;
  private String secret;
  // Optional parameters and defaults
  final String ALPHABET_RECOMMENDED = "abcdefghkmnopqrstuvwxyz";
  // Default-Values from image and audio implementation
  final String ALPHABET_DEFAULT = "abcdefghijklmnopqrstuvwxyz";
  final int  LETTERS_DEFAULT = 6;
  final int WIDTH_DEFAULT = 240;
  final int HEIGHT_DEFAULT = 80;
  private String alphabet = ALPHABET_RECOMMENDED;
  private int letters = LETTERS_DEFAULT;
  private int width = WIDTH_DEFAULT;
  private int height = HEIGHT_DEFAULT;
  // Session handling
  private HttpSession httpSess;
  // The current random
  private String captchaRandom = "";

  /**
   * Constructors 
   */
  // no parameter
  public CaptchasDotNet()
  {
	   this.client ="demo";
	   this.secret= "secret";
  }
  // only required parameters
  public CaptchasDotNet(HttpSession httpSess, 
                        String client, 
                        String secret) {
    this.httpSess = httpSess;
    this.client   = client;
    this.secret   = secret;
  }
   
  // additional alphabet, letters
  public CaptchasDotNet(HttpSession httpSess, 
                        String client, 
                        String secret,
                        String alphabet,
                        int letters) {
    this.httpSess = httpSess;
    this.client   = client;
    this.secret   = secret;
    this.alphabet = alphabet;
    this.letters  = letters;
    this.width    = width;
    this.height   = height;
  } 
  // additional alphabet, letters, width, height
  public CaptchasDotNet(HttpSession httpSess, 
                        String client, 
                        String secret,
                        String alphabet,
                        int letters,
                        int width,
                        int height) {
    this.httpSess = httpSess;
    this.client   = client;
    this.secret   = secret;
    this.alphabet = alphabet;
    this.letters  = letters;
    this.width    = width;
    this.height   = height;
  } 

  /**
   * The first objects are needed in the query phase 
   */

  /**
   * Generate 8 byte hexrandom and set captchasDotNetRandom
   */
  private String randomString() {
    Random r = new Random();
    captchaRandom = Integer.toHexString(r.nextInt())+Integer.toHexString(r.nextInt());
    httpSess.setAttribute("captchasDotNetRandom", captchaRandom);
    return captchaRandom;
  }

  /**
   * Generate image url with parameters
   */
  public String imageUrl() {
    captchaRandom = randomString();
   
    String url = "http://image.captchas.net/";
    // Required parameters
    url += "?client=" + client;
    url += "&random=" + captchaRandom;
    // Optional parameters 
    if (!alphabet.equals(ALPHABET_DEFAULT)) {
      url += "&alphabet=" + alphabet;
    }
    if (letters!=LETTERS_DEFAULT) {
      url += "&letters=" + letters;
    }
    if (width!=WIDTH_DEFAULT) {
      url += "&width=" + width;
    }
    if (height!=HEIGHT_DEFAULT) {
      url += "&height=" + height;
    }
    return url;
  }
 
  // the same with random
  public String imageUrl(String randomString) {
    captchaRandom = randomString;
    httpSess.setAttribute("captchasDotNetRandom", captchaRandom);
    return imageUrl();
  }

  /**
   * Generate audio url with parameters
   * same as image url without width and height
   */
  public String audioUrl() {
    if (captchaRandom=="" || captchaRandom=="used") {
      captchaRandom = randomString();
    }
    String url = "http://audio.captchas.net/";
    url += "?client=" + client;
    url += "&random=" + captchaRandom;
    if (!alphabet.equals(ALPHABET_DEFAULT)) {
      url += "&alphabet=" + alphabet;
    }
    if (letters!=LETTERS_DEFAULT) {
      url += "&letters=" + letters;
    }
    return url;
  }
  // the same with random
  public String audioUrl(String randomString) {
    captchaRandom = randomString;
    httpSess.setAttribute("captchasDotNetRandom", captchaRandom);
    return audioUrl();
  }

  /**
   * And this is for the check phase 
   */
  /**
   * Check the CAPTCHA code 
   * Returns 3 errors codes s, m, w and t if successfull
   */
  public char check(String password) {
    captchaRandom = "" + httpSess.getAttribute("captchasDotNetRandom");
 
    // check if 's'ession object "captchasDotNetRandom" exists
    if (captchaRandom.equals("null")) {
      return 's';
    }
    // check if the captcha is used 'm'ore than once
    if (captchaRandom.equals("used")) {
      return 'm';
    }

    // Compute the correct password
    String encryptionBase = secret + captchaRandom;
    if (!alphabet.equals(ALPHABET_DEFAULT) || letters!=LETTERS_DEFAULT) {
      encryptionBase += ":" + alphabet + ":" + letters;
    }
    MessageDigest md5;
    byte[] digest = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; 
    try {
      md5 = MessageDigest.getInstance("MD5");
      md5.update(encryptionBase.getBytes());
      digest = md5.digest();
    } catch (NoSuchAlgorithmException e) {
    }
    String correctPassword = "";
    int index;
    for (int i=0; i<letters; i++) {
      index = (digest[i]+256)%256%alphabet.length();
      correctPassword += alphabet.substring(index, index+1);
    }
    // Check if password is correct
    if (!password.equals(correctPassword)) {
      // 'w'rong Password
      return 'w';
    } else {
      // invalidate used captcha random
      httpSess.setAttribute("captchasDotNetRandom", "used");
      // 't'rue
      return 't';
    }
   
  }
  
  // getters and setters 
  public void setWidth(int width)
  {
  	this.width = width;
  }
  public int getWidth()
  {
	  return this.width;
  }
  public void setHeight(int height)
  {
  	this.height = height;
  }
  public int getHeigh()
  {
	  return this.height;
  }
  public void setSess(HttpSession httpSess)
  {
	  this.httpSess = httpSess;
  }
}
