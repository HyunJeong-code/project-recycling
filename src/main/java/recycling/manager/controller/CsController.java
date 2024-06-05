package recycling.manager.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import recycling.dto.buyer.Buyer;
import recycling.dto.buyer.Oto;
import recycling.dto.manager.Ans;
import recycling.dto.manager.ManagerLogin;
import recycling.manager.service.face.CsService;
import recycling.manager.service.face.MgrService;
import recycling.util.Paging;
import recycling.util.PagingAndCtg;

@Controller
@RequestMapping("/manager/cs")
public class CsController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CsService csService;
	
	@Autowired
	private MgrService mgrService;
	
	@Autowired 
	private recycling.page.face.PageService pageService;
	
	// 문의글 메인 페이지
	@RequestMapping("/main")
	public void main(
			Authentication authentication
			, Model model
			, @RequestParam(defaultValue = "0") int curPage
			, @RequestParam(defaultValue = "") String search
			, @RequestParam(defaultValue = "") String sCtg) {

		//매니저 권한 부여
		ManagerLogin managerLogin = (ManagerLogin) authentication.getPrincipal();
		
		//페이지 수 계산
		PagingAndCtg upPaging = new PagingAndCtg();
		upPaging = pageService.upPageMgr(curPage, sCtg, search, managerLogin.getMgrCode());
		
<<<<<<< HEAD
		int upPage = mgrService.selectCntAllempList(upPaging);
=======
		int upPage = csService.selectCntAllotoList(upPaging);
>>>>>>> TEST
        upPaging = new PagingAndCtg(upPage, upPaging.getCurPage(), upPaging.getSearch());

		// 게시글 목록 조회
		List<Oto> list = csService.list(upPaging);
//		logger.info("controller list: {}", list);

		model.addAttribute("upPaging", upPaging);
		model.addAttribute("list", list);
		model.addAttribute("upUrl", "/manager/cs/main");
<<<<<<< HEAD

=======
		
>>>>>>> TEST
	}
	
	// 구매자 리스트
	@RequestMapping("/buyerlist")
	public String buyerList(
			Authentication authentication
			, Model model
			, @RequestParam(defaultValue = "0") int curPage
			, @RequestParam(defaultValue = "") String search
			, @RequestParam(defaultValue = "") String sCtg
			) {

		//매니저 권한 부여
		ManagerLogin managerLogin = (ManagerLogin) authentication.getPrincipal();
		
		//페이지 수 계산
		PagingAndCtg upPaging = new PagingAndCtg();
		upPaging = pageService.upPageMgr(curPage, sCtg, search, managerLogin.getMgrCode());
		
<<<<<<< HEAD
		int upPage = mgrService.selectCntAllempList(upPaging);
=======
		int upPage = csService.selectCntAllbuyerList(upPaging);
>>>>>>> TEST
        upPaging = new PagingAndCtg(upPage, upPaging.getCurPage(), upPaging.getSearch());

		// 구매자 목록 조회
		List<Buyer> buyerList = csService.buyerList(upPaging);

		model.addAttribute("upPaging", upPaging);
		model.addAttribute("buyerList", buyerList);
		model.addAttribute("upUrl", "/manager/cs/buyerlist");

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
	public void ansForm(String otoCode, String ansCode, Model model, HttpSession session) {
		
		Oto oto = csService.ansForm(otoCode);
		model.addAttribute("oto", oto);
		
//		logger.info("ans{}", ansCode);
//		logger.info("oto{}", otoCode);
		
		// 답글 리스트 불러오기
		List<Ans> comments = csService.viewCom(otoCode);
		
		boolean chkNull = csService.chkNull(comments);
		
		model.addAttribute("comments", comments);
		model.addAttribute("chkNull", chkNull);

//		logger.info("11111111111111{}", comments);
		
//		return "manager/cs/ansform";
		
	}
	
	// 문의글 답글 작성
	@PostMapping("/ansform")
	@ResponseBody
	public String insert(String ansCode, String ansContent, String otoCode, HttpSession session) {
		
		// 일단 로그인 없어서 mgrCode코드 고정값으로 넣음
	    String mgrCode = "MGR0000001";

//	    String mgrCode = (String) session.getAttribute("mgrCode");
	    
	    logger.info("mgrCode: {}", mgrCode);
	    
	    if (mgrCode == null) {
	        return "Manager code not found in session.";
	    }

	    try {
	        csService.ansFormInsert(mgrCode, ansCode, ansContent, otoCode);
	        return "Success";
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "Error";
	    }
	    
	}
	
	// 문의글 삭제
	@GetMapping("/otodel")
	public String otoDel(@RequestParam("otoCode") String otoCode) {
	    
	    csService.otoDel(otoCode);
	    return "redirect:/manager/cs/main";
	}
	 
}
