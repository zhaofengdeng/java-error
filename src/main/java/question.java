import java.util.HashMap;
import java.util.Map;



import io.ebean.Ebean;
import io.ebean.SqlRow;
/**
 * 调用snCheck,返回的CheckForm各个code都代表什么意思
 * @author admin
 *
 */
public class question {

	private Boolean checkCode(Integer code) {
		if (code == 0 ) {
			return true;
		}
		return false;
	}

	public CheckForm snCheck(String sn, String productId, String storeId) {
		if (StringUtil.isNullOrEmpty(sn)) {
			return new CheckForm().setError(-1, "");
		}
		{
			String searchSnQtySql = "select f_search_sn_count(:sn)  as qty from dual ";
			SqlRow one = Ebean.createSqlQuery(searchSnQtySql).setParameter("sn", sn).findOne();
			if (one.getInteger("qty") > 0) {
				return new CheckForm().setError(-2, "");
			}
		}

		String hlStoreId = "";
		String hlCustomId = "";
		String hlProductId = "";
		{
            HLProductMappingService hlProductMappingService = new HLProductMappingService();
            hlProductId = hlProductMappingService.searchHLProductId(productId);
            if (StringUtil.isNullOrEmpty(hlProductId)) {
                return new CheckForm().setError(-3, "");
            }
			HLStoreMappingService hlStoreMappingService = new HLStoreMappingService();
			hlCustomId=hlStoreMappingService.searchHLCustomerCode(storeId);
            if (StringUtil.isNullOrEmpty(hlCustomId)) {
                return new CheckForm().setError(-6, "");
            }
			hlStoreId=hlCustomId;
			return hlInterfaceCheck(sn, hlProductId, hlStoreId, hlCustomId);
		}

		

	}

	public  CheckForm hlInterfaceCheck(String sn, String hlProductId, String hlStoreId,String hlCustomId) {
		try {
			Map<String, String> map = new HashMap<>();
			map.put("hlProductId", hlProductId);
			map.put("sn", sn);
			map.put("hlStoreId", hlStoreId);
			map.put("hlCustomId", hlCustomId);
			PropertiesUtil propertiesUtil = new PropertiesUtil("project");
			String url = propertiesUtil.getUrl("hl_sn_check_url", null);
			String valueUrl = propertiesUtil.getUrl("hl_sn_check_value", map);
			
			String urlMsg = HttpUtil.post("",url, valueUrl,10000,10000);
			JSONObject jsonObject = JSONObject.fromObject(urlMsg);
			if (!checkCode(jsonObject.getInt("Code"))) {
                 CheckForm checkForm = new CheckForm();
                 checkForm.setSuceessModel(urlMsg);
				return checkForm.setError(-4, jsonObject.getString("Result"));
			}
			return new CheckForm().setSuccess(null);
		} catch (Exception e) {
			e.printStackTrace();
			return new CheckForm().setError(-5, "");
		}
	}
}
