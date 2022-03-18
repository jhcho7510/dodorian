package dodorian.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DodorianDTO {
	private double enterpriseValue;
	private double stockFirstValue;	
	private double stockSecondValue;	
	private double stockPatientValue;	
	
	
	
}
