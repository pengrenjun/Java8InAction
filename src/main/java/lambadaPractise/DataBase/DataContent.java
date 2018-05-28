package lambadaPractise.DataBase;

import lambadaPractise.Util.DateUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * 练习的数据内容
 */
public class DataContent {

    public static final List<Project> projectList=new ArrayList<>();


    static {
        try {
            projectList.add(new Project("项目A",1,DateUtil.convertStrToDate("2017-01-29"),"小明","pcode1","CUSTOMER1"));
            projectList.add(new Project("项目B",2,DateUtil.convertStrToDate("2018-05-28"),"小李","pcode2","CUSTOMER2"));
            projectList.add(new Project("项目C",1,DateUtil.convertStrToDate("2018-05-21"),"阿强","pcode246456734567","CUSTOME5785"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
