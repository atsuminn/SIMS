//ログイン情報管理

package bean;

import java.io.Serializable;

import common.StringCommon;

public class LoginBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String syainNo; // 社員番号
	private String password; // パスワード
	private boolean errFlg; // 入力エラーフラグ
	private String errMsgSyainNo; // 社員番号エラーメッセージ
	private String errMsgPassword; // パスワードエラーメッセージ

	//初期化
	public LoginBean() {
		setSyainNo("");
		setPassword("");
		setErrFlg(false);
		setErrMsgSyainNo("");
		setErrMsgPassword("");
	}

	public String getSyainNo() {
		return syainNo;
	}

	public void setSyainNo(String syainNo) {
		this.syainNo = StringCommon.cnvNull(syainNo);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = StringCommon.cnvNull(password);
	}

	public boolean isErrFlg() {
		return errFlg;
	}

	public void setErrFlg(boolean errFlg) {
		this.errFlg = errFlg;
	}

	public String getErrMsgSyainNo() {
		return errMsgSyainNo;
	}

	public void setErrMsgSyainNo(String errMsgSyainNo) {
		if (!errMsgSyainNo.isEmpty()) {
			this.errFlg = true;
		}
		this.errMsgSyainNo = errMsgSyainNo;
	}

	public String getErrMsgPassword() {
		return errMsgPassword;
	}

	public void setErrMsgPassword(String errMsgPassword) {
		if (!errMsgPassword.isEmpty()) {
			this.errFlg = true;
		}
		this.errMsgPassword = errMsgPassword;
	}
}
