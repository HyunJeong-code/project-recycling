package recycling.seller.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import recycling.dto.buyer.BuyerLogin;
import recycling.dto.buyer.ExpRes;
import recycling.dto.buyer.MyOrder;
import recycling.dto.buyer.OrderDetail;
import recycling.dto.manager.ResSchCnt;
import recycling.dto.seller.Exp;
import recycling.dto.seller.ExpFile;
import recycling.dto.seller.ExpSch;
import recycling.dto.seller.Prd;
import recycling.seller.service.face.SellingService;
import recycling.util.Paging;
<<<<<<< HEAD
=======
import recycling.util.PagingAndCtg;
>>>>>>> TEST
import recycling.util.Range;

// 상품-판매 관리

@Controller
@RequestMapping("/seller/selling")
public class SellingController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired private SellingService sellingService;
	@Autowired private PageService pageService;
	
	@GetMapping("/main")
	public void main(
				Authentication authentication,
				@RequestParam(defaultValue = "0") int curPage,
				@RequestParam(defaultValue = "") String search,
				@RequestParam(defaultValue = "") String sCtg,
				Model model
			) {
		logger.info("/seller/selling/main [GET]");
		
		BuyerLogin buyerLogin = (BuyerLogin) authentication.getPrincipal();
        logger.info("buyerLogin : {}", buyerLogin);
        
        // 문의글 페이지 수 계산
  		PagingAndCtg upPaging = new PagingAndCtg();
  		PagingAndCtg unPaging = new PagingAndCtg();
        
  		upPaging = pageService.upPageSeller(curPage, sCtg, search, buyerLogin.getsCode());
        unPaging = pageService.unPageSeller(curPage, sCtg, search, buyerLogin.getsCode());
  		
        int upPage = sellingService.selectCntAllPrd(upPaging);
        int unPage = sellingService.selectCntAllOrd(unPaging);
        
        upPaging = new PagingAndCtg(upPage, upPaging.getCurPage(), upPaging.getSearch());
        upPaging.setUser(buyerLogin.getsCode());
        unPaging = new PagingAndCtg(unPage, unPaging.getCurPage(), unPaging.getSearch());
        unPaging.setUser(buyerLogin.getsCode());
        
        List<Map<String, Object>> prdList = sellingService.selectAllPrd(upPaging);
        List<Map<String, Object>> ordList = sellingService.selectAllOrd(unPaging);
        
        logger.info("prdList : {}", prdList);
        logger.info("ordList : {}", ordList);
        
        model.addAttribute("prdList", prdList);
        model.addAttribute("prdSize", prdList.size());
        model.addAttribute("upPaging", upPaging);
        
        model.addAttribute("ordList", ordList);
        model.addAttribute("ordSize", ordList.size());
        model.addAttribute("unPaging", unPaging);
        
    }
	
	@GetMapping("/rcylist")
	public void rcyList(Authentication authentication, Model model) {
		BuyerLogin buyerLogin = (BuyerLogin) authentication.getPrincipal();
        logger.info("buyerLogin : {}", buyerLogin);
		
		String sCode = buyerLogin.getsCode();
		
		//로그인 되어있는 아이디의 재활용 판매 상품 조회
		List<Prd> plist = sellingService.selectAllrcyPrd(sCode);
		
		//주문 리스트
		List<MyOrder> olist = new ArrayList<MyOrder>();
		
		//조회된 상품의 주문 리스트 조회
		for(Prd prd : plist) {
			String prdCode = prd.getPrdCode();
			
			List<MyOrder> list = sellingService.selectAllMyOrder(prdCode);
			
			for(MyOrder mo : list) {
				olist.add(mo);
			}
		}
		
		model.addAttribute("plist", plist);
		model.addAttribute("olist", olist);
	}
	
	@GetMapping("/upcylist")
	public void upcyList(Authentication authentication, Model model) {
		BuyerLogin buyerLogin = (BuyerLogin) authentication.getPrincipal();
        logger.info("buyerLogin : {}", buyerLogin);
		
		String sCode = buyerLogin.getsCode();
		
		//로그인 되어있는 아이디의 재활용 판매 상품 조회
		List<Prd> plist = sellingService.selectAllupcyPrd(sCode);
		
		//주문 리스트
		List<MyOrder> olist = new ArrayList<MyOrder>();
		
		//조회된 상품의 주문 리스트 조회
		for(Prd prd : plist) {
			String prdCode = prd.getPrdCode();
			
			List<MyOrder> list = sellingService.selectAllMyOrder(prdCode);
			
			for(MyOrder mo : list) {
				olist.add(mo);
			}
		}
		
		
		//내림차순 정렬
		Collections.sort(olist, new Range());
		
		logger.info("olist: {}",olist);
		logger.info("nplist: {}",plist);
		
		model.addAttribute("plist", plist);
		model.addAttribute("olist", olist);
	}
	
	@GetMapping("/upcydetail")
	public void upcyDetail(String prdCode, Model model) {
		Prd prd = sellingService.selectByprdCode(prdCode);
		
		model.addAttribute("prd", prd);
	}
	
	@GetMapping("/rcydetail")
	public void rcyDetail(String prdCode, Model model) {
		Prd prd = sellingService.selectByprdCode(prdCode);
		
		model.addAttribute("prd", prd);
	}
	
	@RequestMapping("/cydel")
	public String cyDel(@RequestParam(value = "arr[]") List<String> list) {

		
		for(String prdCode : list) {
			int deleteRes = sellingService.deletePrd(prdCode);  
		}
		
		return "jsonView";
	}
	
	@RequestMapping("/upcydel")
	public String upcyDel(String prdCode, Model model) {

		int deleteRes = sellingService.deletePrd(prdCode);
		
		model.addAttribute("msg", "상품이 삭제되었습니다.");
		model.addAttribute("url", "/seller/selling/upcylist");
		return "/layout/alert";
		
	}
	
	@RequestMapping("/rcydel")
	public String rcyDel(String prdCode, Model model) {

		int deleteRes = sellingService.deletePrd(prdCode);
		
		model.addAttribute("msg", "상품이 삭제되었습니다.");
		model.addAttribute("url", "/seller/selling/rcylist");
		return "/layout/alert";
		
	}
	
	@RequestMapping("/cyupdate")
	public String upcyUpdate(Prd prd) {
		logger.info("{}", prd);
		
		int res = sellingService.updatePrd(prd);
		
		return "redirect:/seller/selling/upcylist";
	}
	
	@GetMapping("/updatestt")
    public String updateStt(@RequestParam(value = "arr[]") List<String> list, int sttNo, Model model) {
		logger.info("list: {}",list);
		logger.info("sttNo: {}",sttNo);
		
		if(sttNo == 980) {
			//토큰 발급
			String token = sellingService.getToken();
			
			
			String successRes = "";
			String failRes = "";
			//반복 취소
			for(String orddtCode : list) {
				
				//OrderDetail 조회
				OrderDetail order = sellingService.selectByorddtCode(orddtCode); 
				logger.info("order: {}",order);
				
				//주문 취소(환불)
				int res = sellingService.cencelpay(order, token);
				
				if(res == 1) {
					OrderDetail ordd = new OrderDetail();
					ordd.setOrddtCode(orddtCode);
					ordd.setSttNo(sttNo);
					int updateRes = sellingService.updateOrderDetail(ordd);
					if(successRes != "") {
						successRes += ", ";					
					}
					successRes += orddtCode;
		        	
				} else {
					if(failRes != "") {
						failRes += ", ";					
					}
					failRes += orddtCode;
				}
			}
			
			if(successRes != "") {
				successRes += "환불 완료";
			}
			
			if(failRes != "") {
				failRes += "환불 실패";					
			}
			
			model.addAttribute("Msg", successRes + failRes);
		
		} else {
			for(String orddtCode : list) {
				OrderDetail ordd = new OrderDetail();
				ordd.setOrddtCode(orddtCode);
				ordd.setSttNo(sttNo);
				int updateRes = sellingService.updateOrderDetail(ordd);
			}
			model.addAttribute("Msg", "변경");
		}
        return "jsonView";
    }
	
	@PostMapping("/shipform")
	public String shipForm(@RequestBody List<MyOrder> list) {
		logger.info("list: {}",list);
		
		for(MyOrder myOrder : list) {
			int res = sellingService.insertShip(myOrder);
			
			logger.info("myOrder: {}",myOrder);			
		}
		
	    return "jsonView";	
	}
	
	@PostMapping("/shipdel")
	public String shipDel(@RequestParam(value = "arr[]") List<String> list) {
		
		for(String orddtCode : list) {
			logger.info("{}",orddtCode);
			int deleteRes = sellingService.deleteShip(orddtCode);  
		}
		
	    return "jsonView"; 
	}

	@GetMapping("/upcyorderdetail")
	public void upcyOrderDetail(String orddtCode, Model model) {
		
		MyOrder myOrder = sellingService.selectMyOrderByOrddtCode(orddtCode);
		
		model.addAttribute("order", myOrder);
	}
	
	@PostMapping("/upcyorderupdate")
	public String upcyOrderUpdate(MyOrder myOrder, Model model) {
		logger.info("{}", myOrder);
		
		int res = sellingService.updateMyOrder(myOrder);
		
		model.addAttribute("msg", "주문정보가 수정되었습니다.");
		model.addAttribute("url", "/seller/selling/upcylist");
		return "/layout/alert";
	}
	
	@GetMapping("/delship")
	public String delShip(String orddtCode, Model model) {
		int res = sellingService.deleteShip(orddtCode);
		
		
		model.addAttribute("msg", "송장이 삭제되었습니다.");
		model.addAttribute("url", "/seller/selling/upcylist");
		return "/layout/alert";
	}
	
	//판매자 체험 조회
	@GetMapping("/explist")
	public void expList(
			Model model,
			@RequestParam(defaultValue = "0") int curPage,
			@RequestParam(defaultValue = "") String search,
			@RequestParam(defaultValue = "") String sCtg,
			Authentication authentication,
			Exp exp
			) {
		logger.info("/explist [GET]");
		
		
		BuyerLogin buyerLogin = (BuyerLogin) authentication.getPrincipal();
		logger.info("buyerLogin : {}", buyerLogin);
		
		//페이지 수 계산
		PagingAndCtg upPaging = new PagingAndCtg();
		upPaging = pageService.upPageSeller(curPage, sCtg, search, buyerLogin.getsCode());
		
		int upPage = sellingService.selectCntAllexpList(upPaging);
		upPaging = new PagingAndCtg(upPage, upPaging.getCurPage(), upPaging.getSearch());
		
		logger.info("upPaging : {}", upPaging);
 		upPaging.setUser(buyerLogin.getsCode());
		
		List<Exp> list = sellingService.selectMyExpList(upPaging);
		
		model.addAttribute("upPaging", upPaging);
		model.addAttribute("list", list);
		model.addAttribute("upUrl", "/seller/selling/explist");
		
	}
	
	//체험단 상세조회
	@GetMapping("/expdetail")
	public void expDetail(
			Authentication authentication,
			Model model,
			String expCode,
			ExpFile expFile, 
			@RequestParam(defaultValue = "0") int curPage,
			@RequestParam(defaultValue = "") String search,
			@RequestParam(defaultValue = "") String sCtg
			) {
		
		BuyerLogin buyerLogin = (BuyerLogin) authentication.getPrincipal();
		logger.info("buyerLogin : {}", buyerLogin);
		
		//페이징
		PagingAndCtg upPaging = new PagingAndCtg();
		upPaging = pageService.upPageSeller(curPage, sCtg, search, buyerLogin.getsCode());
		int upPage = sellingService.selectCntAllExpSch(upPaging);
		upPaging = new PagingAndCtg(upPage, upPaging.getCurPage(), upPaging.getSearch());

		//상세 보기
		Exp exp = sellingService.selectByExp(expCode);
		model.addAttribute("exp", exp);
		
		//예약 스케쥴 조회기능
		List<ExpSch> schList = sellingService.selectAllSch(expCode);
		model.addAttribute("expSchList", schList);
		logger.info("schList: {}", schList);
		
		//예약된 인원 조회
		List<ResSchCnt> resCnt = sellingService.selectByResCnt(expCode);
		model.addAttribute("resCnt", resCnt);
		
		//첨부파일 main=썸네일, detail=상세이미지
		List<ExpFile> expFiles = sellingService.selectByExpFile(expCode);
		
		ExpFile main = null;
		List<ExpFile> detail = new ArrayList<>();
		
		for (ExpFile file : expFiles) {
	        if (file.getCtPflNo() == 600) {
	        	main = file;
	        } else if (file.getCtPflNo() == 610) {
	        	detail.add(file);
	        }
	    }
		
		model.addAttribute("expFiles", expFiles);
		model.addAttribute("main", main);
		model.addAttribute("detail", detail);
		model.addAttribute("upPaging", upPaging);
		model.addAttribute("upUrl", "/seller/selling/expdetail?expCode=" + expCode);
	}
	
	
	//체험단 예약관리
	@GetMapping("/expresdetail")
	public void expResDetail(
			Model model,
			String expCode,
			int schNo
			) {
		
		//체험단 조회
		Exp expView = sellingService.expResDetail(expCode);
		model.addAttribute("exp", expView);
		
<<<<<<< HEAD
		model.addAttribute("exp", exp);
=======
		//체험예약 조회
		ExpSch expSch = sellingService.selectExpSchbySchNo(schNo);
		model.addAttribute("expSch", expSch);
		
		List<ExpRes> resList = sellingService.expResDetailRes(schNo);
		model.addAttribute("resList", resList);

		
>>>>>>> TEST
	}
	
	// 체험단 예약 확정,취소 변경
		@PostMapping("/expresupdate")
		public String expresupdate(@RequestParam("chBox[]") List<String> chBox, @RequestParam String actionType) {
			
			sellingService.expResUpdate(chBox, actionType);
			
			return "jsonView";
		}
	
	
}