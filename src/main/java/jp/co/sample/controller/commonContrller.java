package jp.co.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/common")
public class commonContrller {
	
	@RequestMapping("/maintenance")
	public String maintenance() {
		return "common/error";
	}
}
