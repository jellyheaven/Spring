package com.spring.ex01;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MemberDAO {
	private static SqlSessionFactory sqlMapper = null;
	
	//설정가져오기
	public static SqlSessionFactory getInstance() {
		if(sqlMapper == null) {
			String resource = "mybatis/SqlMapconfig.xml";
			try {
				Reader reader = Resources.getResourceAsReader(resource);
				sqlMapper = new SqlSessionFactoryBuilder().build(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		}
		
		return sqlMapper;
	}
	
	
	public List<HashMap<String, String>> selectAllMemberList() {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		List<HashMap<String, String>> memberlist= null;
		memberlist = session.selectList("mapper.member.selectAllMemberList");
		return memberlist;
	}	

}
