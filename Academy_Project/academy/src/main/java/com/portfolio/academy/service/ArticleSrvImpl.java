package com.portfolio.academy.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.portfolio.academy.model.ArticleVO;
import com.portfolio.academy.model.LikesVO;
import com.portfolio.academy.repository.ArticleDao;

@Service
public class ArticleSrvImpl implements ArticleSrv{
	
	@Resource(name="uploadPath")
	private String uploadPath; // 업로드된 파일 저장 경로
	
	private static final String PREFIX_URL = "/upload/article/";
	
	private static final Logger logger = LoggerFactory.getLogger(ArticleSrvImpl.class);

	@Autowired
	ArticleDao articleDao;

	@Override
	public void deleteArticle(String boardCode, List<String> chkArr) {
		// DB에서 삭제하는 거는 dao에서 하고
		// service에서는 file을 삭제한다.
		
		for(int i = 0; i < chkArr.size(); i++) {
			// 해당 aid에 맞는 article_file_url을 들고온다
			ArticleVO avo = new ArticleVO();
			avo.setAid(Integer.parseInt(chkArr.get(i)));
			avo.setBoardCode(boardCode);
			String filename = articleDao.getFileName(avo);
			
			/**** 파일삭제 ****/
			File deleteFile = new File(uploadPath + filename);
			if(!deleteFile.exists()) {
				System.out.println("파일이 존재하지 않습니다.");
			} else {
				deleteFile.delete();
				System.out.println(filename + "파일을 삭제하였습니다.");
			}
			/**** 파일삭제 ****/
		
		}
		articleDao.deleteArticle(boardCode, chkArr);
	}

	@Override
	public void articleWrite(ArticleVO avo, MultipartFile file) throws IOException {
		File folder = new File(uploadPath);
		
		// 해당 디렉토리(upload)가 없을 경우 디렉토리를 생성합니다.
		if( !folder.exists() ) {
			try {
				folder.mkdir(); // 폴더 생성합니다.
				logger.info("폴더가 생성되었습니다.");
			}
			catch(Exception e ) {
				e.getStackTrace();
			}
		}
		
		if( file.isEmpty()) {
			articleDao.articleWrite(avo);
		} else {
			/**** 파일정보 ****/
			String originFileName = file.getOriginalFilename();
//			String extName = originFileName.substring(originFileName.lastIndexOf("."), originFileName.length());
//			Long size = file.getSize();
			
			/**** 서버에서 저장 할 파일 이름 ****/
			String saveFileName = genSaveFileName(originFileName);
			
//			System.out.println("originFileName : " + originFileName);
//			System.out.println("extensionName : " + extName);
//			System.out.println("size : " + size);
//			System.out.println("saveFileName : " + saveFileName);
			
			writeFile(file, saveFileName);
			String url = PREFIX_URL + saveFileName;
			
			avo.setArticleFileName(saveFileName);
			avo.setArticleFileOrigin(originFileName);
			avo.setArticleFileUrl(url);
		
			articleDao.articleWrite(avo);
		}
	}	

	@Override
	public ArticleVO articleView(ArticleVO avo) {
		return articleDao.articleView(avo);
	}

	@Override
	public void delArticleOne(ArticleVO avo) {
		String filename = articleDao.getFileName(avo);
		
		/**** 파일삭제 ****/
		File deleteFile = new File(uploadPath + filename);
		if(!deleteFile.exists()) {
			System.out.println("파일이 존재하지 않습니다.");
		} else {
			deleteFile.delete();
			System.out.println(filename + "파일을 삭제하였습니다.");
		}
		/**** 파일삭제 ****/
		
		articleDao.delArticleOne(avo);
	}

	@Override
	public void articleLikes(LikesVO lvo) {
		articleDao.articleLikes(lvo);
	}

	@Override
	public void articleModify(ArticleVO avo, MultipartFile file) throws IOException {
		
		/**** 이전 첨부파일삭제 ****/
		String filename = articleDao.getFileName(avo);
		if(filename != null) {
			File deleteFile = new File(uploadPath + filename);
			if(!deleteFile.exists()) {
				System.out.println("파일이 존재하지 않습니다.");
			} else {
				deleteFile.delete();
				System.out.println(filename + "파일을 삭제하였습니다.");
			}
		}
		
		/**** 이전 파일삭제 ****/
		
		if( file != null ) {
			/**** 새로운 파일 생성 ****/
			File folder = new File(uploadPath);
			
			// 해당 디렉토리(upload)가 없을 경우 디렉토리를 생성합니다.
			if( !folder.exists() ) {
				try {
					folder.mkdir(); // 폴더 생성합니다.
					logger.info("폴더가 생성되었습니다.");
				}
				catch(Exception e ) {
					e.getStackTrace();
				}
			}
		
			/**** 파일정보 ****/
			String originFileName = file.getOriginalFilename();
//			String extName = originFileName.substring(originFileName.lastIndexOf("."), originFileName.length());
//			Long size = file.getSize();
			
			/**** 서버에서 저장 할 파일 이름 ****/
			String saveFileName = genSaveFileName(originFileName);
			
//			System.out.println("originFileName : " + originFileName);
//			System.out.println("extensionName : " + extName);
//			System.out.println("size : " + size);
//			System.out.println("saveFileName : " + saveFileName);
			
			writeFile(file, saveFileName);
			String url = PREFIX_URL + saveFileName;
			
			avo.setArticleFileName(saveFileName);
			avo.setArticleFileOrigin(originFileName);
			avo.setArticleFileUrl(url);
		}else {
			System.out.println("넘어온 파일이 없습니다. [ArticleSrvImpl.articleModify] ");
		}
		
		articleDao.articleModify(avo);
	}
	
	@Override
	public void setArticleReply(ArticleVO avo, MultipartFile file) throws Exception {
		
		if( file.isEmpty()) {
			articleDao.setArticleReply(avo);
		} else {
			/**** 파일정보 ****/
			String originFileName = file.getOriginalFilename();
//			String extName = originFileName.substring(originFileName.lastIndexOf("."), originFileName.length());
//			Long size = file.getSize();
			
			/**** 서버에서 저장 할 파일 이름 ****/
			String saveFileName = genSaveFileName(originFileName);
			
//			System.out.println("originFileName : " + originFileName);
//			System.out.println("extensionName : " + extName);
//			System.out.println("size : " + size);
//			System.out.println("saveFileName : " + saveFileName);
			
			writeFile(file, saveFileName);
			String url = PREFIX_URL + saveFileName;
			
			avo.setArticleFileName(saveFileName);
			avo.setArticleFileOrigin(originFileName);
			avo.setArticleFileUrl(url);
		
			articleDao.setArticleReply(avo);
		}
	}

	// UUID를 이용한 중복 방지 파일 이름 생성
	private String genSaveFileName(String originFileName) {
		UUID uid = UUID.randomUUID();
		String fileName = uid.toString() + "_" + originFileName;
		
		return fileName;
	}
	
	// 파일을 실제로 write 하는 메서드
	private boolean writeFile(MultipartFile file, String saveFileName) throws IOException {
		boolean result = false;
		
		byte[] data = file.getBytes();
		FileOutputStream fos = new FileOutputStream(uploadPath + "/" + saveFileName);
		fos.write(data);
		fos.close();
		
		return result;
	}
}
