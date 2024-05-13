package recycling.seller.dao.face;

import java.util.List;

import recycling.dto.buyer.ExpRes;
import recycling.dto.buyer.MyOrder;
import recycling.dto.seller.Exp;
import recycling.dto.seller.ExpSch;
import recycling.dto.seller.Prd;
import recycling.util.Paging;

// 상품-판매 관련 DB

public interface SellingDao {

	public List<Exp> selectMyExpList(Paging paging);

	public int selectCntAll(String search);

	public int selectPageAll();

	public Exp selectByExp(String expCode);

	public List<ExpRes> selectResList(Paging paging);

	/**
	 * sCode와 일치하는 모든 rcyPrd 조회
	 * 
	 * @param sCode - 조회할 sCode
	 * @return - 모든 rcyPrd 리스트
	 */
	public List<Prd> selectAllrcyPrd(String sCode);

	/**
	 * prdCode와 일치하는 모든 orders 조회
	 * 
	 * @param prdCode - 조회할 prdCode
	 * @return - 모든 orders 리스트
	 */
	public List<MyOrder> selectAllMyOrder(String prdCode);


}
