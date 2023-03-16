package kh.spring.s03.board.model.vo;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@ToString
public class BoardFileVo {
	private String originalFilename;
	private String renameFilename;
}
