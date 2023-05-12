package ibf2022.batch2.csf.backend.repositories;

import java.time.LocalDate;

import java.util.UUID;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import ibf2022.batch2.csf.backend.models.Record;

@Repository
public class ArchiveRepository {
	@Autowired
	private MongoTemplate template;

	//TODO: Task 4
	// You are free to change the parameter and the return type
	// Do not change the method's name
	// Write the native mongo query that you will be using in this method
	//
	//
	private static final String ARCHIVES_COL = "archives";
	private static final String S3_URL = "https://ibfb2csf-assessment.sgp1.digitaloceanspaces.com/";

	// db.archives.insert({
	// 	"bundleId": <bundleId>,
	// 	"date": <upload date>,
	// 	"title": <title>
	// 	"name": <name from the request>
	// 	"comments": <comments>
	// 	"urls": [<url>]
	// })

	public Record recordBundle(MultipartFile file, String name, 
	String comments, String title) {
		Record r = new Record();
		r.setBundleId(UUID.randomUUID().toString()
					.substring(0, 8));
		r.setDate(LocalDate.now().toString());
		r.setTitle(title);
		r.setName(name);
		r.setComments(comments);
		r.setUrls(S3_URL + file.getOriginalFilename());
		return this.template.insert(r, ARCHIVES_COL);
	}

	//TODO: Task 5
	// You are free to change the parameter and the return type
	// Do not change the method's name
	// Write the native mongo query that you will be using in this method
	//
	//
	// db.archives.find(
	// 	{ bundleId: <bundleId> },
	// 	{ _id: 0, date: 1, title: 1, name: 1, comments: 1, urls: 1 })
	public Object getBundleByBundleId(String bundleId) {
		Query q = new Query().addCriteria(Criteria.where("bundleId").is(bundleId));
		q.fields().exclude("_id")
			.include("date", "title", "name", "comments", "urls");
		return template.find(q, Document.class, ARCHIVES_COL);
	}

	//TODO: Task 6
	// You are free to change the parameter and the return type
	// Do not change the method's name
	// Write the native mongo query that you will be using in this method
	//
	//
	public Object getBundles(/* any number of parameters here */) {
		return null;
	}


}
