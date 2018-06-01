package dao;

import static parameter.ExceptionMessages.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.LoginBean;
import bean.LoginSession;
import exception.DatabaseException;
import exception.SystemException;

public class LoginDAO extends SIMSDAO {

	/** @param loginBean
	 * @return ログインセッション
	 * @throws DatabaseException
	 * @throws SystemException */
	public LoginSession getLoginSession(LoginBean loginBean) throws DatabaseException, SystemException {
		this.open();
		PreparedStatement preparedStatement = null;
		LoginSession loginSession = new LoginSession();
		try {
			String sql = "SELECT syain.syainno, syain.syainname, syain.password, syain_roles.role FROM syain, syain_roles "
					+ "WHERE syain.syainno = syain_roles.syainno " + "AND syain.syainno = ? AND syain.password = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginBean.getSyainNo());
			preparedStatement.setString(2, loginBean.getPassword());
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				loginSession.setSyainNo(resultSet.getString(1));
				loginSession.setSyainName(resultSet.getString(2));
				loginSession.setPassword(resultSet.getString(3));
				loginSession.setRole(resultSet.getString(4));
				loginSession.setLoginFlg(true);
			}
		} catch (SQLException e) {
			throw new DatabaseException(LOGIN_EXCEPTION, e);
		} finally {
			this.close(preparedStatement);
		}
		return loginSession;
	}
}
