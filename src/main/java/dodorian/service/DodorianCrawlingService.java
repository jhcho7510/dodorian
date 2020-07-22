package dodorian.service;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsou.select.Elements;






import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;

@Service
public class DodorianCrawlingService {
	private static String industryA = "http://comp.fnguide.com/SVO2/ASP/SVD_Main.asp?pGB=1&gicode=A007810&cID=&MenuYn=Y&ReportGB=&NewMenuID=101&stkGb=701";
	private static String industryB = "https://finance.naver.com/sise/sise_group_detail.nhn?type=upjong&no=136";
	
	private  Logger logger   = (Logger) LoggerFactory.getLogger(this.getClass().getSimpleName());
	
	@PostConstruct
	public void getCompanyList() throws IOException {
		Document doc = Jsoup.connect(industryA).get();
		Elements contents = doc.select("table tbody tr");
		
		logger.info("tr contents size : " +  contents.size());
		
		
		int idx = 0;
		for(Element content : contents){
            Elements tdContents = content.select("td");
            Elements thContents = content.select("th"); 
            
            logger.info("idx ----------------->: "+idx + " , tdContents size --------------> " + tdContents.size() + " , thContents size --------------> " + thContents.size());

            
            StringBuffer sbf = new StringBuffer();
            if(tdContents.size() > 1) {
            	String td1th = tdContents.get(0).text();
            	String td2th = tdContents.get(1).text();
            	
            	String th1th ="None Title";  
            	String th2th  ="None Title ";
            	if(thContents.size() > 0) {
            		th1th = thContents.get(0).text();
            	}
            	if(thContents.size() > 1) {
            		th2th = thContents.get(1).text();
            	}
            	
            	logger.info(th1th+" : "+td1th);
            	logger.info(th2th+" : "+td2th);
            }
            
            
            idx++;
            logger.info("-----------------------------------------------------------------------------");
		}
		
	}
	
}
