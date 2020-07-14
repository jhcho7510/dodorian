package dodorian.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class FileIO {
	public List<String> getStockInfoList(String filePath) {
        //파일 객체 생성
       Path path = Paths.get(filePath);
        // 캐릭터셋 지정
       Charset cs = StandardCharsets.UTF_8;
        
       //파일 내용담을 리스트
      List<String> list = new ArrayList<String>();
      
      try{
    	  list = Files.readAllLines(path,cs);
      }catch(IOException e){
    	  e.printStackTrace();
       }
//		        for(String readLine : list){
//		            System.out.println(readLine);
//		        }
		       
      return list;
	}
	
//	public List<DotoriDTO> getCandidateList() {
//		List<DotoriDTO> candidateList  =  new ArrayList<DotoriDTO>();
//		try{
//		            //파일 객체 생성
//		            File file = new File("C:\\workspace\\dotori\\src\\main\\resources\\static\\candidate_1.txt");
//		            //입력 스트림 생성
//		            FileReader filereader = new FileReader(file);
//		            //입력 버퍼 생성
//		            BufferedReader bufReader = new BufferedReader(filereader);
//		            String line = "";
//		            while((line = bufReader.readLine()) != null){
//		                System.out.println(line);
//		            }
//		            //.readLine()은 끝에 개행문자를 읽지 않는다.            
//		            bufReader.close();
//		        }catch (FileNotFoundException e) {
//		            // TODO: handle exception
//		        }catch(IOException e){
//		            System.out.println(e);
//		        }
//
//		return candidateList;
//
//
//		}
}
