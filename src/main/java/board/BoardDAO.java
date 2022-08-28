package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import member.MemberVO;
import util.JDBCUtil;

public class BoardDAO {
	Connection conn;
	PreparedStatement pstmt;
	//final String sql_selectAll="SELECT * FROM BOARD ORDER BY BID DESC LIMIT 0,?";
	//전체 게시글 
	final String sql_selectAll = "SELECT * FROM (SELECT * FROM  BOARD ORDER BY BID DESC) WHERE ROWNUM <=?";
	//final String sql_selectAllB = "SELECT * FROM BOARD WHERE MID = ? ORDER BY BID DESC LIMIT 0,?";
	//회원가입
	final String sql_selectAllB = "SELECT * FROM BOARD WHERE MID = ? AND ROWNUM <= ? ORDER BY BID DESC";
	//회원탈퇴용 - 작성 게시글 
	final String sql_selectOneB = "SELECT * FROM BOARD WHERE MID=?";
	//회원탈퇴용 - 작성 댓글 
	final String sql_selectOneR = "SELECT * FROM REPLY WHERE MID=?";
	//내가 작성한글 확인 - 내가쓴글만 보려면 rownum은 필요없음, 내가쓴글에서 더보기를 누를때에도 내가쓴글이 나오게 하기위해/ 누가쓴글인지 구분하기위해 mid 
	final String sql_selectAll_M = "SELECT * FROM (SELECT * FROM BOARD WHERE MID = ? ORDER BY BID DESC) WHERE ROWNUM <=?";
	//LIMIT은 MYSQL
	// Oracle 은 ROWNUM 을 사용함 (※ SQL문 실행순서 유의!)
	// 게시물에 해당된 댓글 
	final String sql_selectAll_R = "SELECT *FROM REPLY WHERE BID=? ORDER BY RID DESC"; 
	//final String sql_insert = "INSERT INTO BOARD(MID,MSG) VALUES(?,?)";
	// 게시물 작성 
	final String sql_insert = "INSERT INTO BOARD (BID,MID,MSG) VALUES((SELECT NVL(MAX(BID),0)+1 FROM BOARD),?,?)";//게시글 등록  
	//final String sql_insert_R = "INSERT INTO REPLY(MID,BID,RMSG) VALUES(?,?,?)";
	// 댓글 작성 
	final String sql_insert_R = "INSERT INTO REPLY VALUES((SELECT NVL(MAX(RID),0)+1 FROM REPLY),?,?,?)"; //댓글 등록 
	// 게시물 삭제 
	final String sql_delete = "DELETE FROM BOARD WHERE BID=?"; //게시글삭제
	// 댓글 삭제 
	final String sql_delete_R = "DELETE FROM REPLY WHERE RID=?"; // 댓글 삭제 
	// 좋아요 
	final String sql_update = "UPDATE BOARD SET FAVCNT=FAVCNT+1 WHERE BID=?"; 

	public BoardVO selectOneB(BoardVO vo) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_selectOneB);
			pstmt.setString(1, vo.getMid());
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				BoardVO data=new BoardVO();
				data.setMid(rs.getString("MID"));
				return data;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}      
		return null;
	}
	public ReplyVO selectOneR(ReplyVO vo) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_selectOneR);
			pstmt.setString(1, vo.getMid());
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				ReplyVO data=new ReplyVO();
				data.setMid(rs.getString("MID"));
				return data;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}      
		return null;
	}

	public boolean insert(BoardVO bvo) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_insert);
			pstmt.setString(1, bvo.getMid());
			pstmt.setString(2, bvo.getMsg());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}

	public boolean insertR(ReplyVO rvo) { // 댓글 등록 
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_insert_R);
			pstmt.setString(1, rvo.getMid()); //회원 pk
			pstmt.setInt(2, rvo.getBid()); //게시판 pk
			pstmt.setString(3, rvo.getRmsg()); // 댓글 내용
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}

	public boolean delete(BoardVO bvo) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_delete);
			pstmt.setInt(1, bvo.getBid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}

	public boolean deleteR(ReplyVO rvo) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_delete_R);
			pstmt.setInt(1, rvo.getRid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}

	public boolean update(BoardVO bvo) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_update);
			pstmt.setInt(1, bvo.getBid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}

	public ArrayList<BoardSet> selectAll(BoardVO bvo) { // 유지보수 용이
		ArrayList<BoardSet> datas = new ArrayList<BoardSet>();
		conn = JDBCUtil.connect();
		try {
			if(bvo.getMid() == null) {  // 전체 게시글 확인
				pstmt = conn.prepareStatement(sql_selectAll);
				pstmt.setInt(1, bvo.getCnt()); // 글 1개당 댓글 cnt개
			}
			else { // 내가 쓴 글 확인
				pstmt = conn.prepareStatement(sql_selectAll_M);
				pstmt.setString(1, bvo.getMid());
				pstmt.setInt(2, bvo.getCnt()); // 글 1개당 댓글 cnt개
			}

			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardSet bs = new BoardSet();

				BoardVO boardVO = new BoardVO();
				boardVO.setBid(rs.getInt("bid"));
				boardVO.setFavcnt(rs.getInt("favcnt")); 
				boardVO.setMid(rs.getString("mid"));
				boardVO.setMsg(rs.getString("msg"));
				boardVO.setRcnt(rs.getInt("rcnt")); // rList.size(); --> 서버 비용 절감
				bs.setBoardVO(boardVO);

				ArrayList<ReplyVO> rList = new ArrayList<>();
				pstmt = conn.prepareStatement(sql_selectAll_R);
				pstmt.setInt(1, rs.getInt("bid"));
				ResultSet rs2 = pstmt.executeQuery();
				while(rs2.next()) {
					ReplyVO replyVO = new ReplyVO();

					replyVO.setBid(rs2.getInt("bid"));
					replyVO.setMid(rs2.getString("mid"));
					replyVO.setRid(rs2.getInt("rid"));
					replyVO.setRmsg(rs2.getString("rmsg"));
					rList.add(replyVO);
				}
				boardVO.setRcnt(rList.size());

				bs.setrList(rList);
				datas.add(bs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return datas;
	}
	public ArrayList<BoardSet> selectB(MemberVO mvo,BoardVO bvo){
		ArrayList<BoardSet> datas=new ArrayList<BoardSet>();
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_selectAllB);
			pstmt.setString(1, mvo.getMid());
			pstmt.setInt(2, bvo.getCnt());
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				BoardSet bs=new BoardSet();
				
				BoardVO boardVO=new BoardVO();
				boardVO.setBid(rs.getInt("BID"));
				boardVO.setMid(rs.getString("MID"));
				boardVO.setMsg(rs.getString("MSG"));
				boardVO.setFavcnt(rs.getInt("FAVCNT"));
				boardVO.setRcnt(rs.getInt("RCNT"));
				
				bs.setBoardVO(boardVO);
				ArrayList<ReplyVO> rList=new ArrayList<ReplyVO>();
				pstmt=conn.prepareStatement(sql_selectAll_R);
				pstmt.setInt(1, rs.getInt("BID")); // 현재 BID
				ResultSet rs2=pstmt.executeQuery();
				while(rs2.next()) {
					ReplyVO replyVO=new ReplyVO();

					replyVO.setBid(rs2.getInt("BID"));
					replyVO.setMid(rs2.getString("MID"));
					replyVO.setRid(rs2.getInt("RID"));
					replyVO.setRmsg(rs2.getString("RMSG"));

					rList.add(replyVO);
				}
				bs.setrList(rList);				

				datas.add(bs);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return datas;
	}

}
