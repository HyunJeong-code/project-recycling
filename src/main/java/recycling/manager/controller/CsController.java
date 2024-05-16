package recycling.manager.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;

import recycling.buyer.service.face.BuyerService;
import recycling.dto.buyer.Buyer;
import recycling.dto.buyer.Oto;
import recycling.dto.manager.Ans;
import recycling.manager.service.face.CsService;
import recycling.util.Paging;

@Controller
@RequestMapping("/manager/cs")
public class CsController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CsService csService;
	
	// 문의글 메인 페이지
	@RequestMapping("/main")
	public void main(@RequestParam(defaultValue = "0") int curPage, @RequestParam(defaultValue = "") String search,
			String category, Paging pagingParam, Model model) {

		Paging paging = new Paging();

		// 페이징 계산
		paging = csService.getPaging(pagingParam);
//		logger.info("{}", paging);

		// 게시글 목록 조회
		List<Oto> list = csService.list(paging);
//		logger.info("controller list: {}", list);

		model.addAttribute("paging", paging);
		model.addAttribute("list", list);

	}
	
	// 구매자 리스트
	@RequestMapping("/buyerlist")
	public String buyerList(@RequestParam(defaultValue = "0") int curPage,
			@RequestParam(defaultValue = "") String search, String category, Paging pagingParam, Model model) {

		Paging paging = new Paging();

		// 페이징 계산
		paging = csService.getPaging(pagingParam);
//		logger.info("{}", paging);

		// 구매자 목록 조회
		List<Buyer> buyerList = csService.buyerList(paging);

		model.addAttribute("paging", paging);
		model.addAttribute("buyerList", buyerList);

		return "manager/cs/buyerlist";

	}
	
	// 구매자 상세
	@GetMapping("/buyerdetail")
	public String buyerDetail(Buyer buyer, Model model) {
		
		Buyer buyerdetail = csService.buyerDetail(buyer);
		
		model.addAttribute("buyerdetail", buyerdetail);
		
		return "manager/cs/buyerdetail";
		
	}
	
	// 구매자 수정
	@GetMapping("/buyerupdate")
	public void buyerUpdate(String bCode, Model model) {
//		logger.info("{}", bCode);
		Buyer buyer = csService.getBuyer(bCode);
		model.addAttribute("buyer", buyer);
	}
	
	@PostMapping("/buyerupdate")
	public String buyerUpdaterForm(String bName, String bPhone, String bEmail, String bCode, Model model) {
		
//		logger.info("/board/update [POST]");
//		logger.info("{}", bName);
//		logger.info("{}", bPhone);
//		logger.info("{}", bEmail);
//		logger.info("{}", bCode);
		
		Buyer buyer = new Buyer();
		buyer.setbName(bName);
		buyer.setbPhone(bPhone);
		buyer.setbEmail(bEmail);
		buyer.setbCode(bCode);
		
//		logger.info("{}", buyer);
		
		csService.buyerUpdate(buyer);
		
		buyer = csService.getBuyer(bCode);
		
		model.addAttribute("buyer", buyer);
		
		return "manager/cs/buyerdetail";
	}
	
	// 구매자 삭제
	@GetMapping("/buyerdel")
	public String buyerDel(String bOut, String bOutDate, String bCode, Model model) {
		
		Buyer buyer = new Buyer();
		buyer.setbOut(bOut);
		buyer.setbOutDate(bOutDate);
		buyer.setbCode(bCode);
		
		csService.buyerDel(buyer);
		
		buyer = csService.getBuyer(bCode);
		
		model.addAttribute("buyer", buyer);
		
//		logger.info("{}", bOut);
//		logger.info("{}", bOutDate);
//		logger.info("{}", bCode);
		
		return "redirect:/manager/cs/buyerlist";
	}
	
	// 문의글 상세
	@GetMapping("/ansform")
	public String ansForm(String otoCode, String ansCode, Model model) {
		
		Oto oto = csService.ansForm(otoCode);
		
		model.addAttribute("oto", oto);
		
		// 답글 리스트 불러오기
		List<Ans> comments = new ArrayList<>(); 
		comments = csService.viewCom(ansCode);
		
		logger.info("22222222222222222{}", comments);
		
		boolean chkNull = csService.chkNull(comments);
		
		model.addAttribute("comments", comments);
		model.addAttribute("chkNull", chkNull);
		logger.info("3333333333333333{}", comments);
		
		return "manager/cs/ansform";
		
	}
	
	// 문의글 답글 작성
	@PostMapping("/ansform")
	@ResponseBody
	public String insert(String ansCode, String ansContent, HttpSession session) {
		
		logger.info("{}, {}", ansCode, ansContent);
		
//		String mgrId = (String) session.getAttribute("mgrId");
//		logger.info("{}", mgrId);		
		
//		String res = csService.ansFormInsert(mgrId, ansCode, ansContent);
		String res = csService.ansFormInsert(ansCode, ansContent);
		
		return res;
	}
	
	// 문의글 삭제
	@GetMapping("/otodel")
	public String otoDel(@RequestParam("otoCode") String otoCode) {
	    
	    csService.otoDel(otoCode);
	    return "redirect:/manager/cs/main";
	}

}
