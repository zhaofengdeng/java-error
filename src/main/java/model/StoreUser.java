package model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import io.ebean.Ebean;

@Entity
@Table(name = "tbl_store_user")
public class StoreUser {

	/**
	 * id
	 */
	 @Id
	 @Column(name = "id", nullable = false)
	 public String id;



	/**
	 * 店员账号
	 */
	 @Column(name = "account", nullable = true)
	 public String account;

	/**
	 * 店员名称
	 */
	 @Column(name = "name", nullable = true)
	 public String name;

	/**
	 * 店员电话
	 */
	@Column(name = "phone", nullable = true)
	public String phone;


	/**
	 * 小程序openid
	 */
	@Column(name = "open_id", nullable = true)
	public String openId;


	/**
	 * 积分
	 */
	@Column(name = "score", nullable = true)
	public Integer score;

	/**
	 * 门店ID
	 */
	@Column(name = "store_id", nullable = true)
	 public String storeId;

	/**
	 * 插入者
	 */
	@Column(name = "inserter", nullable = true)
	 public String inserter;
		
	/**
	 * 插入时间
	 */
	@Column(name = "inserted_at", nullable = true)
	 public Date insertedAt;
		
	/**
	 * 更新者
	 */
	@Column(name = "updater", nullable = true)
	 public String updater;
		
	/**
	 * 更新时间
	 */
	@Column(name = "updated_at", nullable = true)
	 public Date updatedAt;

	/**
	 * 删除标记
	 */
	@Column(name = "deleted", nullable = true)
	public Integer deleted;

	public void save(){
		this.setId(UUID.randomUUID().toString());
		this.setDeleted(0);
		this.setInsertedAt(new Date());
		this.setInserter("system");
		Ebean.save(this);
	}

	public void update(){
		this.setUpdatedAt(new Date());
		this.setUpdater("system");
		Ebean.update(this);
	}

	public void delete(){
		this.setUpdatedAt(new Date());
		this.setUpdater("system");
		this.setDeleted(1);
		Ebean.update(this);
	}





	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getInserter() {
		return inserter;
	}

	public void setInserter(String inserter) {
		this.inserter = inserter;
	}

	public Date getInsertedAt() {
		return insertedAt;
	}

	public void setInsertedAt(Date insertedAt) {
		this.insertedAt = insertedAt;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

}
