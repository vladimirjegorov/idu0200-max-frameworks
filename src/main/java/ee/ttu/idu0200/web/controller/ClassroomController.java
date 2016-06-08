package ee.ttu.idu0200.web.controller;

import ee.ttu.idu0200.web.converter.ClassroomConverterService;
import ee.ttu.idu0200.web.dao.ClassroomDao;
import ee.ttu.idu0200.web.domain.Classroom;
import ee.ttu.idu0200.web.form.ClassroomForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

import static java.lang.String.format;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/classroom/s")
public class ClassroomController {

  private Logger LOG = LogManager.getLogger(ClassroomController.class);

  @Autowired
  private ClassroomDao classroomDao;
  @Autowired
  private ClassroomConverterService classroomConverterService;

  @RequestMapping(method = GET, params = "id")
  public String findClassroom(@RequestParam long id, Model model) {
    ClassroomForm classroomForm;
    if (model.containsAttribute("classroomForm")) {
      classroomForm = (ClassroomForm) model.asMap().get("classroomForm");
    } else {
      Classroom classroom = classroomDao.findOne(id);

      if (classroom == null) {
        return "classroom/classroomNotFound";
      }
      LOG.info(format("Opening form for classroom with id: %s", classroom.getId()));
      classroomForm = classroomConverterService.toClassroomForm(classroom);
    }

    model.addAttribute("classroom", classroomForm);

    return "classroom/classroom";
  }

  @RequestMapping(method = POST, params = "action=save")
  public RedirectView updateClassroom(RedirectAttributes ra, @Valid ClassroomForm classroomForm, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      ra.addFlashAttribute("org.springframework.validation.BindingResult.classroom", bindingResult);
      ra.addFlashAttribute("classroomForm", classroomForm);
    } else {
      Classroom classroom = classroomConverterService.toClassroom(classroomForm);
      LOG.info(format("Updating classroom with id: %s", classroom.getId()));
      classroomDao.save(classroom);

      ra.addFlashAttribute("successMessage", "success");
    }

    // will redirect to findClassroom()
    RedirectView redirectView = new RedirectView("/classroom/s", true);
    // will keep the 'id' parameter in url after redirect
    ra.addAttribute("id", classroomForm.getId());

    return redirectView;
  }
}
