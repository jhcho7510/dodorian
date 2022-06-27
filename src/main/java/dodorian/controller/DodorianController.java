package dodorian.controller;

import java.util.List;

import excel.ExcelDownload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dodorian.dto.DodorianDTO;
import dodorian.service.DodorianService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/dodorian")
public class DodorianController {

	@Autowired
	DodorianService service;

	@Autowired
	ExcelDownload download;
	
	@GetMapping("/stockValuationList")
	public ResponseEntity<List<DodorianDTO>> getList() {
		System.out.println("111111111");
		return ResponseEntity.ok(service.getList());
	}




}
