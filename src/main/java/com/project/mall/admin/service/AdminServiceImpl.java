package com.project.mall.admin.service;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.mall.command.CategoryVO;
import com.project.mall.command.ProductImageVO;
import com.project.mall.command.ProductVO;
import com.project.mall.util.page.Criteria;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	AdminMapper adminMapper;
	
	@Value("${project.upload.path}")
	private String uploadPath;
	
	// 폴더 생성 함수
		public String makeFolder() {
			String path = LocalDate.now().format( DateTimeFormatter.ofPattern("yyyyMMdd"));
			
			File file = new File(uploadPath + "/" + path);
			
			if(!file.exists()) { // 파일이 존재하면 true, 없으면 false
				file.mkdirs();
			}
			return path; // 날짜마다 다른 폴더 경로 반환
		}


	@Override
	public List<ProductVO> getList(String user_id, Criteria cri) {
		return adminMapper.getList(user_id, cri);
	}

	@Override
	public ProductVO getDetail(int product_no, String user_id) {
		return adminMapper.getDetail(product_no, user_id);
	}

	@Override
	public void updateDetail(ProductVO vo) {
		adminMapper.updateDetail(vo);
		
	}

	@Override
	public int registProduct(ProductVO vo, MultipartFile product_image) {
		
		int result = adminMapper.registProduct(vo); // 상품등록
		
		// 파일의 이름
		String originName = product_image.getOriginalFilename();
		
		// 파일 이름 자르기
		String fileName = originName.substring(originName.lastIndexOf("\\") + 1);
		
		
		// 동일한 파일을 재업로드시 기존의 파일을 덮어버리기 때문에, 난수이름으로 파일명을 바꿔서 올린다.
		// 16진수의 랜덤한 UUID값 생성
		String uuid = UUID.randomUUID().toString();
		
		
		// 날짜별로 폴더를 생성
		String filePath = makeFolder();
		
		// 저장할 경로
		String savePath = uploadPath + "/" + filePath + "/" + uuid + "_" + fileName;
		
		
		System.out.println("실제 파일명 : " + fileName);
		System.out.println("날짜별 폴더의 이름 : " + filePath);
		System.out.println("UUID값은 : " + uuid);
		System.out.println("세이브 할 경로 : " + savePath);
		System.out.println("================================");
		
		try {
			File saveFile = new File(savePath);
			product_image.transferTo(saveFile);
		} catch (Exception e) {
			System.out.println("파일업로드 중 에러발생");
			e.printStackTrace();
			return 0; // 업로드 실패의 의미
		}
		
		adminMapper.registProductImage(ProductImageVO.builder().fileName(fileName)
															   .filePath(filePath)
															   .uuid(uuid)
															   .user_id(vo.getUser_id())
															   .product_no(vo.getProduct_no())
															   .build());
		
		return result;
		
	}


	@Override
	public void deleteProduct(ProductVO vo) {
		
		adminMapper.deleteProduct(vo);
		
	}


	@Override
	public ArrayList<CategoryVO> getCategory() {
		
		return adminMapper.getCategory();
	}


	@Override
	public ArrayList<CategoryVO> getCategoryChild(CategoryVO vo) {
		return adminMapper.getCategoryChild(vo);
	}


	@Override
	public List<ProductImageVO> getProductImageList(List<ProductVO> list) {
		
		
		return adminMapper.getProductImageList(list);
	}


	@Override
	public int getTotal(String user_id) {
		
		
		return adminMapper.getTotal(user_id);
	}


	@Override
	public ProductImageVO getProductImage(ProductVO vo) {
		return adminMapper.getProductImage(vo);
	}


	@Override
	public List<ProductVO> getSearchedList(String user_id, Criteria cri, String product_name) {
		return adminMapper.getSearchedList(user_id, cri, product_name);
	}


	@Override
	public int getSearchedTotal(String user_id, String product_name) {
		return adminMapper.getSearchedTotal(user_id, product_name);
	}
	
	
	

}
