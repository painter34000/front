package co.yedam.app.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import co.yedam.app.common.ConnectionManager;

public class MemberDAO {

	
	
	//싱글톤
	private static MemberDAO instance = new MemberDAO();
	public static MemberDAO getInstance () {
		return instance;
	}
	
	
	
	public int memberinsert(Connection conn, MemberVO member) {
		int r = 0;
		
		PreparedStatement psmt = null;
		
		try {
			// 1. DB 연결

			// 2. sql구문 준비
			String sql = "insert into member (id, pwd, name, hobby, gender, religion, introduction, regdt)"
					+ " values ( ?, ?, ?, ?, ?, ?, ?, sysdate)";

			psmt = conn.prepareStatement(sql);

			// 3. 실행
			psmt.setString(1, member.getId());
			psmt.setString(2, member.getPwd());
			psmt.setString(3, member.getName());
			psmt.setString(4, member.getHobby());
			psmt.setString(5, member.getGender());
			psmt.setString(6, member.getReligion());
			psmt.setString(7, member.getIntroduction());

			r = psmt.executeUpdate();

			// 4. 결과처리
			System.out.println(r + " 건이 등록됨.");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 5. 연결해제
			ConnectionManager.close(conn);
		}

		return r;
	}

	//단건조회 (수정페이지로 이동시 호출)
	public MemberVO getMember(String id) {
		Connection conn = null;
		try {
			conn = ConnectionManager.getConnnect();
			return MemberDAO.getInstance().getMember(conn, id);
		}finally {
			ConnectionManager.close(conn);
		}
	}

	
	
	//단건 조회
	public MemberVO getMember(Connection conn, String id) {
		MemberVO vo = new MemberVO();
		PreparedStatement psmt = null;
		try {

			// 1. DB연결

			// 2. 쿼리 준비
			String sql = "select * from member where id = ?";
			psmt = conn.prepareStatement(sql);

			// 3. statement 실행
			psmt.setString(1, id);
			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				vo.setId(rs.getString("id"));
				vo.setGender(rs.getString("gender"));
				vo.setHobby(rs.getString("hobby"));
				vo.setIntroduction(rs.getString("Introduction"));
				vo.setName(rs.getString("Name"));
				vo.setPwd(rs.getString("Pwd"));
				vo.setReligion(rs.getString("religion"));
				vo.setRegdt(rs.getString("regdt"));
			}

			// 4. 결과저장

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 5. 연결해제
		}

		return vo;
	}
		//등록
	public int memberUpdate(Connection conn, MemberVO member) {

		int r = 0;
		PreparedStatement psmt = null;

		try {
			// 1. DB 연결

			// 2. sql구문 준비
			String sql = "update member set pwd=?, name=?," + "hobby=?, gender=?, religion=?," + "introduction=?"
					+ "where id=?";

			psmt = conn.prepareStatement(sql);

			// 3. 실행
			psmt.setString(1, member.getId());
			psmt.setString(2, member.getPwd());
			psmt.setString(3, member.getName());
			psmt.setString(4, member.getHobby());
			psmt.setString(5, member.getGender());
			psmt.setString(6, member.getReligion());
			psmt.setString(7, member.getIntroduction());

			r = psmt.executeUpdate();

			// 4. 결과처리
			System.out.println(r + " 건이 등록됨.");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 5. 연결해제
			ConnectionManager.close(conn);
		}

		return r;
	}
	//전체초회 
	public ArrayList<MemberVO> getMemberList(Connection conn) {
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		PreparedStatement psmt = null;
		try {

			// 1. DB연결

			// 2. 쿼리 준비
			String sql = "select * from member order by id";
			psmt = conn.prepareStatement(sql);

			// 3. statement 실행
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setId(rs.getString("id"));
				vo.setGender(rs.getString("gender"));
				vo.setHobby(rs.getString("hobby"));
				vo.setIntroduction(rs.getString("Introduction"));
				vo.setName(rs.getString("Name"));
				vo.setPwd(rs.getString("Pwd"));
				vo.setReligion(rs.getString("religion"));
				vo.setRegdt(rs.getString("regdt"));
				list.add(vo);
			}

			// 4. 결과저장

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 5. 연결해제
			ConnectionManager.close(conn);
		}

		return list;
	}



	public void memberInsert(MemberVO member) {
		// TODO Auto-generated method stub
		
	}

}
