package recycling.buyer.controller;

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

import recycling.buyer.service.face.HelpService;
import recycling.dto.buyer.Buyer;
import recycling.dto.buyer.Oto;
import recycling.dto.buyer.OtoCt;
import recycling.dto.manager.Faq;
import recycling.dto.manager.FaqCt;
import recycling.dto.manager.Notice;
import recycling.util.Paging;

// 메뉴 - 고객센터

@Controller
@RequestMapping("/buyer/help")
public class HelpController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired private HelpService helpService;
	
	@GetMapping("/main")
	public void main(
			@RequestParam(defaultValue = "0") int curPage,
			Model model,
			HttpSession session,
			Buyer buyer
			) {
		
		Paging paging = helpService.getPaging(curPage);
		List<Faq> list = helpService.selectAllFaq(paging);
		List<FaqCt> faqCtlist = helpService.selectAllCtFaq(paging);
		model.addAttribute("paging", paging);
		model.addAttribute("list", list);
		model.addAttribute("faqCtlist", faqCtlist);
		
	}
	
	@GetMapping("/noticelist")
	public void noticeList(
			@RequestParam(name = "ct_ntcno", defaultValue = "buyer") String ctNtcNo,
			Model model,
			@RequestParam(defaultValue = "0")int curPage, 
			@RequestParam(defaultValue = "") String search,
			HttpSession session
			) {
		
//		Paging paging = helpService.getSearchPaging(curPage, search);
		List<Notice> noticeList = null;

        // 판매자일 경우에만 공지사항 분류 선택 가능하도록 설정
        if ("seller".equals(ctNtcNo)) {
            noticeList = helpService.selectNoticeSeller();
        } else {
            noticeList = helpService.selectNoticeBuyer();
        }
        
        model.addAttribute("noticeList", noticeList);
//        model.addAttribute("paging", paging);
	}
	
	@GetMapping("/noticedetail")
	public void noticeDetail(
			Model model,
			String ntcCode
			) {
		
		Notice notice = helpService.selectByNotice(ntcCode);
		
		model.addAttribute("notice", notice);
	}
	
	
	@GetMapping("/otolist")
	public void otoList(
			Model model,
			@RequestParam(defaultValue = "0")int curPage, 
			@RequestParam(defaultValue = "") String search,
			@RequestParam(defaultValue = "0") int ct_otono
			) {
		
		Paging paging = helpService.getPaging(curPage);
		
		List<Oto> list;
		
		if (ct_otono == 0) {
	        list = helpService.selectAllOto();
	    } else {
	        // ct_otono 값에 해당하는 분류의 게시글만 가져오도록 함
	        list = helpService.selectByCtOto(Integer.toString(ct_otono));
	    }
		
		List<OtoCt> ctlist = helpService.selectAllOtoCt();
		
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("ctlist", ctlist);
	}
	
	@GetMapping("/otoform")
	public void otoForm(
			Model model
			) {
		logger.info("otoform [GET]");
		
		List<OtoCt> oct = helpService.getAllOct();
		model.addAttribute("oct", oct);
		
	}
	
	@PostMapping("/otoform")
	public String otoFormProc(
			HttpSession session,
			Oto oto,
			@RequestParam("ct_otono") String ctOtoNo, // 선택된 분류 값을 받음
			MultipartFile file
//			, @RequestParam("visibility") String visibility,
//            @RequestParam(value = "password", required = false) String password
			) {
		
		oto.setCtOtoNo(Integer.parseInt(ctOtoNo));
//		boolean isPrivate = visibility.equals("private");
		session.setAttribute("bCode", "BUY0000001");
		String bCode = (String) session.getAttribute("bCode");
		oto.setbCode(bCode);
		
		helpService.insertOto(oto);


		return "redirect:/buyer/help/otolist";
	}
	
	@GetMapping("/otodetail")
	public void otoDetail(
			Model model,
			String otoCode
			) {
		Oto oto = helpService.selectByOtoCode(otoCode);
		List<OtoCt> oct = helpService.getAllOct();
		
		model.addAttribute("oto", oto);
		model.addAttribute("oct",oct);
		
	}
}
