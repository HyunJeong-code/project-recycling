package recycling.buyer.dao.face;

import recycling.dto.buyer.Buyer;

// 메인페이지, 로그인/회원가입 관련 DB 처리

public interface BuyDao {

	public Buyer selectByIdPW(Buyer buyer);
	
}
