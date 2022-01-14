package com.myspring.pro30.board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.pro30.board.vo.ArticleVO;

public interface BoardDAO {
	//글목록
	public List selectAllArticlesList() throws DataAccessException;
	//새글
	public int insertNewArticle(Map articleMap) throws DataAccessException;
	public void insertNewImage(Map articleMap) throws DataAccessException;
	//특정목록
	public ArticleVO selectArticle(int articleNO) throws DataAccessException;
	//수정
	public void updateArticle(Map articleMap) throws DataAccessException;
	//삭제
	public void deleteArticle(int articleNO) throws DataAccessException;
	//이미지 목록
	public List selectImageFileList(int articleNO) throws DataAccessException;
}
