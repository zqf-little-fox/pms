package junit.test;

import com.ujiuye.cust.bean.Customer;
import com.ujiuye.cust.service.CustomerServiceImpl;
import com.ujiuye.info.bean.Email;
import com.ujiuye.info.bean.EmailExample;
import com.ujiuye.info.mapper.EmailMapper;
import com.ujiuye.pro.bean.*;
import com.ujiuye.pro.controller.ModelController;
import com.ujiuye.pro.controller.ProjectController;
import com.ujiuye.pro.mapper.AttachmentMapper;
import com.ujiuye.pro.mapper.FunctionMapper;
import com.ujiuye.pro.mapper.ModelMapper;
import com.ujiuye.pro.mapper.ProjectMapper;
import com.ujiuye.pro.service.FunctionService;
import com.ujiuye.pro.service.ModelService;
import com.ujiuye.pro.service.ModelServiceImpl;
import com.ujiuye.pro.service.ProjectServiceImpl;
import com.ujiuye.sys.bean.Employee;
import com.ujiuye.sys.mapper.EmployeeMapper;
import com.ujiuye.sys.service.EmployeeServiceImpl;
import com.ujiuye.usual.bean.Task;
import com.ujiuye.usual.bean.TaskExample;
import com.ujiuye.usual.mapper.TaskMapper;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AppList {

    @Test
    public void test02() throws Exception{

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans-email.xml");
        JavaMailSenderImpl bean = context.getBean(JavaMailSenderImpl.class);

        //邮件对象
        MimeMessage mimeMessage = bean.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);


        helper.setFrom("zqf1481656983@163.com");
        helper.setTo("zqf1481656983@163.com");
        helper.setSubject("这是0708班的邮件测试");
        helper.setText("嘿嘿嘿嘿嘿嘿嘿，阿哦哦阿哦好奥");

        String path = "D:\\idea\\pms\\src\\main\\webapp\\upload\\9506828922c343bb873cc9896e52823d1.jpg";
        FileSystemResource file = new FileSystemResource(new File(path));
        helper.addAttachment("1.jpg",file);

        //发送邮件
        bean.send(mimeMessage);

        System.out.println("EMAIL PASS");

    }
    @Test
    public void test01(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        EmailMapper bean = context.getBean(EmailMapper.class);
        List<Email> emails = bean.selectByExample(new EmailExample());
        for (Email email : emails) {
            System.out.println(email.getEmployee().getEname());
        }

        /*EmployeeMapper bean = context.getBean(EmployeeMapper.class);
        List<Employee> infoList = bean.getInfoList(2);
        for (Employee employee : infoList) {
            System.out.println(employee.getEname());
        }*/

        /*TaskMapper bean = context.getBean(TaskMapper.class);
        Task task = bean.getTaskById(1);
        System.out.println(task.getEmployee1().getEname());*/

        /*TaskMapper bean = context.getBean(TaskMapper.class);
        List<Task> eid = bean.getEmpFkByEname("四");
        System.out.println(eid.get(0).getEmployee().getEname());*/
        /*ModelServiceImpl bean = context.getBean(ModelServiceImpl.class);
        List<Model> models = bean.getModNameList(2);
        for (Model model : models) {
            System.out.println(model.getModname());
        }*/

        /* ProjectMapper bean = context.getBean(ProjectMapper.class);
        List<Project> projects = bean.selectByExample(new ProjectExample());
        for (Project project : projects) {
            System.out.println(project.getCustomer().getComname());
        }*/

        /*ProjectServiceImpl bean = context.getBean(ProjectServiceImpl.class);
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        List<Project> projects = bean.jsonInList(ids);
        for (Project project : projects) {
            System.out.println(project.getPname());
        }*/

    }
}
