package recycling.seller.dao.face;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import recycling.dto.buyer.ExpRes;
import recycling.dto.buyer.MyOrder;
import recycling.dto.buyer.OrderDetail;
import recycling.dto.manager.ResSchCnt;
import recycling.dto.seller.Exp;
import recycling.dto.seller.ExpFile;
import recycling.dto.seller.ExpSch;
import recycling.dto.seller.Prd;
import recycling.dto.seller.PrdFile;
import recycling.util.Paging;
import recycling.util.PagingAndCtg;
import recycling.dto.seller.AllPrd;
import recycling.dto.seller.Exp;

// 상품-판매 관련 DB

public interface SellingDao {

	/**
	 * 판매자가 올린 체험단 리스트
	 * 
	 * @param upPaging 페이징 정보 객체
	 * @return 체험단 List
	 */
	public List<Exp> selectMyExpList(PagingAndCtg upPaging);
	
	/**
	 * exp paging
	 * 
	 * @param upPaging - paging
	 * @return paging 결과
	 */
	public int selectCntAllexpList(PagingAndCtg upPaging);
	

	/**
	 * expcode와 일치하는 체험단 조회
	 * 
	 * @param expCode - 체험단 코드번호
	 * @return 일치하는 exp 조회
	 */
	public Exp selectByExp(String expCode);
	
	/**
	 * 체험 스케쥴 예약된 인원 조회
	 * 
	 * @param schNo - 일정번호
	 * @param expCode - 체험코드
	 * @return 예약된 인원 List
	 */
	public List<ResSchCnt> selectByResCnt(String expCode);

	/**
	 * expCode와 일치하는 체험 file 조회(main, detail)
	 * 
	 * @param expCode - 체험코드
	 * @return file List
	 */
	public List<ExpFile> selectByExpFile(String expCode);
	
	/**
	 * 체험정보
	 * 
	 * @param expRes
	 * @return
	 */
	public Exp expResDetail(String expCode);

	/**
	 * 체험 예약 조회
	 * 
	 * @param schNo - 체험 일정번호로 조회
	 * @return
	 */
	public ExpSch selectExpSchbySchNo(int schNo);
	
	/**
	 * 체험예약 정보
	 * 
	 * @param expCode
	 * @return
	 */
	public List<ExpRes> expResDetailRes(int schNo);

	/**
	 * 예약 확정버튼에 따른 예약변경
	 * 
	 * @param chBox
	 */
	public int expResCnf(String resCode);

	/**
	 * 예약 취소버튼에 따른 예약변경
	 * 
	 * @param chBox
	 */
	public int expResCnl(String resCode);


	//---------------exp END---------------------
	
	/**
	 * sCode와 일치하는 모든 rcyPrd 조회
	 * 
	 * @param upPaging - 조회할 sCode
	 * @return - 모든 rcyPrd 리스트
	 */
	public List<Prd> selectAllrcyPrd(PagingAndCtg upPaging);

	/**
	 * prdCode와 일치하는 upcy orders 조회
	 * 
	 * @param unPaging - 조회할 prdCode
	 * @return - 모든 orders 리스트
	 */
	public List<MyOrder> selectAllupcyMyOrder(PagingAndCtg unPaging);
	
	/**
	 * prdCode와 일치하는 rcy orders 조회
	 * 
	 * @param unPaging - 조회할 prdCode
	 * @return - 모든 orders 리스트
	 */
	public List<MyOrder> selectAllrcyMyOrder(PagingAndCtg unPaging);

	/**
	 * sCode와 일치하는 모든 upcyPrd 조회
	 * 
	 * @param upPaging - 조회할 sCode
	 * @return - 모든 upcyPrd 리스트
	 */
	public List<Prd> selectAllupcyPrd(PagingAndCtg upPaging);

	/**
	 * prdCode와 일치하는 Prd 삭제
	 * 
	 * @param prdCode - 삭제하는 Prd의 prdCode
	 * @return - DELETE 결과
	 */
	public int deletePrd(String prdCode);

	/**
	 * prdCode와 일치하는 Prd 조회
	 * 
	 * @param prdCode - 조회하는 Prd의 prdCode
	 * @return - 조회한 Prd 객체
	 */
	public Prd selectByprdCode(String prdCode);

	/**
	 * Prd 객체 UPDATE
	 * 
	 * @param prd - prd 수정 정보를 가진 DTO 객체
	 * @return - UPDATE 결과
	 */
	public int updatePrd(Prd prd);

	/**
	 * orddtCode로 OrderDetail 조회
	 * 
	 * @param orddtCode - 조회할 orddtCode
	 * @return - SELECT 결과
	 */
	public OrderDetail selectByorddtCode(String orddtCode);
	
	/**
	 * 모든 상품과 체험단을 조회
	 * 
	 * @param seller - 로그인한 판매자 정보
	 * @return 모든 상품에 대한 정보
	 */
//	public List<AllPrd> selectAllPrd(BuyerLogin seller);

	/**
	 * 주문 상태 변경
	 * 
	 * @param ordd - 주문상세 번호와 UPDATE하는 상태번호를 가진 DTO
	 * @return - UPDATE 결과
	 */
	public int updateOrderDetail(OrderDetail ordd);

	/**
	 * 송장 등록
	 * 
	 * @param myOrder - 송장 정보가 담긴 DTO
	 * @return - INSERT 결과
	 */
	public int insertShip(MyOrder myOrder);

	/**
	 * 송장 삭제
	 * 
	 * @param orddtCode - 삭제할 송장의 orddtCode
	 * @return - DELETE 결과
	 */
	public int deleteShip(String orddtCode);

	/**
	 * 주문 상세 조회
	 * 
	 * @param orddtCode - 조회할 주문 상세
	 * @return - 조회한 MyOrder DTO
	 */
	public MyOrder selectMyOrderByOrddtCode(String orddtCode);

	/**
	 * 주문 상세 수정
	 * 
	 * @param myOrder - 수정 정보를 담은 DTO 객체
	 * @return - UPDATE 결과
	 */
	public int updateMyOrder(MyOrder myOrder);

	/**
	 * rcyprd paging
	 * 
	 * @param upPaging - paging
	 * @return
	 */
	public int selectCntAllrcyPrd(PagingAndCtg upPaging);

	/**
	 * rcyMyOrder paging
	 * 
	 * @param unPaging - paging
	 * @return
	 */
	public int selectCntAllrcyMyOrder(PagingAndCtg unPaging);

	/**
	 * upcyprd paging
	 * 
	 * @param upPaging - paging
	 * @return
	 */
	public int selectCntAllupcyPrd(PagingAndCtg upPaging);

	/**
	 * upcyMyOrder paging
	 * 
	 * @param unPaging - paging
	 * @return
	 */
	public int selectCntAllMyOrder(PagingAndCtg unPaging);

	/**
	 * PRD 파일 조회 
	 * 
	 * @param prdCode - 조회할 prdCode
	 * @return - 파일 조회 결과
	 */
	public List<PrdFile> selectPrdFile(String prdCode);

	/**
	 * Prd 파일 업로드
	 * 
	 * @param prdMainFile - prdfile 객체
	 */
	public int insertPrdFile(PrdFile prdFile);

	/**
	 * Prd 파일 삭제
	 * 
	 * @param prdFile - prdfile 객체
	 */
	public int deletePrdFile(PrdFile prdFile);

	/**
	 * 상세 파일 삭제
	 * 
	 * @param map - 삭제할 파일코드
	 */
	public void deleteDetailFile(HashMap<String, String> map);

	 /** 판매자 전체 상품 개수 조회
	 * 
	 * @param upPaging - 판매자 정보, 검색어
	 * @return 개수
	 */
	public int selectCntAllPrd(PagingAndCtg upPaging);
	
	/**
	 * 판매자 전체 주문 개수 조회
	 * 
	 * @param upPaging - 판매자 정보, 검색어
	 * @return 개수
	 */
	public int selectCntAllOrd(PagingAndCtg unPaging);
	
	

	/**
	 * 판매자 전체 상품 조회
	 * 
	 * @param upPaging - 판매자 정보, 검색어
	 * @return 상품 리스트
	 */
	public List<Map<String, Object>> selectAllPrd(PagingAndCtg upPaging);

	/**
	 * 판매자 전체 주문 조회
	 * 
	 * @param upPaging - 판매자 정보, 검색어
	 * @return 주문 리스트
	 */
	public List<Map<String, Object>> selectAllOrd(PagingAndCtg unPaging);

	/**
	 * expCode와 일치하는 체험단 일정 조회
	 * 
	 * @param params - expCode
	 * @return expSch
	 */
	public List<ExpSch> selectAllSch(Map<String, Object> params);

	
	/**
	 * 체험단 체험일정 조회 페이징
	 * 
	 * @param params - paging, expCode
	 * @return paging 결과
	 */
	public int selectCntAllExpSch(Map<String, Object> params);
}
