package sample;

import java.util.Arrays;
import java.util.List;

public class Sample {
    public static void main(String[] agrs) {
        new Sample().dodorian();
    }

    public void dodorian() {
        String literal = "KK_TT, DO_SEQ, TOTO_CD, SEQ";
        String[] wordArray = literal.split(",");
        List<String> wordList = Arrays.asList(wordArray);

        String[] replaceWordArray = {"TOTORO_SEQ:DO_SEQ","TOTORO_CD:TOTO_CD","F_SEQ:SEQ"};

        for(String word :wordList) {

                System.out.println(word);

                for(String replaceWord :replaceWordArray) {
                    String newWord = replaceWord.split(":")[0].toString();
                    String oldWord = replaceWord.split(":")[1].toString();

                    System.out.println("newWord : " + newWord + " , oldWord : " + oldWord);
                }


        }
    }
}
