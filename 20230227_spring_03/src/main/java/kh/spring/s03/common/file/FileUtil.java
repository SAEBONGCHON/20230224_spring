package kh.spring.s03.common.file;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Component("fileUtil")
@PropertySource("classpath:properties/khs2.properties")
public class FileUtil {
	
	@Value("${local.repository}")
	private String UPLOAD_FOLDER; //사실 보안상 감춰야하는 경로라 프로퍼티를 사용하는 것이 좋다
	
	
	public List<Map<String, String>> saveFileList(MultipartHttpServletRequest multiReq,
   												HttpServletRequest request,
												String addedPath){
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		
		Iterator<String> iterator = multiReq.getFileNames(); //Name <input name="n1" type="file">
		while(iterator.hasNext()) {
			String name = iterator.next(); //"n1"이 꺼내진다.
			MultipartFile multiFile = multiReq.getFile(name);
			
			Map<String, String> map = new HashMap<String, String>();
			map = saveFile(multiFile, request, addedPath);
			result.add(map);
		}
		return result;
	}
	
	/**
	 * @param multi 
	 * @param request
	 * @return : map - "original" : original file path, "rename" : saved file path
	 */
	public Map<String, String> saveFile(MultipartFile multi, HttpServletRequest request, String addedPath) throws Exception {
		Map<String, String> result = null;
		
		String result = null;
		if(multi != null && !multi.equals("")) {
			result = new HashMap<String, String>();
			
			String originalFileName = multi.getOriginalFilename();

			//file을 server의 특정 위치(저장할 폴더)에 저장
			String webServerRoot = request.getSession().getServletContext().getRealPath("");
			String savePath = webServerRoot + UPLOAD_FOLDER;
			if(addedPath != null) {
				savePath += addedPath;
			}
			// 폴더 생성 : 저장할 폴더 생성되어있지 않다만 생성해줘야함
			File folder = new File(savePath);
			if(!folder.exists()) {
				folder.mkdirs();
			}
			// 파일을 savepath 위치에 저장
			// 시간을 활용한 rename
			RrenameByTime = System.currentTimeMillis()+ "_"+originalFileName;
			// UUID :
//			String renameByUUID = UUID.randomUUID().toString()+ "_"+originalFileName;
			
			renameFilePath = savePath + "\\" + renameByTime;
			multi.transferTo(new File(savePath + "\\" + renameByTime));
			
			result.put("orignal", originalFileName);
			result.put("orignal", originalFileName); //??
		}
		
		return result;
	}
	
}
