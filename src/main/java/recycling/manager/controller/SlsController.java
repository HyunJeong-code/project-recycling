package recycling.manager.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import recycling.dto.seller.Exp;
import recycling.dto.seller.ExpFile;
import recycling.dto.seller.ExpSch;
import recycling.manager.service.face.SlsService;


@Controller
@RequestMapping("/manager/sls")
public class SlsController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired SlsService slsService;
	@Autowired HttpSession session;
	
	//체험단 전체조회
//	@GetMapping("/explist")
//	public String expList(
//			Model model
//			) {
//		logger.info("controller : explist[get]");
//		
//		//전체 조회기능
//		List<Exp> list = slsService.selectAll();
//		
//		model.addAttribute("exp", list);
//		logger.info("controller explist: {}", list);
//		
//		return "/manager/sls/explist";
//		
//	}
	
	//체험단 전체조회
	@GetMapping("/explist")
	public String expList(
			Model model
			) {
		//전체 조회기능
		List<Exp> list = slsService.selectAll();
		model.addAttribute("exp", list);
		logger.info("controller explist: {}", list);
		
		return "/manager/sls/explist";
	}
	
	//체험단 상세조회
	@GetMapping("/expdetail")
	public void expDetail(Exp exp, Model model) {
		logger.info("contoller: expDetail[GET]");
		
		Exp view = slsService.selectDetail(exp);
		
		model.addAttribute("view", view);
		logger.info("expDetail:{}", view );
		
	}
	
	//체험단 등록
	@GetMapping("/expform")
	private void expform() {}
	
	@PostMapping("/expform")
	public String expformProc(
			Exp exp,
			ExpSch expSch,
			@RequestParam("file") MultipartFile file
			) {

		//test세션
		session.setAttribute("sCode", "SEL0000002");
		String sCode = (String) session.getAttribute("sCode");
		exp.setsCode(sCode);
		
		slsService.insert(exp, expSch, file);
		
		
		
		return "redirect:./explist";
	}
	
	
	
	
	
	
	
//	@GetMapping("expupdate")//체험단 수정
//	@GetMapping("expdel")	// 체험단삭제

//	@GetMapping("expresdetail")	// 체험단 예약 상세조회
//	@GetMapping("expresupdate")	// 체험단 예약 정보변경[예약 구매자]
//	@GetMapping("changeexpres")	// 체험단 예약 변경
}
