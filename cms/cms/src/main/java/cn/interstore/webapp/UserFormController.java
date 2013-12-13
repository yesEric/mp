package cn.interstore.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import cn.interstore.dom.User;

@Controller
@RequestMapping("/admin/*")
public class UserFormController {

	@RequestMapping("login.do")
	public String login(User user) {

		System.out.println(user.getUserName());
		System.out.println(user.getPassword());

		return "/admin/userList";
	}

	@RequestMapping("userForm.do")
	public ModelAndView showForm() {
//		return new ModelAndView(new RedirectView("login.jsp"));
		return new ModelAndView("admin/login");

	}

}
