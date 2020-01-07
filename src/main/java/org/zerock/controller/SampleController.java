package org.zerock.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {
	

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	@RequestMapping("/")
	public String basic() {
		log.info("basic.....!!!!@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@.");
		return "/sample/index";
	}
	
	@RequestMapping(value = "/basic", method = {RequestMethod.GET,RequestMethod.POST})
	public void basicGet() {
		log.info("basic get.............");

	}
	
	@GetMapping("/basicOnlyGet")
	public void basicGet2() {
		log.info("basic get only get.............");

	}
	
	/*
	 * http://localhost:8080/sample/ex01?name=%EA%B9%80%ED%98%84%EC%9A%B0&age=29
	 * */
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		log.info("log4j annotation" + dto);
		
		return "ex01";
	}
	
	/*
	 * http://localhost:8080/sample/ex02List?ids=111&ids=222&ids=333
	 * */
	@GetMapping("/ex02List")
	public String ex02List(@RequestParam("ids") ArrayList<String> ids) {
		log.info("ids : " + ids);
		return "ex02List";
	}
	
	/*
	 * http://localhost:8080/sample/ex02Array?ids=111&ids=222&ids=333
	 * */
	@GetMapping("/ex02Array")
	public String ex02Array(@RequestParam("ids") String []ids) {
		log.info("array ids : " + Arrays.toString(ids));
		return "ex02Array";
	}
	
	/*
	 * http://localhost:8080/sample/ex02Bean?list%5B0%5D.name=aaa&list%5B1%5D.name=BBB&list%5B2%5D.name=CCC
	 * */
	@GetMapping("/ex02Bean")
	public String ex02Bean(SampleDTOList list) {
		log.info("list dtos: " +list);
		return "ex02Bean";
	}
	
	
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
//	}
	
	
	/*
	 * http://localhost:8080/sample/ex03?title=test&dueDate=2018-01-01	 -> InitBinder 
	 * http://localhost:8080/sample/ex03?title=test&dueDate=2018/01/01   -> DateTimeFormat 
	 * */
	@GetMapping("/ex03")
	public String ex03(TodoDTO todo) {
		log.info("todo : " + todo);
		return "ex03";
	}
	
	
	@GetMapping("/home1")
	public String home(Model model) {
		log.info("@@@@@@@@home1.jsp");
		model.addAttribute("serverTime", new java.util.Date());
		return "/sample/home1";
	}
			
	
//	@GetMapping("/ex04")
//	public String ex04(SampleDTO dto , int page) {
//		log.info("dto : " + dto);
//		log.info("page : " + page);
//		
//		return "/sample/ex04";
//	}

	@GetMapping("/ex04")
	public String ex04(SampleDTO dto , @ModelAttribute("page") int page) {
		log.info("dto : " + dto);
		log.info("page : " + page);
		
		return "/sample/ex04";
	}
	
	
	@GetMapping("/ex05")		//void형태는  getMapping의 적힌 경로를 그대로 페이지를 찾음. 즉 위에 클래스에 requestMapping으로 설정한 /sample 과 /ex05를 합친 /sample/ex05 를 찾는다.
	public void ex05() {
		log.info("/ex05..........");
	}
	
	@GetMapping("/ex06")			// json을 리턴해야할때 필요한 어노테이션 @ResponseBody
	public @ResponseBody SampleDTO ex06() {
		log.info("/ex06.......");
		SampleDTO dto = new SampleDTO();
		dto.setAge(10);
		dto.setName("홍길동");
		return dto;
	}
	
	@GetMapping("/ex07")
	public ResponseEntity<String> ex07(){
		log.info("/ex07......");
		
		String msg= "{\"name\" : \"홍길동\"}";
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json;charset=EUC-KR");
		return new ResponseEntity<>(msg,header,HttpStatus.OK);
		
	}
	
	@GetMapping("/exUpload")
	public void exUpload() {
		log.info("/exUpload.......");
	}
	
	@PostMapping("/exUploadPost")
	public void exUploadPost(ArrayList<MultipartFile> files) {
		int size=0;
		files.forEach(file-> {
			log.info("------------------");
			log.info("name : " + file.getOriginalFilename());
			log.info("size : " + file.getSize());
			
		});
		
	
		
	}
	
}



