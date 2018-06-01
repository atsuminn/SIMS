package validator;

import static parameter.ErrorMessages.*;
import static parameter.SyainParameters.*;

public class SyainValidators {

	// 正規表現
	private static final String MATCH_HANKAKUEISUU = "^[a-zA-Z0-9]+$";

	/** @param syainNo
	 * @return エラーメッセージ */
	public static String checkSyainNo(String syainNo) {
		if (syainNo.isEmpty()) {
			return SYAINNO_REQUIRED;
		} else if (syainNo.length() != 5) {
			return SYAINNO_DIGIT;
		} else if (!syainNo.matches(MATCH_HANKAKUEISUU)) {
			return SYAINNO_DIGIT;
		} else {
			return "";
		}
	}

	/** @param syainName
	 * @return エラーメッセージ */
	public static String checkSyainName(String syainName) {
		if (syainName.isEmpty()) {
			return SYAINNAME_REQUIRED;
		} else if (syainName.length() > 25) {
			return SYAINNAME_DIGIT;
		} else {
			return "";
		}
	}

	/** @param password
	 * @return エラーメッセージ */
	public static String checkPassword(String password) {
		if (password.isEmpty()) {
			return PASSWORD_REQUIRED;
		} else if (password.length() < 4 || password.length() > 10) {
			return PASSWORD_DIGIT;
		} else if (!password.matches(MATCH_HANKAKUEISUU)) {
			return PASSWORD_DIGIT;
		} else {
			return "";
		}
	}

	/** @param status
	 * @return エラーメッセージ */
	public static String checkStatus(String status) {
		if (!NOT_RETIRE.equals(status) && !RETIRE.equals(status)) {
			return STATUS_REQUIRED;
		} else {
			return "";
		}
	}
}
