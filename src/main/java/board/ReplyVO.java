package board;

public class ReplyVO {
	private int rid; // 댓글 pk
	private String mid; // 회원 pk
	private int bid; // 게시판 pk
	private String rmsg; //댓글메세지
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getRmsg() {
		return rmsg;
	}
	public void setRmsg(String rmsg) {
		this.rmsg = rmsg;
	}
	@Override
	public String toString() {
		return "ReplyVO [rid=" + rid + ", mid=" + mid + ", bid=" + bid + ", rmsg=" + rmsg + "]";
	}
}
