package recycling.buyer.dao.face;

import java.util.List;
import java.util.Map;

import recycling.dto.buyer.Buyer;
import recycling.dto.buyer.BuyerProf;
import recycling.dto.buyer.ExpRes;
import recycling.dto.buyer.ExpReview;
import recycling.dto.seller.Exp;
import recycling.dto.seller.ExpFile;
import recycling.dto.seller.ExpSch;
import recycling.dto.seller.Seller;
import recycling.util.PagingAndCtg;

// 체험단 관련 DB 처리

public interface ExpDao {


	/**
	 * 인기순 체험단 5개 리스트
	 * 
	 * @return 체험단 List
	 */
	public List<Exp> selectTopPopExp();

	/**
	 * 새체험 체험단 5개 리스트
	 * 
	 * @return 체험단 List
	 */
	public List<Exp> selectTopRecExp();

	/**
	 * expCode와 일치하는 체험단 상세정보
	 * 
	 * @param expCode - 체험단 코드
	 * @return 체험단 상세정보
	 */
	public Exp selectByExpCode(String expCode);

	/**
	 * expCode와 일치하는 이미지
	 * 
	 * @param expCode - 체험단 코드
	 * @return file 
	 */
	public List<ExpFile> selectByExpFile(String expCode);

	/**
	 * 판매자 상세정보
	 * 
	 * @param sCode - 판매자 코드
	 * @return seller
	 */
	public Seller getSellerInfo(String sCode);

	/**
	 * 판매자의 구매자 정보
	 * 
	 * @param bCode - 구매자코드
	 * @return buyer
	 */
	public Buyer getBuyerInfo(String bCode);

	/**
	 * expCode와 일치하는 체험단 조회수
	 * 
	 * @param expCode - 체험단 코드
	 */
	public void updateExpHit(String expCode);

	/**
	 * 개인 구매자 정보 조회
	 * 
	 * @param bId - 구매자 아이디
	 * @return 개인 구매자 정보
	 */
	public Buyer getBuyerDetail(String bId);

	/**
	 * 체험단 시간/날짜 예약 가능 시간List
	 * 
	 * @param expCode - 체험단 코드
	 * @return 시간/날짜 List 
	 */
	public List<ExpSch> getExpSchList(String expCode);

	/**
	 * 체험단 일정 조회
	 * 
	 * @param schNo - 체험일정번호
	 * @return expSch
	 */
	public ExpSch getExpSch(int schNo);

	/**
	 * 체험단 예약 정보 db 삽입
	 * 
	 * @param expRes - 예약정보
	 */
	public void insertExpRes(ExpRes expRes);

	/**
	 * 체험단 일정 예약가능인원 수정
	 * 
	 * @param schNo - 예약일정번호
	 * @param resCnt - 인원수
	 */
	public void updateExpSchCnt(Map<String, Object> params);

	/**
	 * 예약코드가 일치하는 결제정보
	 * 
	 * @param resCode - 예약코드
	 * @return 결제 정보
	 */
	public ExpRes selectByResCode(String resCode);



	/**
	 * 체험 후기 DB 삽입
	 * 
	 * @param expReview - 후기 정보
	 */
	public void insertExpReview(ExpReview expReview);

	/**
	 * ExpRes(체험 예약테이블)에 bCode, expCode가 존재하는 구매자 존재 여부
	 * 
	 * @param params - bCode, expCode
	 * @return 존재 여부
	 */
	public List<ExpRes> selectByBuyerChk(Map<String, Object> params);

	/**
	 * bCode가 일치하는 프로필 판매자 상세정보에 띄워줄 프로필
	 * 
	 * @param bCode - 구매자 코드
	 * @return buyerProf
	 */
	public BuyerProf getBuyerProf(String bCode);

	/**
	 * exp paging
	 * 
	 * @param upPaging - paging
	 * @return paging 결과
	 */
	public int selectCntAllExpList(PagingAndCtg upPaging);

	/**
	 * 최신순 체험단 전체 리스트
	 * 
	 * @param upPaging - 페이징 정보 객체
	 * @return 체험단 List
	 */
	public List<Exp> selectRecentExp(PagingAndCtg upPaging);

	/**
	 * 인기순위 체험단 전체 리스트
	 * 
	 * @param upPaging - 페이징 정보 객체
	 * @return 체험단 List
	 */
	public List<Exp> selectPopularExp(PagingAndCtg upPaging);

	/**
	 * 체험단 전체 리스트
	 * 
	 * @param upPaging - 페이징 정보 객체
	 * @return 체험단 List
	 */
	public List<Exp> selectAllExp(PagingAndCtg upPaging);


	/**
	 * expCode가 일치하는 후기 리스트
	 * 
	 * @param params - expCode, paging
	 * @return expReviews
	 */
	public List<Map<String, Object>> selectRvwByExp(Map<String, Object> params);

	/**
	 * expCode가 일치하는 후기 리스트 페이징
	 * 
	 * @param params - paging, expCode
	 * @return paging
	 */
	public int selectCntRvwList(Map<String, Object> params);

}
