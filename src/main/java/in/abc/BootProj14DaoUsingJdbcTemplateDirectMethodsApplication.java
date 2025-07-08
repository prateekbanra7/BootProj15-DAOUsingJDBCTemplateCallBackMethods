package in.abc;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import in.abc.dto.StudentDTO;
import in.abc.service.IStudentMgmtService;

@SpringBootApplication
public class BootProj14DaoUsingJdbcTemplateDirectMethodsApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(BootProj14DaoUsingJdbcTemplateDirectMethodsApplication.class, args);
		
		IStudentMgmtService service = applicationContext.getBean(IStudentMgmtService.class);
		
				
		List<StudentDTO> list = service.fetchStudentByName("sachin", "dhoni");
		list.forEach(System.out::println);
		
		((ConfigurableApplicationContext) applicationContext).close();
	}

}
