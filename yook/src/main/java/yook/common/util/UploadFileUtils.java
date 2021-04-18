package yook.common.util;

import org.imgscalr.Scalr; 
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import org.springframework.http.MediaType; 
import org.springframework.util.FileCopyUtils; 
import javax.imageio.ImageIO; 
import java.awt.*; 
import java.awt.image.BufferedImage; 
import java.io.File; 
import java.text.DecimalFormat; 
import java.util.Calendar; 
import java.util.UUID; 

public class UploadFileUtils { 
	private static final Logger logger = LoggerFactory.getLogger(UploadFileUtils.class); 
	// ���� ���ε� ó�� 
	public static String uploadFile(String uploadPath, String originalName, byte[] fileData) throws Exception { 
		// �ߺ��� �̸��� ������ �������� �ʱ� ���� UUID Ű�� ���� 
		UUID uuid = UUID.randomUUID(); 
		// ���� ������ ���ϸ� = UUID + _ + �������ϸ� 
		String savedName = uuid.toString() + "_" + originalName; 
		// ��¥ ��� = �� + �� + �� 
		String savedPath = calcPath(uploadPath); 
		// ���� ��ü ���� = �⺻ ������ + ��¥��� + UUID_���ϸ� 
		File target = new File(uploadPath + savedPath, savedName); 
		// fileData�� ���ϰ�ü�� ���� 
		FileCopyUtils.copy(fileData, target); 
		// ���� Ȯ���� ���� 
		String formatName = originalName.substring(originalName.lastIndexOf(".") + 1); 
		// ���ε� ���ϸ� : ����� �̹��� ���ϸ� or �Ϲ� ���ϸ� 
		String uploadFileName = null; 
		// Ȯ���ڿ� ���� ����� �̹��� ���� or �Ϲ� ���� ������ ���� 
		if (MediaUtils.getMediaType(formatName) != null) { 
			// ����� �̹��� ����, ����� �̹��� ���ϸ� 
			uploadFileName = makeThumbnail(uploadPath, savedPath, savedName); 
		} else { 
			// ���� ������ ����, 
			uploadFileName = makeIcon(uploadPath, savedPath, savedName); 
		} 
		// ���ε� ���ϸ� ��ȯ 
		return uploadFileName; 
	} 
	
	// 1. ��¥�� ��� ���� 
	private static String calcPath(String uploadPath) { 
		Calendar calendar = Calendar.getInstance(); 
		// �� 
		String yearPath = File.separator + calendar.get(Calendar.YEAR); 
		// �� + �� 
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(calendar.get(Calendar.MONTH) + 1); 
		// �� + �� + �� 
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(calendar.get(Calendar.DATE)); 
		// ���� ���� �⺻ ��� + ��¥ ��� ���� 
		makeDir(uploadPath, yearPath, monthPath, datePath); 
		// ��¥ ��� ��ȯ 
		return datePath; 
	} 
	
	// 2. ���� ���� �⺻ ��� + ��¥ ��� ���� 
	private static void makeDir(String uploadPath, String... paths) { 
		// �⺻ ��� + ��¥ ��ΰ� �̹� ���� : �޼��� ���� 
		if (new File(uploadPath + paths[paths.length - 1]).exists()) { 
			return; 
		} 
		// ��¥ ��ΰ� ���� X : ��� ������ ���� �ݺ��� ���� 
		for (String path : paths) { 
			// �⺻ ��� + ��¥ ��ο� �ش��ϴ� ���� ��ü ���� 
			File dirPath = new File(uploadPath + path); 
			// ���� ��ü�� �ش��ϴ� ��ΰ� ���� X 
			if (!dirPath.exists()) { 
				// ��� ���� 
				dirPath.mkdir(); 
			} 
		} 
	} 
	
	// 3. ����� ���� : �̹��� ������ ��� 
	private static String makeThumbnail(String uploadPath, String path, String fileName) throws Exception { 
		// BufferedImage : ���� �̹��� X, �޸𸮻��� �̹����� �ǹ��ϴ� ��ü 
		// ���� �̹��������� �޸𸮿� �ε� 
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path, fileName)); 
		// ������ ũ�⿡ �°� �����̹����� ��� 
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100); 
		// ����� �̹��� ���ϸ� 
		String thumbnailName = uploadPath + path + File.separator + "s_" + fileName; 
		// ����� �̹��� ���� ��ü ���� 
		File newFile = new File(thumbnailName); 
		// ���� Ȯ���� ���� 
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1); 
		// ����� ���� ���� 
		ImageIO.write(destImg, formatName.toUpperCase(), newFile); 
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/'); 
	} 
	
	// 4. ������ ���� : �̹��� ������ �ƴ� ��� 
	private static String makeIcon(String uploadPath, String savedPath, String fileName) throws Exception { 
		// ������ ���ϸ� = �⺻ ������ + ��¥��� + ������ + ���ϸ� 
		String iconName = uploadPath + savedPath + File.separator + fileName; 
		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/'); 
	} 
	
	// ���� ����ó�� �޼��� 
	public static void removeFile(String uploadPath, String fileName) { 
		// ���� Ȯ���� ���� 
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1); 
		// ���� Ȯ���ڸ� ���� �̹��� �������� �Ǻ� 
		MediaType mediaType = MediaUtils.getMediaType(formatName); 
		// �̹��� ������ ���, �������� ���� 
		if (mediaType != null) { 
			// ���� �̹����� ��� + ���ϸ� ���� 
			// ��¥ ��� ���� 
			String front = fileName.substring(0, 12); 
			// UUID + ���ϸ� ���� 
			String end = fileName.substring(14); 
			// ���� �̹��� ���� ����(������ ��ȯ) 
			new File(uploadPath + (front + end).replace('/', File.separatorChar)).delete(); 
		} 
		// ���� ����(�Ϲ� ���� or ����� �̹��� ���� ����) 
		new File(uploadPath + fileName.replace('/', File.separatorChar)).delete(); 
		}    
	}
			
		
	


