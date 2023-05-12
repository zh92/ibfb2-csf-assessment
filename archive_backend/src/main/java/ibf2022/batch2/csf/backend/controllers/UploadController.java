package ibf2022.batch2.csf.backend.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import ibf2022.batch2.csf.backend.models.Record;
import ibf2022.batch2.csf.backend.repositories.ArchiveRepository;
import ibf2022.batch2.csf.backend.repositories.ImageRepository;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@Controller
public class UploadController {

	@Autowired
	private ImageRepository imgRepo;
	@Autowired
	private ArchiveRepository archiveRepo;
	// TODO: Task 2, Task 3, Task 4
	@PostMapping(path="/upload",
				consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
				produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> upload(
		@RequestPart MultipartFile file,
		@RequestPart String name,
		@RequestPart String comments,
		@RequestPart String title
	){
		//String key = "";
		Record rr = null;
		
		try {
			this.imgRepo.upload(file, name, comments, title);
			rr = this.archiveRepo.recordBundle(file, name, comments, title);
		}catch(IOException e) {
			return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(e.getMessage());
		}
		String bundleId = rr.getBundleId();
		JsonObject payload = Json.createObjectBuilder()
			.add("bundleId", bundleId)
			.build();

		return ResponseEntity.ok(payload.toString());
	}

	// TODO: Task 5
	@GetMapping(path="/bundle/{bundleId}",
				produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> getBundleByBundleId(@PathVariable String bundleId){
		Object r = archiveRepo.getBundleByBundleId(bundleId);
		return ResponseEntity
			.status(HttpStatus.OK)
			.contentType(MediaType.APPLICATION_JSON)
			.body(r.toString());
	}

	// TODO: Task 6

}
