import java.util.List;


import io.ebean.Ebean;
import model.StoreUser;
import util.PinyinDemo;
import util.StringUtil;

public class Error1 {
	//问题cpu数据飙高
	/**
	 * 获取店员账号
	 * 
	 * @param storeId
	 * @param userName
	 * @return
	 */
	public String getStoreUserAccount(String storeId, String userName) {
		if (StringUtil.isNullOrEmpty(storeId)) {
			return null;
		}
		if (StringUtil.isNullOrEmpty(userName)) {
			return null;
		}
		String accountName = PinyinDemo.ToFirstChar(userName);// 取姓名首字母
		// 查询该门店下店员个数
		int number = Ebean.find(StoreUser.class).where().eq("storeId", storeId).findCount() + 1;
		StoreUser object = null;
		// 判断是否有相同的店员账号
		int i = 1;
		while (i == 1) {
			
			List<StoreUser> objects = Ebean.find(StoreUser.class).where()
					.eq("account", accountName + String.format("%0" + 3 + "d", number)).findList();
			if (objects.size() > 0) {
				object = objects.get(0);
			}
			if (null != object && StringUtil.isNotNullOrEmpty(object.getAccount())) {
				number = number + 1;
			} else {
				i = 0;
			}
		}
		return accountName + String.format("%0" + 3 + "d", number);
	}
}
