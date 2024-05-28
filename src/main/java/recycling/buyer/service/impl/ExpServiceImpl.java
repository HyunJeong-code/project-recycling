package recycling.buyer.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import recycling.buyer.dao.face.ExpDao;
import recycling.buyer.service.face.ExpService;
import recycling.dto.buyer.Buyer;
import recycling.dto.buyer.ExpRes;
import recycling.dto.seller.Exp;
import recycling.dto.seller.ExpFile;
import recycling.dto.seller.ExpSch;
import recycling.dto.seller.Seller;
import recycling.util.Paging;

@Service
public class ExpServiceImpl implements ExpService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired private ExpDao expDao;
	@Autowired private ServletContext servletContext;
	
	@Override
	public List<Exp> selectAllExp(Paging paging) {
		
		return expDao.selectAllExp(paging);
	}

	@Override
	public Paging getSearchPaging(int curPage, String search) {
		
		Paging paging = null;
		
		int totalCount = expDao.selectCntAll(search);
		
		if("".equals(search)) {
			paging = new Paging(totalCount, curPage, search);
		} else {
			paging = new Paging(totalCount, curPage, search);
		}
		return paging;
	}

	@Override
	public List<Exp> selectRecentExp(Paging paging) {
		
		return expDao.selectRecentExp(paging);
	}

	@Override
	public List<Exp> selectPopularExp(Paging paging) {
		return expDao.selectPopularExp(paging);
	}


	@Override
	public List<Exp> selectTopPopExp() {
		return expDao.selectTopPopExp();
	}

	@Override
	public List<Exp> selectTopRecExp() {
		return expDao.selectTopRecExp();
	}

	@Override
	public Exp selectByExpCode(String expCode) {
		expDao.updateExpHit(expCode);
		
		return expDao.selectByExpCode(expCode);
	}

	@Override
	public List<ExpFile> selectByExpFile(String expCode) {
		
		return expDao.selectByExpFile(expCode);
	}

	@Override
	public Seller getSellerInfo(String sCode) {
		
		return expDao.getSellerInfo(sCode);
	}

	@Override
	public Buyer getBuyerInfo(String bCode) {
		return expDao.getBuyerInfo(bCode);
	}

	@Override
	public Buyer getBuyerDetail(String bId) {
		return expDao.getBuyerDetail(bId);
	}

	@Override
	public List<ExpSch> getExpSchList(String expCode) {
		
		return expDao.getExpSchList(expCode);
	}

	@Override
	public ExpSch getExpSch(int schNo) {
		
		return expDao.getExpSch(schNo);
	}

	@Override
	public void insertExpRes(ExpRes expRes) {
		expDao.insertExpRes(expRes);
	}

	@Override
	public void updateExpSchCnt(int schNo, int resCnt) {
		Map<String, Object> params = new HashMap<>();
        params.put("schNo", schNo);
        params.put("resCnt", resCnt);
		expDao.updateExpSchCnt(params);
	}
	
}
