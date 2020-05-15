package co.yedam.app.member.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import co.yedam.app.common.ConnectionManager;

public class MemberService {

	// 싱글톤
	private static MemberService instance = new MemberService();

	public static MemberService getInstance() {
		return instance;
	}

	// 회원가입
	public void memberInsert(MemberVO member) {

		Connection conn = null;
		try {
			conn = ConnectionManager.getConnnect();
			conn.setAutoCommit(false);

			// 회원테이블에 등록
			MemberDAO.getInstance().memberinsert(conn, member);

			// 로그인 테이블에 등록

			// 권한 테이블에 등록
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public ArrayList<MemberVO> getMemberList() {
		Connection conn = null;
		try {
			conn = ConnectionManager.getConnnect();
			return MemberDAO.getInstance().getMemberList(conn);
		} finally {
		}
	}

	public MemberVO getMember(String id) {
		// TODO Auto-generated method stub
		return null;
	}
}
