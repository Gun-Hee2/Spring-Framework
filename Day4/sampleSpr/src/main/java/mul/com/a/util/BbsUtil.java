package mul.com.a.util;

public class BbsUtil {
	
	// 댓글 깊이와 image를 추가하는 함수
	public static String arrow(int depth){
		String rs = "<img src='./image/arrow.png' width='20px' height='20px'>";
		String nbsp = "&nbsp;&nbsp;&nbsp;&nbsp;";			
		
		String ts = "";
		for(int i = 0;i < depth; i++){
			ts += nbsp;
		}
		
		return depth==0 ? "":ts + rs;
	}
	
	// 제목의 문자열의 길이가 28자를 넘을 때 ...으로 표현
	public static String dot3(String title){
		String str = "";
		if(title.length() >= 30){
			str = title.substring(0, 30);
			str += "...";
		}else{
			str = title;
		}	
		return str;
	}
}
