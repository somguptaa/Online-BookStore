package com.som.project.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderModuleDto {
	
	private Long custmerId;
	private List<String> title;
	
	
	

}
