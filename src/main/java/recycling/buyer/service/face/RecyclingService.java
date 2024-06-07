package recycling.buyer.service.face;

import java.util.List;
import java.util.Map;

import recycling.dto.seller.Seller;
import recycling.dto.buyer.Buyer;
import recycling.dto.buyer.Rcy;
import recycling.dto.seller.Prd;
import recycling.dto.seller.PrdFile;

public interface RecyclingService {
	
	/**
	 * 판매자 찾기
	 * 
	 * @return
	 */
	public List<Seller> findSeller();
	
	 /** 제품 DTO에서 리스트를 불러온다
	 * 
	 * @return
	 */
	public List<Prd> selectPrdList();
	
	/**
	 * 최신 상품부터 조회
	 * @return
	 */
	public List<Prd> selectLatestList();

	/**
	 * 조회수가 많은 상품부터 조회
	 * @return
	 */
	public List<Prd> selectHitList();
	
	/**
	 * 상품별 할당된 이미지 로드
	 * @return
	 */
	public List<PrdFile> selectPrdImage();

	/**
	 * 최신순 상품리스트 이미지 로드
	 * @return
	 */
	public List<PrdFile> selectLatestPrdImage();

	/**
	 * 조회순 상품리스트 이미지 로드
	 * @return
	 */
	public List<PrdFile> selectHitPrdImage();

	

	/**
	 * 제품번호를 기준으로 불러오면서 제품 정보를 불러온다
	 * 
	 * @param prdno 제품번호
	 * @return 제품번호의 상세페이지
	 */
	public Prd view(String prdCode);
	
	/**
	 * 판매자 기본 정보 로드
	 * 
	 * @param getsCode 판매자 코드
	 * @return 판매자 정보
	 */
	public Seller selectSeller(String getsCode);
	
	/**
	 * 판매자 정보 상세조회
	 * @param getbCode 구매자코드
	 * @return 판매자 상세 정보
	 */
	public Buyer selectBuyerByBCode(String getbCode);
	
	/**
	 * 판매자 거래 횟수 카운트 
	 * 
	 * @param	getsCode 프라이머리키인 sCode
	 */
	public int selectShipCnt(String getsCode);

	/**
	 * QnA 코드 로드
	 * 
	 * @param prdCode 로드에 필요한 제품 코드
	 * @return QnA
	 */
	public List<Map<String, Object>> selectQnaList(String prdCode);

	/**
	 * 개인 구매자 정보 조회
	 * 
	 * @param bId - 구매자 아이디
	 * @return 개인 구매자 정보
	 */
	public Buyer selectBuyerDetail(String bId);


	/**
	 * 파일 정보 DB에 삽입
	 * 
	 * @param rcy
	 * @return
	 */
	public int insertRcy(Rcy rcy);









	
	

	

}
