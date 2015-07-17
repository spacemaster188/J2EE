package by.gsu.epamlab.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="messages")
public class MessageEntity {
     
    @Id
    @Column(name="idMsg")
    @GeneratedValue
    private Integer idMsg;
     
    @Column(name="usersId")
    private int usersId;
    
    @Column(name="msg")
    private String msg;

	public Integer getIdMsg() {
		return idMsg;
	}

	public int getUsersId() {
		return usersId;
	}

	public void setUsersId(int usersId) {
		this.usersId = usersId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setIdMsg(Integer idMsg) {
		this.idMsg = idMsg;
	}

}