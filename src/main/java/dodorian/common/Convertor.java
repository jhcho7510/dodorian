package dodorian.common;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;

public class Convertor {
    public static void main(String[] args) {
        new Convertor().init();
    }
    
    
    
    
    public void generateVoAttribute(List<String> voLiteralList) {
    	voLiteralList.stream().forEach(System.out::println);
    	System.out.println("-------------------------------------------------------------------------------------------");    			
    	System.out.println("- VO : ");    			
    	System.out.println("-------------------------------------------------------------------------------------------");    			
    	
    	voLiteralList.stream().forEach(voLiteral -> {
    		List<String> voList = Arrays.asList(voLiteral.split("\\,"));

    		
    		
    		// voLiteralList.stream().forEach(System.out::println);
    		
    		
    		voList.stream().forEach(vo -> {
    			if(!vo.trim().isEmpty()) {
    				StringBuilder sbuilder = new StringBuilder();
    				String[] voAttributeArray = vo.split("\\.");
    				
    				if(voAttributeArray.length > 0) {
	    				String voAttribute = vo.split("\\.")[1].toString();
	    				List<String> voWordList = Arrays.asList(voAttribute.split("\\_"));

	    				voWordList.stream().forEach(System.out::println);
	    				int idx = 0;
	    				for(String word :voWordList) {
	    					String camelCaseWord = "";
	    					if(idx == 0) {
	    						camelCaseWord = word.toLowerCase();
	    					} else {
	    						camelCaseWord = Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
	    					}
	    					
	    					// System.out.println("CamelCase word : " + camelCaseWord);
	    					
	    					
//	    					if(camelCaseWord.contains("as") || camelCaseWord.contains("AS") || camelCaseWord.contains("\\s")) {
//	    					}
	    					if(camelCaseWord.contains("as")) {
	    						camelCaseWord = camelCaseWord.split("as")[1].toString();
	    					}
	    					else if(camelCaseWord.contains("AS")) {
	    						camelCaseWord = camelCaseWord.split("AS")[1].toString();
	    					}
	    					else {
	    					}
//	    					char[] c = camelCaseWord.toCharArray();
//	    					for(int i = 0; i <c.length; i++) {
//	    						System.out.println("c : " + Character.toString(c[i]));
//	    					}
//	    					else if(camelCaseWord.contains("\\s")) {
////	    						camelCaseWord.replaceAll("\\s", "");
//	    						System.out.println("else : " + camelCaseWord.replaceAll("\\s", ""));
//	    						// camelCaseWord = camelCaseWord.split("-")[1].toString();
//	    					}
	    					
	    					
	    					
//	    					if(camelCaseWord.contains("\\s")) {
//	    						camelCaseWord = camelCaseWord.split("\\s")[1].toString();
//	    					}
	    					
	    					sbuilder.append(camelCaseWord);
	    					idx++;
	    				}
    					
    				}
    				
    				
    				
    				System.out.println("private String " + sbuilder.toString() +";");
    			}
    		});
    	});
    	
    	
    	
    	
    	
    	voLiteralList.stream().forEach(voLiteral -> {
    		
    	});
    	
    }
    
    

    public void init() {
    	// 1. 변환대상 문자열
    	List<String> literalList = new ArrayList<>();
        literalList.add(" ,A.LION_animal_TYPE, B.LION_ANIMAL, C.APPLE_FRUIT, D.PEACH_FRUIT_TYPE ");
        literalList.add(" ,(E.MBC_BROAD_TYPE)          mbc_broad_type, F.MBC_BROAD, G.KBS_BROAD, H.KBS_BROAD_TYPE ");
        literalList.add(" ,A.dodorian_type,");
        literalList.add(" Z.DOTORI_CL, A.dotori_cl as dotori_cl, B.dotori_cl AS DOTORI_CL");


        // 2. 변환시작
        List<String> voLiteralList = new ArrayList();
        String rtn = "";
        List<String> filterList = getFilterList(); 
        for(String literal :literalList) {
            rtn = convert(literal.toUpperCase(), filterList);
            System.out.println("rtn : " + rtn.toUpperCase());
            // voLiteralList.add(rtn.toUpperCase());
        }
        // generateVoAttribute(voLiteralList);


    }

    // 3. 변환 비즈니스
    public String convert(String literal, List<String> filterList) {
    	// System.out.println("literal : " + literal);
    	for(String filterWord :filterList) {	// 변경대상문자와 변경결과 키워드를 루핑하면서, 변경대상문자열을 비교한다.
    		String[] filterWordArray = StringUtils.split(filterWord, ":");
    		String tobeWord = filterWordArray[0].toString();
    		String asisWord = filterWordArray[1].toString();

    		// 치환대상 문자열내에 
    		if(!asisWord.equals(tobeWord) && literal.contains(asisWord)) { // 변경대상 문자열에, 변경대상문자(ASIS)는 포함, 변경결과문자(TOBE)는 제외한다. 
    			int offset = literal.indexOf(asisWord); // 치환대상 문자열 내의, 치환대상 문자의 시작점을 찾는다.
    			int limit = offset+asisWord.length(); // 치환대상 문자열 내의, 치환대상 문자의 종료지점을 찾는다.
    			StringBuffer sbf = new StringBuffer();
    			sbf.append(literal);

    			// 변경대상 문장에서, 변경대상문자열을 변경결과문자열로 교체한다.
    			// - 변경이후 변경결과문자열(DOTORI_CL_CD)에 변경대상문자열(DOTORI_CL)이 여전히 포함되어 있다면, Z.DOTORI_CL_CD_CD형태의 문자열이 만들어지게되어, 
    			//   변경결과 문자열을 소문자로 치환한다. REPLACE는 대소문자를 다르게 인식함으로, 해당 문자열이 변경되었음을 태깅하는 효과를 얻을 수 있다.   
    			if(literal.contains(tobeWord)) {
    				literal = literal.replaceAll(tobeWord, tobeWord.toUpperCase());
    			}
    			literal = sbf.replace(offset, limit, tobeWord.toLowerCase()).toString();
    			// System.out.println("literal : " + literal);
    			
    			
    			if(literal.contains(asisWord) ) { // 변환대상 문장내에, 변환대상문자열이 여전히 포함되어 있다면 재귀호출을 시도한다.  
    				return convert(literal, filterList);
    			}
    		}
    	}	
        return literal;

    }
    
    // Filter List - 변경대상과 변경이후 결과가 포함된 리스트이다.
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
        filterLiteral.add("DOTORI_CL_CD:DOTORI_CL");

        // filterLiteral.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
        // 문자열을 내림차순으로 정렬한다.
        // - G.KBS_BROAD, H.KBS_BROAD_TYPE 문자열 변경시, 오름차순이면 G.KBS_TYPE, H.KBS_TYPE_TYPE으로 바뀔수 있으나, 
        //   내림 차순이면 처음엔 G.KBS_BROAD, H.KBS_TYPE, 두번째는 G.KBS_TYPE, H.KBS_TYPE으로 변경된다. 
    	return filterLiteral.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()); 
    }


}

