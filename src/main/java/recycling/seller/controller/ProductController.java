package recycling.seller.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import recycling.dto.buyer.BuyerLogin;
import recycling.dto.seller.Prd;
import recycling.dto.seller.PrdFile;
import recycling.seller.service.face.ProductService;

// 상품 등록

@Controller
@RequestMapping("/seller/prd")
public class ProductController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired private ProductService productService;
	
	@GetMapping("/main")
	public void main() {
		logger.info("/seller/prd/main [GET]");
	}
	
	@GetMapping("/rcyform")
	public void rcyForm() {
		logger.info("/seller/prd/rcyform [GET]");
	}
	
	@PostMapping("/rcyform")
	public String rcyFormProc(Authentication authentication, Prd prd, MultipartFile main, List<MultipartFile> detail, Model model) {
		logger.info("/seller/prd/rcyform [POST]");
		
		BuyerLogin buyerLogin = (BuyerLogin) authentication.getPrincipal();
		logger.info("seller : {}", buyerLogin);
		prd.setsCode(buyerLogin.getsCode());
		logger.info("before -> rcy : {}", prd);
		
		int res = productService.insertRcy(prd);
		
		logger.info("main : {}", main);
		for(MultipartFile mult : detail) {
			logger.info("detail : {}", mult);			
		}
		
		logger.info("after -> rcy : {}", prd);
		
		
		PrdFile prdMain = productService.saveFile(main, prd);
		if(prdMain != null) {
			int resMain = productService.insertFileMain(prdMain);
			
			int resDetail = 0;
			for(MultipartFile mult : detail) {
				
				List<PrdFile> prdDetail = new ArrayList<PrdFile>();
				prdDetail.add(productService.saveFile(mult, prd));
				if(prdDetail != null) {
					for(int i = 0; i < prdDetail.size(); i++) {
						resDetail += productService.insertFileDetail(prdDetail.get(i));													
					}
				}
			}		
		}
		
		model.addAttribute("msg", "상품 등록이 완료되었습니다.");
		model.addAttribute("url", "/seller/prd/main");
		
		return "/layout/alert";
	}
	
	@GetMapping("/upcyform")
	public void upcyForm() {
		logger.info("/seller/prd/upyform [GET]");
	}
	
	@PostMapping("/upcyform")
	public String upcyFormProc(Authentication authentication, Prd prd, MultipartFile main, List<MultipartFile> detail, Model model) {
		logger.info("/seller/prd/upyform [POST]");
		
		BuyerLogin buyerLogin = (BuyerLogin) authentication.getPrincipal();
		logger.info("seller : {}", buyerLogin);
		prd.setsCode(buyerLogin.getsCode());
		logger.info("rcy : {}", prd);
		
		int res = productService.insertUpcy(prd);
		
		logger.info("main : {}", main);
		for(MultipartFile mult : detail) {
			logger.info("detail : {}", mult);			
		}
		
		PrdFile prdMain = productService.saveFile(main, prd);
		if(prdMain != null) {
			int resMain = productService.insertFileMain(prdMain);
			
			int resDetail = 0;
			for(MultipartFile mult : detail) {
				
				List<PrdFile> prdDetail = new ArrayList<PrdFile>();
				prdDetail.add(productService.saveFile(mult, prd));
				if(prdDetail != null) {
					for(int i = 0; i < prdDetail.size(); i++) {
						resDetail += productService.insertFileDetail(prdDetail.get(i));													
					}
				}
			}
			
		}
		
		model.addAttribute("msg", "상품 등록이 완료되었습니다.");
		model.addAttribute("url", "/seller/prd/main");
		
		return "/layout/alert";
	}
}
