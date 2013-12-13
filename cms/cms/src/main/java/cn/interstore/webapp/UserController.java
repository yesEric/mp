package cn.interstore.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/users.do")
public class UserController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest(
			@RequestParam(required = false, value = "q") String query)
			throws Exception {
		Model model = new ExtendedModelMap();
		model.addAttribute(Constants.USER_LIST, "UserList");
		return new ModelAndView("admin/userList", model.asMap());
	}
}
