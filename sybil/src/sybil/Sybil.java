package sybil;
import org.jsoup.*;
import java.io.IOException;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import java.util.*;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.comprehend.AmazonComprehend;
import com.amazonaws.services.comprehend.AmazonComprehendClientBuilder;
import com.amazonaws.services.comprehend.model.DetectDominantLanguageRequest;
import com.amazonaws.services.comprehend.model.DetectDominantLanguageResult;


/**
 *
 * @Davis Zheng ^
 */
public class Sybil {
    public static int countWords(String s){

    int wordCount = 0;

    boolean word = false;
    int endOfLine = s.length() - 1;

    for (int i = 0; i < s.length(); i++) {
        // if the char is a letter, word = true.
        if (Character.isLetter(s.charAt(i)) && i != endOfLine) {
            word = true;
            // if char isn't a letter and there have been letters before,
            // counter goes up.
        } else if (!Character.isLetter(s.charAt(i)) && word) {
            wordCount++;
            word = false;
            // last word of String; if it doesn't end with a non letter, it
            // wouldn't count without this.
        } else if (Character.isLetter(s.charAt(i)) && i == endOfLine) {
            wordCount++;
        }
    }
    return wordCount;
}

    public static ArrayList<String> scrap(){
        Document doc;
        ArrayList pop = new ArrayList();
        try{
        
        doc = Jsoup.connect("https://news.google.com/topics/CAAqJggKIiBDQkFTRWdvSUwyMHZNRGx6TVdZU0FtVnVHZ0pUUnlnQVAB?hl=en-SG&gl=SG&ceid=SG%3Aen").get();
	Elements art = doc.select("a[href]");
      for (int i=0; i < art.size(); i++) {
            pop.add(art.get(i).text());
        }
        //String ortit= art.attr("title");
        //System.out.println(ortit);
        //String title = doc.title(); //Get title
        //System.out.println (" Title: " + title); //Print title.
        
        }catch (IOException e){
        e.printStackTrace();
    }
        return pop;
    }
    public static void main(String[] args) {
        ArrayList<String> scrappy = scrap();
        Iterator<String> i = scrappy.iterator();
        while (i.hasNext()) {
        String s = i.next(); // must be called before you can call i.remove()
        System.out.println(s);
        if (countWords(s)<5){
            i.remove();
        }
        }
        System.out.println(scrappy);
        }
    
    
}
