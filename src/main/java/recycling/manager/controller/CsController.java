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
import org.springframework.web.multipart.MultipartFile;

import recycling.dto.buyer.Buyer;
import recycling.dto.buyer.BuyerAdr;
import recycling.dto.buyer.BuyerProf;
import recycling.dto.buyer.BuyerRank;
import recycling.dto.buyer.Oto;
import recycling.dto.buyer.OtoFile;
import recycling.dto.manager.Ans;
import recycling.dto.manager.Manager;
import recycling.dto.manager.ManagerLogin;
import recycling.dto.manager.MgrFile;
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
	public void main(Authentication authentication, Model model, @RequestParam(defaultValue = "0") int curPage,
			@RequestParam(defaultValue = "") String search, @RequestParam(defaultValue = "") String sCtg) {

		// 매니저 권한 부여
		ManagerLogin managerLogin = (ManagerLogin) authentication.getPrincipal();

		// 페이지 수 계산
		PagingAndCtg upPaging = new PagingAndCtg();
		upPaging = pageService.upPageMgr(curPage, sCtg, search, managerLogin.getMgrCode());

		int upPage = csService.selectCntAllotoList(upPaging);
		upPaging = new PagingAndCtg(upPage, upPaging.getCurPage(), upPaging.getSearch());

		// 게시글 목록 조회
		List<Oto> list = csService.list(upPaging);
//		logger.info("controller list: {}", list);

		model.addAttribute("upPaging", upPaging);
		model.addAttribute("list", list);
		model.addAttribute("upUrl", "/manager/cs/main");

	}

	// 구매자 리스트
	@RequestMapping("/buyerlist")
	public String buyerList(Authentication authentication, Model model, @RequestParam(defaultValue = "0") int curPage,
			@RequestParam(defaultValue = "") String search, @RequestParam(defaultValue = "") String sCtg) {

		// 매니저 권한 부여
		ManagerLogin managerLogin = (ManagerLogin) authentication.getPrincipal();

		// 페이지 수 계산
		PagingAndCtg upPaging = new PagingAndCtg();
		upPaging = pageService.upPageMgr(curPage, sCtg, search, managerLogin.getMgrCode());

		int upPage = csService.selectCntAllbuyerList(upPaging);
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
	public void empDetail(Buyer buyer, BuyerAdr buyerAdr, BuyerProf buyerProf, BuyerRank buyerRank, Model model) {
	
		// 세부사항 조회
		Buyer buyerdetail = csService.buyerDetail(buyer);
		model.addAttribute("buyerdetail", buyerdetail);
		
		// 주소 조회
		BuyerAdr buyerAdrdetail = csService.buyerAdrDetail(buyerAdr);
		model.addAttribute("buyerAdrdetail", buyerAdrdetail);
		
		// 프로필 사진 조회
		BuyerProf buyerProfdetail = csService.buyerProfDetail(buyerProf);
		model.addAttribute("buyerProfdetail", buyerProfdetail);
		
		// 등급 조회
		BuyerRank buyerRankdetail = csService.buyerRankDetail(buyerRank);
		model.addAttribute("buyerRankdetail", buyerRankdetail);
		
	}
	
	// 구매자 정보 업데이트창
	@GetMapping("/buyerupdate")
	public void buyerUpdate(Buyer buyer, Model model, BuyerProf buyerProf, BuyerAdr buyerAdr, String bCode) {
		
		// 주소 조회
		BuyerAdr buyerAdrdetail = csService.buyerAdrUpdate(buyerAdr);
		model.addAttribute("buyerAdrdetail", buyerAdrdetail);
		
		// 파일 조회
		BuyerProf buyerProfdetail = csService.buyerFileUpdate(buyerProf);
		model.addAttribute("buyerProfdetail", buyerProfdetail);
	
		// 정보 조회
		Buyer update = csService.csUpdateView(buyer);
		model.addAttribute("view", update);

	}
	
	// 구매자 정보 업데이트
	@PostMapping("/buyerupdate")
	public String updateProc(
			Buyer buyer
			, Model model
			, int bProfNo
			, String bCode
			, BuyerAdr buyerAdr
			, @RequestParam("buyerfileUpdate") MultipartFile buyerFileUpdate
			) {
		logger.info("buyerFileUpdate: {}", buyerFileUpdate);
		
		csService.csUpdate(buyer);
		logger.info("csUpdate: {}", buyer);
		
		csService.csUpdateAdr(buyerAdr);
		logger.info("csUpdateAdr: {}", buyerAdr);
		
		// buyerfile 프로필 업데이트
		if(buyerFileUpdate != null && !buyerFileUpdate.isEmpty()) {
		
			BuyerProf buyerProf = new BuyerProf();
			buyerProf = csService.updateProFileGet(buyerFileUpdate, buyer);
			buyerProf.setbProfNo(bProfNo);
			buyerProf.setbCode(bCode);
			
			// 파일 업데이트
			csService.updateProfileProc(buyerProf);
		}else {
			logger.info("프로필이 존재합니다.");
		}
		
		model.addAttribute("msg", "회원정보가 변경되었습니다.");
		model.addAttribute("url", "/manager/cs/buyerdetail?bCode=" + bCode);
		
		return "/layout/alert";

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

		return "redirect:/manager/cs/buyerlist";
	}

	// 문의글 상세
	@GetMapping("/ansform")
	public void ansForm(String otoCode, String ansCode, Model model, HttpSession session) {

		Oto oto = csService.ansForm(otoCode);
		List<OtoFile> otoFiles = csService.getOtoFiles(otoCode);
		
		model.addAttribute("oto", oto);
		model.addAttribute("otoFiles",otoFiles);

		// 답글 리스트 불러오기
		List<Ans> comments = csService.viewCom(otoCode);

		boolean chkNull = csService.chkNull(comments);

		model.addAttribute("comments", comments);
		model.addAttribute("chkNull", chkNull);

	}

	// 문의글 답글 작성
	@PostMapping("/ansform")
	@ResponseBody
	public String insert(Authentication authentication, String mgrCode, String ansCode, String ansContent,
			String otoCode, HttpSession session) {

		// 매니저 권한 부여
		ManagerLogin managerLogin = (ManagerLogin) authentication.getPrincipal();

		logger.info("mgrCode: {}", managerLogin);

		if (managerLogin == null) {
			return "Manager code not found in session.";
		}
		
		// 답변 작성 시 로그인한 아이디로 매니저 코드 삽입됨
		String mgrCode1 = managerLogin.getMgrCode();

		try {
			csService.ansFormInsert(mgrCode1, ansCode, ansContent, otoCode);
			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}

	}

	// 문의글 삭제
	@GetMapping("/otodel")
	public String otoDel(@RequestParam("otoCode") String otoCode) {

		csService.otoDelAns(otoCode);
		csService.otoDelOtoFile(otoCode);
		csService.otoDelOto(otoCode);
		
		return "redirect:/manager/cs/main";
	}

}
