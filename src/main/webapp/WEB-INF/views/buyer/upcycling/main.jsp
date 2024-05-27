<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새상품 업사이클링</title>
<style>

	body {
		display: flex;
		flex-direction: column;
		align-items: center;
		margin: 0;
		overflow: auto;
		width: 100%;
		min-height: 100vh;
<<<<<<< HEAD
=======
		padding-bottom: 50px;
>>>>>>> 6658305de9ba193b447276c42d2b25f3b0299231
		position: relative;
	}
	
	header, footer {
        width: 100%;
        flex-shrink: 0;
    }
    
    


	.mainContainer {
		flex: 1;
		overflow-y: auto;
		margin-top: 20px;
<<<<<<< HEAD
		margin-bottom: 50px;
=======
		margin-bottom: 100px;
>>>>>>> 6658305de9ba193b447276c42d2b25f3b0299231
		max-width: 1000px;
		text-align: center;
		width: 100%;
	}

	.mainBanner {
		width: 100%;
		max-width: 1000px;
		height: 350px;
		border: 1px solid black;
		overflow: hidden;
		display: flex;
		flex-direction: column;
		align-items: center;
		padding-top: 20px;
		background-color: #f8f9fa;
	}

	.btnArea {
		display: flex;
		justify-content: center;
		align-items: center;
		overflow: visible;
		width: 100%;
	}

	.bannerBtn {
		padding: 10px 20px;
		background-color: gray;
		color: white;
		border: none;
		cursor: pointer;
		margin-right: 10px;
		border-radius: 30px;
	}

	.bannerInfo {
		width: 100%;
		display: flex;
		justify-content: space-around;
		align-items: center;
		flex-wrap: wrap;
	}

	.bannerContent {
		width: 220px;
		padding: 10px;
		margin-bottom: 20px;
		background-color: #f8f9fa;
		text-align: left;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}

	.bannerContent img {
		width: 200px;
		height: 200px;
		margin-top: 10px;
		margin-bottom: 5px;
	}

	.bannerTitle,
	.bannerPrice {
		margin-top: 5px;
		margin-bottom: 5px;
	}

	.bannerPrice {
		font-size: 16px;
		font-weight: bold;
	}

	.categoryContainer {
		display: flex;
		flex-direction: column;
		justify-content: space-around;
		margin-top: 5px;
		margin-bottom: 10px;
		padding: 10px 0;
	}

	.categoryBig {
		font-weight: bold;
		font-size: large;
		text-align: left;
		margin-bottom: 10px;
	}

	.categoryBox {
		display: flex;
		flex-wrap: wrap;
		gap: 10px;
		background-color: #d1d1d1;
		padding: 3px;
		justify-content: center;
	}

	.categoryMid {
		cursor: pointer;
		padding: 8px 16px;
		color: #6b6b6b;
		white-space: nowrap; /* 텍스트 줄 바꿈 방지 */
	}

	.sortBtn {
		display: flex;
		justify-content: flex-start;
		margin-bottom: 20px;
		
	}

	.sortBtn button {
		padding: 10px 20px;
		background-color: gray;
		color: white;
		border: none;
		cursor: pointer;
		margin-right: 10px;
		border-radius: 15px;
	}

	.prdContainer {
		display: flex;
		flex-direction: column;
		align-items: center;
		margin-top: 20px;
		max-width: 1000px;
		width: 100%;
	}

	.prdList {
		display: flex;
		flex-wrap: wrap;
		justify-content: flex-start;
		width: 100%;
		box-sizing: border-box;
	}

	.productInfo {
		width: 100%;
		display: flex;
		justify-content: space-around;
		align-items: flex-start;
		flex-wrap: wrap;
	}

	.prd {
		width: 220px;
		padding: 10px;
		margin-bottom: 20px;
		text-align: left;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}

	.prd img {
		width: 200px;
		height: 200px;
		margin-top: 10px;
		margin-bottom: 5px;
	}

	.prdTitle,
	.prdPrice {
		margin-top: 5px;
		margin-bottom: 5px;
	}

	.prdPrice {
		font-size: 16px;
		font-weight: bold;
	}
	
	.spacer {
		height: 130px;
		opacity: 1;
		pointer-events: none;
	}
	


</style>

</head>

<header>
	<c:import url="/WEB-INF/views/layout/buyer/buyerheader.jsp"/>
</header>
<body>
<<<<<<< HEAD
	<header>
		<c:import url="/WEB-INF/views/layout/buyer/buyerheader.jsp"/>
	</header>
=======
>>>>>>> 6658305de9ba193b447276c42d2b25f3b0299231
	
	<div class="mainContainer">
		<div class="mainBanner">
			<div class="btnArea">
				<button class="bannerBtn" id="newPrdBtn">신규 상품</button>
				<button class="bannerBtn" id="popPrdBtn">인기 상품</button>
			</div>
			<div class="bannerInfo">
				<!-- 신규 상품  -->
				<div class="bannerContent" id="newPrd1">
					<img src="${pageContext.request.contextPath}/resources/img/empty_400px.png">
					<p class="bannerTitle">신규 상품 1</p>
					<p class="bannerPrice">10,000원</p>
				</div>
				<div class="bannerContent" id="newPrd2">
					<img src="${pageContext.request.contextPath}/resources/img/empty_400px.png">
					<p class="bannerTitle">신규 상품 2</p>
					<p class="bannerPrice">10,000원</p>
				</div>
				<div class="bannerContent" id="newPrd3">
					<img src="${pageContext.request.contextPath}/resources/img/empty_400px.png">
					<p class="bannerTitle">신규 상품 3</p>
					<p class="bannerPrice">10,000원</p>
				</div>
				<div class="bannerContent" id="newPrd4">
					<img src="${pageContext.request.contextPath}/resources/img/empty_400px.png">
					<p class="bannerTitle">신규 상품 4</p>
					<p class="bannerPrice">10,000원</p>
					</div>
					<!-- 인기 상품  -->
					<div class="bannerContent" id="popPrd1" style="display: none;">
						<img src="${pageContext.request.contextPath}/resources/img/popular_400px.png">
						<p class="bannerTitle">인기 상품 1</p>
						<p class="bannerPrice">10,000원</p>
					</div>
					<div class="bannerContent" id="popPrd2" style="display: none;">
						<img src="${pageContext.request.contextPath}/resources/img/popular_400px.png">
						<p class="bannerTitle">인기 상품 2</p>
						<p class="bannerPrice">10,000원</p>
					</div>
					<div class="bannerContent" id="popPrd3" style="display: none;">
						<img src="${pageContext.request.contextPath}/resources/img/popular_400px.png">
						<p class="bannerTitle">인기 상품 3</p>
						<p class="bannerPrice">10,000원</p>
					</div>
					<div class="bannerContent" id="popPrd4" style="display: none;">
						<img src="${pageContext.request.contextPath}/resources/img/popular_400px.png">
						<p class="bannerTitle">인기 상품 4</p>
						<p class="bannerPrice">10,000원</p>
					</div>
				</div>
			</div>
		<div class="categoryContainer">
				<div class="categoryBig"><strong>업사이클</strong></div>
				<div class="categoryBox">
					<div class="categoryMid">전체</div>
					<div class="categoryMid">문구/오피스</div>
					<div class="categoryMid">완구/취미</div>
					<div class="categoryMid">의류</div>
					<div class="categoryMid">주방용품</div>
					<div class="categoryMid">생활용품</div>
					<div class="categoryMid">스포츠/레저</div>
					<div class="categoryMid">자동차용품</div>
					<div class="categoryMid">반려동물용품</div>
				</div>
			</div>
		<div class="sortBtn">
			<button id="lowPrice">낮은가격순</button>
			<button id="highPrice">높은가격순</button>
			<button id="sales">판매량순</button>
			<button id="latest">최신순</button>
		</div>
		
		<div class="prdContainer">
			<div class="prdList">
				<!-- 테스트용 상품 리스트 -->
<%-- 				<c:forEach var="index" begin="1" end="32">
					<div class="prd" style="display: ${index <= 16 ? 'flex' : 'none'};">
						<img src="${pageContext.request.contextPath}/resources/img/product_${index}.jpg">
						<p class="prdTitle">Proddddddddddddddddduct ${index}</p>
						<p class="prdPrice">30,000원</p>
					</div>
                </c:forEach> --%>
                
				<c:forEach var="prd" items="${list}">
				    <div class="prd">
				        <a href="${pageContext.request.contextPath}/buyer/upcycling/upcydetail?prdcode=${prd.prdCode}"">
				            <img src="${pageContext.request.contextPath}/resources/img/product_${prd.prdCode}.jpg">
				            <p class="prdTitle">${prd.prdName}</p>
				            <p class="prdPrice">${prd.price}원</p>
				        </a>
				    </div>
				</c:forEach>
                
			</div>
		</div>
		
		<!-- 페이지 이동 버튼 -->
		<div class="pagination">
			<button onclick="prevPage()">이전</button>
			<button onclick="nextPage()">다음</button>
		</div>
	</div>
	
	<div class="spacer"></div>
	
	<footer>
		<c:import url="/WEB-INF/views/layout/buyer/buyerfooter.jsp"/> 
	</footer>
	
<<<<<<< HEAD
</body>

<script>
=======
    <script>
>>>>>>> 6658305de9ba193b447276c42d2b25f3b0299231
	    document.addEventListener('DOMContentLoaded', function() {
			const newPrdBtn = document.getElementById('newPrdBtn');
	        const popPrdBtn = document.getElementById('popPrdBtn');
	        const newPrds = document.querySelectorAll('.bannerContent[id^="newPrd"]');
	        const popPrds = document.querySelectorAll('.bannerContent[id^="popPrd"]');
	        const sortButtons = document.querySelectorAll('.sortBtn button');
	        const categoryItems = document.querySelectorAll('.categoryMid');
	        const prdLists = document.querySelectorAll('.prdList');
	
	        let currentPage = 1;
	
	        // 초기 설정: 신규 상품 표시
	        newPrdBtn.style.backgroundColor = '#007bff';
	        newPrdBtn.style.color = 'white';
	        popPrds.forEach(prd => {
	            prd.style.display = 'none';
	        });
	
	        // 중분류 '전체' 활성화
	        const allCategory = document.querySelector('.categoryMid:first-child');
	        allCategory.classList.add('active');
	        allCategory.style.fontWeight = 'bold';
	        allCategory.style.color = 'black';
	
	        // 정렬 버튼 중 '낮은가격순' 활성화
	        const lowPriceBtn = document.getElementById('lowPrice');
	        lowPriceBtn.classList.add('active');
	        lowPriceBtn.style.backgroundColor = '#007bff';
	        lowPriceBtn.style.color = 'white';
	
	        // 신규 상품 버튼 클릭 시
	        newPrdBtn.addEventListener('click', function() {
	            newPrds.forEach(prd => {
	                prd.style.display = 'block';
	            });
	            popPrds.forEach(prd => {
	                prd.style.display = 'none';
	            });
	            newPrdBtn.style.backgroundColor = '#007bff';
	            newPrdBtn.style.color = 'white';
	            popPrdBtn.style.backgroundColor = '';
	            popPrdBtn.style.color = '';
	        });
	
	        // 인기 상품 버튼 클릭 시
	        popPrdBtn.addEventListener('click', function() {
	            newPrds.forEach(prd => {
	                prd.style.display = 'none';
	            });
	            popPrds.forEach(prd => {
	                prd.style.display = 'block';
	            });
	            newPrdBtn.style.backgroundColor = '';
	            newPrdBtn.style.color = '';
	            popPrdBtn.style.backgroundColor = '#007bff';
	            popPrdBtn.style.color = 'white';
	        });
	
	        // 카테고리 클릭 시
	        categoryItems.forEach(item => {
	            item.addEventListener('click', function() {
	                categoryItems.forEach(item => {
	                    item.classList.remove('active');
	                    item.style.fontWeight = 'normal';
	                    item.style.color = '';
	                });
	                this.classList.add('active');
	                this.style.fontWeight = 'bold';
	                this.style.color = 'black';
	            });
	        });
	
	        // 정렬 버튼 클릭 시
	        sortButtons.forEach(button => {
	            button.addEventListener('click', function() {
	                sortButtons.forEach(button => {
	                    button.classList.remove('active');
	                    button.style.backgroundColor = '';
	                    button.style.color = '';
	                    button.style.fontWeight = 'normal';
	                });
	                this.classList.add('active');
	                this.style.backgroundColor = '#007bff';
	                this.style.color = 'white';
	                this.style.fontWeight = 'bold';
	            });
	        });
	
	        // 페이지 표시 함수
	        function showPage(pageNum) {
	            // 모든 페이지 숨기기
	            prdLists.forEach(list => {
	                list.style.display = 'none';
	            });
	            // 현재 페이지만 보이기
	            if (prdLists[pageNum - 1]) {
	                prdLists[pageNum - 1].style.display = 'flex';
	            }
	        }
	
	        // 다음 페이지로 이동
	        function nextPage() {
	            if (currentPage < prdLists.length) {
	                currentPage++;
	                showPage(currentPage);
	            }
	        }
	
	        // 이전 페이지로 이동
	        function prevPage() {
	            if (currentPage > 1) {
	                currentPage--;
	                showPage(currentPage);
	            }
	        }
	
	        // 초기 설정: 첫 번째 페이지 표시
	        showPage(currentPage);
	        
		// footer 위치 조정 함수
		function adjustFooterPosition() {
			var bodyHeight = document.body.clientHeight;
			var windowHeight = window.innerHeight;
			var footer = document.querySelector('footer');

			if (windowHeight > bodyHeight) {
				var offset = windowHeight - bodyHeight;
				footer.style.bottom = offset + 'px';
			} else {
				footer.style.bottom = '0';
			}
		}

		// resize 이벤트 리스너 등록하여 footer 위치 조정
		window.addEventListener('resize', adjustFooterPosition);

		// 페이지 로드 후 초기에도 한 번 실행하여 화면 크기에 맞게 footer를 조정합니다.
		adjustFooterPosition();
	        
	    });
	</script>

<<<<<<< HEAD
=======
	
</body>
>>>>>>> 6658305de9ba193b447276c42d2b25f3b0299231


</html>