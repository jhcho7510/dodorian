package dodorian.service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;
import dodorian.common.FileIO;
import dodorian.dto.DodorianDTO;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class DodorianService {
	 /*
	 private String companyName; // 기업명
	 private double roe; // 기업 ROE
	 private double stockCount; // 자기주식제외한 총 주식수
	 private double companyCapital;
	
	 private double stockPrice; // 기업가치 최종 가격
	 private double stockFirstPrice; // 기업가치 1차 할인가
	 private double stockSecondPrice; // 기업가치 2차 할인가
     */
	
	@Autowired
     private FileIO fileIO;
	
	@Autowired
	private DodorianCrawlingService crawlingService;

     private  Logger logger   = (Logger) LoggerFactory.getLogger(this.getClass().getSimpleName());


     private double patientValue(double stockSecondValue) {
         double discountFirstAmount = stockSecondValue * 0.8;
		 // double discountSecondAmount = discountFirstAmount * 0.8;
		 return discountFirstAmount;
     }
	
	 private String decimalFormat(double stockPrice) {
		 log.debug("123");
		 DecimalFormat df = new DecimalFormat("#,###");
		 return df.format(stockPrice);
	 }
	 
	 public List<DodorianDTO> getList() {
		 return null; 
	 }
	
	 public void dotoriRun() throws IOException {
		 
		 crawlingService.getCompanyList();
		//String filePath = "C:\\workspace\\dotori\\src\\main\\resources\\static\\candidate_1.txt";
	     String filePath = "//Users/jhcho/dodorian/src/main/resources/static/candidate.txt";
//	     String filePath = "C:\\Users\\SKTelecom\\Documents\\dodorian\\src\\main\\resources\\static\\candidate.txt";
	
	     List<String> candidateList = fileIO.getStockInfoList(filePath);
         int idx  = 1;
         for(String readLine : candidateList){
             String[] candidateInfo = readLine.split("\\|");
             
			 if(candidateInfo[0].toString().indexOf("#") < 0) {
    		      double enterpriseValue = stockPrice(candidateInfo, "stockPrice");
                double stockFirstValue = stockPrice(candidateInfo, "stockFirstPrice");
                 double stockSecondValue = stockPrice(candidateInfo, "stockSecondPrice");
                 double stockPatientValue = patientValue(stockSecondValue);
    		   
    		     DodorianDTO dto = new DodorianDTO();
    		   
       
			     logger.info("|-["+idx+"] "+candidateInfo[0]+"-----------------------------------------------------------------------------------------------------------------------------------|");
			     logger.info("| 1. ROE : "+ candidateInfo[1]+" | 2. stockCount : "+ candidateInfo[2] +" | 3. companyCapital : "+ candidateInfo[3]);
			     logger.info("| 4. Enterprise Value : "+ decimalFormat(enterpriseValue) +" | 5. stockFirstPrice : "+ decimalFormat(stockFirstValue) +" | 6. stockSecondPrice : "+ decimalFormat(stockSecondValue));
			     logger.info("| 7. Patient Value : " + decimalFormat(stockPatientValue));
			     idx++;
	         }
         }
     }
	
	private double stockPrice(String[] candidateInfo, String stockPriceFlag) {
		 String roe = candidateInfo[1].toString();
		 String stockCount = candidateInfo[2].toString();
		 String companyCapital = candidateInfo[3].toString();

		 double roeValue = Double.parseDouble(roe);
		 double stockCountValue = Double.parseDouble(stockCount.replaceAll("\\,", ""));
		 double companyCapitalValue = Double.parseDouble(companyCapital.replaceAll("\\,", ""));

		 double stockPrice = 0.0;

		 if("stockPrice".equals(stockPriceFlag)) { // 기업가치 최종 가격
			 stockPrice = (companyCapitalValue + (roeValue - 8.0) / 8.0 * companyCapitalValue) / stockCountValue;
		 }
		 else if("stockFirstPrice".equals(stockPriceFlag)) { // 기업가치 1차 할인가
			 stockPrice = (companyCapitalValue + companyCapitalValue*(roeValue * 0.01 - 0.08) * (0.9/(1+0.08-0.9))) / stockCountValue;
		 }
		 else if("stockSecondPrice".equals(stockPriceFlag)) { // 기업가치 2차 할인가
			 stockPrice = (companyCapitalValue + companyCapitalValue*(roeValue * 0.01 - 0.08) * (0.8/(1+0.08-0.8))) / stockCountValue;
		 }
		 else {

		 }

		 return stockPrice;


	 }
	
}