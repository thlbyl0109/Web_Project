package mypage;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsplec.command.command;

import DAO.aItemDAO;
import DAO.listDAO;
import DTO.aItemDTO;
import DTO.listDTO;

public class UserListPagingCommand implements command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		// TODO Auto-generated method stub
		String nowID = (String) session.getAttribute("mId");
		listDAO listdao1 = new listDAO();
		ArrayList<listDTO> dtos1 = listdao1.postlist(request,nowID);
		
		int noticeViewRowCount = listdao1.postlistViewRowCount(nowID);
		int RowCount = noticeViewRowCount;
		
		
		int nowPage = Integer.parseInt(request.getParameter("page"));
		int pageRow=10;
		int beginNum=(nowPage*pageRow)-pageRow;
		int endNum=(nowPage*pageRow)-1;
		int totalPage=(RowCount/pageRow);
		if(RowCount%10!=0) {
			totalPage++;
		}
		
//		for(int i=1;i<=totalPage;i++) {
//			if(nowPage==i) {
//				beginNum=(i*pageRow)-pageRow;
//				endNum=(i*pageRow)-1;
//			}
//		}
		
		
		// 리스트
		request.setAttribute("postlist", dtos1);
		//페이지
		request.setAttribute("beginNum", beginNum);
		request.setAttribute("endNum", endNum);
		request.setAttribute("totalPage", totalPage);
	}

}
