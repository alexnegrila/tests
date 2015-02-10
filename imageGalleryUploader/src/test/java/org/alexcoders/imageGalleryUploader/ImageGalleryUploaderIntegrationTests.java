package org.alexcoders.imageGalleryUploader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.alexcoders.imageGalleryUploader.dtos.FileDescription;
import org.alexcoders.imageGalleryUploader.models.ImageDescription;
import org.alexcoders.imageGalleryUploader.services.ImageDescriptionService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.gridfs.GridFSDBFile;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
public class ImageGalleryUploaderIntegrationTests {

	
	@Autowired
	ImageDescriptionService imageDescriptionService;
	
	@Test
	public void fileSaveTest() {
		byte[] content = {1,3,2,3};
		String name1 = "test1.jpg";
		String name2 = "test2.jpg";
		String name3 = "test3.jpg";
		MockMultipartFile mockMultipartFile1 = new MockMultipartFile(name1, content);
		MockMultipartFile mockMultipartFile2 = new MockMultipartFile(name2, content);
		MockMultipartFile mockMultipartFile3 = new MockMultipartFile(name3, content);
		List<FileDescription> fileDescriptions = new ArrayList<FileDescription>();
		FileDescription fileDescription1 = new FileDescription();
		fileDescription1.setAltTag("testTag1");
		fileDescription1.setDefaultName(true);
		fileDescription1.setFile(mockMultipartFile1);
		fileDescriptions.add(fileDescription1 );
		
		FileDescription fileDescription2 = new FileDescription();
		fileDescription2.setFile(mockMultipartFile2);
		fileDescriptions.add(fileDescription2);
		
		FileDescription fileDescription3 = new FileDescription();
		fileDescription3.setName("test.jpg");
		fileDescription3.setFile(mockMultipartFile3);
		fileDescriptions.add(fileDescription3);
		imageDescriptionService.save(fileDescriptions);
		
		Map<ImageDescription, GridFSDBFile> images = imageDescriptionService.getImages(fileDescriptions);
		boolean found1 = false;
		boolean found2 = false;
		boolean found3 = false;
		for (ImageDescription imageDescription : images.keySet()) {
			if (imageDescription.getName().equals("testTag1.jpg") && images.get(imageDescription) != null) {
				found1 = true;
			}
			if (imageDescription.getName().equals(name2) && images.get(imageDescription) != null) {
				found2 = true;
			}
			if (imageDescription.getName().equals("test.jpg") && images.get(imageDescription) != null) {
				found3 = true;
			}
		}
		Assert.assertTrue("File1 not saved", found1);
		Assert.assertTrue("File2 not saved", found2);
		Assert.assertTrue("File3 not saved", found3);
	}
	

}