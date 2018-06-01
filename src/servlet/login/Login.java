package servlet.login;

import static parameter.ButtonNames.*;
import static parameter.ContentPaths.*;
import static parameter.ErrorMessages.*;
import static parameter.InfoMessages.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.LoginBean;
import bean.LoginSession;
import dao.LoginDAO;
import dao.SIMSDAO;
import exception.DatabaseException;
import exception.SystemException;
import validator.SyainValidators;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//DBコネクションプールを作成
	@Override
	public void init() throws ServletException {
		try {
			new SIMSDAO().init();
		} catch (SystemException | DatabaseException e) {
			e.printStackTrace();
		}
	}

	//ログイン画面を表示
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("msg", LOGIN);
		getServletContext().getRequestDispatcher(LOGIN_JSP).forward(request, response);
	}

	//クリア
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String button = request.getParameter("button");

		if (LOGIN_BTN.equals(button)) {

			String syainNo = request.getParameter("syainNo");
			String password = request.getParameter("password");

			LoginBean loginBean = new LoginBean();
			loginBean.setSyainNo(syainNo);
			loginBean.setPassword(password);
			loginBean.setErrMsgSyainNo(SyainValidators.checkSyainNo(syainNo));
			loginBean.setErrMsgPassword(SyainValidators.checkPassword(password));

			if (loginBean.isErrFlg()) {
				request.setAttribute("loginBean", loginBean);
				getServletContext().getRequestDispatcher(LOGIN_JSP).forward(request, response);
				return;
			}

			LoginSession loginSession = null;
			try {
				LoginDAO loginDAO = new LoginDAO();
				loginSession = loginDAO.getLoginSession(loginBean);
			} catch (SystemException | DatabaseException e) {
				//ログイン前エラー画面に行く
				e.printStackTrace();
				request.setAttribute("exMsg", e.getMessage());
				getServletContext().getRequestDispatcher(LOGIN_ERROR_JSP).forward(request, response);
				return;
			}

			//ログイン画面に行く
			if (!loginSession.isLoginFlg()) {
				loginBean.setErrMsgSyainNo(LOGIN_PARAM_MISTAKE);
				request.setAttribute("loginBean", loginBean);
				getServletContext().getRequestDispatcher(LOGIN_JSP).forward(request, response);
				return;
			}

			//トップページに行く
			HttpSession session = request.getSession();
			session.setAttribute("loginSession", loginSession);
			getServletContext().getRequestDispatcher(TOP_JSP).forward(request, response);

		} else {
			//クリア
			request.setAttribute("loginBean", new LoginBean());
			getServletContext().getRequestDispatcher(LOGIN_JSP).forward(request, response);
		}
	}
}
