package kh.spring.s03.board.model.vo;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class BoardVo {
	private int boardNum;
	private String boardTitle;
	private String boardWriter;
	private String boardContent;
	private String boardOriginalFilename;
	private String boardRenameFilename;
//	private BoardFileVo boardMainFile; //어소씨에이션쓸때 사용하는 것~! 
	
	private Date boardDate;
	private int boardLevel; // 
	private int boardRef;	// 답글을 작성하고자하는 4번글이 속하는 ref를 넣어줘야함 
	private int boardReplySeq;
	private int boardReadcount;
	
	private List<BoardFileVo> boardFileList;
	

}
