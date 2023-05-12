package ibf2022.batch2.csf.backend.repositories;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Repository
public class ImageRepository {

	@Autowired
	private AmazonS3 s3Client;

	@Value("${DO_STORAGE_BUCKETNAME}")
	private String bucketName;

	//TODO: Task 3
	// You are free to change the parameter and the return type
	// Do not change the method's name
	public String upload(MultipartFile file, String name, 
		String comments, String title) throws IOException {
			Map<String, String> userData = new HashMap<>();
			userData.put("name", name);
			userData.put("date", LocalDate.now().toString());
			userData.put("comments", "comments");
			userData.put("title", title);
			userData.put("originalFileName", file.getOriginalFilename());

			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentType(file.getContentType());
			metadata.setContentLength(file.getSize());
			metadata.setUserMetadata(userData);

			// String fileExt = FilenameUtils.getExtension(file.getOriginalFilename());
			String fileName  = file.getOriginalFilename();
			// 				+ "." + fileExt;
			// userData.get("originalFileName");
		
			PutObjectRequest putRequest = 
				new PutObjectRequest(bucketName, fileName, 
					file.getInputStream() ,metadata);
			putRequest.withCannedAcl(CannedAccessControlList.PublicRead);
			s3Client.putObject(putRequest);
			
			return fileName;

	}
}
