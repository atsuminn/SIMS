package parameter;

import static parameter.RoleNames.*;

import java.util.HashMap;
import java.util.Map;

public class AccessControlMap {

	private AccessControlMap() {
	}

	private static final String TOP = "/Top";// トップ画面

	//社員情報
	private static final String SYAIN_TOP = "/SyainTop";
	private static final String SYAIN_LIST = "/SyainList";
	private static final String SYAIN_ENTRY = "/SyainEntry";
	private static final String SYAIN_UPDATE = "/SyainUpdate";
	private static final String SYAIN_DELETE = "/SyainDelete";

	//スキル
	private static final String SKILL_ENTRY = "/SkillEntry";

	//社員ロール
	private static final String ROLE_TOP = "/RoleTop";
	private static final String ROLE_LIST = "/RoleList";
	private static final String ROLE_UPDATE = "/RoleUpdate";

	private static final Map<String, Boolean> ALL_ACCESS = new HashMap<String, Boolean>() {
		{
			put(ADMIN, true);
			put(MANAGER, true);
			put(USER, true);
		}
	};

	//部長以上アクセス可
	private static final Map<String, Boolean> MORE_MANEGER_ACCESS = new HashMap<String, Boolean>() {
		{
			put(ADMIN, true);
			put(MANAGER, true);
			put(USER, false);
		}
	};

	//管理者
	private static final Map<String, Boolean> ONLY_ADMIN_ACCESS = new HashMap<String, Boolean>() {
		{
			put(ADMIN, true);
			put(MANAGER, false);
			put(USER, false);
		}
	};

	//アクセス制御マップ
	public static final Map<String, Map<String, Boolean>> ACCESS_CONTROL_MAP = new HashMap<String, Map<String, Boolean>>() {
		{
			put(TOP, ALL_ACCESS);
			put(SYAIN_TOP, ALL_ACCESS);
			put(SYAIN_LIST, ALL_ACCESS);
			put(SYAIN_ENTRY, MORE_MANEGER_ACCESS);
			put(SYAIN_UPDATE, MORE_MANEGER_ACCESS);
			put(SYAIN_DELETE, ONLY_ADMIN_ACCESS);
			put(SKILL_ENTRY, MORE_MANEGER_ACCESS);
			put(ROLE_TOP, ONLY_ADMIN_ACCESS);
			put(ROLE_LIST, ONLY_ADMIN_ACCESS);
			put(ROLE_UPDATE, ONLY_ADMIN_ACCESS);
		}
	};
}
