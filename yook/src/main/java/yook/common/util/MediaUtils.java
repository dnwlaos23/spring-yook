package yook.common.util;

import org.springframework.http.MediaType; 
import java.util.HashMap; 
import java.util.Map;

public class MediaUtils { 
	
	private static Map<String, MediaType> mediaMap;
	
	// meidaMap�� �̹���Ȯ���ڸ� ���� MINEType ���� 
	static { 
		mediaMap = new HashMap<String, MediaType>(); 
		mediaMap.put("JPG", MediaType.IMAGE_JPEG); 
		mediaMap.put("GIF", MediaType.IMAGE_GIF); 
		mediaMap.put("PNG", MediaType.IMAGE_PNG); 
	} 
	
	public static MediaType getMediaType(String type) { 
		// �̹��� MINEType ������ ��ȯ, �̹��� ������ �ƴ� ��� null ��ȯ 
		return mediaMap.get(type.toUpperCase()); 
	}
}

