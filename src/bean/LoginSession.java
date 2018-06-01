//ログインセッション情報

package bean;

import java.io.Serializable;

public class LoginSession implements Serializable {
	private static final long serialVersionUID = 1L;

	private String syainNo; // 社員番号
	private String syainName;// 社員名
	private String password;// パスワード
	private String role; // ロール
	private boolean loginFlg; //フラグ

	//コンストラクタ（ログインセッション情報の初期化）
	public LoginSession() {
		setSyainNo("");
		setSyainName("");
		setPassword("");
		setRole("");
		setLoginFlg(false);
	}

	public String getSyainNo() {
		return syainNo;
	}

	public void setSyainNo(String syainNo) {
		this.syainNo = syainNo;
	}

	public String getSyainName() {
		return syainName;
	}

	public void setSyainName(String syainName) {
		this.syainName = syainName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isLoginFlg() {
		return loginFlg;
	}

	public void setLoginFlg(boolean loginFlg) {
		this.loginFlg = loginFlg;
	}
}
