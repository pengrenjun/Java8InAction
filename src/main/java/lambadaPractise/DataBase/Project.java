
package lambadaPractise.DataBase;


import java.util.Date;

/**
 * @Description: 项目
 */
public class Project implements java.io.Serializable {


	/**项目名称*/
	private String projectName;
	/**项目状态*/
	private Integer projectStatus;
	/**项目开始时间*/
	private Date startDate;
	/**项目经理*/
	private String projectManager;
	/**项目类型*/
	private String projectTypeCode;
	/**客户*/
	private String customerNumber;

	public Project(String projectName, Integer projectStatus, Date startDate, String projectManager, String projectTypeCode, String customerNumber) {
		this.projectName = projectName;
		this.projectStatus = projectStatus;
		this.startDate = startDate;
		this.projectManager = projectManager;
		this.projectTypeCode = projectTypeCode;
		this.customerNumber = customerNumber;
	}

	public Project() {
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Integer getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(Integer projectStatus) {
		this.projectStatus = projectStatus;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}

	public String getProjectTypeCode() {
		return projectTypeCode;
	}

	public void setProjectTypeCode(String projectTypeCode) {
		this.projectTypeCode = projectTypeCode;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	@Override
	public String toString() {
		return "Project{" +
				"projectName='" + projectName + '\'' +
				", projectStatus=" + projectStatus +
				", startDate=" + startDate +
				", projectManager='" + projectManager + '\'' +
				", projectTypeCode='" + projectTypeCode + '\'' +
				", customerNumber='" + customerNumber + '\'' +
				'}';
	}
}
