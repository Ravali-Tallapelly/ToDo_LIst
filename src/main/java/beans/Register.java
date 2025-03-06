package beans;

import java.util.Objects;

public class Register {
	private int regid;
	private String ftName;
	private String lName;
	private String email;
	private String password;
	private long phone;
	private String address;
	
	public Register() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Register(int regid, String ftName, String lName, String email, String password, long phone, String address) {
		super();
		this.regid = regid;
		this.ftName = ftName;
		this.lName = lName;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.address = address;
	}
	public int getRegid() {
		return regid;
	}
	public void setRegid(int regid) {
		this.regid = regid;
	}
	public String getFtName() {
		return ftName;
	}
	public void setFtName(String ftName) {
		this.ftName = ftName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(address, email, ftName, lName, password, phone, regid);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Register other = (Register) obj;
		return Objects.equals(address, other.address) && Objects.equals(email, other.email)
				&& Objects.equals(ftName, other.ftName) && Objects.equals(lName, other.lName)
				&& Objects.equals(password, other.password) && phone == other.phone && regid == other.regid;
	}
	@Override
	public String toString() {
		return "Register [regid=" + regid + ", ftName=" + ftName + ", lName=" + lName + ", email=" + email
				+ ", password=" + password + ", phone=" + phone + ", address=" + address + "]";
	}
	
}
