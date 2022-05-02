package dodorian;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;

public class Convertor {
    public static void main(String[] args) {
        new Convertor().init();
    }

    public void init() {
        // 1. 전문
        List<String> literalList = new ArrayList<>();
        literalList.add(" ,A.LION_animal_TYPE, B.LION_ANIMAL, C.APPLE_FRUIT, D.PEACH_FRUIT_TYPE ");
        literalList.add(" (E.MBC_BROAD_TYPE) AS mbc_broad_type, F.MBC_BROAD, G.KBS_BROAD, H.KBS_BROAD_TYPE ");


        // 2. 전문 한줄씩 변경한다.
        String rtn = "";
        List<String> filterList = getFilterList();
        for(String literal :literalList) {
            rtn = convert(literal.toUpperCase(), filterList);
            System.out.println("rtn : " + rtn);
        }


    }

    public String convert(String literal, List<String> filterList) {
    	// System.out.println("literal : " + literal);
    	for(String filterWord :filterList) {	
    		String[] filterWordArray = StringUtils.split(filterWord, ":");
    		String tobeWord = filterWordArray[0].toString();
    		String asisWord = filterWordArray[1].toString();
    		
    		if(literal.contains(asisWord)) {
    			int offset = literal.indexOf(asisWord);
    			int limit = offset+asisWord.length();
    			StringBuffer sbf = new StringBuffer();
    			sbf.append(literal);
    			literal = sbf.replace(offset, limit, tobeWord).toString();
    			
    			if(literal.contains(asisWord)) {
    				literal = literal.replaceAll(asisWord, tobeWord);
    			}
    		}
    		
    	}	
    	
        return literal;

    }
    
    public List<String> getFilterList() {
        List<String> filterLiteral = new ArrayList<String>();
        filterLiteral.add("LION_TYPE:LION_ANIMAL");
        filterLiteral.add("MBC_TYPE:MBC_BROAD_TYPE");
        filterLiteral.add("PEACH_TYPE:PEACH_FRUIT_TYPE");
        filterLiteral.add("KBS_TYPE:KBS_BROAD_TYPE");
        filterLiteral.add("MBC_TYPE:MBC_BROAD");
        filterLiteral.add("LION_TYPE:LION_ANIMAL_TYPE");
        filterLiteral.add("APPLE_TYPE:APPLE_FRUIT");
        filterLiteral.add("KBS_TYPE:KBS_BROAD");
        // filterLiteral.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
    	return filterLiteral.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
    }


}

