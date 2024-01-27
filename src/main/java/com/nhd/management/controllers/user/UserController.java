package com.nhd.management.controllers.user;

import java.util.List;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import com.nhd.management.dto.UserDto;
import com.nhd.management.form.UserForm;
import com.nhd.management.models.User;
import com.nhd.management.services.user.IUserManagementService;
import com.nhd.management.utils.MngCommonUtils;
import jakarta.validation.Valid;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/users")
public class UserController {
  private final IUserManagementService userManagementService;

  // add an initbinder
  // remove leading and trailing whitespacce
  // resolve issue for our validation
  @InitBinder
  public void initBinder(WebDataBinder dataBinder) {
    StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
    dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
  }

  public UserController(IUserManagementService theUserManagementService) {
    userManagementService = theUserManagementService;
  }

  @GetMapping("")
  public String index(Model theModel) {
    List<User> list = userManagementService.getAllListUser();
    theModel.addAttribute("users", list);
    return "pages/users/index";
  }

  @GetMapping("/register")
  public String pageRegister(Model theModel) {
    UserForm form = new UserForm();
    theModel.addAttribute("userForm", form);
    return "pages/users/register";
  }

  @PostMapping("/save")
  public String saveUser(@Valid @ModelAttribute("userForm") UserForm theForm,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "pages/users/register";
    }
    // Create user
    UserDto newUser = new UserDto();
    if(!MngCommonUtils.isEmpty(theForm.getId())) {
      newUser.setId(theForm.getId());
    }
    newUser.setFullName(theForm.getFullName());
    newUser.setEmail(theForm.getEmail());
    newUser.setUsername(theForm.getUsername());
    newUser.setPassword(theForm.getPassword());
    userManagementService.save(newUser);
    return "redirect:/users";
  }

  @GetMapping("/detail")
  public String showDetail(@RequestParam("userId") int theId, Model theModel) {
    User userFound = userManagementService.findById(theId);
    if (MngCommonUtils.isEmpty(userFound)) {
      return "redirect:/users";
    }
    UserForm userForm = new UserForm();
    String id = userFound.getId().toString();
    userForm.setId(Integer.parseInt(id));
    userForm.setFullName(userFound.getFullName());
    userForm.setEmail(userFound.getEmail());
    userForm.setUsername(userFound.getUsername());
    userForm.setRoles(userFound.getRoles());
    theModel.addAttribute("userForm", userForm);
    return "pages/users/register";
  }

  @GetMapping("/delete")
  public String delete(@RequestParam("userId") int theId) {
    User userFound = userManagementService.findById(theId);
    if (MngCommonUtils.isEmpty(userFound)) {
      return "redirect:/users";
    }
    userManagementService.deleteById(theId);
    return "redirect:/users";
  }
}
