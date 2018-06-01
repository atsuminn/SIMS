package dao;

import static parameter.ExceptionMessages.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import exception.DatabaseException;
import exception.SystemException;

public class SIMSDAO {

	private static DataSource dataSource = null;
	private static final String JNDI_NAME = "java:comp/env/jdbc/oracle";
	Connection connection = null;

	/** コネクションプール作成
	 *
	 * @throws DatabaseException */
	public void init() throws DatabaseException, SystemException {
		try {
			getDataSource();
		} catch (NamingException e) {
			throw new SystemException(SYSTEM_EXCEPTION, e);
		}
	}

	/** プールからコネクション取得
	 *
	 * @throws DatabaseException
	 * @throws SystemException */
	public void open() throws DatabaseException, SystemException {
		try {
			getDataSource();
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
		} catch (NamingException e) {
			throw new SystemException(SYSTEM_EXCEPTION, e);
		} catch (SQLException e) {
			throw new DatabaseException(DB_CONNECT_EXCEPTION, e);
		}
	}

	/** statementとconnectionのクローズ処理。
	 *
	 * @param statement
	 * @throws DatabaseException */
	protected void close(Statement statement) throws DatabaseException {
		try {
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			throw new DatabaseException(DB_CLOSE_EXCEPTION, e);
		}
	}

	/** DBコミット
	 *
	 * @param conn Connection */
	protected void commit(Connection connection) {
		if (connection != null) {
			try {
				connection.commit();
			} catch (SQLException e) {
				while (e != null) {
					System.err.println("@Err@SQLException DBConnectUtil commit");
					System.err.println("[" + e.getErrorCode() + "]");
					System.err.println(e.getMessage());
					System.err.println(e.getSQLState());
					System.err.println();
					e = e.getNextException();
				}
			} catch (Exception e) {
				System.err.println("@Err@Exception DBConnectUtil commit");
				e.printStackTrace();
			}
		}
	}

	/** DBロールバック
	 *
	 * @param connection Connection */
	protected void rollback(Connection connection) {
		if (connection != null) {
			try {
				connection.rollback();
			} catch (SQLException e) {
				while (e != null) {
					System.err.println("@Err@SQLException DBConnectUtil rollback");
					System.err.println("[" + e.getErrorCode() + "]");
					System.err.println(e.getMessage());
					System.err.println(e.getSQLState());
					System.err.println();
					e = e.getNextException();
				}
			} catch (Exception e) {
				System.err.println("@Err@Exception DBConnectUtil rollback");
				e.printStackTrace();
			}
		}
	}

	/** スレッドに排他制御、プール取得。ないならつくる
	 *
	 * @throws NamingException */
	private synchronized void getDataSource() throws NamingException {
		if (dataSource == null) {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup(JNDI_NAME);
		}
	}
}
